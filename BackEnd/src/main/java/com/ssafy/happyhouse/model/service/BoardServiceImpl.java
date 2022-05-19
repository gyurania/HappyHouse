package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.BoardDto;
import com.ssafy.happyhouse.model.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> selectBoard() {
		return boardMapper.selectBoard();
	}

	@Override
	public BoardDto detailBoard(int boardNo) {
		return boardMapper.selectBoardByNo(boardNo);
	}

	@Override
	public boolean insertBoard(BoardDto board) {
		return boardMapper.insertBoard(board) == 1;
	}

	@Override
	public boolean modifyBoard(BoardDto board) {
		return boardMapper.modifyBoard(board) == 1;
	}

	@Override
	public boolean deleteBoard(int boardNo) {
		return boardMapper.deleteBoard(boardNo) == 1;
	}

}
