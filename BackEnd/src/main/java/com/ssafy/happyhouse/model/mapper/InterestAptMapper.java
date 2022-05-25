package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDto;

public interface InterestAptMapper {
	public List<HouseDto> getInterestApt(String userid);
	public void addInterestApt(HouseDto houseDto,String userid);
	public void deleteInterestApt(String userid, String code);

	public List<HouseDto> getInterestAptList(String userid, String code);
	public void addInterestAptList(HouseDto houseDto,String userid);
	
	
	
}
