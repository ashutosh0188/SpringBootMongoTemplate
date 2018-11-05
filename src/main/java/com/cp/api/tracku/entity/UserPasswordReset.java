package com.cp.api.tracku.entity;

public class UserPasswordReset {
	private String id;
	private String oldPassword;
	private String newPassword;
	private String client;
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "UserPasswordReset [id=" + id + ", oldPassword=" + oldPassword
				+ ", newPassword=" + newPassword + ", client=" + client
				+ ", token=" + token + "]";
	}
}
