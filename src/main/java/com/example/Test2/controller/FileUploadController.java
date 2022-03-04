package com.example.Test2.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Test2.service.FileUploadService;
import com.example.Test2.vo.User;

@RestController
public class FileUploadController {
	@Autowired
	FileUploadService fileUploadService;
	
	@PostMapping("/LOGIN/FileUpload")
	public Map<String, Object> postFileUpload(MultipartFile uploadFile) {
		System.out.println(uploadFile + "매개변수 확인");
		// Grid에 넣어줄 입력성공한 데이터리스트
		List<User> userList = new ArrayList<>();
		// 입력하는 레코드의 수 기록하는 변수
		int fileUploadLine = 0;
		// 매개변수로 받아온 파일의 이름으로 File타입을 새로 생성
		File file = new File(uploadFile.getOriginalFilename());
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file); 
		    fos.write(uploadFile.getBytes());
		    fos.close();
		} catch (IOException e1) {
			System.out.println("예외 발생!");
			e1.printStackTrace();
		}
		//생성된 file을 스캐너로 파일 읽기
        Scanner scan = null;
		try {
			scan = new Scanner(file);
			// scanner를 이용하여 파일을 1줄씩 반복
			while(scan.hasNextLine()){
				fileUploadLine++;
				// 1줄씩 String형 userLine에 저장하여 uploadService호출
				 String userLine = scan.nextLine();
				 // System.out.println(userLine);
				 User resultUser = fileUploadService.upload(userLine);
				 if(resultUser.getId() != null) { // insert성공 시 resultUser의 id,pwd .... 등등 값이 null이 아니면 userList에 add
					 userList.add(resultUser);
				 }
			}
		} catch (FileNotFoundException e) {
			System.out.println("예외 발생!");
			e.printStackTrace();
		}
        file.delete();
        scan.close();
        
        System.out.println(userList.toString() + "유저 리스트");
        Map<String, Object> map = new HashMap();
        map.put("userList", userList);
        map.put("failCount",fileUploadLine-userList.size());
        map.put("sucessCount", userList.size());

        return map;
	}
}
