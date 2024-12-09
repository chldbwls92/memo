package com.chldbwls.memo.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
			, @RequestParam("imageFile") MultipartFile file
			, HttpSession session) {
		// + 세션관리까지 하게 됨
		// 다운캐스팅 해야돼서 integer
		int userId = (Integer)session.getAttribute("userId");
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, title, contents, file)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		postService.addPost(userId, title, contents, file);
		return resultMap;
	}
	

	
	
}
