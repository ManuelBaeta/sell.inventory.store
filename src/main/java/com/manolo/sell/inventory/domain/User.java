package com.manolo.sell.inventory.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="USERS")
public class User {

	public static enum STATES{ ACTIVE, CANCELLED, CLOSE}
	
	@JsonProperty("user-id")
	@Column(name="USER_ID", nullable=false)
	@SequenceGenerator(name="User_Gen", sequenceName="USER_SEQ")
	@Id @GeneratedValue(generator="User_Gen")
	private int userId ;
	
	@NotBlank
	@JsonProperty("user-name")
	@Column(name="USER_NAME", nullable=false)
	private String userName;
	
	@NotBlank
	@JsonProperty("address")
	@Column(name="ADDRESS", nullable=false)
	private String address;
	
	@Min(18)
	@Max(200)
	@JsonProperty("age")
	@Column(name="AGE", nullable=false)
	private int age;
	
	//"yyyy-MM-dd HH:mm:ss"	2001-07-04 12:08:56
	@JsonProperty("added-at")
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="ADDED_AT", nullable=false)
	private LocalDateTime addedAt;
	
	@JsonProperty("last-updated")
	@JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name="LAST_UPDATED", nullable=false)
	private LocalDateTime lastUpdated;
	
	@NotBlank
	@JsonProperty("dni")
	@Column(name="DNI",nullable=false, unique=true)
	private String dni;
	
	@JsonProperty("state")
	@Column(name="STATE", nullable=false)
	@Enumerated(EnumType.STRING)
	private STATES state = STATES.ACTIVE;
	
	@JsonProperty("user-version")
	@Column(name="VERSION")
	private int version;
	
	@JsonIgnore
	@OneToMany(mappedBy="user", cascade= {CascadeType.REMOVE})
	private Collection<Order> orders;
	
	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public int getVersion() {
		return version;
	}

	public User() {}

	public User makeInsertable() {
		this.addedAt = LocalDateTime.now();
		this.lastUpdated= LocalDateTime.now();
		this.state = STATES.ACTIVE;
		return this;
	}
	
	public User simulateInsert() {
		this.addedAt = LocalDateTime.now();
		this.lastUpdated= LocalDateTime.now();
		this.userId = new Random().nextInt();
		return this;
	}
	
	public static User giveMeDefaultUser() {
		User newUser = new User();
		newUser.addedAt = LocalDateTime.now();
		newUser.address = "default-address";
		newUser.age = 18;
		newUser.dni = "default-dni";
		newUser.lastUpdated = LocalDateTime.now();
		newUser.state = STATES.ACTIVE;
		newUser.userId = 101;
		newUser.userName = "default-userName";
		newUser.version = 1;
		return newUser;
	}
	
	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getAddress() {
		return address;
	}

	public int getAge() {
		return age;
	}

	public LocalDateTime getAddedAt() {
		return addedAt;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public String getDni() {
		return dni;
	}

	public STATES getState() {
		return state;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAddedAt(LocalDateTime addedAt) {
		this.addedAt = addedAt;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setState(STATES state) {
		this.state = state;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=").append(userId).append(", userName=").append(userName).append(", address=")
				.append(address).append(", age=").append(age).append(", addedAt=").append(addedAt)
				.append(", lastUpdated=").append(lastUpdated).append(", dni=").append(dni).append(", state=")
				.append(state).append(", version=").append(version).append("]");
		return builder.toString();
	}
	
}
