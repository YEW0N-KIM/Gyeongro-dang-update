<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>경로게시판</title>
    
     <link href="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.snow.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.js"></script>
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/atom-one-dark.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.css" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/createRouteForm.css" >
   <script defer src="${pageContext.request.contextPath}/resources/js/map.js"></script>
   <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
   

    <!-- icon key -->
    <script src="https://kit.fontawesome.com/7fa6781ad2.js" crossorigin="anonymous"></script>
    
</head>
<script type="text/javascript">    
   function previewImage(input) {
       if (input.files && input.files[0]) {
           var reader = new FileReader();
   
           reader.onload = function(e) {
               $('#coverImagePreview').attr('src', e.target.result).show();
           };
           reader.readAsDataURL(input.files[0]);
       }
   }
   
   // editor의 내용 input으로 전달
   function send() {
       let boardTitle = document.getElementById("boardTitle").value.trim();
       let boardPoint = document.getElementById("boardPoint").value;
       let boardRegion = document.getElementById("boardRegion").value;
       let boardTheme = document.getElementById("boardTheme").value;
   
       if (boardTitle === '') {
           alert("제목을 입력해주세요.");
           return false;
       }
   
       if (boardRegion == "-1") {
           alert("지역을 선택하세요.");
           return false;
       }
   
       if (boardTheme == "0") {
           alert("여행 테마를 선택하세요.");
           return false;
       }
        
         document.getElementById("contentMessage").style.display = "none";
  
           let editor  = document.querySelector(".ql-container");
           let content =  editor.innerHTML;
           let  boardContent  = document.querySelector("#boardContent");
           boardContent.value= content;
           
           
           
           if (boardContent.value.length < 384) {
              alert("총평을 입력해주세요.");
              return false;
           }
           
           if(boardPoint > 10000) {
              alert('포인트는 최대 10000 포인트까지 가능합니다. 다시 입력해주세요.');
              return false;
           }
           
           if (toggle === 0) {
              alert('일정을 먼저 저장해주세요.');
              return false;
           } 
           
          document.frm.submit();
  }
  
</script>
<body>
      
   <div class="wrap">
    <!-- nav -->
     <%@ include file="../nav.jsp" %>
      
      <!-- header -->

      <div class="container">
       <form action="/project/board/createMap_process.do" method="post" id="createMapForm" name="frm" enctype="multipart/form-data">   
          <div class="title-container">
              <!-- 이미지 영역 -->
              <div class="image-box">
                  <div id="img-container"> < 파일을 첨부해주세요 > </div>
                  <input class="imgUploader" type="file"  name="boardImg" accept="image/*" onchange="loadFiles(this)" >
              </div>
              <!-- 타이틀 영역 -->
             
              <div class="title-box">
                 <input type="hidden" id="userCode" name="userCode" value="${user.userCode}">
                  <div class="icon-region">
                      <select name="boardRegion" id="boardRegion" required>
                          <option value="-1">지역선택</option>
                        <option value="0">서울</option>
                        <option value="1">인천</option>
                        <option value="2">대전</option>
                        <option value="3">대구</option>
                        <option value="4">경기</option>
                        <option value="5">부산</option>
                        <option value="6">울산</option>
                        <option value="7">광주</option>
                        <option value="8">강원</option>
                        <option value="9">충북</option>
                        <option value="10">충남</option>
                        <option value="11">경북</option>
                        <option value="12">경남</option>
                        <option value="13">전북</option>
                        <option value="14">전남</option>
                        <option value="15">제주</option>
                        <option value="16">세종</option>
                    </select>
                  </div>
                  <div class="theme-section"> 
                     <select name="boardTheme" id="boardTheme" required>
                          <option value="0">여행테마선택</option>
                        <option value="1">나홀로 힐링</option>
                        <option value="2">연인과 함께</option>
                        <option value="3">친목 다지기~</option>
                        <option value="4">가족 나들이</option>
                        <option value="5">모임 여행</option>
                    </select>
                  </div>
                  <div class="title-section"><input type="text" id="boardTitle" name="boardTitle" placeholder="제목을 입력하세요" required maxlength="40"></div>
                  <div class="date-section">여행일수 : <input type="text" id="boardTourdays" name="boardTourdays" readonly required>일 여행</div>

                  
                  <div class="point-section">
                      <div class="point-section-input">
                          <i class="fa-brands fa-product-hunt fa-2x"></i>
                          <input type="number" id ="boardPoint" min="0" value="0" max="10000" step="50" name="boardPoint" class="price" required>
                      </div>
                     <div class="point-section-length">
                          <b>[포인트사용 주의사항]</b><br>
                          포인트는 기본 0포인트 이며, 포인트는 최대 10000 포인트까지 가능합니다. <br>
                          유료 포스트로 작성하시려면 받고 싶은 포인트를 입력하세요.
                      </div>
                  </div>
              </div>
              
               <div class="review-container">
                 <h2># 총평</h2>
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
             </div>
          </div>
       </form>
       
                              <!-- 경로 사용법 모달 -->
         <div class="route-modal"> 
              <button id="btn-modal">
                  <img class="img-black" src="${pageContext.request.contextPath}/resources/img/question.png" alt="" style="width: 25px; height: 25px;">
                  <img class="img-color" src="${pageContext.request.contextPath}/resources/img/question-color.png" alt="" style="width: 25px; height: 25px;">
              </button>
          </div>
          <div id="modal" class="modal-overlay">
              <div class="modal-window">
                  <div class="title">
                      <h2><img src="${pageContext.request.contextPath}/resources/img/question.png" alt="" style="width: 25px; height: 25px;"> 경로게시판 작성방법</h2>
                  </div>
                  <div class="close-area">X</div>
                  <div class="content">
                      <img src="${pageContext.request.contextPath}/resources/img/route_manual.jpg" alt="">
                  </div>
              </div>
            </div>
              
       
          <hr class="divider first">

          <!-- 경로 영역 -->
          <div class="route-container">

              <div class="route-index">
                 <div class="newIndex-button">
                     <div class="index-button">
                         <h1>1</h1>
                     </div>
                    
                    </div>
                  <div class="add-button">
                      <button type="button" id="addDayBtn">+</button>
                  </div>
              </div>

              <div class="route-box">

                  <div class="route-upperside">
                      <div class="map" id="map" ></div>
                      <div class="toolTab">
                       <div class="toolBtn">
                           <button id="myButton">
                               <div class="btnImg">
                                   <img src="${pageContext.request.contextPath}/resources/img/search.png" alt="">
                               </div>
                               Search
                           </button>
                       </div>
                       <div class="toolBtn">
                           <button id="test">
                               <div class="btnImg">
                                   <img src="${pageContext.request.contextPath}/resources/img/checkmark.png" alt="">
                               </div>
                               Test
                           </button>
                       </div>
                       <div class="toolBtn">
                           <button id="line">
                               <div class="btnImg">
                                   <img src="${pageContext.request.contextPath}/resources/img/gps-route.png" alt="">
                               </div>
                               Line
                           </button>
                       </div>
                       <div class="toolBtn">
                           <button id="linedelete">
                               <div class="btnImg">
                                   <img src="${pageContext.request.contextPath}/resources/img/eraser.png" alt="">
                               </div>
                               Delete
                           </button>
                       </div>
                   </div>
                      
                      <div id="searchTab" class="bg_white" class="slide">
                       <div class="option">
                           <div>
                           <form onsubmit="searchPlaces(); return false;">
                           <input type="text" class="searchInput" value="이태원 맛집" id="keyword" size="5"> 
                           <button type="submit">검색하기</button> 
                          </form>
                           </div>
                      </div>
                       <hr>
                       <ul id="placesList"></ul>
                       <div id="pagination"></div>
                   </div>
                   
                      <div class="btn-container">
                      <button class="cancle-button" onclick="history.back()">목록보기</button>
                      <button type="button" id="submitBtn" onclick="send()" class="submit-button" > 전체 저장</button>
                      </div>
                  </div>

             <form action="/project/board/dayPlans.do" method="post" id="dayPlanForm">
                     <div class="route-lowerside">
                        <div class="map-place-list-section">
                             <h2>#상세코스</h2>
                             <div class="place-list">
                          <input type="hidden" name="days[${dayIndex - 1}].day" id="dayIndex" placeholder="dayIndex" required> 
                          <div id="clickLatlng" class="click"></div>
                            </div>
                         </div>
                        <div class="day-comment-list">
                            <h2 id="dayCommentH2"># 코멘트</h2>
                            <div class="day-comment">
                            <textarea rows="" cols="" name="days[${dayIndex - 1}].dayInfo" id="dayInfo" placeholder="해당 일차 후기를 작성해 주세요." required class="commentTextarea"></textarea>                           </div>
                         </div>
                        
                     </div>
                     <button type="button" id="dayBtn">일정 저장</button>
             </form>
             </div>


          </div>

   
          <div class="return-to-list-button"></div>
      </div>
    <%@ include file="../footer-sub.jsp" %>
  </div>


   <!-- Include the Quill library -->
   <script src="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.js"></script>
   
   <!-- Initialize Quill editor -->
   <script>
   const quill = new Quill('#editor', {
     modules: {
       syntax: true,
       toolbar: '#toolbar-container',
     },
     placeholder: '* [주의] 유료 포스트로 작성하여도 총평은 전체공개 됩니다. 공개하고 싶지 않으시면 일차별 후기에 작성하세요 ^^',
     theme: 'snow',
   });
   </script>
   
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e4e2ed92d2d2bdb6c1837e8f4e3094f&libraries=services,clusterer,drawing"></script>
   <script>
   var toggle = 0;
      $(document).ready(function() {
          var dayIndex = 1; 
          
      
          document.getElementById("boardTourdays").value = dayIndex;
          document.getElementById("dayIndex").value = dayIndex;
          document.getElementById("dayCommentH2").innerHTML = '#'+dayIndex+'일차 후기';
          
          
          
          $("#addDayBtn").on('click', function() {
              if (toggle === 1) {
                  var confirm_ = window.confirm("다음 일차를 추가하시면 이전 일차로 돌아가실 수 없습니다.");
                  if (confirm_) {
                      dayIndex++;
                      document.getElementById("dayIndex").value = dayIndex;
                      document.getElementById("boardTourdays").value = dayIndex;    
                      document.getElementById("dayInfo").value = '';
                      document.getElementById("clickLatlng").innerHTML = '';
                      document.getElementById("dayCommentH2").innerHTML = '#' + dayIndex + '일차 후기';

                      deleteAll();
                      toggle = 0;

                      var newIndexBtn = document.getElementsByClassName("newIndex-button")[0];
                      var newDiv = document.createElement("div");
                      newDiv.className = "index-button";
                      var newH1 = document.createElement("h1");
                      newH1.innerText = dayIndex;
                      newDiv.appendChild(newH1);
                      newIndexBtn.appendChild(newDiv);
                      document.getElementById("dayBtn").innerHTML = '일정 저장';
                      document.getElementById('submitBtn').disabled = true;
                  }
              } else {
                  alert("저장한 후에 일정을 추가해주세요.");
              }
          });
      
          $("#dayBtn").on('click', function() {
             var dayInfo = $("#dayInfo").val().replace(/\n/g, "\\n");
              var clickLatlngDiv = document.getElementById("clickLatlng");
              var positionInfos = clickLatlngDiv.getElementsByClassName("positionInfo");
              
              if (positionInfos.length === 0) {
                  alert("명소를 추가해주세요.");
                  return;
              }
              
              var jsonData = {
                  day: dayIndex,
                  dayInfo: dayInfo,
                  markers: []
              };
              
              var valid = true;
              
              for (var i = 0; i < positionInfos.length; i++) {
                  var positionInfo = positionInfos[i];
                  var markerIndex = i;
                  var title = positionInfo.querySelector("#markerTitle_" + markerIndex).value;
                  var latitude = positionInfo.querySelector("#latitude_" + markerIndex).value;
                  var longitude = positionInfo.querySelector("#longitude_" + markerIndex).value;
                  var position = positionInfo.querySelector("#position_" + markerIndex).value;
                  var explain = positionInfo.querySelector("#explain_" + markerIndex).value;
                  
                  if (!title || !latitude || !longitude || !position || !explain) {
                      valid = false;
                      break;
                  }
                  
                  var markerData = {
                      title: title,
                      latitude: latitude,
                      longitude: longitude,
                      position: position,
                      explain: explain
                  };
                  jsonData.markers.push(markerData);
              }
      
              if (!valid) {
                  alert("모든 필드를 채워주세요.");
                  return;
              }
              $.ajax({
                  type: "POST",
                  url: "${pageContext.request.contextPath}/board/dayPlans.do",
                  contentType: "application/json",
                  data: JSON.stringify(jsonData),
                  success: function(data) {
                      console.log("Success:", data);
                      alert('저장 성공!');
                      document.getElementById("dayBtn").innerHTML = '수정';
                      document.getElementById('submitBtn').disabled = false;
                      toggle = 1;
                  },
                  error: function(error) {
                      console.error("Error:", error);
                  }
              });
          });
      });
      
      
   </script>
   
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

   <script>
        const modal = document.getElementById("modal");
        
        function modalOn() {
            modal.style.display = "flex";
        }

        function isModalOn() {
            return modal.style.display === "flex";
        }

        function modalOff() {
            modal.style.display = "none";
        }

        const btnModal = document.getElementById("btn-modal");

        btnModal.addEventListener("click", e => {
            modalOn();
        });

        // 모달 닫기 기능 추가
        const closeArea = document.querySelector("#modal .close-area");
        closeArea.addEventListener("click", e => {
            modalOff();
        });

        // 외부 스크립트 동적 로드
        var script = document.createElement('script');
        script.src = 'http://t1.daumcdn.net/mapjsapi/js/libs/services/1.0.2/services.js';
        document.head.appendChild(script);
    </script>

</body>
</html>