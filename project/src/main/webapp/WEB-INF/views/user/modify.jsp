<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 정보 변경</title>
<link rel="stylesheet" type="text/css"
   href="${pageContext.request.contextPath}/resources/css/modify.css">
<link rel="stylesheet"
   href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"></script>
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
   $(document)
         .ready(
               function() {
                  $("#submitBtn").prop("disabled", false);

                  $("#nickname")
                        .on(
                              'input',
                              function() {
                                 var currentNickname = "${user.nickname}";
                                 var newNickname = $(this).val();

                                 if (currentNickname !== newNickname) {
                                    $
                                          .ajax({
                                             type : "POST",
                                             url : "${pageContext.request.contextPath}/user/check_nickname.do",
                                             data : {
                                                nickname : newNickname
                                             },
                                             success : function(
                                                   data) {
                                                if (data == "duplicate") {
                                                   $(
                                                         "#nicknameCheckMsg")
                                                         .text(
                                                               "이미 사용 중인 닉네임입니다.");
                                                   $(
                                                         "#submitBtn")
                                                         .prop(
                                                               "disabled",
                                                               true);
                                                   $(
                                                         "input:not(#nickname), button")
                                                         .on(
                                                               'mousedown click keydown focusin',
                                                               function(
                                                                     e) {
                                                                  e
                                                                        .preventDefault();
                                                                  return false;
                                                               });
                                                } else {
                                                   $(
                                                         "#nicknameCheckMsg")
                                                         .text(
                                                               "");
                                                   $(
                                                         "#submitBtn")
                                                         .prop(
                                                               "disabled",
                                                               false);
                                                   $(
                                                         'input:not(#nickname), button')
                                                         .off(
                                                               'mousedown click keydown focusin');
                                                }
                                             }
                                          });
                                 }
                              });

                  $("#userBirth")
                        .on(
                              'input',
                              function() {
                                 var userBirth = $(this).val()
                                       .replace(/[^0-9]/g, '');
                                 var formattedBirth = formatBirth(userBirth);
                                 $(this).val(formattedBirth);

                                 if (userBirth.length !== 8
                                       && userBirth !== '') {
                                    $("#userBirthCheckMsg").text(
                                          "올바른 날짜를 입력해주세요.");
                                    $("#submitBtn").prop(
                                          "disabled", true);
                                    $(
                                          "input:not(#userBirth), button")
                                          .on(
                                                'mousedown click keydown focusin',
                                                function(e) {
                                                   e
                                                         .preventDefault();
                                                   return false;
                                                });
                                 } else {
                                    $("#userBirthCheckMsg")
                                          .text("");
                                    $("#submitBtn").prop(
                                          "disabled", false);
                                    $(
                                          'input:not(#userBirth), button')
                                          .off(
                                                'mousedown click keydown focusin');
                                 }
                              });

                  $("#userTel")
                        .on(
                              'input',
                              function() {
                                 var currentUserTel = "${user.userTel}";
                                 var newUserTel = $(this).val()
                                       .replace(/[^0-9]/g, '');
                                 var formattedTel = formatPhoneNumber(newUserTel);

                                 $(this).val(formattedTel);

                                 if (newUserTel.length !== 11) {
                                    $("#userTelCheckMsg").text(
                                          "올바른 전화번호를 입력해주세요.");
                                    $("#submitBtn").prop(
                                          "disabled", true);
                                    $("input:not(#userTel), button")
                                          .on(
                                                'mousedown click keydown focusin',
                                                function(e) {
                                                   e
                                                         .preventDefault();
                                                   return false;
                                                });
                                 } else {
                                    $("#userTelCheckMsg").text("");
                                    $("#submitBtn").prop(
                                          "disabled", false);
                                    $('input:not(#userTel), button')
                                          .off(
                                                'mousedown click keydown focusin');

                                    if (currentUserTel !== formattedTel) {
                                       $
                                             .ajax({
                                                type : "POST",
                                                url : "${pageContext.request.contextPath}/user/check_userTel.do",
                                                data : {
                                                   userTel : formattedTel
                                                },
                                                success : function(
                                                      data) {
                                                   if (data == "duplicate") {
                                                      $(
                                                            "#userTelCheckMsg")
                                                            .text(
                                                                  "이미 가입된 전화번호입니다.");
                                                      $(
                                                            "#submitBtn")
                                                            .prop(
                                                                  "disabled",
                                                                  true);
                                                      $(
                                                            "input:not(#userTel), button")
                                                            .on(
                                                                  'mousedown click keydown focusin',
                                                                  function(
                                                                        e) {
                                                                     e
                                                                           .preventDefault();
                                                                     return false;
                                                                  });
                                                   } else {
                                                      $(
                                                            "#userTelCheckMsg")
                                                            .text(
                                                                  "");
                                                      $(
                                                            "#submitBtn")
                                                            .prop(
                                                                  "disabled",
                                                                  false);
                                                      $(
                                                            'input:not(#userTel), button')
                                                            .off(
                                                                  'mousedown click keydown focusin');
                                                   }
                                                }
                                             });
                                    }
                                 }
                              });

                  $("#userPw")
                        .on(
                              'input',
                              function() {
                                 var userPw = $(this).val();

                                 var regex = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,}$/;

                                 if (!regex.test(userPw)) {
                                    $("#pwError")
                                          .text(
                                                "비밀번호는 영문자와 숫자의 조합으로 최소 8자리 이상이어야 합니다.");
                                    $("#submitBtn").prop(
                                          "disabled", true);
                                    $("input:not(#userPw), button")
                                          .on(
                                                'mousedown click keydown focusin',
                                                function(e) {
                                                   e
                                                         .preventDefault();
                                                   return false;
                                                });
                                 } else {
                                    $("#pwError").text("");
                                    $("#submitBtn").prop(
                                          "disabled", false);
                                    $('input:not(#userPw), button')
                                          .off(
                                                'mousedown click keydown focusin');
                                 }
                              });

                  function formatPhoneNumber(phoneNumber) {
                     var formatted = phoneNumber.replace(
                           /(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
                     return formatted;
                  }

                  function formatBirth(userBirth) {
                     var formatted = userBirth.replace(
                           /(\d{4})(\d{2})(\d{2})/, '$1-$2-$3');
                     return formatted;
                  }

               });

   function cancel() {
      window.location.href = "${pageContext.request.contextPath}/user/mypage.do";
   }
</script>
</head>
<body>
   <div class="wrap">
      <%@ include file="../nav.jsp"%>
      <div class="container">
         <div class="form-container">
            <div class="form-title">
               <h1>내 정보 변경</h1>
               회원님의 소중한 정보를 안전하게 관리하세요.
            </div>
            <div class="form-title-mypage">
               <a href="/project/user/mypage.do">마이페이지</a>
            </div>
            <form name="myForm"
               action="${pageContext.request.contextPath}/user/modify_process.do"
               method="post">
               <table class="input-table">
                  <tr class="form-table-tr1">
                     <td class="form-label">아이디</td>
                     <td class="form-input"><input type="text" name="userId"
                        class="input-box blank" value="${user.userId}" readonly><br>* 아이디는
                        변경하실 수 없습니다.</td>
                  </tr>
                  <tr class="form-table-tr1">
                     <td class="form-label">비밀번호</td>
                     <td class="form-input"><input type="password"
                        class="input-box" name="userPw" id="userPw"
                        value="${user.userPw}" required><br> <span
                        id="pwError" style="color: red;"></span><br>* 영문자, 숫자 조합하여
                        만들어야 합니다.</td>
                  </tr>
                  <tr class="form-table-tr2">
                     <td class="form-label">닉네임</td>
                     <td class="form-input"><input type="text" name="nickname"
                        id="nickname" class="input-box" value="${user.nickname}"
                        maxlength="7" required><br> <span
                        id="nicknameCheckMsg" style="color: red;"></span><br>* 닉네임은
                        최대 7글자까지 가능합니다.</td>
                  </tr>
                  <tr class="form-table-tr2">
                     <td class="form-label">이름</td>
                     <td class="form-input"><input type="text" name="userName"
                        class="input-box" value="${user.userName}" required></td>
                  </tr>
                  <tr class="form-table-tr1">
                     <td class="form-label">생년월일</td>
                     <td class="form-input"><input type="text" id="userBirth"
                        name="userBirth" class="input-box"
                        value='<fmt:formatDate value="${user.userBirth}" pattern="yyyy-MM-dd"/>' /><br>
                        <span id="userBirthCheckMsg" style="color: red;"></span><br>*
                        생년월일은 8자리로 입력해야 합니다. ex) 1990-02-13</td>
                  </tr>
                  <tr class="form-table-tr2">
                     <td class="form-label">전화번호</td>
                     <td class="form-input"><input type="text" name="userTel"
                        id="userTel" class="input-box" value="${user.userTel}" required><br>
                        <span id="userTelCheckMsg" style="color: red;"></span></td>
                  </tr>
               </table>
               <div class="btn-container">
                  <button type="submit" id="submitBtn" class="btn-register" disabled>등록</button>
                  <input type="button" class="btn-cancel" onclick="cancel()"
                     value="취소">
               </div>
            </form>
         </div>
      </div>
      <%@ include file="../footer-sub.jsp"%>
   </div>
</body>
</html>
