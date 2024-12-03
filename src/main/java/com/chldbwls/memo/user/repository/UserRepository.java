package com.chldbwls.memo.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chldbwls.memo.user.domain.User;

@Mapper
public interface UserRepository {

	// insert는 무조건 실행된 행의 갯수 return
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);
	
	public User selectUser(
			@Param("loginId") String loginId
			, @Param("password") String password);
	
	
}
