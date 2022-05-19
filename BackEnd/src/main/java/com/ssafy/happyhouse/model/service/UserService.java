package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.UserDto;

public interface UserService {

	boolean signUp(UserDto user) throws Exception; // 회원가입
	UserDto login(Map<String, String> map) throws Exception; // 로그인
	boolean update(UserDto user) throws Exception;
	boolean deleteUser(UserDto user) throws Exception;

	UserDto findPW(String id, String username, String email);

	int idCheck(String id) throws SQLException;
	boolean changePW(String password, String salt, String id);

}
