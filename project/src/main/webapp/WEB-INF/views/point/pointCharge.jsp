<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../nav.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트 충전</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/reset.css" >
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/pointCharge.css" >
<script defer src="${pageContext.request.contextPath}/resources/js/pointChar.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
    $("#chargeBtn").click(function(){
        var amount = $("input[name='pointAmount']:checked").val();
        $.ajax({
            type: "POST",
            url: "charge_process.do",
            data: { pointAmount: amount },
            success: function(response){
                alert("충전 성공");
                window.location.href = "${pageContext.request.contextPath}/user/showMyPoint.do";
            },
            error: function(){
                alert("오류 발생");
            }
        });
    });
});
</script>

</head>
<body>

  <!-- 본문 --> 
    <div class="ptc_wrap">
         <!-- 왼쪽 네비 위-->
 <div class="pt_nav">
    <div class="pt_nav_up">
        <div class="pt_nav"></div>
        <div class="pt_nav_h2"><h2>${user.nickname}</h2></div>
        <div class="pt_nav_pt"><span>포인트</span> <span style="color: rgba(253, 177, 177, 0.924);">${user.userPoint}</span></div>
    </div>
    <div class="pt_nav_down">
        <div class="pt_nav_char"><h1>전체내역</h1>
         <a href="#"> <p>사용내역</p> </a>
         <a href="#"> <p>획득내역</p> </a>
        </div>
        <div class="pt_nav_all"><h1>충전/환전</h1>
        <a href="pointchar.html"> <p>충전하기</p></a>
        <a href="#"> <p>환전하기</p></a>
        </div>
    </div>
</div>
<!-- 나의포인트 -->
        <div class="ptc_header" id="content1">
            <div class="ptc_h3"><h3>${user.nickname}님의 보유 포인트</h3></div>
            <div class="ptc_point"> <span style="font-size:x-large; color:blue; ">${user.userPoint}</span></div>
           <div class="ptc_btn"><button class="open" id="openModal">포인트 충전 </button></div>  
        </div>

        <div class="ptc_content" id="content2">
            <div class="ptc_title">포인트 충전 내역</div>
            <div class="ptc_categori">
                <div class="ptc_date1"></div>
                <div class="ptc_date2"></div>
                <input class="ptc_search" placeholder="    사용내역을 입력하세요"></input>
                <button class="ptc_cont_btn">검색</button>
            </div>



    <!-- 모달 -->
    <div id="modal" class="modal">
        <div class="modal-content">
            <div class="tab-container">
                <div id="tab1" class="tab selected">충전 포인트 선택</div>
                <div id="tab2" class="tab">결제 수단 선택</div>
            </div>
            <form id="form1" class="form">
                <div class="point-selection">
                    <label for="points" class="points-label">보유 포인트: ${user.userPoint}p</label>
                    <div class="points">
                        <input type="radio" id="1000p" name="pointAmount" value="1000">
                        <label for="1000p" class="point-btn">1000p</label>
                        <input type="radio" id="5000p" name="pointAmount" value="5000">
                        <label for="5000p" class="point-btn">5000p</label>
                        <input type="radio" id="10000p" name="pointAmount" value="10000">
                        <label for="10000p" class="point-btn">10,000p</label>
                        <input type="radio" id="50000p" name="pointAmount" value="50000">
                        <label for="50000p" class="point-btn">30,000p</label>
                        <input type="radio" id="100000p" name="pointAmount" value="100000">
                        <label for="100000p" class="point-btn">50,000p</label>
                        <input type="radio" id="300000p" name="pointAmount" value="300000">
                        <label for="300000p" class="point-btn">100,000p</label>
                    </div>
                </div>
                <div class="buttons">
                    <button type="button" class="btn" id="cancel">취소</button>
                    <button type="button" class="btn" id="next">다음</button>
                </div>
            </form>
            <form id="form2" class="form" style="display: none;">
                <div class="payment-method">
                    <label for="payment">결제 수단을 선택하세요:</label>
                    <div class="payment-options">
                        <div class="payment-row">
                            <input type="radio" id="credit" name="payment" value="credit">
                            <label for="credit" class="payment-btn">
                                <img src="${pageContext.request.contextPath}/resources/img/credit-card.png" alt="Credit Card">
                                신용카드 결제
                            </label>
                            <input type="radio" id="bank" name="payment" value="bank">
                            <label for="bank" class="payment-btn">
                                <img src="${pageContext.request.contextPath}/resources/img/bank-transfer.png" alt="Bank Transfer">
                                무통장입금
                            </label>
                        </div>
                        <div class="payment-row">
                            <input type="radio" id="phone" name="payment" value="phone">
                            <label for="phone" class="payment-btn">
                                <img src="${pageContext.request.contextPath}/resources/img/mobile-payment.png" alt="Mobile Payment">
                                휴대폰 결제
                            </label>
                            <input type="radio" id="simple" name="payment" value="simple">
                            <label for="simple" class="payment-btn">
                                <img src="${pageContext.request.contextPath}/resources/img/simple-payment.png" alt="Simple Payment">
                                간편결제
                            </label>
                        </div>
                    </div>
                </div>
                <div class="buttons">
                    <button type="button" class="btn" id="cancel">취소</button>
                    <button type="button" class="btn" id="back">이전</button>
                    <button type="submit" class="btn" id="chargeBtn">완료</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>