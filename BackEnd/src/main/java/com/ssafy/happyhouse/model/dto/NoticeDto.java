package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "공지사항", description = "관리자만 작성 가능한 공지사항")
public class NoticeDto {
	@ApiModelProperty(value = "공지사항 번호")
	private int notice_no;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String create_time;
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;
	
	public NoticeDto() {
		super();
	}

	public NoticeDto(int notice_no, String title, String content, String create_time, String userid) {
		super();
		this.notice_no = notice_no;
		this.title = title;
		this.content = content;
		this.create_time = create_time;
		this.userid = userid;
	}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "NoticeDto [notice_no=" + notice_no + ", title=" + title + ", content=" + content + ", create_time="
				+ create_time + ", userid=" + userid + "]";
	}
	

}
