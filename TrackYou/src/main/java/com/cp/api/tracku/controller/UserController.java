package com.cp.api.tracku.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cp.api.tracku.entity.User;
import com.cp.api.tracku.entity.UserPasswordReset;
import com.cp.api.tracku.response.CommonResponse;
import com.cp.api.tracku.response.LoginResponse;
import com.cp.api.tracku.response.ResponseEntity;
import com.cp.api.tracku.response.UserListResponse;
import com.cp.api.tracku.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/cu")
	public ResponseEntity<CommonResponse> createUser(@RequestBody User user) {
		
		return service.processCreateUserRequest(user);
	}

	@PostMapping("/vu")
	public ResponseEntity<LoginResponse> verifyUser(@RequestBody User user) {
		
		return service.processLoginRequest(user);
	}

	@PostMapping("/rp")
	public ResponseEntity<CommonResponse> resetPassword(
			@RequestBody UserPasswordReset upr) {
		
		return service.processResetPasswordRequest(upr);
	}

	@GetMapping("/gup/{id}")
	public ResponseEntity<LoginResponse> getUserProfile(
			@PathVariable String id, @PathParam("client") String client,
			@PathParam("token") String token) {

		return service.processGetUserProfileRequest(id, client, token);
	}

	@GetMapping("/lu/{id}")
	public ResponseEntity<CommonResponse> logoutUser(@PathVariable String id,
			@PathParam("client") String client) {
		
		return service.processLogoutUserRequest(id, client);
	}
	
	@GetMapping("/gau/{id}")
	public ResponseEntity<UserListResponse> getAllUsers(
			@PathVariable String id, 
			@PathParam("client") String client,
			@PathParam("token") String token) {
		
		return service.processGetAllUsers(client);
	}
	
}