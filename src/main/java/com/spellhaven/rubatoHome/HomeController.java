package com.spellhaven.rubatoHome;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.spellhaven.rubatoHome.dao.IDao;
import com.spellhaven.rubatoHome.dto.FreeBoardDto;

@Controller
public class HomeController {
	
	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value="/index")
	public String index(Model model) {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		ArrayList<FreeBoardDto> fboardDtos = dao.fblistDao();
		
		model.addAttribute("freeboard01", fboardDtos.get(0)); 
		model.addAttribute("freeboard02", fboardDtos.get(1)); 
		model.addAttribute("freeboard03", fboardDtos.get(2)); 
		model.addAttribute("freeboard04", fboardDtos.get(3)); 
		
		
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
		
		model.addAttribute("yourface", dao.fbviewDao(fbnum));  // 진짜로 글 내용 보여 주는 함수. 인자 이름을 이상하게 지으니까 훨씬 신경써야 했고 힘들었다. 다음부턴 이러지 말자.
		model.addAttribute("fileInfo", dao.fbfileInfoDao(fbnum));
		
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
	
	
	@RequestMapping(value="/fbWrite", method = RequestMethod.POST)
	public String fbWrite(HttpServletRequest request, @RequestPart MultipartFile files) throws Exception {
		
		IDao dao = sqlSession.getMapper(IDao.class);
		
		String fbname = request.getParameter("fbname");
		String fbtitle = request.getParameter("fbtitle");
		String fbcontent = request.getParameter("fbcontent");
		
		HttpSession session = request.getSession();
		String fbid = (String) session.getAttribute("id");
		
		if (fbid == null) {
			fbid = "GUEST";
		}
		
		if (files.isEmpty()) { // 사용자가 게시글 작성 시 파일 첨부했나? 여부 판단하는 놈. (.isEmpty() 대신 == null도 가능하다. (대신 swag이 없다))
			
			dao.fbwriteDao(fbname, fbtitle, fbcontent, fbid); // 첨부파일이 없다? 사용자가 작성한 글만 DB에 삽입.
						
		} else {
			
			dao.fbwriteDao(fbname, fbtitle, fbcontent, fbid); // 일단 글은 찐다. 그 다음 파일업로드는...
			
			String oriFileName = files.getOriginalFilename(); // 업로드된 파일의 원래 이름
			String oriFileNameExtension = FilenameUtils.getExtension(oriFileName).toLowerCase(); // 업로드된 파일의 원래 이름에서 확장자(예: .jpg)만 추출했다. 혹시 모를 오류 방지를 위해 .toLowerCase()
			File destinationFile; // 꼭 File (java.io)를 import해야 해. 에러나지안캐 ㅋ
			String destinationFileName; // 실제 서버에 저장되는 파일 이름
			
			String fileUrl = "D:/springBoot_workspace/220621_1_RubatoHomepageProject/src/main/resources/static/uploads/";
			// 사용자가 업로드한 첨부파일들을 저장할 실제 폴더의 경로. 필요에 따라 oriFileNameExtension(확장자)을 가지고 if문 써서, 파일 종류에 따라 다른 새끼 폴더에 저장할 수도 있겠다.
			
			do {
				destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + oriFileNameExtension;
				// 영어 대소문자, 숫자가 혼합된 랜덤 32글자 문자열을 생성한 후 끝에 원본 파일 확장자를 붙인다.
				// 와! 그러면 서버상에는 모든 첨부파일을 겹치지 않는 고유한 이름으로 잘 저장할 수 있겠네요! (어. 맞아.)

				destinationFile = new File(fileUrl+destinationFileName);
				
			} while(destinationFile.exists());	// 설마 랜덤 32자리 이름인데 겹치겠어? 싶지만 교수님 특유의 장인정신으로... do-while문을 이용한 예외처리.
			
			destinationFile.getParentFile().mkdir();
			files.transferTo(destinationFile);
			
			// 이 코드 3줄 중요하다. " '작성' 버튼을 누르면서 현재 글에 해당하는 파일을 첨부하려면 현재 글 일련번호를 가져와야 하는데 그건 당장 '작성' 버튼을 눌렀을 때 생성된 거니까...
			ArrayList<FreeBoardDto> fbDtos = dao.fblistDao(); // Line 131에서 글을 썼는데 그걸 가져와.
			int fbnum = fbDtos.get(0).getFbnum(); // 글목록에서 최상단에 있는 글이 당장 쓴 글일 테니까.
		
			dao.fbfileInsertDao(fbnum, destinationFileName, oriFileName, fileUrl, oriFileNameExtension);
			
			// 어. 내가 이 코드 맞게썻니? (코드 자체는 맞다 제법인데? 그치만 여기 말고 board_view에 있어야 한다. "남이 써 놓은 글을 볼 때" fileInfo가 와야지.
			// 깜찍아! 너 Model 개념 사실 헷갈리지!! Model이랑 Request 차이점도 사실 헷갈리지!! (네.)
			// model.addAttribute("fileInfo", dao.fbfileInfoDao(fbnum));
			
		}
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









