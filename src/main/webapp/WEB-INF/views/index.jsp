<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>     
<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8">
<title>가장 깜찍한 클래식기타 커뮤니티, 루바토</title>

<!-- contextPath로 상대경로 잘 업뎃했다. -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css">
</head>
<body>
<div id="wrap">
<header> <!-- index.html에서 그냥 jsp 요청 형식인 index로 잘 바꿨다. -->
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
    
  
  	| <a href = "join">JOIN</a> | NOTICE
</nav>
<nav id="main_menu">
  <ul> <!-- board_list.html에서 그냥 jsp 요청 형식인 board_list로 잘 바꿨다. -->
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
      <a href = "join"><img src="${pageContext.request.contextPath}/resources/img/btn_join.gif"></a>
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
  <article id="guestbook">
    <div id="guestbook_title">
      <img src="${pageContext.request.contextPath}/resources/img/ttl_memo.gif">
    </div>
    <ul>
      <li>안녕하세요!</li>
      <li>안녕하세요!</li>
      <li>안녕하세요!</li>
      <li>안녕하세요!</li>
    </ul>
  </article>
</aside>

<section id="main">
  <img src="${pageContext.request.contextPath}/resources/img/main_img.png">
  <section id="notice_free_youtube">
    <article id="notice"> 	<!-- 공지사항 -->
      <div class="latest_title">
        <img class="latest_img" src="${pageContext.request.contextPath}/resources/img/latest1.gif">
        <img class="more" src="${pageContext.request.contextPath}/resources/img/more.gif">
        <div class="clear"></div>					
      </div>
      <div class="latest_content">
        <img class="image" src="${pageContext.request.contextPath}/resources/img/book_pen.gif">
        <ul class="list">
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>								
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>		
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>	
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>				
        </ul>							
      </div>
    </article>
    <article id="free"> 	<!—자유 게시판 -->
      <div class="latest_title">
        <img class="latest_img" src="${pageContext.request.contextPath}/resources/img/latest2.gif">
        <a href = "board_list">
        	<img class="more" src="${pageContext.request.contextPath}/resources/img/more.gif">
        </a>	
        <div class="clear"></div>					
      </div>
      <div class="latest_content">
        <img class="image" src="${pageContext.request.contextPath}/resources/img/book_pen.gif">
        
        <ul class="list">
          <li>
            <div class="subject">
            
            	<!-- 자게 최근글이 14자보다 길면 자르고 ... 붙여 주는 함수. -->
            	
            	<!-- 그냥 <div class="subject">${fn:substring(freeboard02.fbtitle, 0, 14)}...</div> 
            		 라고 하면 안 된다. 왜냐면 이러면 짧은 제목 끝에도 다 ...이 붙어 버리잖아. -->
            	<a href = "board_view?fbnum=${freeboard01.fbnum}" style = "text-decoration:none">
	            	<c:choose>
	            		<c:when test="${fn:length(freeboard01.fbtitle) > 14 }">
	            			<c:out value = "${fn:substring(freeboard01.fbtitle, 0, 14)}"/>...
	            		</c:when>
	            		<c:otherwise>
	            			<c:out value = "${freeboard01.fbtitle}"/>
	            		</c:otherwise>
	            	</c:choose>
            	</a>
            </div>
            <div class="date">
            	<c:out value = "${fn:substring(freeboard01.fbdate, 0, 10)}"></c:out> <!-- 날짜를 fn:substring으로 안 자르면 css가 터지더라.-->
            </div>
            <div class="clear"></div>		
          </li>
          
          <li>
            <div class="subject">
            	<a href = "board_view?fbnum=${freeboard02.fbnum}" style = "text-decoration:none">
	            	<c:choose>
	            		<c:when test="${fn:length(freeboard02.fbtitle) > 14 }">
	            			<c:out value = "${fn:substring(freeboard02.fbtitle, 0, 14)}"/>...
	            		</c:when>
	            		<c:otherwise>
	            			<c:out value = "${freeboard02.fbtitle}"/>
	            		</c:otherwise>
	            	</c:choose>
	            </a>	
            </div>	
            <div class="date">
            	<c:out value = "${fn:substring(freeboard02.fbdate, 0, 10)}"></c:out>
            </div>
            <div class="clear"></div>		
          </li>
          
          <li>
            <div class="subject">
            	<a href = "board_view?fbnum=${freeboard03.fbnum}" style = "text-decoration:none">
	            	<c:choose>
	            		<c:when test="${fn:length(freeboard03.fbtitle) > 14 }">
	            			<c:out value = "${fn:substring(freeboard03.fbtitle, 0, 14)}"/>...
	            		</c:when>
	            		<c:otherwise>
	            			<c:out value = "${freeboard03.fbtitle}"/>
	            		</c:otherwise>
	            	</c:choose>
	            </a>	
            </div>
            <div class="date">
            	<c:out value = "${fn:substring(freeboard03.fbdate, 0, 10)}"></c:out>
            </div>
            <div class="clear"></div>		
          </li>
          
          <li>
            <div class="subject">
            	<a href = "board_view?fbnum=${freeboard04.fbnum}" style = "text-decoration:none">
	            	<c:choose>
	            		<c:when test="${fn:length(freeboard04.fbtitle) > 14 }">
	            			<c:out value = "${fn:substring(freeboard04.fbtitle, 0, 14)}"/>...
	            		</c:when>
	            		<c:otherwise>
	            			<c:out value = "${freeboard04.fbtitle}"/>
	            		</c:otherwise>
	            	</c:choose>
	            </a>	
            </div>
            <div class="date">
            	<c:out value = "${fn:substring(freeboard04.fbdate, 0, 10)}"></c:out>
            </div>
            <div class="clear"></div>		
          </li>
								
        </ul>							
      </div>
    </article>			
    <article id="youtube">	    <!—YOUTUBE 동영상 -->		
      <div class="latest_title">
        <img class="latest_img" src="${pageContext.request.contextPath}/resources/img/latest3.gif">
        <img class="more" src="${pageContext.request.contextPath}/resources/img/more.gif">
        <div class="clear"></div>					
      </div>				
      <img id="youtube_img" src="${pageContext.request.contextPath}/resources/img/bach.jpg">
    </article>
  </section> <!-- notice_free_youtube -->
  <section id="gallery">
    <img src="${pageContext.request.contextPath}/resources/img/latest4.gif">
    <div id="gallery_box">
      <div id="gallery_list">
        <div class="items">
          <ul>
            <li><img src="${pageContext.request.contextPath}/resources/img/img1.jpg"></li>
            <li>기타 페스티벌 4중주</li>
          </ul>
        </div>
        <div class="items">
          <ul>
            <li><img src="${pageContext.request.contextPath}/resources/img/img1.jpg"></li>
            <li>기타 페스티벌 4중주</li>
          </ul>
        </div>
        <div class="items">
          <ul>
            <li><img src="${pageContext.request.contextPath}/resources/img/img1.jpg"></li>
            <li>기타 페스티벌 4중주</li>
          </ul>
        </div>
      </div> <!-- galley_list -->
    </div> <!-- gallery_box -->		
  </section> <!-- gallery -->
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

