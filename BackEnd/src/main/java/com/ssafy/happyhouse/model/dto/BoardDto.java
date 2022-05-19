package com.ssafy.happyhouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Board(QnA 게시판)", description = "글번호, 제목, 내용, 작성시간, 작정자를 가진 Domain Class")
public class BoardDto {
	@ApiModelProperty(value = "글 번호")
	private int board_no;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	@ApiModelProperty(value = "작성일")
	private String create_time;
	@ApiModelProperty(value = "작성자 아이디")
	private String userid;

	public BoardDto() {
		super();
	}

	public BoardDto(int board_no, String title, String content, String create_time, String userid) {
		super();
		this.board_no = board_no;
		this.title = title;
		this.content = content;
		this.create_time = create_time;
		this.userid = userid;
	}

	public int getboard_no() {
		return board_no;
	}

	public void setboard_no(int board_no) {
		this.board_no = board_no;
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

	public String getcreate_time() {
		return create_time;
	}

	public void setcreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getuserid() {
		return userid;
	}

	public void setuserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "BoardDto [board_no=" + board_no + ", title=" + title + ", content=" + content + ", create_time="
				+ create_time + ", userid=" + userid + "]";
	}

}
