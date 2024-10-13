<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
    
    <!-- reset.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
    
    <!-- freeboard.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/freeboard.css">
    
    <!-- style -->
    <style>
       
    </style>
    
</head>
<body>
    <div class="wrap">
    
        <!-- nav -->
        <%@ include file="../nav.jsp" %>
        
        <!-- container -->
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
            
            <!-- 본문내용 -->
         <div class="notice">
                <h2 id="changeTitle">0</h2>
                <a class="write-link" href="/project/board/free/reg">글쓰기</a>
                <div class="notice_list">
                    <table>
                        <thead>
                            <tr>
                                <td class="td1">번호</td>
                                <td class="td2">글제목</td>
                                <td class="td3">작성자</td>
                                <td class="td4">작성시간</td>
                                <td class="td5">조회수</td>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- DB 연동해서 불러오기 -->
                            <c:forEach var="list" items="${freeBoardList}" varStatus="status">
                                <tr>
                                    <td>${(paging.totRecords - (status.index + 1) - ((paging.currentPage - 1) * paging.pageSize))+1}</td>
                                    <td class="td-title" title="${list.boardTitle}"><a href="/project/board/free/${list.boardCode}">${list.boardTitle}</a></td>
                                    <td>${list.nickname}</td>
                                    <td>${list.boardWritedate}</td>
                                    <td>${list.boardViews}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                
				<div class="page_number"> <!-- page_number -->
					<div class="paging"> <!-- paging -->
				    	<c:if test="${ empty search.condition}">
				        	<c:if test="${paging.currentGrp > 1}">
				            	<a href="/project/board/free?type=${type}&page=${paging.grpStartPage - 1}">이전</a>
							</c:if>
				            <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
				            	<a class="paging_i" href="/project/board/free?type=${type}&page=${i}">${i}</a>
				            </c:forEach>
				            <c:if test="${paging.grpEndPage <  paging.totalPage}">
				            	<a href="/project/board/free?type=${type}&page=${paging.grpEndPage + 1}">다음</a>
							</c:if>
						</c:if>
				        
				        <c:if test="${ not empty search.condition}">
				        	<c:if test="${paging.currentGrp > 1}">
				            	<a href="/project/board/free/search?page=${paging.grpStartPage - 1}" >이전</a>
				            </c:if>
				            <c:forEach var="i" begin="${paging.grpStartPage}" end="${paging.grpEndPage}">
				            	<a class="paging_i" href="/project/board/free/search?condition=${search.condition}&keyword=${search.keyword}&start=${search.start}&page=${i}">${i}</a>
							</c:forEach>
				            <c:if test="${paging.grpEndPage <  paging.totalPage}">
				            	<a href="/project/board/free/search?page=${paging.grpEndPage + 1}">다음</a>
							</c:if>
						</c:if> 
					</div>
				</div>

                <div class="search_form">
                    <form action="/project/board/free/search" method="get" class="search-form-inner" onsubmit="return searchCheck()">
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
        </div> <!-- container 끝 -->
        
      <!-- footer -->
      <%@ include file="../footer-sub.jsp" %>
    </div>
    
    <!-- script -->
    <script>
    document.addEventListener("DOMContentLoaded", function() {
        typeTitle();
        
        // 현재 URL 가져오기
        var currentURL = window.location.href;

        // 페이지 번호 클릭 시 bold 스타일 적용
        var pagingLinks = document.querySelectorAll(".paging_i");
        pagingLinks.forEach(function(link) {
            link.addEventListener("click", function() {
                pagingLinks.forEach(function(link) {
                    link.classList.remove("active");
                });
                this.classList.add("active");
            });
        });

        // 현재 URL에서 페이지 번호를 추출하여 해당 링크에 active 클래스 추가
        var pageParts = currentURL.split("page=");
        if (pageParts.length > 1) {
            var currentPage = pageParts[1].split("&")[0];
            pagingLinks.forEach(function(link) {
                if (link.href.includes("page=" + currentPage)) {
                    link.classList.add("active");
                }
            });
        }
    });

    function searchCheck() {
        var condition = document.getElementsByName("condition")[0].value;
        if (condition === "") {
            alert("검색 조건을 선택해주세요.");
            return false; // 폼 전송을 중단합니다.
        }
        return true; // 폼을 제출합니다.
    }
    
    function typeTitle() {
        var currentURL = window.location.href;
        var urlParts = currentURL.split("type=");
        var lastPart = urlParts[urlParts.length - 1]; // type= 뒤의 값만 가져오기
        var lastValue = lastPart.split("&")[0]; // '&' 문자 이후의 값은 무시

        var header = document.querySelector("h2");

        // 헤더 텍스트 설정
        if (lastValue === "-1") {
            header.innerText = "전체글";
        } else if (lastValue === "1") {
            header.innerText = "여행후기";
        } else if (lastValue === "2") {
            header.innerText = "꿀팁공유";
        } else if (lastValue === "3") {
            header.innerText = "질문있어요!";
        } else if (lastValue === "4") {
            header.innerText = "수다방";
        } else if (lastValue === "5") {
            header.innerText = "동행구해요";
        } else {
            header.innerText = "전체글";
        }
    }
    
    document.addEventListener('DOMContentLoaded', function() {
        const menuItems = document.querySelectorAll('.side_menu_set ul li');

        menuItems.forEach(item => {
            item.addEventListener('click', function(event) {
                event.preventDefault();
                menuItems.forEach(item => item.classList.remove('active'));
                this.classList.add('active');
                window.location.href = this.querySelector('a').href;
            });
        });

        const currentUrl = window.location.href;
        menuItems.forEach(item => {
            if (item.querySelector('a').href === currentUrl) {
                item.classList.add('active');
            }
        });
    });
    </script>
</body>
</html>
