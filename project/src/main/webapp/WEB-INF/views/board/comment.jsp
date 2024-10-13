<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"   href="${pageContext.request.contextPath}/resources/css/comment.css">
<script defer src="${pageContext.request.contextPath}/resources/js/comment.js"></script>
<script defer src="${pageContext.request.contextPath}/resources/js/LikeArch.js"></script>
<!--jquery-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>

<body>
    <div class="freeboard_page">
       <div class="freeboard_page_img">
          <div>
             <!-- 좋아요, 스크랩 부분 -->
             <a href="#" class="like-btn"> 
                <i class="fa-regular fa-heart fa-xl like-icon"></i> <!-- 좋아요 클릭 변경 아이콘 -->
                <i class="fa-solid fa-heart fa-xl liked-icon hidden"></i>
             </a> <a href="#" class="scrap-btn"> 
                <i class="fa-regular fa-bookmark fa-lg scrap-icon"></i> <!-- 글 스크랩 시 변경 아이콘 -->
                <i class="fa-solid fa-bookmark fa-lg scraped-icon hidden"></i>
             </a>

          </div>
       
       <input type="hidden" class="admin" value="${user.userId}">
       
       <h3 class="count-comment">댓글 ${count}</h3>
       </div>

       <!-- 댓글 보기 -->
       <div class="freeboard_page_comments">

          <div id="no-comments-message" style="display: none;">작성된 댓글이 없습니다.</div>
          <div class="comment-box">
             <!-- test -->
             <c:forEach var="comment" items="${comments}" varStatus="loop">

                <div class="comment" id="comment_${loop.index}"  data-user-code="${comment.userCode}">
                   <div class="comment_name">${comment.nickname}</div>
                   <input type="hidden" class="modiCommentCode" value="${comment.commentCode}">
                   <div class="comment_content">${comment.commentContent}</div>
                   <div class="comment_date">${comment.commentDate}</div>
                   <button class="btnModi" onclick="editComment(this)">수정</button>
                   <button class="btnDel" onclick="delComment(this)">삭제</button>
                </div>
             </c:forEach>
          </div>

          <!-- 댓글 작성 -->
          <div>
             <div class="my_comment">
                <div class="my_comment_sender">${user.nickname}
                   <button class="my_comment_sender_btn" onclick="regComment()">등록</button>
                </div>
                <input type="hidden" id="regBoardCode"value="${freeboard.boardCode}"> 
                <input type="hidden" id="regUserCode" value="${user.userCode}"> 
                <input type="hidden" id="regCommentDate" value="null">
                <div class="comment_textarea">
                   <textarea class="my_comment_content" name="" maxlength="400" id="regComment" placeholder="댓글을 입력하세요." ></textarea>
                </div>
             </div>
          </div>
       </div>
    </div>
    
    <div class="freeBoardForm-btn">
    	<%
		    String currentURL = request.getRequestURL().toString();
    		pageContext.setAttribute("currentURL", currentURL);
		%>
		
		<c:if test="${currentURL eq 'http://localhost:8080/project/WEB-INF/views/board/freeboardDetail.jsp'}"> 
		    <button onclick="window.location.href='${pageContext.request.contextPath}/board/free?type=-1'">목록보기</button> 
		</c:if>
		
		<c:if test="${currentURL eq 'http://localhost:8080/project/WEB-INF/views/board/routePost.jsp'}"> 
		    <button onclick="window.location.href='${pageContext.request.contextPath}/board/route'">목록보기</button> 
		</c:if>
		
		<c:if test="${currentURL eq 'http://localhost:8080/project/WEB-INF/views/board/freeboardDetail.jsp'}"> 
		  	<c:if test="${user.userId eq 'admin'}">
            	<button type="button" onclick="location.href='/project/board/my/del/${freeboard.boardCode}'">삭제</button>
        	</c:if>
		</c:if>
		
		<c:if test="${currentURL eq 'http://localhost:8080/project/WEB-INF/views/board/routePost.jsp'}"> 
		   <c:if test="${user.userId eq 'admin'}">
            	<button type="button" onclick="location.href='/project/board/route/del/${routeBoard.boardCode}'">삭제</button>
          </c:if>
		</c:if>
		
		
      	
       <button id="boardModi" onclick="window.location.href='${pageContext.request.contextPath}/board/my/${freeboard.boardCode}'">수정</button>
    </div>
</body>
</html>