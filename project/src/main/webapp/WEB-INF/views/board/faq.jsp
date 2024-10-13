<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>고객센터</title>
    
    <!-- reset.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
    <!-- faq.css -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/faq.css" >
    
    
    <!-- Kakao 상담 api  -->
   <script  src="https://t1.kakaocdn.net/kakao_js_sdk/2.7.2/kakao.min.js" integrity="sha384-TiCUE00h649CAMonG018J2ujOgDKW/kVWlChEuu4jK2vxfAAD0eZxzCKakxg55G4" crossorigin="anonymous"
   ></script>
   <script>
        Kakao.init('5641c0ed7d6b511cb5b239ea2542a2be'); // 사용하려는 앱의 JavaScript 키 입력
   </script>
   <script>
      function chatChannel() {
         Kakao.Channel.chat({
            channelPublicId: '_sExlLG', 
         });
      }
   </script>
   
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="wrap">
    
        <!-- nav -->
        <%@ include file="../nav.jsp" %>
        
        <div class="container">
        	<!-- icon 컨테이너 -->
            <div class="iconContainer">
	            <div class="faqIcon" onclick="location.href='/project/board/inquiry'"><img src="${pageContext.request.contextPath}/resources/img/일대일문의.png" alt=""><h4>일대일문의</h4></div>
	            <div class="faqIcon" onclick="chatChannel()"><img src="${pageContext.request.contextPath}/resources/img/free-icon-communication-7182345.png" alt=""><h4>상담사 연결</h4></div>
	        </div>
	        
	        <div class="faq-container">
	            <div class="faqList">
	                <div class="faq-title">
	                    <div class="index">분류</div><div class="index-title">제목</div>
	                </div>
	            </div>

         		<div class="accordion-wrap">
	                <div class="question">
	                    <span id="acco-index">결제</span>
	                    <span id="acco-title">포인트 충전은 어떻게 하나요?</span>
	                </div>
	                <div class="answer">
	                    <span>포인트 충전은 마이페이지 > 포인트 충전에서 하실 수 있습니다.<br>
	                        포인트 충전 최소 금액은 1,000원으로 계좌 입금을 통한 충전만 가능합니다.
	                    </span>
	                </div>
	                <div class="question">
	                    <span id="acco-index">결제</span>
	                    <span id="acco-title">포인트 충전이 안 돼요.</span>
	                </div>
	                <div class="answer">
	                    <span>포인트 충전은 마이페이지 > 포인트 충전에서 하실 수 있습니다. <br> 
	                    만일 충전이 안 될 경우, 일대일 문의를 통해 오류 스크린샷 첨부 부탁드립니다.
	                    </span>
	                </div>
	                <div class="question">
	                    <span id="acco-index">결제</span>
	                    <span id="acco-title">포인트 환전은 어떻게 하나요?</span>
	                </div>
	                <div class="answer">
	                    <span>포인트 환전은 최소 5,000포인트 이상부터 가능합니다.<br>
	                        마이페이지 > 포인트환전에서 진행 가능합니다. </span>
	                </div>
	                <div class="question">
	                    <span id="acco-index">결제</span>
	                    <span id="acco-title">충전한 포인트를 환불하고 싶어요.</span>
	                </div>
	                <div class="answer">
	                    <span>포인트 최소 충전은 1,000원부터이고 환전은 5,000원 이상부터 가능합니다.</span>
	                </div>
	                <div class="question">
	                    <span id="acco-index">결제</span>
	                    <span id="acco-title">포인트 선물이 가능한가요?</span>
	                </div>
	                <div class="answer">
	                    <span>아직 포인트 선물은 불가능합니다.</span>
	                </div>
	                <div class="question">
	                    <span id="acco-index">계정</span>
	                    <span id="acco-title">회원 정보를 수정하고 싶습니다.</span>
	                </div>
	                <div class="answer">
	                    <span> 회원 정보는 마이페이지에서 수정하실 수 있습니다. 아이디는 변경 불가하오니 주의 바랍니다. </span>
	                </div>
	                <div class="question">
	                    <span id="acco-index">계정</span>
	                    <span id="acco-title">회원 탈퇴를 하고 싶어요.</span>
	                </div>
	                <div class="answer">
	                    <span>회원 탈퇴는 마이페이지 > 회원 탈퇴에서 진행하실 수 있습니다. <br> 보유하신 포인트와 계정 정보는 탈퇴 시 복구가 불가능하니 주의 바랍니다.</span>
	                </div>
	                <div class="question">
	                    <span id="acco-index">신고</span>
	                    <span id="acco-title">글 신고는 어떻게 하나요?</span>
	                </div>
	                <div class="answer">
	                    <span>글 신고는 페이지 별 신고 버튼을 통해 가능합니다.</span>
	                </div>
            	</div>    
        	</div>  
        </div>
        
		<!-- footer -->
<%@ include file="../footer-sub.jsp" %>
    </div>
    
    <script>
    $(document).ready(function() {
        $(".question").click(function() {
            $(this).next(".answer").stop().slideToggle(300);
            $(this).toggleClass('on').siblings().removeClass('on');
            $(this).next(".answer").siblings(".answer").slideUp(300);
        });
    });
    </script>
</body>
</html>
