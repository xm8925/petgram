package com.yoyo.petgram.models;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="posts")
public class Post {

	//==================== PRIMARY KEY ======================//	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	//==================== MEMBER VARIABLES ======================//	
	
	@NotNull(message="Description is required")
	@Size(min=1, max=255, message="Description must be between 1 and 255 characters")
	private String content;
	
	@NotNull(message="Photo is required")
	private String file;

	//==================== DATA CREATION TRACKERS ======================//	

	@Column(updatable=false) //This means that the createdAt does not update on ever "save", we want to keep this data just like it is once the Object is created
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;

	//==================== RELATIONSHIPS ======================//	

	//==User==//
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="creator_id")
	private User creator;
	
	//==Likes Join Table==//
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "likes",
			joinColumns = @JoinColumn(name = "post_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
	)
    private List<User> users = new ArrayList<User>();
	
	//==================== CONSTRUCTORS ======================//	
	
	public Post() {
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
}
