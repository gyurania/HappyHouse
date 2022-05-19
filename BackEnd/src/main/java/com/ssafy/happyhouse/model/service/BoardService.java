package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.BoardDto;

public interface BoardService {
	public List<BoardDto> selectBoard();
	
	public BoardDto detailBoard(int boardNo);

	public boolean insertBoard(BoardDto board);

	public boolean modifyBoard(BoardDto board);

	public boolean deleteBoard(int boardNo);

}
