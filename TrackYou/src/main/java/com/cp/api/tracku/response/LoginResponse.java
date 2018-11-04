package com.cp.api.tracku.response;

import com.cp.api.tracku.entity.User;

public class LoginResponse implements ResponseEntity<LoginResponse>{
	private String message;
	private String code;
	private User payload;

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

	public User getPayload() {
		return payload;
	}

	public void setPayload(User payload) {
		this.payload = payload;
	}

}
