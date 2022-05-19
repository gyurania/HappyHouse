package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> selectBoard();
	
	public BoardDto selectBoardByNo(int boardNo);

	public int insertBoard(BoardDto board);

	public int modifyBoard(BoardDto board);

	public int deleteBoard(int boardNo);

}
