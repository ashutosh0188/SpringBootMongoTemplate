package com.cp.api.tracku.response;

import java.util.List;

import com.cp.api.tracku.entity.User;

public class UserListResponse implements ResponseEntity<UserListResponse>{
	private String message;
	private String code;
	private List<User> payload;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<User> getPayload() {
		return payload;
	}
	public void setPayload(List<User> payload) {
		this.payload = payload;
	}
	
}
