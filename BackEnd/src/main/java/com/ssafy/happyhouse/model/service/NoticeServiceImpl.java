package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> selectNotice() {
		// TODO Auto-generated method stub
		return noticeMapper.selectNotice();
	}

	@Override
	public NoticeDto detailNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return noticeMapper.selectNoticeByNo(noticeNo);
	}

	@Override
	public boolean insertNotice(NoticeDto notice) {
		// TODO Auto-generated method stub
		return noticeMapper.insertNotice(notice) == 1;
	}

	@Override
	public boolean modifyNotice(NoticeDto notice) {
		// TODO Auto-generated method stub
		return noticeMapper.modifyNotice(notice) == 1;
	}

	@Override
	public boolean deleteNotice(int noticeNo) {
		// TODO Auto-generated method stub
		return noticeMapper.deleteNotice(noticeNo) == 1;
	}

}
