package com.ssafy.happyhouse.model.dto;

public class NoticeDto {
	private int idx;
	private String userId;
	private String subject;
	private String content;
	
	public NoticeDto() {
		super();
	}
	public NoticeDto(int idx, String userId, String subject, String content) {
		super();
		this.idx = idx;
		this.userId = userId;
		this.subject = subject;
		this.content = content;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "NoticeDto [idx=" + idx + ", userId=" + userId + ", subject=" + subject + ", content=" + content + "]";
	}
	

}
