package com.ssafy.happyhouse.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.InfoDto;

@Mapper
public interface InfoMapper {
	public InfoDto guInfo(String guName);
	public InfoDto getAvg();

}
