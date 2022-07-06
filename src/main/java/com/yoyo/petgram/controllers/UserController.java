package com.yoyo.petgram.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.yoyo.petgram.models.LoginUser;
import com.yoyo.petgram.models.User;
import com.yoyo.petgram.services.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService serv;
	
	//==================== DISPLAY ======================//
	
	@GetMapping("/")
	public String index(Model model, HttpSession session) {
		// if the user is in session redirect to dashboard
		if(session.getAttribute("uuid") != null) {
			return "redirect:/petgram";
		}
		
		model.addAttribute("newUser", new User());
		model.addAttribute("newLogin", new LoginUser());
		
		return "index.jsp";
	}
	
	
	//==================== ACTION ======================//
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session) {
		User user = serv.register(newUser, result);
		
		
		if(result.hasErrors()) {
			model.addAttribute("newLogin", new LoginUser());
			
			return "index.jsp";
		}
		
		session.setAttribute("uuid", user.getId());
		
		return "redirect:/petgram";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
		User user = serv.login(newLogin, result);
		
		if(result.hasErrors()) {
			model.addAttribute("newUser", new User());
			
			return "index.jsp";
		}
		
		session.setAttribute("uuid", user.getId());
		
		return "redirect:/petgram";
	}
	
	

	@GetMapping("/logout") 
	public String logout(HttpSession session) {
		session.removeAttribute("uuid");
		
		return "redirect:/";
	}
	
	
	
}
