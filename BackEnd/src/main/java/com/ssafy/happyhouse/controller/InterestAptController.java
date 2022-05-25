package com.ssafy.happyhouse.controller;

import java.util.List;

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

import com.ssafy.happyhouse.model.dto.HouseDto;
import com.ssafy.happyhouse.model.service.InterestAptService;


@RestController
@RequestMapping("/interest/apt")
public class InterestAptController {

	@Autowired
	public InterestAptService interestAptService;
	
	@GetMapping("{userid}")
	public ResponseEntity<?> getApt(@PathVariable String userid){
		System.out.println(userid);
		return new ResponseEntity<List<HouseDto>>(interestAptService.getInterestApt(userid),HttpStatus.OK);
	}
	
//	@GetMapping("{userid}/{code}")
//	public ResponseEntity<?> getAptList(@PathVariable String userid, @PathVariable String code){
//		return new ResponseEntity<List<HouseDto>>(interestAptService.getInterestAptList(userid, code),HttpStatus.OK);
//	}
	
//	@PostMapping("")
//	public ResponseEntity<?> addApt(@RequestBody HouseDto houseDto,@PathVariable String userid){
//		interestAptService.addInterestApt(houseDto,userid);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
//	@PostMapping("/{userid}/{code}")
//	public ResponseEntity<?> addAptList(@RequestBody HouseDto houseDto,@PathVariable String userid){
//		interestAptService.addInterestAptList(houseDto,userid);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/{userid}/{code}")
//	public ResponseEntity<?> deleteApt(@PathVariable String userid, @PathVariable String code){
//		interestAptService.deleteInterestApt(userid, code);
//		return new ResponseEntity<Void>(HttpStatus.OK);
//	}
//	
	
}
