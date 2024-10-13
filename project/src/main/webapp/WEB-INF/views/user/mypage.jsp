<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>마이페이지</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >

<!-- 모달용 css, js -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pointCharge.css" >

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script defer src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>

<style>
.container{
   position: relative;
   width: 750px;
   margin: 0 auto;
    padding-bottom: 150px;
}

.page-name {
   margin-top: 75px;
   font-size: 26px;
   font-weight: bold;
}

hr {
   margin: 20px 0;
   height: 2px;
}

.my_top {
   margin-top: 30px;
   display: flex;
   justify-content: space-between;
}

.my_side {
   background-color: #fcfcfc;
   width: 210px;
   padding: 20px;
   box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
}

.my_side ul li {
   padding: 5px 0;
}

.my-content {
   background-color: #fcfcfc;
   width: 500px;
   box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.1);
}

.my-content>div {
   padding: 25px
}

.my-sub-info {
   margin: 20px 0;
   font-size: 14px;
   color: #5c5c5c;
}

.my-sub-info, .my-sub-info td {
   border: none;
}

.my-sub-info tr td {
   padding-right: 20px;
}

.my-info {
   width: 100%;
}

.my_name span {
   font-size: 24px;
   font-weight: bold;
}

.my_point {
   width: 100%;
   display: flex;
}

.my_point_text {
   color: #ffc9c9;
   font-size: 24px;
   font-weight: bold;
   margin-right: 10px;
}

.my_point_btn {
   margin: 5px 3px;
}

.my_point_btn button {
   width: 75px;
   height: 25px;
   border-radius: 25px;
   border: 0px solid #5c5c5c;
   background-color: #e7e7e7;
   transition: 0.3s;
}

.my_point_btn:hover {
   text-decoration: none;
}

.my_point_btn button:hover {
   background-color: #ffc9c9;
}

.my-list {
   position: relative;
   width: 750px;
   min-height: 315px;
   margin: 50px auto;
}

.my-list-ul {
   margin: 0 auto;
   font-weight: bold;
}

.my-list-ul ul {
   display: flex;
   justify-content: space-between;
   padding: 20px 10px;
   border-bottom: 2px solid #5c5c5c;
}

.my-list-ul hr {
   width: 100%;
   margin: 15px auto;
}

/* 페이징 */
.paging {
   padding: 30px;
   text-align: center;
   width: 400px;
   align-items: center;
   margin: 0 auto;
}

.paging a {
   width: 40px;
   height: 30px;
   line-height: 30px;
   display: inline-block;
   border-radius: 5px;
   color: rgb(53, 53, 53)
   
}
.paging a:hover {
   text-decoration: none;
   color: #d1d1d1;
}
.paging_i{
   background-color: #ffffff;
}
.paging_i.active {
    font-weight: bold;
    color: black;
}

.list-tbl {
   width: 90%;
   margin: 10px auto;
}

.list-head {
   color: #5c5c5c;
   font-weight: bold;
   text-align: left;
   border-bottom: 1px solid #a7a7a7;
   
}
.list-no{	
	width: 10%;
}
.head-title {
 	max-width: 100px;
   overflow: hidden; 
   text-overflow: ellipsis; 
   white-space: nowrap;
   text-align: left !important;
}


.list-title{
	max-width: 200px;
   padding-left: 5px !important;
   overflow: hidden;
   text-overflow: ellipsis; 
   white-space: nowrap;
   text-align: left !important;
}
.list-date{
	width: 25%;
}
.list-head td {
   padding: 10px 20px;
}

.list-data {
   text-align: left;
   border-bottom: 1px dotted #a7a7a7;
}

.list-data td {
   padding: 10px 20px;
}

#account-holder{
   width: 200px;
}
td{
	text-align: center;
}
.post-title {
   display: block;
   width: 330px;
   text-align: left !important;
   overflow: hidden;
   text-overflow: ellipsis;
   white-space: nowrap;
   cursor: pointer;
}

.head-title, .post-title {
   padding-left: 5px !important;
}
.inquiry{
	margin-left: 160px;
}
.report{
	margin-right: 160px;
}

</style>

</head>
<body>

   
<div class="wrap">
   <%@ include file="../nav.jsp" %>
   <div class="container">
        <div class="page-name">
         마이페이지
           <hr>
         </div>
      <!-- my_top -->
      <div class="my_top">
           <div class="my_side">
               <ul>
                  <li><a href="/project/board/my/inquiry6">1:1 문의내역</a></li>
                   <li><a href="/project/point/showMyPoint.do">포인트 내역</a></li>
                   <li><a href="/project/user/modifyInfo.do">정보수정</a></li>
                   <li><a href="" onclick="deleteAccount()" style="cursor: pointer;">회원탈퇴</a></li>
              </ul>
         </div>

            <div class="my-content">
               <div class="my-info">
                  <div class="my_name">
                     <span>${user.nickname}</span> 님 반갑습니다😘
                  </div>
               </div>


               <div class="my_point">
                   <div class="my_point_text">${user.userPoint}P</div>
               <div class="my_point_btn">
                  <button class="open" id="openModal">충전</button>
                  <button class="open" id="openModal2">환전</button>
               </div>
               </div>
         </div>
      </div>

      <!-- my_list -->
   <div class="my-list">
    <c:choose>
    	<c:when test="${user.userId eq 'admin'}">
			<div class="my-list-ul">
		        <ul>
		            <li class="inquiry"><a href="/project/user/mypage.do/inquiry">문의 내역</a></li>
		            <li class="report"><a href="/project/user/mypage.do/report">신고 내역</a></li>
		        </ul>
		    </div>
    	</c:when>
    	<c:otherwise>
	   		 <div class="my-list-ul">
		        <ul>
		            <li><a href="/project/user/mypage.do">작성한 글</a></li>
		            <li><a href="/project/user/mypage.do/arch">스크랩</a></li>
		            <li><a href="/project/user/mypage.do/like">추천한 글</a></li>
		            <li><a href="/project/user/mypage.do/point">구매한 글</a></li>
		            <li><a href="/project/user/mypage.do/com" onclick="">내 댓글</a></li>
		        </ul>
		    </div>
    	</c:otherwise>
    </c:choose>

	<c:choose>
	    <c:when test="${url.contains('com')}">
	        <table class="list-tbl">
	            <tr class="list-head">
	                <td>No.</td>
	                <td>내용</td>
	                <td>글 제목</td>
	                <td>작성일</td>
	            </tr>
	
	            <c:forEach var="board" items="${list}" varStatus="status">
	                <tr class="list-data">
	                    <td  class="list-no">${(paging.totRecords - (status.index + 1) - ((paging.currentPage - 1) * paging.pageSize)) + 1}</td>
	                    <td class="head-title" >
	                        <c:if test="${board.boardType != 0}">
	                       
	                            <a title="<c:out value='${board.commentContent}' />" href="/project/board/free/${board.boardCode}">
	                                <c:out value="${board.commentContent}" />
	                            </a>
	                        </c:if>
	
	                        <c:if test="${board.boardType == 0}">
	                            <a title="<c:out value='${board.commentContent}' />" href="/project/board/route/${board.boardCode}">
	                                <c:out value="${board.commentContent}" />
	                            </a>
	                        </c:if>
	                    </td>
	
	                    <td class="list-title">
	                         <c:if test="${board.boardType != 0}">
	                            <a title="<c:out value='${board.boardTitle}' />" href="/project/board/free/${board.boardCode}">
	                                <c:out value="${board.boardTitle}" />
	                            </a>
	                        </c:if>
	
	                        <c:if test="${board.boardType == 0}">
	                            <a title="<c:out value='${board.boardTitle}' />" href="/project/board/route/${board.boardCode}">
	                                <c:out value="${board.boardTitle}" />
	                            </a>
	                        </c:if>
	                    </td>
	
	                    <td class="list-date"><c:out value="${board.commentDate}" /></td>
	                </tr>
	            </c:forEach>
	        </table>
	    </c:when>
	    
	     <c:when test="${url.contains('inquiry')}">
	        <table class="list-tbl">
	            <tr class="list-head">
	                <td>No.</td>
	                <td>내용</td>
	                <td>글 제목</td>
	                <td>작성일</td>
	            </tr>
	
	              <c:forEach var="board" items="${list}" varStatus="status">
	                <tr class="list-data">
	                    <td>${(paging.totRecords - (status.index + 1) - ((paging.currentPage - 1) * paging.pageSize)) + 1}</td>
	                    <td class="post-title">
                            <a title="<c:out value='${board.boardTitle}' />" href="/project/board/inquiry/answer${board.boardCode}">
                                <c:out value="${board.boardTitle}" />
                            </a>
	
	                    </td>
	
	                    <td>
	                        <c:choose>
	                            <c:when test="${board.boardType == 0}">경로게시판</c:when>
	                            <c:when test="${board.boardType == 1}">여행후기</c:when>
	                            <c:when test="${board.boardType == 2}">꿀팁공유</c:when>
	                            <c:when test="${board.boardType == 3}">질문있어요</c:when>
	                            <c:when test="${board.boardType == 4}">수방</c:when>
	                            <c:when test="${board.boardType == 5}">동행 구해요!</c:when>
	                            <c:when test="${board.boardType == 6}">문의</c:when>
	                            <c:otherwise>알 수 없음</c:otherwise>
	                        </c:choose>
	                    </td>
	
	                    <td><c:out value="${board.boardWritedate}" /></td>
	                </tr>
	            </c:forEach>
	            
	        </table>
	    </c:when>
	    
	    <c:when test="${url.contains('report')}">
	        <table class="list-tbl">
	            <tr class="list-head">
	                <td>No.</td>
	                <td>글 제목</td>
	                <td>신고자 아이디</td>
	                <td>작성일</td>
	            </tr>
	
	              <c:forEach var="board" items="${list}" varStatus="status">
	                <tr class="list-data">
	                    <td class="list-no">${(paging.totRecords - (status.index + 1) - ((paging.currentPage - 1) * paging.pageSize)) + 1}</td>
	                    <td class="head-title" style="max-width: 300px;">
                          <a title="<c:out value='${board.boardTitle}' />" href="/project/board/free/${board.boardCode}">
                                <c:out value="${board.boardTitle}" />
                            </a>
							
	                    </td>
						
	                    <td> ${board.reportingUserId} </td>
					
	                    <td class="list-date"><c:out value="${board.reportDate}" /></td>
	                </tr>
	            </c:forEach>
	            
	        </table>
	    </c:when>
	    
	    <c:otherwise>
	        <table class="list-tbl">
	            <tr class="list-head">
	                <td>No.</td>
	                <td>글 제목</td>
	                <td>카테고리</td>
	                <td>작성일</td>
	            </tr>
	
	            <c:forEach var="board" items="${list}" varStatus="status">
	                <tr class="list-data">
	                    <td>${(paging.totRecords - (status.index + 1) - ((paging.currentPage - 1) * paging.pageSize)) + 1}</td>
	                    <td class="post-title">
	                        <c:if test="${board.boardType != 0}">
	                            <a title="<c:out value='${board.boardTitle}' />" href="/project/board/free/${board.boardCode}">
	                                <c:out value="${board.boardTitle}" />
	                            </a>
	                        </c:if>
	
	                        <c:if test="${board.boardType == 0}">
	                            <a title="<c:out value='${board.boardTitle}' />" href="/project/board/route/${board.boardCode}">
	                                <c:out value="${board.boardTitle}" />
	                            </a>
	                        </c:if>
	                    </td>
	
	                    <td>
	                        <c:choose>
	                            <c:when test="${board.boardType == 0}">경로게시판</c:when>
	                            <c:when test="${board.boardType == 1}">여행후기</c:when>
	                            <c:when test="${board.boardType == 2}">꿀팁공유</c:when>
	                            <c:when test="${board.boardType == 3}">질문있어요</c:when>
	                            <c:when test="${board.boardType == 4}">수방</c:when>
	                            <c:when test="${board.boardType == 5}">동행 구해요!</c:when>
	                            <c:when test="${board.boardType == 6}">문의</c:when>
	                            <c:otherwise>알 수 없음</c:otherwise>
	                        </c:choose>
	                    </td>
	
	                    <td><c:out value="${board.boardWritedate}" /></td>
	                </tr>
	            </c:forEach>
	        </table>
	     </c:otherwise>
	</c:choose>
 </div>
			
	 

         <div class="paging">
         <c:choose>
         
         	<c:when test="${url.contains('arch')}">
	         	<c:if test="${paging.currentGrp > 1}">
	                 <a href="/project/user/mypage.do/arch?page=${paging.grpStartPage - 1}">이전</a>
	             </c:if>
	             <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
	                 <a class="paging_i" href="/project/user/mypage.do/arch?page=${i}">${i}</a>
	             </c:forEach>
	             <c:if test="${paging.grpEndPage <  paging.totalPage}">
	                 <a href="/project/user/mypage.do/arch?page=${paging.grpEndPage + 1}">다음</a>
	             </c:if>
         	</c:when>
         	
    	   	<c:when test="${url.contains('like')}">
	         	<c:if test="${paging.currentGrp > 1}">
	                 <a href="/project/user/mypage.do/like?page=${paging.grpStartPage - 1}">이전</a>
	             </c:if>
	             <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
	                 <a class="paging_i" href="/project/user/mypage.do/like?page=${i}">${i}</a>
	             </c:forEach>
	             <c:if test="${paging.grpEndPage <  paging.totalPage}">
	                 <a href="/project/user/mypage.do/like?page=${paging.grpEndPage + 1}">다음</a>
	             </c:if>
         	</c:when>
         	
    	  	<c:when test="${url.contains('com')}">
	         	<c:if test="${paging.currentGrp > 1}">
	                 <a href="/project/user/mypage.do/com?page=${paging.grpStartPage - 1}">이전</a>
	             </c:if>
	             <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
	                 <a class="paging_i" href="/project/user/mypage.do/com?page=${i}">${i}</a>
	             </c:forEach>
	             <c:if test="${paging.grpEndPage <  paging.totalPage}">
	                 <a href="/project/user/mypage.do/com?page=${paging.grpEndPage + 1}">다음</a>
	             </c:if>
         	</c:when>
         	
         	<c:when test="${url.contains('point')}">
	         	<c:if test="${paging.currentGrp > 1}">
	                 <a href="/project/user/mypage.do/point?page=${paging.grpStartPage - 1}">이전</a>
	             </c:if>
	             <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
	                 <a class="paging_i" href="/project/user/mypage.do/point?page=${i}">${i}</a>
	             </c:forEach>
	             <c:if test="${paging.grpEndPage <  paging.totalPage}">
	                 <a href="/project/user/mypage.do/point?page=${paging.grpEndPage + 1}">다음</a>
	             </c:if>
         	</c:when>
         	
         	
         	<c:otherwise>
        		<c:if test="${paging.currentGrp > 1}">
	                 <a href="/project/user/mypage.do?page=${paging.grpStartPage - 1}">이전</a>
	             </c:if>
	             <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
	                 <a class="paging_i" href="/project/user/mypage.do?page=${i}">${i}</a>
	             </c:forEach>
	             <c:if test="${paging.grpEndPage <  paging.totalPage}">
	                 <a href="/project/user/mypage.do?page=${paging.grpEndPage + 1}">다음</a>
	             </c:if>
         	</c:otherwise>
         
         </c:choose>
         
          
          
         </div>
    </div>
</div>
	<!-- 모달 -->
      <div class="modal" id="modal">
         <div class="modal-content">
            <div class="tab-container">
               <div id="tab1" class="tab selected">충전 포인트 선택</div>
               <div id="tab2" class="tab">결제 수단 선택</div>
            </div>
            <form id="form1" class="form">
               <div class="point-selection">
                  <!-- <label for="points" class="points-label">보유 포인트: ${user.userPoint}p</label> -->
                  <div class="points">
                     <input type="radio" id="1000p" name="pointAmount" value="1000">
                     <label for="1000p" class="point-btn">1000p</label> 
                     <input type="radio" id="5000p" name="pointAmount" value="5000">
                     <label for="5000p" class="point-btn">5000p</label> 
                     <input type="radio" id="10000p" name="pointAmount" value="10000">
                     <label for="10000p" class="point-btn">10,000p</label> 
                     <input type="radio" id="50000p" name="pointAmount" value="50000">
                     <label for="50000p" class="point-btn">50,000p</label> 
                     <input type="radio" id="100000p" name="pointAmount" value="100000">
                     <label for="100000p" class="point-btn">100,000p</label> 
                     <input type="radio" id="300000p" name="pointAmount" value="300000">
                     <label for="300000p" class="point-btn">300,000p</label>
                  </div>
                  <div class="buttons">
                     <button type="button" class="btn" id="cancel">취소</button>
                     <button type="button" class="btn" id="next">다음</button>
                  </div>
               </div>
            </form>

            <form id="form2" class="form" style="display: none;">
               <div class="payment-method">
                  <label for="payment">결제 수단을 선택하세요:</label>
                  <div class="payment-options2">
                     <div class="payment-row">
                        <input type="radio" id="credit" name="payment" value="credit">
                        <label for="credit" class="payment-btn"> <img
                           src="${pageContext.request.contextPath}/resources/img/credit-card.png"
                           alt="Credit Card"> 신용카드 결제
                        </label> <input type="radio" id="bank" name="payment" value="bank">
                        <label for="bank" class="payment-btn"> <img
                           src="${pageContext.request.contextPath}/resources/img/bank-transfer.png"
                           alt="Bank Transfer"> 무통장입금
                        </label>
                     </div>
                     <div class="payment-row">
                        <input type="radio" id="phone" name="payment" value="phone">
                        <label for="phone" class="payment-btn"> <img
                           src="${pageContext.request.contextPath}/resources/img/mobile-payment.png"
                           alt="Mobile Payment"> 휴대폰 결제
                        </label> <input type="radio" id="simple" name="payment" value="simple">
                        <label for="simple" class="payment-btn"> <img
                           src="${pageContext.request.contextPath}/resources/img/simple-payment.png"
                           alt="Simple Payment"> 간편결제
                        </label>
                     </div>
                  </div>
                  <div class="buttons">
                     <button type="button" class="btn" id="cancel">취소</button>
                     <button type="button" class="btn" id="back">이전</button>
                     <button type="button" class="btn" id="chargeBtn">완료</button>
                  </div>
               </div>

            </form>
         </div>
      </div>


      <!-- 모달2 -->
      <div class="modal" id="modal2">
         <div class="modal-content">
            <div class="tab-container">
               <div id="tab3" class="tab2 selected2">환전 포인트 선택</div>
               <div id="tab4" class="tab2">은행 송금 연결</div>
            </div>
            <form id="form3" class="form">
               <div class="point-selection">
                  <!-- <label for="points" class="points-label">보유 포인트: ${user.userPoint}p</label> -->
                  <div class="points">
                     <input type="radio" id="5000p2" name="extend" value="5000">
                     <label for="5000p2" class="point-btn">5000p</label> 
                     <input type="radio" id="10000p2" name="extend" value="10000">
                     <label for="10000p2" class="point-btn">10,000p</label> 
                     <input type="radio" id="20000p2" name="extend" value="20000">
                     <label for="20000p2" class="point-btn">20,000p</label> 
                     <input
                        type="radio" id="30000p2" name="extend" value="30000">
                     <label for="30000p2" class="point-btn">30,000p</label> 
                     <input type="radio" id="50000p2" name="extend" value="50000">
                     <label for="50000p2" class="point-btn">50,000p</label> 
                     <input type="radio" id="100000p2" name="extend" value="100000">
                     <label for="100000p2" class="point-btn">100,000p</label>
                  </div>
                  <div class="buttons">
                     <button type="button" class="btn2" id="cancel2">취소</button>
                     <button type="button" class="btn2" id="next2">다음</button>
                  </div>
               </div>
            </form>

            <form id="form4" class="form" >
               <div class="payment-method">

                  <div class="payment-options">

                     <div class="payment-row">

                        <select id="bank-name" name="bank-name" required>
                           <option value="은행 선택">은행 선택</option>
                           <option value="케이뱅크">케이뱅크</option>
                           <option value="카카오뱅크">카카오뱅크</option>
                           <option value="kb국민은행">KB국민은행</option>
                           <option value="우리은행">우리은행</option>
                           <option value="신한은행">신한은행</option>
                           <option value="농협">농협</option>
                           <option value="하나은행">하나은행</option>
                           <option value="기업은행">기업은행</option>
                        </select>
                        
                        <input type="number" oninput='handleOnInput(this, 15)' id="account-number" placeholder="계좌번호 입력" name="account-number" pattern="\d*"  maxlength="10" required>

                     </div>
                     <br> 
                     <label for="account-holder">예금주명: </label> 
                     <input type="text" id="account-holder" name="account-holder" maxlength="5" required>
                  </div>
                  
                  <div class="buttons">
                     <button type="button" class="btn2" id="cancel2">취소</button>
                     <button type="button" class="btn2" id="back2">이전</button>
                     <button type="button" class="btn2" id="chargeBtn2">완료</button>
                  </div>
               </div>

            </form>
        
            </div>
        </div>
   <%@ include file="../footer-sub.jsp" %>
</div>
</body>
</html>
