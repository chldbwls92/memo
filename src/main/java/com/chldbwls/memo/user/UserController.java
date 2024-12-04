package com.chldbwls.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@Controller
public class UserController {
	
	
	// view는 responsebody가 없고 api는 responseBody가 있음
	
	
	@GetMapping("/join-view")
	public String inputJoin() {
		return "user/join";
	}
	 
	 @GetMapping("/login-view")
	 public String inputlogin() {
		 return "user/login";
	 }
	 
	 
	 @GetMapping("/logout")
	 public String logout(HttpServletRequest request) {
		 HttpSession session = request.getSession();
		 
		 // id랑 name 지우기 => 로그인 안 한 상태
		 session.removeAttribute("userId");
		 session.removeAttribute("userName");
		 
		 //리다이렉트
		 return "redirect:/user/login-view";
	 }

}
