package com.chldbwls.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 
	 

}
