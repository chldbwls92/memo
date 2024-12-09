package com.chldbwls.memo.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chldbwls.memo.post.domain.Post;
import com.chldbwls.memo.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {
	
	private PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/list-view")	//springFrameWork import
	public String memoList(
			Model model
			,HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Post> memoList = postService.getPostList();
		
		model.addAttribute("memoList", memoList);
		
		return "post/list";
	}
	
	
	@GetMapping("/create-view")
	public String inputMemo() {
		return "post/input";
	}

	@GetMapping("/detail-view")
	public String memoDetail(
			@RequestParam("id") int id
			, Model model) {
		
		Post memo = postService.getPost(id);
		model.addAttribute("memo", memo);
		return "post/detail";
	}
	
	
}
