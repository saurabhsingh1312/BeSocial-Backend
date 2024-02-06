package com.web.socialweb.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.web.socialweb.entity.Post;



public interface PostRepositary extends CrudRepository<Post,Long>{

	@Query(value = "SELECT * FROM post p WHERE p.currentlevel=0 ORDER BY p.likes DESC", 
			  nativeQuery = true)
	List<Post> getAllTopPosts();

	@Query(value="SELECT * FROM post p WHERE p.currentlevel=0 ORDER BY p.publishtime DESC",nativeQuery = true)
	List<Post> getAllNewPosts();

	
	@Modifying
	@Transactional
	@Query("UPDATE Post SET likes=?2 WHERE id=?1")
	void like(Long id, Long value);

	@Modifying
	@Transactional
	@Query("UPDATE Post SET dislikes=?2 WHERE id=?1")
	void dislike(Long id, Long value);

	@Query("SELECT currentLevel FROM Post WHERE id=?1")
	Object getlevel(Long id);

	@Modifying
	@Transactional
	@Query("UPDATE Post SET comments=?2 WHERE id=?1")
	void setCommentPath(Long id, List<Post> arrayPosts);

	@Query(value="SELECT id FROM Post  WHERE body=?1 ORDER BY publishTime")
	List<Long> searchTweetBody(String body);
}
