package com.chldbwls.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManager {
	// 파일을 다루는 곳
	
	// 파일 저장 경로
	// 멤버변수에도 static 붙이면 객체 생성 없이도 사용할 수 있음
	// final == (최초 세팅한) 변수값을 바꾸지 못하도록 하는
	// => 상수!!!!!!!!!!!!(변수x)
	public static final String FILE_UPLOAD_PATH = "D:\\cujSpringProject\\upload\\memo";
	// 이 경로를 url path와 연결시켜야됨
	
	// 파일 저장하는 기능	
	public static String saveFile(int userId, MultipartFile file) {
		
		// 파일 이름 유지
		// 같은 이름의 파일이 전달 될 경우를 대비하여 디렉토리를 만들어서 파일 저장
		// 사용자별로 구분하여 저장 => 디렉토리 이름에 사용자 정보 포함
		// 시간을 디렉토리 이름에 포함
		// UNIX TIME : 1970년 1월 1일부터 흐른 시간을 milli second(1 / 1000초)로 표현한 값
		// ex ) 2_89723598273
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis();
		
		// 디렉토리 만들기
		// 기본 경로에 이어붙인 것
		// 전체 경로(만 잘 잡아주고 사용법대로 사용하면 됨)
		String directoryPath = FILE_UPLOAD_PATH + directoryName;
		
		// java.io.어쩌구 import
		File directory = new File(directoryPath);
		
		// 파일 생성 과정에서 실패가 일어나는 경우가 많기 때문에
		if(!directory.mkdir()) {
			// 디렉토리 생성 실패
			return null;
		}
		
		// 파일 저장
		String filePath = directoryPath + "/" + file.getOriginalFilename();
		
		// 원초적인 data 다룰 때는 byte를 사용하는구나
		try {
			byte[] bytes = file.getBytes();
			// bytes가 실제 파일 경로
			// java.nyo?import
			Path path = Paths.get(filePath);
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
			// 파일 저장 실패
			return null;
		}
		
		// 실제 cliant에 연결해줘야돼
		// D:\\cujSpringProject\\upload\\memo/2_89723598273/test.png
		
		// 클라이언트가 접근할 수 있게 아래 문자열을 만들어줘야돼
		// /images/2_89723598273/test.png
		
		return "/images" + directoryName + "/" + file.getOriginalFilename();
	}

}
