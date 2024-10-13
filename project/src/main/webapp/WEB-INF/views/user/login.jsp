<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

	
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>로그인 및 회원가입</title>
    
    <link rel="stylesheet" type="text/css" href="${path}/resources/css/login.css">
   <!--  <script src="https://apis.google.com/js/platform.js" async defer></script>
     <script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script> -->
     <meta name="google-signin-client_id" content="188576259803-g78m0a221jlk19jg8f91akb3eu0ehh0b.apps.googleusercontent.com">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
        	//아이디 기억
        	if (localStorage.getItem('rememberedUserId')) {
                $("#loginUserId").val(localStorage.getItem('rememberedUserId'));
                $("#rememberMe").prop('checked', true);
            }
        	//자동
        	/*
        	  if (localStorage.getItem('autoLogin') === 'true') {
        	        $("#loginUserId").val(localStorage.getItem('rememberedUserId'));
        	        $("#loginUserPw").val(localStorage.getItem('rememberedUserPw'));
        	        $("#autoLogin").prop('checked', true);
        	        submitLoginForm();  // 자동 로그인 
        	    }
        	*/
            $("#btnLogin").click(function(){
                var userId = $("#loginUserId").val();
                var userPw = $("#loginUserPw").val(); 
                if(userId == ""){
                    $("#loginUserId").focus();
                    return;
                }
                if(userPw == ""){ 
                    $("#loginUserPw").focus();
                    return;
                }
            
            
         // 아이디 기억하기
            if ($("#rememberMe").is(':checked')) {
                localStorage.setItem('rememberedUserId', userId);
            } else {
                localStorage.removeItem('rememberedUserId');
            }
         /*
            // "자동 로그인" 체크박스 상태에 따라 아이디와 비밀번호 저장 또는 삭제
            if ($("#autoLogin").is(':checked')) {
                localStorage.setItem('autoLogin', 'true');
                localStorage.setItem('rememberedUserPw', userPw); 
            } else {
                localStorage.removeItem('autoLogin');
                localStorage.removeItem('rememberedUserPw');
            } */

            document.loginForm.action = "${path}/user/login_check.do";
            document.loginForm.submit();
        });

            $("#registerUserId").on('input', function() {
                var userId = $(this).val();
                $.ajax({
                    type: "POST",
                    url: "${path}/user/check_id.do",
                    data: {userId: userId},
                    success: function(data) {
                        if (data == "duplicate") {
                            $("#idCheckMsg").text("이미 사용 중인 아이디입니다.");
                            $("input:not(#registerUserId), button").on('mousedown click keydown focusin', function(e) {
                                e.preventDefault();
                                return false;
                            });
                        } else {
                            $("#idCheckMsg").text("");
                            $('input:not(#registerUserId), button').off('mousedown click keydown focusin');
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
                        url: "${path}/user/check_userTel.do",
                        data: {userTel: formattedTel},
                        success: function(data) {
                            if (data == "duplicate") {
                                $("#userTelCheckMsg").text("이미 가입된 전화번호입니다.");
                                $("#registerBtn").prop("disabled", true);
                                $("input:not(#userTel), button").on('mousedown click keydown focusin', function(e) {
                                    e.preventDefault();
                                    return false;
                                });
                            } else {
                                $("#userTelCheckMsg").text("");
                                $("#registerBtn").prop("disabled", false);
                                $('input:not(#userTel), button').off('mousedown click keydown focusin');
                            }
                        }
                    });
                } else {
                    $("#userTelCheckMsg").text("전화번호는 최소 11자리 이상 입력해야 합니다.");
                    $("#registerBtn").prop("disabled", true);
                    $("input:not(#userTel), button").on('mousedown click keydown focusin', function(e) {
                        e.preventDefault();
                        return false;
                    });
                }
            });

            
            
            
            
            
            
            
            $("#registerUserPw").on('input', function() {
                var userPw = $(this).val();
                var regex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$/;
                
                if (!regex.test(userPw)) {
                    $("#pwError").text("비밀번호는 영문자와 숫자의 조합으로 최소 8자리 이상이어야 합니다.");
                    $("#btnLogin").removeClass("valid-password");
                    $("#registerBtn").prop("disabled", true);
                    $("input:not(#registerUserPw), button").on('mousedown click keydown focusin', function(e) {
                        e.preventDefault();
                        return false;
                    });
                } else {
                    $("#pwError").text("");
                    $("#registerBtn").prop("disabled", false);
                    $('input:not(#registerUserPw), button').off('mousedown click keydown focusin');
                    $("#btnLogin").addClass("valid-password");
                }
            });

            function formatPhoneNumber(phoneNumber) {
                return phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            }

            $('#sign-in-form').keypress(function(event){
                var keycode = (event.keyCode ? event.keyCode : event.which);
                if(keycode == '13'){
                    submitLoginForm();
                }
            });

            function submitLoginForm() {
                var userId = $("#loginUserId").val();
                var userPw = $("#loginUserPw").val(); 
                
                if(userId == ""){
                    $("#loginUserId").focus();
                    return false;
                }
                if(userPw == ""){ 
                    $("#loginUserPw").focus();
                    return false;
                }

                document.loginForm.action = "${path}/user/login_check.do";
                document.loginForm.submit();

                return false;
            }
        });
        
        $("#loginUserId").on('input', function() {
            alert("아이디를 입력 중입니다.");
        });
    </script>
</head>
<body>
<div class="wrap">

	<%@ include file="../nav.jsp" %>
	
    <div class="container">
        <div class="overlay" id="overlay">
            <div class="sign-in" id="sign-in">
                <h1>안녕하세요</h1>
                <p>우리와 함께하려면 당신의 개인정보를 입력해주세요</p>
                <button class="switch-button" id="slide-right-button">로그인</button>
            </div>
            <div class="sign-up" id="sign-up">
                <h1>Hello, Friend!</h1>
                <p>Enter your personal details and start a journey with us</p>
                <button class="switch-button" id="slide-left-button">회원가입</button>
            </div>
        </div>
        <div class="form">
        
            <div class="sign-in" id="sign-in-info">
                <h1>로그인</h1>
               <!--  <div class="social-media-buttons">
                    <div class="icon">
                        <div class="g-signin2" data-onsuccess="onSignIn"></div>
                    </div>
                    <div class="icon">
                         <div>
					         카카오 로그인 버튼
					          <button id="kakao-login-btn" class="kakao-login-btn">카카오로 로그인</button>
					     </div>
                    </div>
                    <div class="icon">
                        <svg viewBox="0 0 24 24">
                            <path fill="#000000" d="M21,21H17V14.25C17,13.19 15.81,12.31 14.75,12.31C13.69,12.31 13,13.19 13,14.25V21H9V9H13V11C13.66,9.93 15.36,9.24 16.5,9.24C19,9.24 21,11.28 21,13.75V21M7,21H3V9H7V21M5,3A2,2 0 0,1 7,5A2,2 0 0,1 5,7A2,2 0 0,1 3,5A2,2 0 0,1 5,3Z" />
                        </svg>
                    </div>
                </div> -->
                <p class="small">혹은 당신의 정보로 로그인하세요:</p>
                <form id="sign-in-form" name="loginForm" method="post" onsubmit="return submitLoginForm();">
                    <input type="text" id="loginUserId" name="userId" placeholder="ID" required/><br>
                    <input type="password" id="loginUserPw" name="userPw" placeholder="Password" required/><br>
                     <input type="checkbox" id="rememberMe" style="width:15px;"><span style="margin-right:150px;">아이디 저장</span><br>
                    <c:if test="${message == 'error'}">
                        <div style="color:red;">아이디 또는 비밀번호가 일치하지 않습니다.</div>
                    </c:if>
                    <a href="/project/user/findMyInfo.do"><p class="forgot-password">아이디/비밀번호를 잊으셨나요?</p></a>
                    <button type="submit" id="btnLogin" class="control-button in">Sign In</button>
                </form>
            </div>

            <div class="sign-up" id="sign-up-info">
                <h1>Create Account</h1>
                <div class="social-media-buttons">
                    <div class="icon">
                        <svg viewBox="0 0 24 24">
                            <path fill="#000000" d="M17,2V2H17V6H15C14.31,6 14,6.81 14,7.5V10H14L17,10V14H14V22H10V14H7V10H10V6A4,4 0 0,1 14,2H17Z" />
                        </svg>
                    </div>
                    <div class="icon">
                        <svg viewBox="0 0 24 24">
                            <path fill="#000000" d="M23,11H21V9H19V11H17V13H19V15H21V13H23M8,11V13.4H12C11.8,14.4 10.8,16.4 8,16.4C5.6,16.4 3.7,14.4 3.7,12C3.7,9.6 5.6,7.6 8,7.6C9.4,7.6 10.3,8.2 10.8,8.7L12.7,6.9C11.5,5.7 9.9,5 8,5C4.1,5 1,8.1 1,12C1,15.9 4.1,19 8,19C12,19 14.7,16.2 14.7,12.2C14.7,11.7 14.7,11.4 14.6,11H8Z" />
                        </svg>
                    </div>
                    <div class="icon">
                        <svg viewBox="0 0 24 24">
                            <path fill="#000000" d="M21,21H17V14.25C17,13.19 15.81,12.31 14.75,12.31C13.69,12.31 13,13.19 13,14.25V21H9V9H13V11C13.66,9.93 15.36,9.24 16.5,9.24C19,9.24 21,11.28 21,13.75V21M7,21H3V9H7V21M5,3A2,2 0 0,1 7,5A2,2 0 0,1 5,7A2,2 0 0,1 3,5A2,2 0 0,1 5,3Z" />
                        </svg>
                    </div>
                </div>
                <p class="small">or use your info for registration:</p>
                <form id="sign-up-form" action="${path}/user/register_process.do" method="post">
                    <input type="text" name="userName" placeholder="Name" required /><br>
                    <input type="text" name="userId" id="registerUserId" placeholder="ID" required /><br><span id="idCheckMsg" style="color:red;"></span>
                    <input type="password" placeholder="Password" name="userPw" id="registerUserPw" required /><br><span id="pwError" style="color:red;"></span>
                    <input type="text" name="userTel" id="userTel" placeholder="tel" required /><br><span id="userTelCheckMsg" style="color:red;"></span>
                    <button type="submit" class="control-button up" id="registerBtn">Sign Up</button>
                </form>
            </div>
        </div>
    </div>
    <%@ include file="../footer-sub.jsp" %>
</div> <!-- wrap 끝 -->
    <script src="${path}/resources/js/login.js"></script>
    <script>
  function onSignIn(googleUser) {
    // Google 계정 정보를 가져옵니다
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId());
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail());

    // ID 토큰을 가져와서 서버로 전송할 수 있습니다
    var id_token = googleUser.getAuthResponse().id_token;
    console.log('ID Token: ' + id_token);
    
    // 예제 AJAX 요청 (서버로 ID 토큰 전송)
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'YOUR_SERVER_URL');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({ token: id_token }));
  }
</script>
    <script>
        // 카카오 SDK 초기화
        Kakao.init('768dd99ba393dd6e3c3d582338924bce'); // 실제 JavaScript 키로 교체하세요

        // 카카오 로그인 버튼에 이벤트 리스너 추가
        document.getElementById('kakao-login-btn').addEventListener('click', function() {
            Kakao.Auth.login({
                success: function(authObj) {
                    console.log(authObj);
                    Kakao.API.request({
                        url: '/v2/user/me',
                        success: function(res) {
                            console.log(res);
                            // 사용자 정보 처리 및 로그인 로직 추가
                        },
                        fail: function(error) {
                            console.error(error);
                        }
                    });
                },
                fail: function(err) {
                    console.error(err);
                }
            });
        });
    </script>
</body>
</html>