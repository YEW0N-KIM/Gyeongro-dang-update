<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
    <link href="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.snow.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.js"></script>
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/atom-one-dark.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/freeBoardForm.css" >
      <script type="text/javascript">    
      // editor의 내용 input으로 전달
         function send(){
          
            document.getElementById("titleMessage").style.display = "none";
            document.getElementById("contentMessage").style.display = "none";
         
          let editor  = document.querySelector(".ql-container");
          let content =  editor.innerHTML;
          let  boardContent  = document.querySelector("#boardContent");
          boardContent.value= content;
         
       }
       
          // 모든 입력란이 유효한지 확인 후 페이지 전환 함수
          function validate() {
             send();
              var title = $("#boardTitle").val().trim();
              var content = $(".ql-editor").html();
              var boardType = $("#boardType").val();
         
              
              // 제목 입력 여부 확인
              if (title === "") {
                  $("#titleMessage").html("제목을 입력하세요").show();
                  $("#boardTitle").focus();
                  return;
              } 
           // 내용 입력 여부 확인
             if (content === "<p><br></p>") {
                  $("#contentMessage").html("내용을 입력하세요").show();
                  $(".ql-editor").focus();
                  return;
              }

              // 모든 입력이 유효할 경우 폼 전송
              document.frm.submit();
          }
      </script>
       
       
</head>
<body>
   <div class="wrap">

      <%@ include file="../nav.jsp" %>   
   
       <div class="container">
          <div class="freeBoardForm-section">
              <h2>글쓰기</h2>
          </div>
           <form  name="frm" action="reg" method="post">
             <div class="custom-section">
                 <select name="boardType" id="boardType" required>
                       <option value="1">여행후기</option>
                     <option value="2">꿀팁공유</option>
                     <option value="3">질문있어요</option>
                     <option value="4">수다방</option>
                       <option value="5">동행구해요!</option>
                   </select>
                   <input type="text" placeholder="제목을 입력하세요." name="boardTitle" id="boardTitle" maxlength="40" required>
               </div>  
                 <div id="titleMessage" style="display:none; color:red;"></div>
                 <input type="hidden" name="userCode" id="userCode" value="${user.userCode}">
                 <input type="hidden" name="boardImgOrigin" id="boardImgOrigin" value="null">
                 <input type="hidden" name="boardImgReal" id="boardImgReal" value="null">
                 <input type="hidden" name="boardTheme" id="boardTheme" value="-1">
                 <input type="hidden" name="boardTourdays" id="boardTourdays" value="-1">
                 <input type="hidden" name="boardViews" id="boardViews" value="0">
                 <input type="hidden" name="boardPoint" id="boardPoint" value="0">
          
      

               
       <!-- Create the editor container -->
       <div id="contentMessage" style="display:none; color:red;"></div>
       <div id="toolbar-container">
         
           <span class="ql-formats">
             <select class="ql-font"></select>
             <select class="ql-size"></select>
           </span>
           <span class="ql-formats">
             <button class="ql-bold"></button>
             <button class="ql-italic"></button>
             <button class="ql-underline"></button>
             <button class="ql-strike"></button>
           </span>
           <span class="ql-formats">
             <select class="ql-color"></select>
             <select class="ql-background"></select>
           </span>
           <span class="ql-formats">
             <button class="ql-script" value="sub"></button>
             <button class="ql-script" value="super"></button>
           </span>
           <span class="ql-formats">
             <button class="ql-header" value="1"></button>
             <button class="ql-header" value="2"></button>
             <button class="ql-blockquote"></button>
             <button class="ql-code-block"></button>
           </span>
           <span class="ql-formats">
             <button class="ql-list" value="ordered"></button>
             <button class="ql-list" value="bullet"></button>
             <button class="ql-indent" value="-1"></button>
             <button class="ql-indent" value="+1"></button>
           </span>
           <span class="ql-formats">
             <button class="ql-direction" value="rtl"></button>
             <select class="ql-align"></select>
           </span>
           <span class="ql-formats">
             <button class="ql-link"></button>
             <button class="ql-image"></button>
             <button class="ql-video"></button>
             <button class="ql-formula"></button>
           </span>
           <span class="ql-formats">
             <button class="ql-clean"></button>
           </span>
         </div>
       
         <div id="editor"></div>
         <input type="hidden" id="boardContent" name="boardContent" required>
         
         
          <div class="freeBoardForm-btn">
            <button onclick="history.back()">뒤로가기</button>
            <button  type="button"   onclick="validate()"> 전송</button>
           </div>
     </form>    
   
        
       </div>
   </div> <!-- wrap 끝 -->

   <div class="footer"><%@ include file="../footer-sub.jsp" %></div>
      
   <!-- Include the Quill library -->
   <script src="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.js"></script>
   
   <!-- Initialize Quill editor -->
   <script>
   const quill = new Quill('#editor', {
     modules: {
       syntax: true,
       toolbar: '#toolbar-container',
     },
     placeholder: 'Compose an epic...',
     theme: 'snow',
   });
   </script>
</body>
</html>