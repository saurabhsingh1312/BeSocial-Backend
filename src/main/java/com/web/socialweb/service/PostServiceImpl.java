package com.web.socialweb.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.web.socialweb.dto.PostDTO;
import com.web.socialweb.entity.Post;
import com.web.socialweb.repo.PostRepositary;





@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepositary postRepositary;
	
	@Override
	public ResponseEntity<List<PostDTO>> getAllPosts(String filter) {
		List<Post> posts = null;
		if(filter.equals("top")) {
			posts=postRepositary.getAllTopPosts();
			
		}
		else if(filter.equals("newest")) {
			posts=postRepositary.getAllNewPosts();
		}
		List<PostDTO> postDTOs=new ArrayList<>();
		for(Post post:posts) {
			PostDTO postDTO=Post.cvtToDTO(post);
			postDTOs.add(postDTO);
			
		}
		return new ResponseEntity<>(postDTOs,HttpStatus.OK);
		
	}
	@Override
	public ResponseEntity<PostDTO> getPostAndComments(Long id) {
		Optional<Post> post=postRepositary.findById(id);
		PostDTO postDTO=null;
		if(post.isPresent()) {
			postDTO=Post.cvtToDTO(post.get());
			List<Post> comments=post.get().getComments();
			List<PostDTO> commentDTOs=new ArrayList<>();
			for(Post comment:comments) {
				PostDTO commentDTO=Post.cvtToDTO(comment);
				commentDTOs.add(commentDTO);
			}
			postDTO.setComments(commentDTOs);
		}
		return new ResponseEntity<>(postDTO,HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Long> uploadPost(String body,Long level) {
		
		Post post=new Post();
		post.setBody(body);
		//post.setComments(null);
		post.setCurrentLevel(level);
		post.setDislikes(0L);
		post.setLikes(0L);
		post.setTime(new Date());
		
		Post returnedPost=postRepositary.save(post);
		return ResponseEntity.ok(returnedPost.getId());
	}
	@Override
	public ResponseEntity<String> likepost(Long id, Long value) {
		postRepositary.like(id,value);
		return ResponseEntity.ok("Liked...");
	}
	@Override
	public ResponseEntity<String> dislikepost(Long id, Long value) {
		postRepositary.dislike(id,value);
		return ResponseEntity.ok("Disliked...");
	}
	@Override
	public ResponseEntity<String> commentOnPost(Long id, String body) {
		Optional<Post> pstOpt=postRepositary.findById(id);
		Post post=pstOpt.get();
		Long commentId;
		if(post.getComments().size()==0) {
			Long level=postRepositary.count()+1;
			commentId=this.uploadPost(body,level).getBody();
		}
		else {
			Long level=post.getComments().get(0).getCurrentLevel();
			commentId=this.uploadPost(body,level).getBody();
		}
		Post comment=postRepositary.findById(commentId).get();
		List<Post> allComments=post.getComments();
		allComments.add(comment);
		post.setComments(allComments);
		postRepositary.deleteById(id);
		System.out.println(post.getComments().size());
		postRepositary.save(post);
		return ResponseEntity.ok("Added..");
	}
	@Override
	public ResponseEntity<Long> searchTweetBody(String body) {
		List<Long> ids=postRepositary.searchTweetBody(body);
		if(ids.size()==0) {
			return new ResponseEntity<>(null,HttpStatus.OK);
		}
		return new ResponseEntity<>(ids.get(0),HttpStatus.OK);
	}
	@Override
	public ResponseEntity<String[]> trends() {
		Iterable<Post> allPosts=postRepositary.findAll();
		HashMap<String,Integer> hashTagCounts=new HashMap<>();
		Iterator<Post> it=allPosts.iterator();
		while(it.hasNext()) {
			Post post=it.next();
			//if(post.getTime().getDate()!=new Date().getDate())continue;
			String s=post.getBody();
			String[] t=s.split(" ");
			for(int i=0;i<t.length;i++) {
				
				if(t[i].contains("#")) {
					//System.out.println(t[i]);
					hashTagCounts.merge(t[i], 1, Integer::sum);
					//hashTagCounts.put(t[i], hashTagCounts.get(t[i])+1);
				}
			}
		}
		String[] tags=sortByValue(hashTagCounts);
		
		return new ResponseEntity<>(tags,HttpStatus.OK);
	}
	
	public static String[] sortByValue(HashMap<String, Integer> hm)
    {
        List<Map.Entry<String, Integer> > list =
               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2)
            {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        String[] tags=new String[10];
        int i=0;
        for (Map.Entry<String, Integer> aa : list) {
        	tags[i]=aa.getKey();
        	i+=1;
        }
        return tags;
    }

}
