package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	public List<NoticeDto> selectNotice();
	
	public NoticeDto selectNoticeByNo(int noticeNo);
	
	public int insertNotice(NoticeDto notice);
	
	public int modifyNotice(NoticeDto notice);
	
	public int deleteNotice(int noticeNo);
}
