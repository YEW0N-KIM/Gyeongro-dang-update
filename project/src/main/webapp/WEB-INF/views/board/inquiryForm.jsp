<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>고객센터</title>
    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/inquiryForm.css" >

</head>

<body>

    <div class="wrap">
      <%@ include file="../nav.jsp" %>
        <!-- 시작 -->
        <div class="inquiry-container">

            <div class="inquiry-header">
                <h1>1:1 문의</h1>
                <p>문의사항을 아래 박스에 입력해주시면 답변 드리겠습니다.</p>

            </div>

            <span class="notice-required" id="notice-star">*</span><span class="notice-required">필수입력사항입니다</span>
            <a href="/project/board/my/inquiry6" class="myInquiry">내 문의내역</a>
            <div class="inquiry-content">
            
            
                <form action="inquiry" method="post"  enctype="multipart/form-data">
                     
                      <input type="hidden" name="userCode" value="${user.userCode}">
                    <fieldset>
                        <div>
                            <label for="title">제목</label>
                            <input type="text" name="boardTitle" id="boardTitle" maxlength="20" required>
                            <span class="form-input-required">*</span>
                        </div>

                        <div>
                            <label for="name">이름</label>
                            <input type="text" name="name" required value="${user.userName}" readonly>
                            <span class="form-input-required">*</span>
                        </div>

                        <div class="content">
                            <label for="content" class="content">내용</label>
                            <textarea cols="70" rows="20" name="boardContent" class="required" placeholder=""></textarea>
                        </div>

                        <div id="img-container"></div>
                        <input class="imgUploader" type="file"  name="boardImg" accept="image/*" onchange="loadFiles(this)" multiple>

                        <p class="notice"> 이미지는 한 장만 첨부 가능합니다.</p>
                        
                        
                  <input type=hidden name="boardTheme" id="boardTheme" value="0">
                    <input type=hidden name="boardTourdays" id="boardTourdays" value="0">
                    <input type=hidden name="boardViews" id="boardViews" value="0" >
                    <input type=hidden name="boardLikes" id="boardLikes"value="0" >
                    <input type=hidden name="boardPoint" id="boardPoint"value="0" >
                    <input type="hidden" name="boardType" value="6">
                        
                        
                       <!-- <a class="cancle-button" href="/project/board/faq">취소</a> --> 
                       <!-- 문의내역에서 들어가는 경우랑 고객센터에서 들어가는 경우 두 가지라서 링크 말고 뒤로가기로 바꿨음 -->
                        <button class="cancle-button" onclick="history.back()">취소</button>
                        <button class="submit-button">문의등록</button>
                    </fieldset>
                    
                </form>        
            </div>


        </div>

            <%@ include file="../footer-sub.jsp" %>
    </div>
    
</body>
<script>

    function loadFiles(input) {
        const files = input.files;
        const container = document.getElementById('img-container');

        container.innerHTML = '';

        const maxPreviews = 3;
        for (let i = 0; i < Math.min(files.length, maxPreviews); i++) {
            const file = files[i];
            const newImage = document.createElement("img");

            newImage.src = URL.createObjectURL(file);
            newImage.style.width = "100%";
            newImage.style.height = "100%";
            newImage.style.objectFit = "cover";

            const imgContainer = document.createElement('div');
            imgContainer.className = 'uploadImg';
            imgContainer.appendChild(newImage);
            container.appendChild(imgContainer);
        }
    }

</script>

</html>