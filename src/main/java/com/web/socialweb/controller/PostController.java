package com.web.socialweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.socialweb.dto.PostDTO;
import com.web.socialweb.service.PostServiceImpl;



@RestController
@RequestMapping(value="/ogreddit")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
	@Autowired
	private PostServiceImpl service;
	
	@GetMapping(value="/posts/{filter}")
	public ResponseEntity<List<PostDTO>> getAllPosts(@PathVariable("filter") String filter){
		return service.getAllPosts(filter);
	}
	
	@GetMapping(value="/post/{id}")
	public ResponseEntity<PostDTO> getPostandComments(@PathVariable("id") Long id){
		return service.getPostAndComments(id);
	}
	
	@PostMapping(value="createpost")
	public ResponseEntity<Long> uploadPost(@RequestBody String body){
		return service.uploadPost(body,0L);
	}
	@PutMapping(value="like/{id}")
	public ResponseEntity<String> likepost(@PathVariable("id") Long id,@RequestBody Long value){
		return service.likepost(id,value);
	}
	@PutMapping(value="dislike/{id}")
	public ResponseEntity<String> dislikepost(@PathVariable("id") Long id,@RequestBody Long value){
		return service.dislikepost(id,value);
	}
	@PutMapping(value="addcomment/{id}")
	public ResponseEntity<String> commentOnPost(@PathVariable("id") Long id, @RequestBody String body){
		return service.commentOnPost(id,body);
	}
	
	@PostMapping(value="searchtweet")
	public ResponseEntity<Long> searchTweetBody(@RequestBody String body){
		return service.searchTweetBody(body);
	}
	@GetMapping(value="searchtrends")
	public ResponseEntity<String[]> searchTrends(){
		return service.trends();
	}
	
	
	
	
}
