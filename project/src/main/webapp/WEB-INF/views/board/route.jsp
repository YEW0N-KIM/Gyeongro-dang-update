<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>경로게시판</title>
    
    <!-- reset.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
    
    <!-- routelist.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/routelist.css" >
    
<!-- style -->
<style>

/** 검색바 **/
.search_form {width: 600px; text-align: center; padding: 20px; margin: 0 auto;}
.search-form-inner {display: flex;}
.search_form select {width: 100px; height: 30px; border-radius: 5px; margin-right: 5px;}
.search_form input {width: 400px; height: 30px; text-indent: 10px; outline: none; border-radius: 5px; margin-right: 5px; border: 1px solid black;}
.search_form button {width: 50px; height: 30px; border-radius: 5px; border: 1px solid black;}

/* 페이징 */
.page_number {padding: 30px; text-align: center;}
.paging {width: 400px; align-items: center; margin: 0 auto;}
.paging a {width: 40px; height: 30px; line-height: 30px; display: inline-block; border-radius: 5px; color: rgb(53, 53, 53);}
.paging a:hover {text-decoration: none; color: #d1d1d1;}
.paging_i{background-color: #ffffff;}
.paging_i.active {font-weight: bold; color: black;}
span.active {color: orangered; font-weight: bold;}

.filter-reset-btn a {
   text-decoration: none;
   color: #5c5c5c;
}
</style>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
    <div class="wrap">
    
        <!-- nav -->
        <%@ include file="../nav.jsp" %>
        
        <!-- container -->
        <div class="container">
           <!-- 사이드 네비 -->
            <div class="side-container">
                <div class="side">

                    <div class="side-name">지역</div>
                    <div class="local-box">
                       <div class="local" id="local0">서울</div>
                        <div class="local" id="local1">인천</div>
                        <div class="local" id="local2">대전</div>
                        <div class="local" id="local3">대구</div>
                        <div class="local" id="local4">경기</div>
                        <div class="local" id="local5">부산</div>
                        <div class="local" id="local6">울산</div>
                        <div class="local" id="local7">광주</div>
                        <div class="local" id="local8">강원</div>
                        <div class="local" id="local9">충북</div>
                        <div class="local" id="local10">충남</div>
                        <div class="local" id="local11">경북</div>
                        <div class="local" id="local12">경남</div>
                        <div class="local" id="local13">전북</div>
                        <div class="local" id="local14">전남</div>
                        <div class="local" id="local15">제주</div>
                        <div class="local" id="local16">세종</div>
                    </div>

                    <div class="side-name">테마</div>
                    <div class="theme-box">
                        <div class="theme" id="theme1">나홀로 힐링</div>
                        <div class="theme" id="theme2">연인과 함께</div>
                        <div class="theme" id="theme3">친목다지기</div>
                        <div class="theme" id="theme4">가족 나들이</div>
                        <div class="theme" id="theme5">모임 여행</div>
                    </div>
                    
                    <div class="side-name">기간</div>
                    <div class="tourdays-box">
                        <div class="tourdays" id="tourdays1">당일</div>
                        <div class="tourdays" id="tourdays2">2~4일</div>
                        <div class="tourdays" id="tourdays3">5~9일</div>
                        <div class="tourdays" id="tourdays4">10~19일</div>
                        <div class="tourdays" id="tourdays5">20일 이상</div>
                    </div>

                    <div class="filter-btn-box">
                    <div class="filter-reset-btn" id="searchBtn">
                        검색
                    </div>
                    <div class="filter-reset-btn" id="resetBtn">
                        초기화
                    </div>
                        
                        <!-- 보류 -->
                    </div>
                    
                </div>
            </div>
      
<!-- 루트 목록 -->
<div class="section">
   <div class="section-name">경로 게시판<hr></div>
    <a class="write-link" href="/project/board/route/create">글쓰기</a>
    
    <table class="route-table">
       <c:forEach var="list" items="${routeBoardList}" varStatus="status">
         <tr class="route-item" onclick="location.href='${pageContext.request.contextPath}/board/route/${list.boardCode}'">
            <td class="route-info">
               <div class="route-img">
                   <c:choose>
	                    <c:when test="${not empty list.boardImgReal}">
	                        <img class="boardImg" src="<c:url value='/board/images/${list.boardImgReal}' />" alt="Board Image">
	                    </c:when>
	                    <c:otherwise>
	                        <img class="boardImg" src="${pageContext.request.contextPath}/resources/img/blankimg.png" alt="Default Image">
	                    </c:otherwise>
	                </c:choose>
                   
                   <div class="route-point">
                      <span class="route-price">
                          <c:choose>
                              <c:when test="${list.boardPoint != 0}">
                                 <div>
                                     price ${list.boardPoint}
                                  </div>
                              </c:when>
                              <c:otherwise>
                                  <div style="display: none;">
                                   price 0
                               	</div>
                              </c:otherwise>
                          </c:choose>
                      </span>
                  </div>
               </div>
                    <div class="info-box">
                    
                       <!-- info-01 -->
                       <div class="info-01">
                          <div class="left-content">
                             <div class="local-icon">
                                <c:choose>
		                              <c:when test="${list.boardRegion == 0}">서울</c:when>
		                              <c:when test="${list.boardRegion == 1}">인천</c:when>
		                              <c:when test="${list.boardRegion == 2}">대전</c:when>
		                              <c:when test="${list.boardRegion == 3}">대구</c:when>
		                              <c:when test="${list.boardRegion == 4}">경기</c:when>
		                              <c:when test="${list.boardRegion == 5}">부산</c:when>
		                              <c:when test="${list.boardRegion == 6}">울산</c:when>
		                              <c:when test="${list.boardRegion == 7}">광주</c:when>
		                              <c:when test="${list.boardRegion == 8}">강원</c:when>
		                              <c:when test="${list.boardRegion == 9}">충북</c:when>
		                              <c:when test="${list.boardRegion == 10}">충남</c:when>
		                              <c:when test="${list.boardRegion == 11}">경북</c:when>
		                              <c:when test="${list.boardRegion == 12}">경남</c:when>
		                              <c:when test="${list.boardRegion == 13}">전북</c:when>
		                              <c:when test="${list.boardRegion == 14}">전남</c:when>
		                              <c:when test="${list.boardRegion == 15}">제주</c:when>
		                              <c:when test="${list.boardRegion == 16}">세종</c:when>
                           	</c:choose>
                        </div>
                        <div class="route-name">
                           <span>${list.boardTitle}</span>
                        </div>
                     </div>
                     <div class="user-info">
                        <span>by </span>
                        <span class="user-name">${list.nickname}</span>
                     </div>
                  </div>
                  
                  <!-- info-02 -->
                  <div class="info-02">
                     <div class="route-tag">
                        <span>${list.boardTourdays}일 여행</span>
                        <span>
                           <c:choose>
                              <c:when test="${list.boardTheme == 1}">#나홀로 힐링</c:when>
                              <c:when test="${list.boardTheme == 2}">#연인과 데이트</c:when>
                              <c:when test="${list.boardTheme == 3}">#친구들과</c:when>
                              <c:when test="${list.boardTheme == 4}">#가족 나들이</c:when>
                              <c:when test="${list.boardTheme == 5}">#모임 단체 여행</c:when>
                           </c:choose>
                        </span>
                     </div>
                  </div>
                  <hr class="info-hr">
                  <!-- info-03 -->
                  <div class="info-03">
                     <div class="route-content">${list.boardContent}</div>
                  </div>
            
                  <!-- info-04 -->
                  <div class="info-04">
                     <div class="route-writeDate">${list.boardWritedate}</div>
                     <div class="views-icon">views <span class="views-int">${list.boardViews}</span></div>                  
                  </div>
               </div><!-- .info-box -->
            </td>
         </tr>
      </c:forEach>
   </table>
            
                  <div class="page_number"> <!-- page_number -->
                    <div class="paging">
					    <c:if test="${empty search.condition}">
					    	
					        <c:if test="${searchPaging.currentGrp > 1}">
					            <a href="/project/board/route?page=${searchPaging.grpStartPage - 1}&region=${param.region}&theme=${param.theme}&tourdays=${tourdays}">이전</a>
					        </c:if>
					        <c:forEach var="i" begin="${searchPaging.grpStartPage}" end="${searchPaging.grpEndPage}">
					            <a class="paging_i" href="/project/board/route?page=${i}&region=${param.region}&theme=${param.theme}&tourdays=${tourdays}">${i}</a>
					        </c:forEach>
					        <c:if test="${searchPaging.grpEndPage < searchPaging.totalPage}">
					            <a href="/project/board/route?page=${searchPaging.grpEndPage + 1}&region=${param.region}&theme=${param.theme}&tourdays=${tourdays}">다음</a>
					        </c:if>
					    </c:if>
					    <c:if test="${not empty search.condition}">
					        <c:if test="${paging.currentGrp > 1}">
					            <a href="/project/board/route/search?page=${paging.grpStartPage - 1}&condition=${search.condition}&keyword=${search.keyword}&start=${search.start}">이전</a>
					        </c:if>
					        <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
					            <a class="paging_i" href="/project/board/route/search?page=${i}&condition=${search.condition}&keyword=${search.keyword}&start=${search.start}">${i}</a>
					        </c:forEach>
					        <c:if test="${paging.grpEndPage < paging.totalPage}">
					            <a href="/project/board/route/search?page=${paging.grpEndPage + 1}&condition=${search.condition}&keyword=${search.keyword}&start=${search.start}">다음</a>
					        </c:if>
					    </c:if>
					</div>

                </div>
              <div class="search_form">
                    <form action="/project/board/route/search" method="get" class="search-form-inner" onsubmit="return searchCheck()">
                        <select name="condition">
                            <option value="">선택하시오</option>
                            <option value="writer">작성자</option>
                            <option value="title">제목</option>
                            <option value="content">내용</option>
                        </select>
                        <input type="text" name="keyword">
                        <input type="hidden" name="start" value="1">
                        <div><button>검색</button></div>
                    </form>
                </div>
           </div>
       </div>
       
       
       
       
       
       <!-- footer -->
      <%@ include file="../footer-sub.jsp" %>
   </div>
   
    <!-- script -->
    <script src="${pageContext.request.contextPath}/resources/js/route.js" defer></script>

</body>
</html>