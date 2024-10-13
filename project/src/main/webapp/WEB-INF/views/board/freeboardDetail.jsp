<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.acorn.project.user.domain.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${freeboard.boardTitle}</title>
<link href="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.snow.css"   rel="stylesheet" />
<link rel="stylesheet"   href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/atom-one-dark.min.css" />
<link rel="stylesheet"   href="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- icon key -->
<script src="https://kit.fontawesome.com/7fa6781ad2.js" crossorigin="anonymous"></script>

<link rel="stylesheet" type="text/css"   href="${pageContext.request.contextPath}/resources/css/freeBoardDetail.css">
<link rel="stylesheet" type="text/css"   href="${pageContext.request.contextPath}/resources/css/reset.css">
<style type="text/css">

.ql-tooltip {
   display: none;
}

</style>
<script defer src="${pageContext.request.contextPath}/resources/js/changeType.js"></script>
<script defer src="${pageContext.request.contextPath}/resources/js/report.js"></script>


<%
User user = (User) session.getAttribute("user");
String userCode = (user != null) ? user.getUserCode() : null;
%>

</head>
<body>
   <div class="wrap">
      <%@ include file="../nav.jsp" %>
      <!-- 게시판 -->
      <div class="container">
         <!-- 사이드 메뉴 -->
           <div class="side_menu">
             <div class="side_menu_set">
                 <h3>자유게시판</h3>
                 <ul>
                     <li><a href="/project/board/free?type=-1">전체글</a></li>
                     <li><a href="/project/board/free?type=1">여행후기</a></li>
                     <li><a href="/project/board/free?type=2">꿀팁공유</a></li>
                     <li><a href="/project/board/free?type=3">질문있어요!</a></li>
                     <li><a href="/project/board/free?type=4">수다방</a></li>
                     <li><a href="/project/board/free?type=5">동행구해요!</a></li>
                 </ul>
             </div>
         </div>
         
         
         <div class="content_wrap">
            <!-- 게시판이름 -->
            <div class="boardType">${freeboard.boardType}</div>
            <div class="report">
               <button onclick="report()">
                  <img class="img-black"   src="${pageContext.request.contextPath}/resources/img/alert-black.png" style="width: 20px; height: 20px;" alt=""> 
                  <img class="img-red" src="${pageContext.request.contextPath}/resources/img/alert-red.png" style="width: 20px; height: 20px;" alt="">
               </button>
            </div>
            <div class="freeboard_page_title">
               <div class="title">${freeboard.boardTitle}</div>
               <div class="nickname">${freeboard.nickname}</div>
               <div class="writeDatw-boardViews">
                  <div class="writeDatw">${freeboard.boardWritedate}</div>
                  <div class="boardViews">조회수 ${freeboard.boardViews}</div>
              </div>

               <input type="hidden" class="boardUsercode"   value="${freeboard.userCode}"> 
               <input type="hidden" class="userCode" value=<%=userCode%>> 
               <input type="hidden" class="boardCode" value="${freeboard.boardCode}">
            </div>

            <div class="freeboard_page_content">
               <!-- 내용 -->
               <div class="content">${freeboard.boardContent}</div>
            </div>
            
           
            
         <%@ include file="comment.jsp" %>
        
         
         </div>
      </div>
   </div>
<div class="footer"><%@ include file="../footer-sub.jsp" %></div>
</body>
</html>