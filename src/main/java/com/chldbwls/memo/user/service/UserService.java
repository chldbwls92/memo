package com.chldbwls.memo.user.service;

import org.springframework.stereotype.Service;

import com.chldbwls.memo.user.repository.UserRepository;

@Service
public class UserService {
	
	
	private UserRepository userRepository;
	
	// 생성자에 붙이기
	// autowired를 위한 생성자만 존재하는 경우 생략 가능
//	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	// 멤버변수에 직접 값을 부여하기보다 생성자에 부여를 하면 꼭 autowired가 아니더라도 직접 생성자를 통해 활용할 수 있기 때문에 더 좋음
	
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
//		String encodingPassword = MD5HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, password, name, email);
		
		// 잘 저장 됐다 안 됐다
		if(count == 1) {
			return true;
		} else {
			return false;
		}
		
	}
}
