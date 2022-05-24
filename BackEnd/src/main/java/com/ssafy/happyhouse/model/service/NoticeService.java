package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.NoticeDto;

public interface NoticeService {
	
	public List<NoticeDto> selectNotice();

	public NoticeDto detailNotice(int noticeNo);

	public boolean insertNotice(NoticeDto notice);

	public boolean modifyNotice(NoticeDto notice);

	public boolean deleteNotice(int noticeNo);

}
