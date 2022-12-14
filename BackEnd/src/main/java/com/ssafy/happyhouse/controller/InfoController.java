package com.ssafy.happyhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.InfoDto;
import com.ssafy.happyhouse.model.dto.InfoResultDto;
import com.ssafy.happyhouse.model.service.InfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/info")
public class InfoController {

	@Autowired
	private InfoService infoService;

	@ApiOperation(value = "해당 자치구의 정보 반환")
	@GetMapping("/{guName}")
	public ResponseEntity<InfoResultDto> infoList(@PathVariable String guName, HttpSession session) {

		InfoResultDto resultDto = new InfoResultDto();

		InfoDto result = infoService.guInfo(guName);
		InfoDto avg = infoService.getAvg();
		if (result != null) {
			resultDto.setInfo(result);
			resultDto.setAvg(avg);
			session.setAttribute("infoResultDto", resultDto);
			return new ResponseEntity<InfoResultDto>(resultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<InfoResultDto>(resultDto, HttpStatus.NOT_FOUND);
		}
	}

}
