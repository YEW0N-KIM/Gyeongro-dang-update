<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포인트 환전</title>
</head>
<script>
$(document).ready(function(){
    var errorMessage = "${errorMessage}";
    if (errorMessage != null) {
        alert(errorMessage);
    }
});

</script>
<body>
당신의 포인트는 ${user.userPoint}P입니다.<br>
2000 포인트부터 환전 가능합니다.<br><br>

<form name="exchangeForm" method="post" action="exchange_process.do">
    환전할 포인트: 
    <input type="number" name="exchangeAmount" min="2000" value="2000" required>
    <br><br>
    <button type="exchangeBtn">환전 신청</button>
</form>

</body>
</html>
