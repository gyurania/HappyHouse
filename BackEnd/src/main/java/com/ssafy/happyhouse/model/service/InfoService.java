package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.dto.InfoDto;

public interface InfoService {

	public InfoDto guInfo(String guName);
	public InfoDto getAvg();

}
