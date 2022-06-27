package com.spellhaven.rubatoHome.dto;

public class FreeBoardDto {

	private int fbnum; // 게시판 글 번호
	private String fbid; // 게시판 글쓴쉒 아이디
	private String fbname; // 글쓴쉒 이름
	private String fbtitle; // 글 제목
	private String fbcontent; // 글 내용
	private String fbdate; // 글 작성 일자
	private String fbhit; // 하하 fbhit 너도 int가 되고 싶나? "int돼봐" "int돼봐" "안해줘" (단소 살인마 톤으로)
	private String fbreplycount; // 해당 글에 달린 댓글 수.
	
	public FreeBoardDto() {
		super();
	}
	
	public FreeBoardDto(int fbnum, String fbid, String fbname, String fbtitle, String fbcontent, String fbdate,
			String fbhit, String fbreplycount) {
		super();
		this.fbnum = fbnum;
		this.fbid = fbid;
		this.fbname = fbname;
		this.fbtitle = fbtitle;
		this.fbcontent = fbcontent;
		this.fbdate = fbdate;
		this.fbhit = fbhit;
		this.fbreplycount = fbreplycount;
	}



	public int getFbnum() {
		return fbnum;
	}

	public void setFbnum(int fbnum) {
		this.fbnum = fbnum;
	}

	public String getFbid() {
		return fbid;
	}

	public void setFbid(String fbid) {
		this.fbid = fbid;
	}

	public String getFbname() {
		return fbname;
	}

	public void setFbname(String fbname) {
		this.fbname = fbname;
	}

	public String getFbtitle() {
		return fbtitle;
	}

	public void setFbtitle(String fbtitle) {
		this.fbtitle = fbtitle;
	}

	public String getFbcontent() {
		return fbcontent;
	}

	public void setFbcontent(String fbcontent) {
		this.fbcontent = fbcontent;
	}

	public String getFbdate() {
		return fbdate;
	}

	public void setFbdate(String fbdate) {
		this.fbdate = fbdate;
	}

	public String getFbhit() {
		return fbhit;
	}

	public void setFbhit(String fbhit) {
		this.fbhit = fbhit;
	}

	public String getFbreplycount() {
		return fbreplycount;
	}

	public void setFbreplycount(String fbreplycount) {
		this.fbreplycount = fbreplycount;
	}
	
	
	
	
}
