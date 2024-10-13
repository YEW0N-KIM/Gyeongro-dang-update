<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find User Info</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/findInfo.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
window.onload = function() {
    var urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('type') === 'pw') {
        showPwSearch();
    } else {
        showIdSearch();
    }
};

function showIdSearch() {
    document.getElementById("form1").style.display = "block";
    document.getElementById("form2").style.display = "none";
    document.getElementById("IdBtn").style.borderBottom = "none";
    document.getElementById("PwBtn").style.borderBottom = "";
}

function showPwSearch() {
    document.getElementById("form1").style.display = "none";
    document.getElementById("form2").style.display = "block";
    document.getElementById("PwBtn").style.borderBottom = "none";
    document.getElementById("IdBtn").style.borderBottom = "";
}

function cancel() {
    window.location.href = "${pageContext.request.contextPath}/";
}

$(document).ready(function() {
    $("#userTel").on('input', function() {
            var userTel = $(this).val().replace(/[^0-9]/g, '');
            var formattedTel = formatPhoneNumber(userTel);
            $(this).val(formattedTel);

            if (userTel.length >= 11) {
               $("#registerBtn").prop("disabled", false);
            } else {
                $("#registerBtn").prop("disabled", true);
            }
        });

        function formatPhoneNumber(phoneNumber) {
            var formatted = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            return formatted;
        }
        
        
        $("#PwUserTel").on('input', function() {
            var PwUserTel = $(this).val().replace(/[^0-9]/g, '');
            var formattedTel = formatPhoneNumber(PwUserTel);
            $(this).val(formattedTel);

            if (PwUserTel.length >= 11) {
               $("#registerBtn").prop("disabled", false);
            } else {
                $("#registerBtn").prop("disabled", true);
            }
        });

        function formatPhoneNumber(phoneNumber) {
            var formatted = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            return formatted;
        }    
});
</script>
</head>
<body>
<div class="wrap">
   <%@ include file="../nav.jsp" %>
   <div class="container">
       <div class="btnContainer">
           <button id="IdBtn" onclick="showIdSearch()">아이디 찾기</button>
           <button id="PwBtn" onclick="showPwSearch()">비밀번호 찾기</button>
       </div>
       <div class="form-container" id="form1" style="display:none;">
           <div class="form-title">
               <h1>아이디 찾기</h1>
           </div>
           <form id="idSearch" action="/project/user/findMyId.do" method="post">
               <table class="input-table">
                   <tr class="from-table-tr1">
                       <td class="form-label">이름</td>
                       <td class="form-input"><input id="userName" class="input-box" name="userName"></td>
                   </tr>
                   <tr class="from-table-tr2">
                       <td class="form-label">전화번호</td>
                       <td class="form-input"><input id="userTel" class="input-box" name="userTel"></td>
                   </tr>
               </table>
               <c:if test="${not empty IdErrorMessage}">
                   <p style="color: red; margin-left: 90px;">${IdErrorMessage}</p>
               </c:if>
               <div class="btn-container">
                   <button type="submit" id="submitBtn" class="btn-register">확인</button>
                   <input type="button" class="btn-cancel" onclick="cancel()" value="취소">
               </div>
           </form>
       </div>
   
       <div class="form-container" id="form2" style="display:none;">
           <div class="form-title">
               <h1>비밀번호 찾기</h1>
           </div>
           <form id="pwSearch" action="/project/user/findMyPw.do" method="post">
               <table class="input-table">
                   <tr class="from-table-tr1">
                       <td class="form-label">이름</td>
                       <td class="form-input"><input id="PwUserName" class="input-box" name="userName"></td>
                   </tr>
                   <tr class="from-table-tr1">
                       <td class="form-label">아이디</td>
                       <td class="form-input"><input class="input-box" id="PwUserId" name="userId"></td>
                   </tr>
                   <tr class="from-table-tr2">
                       <td class="form-label">전화번호</td>
                       <td class="form-input"><input id="PwUserTel" class="input-box" name="userTel"></td>
                   </tr>
               </table>
               <c:if test="${not empty PwErrorMessage}">
                   <p style="color: red; margin-left: 90px;">${PwErrorMessage}</p>
               </c:if>
               <div class="btn-container">
                   <button type="submit" id="submitBtn" class="btn-register">확인</button>
                   <input type="button" class="btn-cancel" onclick="cancel()" value="취소">
               </div>
           </form>
       </div>
   </div>
   
   
   <%@ include file="../footer-sub.jsp" %>
</div>
</body>
</html>