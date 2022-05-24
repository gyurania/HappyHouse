package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private NoticeService noticeService;


    @ApiOperation(value = "모든 공지사항 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<NoticeDto>> selectAllNotice() throws Exception {
		logger.debug("selectNotice - 호출");
		return new ResponseEntity<List<NoticeDto>>(noticeService.selectNotice(), HttpStatus.OK);
	}

    @ApiOperation(value = "글 번호에 해당하는 공지사항의 정보를 반환한다.", response = NoticeDto.class)    
	@GetMapping("{noticeNo}")
	public ResponseEntity<NoticeDto> detailBoard(@PathVariable int noticeNo) {
		logger.debug("detailNotice - 호출");
		return new ResponseEntity<NoticeDto>(noticeService.detailNotice(noticeNo), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 공지사항 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeNotice(@RequestBody NoticeDto notice) {
		logger.debug("insertNotice - 호출");
		if (noticeService.insertNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글 번호에 해당하는 공지사항의 정보를 수정한다. 그리고 DB 수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{noticeNo}")
	public ResponseEntity<String> modifyNotice(@RequestBody NoticeDto notice) {
		logger.debug("modifyNotice - 호출");
		logger.debug("" + notice);
		
		if (noticeService.modifyNotice(notice)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글 번호에 해당하는 공지사항 정보를 삭제한다. 그리고 DB 삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{noticeNo}")
	public ResponseEntity<String> deleteNotice(@PathVariable int noticeNo) {
		logger.debug("deleteNotice - 호출");
		if (noticeService.deleteNotice(noticeNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
