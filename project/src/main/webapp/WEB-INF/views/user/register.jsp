<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
    $(document).ready(function() {
        $("#userId").on('input', function() {
            var userId = $(this).val();
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/user/check_id.do",
                data: {userId: userId},
                success: function(data) {
                    if (data == "duplicate") {
                        $("#idCheckMsg").text("이미 사용 중인 아이디입니다.");
                        $("#registerBtn").prop("disabled", true);
                    } else {
                        $("#idCheckMsg").text("");
                        $("#registerBtn").prop("disabled", false);
                    }
                }
            });
        });

        $("#userTel").on('input', function() {
            var userTel = $(this).val().replace(/[^0-9]/g, '');
            var formattedTel = formatPhoneNumber(userTel);
            $(this).val(formattedTel);

            if (userTel.length >= 11) {
                $.ajax({
                    type: "POST",
                    url: "${pageContext.request.contextPath}/user/check_userTel.do",
                    data: {userTel: formattedTel},
                    success: function(data) {
                        if (data == "duplicate") {
                            $("#userTelCheckMsg").text("이미 가입된 전화번호입니다.");
                            $("#registerBtn").prop("disabled", true);
                        } else {
                            $("#userTelCheckMsg").text("");
                            $("#registerBtn").prop("disabled", false);
                        }
                    }
                });
            } else {
                $("#userTelCheckMsg").text("전화번호는 최소 11자리 이상 입력해야 합니다.");
                $("#registerBtn").prop("disabled", true);
            }
        });
        
        $("#userPw").on('input', function() {
            var userPw = $(this).val();
            
            var regex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$/;
            
            if (!regex.test(userPw)) {
                $("#pwError").text("비밀번호는 영문자와 숫자의 조합으로 최소 8자리 이상이어야 합니다.");
                $("#registerBtn").prop("disabled", true);
            } else {
                $("#pwError").text("");
                $("#registerBtn").prop("disabled", false);
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
    <h2>회원가입</h2>
    <form action="${pageContext.request.contextPath}/user/register_process.do" method="post">
        <table>
            <tr>
                <td>ID</td>
                <td><input type="text" name="userId" id="userId" required><br><span id="idCheckMsg" style="color:red;"></span></td>
                
            </tr>
            <tr>
                <td>비밀번호</td>
                <td><input type="password" name="userPw" id="userPw" required><br><span id="pwError" style="color:red;"></span>* 영문자, 숫자 조합하여 만들어야 합니다.</td>
            </tr>
            <tr>
                <td>이름</td>
                <td><input type="text" name="userName" required></td>
            </tr>
            <tr>
                <td>전화번호</td>
                <td><input type="text" name="userTel" id="userTel" required><br><span id="userTelCheckMsg" style="color:red;"></span></td>
                
            </tr>
            <tr>
                <td colspan="2"><input type="submit" id="registerBtn" value="가입하기" disabled></td>
            </tr>
        </table>
    </form>
</body>
</html>
