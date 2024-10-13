<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관광</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/tourlist.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.filter-keep-btn:hover, .filter-keep-btn.hovered, .filter-keep-btn.clicked
	{
	color: white;
	background-color: #E78181;
}

.info-name {
	white-space: nowrap;
	width: 230px;
	margin-top: 3.5px;
	overflow: hidden;
	position: relative;
}

.info-name span {
	display: inline-block;
	transition: transform 5s;
}

img {
	cursor: pointer;
}

.find-nearby {
	display: flex;
	align-items: center;
	cursor: pointer;
	font-size: 16px;
	float: right;
	font-weight: 350px;
}

.find-nearby img {
	width: 30px;
	height: 30px;
	margin-right: 5px;
}

.paging {
	width: 480px;
	margin: 0 auto;
	text-align: center;
	padding: 40px;
}

.paging .more-btn-box {
	display: inline-block;
}

.paging .page-number-box {
	display: inline-block;
}

.paging .page-number, .paging .current-page
	{
	padding: 0 15px;
	text-decoration: none;
	color: rgb(53, 53, 53);
}

.paging .prev-group, .paging .next-group {
	padding: 0 20px;
	text-decoration: none;
	color: rgb(53, 53, 53);
}

.paging .prev-group:hover, .paging .next-group:hover, .paging .page-number:hover
	{
	text-decoration: none;
	color: #d1d1d1;
}

.paging .current-page {
	color: rgb(53, 53, 53);
}
</style>

</head>
<body>
	<div class="wrap">
		<%@ include file="../nav.jsp"%>
		<div class="container">
			<div class="side-container">
				<div class="side">
					<div class="side-name">지역</div>
					<div class="local-box">
						<div class="area filter-keep-btn" data-area="1">서울</div>
						<div class="area filter-keep-btn" data-area="2">인천</div>
						<div class="area filter-keep-btn" data-area="3">대전</div>
						<div class="area filter-keep-btn" data-area="4">대구</div>
						<div class="area filter-keep-btn" data-area="31">경기</div>
						<div class="area filter-keep-btn" data-area="6">부산</div>
						<div class="area filter-keep-btn" data-area="7">울산</div>
						<div class="area filter-keep-btn" data-area="5">광주</div>
						<div class="area filter-keep-btn" data-area="32">강원</div>
						<div class="area filter-keep-btn" data-area="33">충북</div>
						<div class="area filter-keep-btn" data-area="34">충남</div>
						<div class="area filter-keep-btn" data-area="35">경북</div>
						<div class="area filter-keep-btn" data-area="36">경남</div>
						<div class="area filter-keep-btn" data-area="37">전북</div>
						<div class="area filter-keep-btn" data-area="38">전남</div>
						<div class="area filter-keep-btn" data-area="39">제주</div>
						<div class="area filter-keep-btn" data-area="8">세종</div>
					</div>
				</div>
			</div>
			<div class="section">
				<div class="section-name">
					# 관광지
					<div class="find-nearby arrange-option" data-arrange="S">
						<img
							src="${pageContext.request.contextPath}/resources/img/map-icon.gif"	alt="Map Icon"> 내 주변 관광지 찾기
					</div>
					<hr>
				</div>
				<div class="content-box">
					<c:forEach var="item" items="${tourList}">
						<div class="content" data-contentid="${item.contentid}"
							data-contenttypeid="${item.contenttypeid}">
							<div class="content-img">
								<img src="${item.firstimage}">
							</div>
							<div class="content-info">
								<div class="info-name">
									<span>${item.title}</span>
								</div>
								<div class="info-local">${item.addr1}</div>
								<div class="info-dist">${item.dist}</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="paging">
					<div class="more-btn-box">
						<c:if test="${currentPage > 5}">
							<a
								href="${pageContext.request.contextPath}/tourlist?pageNo=${currentPage - ((currentPage - 1) % 5) - 5}&arrange=${selectedArrange}&area=${area}&mapX=${mapX}&mapY=${mapY}"
								class="prev-group">이전</a>
						</c:if>
					</div>
					<div class="page-number-box">
						<c:forEach var="i" begin="1" end="${totalPages}" step="5">
							<c:choose>
								<c:when test="${currentPage >= i && currentPage < i + 5}">
									<c:forEach var="j" begin="${i}" end="${i + 4}"
										varStatus="status">
										<c:if test="${j <= totalPages}">
											<c:choose>
												<c:when test="${j == currentPage}">
													<span class="current-page">${j}</span>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/tourlist?pageNo=${j}&arrange=${selectedArrange}&area=${area}&mapX=${mapX}&mapY=${mapY}"
														class="page-number">${j}</a>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
								</c:when>
							</c:choose>
						</c:forEach>
					</div>
					<div class="more-btn-box">
						<c:if test="${currentPage + 5 <= totalPages}">
							<a
								href="${pageContext.request.contextPath}/tourlist?pageNo=${currentPage - ((currentPage - 1) % 5) + 5}&arrange=${selectedArrange}&area=${area}&mapX=${mapX}&mapY=${mapY}"
								class="next-group">다음</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../footer-sub.jsp"%>
	</div>
	<script
		src="${pageContext.request.contextPath}/resources/js/textSlide.js"
		defer></script>
	<script>
$(document).ready(function() {
    // 기본값 설정
    var defaultArea = "${param.area != null ? param.area : '1'}";
    var defaultArrange = "${param.arrange != null ? param.arrange : 'Q'}";
    
    // 초기 로드 시 클릭 상태 설정
    if (defaultArrange === 'S') {
        $(".filter-keep-btn").removeClass("clicked");
    } else {
        $(".filter-keep-btn[data-area='" + defaultArea + "']").addClass("clicked");
    }

    // 거리 정보 표시 및 거리순 정렬 유지
    function displayDistanceAndSort() {
        $('.content').each(function() {
            var distElement = $(this).find('.info-dist');
            var distanceText = distElement.text().trim();

            if (!isNaN(parseFloat(distanceText))) {
                var distanceInKm = parseFloat(distanceText) / 1000; // km 단위로 변환
                if (distanceInKm > 0) {
                    distElement.removeClass('hidden');
                    distElement.text(distanceInKm.toFixed(1) + ' km');
                } else {
                    distElement.addClass('hidden');
                }
            } else {
                distElement.addClass('hidden');
            }
        });
    }

    displayDistanceAndSort();

    // 정렬 옵션 클릭 시 동작
    $(".arrange-option").click(function() {
        $(".arrange-option, .filter-keep-btn").removeClass("clicked"); // 모든 정렬 및 지역 버튼에서 clicked 클래스 제거
        $(this).addClass("clicked"); // 현재 클릭한 정렬 옵션에 clicked 클래스 추가
        var arrange = $(this).data("arrange");

        if (arrange === "S") {
            $(".filter-keep-btn").removeClass("clicked"); // 거리순일 때 모든 지역 버튼의 clicked 클래스 제거
            navigator.geolocation.getCurrentPosition(function(position) {
                var mapX = position.coords.longitude;
                var mapY = position.coords.latitude;
                var url = "${pageContext.request.contextPath}/tourlist?pageNo=1&arrange=S&mapX=" + mapX + "&mapY=" + mapY;
                window.location.href = url;
            });
        } else if (arrange === "Q") {
            var area = $(".filter-keep-btn[data-area].clicked").data("area") || "1"; // 기본 서울로 설정
            var url = "${pageContext.request.contextPath}/tourlist?pageNo=1&arrange=Q&area=" + area;
            window.location.href = url;
        }
    });

    // 초기화 버튼 클릭 이벤트 핸들러
    $("#resetBtn").click(function() {
        $(".arrange-option, .filter-keep-btn").removeClass("clicked");
        window.location.href = "${pageContext.request.contextPath}/tourlist";
    });

    // 지역 버튼 클릭 이벤트 핸들러
    $(".area").click(function() {
        $(".area").removeClass("clicked");
        $(this).addClass("clicked");
        var clickedArea = $(this).data("area");
        var url = "${pageContext.request.contextPath}/tourlist?pageNo=1&arrange=Q&area=" + clickedArea;
        window.location.href = url;
    });

    // 콘텐츠 클릭 시 동작
    $(".content").click(function() {
        var contentId = $(this).data("contentid");
        var contentTypeId = $(this).data("contenttypeid");
        window.location.href = "${pageContext.request.contextPath}/tourlist/tourInfo?contentId=" + contentId + "&contentTypeId=" + contentTypeId;
    });
});
</script>
</body>
</html>
