package com.yoyo.petgram.models;



import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.StoredFile;

public class FileUpload extends StoredFile{
	
	@NotNull(message="Description is required")
	@Size(min=1, max=255, message="Description must be between 1 and 255 characters")
	private String content;
	
	@NotNull(message="Photo is required")
	private MultipartFile file;
	
	//==================== RELATIONSHIP ======================//	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="creator_id")
	private User creator;

	
	//==================== GETTERS AND SETTERS ======================//	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	
	
	
	
	
}
