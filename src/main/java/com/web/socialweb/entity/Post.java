package com.web.socialweb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web.socialweb.dto.PostDTO;



@Entity
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String body;
	@Column(name="publishtime")
	private Date publishTime;
	private Long likes;
	private Long dislikes;
	@Column(name="currentlevel")
	private Long currentLevel; //posts==0
	@OneToMany(cascade = CascadeType.ALL)//Referees to more than 1 hence list or collection
	@JoinColumn(name = "currentlevel")//refers to currentlevel...
	private List<Post> comments;
	
	public Long getCurrentLevel() {
		return currentLevel;
	}
	public void setCurrentLevel(Long currentLevel) {
		this.currentLevel = currentLevel;
	}
	public Long getId() {
		return id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String text) {
		this.body = text;
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
	public List<Post> getComments() {
		return comments;
	}
	public void setComments(List<Post> comments) {
		this.comments = comments;
	}
	
	public static PostDTO cvtToDTO(Post post) {
		PostDTO postDTO=new PostDTO();
		postDTO.setCurrentLevel(post.getCurrentLevel());
		postDTO.setDislikes(post.getDislikes());
		postDTO.setLikes(post.getLikes());
		postDTO.setBody(post.getBody());
		postDTO.setTime(post.getTime());
		postDTO.setId(post.getId());
		return postDTO;
	}
}
