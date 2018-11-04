package com.cp.api.tracku.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.api.tracku.entity.DoublyEntity;
import com.cp.api.tracku.entity.User;
import com.cp.api.tracku.entity.UserPasswordReset;
import com.cp.api.tracku.repository.UserRepository;
import com.cp.api.tracku.response.CommonResponse;
import com.cp.api.tracku.response.LoginResponse;
import com.cp.api.tracku.response.ResponseEntity;
import com.cp.api.tracku.response.UserListResponse;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public ResponseEntity<CommonResponse> processCreateUserRequest(User user) {

		user.setStatus("2");
		long timeInMillis = Calendar.getInstance().getTimeInMillis();
		user.setCreateTime(timeInMillis);
		user.setLastModified(timeInMillis);

		String msg = repository.createUser(user);
		CommonResponse cr = new CommonResponse();

		cr.setMessage(msg);
		if (msg != null) {
			cr.setCode("1000");
		} else {
			cr.setCode("1001");
		}

		return cr;
	}

	public ResponseEntity<LoginResponse> processLoginRequest(User user) {
		DoublyEntity de = repository.verifyLogin(user);
		LoginResponse lr = new LoginResponse();

		lr.setCode("1002");
		if (de == null) {
			lr.setMessage("User not found in the system.");
		} else if (de.getObj() != null) {
			lr.setCode("1000");
			lr.setMessage("User logged in successfully.");
			User u = (User) de.getObj();
			lr.setPayload(u);
		} else {

			lr.setMessage("User is already active on one device.");
		}
		return lr;
	}

	public ResponseEntity<CommonResponse> processResetPasswordRequest(UserPasswordReset upr) {
		CommonResponse cr = new CommonResponse();
		boolean status = repository.resetPassword(upr);

		if (status) {
			cr.setCode("1000");
			cr.setMessage("Password reset successfully.");
		} else {
			cr.setCode("1002");
			cr.setMessage("Password reset failed due to wrong information");
		}

		return cr;
	}
	
	public ResponseEntity<LoginResponse> processGetUserProfileRequest(String id, String client, String token) {
		User u = repository.getUserProfile(id, client, token);
		LoginResponse lr = new LoginResponse();
		if(u!=null) {
			lr.setCode("1000");
			lr.setMessage("User profile retrieved successfully.");
			lr.setPayload(u);
			return lr;
		}
		
		lr.setCode("1001");
		lr.setMessage("User profile could not retrieved.");
		return lr;
	}
	
	public ResponseEntity<CommonResponse> processLogoutUserRequest(String id, String client) {
		boolean status = repository.logoutUser(id, client);
		CommonResponse cr = new CommonResponse();
		if (status) {
			cr.setCode("1000");
			cr.setMessage("User logged out successfully.");
		} else {
			cr.setCode("1002");
			cr.setMessage("User logout failed.");
		}
		return cr;
	}
	
	public ResponseEntity<UserListResponse> processGetAllUsers(String client){
		List<User> users = repository.getAllUsers(client);
		UserListResponse ulr = new UserListResponse();
		
		if(users!=null && users.size()>0){
			ulr.setCode("1000");
			ulr.setMessage("All users retrieved successfully.");
			List<User> ul = new ArrayList<User>();
			for(User u : users){
				User ur = new User();
				ur.setEmail(u.getEmail());
				ur.setFirstName(u.getFirstName());
				ur.setLastName(u.getLastName());
				ur.setMobile(u.getMobile());
				ur.setRole(u.getRole());
				ur.setToken(u.getToken());
				ur.setId(u.getId());
				ul.add(ur);
			}
			ulr.setPayload(ul);
			return ulr;
		}
		
		ulr.setCode("1001");
		ulr.setMessage("Users could not retrieved.");
		return ulr;
	}

}