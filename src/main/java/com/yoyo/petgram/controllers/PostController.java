package com.yoyo.petgram.controllers;




import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.yoyo.petgram.models.FileUpload;
import com.yoyo.petgram.models.Post;
import com.yoyo.petgram.models.User;
import com.yoyo.petgram.services.PostService;
import com.yoyo.petgram.services.UserService;


@Controller
@PropertySource("classpath:application-dev.properties")
public class PostController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private PostService postServ;
	
	@Value("${apiKey}")
	private String apiKey;
	@Value("${cloudName}")
	private String cloudName;
	@Value("${apiSecret}")
	private String apiSecret;

	//==================== DASHBOARD ======================//
	
	@GetMapping("/petgram")
	public String dashboard(Model model, HttpSession session) {
		// if the user is not in session redirect to the login page
		if(session.getAttribute("uuid") == null) {
				return "redirect:/logout";
		}
		
		model.addAttribute("user", userServ.getOne((Long) session.getAttribute("uuid")));
		model.addAttribute("posts", postServ.getAll());
		

	
		return "dashboard.jsp";
	}
	
	@RequestMapping("/petgram/like/{id}")
	public String liked(@PathVariable("id") Long id, HttpSession session, Model model) {
	// if the user is not in session redirect to the login page
		if(session.getAttribute("uuid") == null) {
			return "redirect:/logout";
		}
		
		Long userId = (Long) session.getAttribute("uuid");
		
		
		
		Post post = postServ.findById(id);
		User user = userServ.findById(userId);
		
		
		user.getPostLiked().add(post);
		userServ.updateUser(user);
		
		model.addAttribute("user", userServ.findById(userId));

		
		return "redirect:/petgram";
	}
	
	@RequestMapping("/petgram/unlike/{id}")
	public String unLiked(@PathVariable("id") Long id, HttpSession session, Model model) {
		// if the user is not in session redirect to the login page
				if(session.getAttribute("uuid") == null) {
					return "redirect:/logout";
				}
		Long userId = (Long) session.getAttribute("uuid");
		
		Post post = postServ.findById(id);
		User user = userServ.findById(userId);
		
		user.getPostLiked().remove(post);
		userServ.updateUser(user);
		
		model.addAttribute("user", userServ.findById(userId));

		
		return "redirect:/petgram";
	}
	
	//==================== DISPLAY ROUTES ======================//
	
	@GetMapping("/petgram/new")
	public String newPost(@ModelAttribute("newPhoto") Post post, HttpSession session) {
		// if the user is not in session redirect to the login page
		if(session.getAttribute("uuid") == null) {
			return "redirect:/logout";
		}
				
		return "newpost.jsp";
	}
	
	@GetMapping("/petgram/old")
	public String oldPosts(HttpSession session, Model model) {
		// if the user is not in session redirect to the login page
		if(session.getAttribute("uuid") == null) {
			return "redirect:/logout";
		}
		
		model.addAttribute("user", userServ.getOne((Long) session.getAttribute("uuid")));
		
		Long userId = (Long) session.getAttribute("uuid");
		
		User user = userServ.findById(userId);
		
		model.addAttribute("posts", user.getPosts());
		
		
		
		return "oldpost.jsp";
	}
	
	//==================== ACTION ROUTES ======================//
	//==================== CREATE ======================//
	
	@SuppressWarnings("rawtypes")
	@PostMapping(value="/photo/new")
	public String createPhoto(@Valid  @ModelAttribute("newPhoto") FileUpload post, BindingResult result, HttpSession session) throws IOException {
		// if the user is not in session redirect to the login page
		if(session.getAttribute("uuid") == null) {
				return "redirect:/logout";
			}
		
		if(result.hasErrors()) {
			return "newpost.jsp";
		} 
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecret));
		
		Map uploadResult = null;
		
		
			
		if(post.getFile() != null && !post.getFile().isEmpty()) {
			
			uploadResult = cloudinary.uploader().upload(post.getFile().getBytes(),
					ObjectUtils.asMap("public_id", "test"));
		 
		}
		
		Post p = new Post();
		User u = userServ.getOne((Long) session.getAttribute("uuid"));
		p.setContent(post.getContent());
		p.setFile((String) uploadResult.get("url"));
		p.setCreator(u);
		
		postServ.save(p);
			
		return "redirect:/petgram";
	}

	//==================== EDIT ======================//
	
//	FIX THIS UPDATE
	
	@PutMapping("/petgram/{id}/edit")
	public String update(@PathVariable("id") Long id, HttpSession session, @RequestParam("content") String content) {
		// if the user is not in session redirect to the login page
		if(session.getAttribute("uuid") == null) {
			return "redirect:/logout";
		}
		
// Figure out Flash method
//		if(content == " ") {
//			return "redirect:/petgram/old";
//		}
		
		User user = userServ.getOne((Long) session.getAttribute("uuid"));
		Post post = postServ.getOne(id);
		
		post.setContent(content);
		postServ.save(post);
		
	
		return "redirect:/petgram/old";
	}
			
	//==================== DELETE ROUTE ======================//
	
	@DeleteMapping("/petgram/{id}")
	public String destroy(@PathVariable("id") Long id) {
		postServ.delete(id);
		return "redirect:/petgram";
	}
	
}
