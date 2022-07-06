package com.yoyo.petgram.repositories;



import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yoyo.petgram.models.Post;

@Repository
public interface PostRepo extends CrudRepository<Post, Long>{

	List<Post> findAll();

}
