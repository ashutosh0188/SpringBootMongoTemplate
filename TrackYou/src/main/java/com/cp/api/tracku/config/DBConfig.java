package com.cp.api.tracku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
public class DBConfig {

	@Bean
	public MongoClient mongoClient() {
		String uri = "mongodb://serverip:port";
		return new MongoClient(new MongoClientURI(uri));
	}
	
	@Bean
	public MongoTemplate mongoTemplate(){
		return new MongoTemplate(mongoClient(), "dbname");
	}

}
