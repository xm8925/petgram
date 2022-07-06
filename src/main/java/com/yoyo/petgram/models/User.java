package com.yoyo.petgram.models;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;





@Entity
@Table(name="users")
public class User {

//==================== PRIMARY KEY ======================//	

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;

//==================== MEMBER VARIABLES ======================//	

@NotEmpty(message="First name required")
@Size(min=2, max=45, message="First name must be between 2 and 45 characters")
private String firstName;

@NotEmpty(message="Last name required")
@Size(min=2, max=45, message="Last name must be between 2 and 45 characters")
private String lastName;

@NotEmpty(message="Email required")
@Email(message="Invalid email format")
private String email;

@NotEmpty(message="Password is required")
@Size(min=2, max=255, message="Password must be between 2 and 255 characters")
private String password;

@Transient
@NotEmpty(message="Confirm password is required")
@Size(min=2, max=255, message="Confirm password must be between 2 and 255 characters")
private String confirmPassword;

//==================== DATA CREATION TRACKERS ======================//	

@Column(updatable=false) //This means that the createdAt does not update on ever "save", we want to keep this data just like it is once the Object is created
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date createdAt;
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date updatedAt;

//==================== RELATIONSHIPS ======================//	

//===Post===//

@OneToMany(mappedBy="creator", cascade=CascadeType.ALL,fetch= FetchType.LAZY)
private List<Post> posts;



//==Like==//
@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(
			name = "likes",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "post_id")
	)
   private List<Post> postLiked = new ArrayList<Post>();


//==================== CONSTRUCTORS ======================//	

public User() {
}

//==================== DATA CREATION EVENT ======================//	

//This means right before the object is created save the date the object is createdAt
@PrePersist // (runs the method right before the object is created)
protected void onCreate() {
	this.createdAt = new Date();
}

//This means save the date the Object has been updatedAt
@PreUpdate //(runs a method when the object is modified)
protected void onUpdate() {
	this.updatedAt = new Date();

}

//==================== GETTERS AND SETTERS ======================//	

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
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

public String getConfirmPassword() {
	return confirmPassword;
}

public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}

public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public Date getUpdatedAt() {
	return updatedAt;
}

public void setUpdatedAt(Date updatedAt) {
	this.updatedAt = updatedAt;
}

public List<Post> getPosts() {
	return posts;
}

public void setPosts(List<Post> posts) {
	this.posts = posts;
}

public List<Post> getPostLiked() {
	return postLiked;
}

public void setPostLiked(List<Post> postLiked) {
	this.postLiked = postLiked;
}



}
