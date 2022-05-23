package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.UserDto;

@Mapper
public interface UserMapper {

	boolean signUp(UserDto user) throws Exception; // 회원가입

	UserDto login(Map<String, String> map) throws Exception; // 로그인

	UserDto userInfo(String userid) throws SQLException;

//	UserDto detailUser(String userid) throws SQLException;
	
	boolean update(UserDto user) throws Exception;

	boolean deleteUser(String userId) throws Exception;

	UserDto findPW(String id, String username, String email);

	int idCheck(String id) throws SQLException;

	boolean changePW(String password, String salt, String id);

}
