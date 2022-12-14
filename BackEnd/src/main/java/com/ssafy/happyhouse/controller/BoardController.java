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

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.service.BoardService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;


    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<BoardDto>> selectAllBoard() throws Exception {
		logger.debug("selectBoard - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.selectBoard(), HttpStatus.OK);
	}

    @ApiOperation(value = "글 번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)    
	@GetMapping("{boardNo}")
	public ResponseEntity<BoardDto> detailBoard(@PathVariable int boardNo) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<BoardDto>(boardService.detailBoard(boardNo), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody BoardDto board) {
		logger.debug("insertBoard - 호출");
		if (boardService.insertBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글 번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{boardNo}")
	public ResponseEntity<String> modifyBoard(@RequestBody BoardDto board) {
		logger.debug("modifyBoard - 호출");
		logger.debug("" + board);
		if (boardService.modifyBoard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글 번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{boardNo}")
	public ResponseEntity<String> deleteBoard(@PathVariable int boardNo) {
		logger.debug("deleteBoard - 호출");
		if (boardService.deleteBoard(boardNo)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
