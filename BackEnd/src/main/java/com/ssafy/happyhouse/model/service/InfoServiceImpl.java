package com.ssafy.happyhouse.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.InfoDto;
import com.ssafy.happyhouse.model.mapper.InfoMapper;

@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	private InfoMapper infoMapper;
	
	@Override
	public InfoDto guInfo(String guName) {
		return infoMapper.guInfo(guName);
	}

	@Override
	public InfoDto getAvg() {
		return infoMapper.getAvg();
	}

}
