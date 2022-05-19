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
	public boolean update(UserDto user) throws Exception {
		return userMapper.update(user);
	}

	@Override
	public boolean deleteUser(UserDto user) throws Exception {
		return userMapper.deleteUser(user);
	}

	@Override
	public UserDto findPW(String id, String username, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int idCheck(String id) throws SQLException {
		return userMapper.idCheck(id);
	}

	@Override
	public boolean changePW(String password, String salt, String id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
