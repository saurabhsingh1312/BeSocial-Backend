package com.web.socialweb.dto;


import java.util.Date;
import java.util.List;

import com.web.socialweb.entity.Post;



public class PostDTO {
	private Long id;
	private String body;
	private Date publishTime;
	private Long likes;
	private Long dislikes;
	private Long currentLevel;
	private List<PostDTO> comments;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id=id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String text) {
		this.body = text;
	}
	
	public Long getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(Long currentLevel) {
		this.currentLevel = currentLevel;
	}
	public Date getTime() {
		return publishTime;
	}
	public void setTime(Date time) {
		this.publishTime = time;
	}
	public long getLikes() {
		return likes;
	}
	public void setLikes(long likes) {
		this.likes = likes;
	}
	public long getDislikes() {
		return dislikes;
	}
	public void setDislikes(long dislikes) {
		this.dislikes = dislikes;
	}
	public List<PostDTO> getComments() {
		return comments;
	}
	public void setComments(List<PostDTO> comments) {
		this.comments = comments;
	}
	public static Post cvtToEntity(PostDTO postDTO) {
		Post post=new Post();
		post.setCurrentLevel(postDTO.getCurrentLevel());
		post.setDislikes(postDTO.getDislikes());
		post.setLikes(postDTO.getLikes());
		post.setBody(postDTO.getBody());
		post.setTime(postDTO.getTime());
		return post;
	}
	
	
}
