package com.spellhaven.rubatoHome.dao;

import java.util.ArrayList;

import com.spellhaven.rubatoHome.dto.FreeBoardDto;

public interface IDao {

	// freeboard용 DAO
	public void fbwriteDao(String fbname, String fbtitle, String fbcontent, String fbid); // 자유게시판 글쓰기
	public ArrayList<FreeBoardDto> fblistDao(); // 자유게시판 글목록 가져오기 ㅋ
	public int fblistCountDao(); // 자유게시판에서 오천 원만 (안) 주면 "총 n개의 게시물이 있습니다." 세어 주는 놈
	public void fbBigHitDao(String fbnum); // 조회수 +1씩 해 주는 DAO 메서드. 특별한 이름을 지어 주고 싶어서, 최근 주가가 많이 하락한 연예 기획사 이름을 따 왔다.
	
	// member용 DAO
	
}
