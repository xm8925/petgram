package com.yoyo.petgram.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yoyo.petgram.models.Post;
import com.yoyo.petgram.repositories.PostRepo;


@Service
public class PostService {

	@Autowired
	private PostRepo postRepo;
	
	//==================== CREATE / UPDATE ======================//	
	
	public Post save(Post post) {
		return postRepo.save(post);
	}
	
	public Post updatePost(Post post) {
		return postRepo.save(post);
	}

	//==================== READ ======================//	
	
	public List<Post> getAll(){
		return postRepo.findAll();
	}
	
	//Shorthand way to get one post
	public Post getOne(Long id) {
		return postRepo.findById(id).orElse(null);
	}
	
	public Post findById(Long id) {
		Optional<Post> optionalPost = postRepo.findById(id);
		if(optionalPost.isPresent()) {
			return optionalPost.get();
		}else {
			return null;
		}
	}
	
	//==================== DELETE======================//	
	
		public void delete(Long id) {
			postRepo.deleteById(id);
		}
}
