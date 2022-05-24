package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.AddressDto;
import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.mapper.AddressMapper;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressMapper addressMapper;
	
	
	@Override
	public List<Map<String, String>> getGugunList(String sidoCode) {
		return addressMapper.getGugunList(sidoCode);
	}

	@Override
	public List<AddressDto> getDongList(String gugunCode) {
		return addressMapper.getDongList(gugunCode);
	}

	@Override
	public List<AddressDto> getInterest(String userid) {
		return addressMapper.getInterest(userid);
	}

	@Override
	public List<AddressDto> getInterestNew(UserDto user) {
		return addressMapper.getInterestNew(user);
	}
	
	@Override
	public void addInterest(String userid, String dongCode, String dongName) {
		addressMapper.addInterest(userid,dongCode,dongName);
	}
	@Override
	public void deleteInterest(String userid, String dongCode) {
		addressMapper.deleteInterest(userid,dongCode);
	}

	@Override
	public List<AddressDto> getGeoCode(String dongCode) {
		return addressMapper.getGeoCode(dongCode);
	}

	@Override
	public List<AddressDto> getSidoList() {
		return addressMapper.getSidoList();
	}
}
