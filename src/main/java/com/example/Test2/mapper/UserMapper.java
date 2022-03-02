package com.example.Test2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.Test2.vo.User;

@Mapper
public interface UserMapper {
	List<User> selectUserData();
}
