package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.AddressDto;
import com.ssafy.happyhouse.model.dto.UserDto;


public interface AddressService {
	public List<AddressDto> getSidoList();
	public List<Map<String, String>> getGugunList(String sidoCode);

	public List<AddressDto> getDongList(String gugunCode);
	public List<AddressDto> getGeoCode(String dongCode);
	
	public List<AddressDto> getInterest(UserDto user);
	public List<AddressDto> getInterestNew(UserDto user);
	
	public void addInterest(String userid, String dongCode, String dongName);
	
	public void deleteInterest(String userid, String dongCode);
}
