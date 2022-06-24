package com.spellhaven.rubatoHome.dto;

public class FileDto {
	
	// 글 내용을 불러오는 게 FreeBoardDto였다면, 이 FileDto는 "한 놈만 패". 오직 첨부파일을 위해 만든 DTO다.
	
	private int fno; // 파일의 고유번호(sql 시퀀스로 넣을거임, ㅋ)
	private int bno; // 파일이 첨부된 게시판 글의 고유번호
	private String fileName; // 파일 이름 (시스템이 알아서 만든 고유 이름. 이래야 파일 이름끼리 겹치지 않는다.)
	private String fileOriName; // 원래 파일 이름
	private String fileUrl; // 업로드된 파일이 저장되는 곳
	private String fileExtension; // 파일 확장자
	
	public int getFno() {
		return fno;
	}
	public void setFno(int fno) {
		this.fno = fno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileOriName() {
		return fileOriName;
	}
	public void setFileOriName(String fileOriName) {
		this.fileOriName = fileOriName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	
}
