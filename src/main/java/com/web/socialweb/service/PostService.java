package com.web.socialweb.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.web.socialweb.dto.PostDTO;



public interface PostService {

	public ResponseEntity<List<PostDTO>> getAllPosts(String filter);
	public ResponseEntity<PostDTO> getPostAndComments(Long id);
	public ResponseEntity<Long> uploadPost(String body,Long level);
	public ResponseEntity<String> likepost(Long id, Long value);
	public ResponseEntity<String> dislikepost(Long id, Long value);
	ResponseEntity<String> commentOnPost(Long id, String body);
	ResponseEntity<Long> searchTweetBody(String body);
	ResponseEntity<String[]> trends();
}
