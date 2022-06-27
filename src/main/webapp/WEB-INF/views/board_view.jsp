<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8">
<title>클래식기타 커뮤니티</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board_left.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/board_view_main.css">
</head>
<body>
<div id="wrap">
<header>
  <a href="index"><img id="logo" src="${pageContext.request.contextPath}/resources/img/logo.png"></a>
<nav id="top_menu">
  	HOME | 
  
	<%     
		String sessionId = (String)session.getAttribute("id");
		if (sessionId == null) {
	%>
  
  		LOGIN 
  
    <% 
    	} else {
    %>  
    
    	<a href = "logout">LOGOUT</a>
    
    <%
    	}
    %>  
    
  
  	| JOIN | NOTICE
</nav>
<nav id="main_menu">
  <ul>
    <li><a href="board_list">자유 게시판</a></li>
    <li><a href="#">기타 연주</a></li>
    <li><a href="#">공동 구매</a></li>
    <li><a href="#">연주회 안내</a></li>
    <li><a href="#">회원 게시판</a></li>
  </ul>
</nav>
</header> <!-- header -->
<aside>
  <article id="login_box">
    <img id="login_title" src="${pageContext.request.contextPath}/resources/img/ttl_login.png">
    <div id="input_button">
    
    <% 
    	if (sessionId == null) {
    %>
    
    <form action = "loginOk" method = "post">
	    <ul id="login_input">
	      <li><input type="text" name = "mid"></li>
	      <li><input type="password" name = "mpw"></li>
	    </ul>
	    
	    <input type = "image" src = "${pageContext.request.contextPath}/resources/img/btn_login.gif">
	    <!-- <img id="login_btn" src="${pageContext.request.contextPath}/resources/img/btn_login.gif">-->
    </form>
    
    <% 
    	} else {
    
    out.print(sessionId);

	%>님 로그인 되었습니다 	<br> <!-- model에서 memberId를 가져온거임. (기억도 않 난다 ㅋ...) -->
    	<a href = "logout">LOG OUT</a>
    	
    <% 
    	}
    %>
	</div>
    
    <% 
    	if (sessionId == null) {
    %>  
    <div class="clear"></div>
    <div id="join_search">
      <img src="${pageContext.request.contextPath}/resources/img/btn_join.gif">
      <img src="${pageContext.request.contextPath}/resources/img/btn_search.gif">
    </div>
    
    <% 
    	} else {
	%>
		<div></div>
	<% 
    	}
	%>

  </article>
  <nav id="sub_menu">
    <ul>
      <li><a href="board_list">+ 자유 게시판</a></li>
      <li><a href="#">+ 방명록</a></li>
      <li><a href="#">+ 공지사항</a></li>
      <li><a href="#">+ 등업요청</a></li>
      <li><a href="#">+ 포토갤러리</a></li>
    </ul>
  </nav>
  <article id="sub_banner">
    <ul>
      <li><img src="${pageContext.request.contextPath}/resources/img/banner1.png"></li>
      <li><img src="${pageContext.request.contextPath}/resources/img/banner2.png"></li>		
      <li><img src="${pageContext.request.contextPath}/resources/img/banner3.png"></li>
    </ul>	
  </article>
</aside> 

<section id="main">
  <img src="${pageContext.request.contextPath}/resources/img/comm.gif">
  <h2 id="board_title">자유 게시판 </h2>
  <div id="view_title_box"> 
    <span>${yourface.fbtitle }</span>
    <span id="info">글쓴쉑: ${yourface.fbname } | 조회수 : ${yourface.fbhit } | ${yourface.fbdate }</span>
  </div>	
  <p id="view_content">
    ${yourface.fbcontent }
  </p>	
  <hr>
  	<br>
  
  		
	  	<p id = "file_info">
	  		※첨부파일:
	  		<a href = "${pageContext.request.contextPath}/resources/uploads/${fileInfo.fileName}" style = "text-decoration:none">${fileInfo.fileOriName}</a>
	  	</p>
	  	<c:if test="${fileInfo.fileExtension == 'jpg' or fileInfo.fileExtension == 'png' or fileInfo.fileExtension == 'gif' or fileInfo.fileExtension == 'bmp'}">
		  	<p id = "image_view">
		  		<img width = "200" src = "${pageContext.request.contextPath}/resources/uploads/${fileInfo.fileName}">
		  	</p>
	  	</c:if>
  	<br>
  <hr>
  <!-- 해당 글의 덧글 리스트 출력해주는놈 -->
  
  <table border = "1" cellpadding = "0" cellspacing = "0">
  	<c:forEach items="${rblist}" var="rbdto">	
	  	<tr>
	  		<td>아이디 : ${rbdto.rbid }</td> 
	  		<td>${rbdto.rbcontent } </td>
	  		<td>작성시간 : ${rbdto.rbdate }</td>
	  	</tr>
	</c:forEach>  	
  </table>
  
  <hr>
  <!-- 댓글입력 UI. (댓글창 만들려니까 킹받네. 너를 위해서만 sql 테이블을 만듫엀어 . . . 😩)-->
  <div id="comment_box">
  	<form action = "replyOk">
  		<!-- fbnum 싣다가 ㅈㄴ 킹받는 오류를 맏닥뜨렸다. 조심해라. value = "${yourface.fbnum} "이라고 하면 value는 EL로 가져온 fbnum에다가 [[[맨 끝 공백 하나까지]]] 실린다!!! 진짜 킹받네 -->
  		<input type = "hidden" name = "fbnum" value = "${yourface.fbnum}"> <!-- 이렇게 해야 원글번호가 같이 request에 실려간다. -->
	    <img id="title_comment" src="${pageContext.request.contextPath}/resources/img/title_comment.gif">
	    <textarea name = "replycontent"></textarea>
	    <input type = "image" id="ok_ripple" src = "${pageContext.request.contextPath}/resources/img/ok_ripple.gif">
	    <!-- img id="ok_ripple" src="${pageContext.request.contextPath}/resources/img/ok_ripple.gif" -->
	</form>    
  </div>
   	
  <div id="buttons">
    <a href="delete?fbnum=${yourface.fbnum}"><img src="${pageContext.request.contextPath}/resources/img/delete.png"></a>		
    <a href="board_list"><img src="${pageContext.request.contextPath}/resources/img/list.png"></a>
    <a href="board_write"><img src="${pageContext.request.contextPath}/resources/img/write.png"></a>			
  </div>
</section> <!-- section main -->
<div class="clear"></div>
<footer>
  <img id="footer_logo" src="${pageContext.request.contextPath}/resources/img/footer_logo.gif">
  <ul id="address">
    <li>서울시 깜찍구 깜찍동 1234 우 : 123-1234</li>  
    <li>TEL : 031-123-1234  Email : email@domain.com</li>
    <li>COPYRIGHT (C) 루바토 ALL RIGHTS RESERVED</li>
  </ul>
  <ul id="footer_sns">
    <li><img src="${pageContext.request.contextPath}/resources/img/facebook.gif"></li>  
    <li><img src="${pageContext.request.contextPath}/resources/img/blog.gif"></li>
    <li><img src="${pageContext.request.contextPath}/resources/img/twitter.gif"></li>
  </ul>
</footer> <!-- footer -->
</div> <!-- wrap -->
</body>
</html>