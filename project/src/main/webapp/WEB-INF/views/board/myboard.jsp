<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../nav.jsp" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	
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
                            <% int index =0; %>
                           <c:forEach var="myboard" items="${BoardList}">
                           		<%index++; %>
								<tr>
									<td><%=index%></td>
                                	<td class="td22"><a href="/project/board/my/reg${myboard.boardCode}">${myboard.boardTitle}</a></td>
                               		<td>${myboard.nickname}</td>
                                	<td>${myboard.boardWritedate}</td>
                                	<td>${myboard.boardViews}</td>
								<tr>
						</c:forEach>
                            
                        </tbody>
                    </table>
                </div>
</body>
</html>