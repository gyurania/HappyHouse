package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.HouseDto;
import com.ssafy.happyhouse.model.mapper.InterestAptMapper;

@Service
public class InterestAptServiceImpl implements InterestAptService {

	@Autowired
	private InterestAptMapper interestAptMapper;
	
	@Override
	public List<HouseDto> getInterestApt(String userid) {
		return interestAptMapper.getInterestApt(userid);
	}

	@Override
	public void addInterestApt(HouseDto houseDto,String userid) {
		interestAptMapper.addInterestApt(houseDto,userid);
	}

	@Override
	public void deleteInterestApt(String userid, String code) {
		interestAptMapper.deleteInterestApt(userid, code);
	}

	@Override
	public List<HouseDto> getInterestAptList(String userid, String code) {
		return interestAptMapper.getInterestAptList(userid, code);
	}

	@Override
	public void addInterestAptList(HouseDto houseDto,String userid) {
		interestAptMapper.addInterestAptList(houseDto, userid);		
	}

}
