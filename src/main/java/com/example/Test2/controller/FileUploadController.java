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
		// 다중 insert용 리턴 list
		List<User> userList = new ArrayList<>();
		// 입력하는 레코드의 수 기록하는 변수
		int fileUploadLine = 0;
		// 매개변수로 받아온 파일의 이름으로 File타입을 새로 생성
		File file = new File(uploadFile.getOriginalFilename());
		// 다중 insert용 매개변수 list
		List<String> list = new ArrayList<>();
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file); 
		    fos.write(uploadFile.getBytes());
		    fos.close();
		} catch (IOException e1) {
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
				 list.add(userLine);
			}
			// userList에 파일 업로드의 성공한 데이터를 입력
			userList = fileUploadService.upload(list);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(file.getAbsolutePath());
        file.deleteOnExit();// JVM 이 종료 될 때 자동으로 지정된 파일을 삭제
        scan.close();
        
        Map<String, Object> map = new HashMap<>();
        map.put("userList", userList);
        map.put("failCount",fileUploadLine-userList.size());
        map.put("sucessCount", userList.size());

        return map;
	}
}
