package com.chldbwls.memo.post.service;

import org.springframework.stereotype.Service;

import com.chldbwls.memo.post.domain.Post;
import com.chldbwls.memo.post.repository.PostRepository;

@Service
public class PostService {
	
	//jpa 사용할 것임
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public boolean addPost(int userId, String title, String contents) {
		
		Post post = Post.builder()
		.userId(userId)
		.title(title)
		.contents(contents)
		.build();
		
		try {
			postRepository.save(post);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
