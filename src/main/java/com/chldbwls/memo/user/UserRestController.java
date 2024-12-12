package com.chldbwls.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chldbwls.memo.user.domain.User;
import com.chldbwls.memo.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserRestController {
	// api controller	
	
	private UserService userService;
	
	// 생성자
//	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("/join")
	public Map<String, String> join(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, name, email)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	@PostMapping("/login")
	public Map<String, String> login(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		
		// 실제 user table에 있는지 확인하는 방법
		User user = userService.getUser(loginId, password);
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		// 조회된 결과가 있다 없다
		// user 가 null이냐 아니냐
		if(user != null) {
			// 성공했을 경우
			// 서블릿 기반
			HttpSession session = request.getSession();
			
			// user id, user name
			// 어디서든 session값 가져와서 쓸 수 있음
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			// 사용자정보의 이름, id 정보 저장(업캐스팅 되어서)
			
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}

}
