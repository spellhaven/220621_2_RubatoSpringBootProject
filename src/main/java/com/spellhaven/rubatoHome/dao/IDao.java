package com.spellhaven.rubatoHome.dao;

import java.util.ArrayList;

import com.spellhaven.rubatoHome.dto.FreeBoardDto;

public interface IDao {

	// freeboard용 DAO
	public void fbwriteDao(String fbname, String fbtitle, String fbcontent, String fbid); // 자유게시판 글쓰기
	//public int fblistCountDao(); // 자유게시판에서 오천 원만 (안) 주면 "총 n개의 게시물이 있습니다." 세어 주는 놈인데 .size() 때문에 사실 필요없었네^^
	public void fbBigHitDao(String fbnum); // 조회수 +1씩 해 주는 DAO 메서드. 특별한 이름을 지어 주고 싶어서, 최근 주가가 많이 하락한 연예 기획사 이름을 따 왔다.
	public FreeBoardDto fbviewDao(String fbnum); // 자유게시판 글 보기 ㅋ
	
	public ArrayList<FreeBoardDto> fblistDao(); // 자유게시판 전체 글목록 가져오기 ㅋ
	public ArrayList<FreeBoardDto> fbTitleSearchListDao(String keyword); // 자유게시판 - 제목으로 검색한 글 리스트
	public ArrayList<FreeBoardDto> fbContentSearchListDao(String keyword); // 자유게시판 - 글내용으로 검색한 글 리스트
	public ArrayList<FreeBoardDto> fbWriterSearchListDao(String keyword); // 자유게시판 - 글쓴이로 검색한 글 리스트
	
	public void fbdeleteDao(String fbnum);
	
	
	
	// member용 DAO
	
}