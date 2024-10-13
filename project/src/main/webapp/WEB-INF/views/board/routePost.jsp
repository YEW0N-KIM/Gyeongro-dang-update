<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="com.acorn.project.user.domain.User"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>경로게시판 ${routeBoard.boardTitle}</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/routePost.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/quill@2.0.1/dist/quill.snow.css"   rel="stylesheet" />
<link rel="stylesheet"   href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/atom-one-dark.min.css" />
<link rel="stylesheet"   href="https://cdn.jsdelivr.net/npm/katex@0.16.9/dist/katex.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<!-- icon key -->
<script src="https://kit.fontawesome.com/7fa6781ad2.js" crossorigin="anonymous"></script>

<style type="text/css">
#boardModi{
	display: none !important;
}
</style>

</head>

<body>


   <%
   User user = (User) session.getAttribute("user");
   String userCode = (user != null) ? user.getUserCode() : null;
   %>

   <div class="wrap">
      <!-- nav -->
      <%@ include file="../nav.jsp"%>

      <!-- header -->

      <div class="container">
         <div class="btn-container"></div>


         <div class="title-container">
            <!-- 이미지 영역 -->
            <div class="image-box">
           		<c:choose>
			        <c:when test="${not empty routeBoard.boardImgReal}">
			            <img src="<c:url value='/board/images/${routeBoard.boardImgReal}' />" >
			        </c:when>
			        <c:otherwise>
			            <img src="${pageContext.request.contextPath}/resources/img/blankimg.png" >
			        </c:otherwise>
			    </c:choose>
            </div>            
            
            
            <!-- 타이틀 영역 -->
            <div class="title-box">
               <div class="icon-region">
                  <p>
                     <c:choose>
                        <c:when test="${routeBoard.boardRegion == 0}">서울</c:when>
                        <c:when test="${routeBoard.boardRegion == 1}">인천</c:when>
                        <c:when test="${routeBoard.boardRegion == 2}">대전</c:when>
                        <c:when test="${routeBoard.boardRegion == 3}">대구</c:when>
                        <c:when test="${routeBoard.boardRegion == 4}">경기</c:when>
                        <c:when test="${routeBoard.boardRegion == 5}">부산</c:when>
                        <c:when test="${routeBoard.boardRegion == 6}">울산</c:when>
                        <c:when test="${routeBoard.boardRegion == 7}">광주</c:when>
                        <c:when test="${routeBoard.boardRegion == 8}">강원</c:when>
                        <c:when test="${routeBoard.boardRegion == 9}">충북</c:when>
                        <c:when test="${routeBoard.boardRegion == 10}">충남</c:when>
                        <c:when test="${routeBoard.boardRegion == 11}">경북</c:when>
                        <c:when test="${routeBoard.boardRegion == 12}">경남</c:when>
                        <c:when test="${routeBoard.boardRegion == 13}">전북</c:when>
                        <c:when test="${routeBoard.boardRegion == 14}">전남</c:when>
                        <c:when test="${routeBoard.boardRegion == 15}">제주</c:when>
                        <c:when test="${routeBoard.boardRegion == 16}">세종</c:when>
                     </c:choose>
                  </p>
               </div>
               <div class="title-section">${routeBoard.boardTitle}</div>
               <div class="writer-section">
                  <p>${routeBoard.nickname}</p>
               </div>
               <div class="date-section">${routeBoard.boardTourdays}일</div>
               <div class="theme-section">
                  <c:choose>
                     <c:when test="${routeBoard.boardTheme == 1}">#나홀로 힐링</c:when>
                     <c:when test="${routeBoard.boardTheme == 2}">#연인과 데이트</c:when>
                     <c:when test="${routeBoard.boardTheme == 3}">#친구들과</c:when>
                     <c:when test="${routeBoard.boardTheme == 4}">#가족 나들이</c:when>
                     <c:when test="${routeBoard.boardTheme == 5}">#모임 단체 여행</c:when>
                  </c:choose>
               </div>
               <div class="mark-section">
                  <div class="likes">
                     <i class="fa-regular fa-heart"></i>
                     <span>${like}</span>
                  </div>
                  <div class="bookmark">
                     <i class="fa-regular fa-bookmark"></i>
                      <span>${arch}</span>
                  </div>
                  <div class="views">
                     <span>views</span><span> ${routeBoard.boardViews }</span>
                  </div>
               </div>

               <div class="point-section">
                  <i class="fa-brands fa-product-hunt fa-2x"></i> 
                  <span class="price">${routeBoard.boardPoint }</span>
               </div>
            </div>

         </div>

         <hr class="divider first">

         <!-- 경로 영역 -->
         <div class="route-container">
            <div class="route-index">
               <c:forEach var="day" items="${routeBoard.days}" varStatus="loop">
                  <div class="index-button">
                     <h1>${loop.index+1}</h1>
                  </div>
               </c:forEach>
            </div>

            <div class="route-box">
               <c:choose>
                  
                  <c:when test="${message eq 'X'}">
                     <div class="route-container-blur not-purchased">


                        <div class="route-upperside">
                           <div class="map" id="map" style="width: 60%; height: 380px;"></div>
                           <div class="map-place-list-section">
                              <h2>#상세코스</h2>
                              <div class="place-list" id="place-list"></div>
                           </div>
                        </div>

                        <div class="route-lowerside">
                           <h2># 코멘트</h2>
                           <div class="day-comment">
                              <!-- test -->
                              <p class="dayCommentP">하루의 코멘트</p>

                           </div>
                        </div>

                     </div>

                     <div class="alert-box">

                        <p class="alert-message">결제 후, 열람 가능합니다.</p>

                        <form action="/project/point/buyBoard_process.do" method="post"
                           id="buyBoardForm">
                           <div class="buybtnWrap">
                              <input type="hidden" name="userCode" value="<%=userCode%>">
                              <input type="hidden" name="writerCode"
                                 value="${routeBoard.userCode}"> <input type="hidden"
                                 name="boardCode" value="${routeBoard.boardCode}"> <input
                                 type="hidden" name="pointAmount"
                                 value="${routeBoard.boardPoint}">
                              <button type="button" id="buyBtn" class="button-for-register">구매하기</button>
                           </div>
                        </form>

                     </div>

                  </c:when>

                  
                  <c:when test="${message eq 'login'}">
                     <div class="route-container-blur not-purchased">


                        <div class="route-upperside">
                           <div class="map" id="map" style="width: 60%; height: 380px;"></div>
                           <div class="map-place-list-section">
                              <h2>#상세코스</h2>
                              <div class="place-list" id="place-list"></div>
                           </div>
                        </div>

                        <div class="route-lowerside">
                           <h2># 코멘트</h2>
                           <div class="day-comment">
                              <!-- test -->
                              <p class="dayCommentP">하루의 코멘트</p>

                           </div>
                        </div>

                     </div>

                     <div class="alert-box">

                        <p class="alert-message">로그인 진행 후, 열람 가능합니다.</p>

                        <button type="button" class="button-for-register"
                           onclick="location.href='/project/user/login.do'">로그인</button>

                     </div>
                  </c:when>

                  <c:otherwise>
                     <div class="route-container-blur purchased">
                        <div class="route-upperside">
                           <div class="map" id="map" style="width: 60%; height: 380px;"></div>
                           <div class="map-place-list-section">
                              <h2>#상세코스</h2>
                              <div class="place-list" id="place-list"></div>
                           </div>
                        </div>

                        <div class="route-lowerside">
                           <h2># 코멘트</h2>
                           <div class="day-comment">
                              <!-- test -->
                              <p class="dayCommentP">하루의 코멘트</p>

                           </div>
                        </div>

                     </div>

                  </c:otherwise>
               </c:choose>


            </div>
         </div>

         <hr class="divider second">

         <!-- 총평 영역 -->
         <div class="review-container">
            <h2># 총평</h2>
            <div class="review-section">
               ${routeBoard.boardContent}
            </div>
         </div>

         <!-- 댓글영역 -->
         <div class="comment-container">

            <input type="hidden" class="boardUsercode"
               value="${routeBoard.userCode}"> <input type="hidden"
               class="userCode" value=<%=userCode%>> <input type="hidden"
               class="boardCode" value="${routeBoard.boardCode}">
            <%@ include file="comment.jsp"%>
         </div>

         <!-- 댓글 영역 끝-->






      </div>
      <%@ include file="../footer-sub.jsp"%>
   </div>


</body>

<script>

$(document).ready(function() {
    $('#buyBtn').click(function() {
        var formData = $('#buyBoardForm').serialize();

        $.ajax({
            url: '/project/point/buyBoard_process.do',
            type: 'post',
            data: formData,
            dataType: 'json',
            success: function(response) {
                if (response > 0) {
                    alert('구매가 성공적으로 처리되었습니다.');
                    
                    location.reload(); 
                    
                } else {
                   alert('포인트를 충전해주세요');
                    window.location.href = '/project/user/mypage.do';
                }
            },
            error: function(error) {
                console.error('error:', error);
                alert('구매 요청 중 오류가 발생하였습니다.');
            }
        });
    });
});





    var indexBtn = document.getElementsByClassName("index-button");

    function handleClick(event) {

        if (event.currentTarget.classList.contains("clicked")) {

            event.currentTarget.classList.remove("clicked");
        } else {
            for (var i = 0; i < indexBtn.length; i++) {
                indexBtn[i].classList.remove("clicked");
            }
            event.currentTarget.classList.add("clicked");
        }
    }

    function init() {

        if (indexBtn.length > 0) {
            indexBtn[0].classList.add("clicked");
        }

        for (var i = 0; i < indexBtn.length; i++) {
            indexBtn[i].addEventListener("click", handleClick);
        }
    }

    init();
</script>

<script>
    function checkComments() {
        var comments = document.getElementsByClassName("each-comment");
        var noCommentsMessage = document.getElementById("no-comments-message");
        
        if (comments.length === 0) {
            noCommentsMessage.style.display = "block";
        } else {
            noCommentsMessage.style.display = "none";
        }
    }

    document.addEventListener("DOMContentLoaded", function() {
        checkComments();
    });
</script>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5e4e2ed92d2d2bdb6c1837e8f4e3094f"></script>
<script>
    var markersData = {};
    var dayInfo = [];
    <c:forEach var="day" items="${routeBoard.days}" varStatus="loop">
        dayInfo[${loop.index}] = ['<c:out value="${day.dayInfo}" />'],
        markersData[${loop.index}] = [
            <c:forEach var="marker" items="${day.markers}" varStatus="innerLoop">
            {
                title: '<c:out value="${marker.title}" />',
                position : '<c:out value="${marker.position}" />',
                explain : '<c:out value="${marker.explain}" />',
                latlng: {lat: <c:out value="${marker.latitude}" />, lng: <c:out value="${marker.longitude}" />}
            }<c:if test="${not innerLoop.last}">,</c:if>
            </c:forEach>
        ];
    </c:forEach>

    var map;
    var markers = [];
    var infowindows = [];

    function clearMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].setMap(null);
            infowindows[i].close();
        }
        markers = [];
        infowindows = [];
    }

    function updateMapMarkers(dayIndex) {
        clearMarkers();

        var positions = markersData[dayIndex];
        var dayInfoElement = document.querySelector(".dayCommentP");
        var preElement = document.createElement("pre");
        preElement.textContent = dayInfo[dayIndex];
        
        dayInfoElement.innerHTML = '';
        dayInfoElement.appendChild(preElement);

        if (positions.length > 0) {
            map.setCenter(new kakao.maps.LatLng(positions[0].latlng.lat, positions[0].latlng.lng));
        }

        for (var i = 0; i < positions.length; i++) {
            var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png';
            var imageSize = new kakao.maps.Size(36, 37);
            var imgOptions = {
                spriteSize: new kakao.maps.Size(36, 691),
                spriteOrigin: new kakao.maps.Point(0, (i * 46) + 10),
                offset: new kakao.maps.Point(13, 37)
            };
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions);

            var marker = new kakao.maps.Marker({
                map: map,
                position: new kakao.maps.LatLng(positions[i].latlng.lat, positions[i].latlng.lng),
                title: positions[i].title,
                image: markerImage
            });

            markers.push(marker);
            

            var iwContent = '<div style="padding:5px;">' + '<strong>' + positions[i].title + '</strong><br>' +
                '<a href="https://map.kakao.com/link/map/' + encodeURIComponent(positions[i].title) + ',' + positions[i].latlng.lat + ',' + positions[i].latlng.lng + '" target="_blank">큰지도보기</a> ' +
                '<a href="https://map.kakao.com/link/to/' + encodeURIComponent(positions[i].title) + ',' + positions[i].latlng.lat + ',' + positions[i].latlng.lng + '" target="_blank">길찾기</a>' + '</div>';

            var infowindow = new kakao.maps.InfoWindow({
                content: iwContent
            });

            infowindows.push(infowindow);
            if (i === 0) {
                infowindow.open(map, marker);
            }
            
            kakao.maps.event.addListener(marker, 'click', (function (marker, infowindow) {
                return function () {
                   if (infowindow.getMap()) {
                        infowindow.close();
                    } else {
                        infowindow.open(map, marker);
                    }
                };
            })(marker, infowindow));
        }

        var placeList = document.querySelector(".place-list");
        placeList.innerHTML = "";

        for (var i = 0; i < positions.length; i++) {
            var div = document.createElement("div");
            div.className = "markerDetail";

            var number = document.createElement("span");
            number.className = "number";
            number.textContent = (i + 1) + ".";

            var inputs = document.createElement("div");
            inputs.className = "markerInputs";

            var title = document.createElement("input");
            title.className = "markerInput markerTitle";
            title.id = "markerTitle" + i;
            title.value = positions[i].title;
            title.readOnly = true;

            var position = document.createElement("input");
            position.className = "markerInput markerPosition";
            position.id = "markerPosition" + i;
            position.value = positions[i].position;
            position.readOnly = true;

            var explain = document.createElement("input");
            explain.className = "markerInput markerExplain";
            explain.id = "markerExplain" + i;
            explain.value = positions[i].explain;
            explain.readOnly = true;

            placeList.appendChild(div);
            div.appendChild(number);
            div.appendChild(inputs);
            inputs.appendChild(title);
            inputs.appendChild(position);
            inputs.appendChild(explain);
        }
    }

    document.addEventListener("DOMContentLoaded", function() {
        var mapContainer = document.getElementById('map');
        var mapOption = {
            center: new kakao.maps.LatLng(${routeBoard.days[0].markers[0].latitude}, ${routeBoard.days[0].markers[0].longitude}),
            level: 5
        };
        map = new kakao.maps.Map(mapContainer, mapOption);
        updateMapMarkers(0);
        
        
    });

    var indexBtn = document.getElementsByClassName("index-button");

    function handleClick(event) {
        var index = Array.prototype.indexOf.call(indexBtn, event.currentTarget);
        updateMapMarkers(index);

        if (!event.currentTarget.classList.contains("clicked")) {
            event.currentTarget.classList.add("clicked");
        }

        for (var i = 0; i < indexBtn.length; i++) {
            if (i !== index) {
                indexBtn[i].classList.remove("clicked");
            }
        }
    }

    function init() {
        if (indexBtn.length > 0) {
            indexBtn[0].classList.add("clicked");
        }

        for (var i = 0; i < indexBtn.length; i++) {
            indexBtn[i].addEventListener("click", handleClick);
        }
    }

    init();
</script>
</html>