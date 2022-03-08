package com.example.Test2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Test2.mapper.FileUploadMapper;
import com.example.Test2.vo.User;

@Service
public class FileUploadService {
	@Autowired
	FileUploadMapper fileUploadMapper;
	
	public List<User> upload(List<String> userLine) {
		List<User> insertUser = new ArrayList<>();
		List<User> userList = new ArrayList<>();
		// 매겨변수로 받은 List<String>타입의 userLine을 split을 이용하여 List<User>타입의 userList에 입력
		for(String i : userLine) {
			String[] array = i.split("/");
			User user = new User();
			user.setId(array[0]);
			user.setPwd(array[1]);
			user.setName(array[2]);
			user.setLevel(array[3].charAt(0)); // array를 이용시 char형으로 저장이 안되서 chartAt(0)을 사용하여 배열의 첫글자만 따로 추출해서 저장
			user.setDesc(array[4]);
			user.setRegDate(array[5]);
			userList.add(user);
		}
		try {
			insertUser = fileUploadMapper.fileUpload(userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertUser;
	}
}