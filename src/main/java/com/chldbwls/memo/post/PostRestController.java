package com.chldbwls.memo.post;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chldbwls.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@RestController
public class PostRestController {

	private PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createMemo(
			@RequestParam("title") String title
			, @RequestParam("contents") String contents
			, HttpSession session) {
		// + 세션관리까지 하게 됨
		int userId = session.getAttribute("userId");
		
		postService.addPost(0, title, contents);
	}
	
}
