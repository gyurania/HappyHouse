package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public boolean signUp(UserDto user) throws Exception {
		return userMapper.signUp(user);
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return userMapper.login(map);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return userMapper.userInfo(userid);
	}
	
	
	@Override
	public boolean update(UserDto user) throws Exception {
		return userMapper.update(user);
	}

	@Override
	public boolean deleteUser(String userId) throws Exception {
		return userMapper.deleteUser(userId);
	}

	@Override
	public UserDto findPW(String userId, String email) {
		return userMapper.findPW(userId, email);
	}

	@Override
	public int idCheck(String id) throws SQLException {
		return userMapper.idCheck(id);
	}

	
}
