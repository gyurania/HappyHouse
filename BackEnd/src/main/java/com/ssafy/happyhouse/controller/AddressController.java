package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.AddressDto;
import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.service.AddressService;


@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping("/sido")
	public ResponseEntity<?> sido() throws Exception {
		return new ResponseEntity<List<AddressDto>>(addressService.getSidoList(),HttpStatus.OK);
	}
	
	@GetMapping("/sido/{sidoCode}")
	public ResponseEntity<?> listGugun(@PathVariable String sidoCode){
		 List<Map<String, String>> sido = addressService.getGugunList(sidoCode);
		if(sido != null) {
			return new ResponseEntity<List<Map<String, String>>>(sido,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/gugun/{gugunCode}")
	public ResponseEntity<?> listDong(@PathVariable String gugunCode){
		 List<AddressDto> gugun = addressService.getDongList(gugunCode);
		if(gugun != null) {
			return new ResponseEntity<List<AddressDto>>(gugun,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/dong/{dongCode}")
	public ResponseEntity<?> geoCode(@PathVariable String dongCode) {
		List<AddressDto> geocode = addressService.getGeoCode(dongCode);
		if(geocode != null) {
			return new ResponseEntity<List<AddressDto>>(geocode,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}

	
	@GetMapping("/interest/list/{userid}")
	public ResponseEntity<?> listInterest(@PathVariable String userid){
		List<AddressDto> list= addressService.getInterest(userid);
		if(list != null) {
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/interest/{userid}/{dongCode}")
	public ResponseEntity<?> deleteInterest(@PathVariable String userid,@PathVariable String dongCode){
		if(userid != null&&dongCode!=null) {
			addressService.deleteInterest(userid, dongCode);
			return new ResponseEntity<String>("삭제성공",HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	@PostMapping("/interest")
	public ResponseEntity<?> insertInterest(@RequestBody HashMap<String,String> map){
		String userid = (String) map.get("userid");
		String dongCode = (String) map.get("dongCode");
		if(userid != null) {
			addressService.addInterest(userid, dongCode);
			return new ResponseEntity<String>("등록성공~",HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	
}
