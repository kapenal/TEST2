package com.example.Test2.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.Test2.vo.User;

@Mapper
public interface FileUploadMapper {
	int fileUpload(User user);
}
