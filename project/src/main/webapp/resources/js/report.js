function report() {
    var boardUserCode = document.querySelector('.boardUsercode').value;
    var userCode = document.querySelector('.userCode').value;
    var boardCode = document.querySelector('.boardCode').value;

    var reportingUserCode = $("#regUserCode").val() ? $("#regUserCode").val() : null;
    var reportContent = $("#reportContent").val() ? $("#reportContent").val() : null;

    var report = {
        reportCode: null,
        boardCode: boardCode,
        reportedUserCode: boardUserCode,
        reportingUserCode: reportingUserCode,
        reportDate: null,
        reportContent: reportContent
    };

    console.log(report);

    var reportString = JSON.stringify(report);
    console.log(reportString);

    $.ajax({
    type: "post",
    url: "/project/board/free/" + boardCode + "/report/", 
    data: reportString,
    contentType: "application/json",
    success: function(response) {
        alert("신고되었습니다.");
        window.location.href = response.redirect;
    },
    error: function(xhr, status, error) {
        console.log("오류");
        window.location.href = response.redirect;
    }
	});
}