package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.CommentDto;

public interface CommentMapper {
	List<CommentDto> list(String board_no);

	int create(CommentDto commentDto);

	int modify(CommentDto commentDto);

	int delete(int commentNo);
}
