<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${myboard.boardTitle}</title>
    <!-- CSS -->
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/answer.css" >
	<style type="text/css">
		textarea{
			resize: none;
		}
	</style>
</head>

<body>
   <script>
        // 이전 페이지로 이동하는 함수
        function goBack() {
            window.history.back();
        }
    </script>

    <div class="wrap">
    <%@ include file="../nav.jsp" %>
        <div class="container">
            <!-- 시작 -->
            <div class="inquiry-container">

                <h1 class="title">나의 문의</h1>

                <div class="divider"></div>
                <ul class="inner-box">
                    <li>
                        <dl class="inner-each-box">
                            <dt class="inner-title">제목
                            <dd class="inner-content">${myboard.boardTitle}</dd>
                            </dt>
                        </dl>
                    </li>
                    <li>
                        <dl class="inner-each-box">
                            <dt class="inner-title">작성일
                            <dd class="inner-content">${myboard.boardWritedate}</dd>
                            </dt>
                        </dl>
                    </li>
                    <li>
                        <dl class="inner-each-box">
                            <dt class="inner-title">작성자
                            <dd class="inner-content">${myboard.nickname}</dd>
                            </dt>
                        </dl>
                    </li>
                    <li>
                        <dl class="inner-each-box">
                            <dt class="inner-title">내용
                            <dd class="inner-content">
                            
                               <div class="img-wrap">
                               <c:if test="${empty myboard.boardImgReal}">
                                    <img class="upload-img hidden" alt="문의사진">
                                </c:if>
                                <c:if test="${not empty myboard.boardImgReal}">
                                    <img class="upload-img" alt="문의사진" src="/project/board/images/${myboard.boardImgReal}">
                                </c:if>
                                </div>
                                
                                <p>${myboard.boardContent}</p>
                            </dd>
                            </dt>
                        </dl>
                    </li>
                </ul>

                <h1 class="title">답변</h1>

                <div class="divider"></div>
                <ul class="inner-box">
                    <li>
                    	<form action="/project/board/inquiry/answer${myboard.boardCode}" method="post">
                        <dl class="inner-each-box">
                            <dt class="inner-title">내용
	                            <dd class="inner-content" id="content-answer">
	                              <input type="hidden" value="${myboard.boardCode}" name="boardCode">
                   	              <input type="hidden" value="${user.userCode}" name="userCode">
    	              	          <input type="hidden" value="null" name="commentDate">  
	                              <textarea rows="10" cols="85" name="commentContent">${comment.commentContent }</textarea>
	                            </dd>
	                            <button style="width: 100px; height: 40px; display: block; margin: 0 auto; margin-top:-120px;"> 전송 </button>
                            </dt>
                        </dl>
                        </form>
                    </li>
                </ul>

                

            </div>
        </div> <!-- container -->
        <div id="btn-wrapper"><button onclick="history.back()">목록 돌아가기</button></div>
<%@ include file="../footer-sub.jsp" %>

    </div>
    
</body>

</html>