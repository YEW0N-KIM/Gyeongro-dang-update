<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/foundPw.css" >
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    $("#new_password").on('input', function() {
        var userPw = $(this).val();
        
        var regex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$/;
        
        if (!regex.test(userPw)) {
            $("#pwError").text("비밀번호는 영문자와 숫자의 조합으로 최소 8자리 이상이어야 합니다.");
            $("#submitBtn").prop("disabled", true);
            $("input:not(#new_password), button").on('mousedown click keydown focusin', function(e) {
                e.preventDefault();
                return false;
            });
        } else {
            $("#pwError").text("");
            $("#submitBtn").prop("disabled", false);
            $('input:not(#new_password), button').off('mousedown click keydown focusin');
        }
    });

    $("#confirm_password").on('input', function() {
        var newPassword = $("#new_password").val();
        var confirmPassword = $(this).val();
        var submitBtn = $("#submitBtn");
        var message = $("#message");

        if (newPassword !== confirmPassword) {
            submitBtn.prop("disabled", true);
            message.text("비밀번호가 일치하지 않습니다.");
        } else {
            submitBtn.prop("disabled", false);
            message.text("");
        }
    });
});

</script>
</head>
<body>
<div class="wrap">
	
   	<%@ include file="../nav.jsp" %>
	<div class="container">
		<div class="form-title">
			<h1>비밀번호 변경</h1>
		</div>
	    <form  action="/project/user/updateMyPw.do" method="post">
	    <input type="hidden" id="userId" name="userId" value="${userId}">
	    	<table class="input-table">
	    		<tr class="form-table-tr1">
	    			<td class="form-label">새 비밀번호 : </td>
		        	<td class="form-input"><input type="password" class="input-box" id="new_password" name="newPw" required></td>
		        	
		        </tr>
		        <tr class="form-table-tr1">
		        	<td class="form-label">비밀번호 확인 : </td>
		        	<td class="form-input"><input type="password" class="input-box" id="confirm_password" name="confirmPw" oninput="checkPassword()" required></td>
		        	
		        </tr>
		        <tr>
		        	<td colspan="2" class="span-td"><span id="pwError" style="color:red;"></span>
		        	<span id="message" style="color: red;"></span>
		        	</td>
		        </tr>
	        </table>
	        
	        <div class="btnContainer">
	        	<button type="submit" class="btn-register" id="submitBtn">비밀번호 변경</button>
	       	</div>
	    </form>
	</div>
	<%@ include file="../footer-sub.jsp" %>
</div>
</body>
</html>
