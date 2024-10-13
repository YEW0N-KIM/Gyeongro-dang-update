<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="nav.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
	
    <title>경로당</title>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- reset.css -->   
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >

    <!-- index.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/home.css" >
    
    <script src="https://code.jquery.com/jquery-3.4.1.js"></script>

   <!-- google-font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
</head>
<body>
   <div class="wrap">
   <!-- header --> 
   <header>
      <div class="header-container">
         <div class="header-text-box">
            <div class="header-text01">경로를 공유하는 당신</div>
            <div class="header-text02">경로당</div>
            <img class="header-img4" src="${pageContext.request.contextPath}/resources/img/header-img04.png" alt="">
         </div>
      </div>
      <div class="header-img-box">
         <img class="header-img2" src="${pageContext.request.contextPath}/resources/img/header-map.png" alt="">
         <img class="header-img0" src="${pageContext.request.contextPath}/resources/img/header-img.png" alt="">
         <img class="header-img1" src="${pageContext.request.contextPath}/resources/img/header-img01.png" alt="">
      </div>
   </header>

      <!-- section-01 -->
      <section>
         <div class="section-container">
            <div class="section-slide">
               <div class="section-slide-items">
                  <div class="slide-item slide-item02 area" data-area="1" data-name="서울">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon02.png" alt="">
                       <div>서울</div>
                   </div>
                   <div class="slide-item slide-item03 area" data-area="2" data-name="인천">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon03.png" alt="">
                       <div>인천</div>
                   </div>
                   <div class="slide-item slide-item04 area" data-area="3" data-name="대전">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon04.png" alt="">
                       <div>대전</div>
                   </div>
                   <div class="slide-item slide-item05 area" data-area="4" data-name="대구">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon05.png" alt="">
                       <div>대구</div>
                   </div>
                   <div class="slide-item slide-item06 area" data-area="31" data-name="경기">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon06.png" alt="">
                       <div>경기</div>
                   </div>
                   <div class="slide-item slide-item07 area" data-area="6" data-name="부산">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon07.png" alt="">
                       <div>부산</div>
                   </div>
                   <div class="slide-item slide-item08 area" data-area="7" data-name="울산">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon08.png" alt="">
                       <div>울산</div>
                   </div>
                   <div class="slide-item slide-item09 area" data-area="5" data-name="광주">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon09.png" alt="">
                       <div>광주</div>
                   </div>
                   <div class="slide-item slide-item10 area" data-area="32" data-name="강원">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon10.png" alt="">
                       <div>강원</div>
                   </div>
                   <div class="slide-item slide-item11 area" data-area="33" data-name="충북">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon11.png" alt="">
                       <div>충북</div>
                   </div>
                   <div class="slide-item slide-item12 area" data-area="34" data-name="충남">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon12.png" alt="">
                       <div>충남</div>
                   </div>
                   <div class="slide-item slide-item13 area" data-area="35" data-name="경북">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon13.png" alt="">
                       <div>경북</div>
                   </div>
                   <div class="slide-item slide-item14 area" data-area="36" data-name="경남">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon14.png" alt="">
                       <div>경남</div>
                   </div>
                   <div class="slide-item slide-item15 area" data-area="37" data-name="전북">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon15.png" alt="">
                       <div>전북</div>
                   </div>
                   <div class="slide-item slide-item16 area" data-area="38" data-name="전남">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon16.png" alt="">
                       <div>전남</div>
                   </div>
                   <div class="slide-item slide-item17 area" data-area="39" data-name="제주">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon17.png" alt="">
                       <div>제주</div>
                   </div>
                   <div class="slide-item slide-item18 area" data-area="8" data-name="세종">
                       <img src="${pageContext.request.contextPath}/resources/img/header-icon18.png" alt="">
                       <div>세종</div>
                   </div>
               </div>
            </div>
            
            <button class="local-slide-btn local-back"><img src="${pageContext.request.contextPath}/resources/img/slide-back-btn.png" alt="" ></button>
            <button class="local-slide-btn local-next"><img src="${pageContext.request.contextPath}/resources/img/slide-next-btn.png" alt="" ></button>
            
            <div class="route-box">
                    <div class="title-text"><span class="local-name">서울,</span> 인기루트</div>
                    <div class="route-text"><a href="/project/board/route">더보기</a></div>
                    <div class="route-items">   
                                       
					<c:forEach var="board" items="${homeRouteData}">
                        <div class="route-item">
							<div class="route-img-box">
	                        	<c:choose>
									<c:when test="${not empty board.boardImgReal}">
							        	<img class="boardImg" src="<c:url value='/board/images/${board.boardImgReal}' />" alt="Board Image">
							        </c:when>
									<c:otherwise>
							        	<img class="boardImg" src="${pageContext.request.contextPath}/resources/img/blankimg.png" alt="Default Image">
									</c:otherwise>
								</c:choose>
								<div class="route-top-info">
			                    	<div class="views-info">
			                        	<div class="view-int">views<span> ${board.boardViews}</span></div>
									</div>
			                        <div class="user-info">
			                        	<div class="user-name"><span>${board.nickname}</span></div>
									</div>
			                    </div>
							</div>
	                                		
							<div class="route-title">
	                        	<div class="local-box">
					        		<c:choose>
							        	<c:when test="${board.boardRegion == 0}">서울</c:when>
							            <c:when test="${board.boardRegion == 1}">인천</c:when>
							            <c:when test="${board.boardRegion == 2}">대전</c:when>
							            <c:when test="${board.boardRegion == 3}">대구</c:when>
							            <c:when test="${board.boardRegion == 4}">경기</c:when>
							            <c:when test="${board.boardRegion == 5}">부산</c:when>
							            <c:when test="${board.boardRegion == 6}">울산</c:when>
							            <c:when test="${board.boardRegion == 7}">광주</c:when>
							            <c:when test="${board.boardRegion == 8}">강원</c:when>
							            <c:when test="${board.boardRegion == 9}">충북</c:when>
							            <c:when test="${board.boardRegion == 10}">충남</c:when>
							            <c:when test="${board.boardRegion == 11}">경북</c:when>
							            <c:when test="${board.boardRegion == 12}">경남</c:when>
							            <c:when test="${board.boardRegion == 13}">전북</c:when>
							            <c:when test="${board.boardRegion == 14}">전남</c:when>
							            <c:when test="${board.boardRegion == 15}">제주</c:when>
							            <c:when test="${board.boardRegion == 16}">세종</c:when>
					                </c:choose>
								</div>
		                        <div class="route-name-box">
			                        <div class="route-name">${board.boardTitle}</div>
								</div>
							</div>                        	
						</div>
					</c:forEach>
				</div>
                <hr class="section-hr">
			</div>
                
            <div class="tour-box">
               <div class="title-text">
                  <span class="local-name">서울,</span> 인기 관광지
               </div>
               <div class="see-text tour-text">
                  <a href="/project/tourlist" class="load-more-btn" data-type="tour">더보기</a>
               </div>
               <div class="tour-container" id="tourContainer">
                  <!-- 관광 정보 -->
               </div>
            </div>
         </div>
      </section>

      <div class="event-box">
         <div class="title-text">축제·행사</div>
         <div class="calendar">
            <hr>
            <div class="month-box">
               <div class="calendar-year">2024</div>
               <div class="month month-1" data-month="01">1월</div>
               <div class="month month-2" data-month="02">2월</div>
               <div class="month month-3" data-month="03">3월</div>
               <div class="month month-4" data-month="04">4월</div>
               <div class="month month-5" data-month="05">5월</div>
               <div class="month month-6" data-month="06">6월</div>
               <div class="month month-7" data-month="07">7월</div>
               <div class="month month-8" data-month="08">8월</div>
               <div class="month month-9" data-month="09">9월</div>
               <div class="month month-10" data-month="01">10월</div>
               <div class="month month-11" data-month="11">11월</div>
               <div class="month month-12" data-month="12">12월</div>
            </div>
         </div>
         <div class="event-text">
            <a href="/project/eventlist" class="load-more-btn" data-type="event">더보기</a>
         </div>    
         <div class="event-container" id="eventContainer">
            <!-- 행사 정보 -->
         </div>
      </div>
      
      <!-- footer -->
        <footer>
            <div class="footer-container">
                <div class="footer-img">
                    <div class="wave"></div>
                    <div class="wave"></div>
                </div>
                <div class="footer-text-box">
                    <div class="footer-text">
                        <div class="footer-text01">
                            <img src="${pageContext.request.contextPath}/resources/img/logo2.png" alt="logo-icon">  
                        </div>
                        <div class="footer-text02">
                            <div class="page-info">
                            <span><strong>㈜경로당 l</strong> 대표 윤라영</span>
                            <span><strong>주소 l</strong> 서울특별시 마포구 양화로 122 LAB7 빌딩 3층, 4층</span>
                            <span><strong>TEL l</strong> 02-123-4567</span>
                            <span><strong>FAX l</strong> 02-849-8462</span> <br>
                            <span><strong>EMAIL l</strong> rootdang@gmail.com</span>
                            <span><strong>사업자 등록번호 l</strong> 824-81-02515</span>
                            <span><strong>통신판매업 신고번호 l</strong> 2024-서울강남-02179</span>
                            
                            </div>
                        </div>
                        <div class="footer-text03">
                            <div class="copyright-info">
                                <span><img src="${pageContext.request.contextPath}/resources/img/footer-img.png" alt=""></span>
                                <span><img src="${pageContext.request.contextPath}/resources/img/footer-img01.png" alt=""></span>
                            </div>
                        </div>
                        <div class="footer-text04">
                            <div class="footer-social-icons footer-social-ins">
                                <img src="${pageContext.request.contextPath}/resources/img/footer-social-ins.png" alt="">
                            </div>
                            <div class="footer-social-icons footer-social-fb">
                                <img src="${pageContext.request.contextPath}/resources/img/footer-social-fb.png" alt="">
                            </div>
                            <div class="footer-social-icons footer-social-ytb">
                                <img src="${pageContext.request.contextPath}/resources/img/footer-social-ytb.png" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
   </div>

<script>
    $(document).ready(function() {
        function fetchData() {
            // Fetch event data
            $.ajax({
                url: "${pageContext.request.contextPath}/ajax/eventlist",
                type: "GET",
                success: function(data) {
                    updateEventList(data);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching event list:", error);
                }
            });
            // Fetch tourism data
            $.ajax({
                url: "${pageContext.request.contextPath}/ajax/tourlist",
                type: "GET",
                success: function(data) {
                    updateTourList(data);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching tour list:", error);
                }
            });
        }

        // Fetch initial data on page load
        fetchData();

        // Click handler for area selection
        $(".area").click(function() {
        	
            var selectedArea = $(this).data("area");
            $.ajax({
                url: "${pageContext.request.contextPath}/ajax/tourlist",
                type: "GET",
                data: {
                    area: selectedArea
                },
                success: function(data) {
                    updateTourList(data);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching tour list:", error);
                }
            });
            
            
            var regionValue;
            
            switch(selectedArea) {
                case 1:
                    regionValue = 0;
                    break;
                case 2:
                    regionValue = 1;
                    break;
                case 3:
                    regionValue = 2;
                    break;
                case 4:
                    regionValue = 3;
                    break;
                case 31:
                    regionValue = 4;
                    break;
                case 6:
                    regionValue = 5;
                    break;
                case 7:
                    regionValue = 6;
                    break;
                case 5:
                    regionValue = 7;
                    break;
                case 32:
                    regionValue = 8;
                    break;
                case 33:
                    regionValue = 9;
                    break;
                case 34:
                    regionValue = 10;
                    break;
                case 35:
                    regionValue = 11;
                    break;
                case 36:
                    regionValue = 12;
                    break;
                case 37:
                    regionValue = 13;
                    break;
                case 38:
                    regionValue = 14;
                    break;
                case 39:
                    regionValue = 15;
                    break;
                case 8:
                    regionValue = 16;
                    break;
                default:
                    regionValue = 0; // 기본값
            }

            
            $.ajax({
                url: "${pageContext.request.contextPath}/ajax/routeList",
                type: "GET",
                data: {
                    boardRegion: regionValue
                },
                success: function(data) {
                    updateRouteList(data);
                    data.forEach(function(Board) {
                        console.log("Title: " + Board.boardTitle);  // 각 객체의 boardTitle 출력
                    });
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching route list:", error);
                }
            });

            function updateRouteList(data) {
                var routeContainer = $(".route-items");
                routeContainer.empty(); // 기존의 내용을 비움
                
                data.forEach(function(board) {
                    var boardRegionName = getRegionName(board.boardRegion);
                    var boardImgReal = board.boardImgReal;
                    var boardImg = board.boardImgReal ? '<img class="boardImg" src="/project/board/images/' + boardImgReal + '" alt="Board Image">' : '<img class="boardImg" src="${pageContext.request.contextPath}/resources/img/blankimg.png" alt="Default Image">';
                  
                    var routeItem = 
                    	'<div class="route-item" onclick="location.href=\'${pageContext.request.contextPath}/board/route/' + board.boardCode + '\'">'+
                            '<div class="route-img-box">'+
                                boardImg+
                                '<div class="route-top-info">'+
                                    '<div class="views-info">'+
                                        '<div class="view-int">views<span>'+ board.boardViews +'</span></div>'+
                                    '</div>'+
                                    '<div class="user-info">'+
                                        '<div class="user-name"><span>'+ board.nickname +'</span></div>'+
                                    '</div>'+
                                '</div>'+
                            '</div>'+
                            '<div class="route-title">'+
                                '<div class="local-box">'+ boardRegionName +'</div>'+
                                '<div class="route-name-box">'+
                                    '<div class="route-name">'+ board.boardTitle +'</div>'+
                                '</div>'+
                            '</div>'+
                        '</div>';
                    
                    routeContainer.append(routeItem);
                });	
            }

            function getRegionName(region) {
                switch(region) {
                    case 0: return "서울";
                    case 1: return "인천";
                    case 2: return "대전";
                    case 3: return "대구";
                    case 4: return "경기";
                    case 5: return "부산";
                    case 6: return "울산";
                    case 7: return "광주";
                    case 8: return "강원";
                    case 9: return "충북";
                    case 10: return "충남";
                    case 11: return "경북";
                    case 12: return "경남";
                    case 13: return "전북";
                    case 14: return "전남";
                    case 15: return "제주";
                    case 16: return "세종";
                    default: return "서울";
                }
            }
        });

        // Click handler for month selection
        $(".month").click(function() {
            var selectedMonth = $(this).data("month");
            var selectedYear = new Date().getFullYear();
            $.ajax({
                url: "${pageContext.request.contextPath}/ajax/eventlist",
                type: "GET",
                data: {
                    year: selectedYear,
                    month: selectedMonth
                },
                success: function(data) {
                    updateEventList(data);
                },
                error: function(xhr, status, error) {
                    console.error("Error fetching event list:", error);
                }
            });
        });

        function processAddress(addr1) {
            if (addr1.startsWith('경상') || addr1.startsWith('전라') || addr1.startsWith('충청')) {
                return addr1.substring(0, 1) + addr1.substring(2, 3);
            }
            return addr1.substring(0, 2);
        }

        // Function to update tourism section
        function updateTourList(data) {
            $("#tourContainer").empty();
            $.each(data, function(i, item) {
                var processedAddr = processAddress(item.addr1);
                var tourItem = '<div class="tour-item content" data-contentid="' + item.contentid + '" data-contenttypeid="' + item.contenttypeid + '">' +
                                   '<div class="tour-img">' +
                                       '<img src="' + item.firstimage + '" alt="' + item.title + '">' +
                                   '</div>' +
                                   '<div class="tour-info">' +
                                       '<div class="local-box">' +
                                           processedAddr +
                                       '</div>' +
                                       '<div class="tour-name">' + item.title + '</div>' +
                                   '</div>' +
                               '</div>';
                $("#tourContainer").append(tourItem);
            });
        }

        // Function to update event section
        function updateEventList(data) {
            $("#eventContainer").empty();
            $.each(data, function(i, item) {
                var processedAddr = processAddress(item.addr1);
                var eventItem = '<div class="event-item content" data-contentid="' + item.contentid + '" data-contenttypeid="' + item.contenttypeid + '">' +
                                   '<div class="event-img">' +
                                       '<img src="' + item.firstimage + '" alt="' + item.title + '">' +
                                   '</div>' +
                                   '<div class="event-info">' +
                                       '<div class="local-box">' +
                                           processedAddr +
                                       '</div>' +
                                       '<div class="event-name">' + item.title + '</div>' +
                                       '<div class="event-tiem">' + item.eventstartdate + '~' + item.eventenddate + '</div>' +
                                   '</div>' +
                               '</div>';
                $("#eventContainer").append(eventItem);
            });
        }

        // Handler for load more button
        $(".load-more-btn").click(function() {
            var type = $(this).data("type");
            var url = type === "tour" ? "${pageContext.request.contextPath}/tourlist" : "${pageContext.request.contextPath}/eventlist";
            window.location.href = url;
        });

        // Handler for content click (assuming delegation due to dynamic content)
        $(document).on("click", ".content", function() {
            var contentId = $(this).data("contentid");
            var contentTypeId = $(this).data("contenttypeid");
            var url = "";

            if ($(this).hasClass("tour-item")) {
                url = "${pageContext.request.contextPath}//tourInfo?contentId=" + contentId + "&contentTypeId=" + contentTypeId;
            } else if ($(this).hasClass("event-item")) {
                url = "${pageContext.request.contextPath}//eventInfo?contentId=" + contentId + "&contentTypeId=" + contentTypeId;
            }

            window.location.href = url;
        });

        // Automatically select previously selected month and area
        var selectedMonth = "${selectedMonth}";
        var selectedArea = "${selectedArea}";
        $(".month[data-month='" + selectedMonth + "']").addClass("selected");
        $(".area[data-area='" + selectedArea + "']").addClass("selected");
    });
</script>


    <!-- index.js -->
   <script defer src="${pageContext.request.contextPath}/resources/js/home.js"></script>


</body>
</html>