package com.chldbwls.memo.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chldbwls.memo.post.domain.Post;
import com.chldbwls.memo.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	@GetMapping("/list-view")
	public String memoList() {
		return "post/list";
	}
	
	
	@GetMapping("/create-view")
	public String inputMemo() {
		return "post/input";
	}

	@GetMapping("/detail-view")
	public String memoDetail(@RequestParam("id") int id) {
		
		Post memo = postService.getPost(id);
		model.addAttribute("memo", memo);
		return "post/detail";
	}
	
	
}
