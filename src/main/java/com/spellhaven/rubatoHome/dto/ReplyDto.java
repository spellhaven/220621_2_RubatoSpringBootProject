package com.spellhaven.rubatoHome.dto;

public class ReplyDto {
	
	private int rbnum; // 댓글 고유번호
	private int rborifbnum; // 그 댓글이 달린 원 글의 게시판 상 글번호
	private String rbid; // 댓글쓴놈 id
	private String rbcontent; // 댓글내용
	private String rbdate; // 덧글쓴날짜시간
	
	public int getRbnum() {
		return rbnum;
	}
	public void setRbnum(int rbnum) {
		this.rbnum = rbnum;
	}
	public int getRborifbnum() {
		return rborifbnum;
	}
	public void setRborifbnum(int rborifbnum) {
		this.rborifbnum = rborifbnum;
	}
	public String getRbid() {
		return rbid;
	}
	public void setRbid(String rbid) {
		this.rbid = rbid;
	}
	public String getRbcontent() {
		return rbcontent;
	}
	public void setRbcontent(String rbcontent) {
		this.rbcontent = rbcontent;
	}
	public String getRbdate() {
		return rbdate;
	}
	public void setRbdate(String rbdate) {
		this.rbdate = rbdate;
	}
	
	
	
	

}
