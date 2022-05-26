package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.dto.AddressDto;
import com.ssafy.happyhouse.model.dto.UserDto;

public interface AddressMapper {
	public List<AddressDto> getSidoList();
	public List<Map<String, String>> getGugunList(String sidoCode);

	public List<AddressDto> getDongList(String gugunCode);
	public List<AddressDto> getGeoCode(String dongCode);
	
	public List<AddressDto> getInterest(String userid);

	public void addInterest(String userid, String dongCode);

	public void deleteInterest(String userid, String dongCode);
}
