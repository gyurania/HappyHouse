package com.ssafy.happyhouse.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	void registerNotice(NoticeDto noticeDto) throws Exception; // 공지사항 등록
}
