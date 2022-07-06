package com.yoyo.petgram.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.yoyo.petgram.models.LoginUser;
import com.yoyo.petgram.models.User;
import com.yoyo.petgram.repositories.UserRepo;

@Service
public class UserService {

	@Autowired 
	private UserRepo repo;
	
	//==================== REGISTRER AND LOGIN ======================//	
	
	public User register(User newUser, BindingResult result) {
		//Check to see if the email exist in the database if it does we reject the email
		if(repo.findByEmail(newUser.getEmail()).isPresent()){
			result.rejectValue("email", "Unique", "Email is already in use");
		}
		//Check to see if the password and confirmPassword matches and if doesnt we reject the Value
		if(!newUser.getPassword().equals(newUser.getConfirmPassword())) {
			result.rejectValue("confirmPassword","Matches", "Password and Confirm Password must match");
		}
		//Check one more time for any extra errors, if there are we return null
		if(result.hasErrors()) {
			return null;
		}
		//if no errors then hash password, and add salt
		String hashBrowns = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		//set the newUser Object password with the hashed version
		newUser.setPassword(hashBrowns);
		//then we pass that object  to our save
		return repo.save(newUser);
	}
	
public User login(LoginUser newLogin, BindingResult result) {
	//so the invalid credentials doesn't show up
	if(result.hasErrors()) {
		return null;
	}
	//getting the user email and checking if it exist in the user database
		Optional<User> optUser = repo.findByEmail(newLogin.getEmail());
		// if User is not present then reject the value
		if(!optUser.isPresent()) {
			//if the email is not present
			result.rejectValue("email", "Unique", "Invalid Credentials");
		
			return null;
		}
		// we get the user
		User user = optUser.get();
		//if not equal to each other reject the value
		if(!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
			result.rejectValue("password", "Matches", "Invalid Credentials");
		
			return null;
		}
		//everything is good and we return the Object
		return user;
	}
	
	//==================== CREATE / UPDATE ======================//	
public User updateUser(User user) {
	return repo.save(user);
}	


//==================== READ ======================//	

public User getOne(Long id) {
	//sort of like an optional find ID or else return null
	return repo.findById(id).orElse(null);
}

public List<User> allUsers(){
	return repo.findAll();
}

public User findById(Long id) {
	Optional<User> optionalUser = repo.findById(id);
	if(optionalUser.isPresent()) {
		return optionalUser.get();
	}else {
		return null;
	}
}


	//==================== DELETE======================//	
}
