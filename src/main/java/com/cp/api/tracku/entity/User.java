package com.cp.api.tracku.entity;

public class User {
	private String id;
	private String client;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String mobile;
	private String role;
	private String notiToken;
	private String deviceId;
	private String status;
	private long createTime;
	private long lastModified;
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getNotiToken() {
		return notiToken;
	}

	public void setNotiToken(String notiToken) {
		this.notiToken = notiToken;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getLastModified() {
		return lastModified;
	}

	public void setLastModified(long lastModified) {
		this.lastModified = lastModified;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", client=" + client + ", email=" + email
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobile=" + mobile + ", role="
				+ role + ", notiToken=" + notiToken + ", deviceId=" + deviceId
				+ ", status=" + status + ", createTime=" + createTime
				+ ", lastModified=" + lastModified + ", token=" + token + "]";
	}

}
