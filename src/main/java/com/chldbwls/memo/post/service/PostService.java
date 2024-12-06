package com.chldbwls.memo.post.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chldbwls.memo.common.FileManager;
import com.chldbwls.memo.post.domain.Post;
import com.chldbwls.memo.post.repository.PostRepository;

@Service
public class PostService {
	
	//jpa 사용할 것임
	private PostRepository postRepository;
	
	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	// 전달받을 거 그대로 insert
	public boolean addPost(int userId, String title, String contents, MultipartFile file) {
		
		// 파일첨부 추가
		String imagePath =  FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
		.userId(userId)
		.title(title)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {
			postRepository.save(post);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	
	// 특정 사용자의 메모만 볼 수 있도록 userId 가져오기
	public List<Post> getPostList(int userId) {
		return postRepository.findGyUserIdOrderBuIdDesc(userId);
	}
	
	// 하나의 행 수행
	public Post getPost(int id) {
		
		Optional<Post> optionalPost = postRepository.findById(id);
		
		return optionalPost.orElse(null);
		
	}
	
}
