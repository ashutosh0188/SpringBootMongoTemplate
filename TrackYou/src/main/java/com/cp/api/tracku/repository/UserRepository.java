package com.cp.api.tracku.repository;

import java.util.List;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.cp.api.tracku.entity.DoublyEntity;
import com.cp.api.tracku.entity.User;
import com.cp.api.tracku.entity.UserPasswordReset;
import com.mongodb.client.result.UpdateResult;

import static com.cp.api.tracku.collections.UserCollections.USER;

@Repository
public class UserRepository {
	@Autowired
	private MongoTemplate mongoTemplate;

	public String createUser(User user) {
		Query query = new Query();
		CriteriaDefinition cd = Criteria.where("email").is(user.getEmail())
				.and("client").is(user.getClient());
		query.addCriteria(cd);

		if (mongoTemplate.find(query, User.class).size() == 0) {
			User u = mongoTemplate.insert(user);
			
			if (u != null)
				return "User created successfully.";
		} else {
			return "Entered email already exist.";
		}

		return "User creation failed.";
	}

	public DoublyEntity verifyLogin(User u) {
		Query query = new Query();
		CriteriaDefinition cd = Criteria.where("email").is(u.getEmail())
				.and("password").is(u.getPassword()).and("client")
				.is(u.getClient());
		query.addCriteria(cd);
		List<User> user = mongoTemplate.find(query, User.class);
		if (user.size()==0)
			return null;

		User ur = user.get(0);

		DoublyEntity de = new DoublyEntity();

		// checking user is already logged in
		User t = mongoTemplate.findById(new ObjectId(ur.getId()), User.class,
				USER);
		if (t != null && t.getToken() != null) {
			de.setMessage("User already logged in on one device");
			return de;
		}

		Query q1 = new Query();
		CriteriaDefinition c1 = Criteria.where("_id").is(ur.getId());
		q1.addCriteria(c1);

		// this update method is temporarily
		String token = UUID.randomUUID().toString();
		mongoTemplate.updateFirst(q1,
				Update.update("notiToken", u.getNotiToken()), User.class);
		mongoTemplate.updateFirst(q1,
				Update.update("deviceId", u.getDeviceId()), User.class);
		mongoTemplate
				.updateFirst(q1, Update.update("token", token), User.class);

		ur.setDeviceId(u.getDeviceId());
		ur.setNotiToken(u.getNotiToken());
		ur.setToken(token);
		de.setObj(ur);
		return de;
	}

	public boolean resetPassword(UserPasswordReset upr) {
		Query q = new Query();
		
		CriteriaDefinition cd = Criteria.where("_id").is(upr.getId())
				.and("client").is(upr.getClient())
				.and("password").is(upr.getOldPassword())
				.and("token").is(upr.getToken());
		q.addCriteria(cd);
		Update u = Update.update("password", upr.getNewPassword());
		UpdateResult ur = mongoTemplate.updateFirst(q, u, User.class, USER);
		
		return ur.getModifiedCount()>0? true : false;
	}
	
	public User getUserProfile(String id, String client, String token){
		Query q = new Query();
		CriteriaDefinition cd = Criteria.where("_id").is(id)
				.and("client").is(client).and("token")
				.is(token);
		q.addCriteria(cd);
		List<User> u = mongoTemplate.find(q, User.class);
		if(u.size()>0) return u.get(0);
		return null;
	}
	
	public boolean logoutUser(String id, String client){
		Query q = new Query();
		CriteriaDefinition cd = Criteria.where("_id").is(id)
				.and("client").is(client);
		q.addCriteria(cd);
		Update u = Update.update("token", null);
		UpdateResult ur = mongoTemplate.updateFirst(q, u, User.class);
		return ur.getModifiedCount()>0 ? true : false;
	}
	
	public List<User> getAllUsers(String client){
		Query q = new Query();
		CriteriaDefinition cd = Criteria.where("client").is(client);
		q.addCriteria(cd);
		List<User> users = mongoTemplate.find(q, User.class);
		return users;
	}
}
