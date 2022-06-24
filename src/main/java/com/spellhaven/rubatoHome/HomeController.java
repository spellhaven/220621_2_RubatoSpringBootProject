package com.spellhaven.rubatoHome;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spellhaven.rubatoHome.dao.IDao;
import com.spellhaven.rubatoHome.dto.FreeBoardDto;

@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	

	@RequestMapping(value="/index")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping(value="/board_list")
	public String board_list(HttpServletRequest request, Model model) {
		
		String searchKeyword = request.getParameter("searchKeyword");
		String searchOption = request.getParameter("searchOption");
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		ArrayList<FreeBoardDto> fboardDtos = null; // 이걸 선언할 때 초기값을 뭐라도 넣어 주는 게 중요하다. 비록 null일지라도. 안 그러면 if문에서 에러난다.
		// ~초기화의 중요성~ 지금 와서도 계속 강조하는중, ㅋ
		
		if (searchOption == null || searchKeyword == null) {
			fboardDtos = dao.fblistDao();
		} else {
			if(searchOption.equals("title")) { // 왜 String에 대해서는 == 대신 .equals를 써야 할까? 답) ==는 메모리 주소를 비교하고, .equals는 실제 값을 비교하기 때문.
				// 제목에서 특정 키워드를 검색한 결과.
				fboardDtos = dao.fbTitleSearchListDao(searchKeyword);
			
			} else if(searchOption.equals("content")) {
				// 내용에서 특정 키워드를 검색한 결과.
				fboardDtos = dao.fbContentSearchListDao(searchKeyword);
			
			} else {
				// 글쓴이에서 특정 키워드를 검색한 결과. else if(searchOption.equals("writer"))
				fboardDtos = dao.fbWriterSearchListDao(searchKeyword);
			}
		}
		 
		model.addAttribute("fblist", fboardDtos);
		model.addAttribute("listcount", fboardDtos.size());
		
		return "board_list";
	}
	
	@RequestMapping(value="/board_view")
	public String board_view(HttpServletRequest request, Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String fbnum = request.getParameter("fbnum");
		
		dao.fbBigHitDao(fbnum); // 어. 모델에 싣기 전에 먼저 죠훼수 증가시켜. 어.
		
		model.addAttribute("yourface", dao.fbviewDao(fbnum));  // 진짜로 글 내용 보여 주는 함수. "인자 이름 아무렇게나 짓기 운동 명예 참가자"
		
		return "board_view";
	}
	
	@RequestMapping(value="/board_write") 
	public String board_write() {
		
		// 왜 아무것도 없냐? 얘 있는 줄 모르고 아래에 fbWrite()라는 애 만들어서.
		
		return "board_write";
	}
	
	@RequestMapping(value="loginOk", method = RequestMethod.POST)
	public String loginOk(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		session.setAttribute("id", request.getParameter("mid"));
		model.addAttribute("memberId", request.getParameter("mid")); // 막상 만들어 놓고 쓸 데가 없어진 model아, "않미안해 ㅋ"
		
		return "redirect:index";
	}
	
	@RequestMapping(value="/logout")
	public String logout() {
		
		return "logout";
	}
	
	
	@RequestMapping(value="/fbWrite")
	public String fbWrite(HttpServletRequest request) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String fbname = request.getParameter("fbname");
		String fbtitle = request.getParameter("fbtitle");
		String fbcontent = request.getParameter("fbcontent");
		
		HttpSession session = request.getSession();
		String fbid = (String) session.getAttribute("id");
		
		if (fbid == null) {
			fbid = "GUEST";
		}
		
		dao.fbwriteDao(fbname, fbtitle, fbcontent, fbid);
		
		return "redirect:board_list";
	}
	
	@RequestMapping(value = "/delete")
	public String fbDelete(HttpServletRequest request) {
		
		String fbnum = request.getParameter("fbnum"); // 게시판에서 느그(삭제할 글)의 일련번호를 가져왔어, ㅋ
		
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.fbdeleteDao(fbnum);		
		
		return "redirect:board_list";
	}
	
}









