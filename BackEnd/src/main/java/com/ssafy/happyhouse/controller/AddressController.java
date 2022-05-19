package com.ssafy.happyhouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.happyhouse.model.dto.AddressDto;
import com.ssafy.happyhouse.model.dto.UserDto;
import com.ssafy.happyhouse.model.service.AddressService;

@Controller
public class AddressController {
//commit
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
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
		 List<String> gugun = addressService.getDongList(gugunCode);
		if(gugun != null) {
			return new ResponseEntity<List<String>>(gugun,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	@GetMapping("/dong")
	public ModelAndView houseSearch() {
		ModelAndView mav = new ModelAndView("houseSearch");
		return mav;
	}
	@GetMapping("/interest")
	public ModelAndView interest() {
		ModelAndView mav = new ModelAndView("interest");
		return mav;
	}
	
	@GetMapping("/interest/list")
	public ResponseEntity<?> listInterest(HttpSession session){
		UserDto user = (UserDto) session.getAttribute("userinfo");
		List<AddressDto> list= addressService.getInterest(user);
		if(list != null) {
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("/interest/listnewest")
	public ResponseEntity<?> listInterestNew(HttpSession session){
		UserDto user = (UserDto) session.getAttribute("userinfo");
		List<AddressDto> list= addressService.getInterestNew(user);
		if(list != null) {
			return new ResponseEntity<List<AddressDto>>(list,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
	}
	
	@DeleteMapping("/interest/{dongCode}")
	public ResponseEntity<?> deleteInterest(HttpSession session,@PathVariable String dongCode){
		UserDto user = (UserDto) session.getAttribute("userinfo");
		if(user != null) {
			addressService.deleteInterest(user.getId(), dongCode);
			return new ResponseEntity<String>("삭제성공~",HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	@PostMapping("/interest/{dongCode}/{dongName}")
	public ResponseEntity<?> insertInterest(HttpSession session,@PathVariable String dongCode,@PathVariable String dongName){
		UserDto user = (UserDto) session.getAttribute("userinfo");
		if(user != null) {
			addressService.addInterest(user.getId(), dongCode, dongName);
			return new ResponseEntity<String>("등록성공~",HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	
}
