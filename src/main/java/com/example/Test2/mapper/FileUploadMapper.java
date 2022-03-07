package com.example.Test2.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.Test2.vo.User;

@Mapper
public interface FileUploadMapper {
	List<User> fileUpload(List<User> userList);
	List<User> selectList(List<User> userList);
}
