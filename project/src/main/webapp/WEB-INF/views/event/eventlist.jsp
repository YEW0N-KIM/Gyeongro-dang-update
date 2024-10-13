<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>축제/행사</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/reset.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/eventlist.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.filter-keep-btn:hover, .filter-keep-btn.hovered {
	color: white;
	background-color: #E78181;
}

.filter-keep-btn.clicked {
	color: white;
	background-color: #E78181;
}

img {
	cursor: pointer;
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
					<div class="side-name">월</div>
					<div class="month-box">
						<div class="month filter-keep-btn" data-month="01">1월</div>
						<div class="month filter-keep-btn" data-month="02">2월</div>
						<div class="month filter-keep-btn" data-month="03">3월</div>
						<div class="month filter-keep-btn" data-month="04">4월</div>
						<div class="month filter-keep-btn" data-month="05">5월</div>
						<div class="month filter-keep-btn" data-month="06">6월</div>
						<div class="month filter-keep-btn" data-month="07">7월</div>
						<div class="month filter-keep-btn" data-month="08">8월</div>
						<div class="month filter-keep-btn" data-month="09">9월</div>
						<div class="month filter-keep-btn" data-month="10">10월</div>
						<div class="month filter-keep-btn" data-month="11">11월</div>
						<div class="month filter-keep-btn" data-month="12">12월</div>
					</div>
					<div class="filter-btn-box">
						<div class="filter-reset-btn filter-keep-btn" id="resetBtn">초기화</div>
					</div>
				</div>
			</div>
			<div class="section">
				<div class="section-name">
					# 축제/행사
					<hr>
				</div>
				<div class="content-box">
					<c:forEach var="item" items="${eventList}">
						<div class="content" data-contentid="${item.contentid}"
							data-contenttypeid="${item.contenttypeid}">
							<div class="content-img">
								<img src="${item.firstimage}">
							</div>
							<div class="content-info">
								<div class="info-name">${item.title}</div>
								<div class="info-local">${item.addr1}</div>
								<div class="info-local">${item.eventstartdate}~
									${item.eventenddate}</div>
							</div>
						</div>
					</c:forEach>
				</div>

				<div class="paging">
					<div class="more-btn-box">
						<c:if test="${currentPage > 5}">
							<a
								href="${pageContext.request.contextPath}/eventlist?pageNo=${currentPage - ((currentPage - 1) % 5) - 5}&month=${param.month}&area=${param.area}"
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
														href="${pageContext.request.contextPath}/eventlist?pageNo=${j}&month=${param.month}&area=${param.area}"
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
								href="${pageContext.request.contextPath}/eventlist?pageNo=${currentPage - ((currentPage - 1) % 5) + 5}&month=${param.month}&area=${param.area}"
								class="next-group">다음</a>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->
		<%@ include file="../footer-sub.jsp"%>
	</div>
	<script>
		$(document)
				.ready(
						function() {
							// URL 파라미터를 파싱하는 함수
							function getParameterByName(name) {
								const url = window.location.href;
								const nameRegex = name.replace(/[\[\]]/g,
										"\\$&");
								const regex = new RegExp("[?&]" + nameRegex
										+ "(=([^&#]*)|&|#|$)");
								const results = regex.exec(url);
								if (!results)
									return null;
								if (!results[2])
									return '';
								return decodeURIComponent(results[2].replace(
										/\+/g, " "));
							}

							// URL에서 area와 month 파라미터 값 가져오기
							const selectedArea = getParameterByName('area')
									|| "1"; // 기본값으로 서울 설정
							const selectedMonth = getParameterByName('month')
									|| ("0" + (new Date().getMonth() + 1))
											.slice(-2); // 현재 월 기본값 설정

							// 선택된 월 및 지역에 스타일 적용
							$(".filter-keep-btn").removeClass("clicked");
							$(
									".filter-keep-btn[data-area='"
											+ selectedArea + "']").addClass(
									"clicked");
							$(
									".filter-keep-btn[data-month='"
											+ selectedMonth + "']").addClass(
									"clicked");

							$(".filter-keep-btn.area")
									.click(
											function() {
												const clickedArea = $(this)
														.data("area");

												$(".filter-keep-btn.area")
														.removeClass("clicked");
												$(this).addClass("clicked");

												// 페이지 리다이렉트
												const selectedYear = new Date()
														.getFullYear();
												const selectedMonth = $(
														".filter-keep-btn.month.clicked")
														.data("month")
														|| ("0" + (new Date()
																.getMonth() + 1))
																.slice(-2);
												window.location.href = "${pageContext.request.contextPath}/eventlist?year="
														+ selectedYear
														+ "&month="
														+ selectedMonth
														+ "&area="
														+ clickedArea;
											});

							$(".filter-keep-btn.month")
									.click(
											function() {
												const clickedMonth = $(this)
														.data("month");

												$(".filter-keep-btn.month")
														.removeClass("clicked");
												$(this).addClass("clicked");

												// 페이지 리다이렉트
												const selectedYear = new Date()
														.getFullYear();
												const selectedArea = $(
														".filter-keep-btn.area.clicked")
														.data("area")
														|| "1";
												window.location.href = "${pageContext.request.contextPath}/eventlist?year="
														+ selectedYear
														+ "&month="
														+ clickedMonth
														+ "&area="
														+ selectedArea;
											});

							$("#resetBtn")
									.click(
											function() {
												$(".filter-keep-btn")
														.removeClass("clicked");
												window.location.href = "${pageContext.request.contextPath}/eventlist";
											});

							// 이벤트 목록 클릭 시 상세 페이지로 이동
							$(".content")
									.click(
											function() {
												const contentId = $(this).data(
														"contentid");
												const contentTypeId = $(this)
														.data("contenttypeid");
												window.location.href = "${pageContext.request.contextPath}/eventlist/eventInfo?contentId="
														+ contentId
														+ "&contentTypeId="
														+ contentTypeId;
											});

						});
	</script>

</body>
</html>
