document.addEventListener("DOMContentLoaded", function() {
    // 페이지 로드 시 모달이 자동으로 열리지 않도록 설정
    document.getElementById('modal').style.display = 'none';
    document.getElementById('modal2').style.display = 'none';

    // 모달 열기 버튼에 클릭 이벤트 핸들러 등록
    document.getElementById('openModal').onclick = function() {
        document.getElementById('modal').style.display = 'flex';
        resetForms();
    };
    document.getElementById('openModal2').onclick = function() {
        document.getElementById('modal2').style.display = 'flex';
        resetForms();
    };

    // 다음 버튼에 클릭 이벤트 핸들러 등록
    document.getElementById('next').onclick = function() {
        const selectedPoint = document.querySelector('input[name="pointAmount"]:checked');
        if (!selectedPoint) {
            alert("충전할 금액을 선택하세요.");
            return;
        }
        document.getElementById('form1').style.display = 'none';
        document.getElementById('form2').style.display = 'block';
        document.getElementById('tab1').classList.remove('selected');
        document.getElementById('tab2').classList.add('selected');
    };

    document.getElementById('next2').onclick = function() {
        const selectedPoint = document.querySelector('input[name="extend"]:checked');
        if (!selectedPoint) {
            alert("환전할 금액을 선택하세요.");
            return;
        }
        document.getElementById('form3').style.display = 'none';
        document.getElementById('form4').style.display = 'block';
        document.getElementById('tab3').classList.remove('selected2');
        document.getElementById('tab4').classList.add('selected2');
    };

    // 뒤로 가기 버튼에 클릭 이벤트 핸들러 등록
    document.getElementById('back').onclick = function() {
        document.getElementById('form2').style.display = 'none';
        document.getElementById('form1').style.display = 'block';
        document.getElementById('tab2').classList.remove('selected');
        document.getElementById('tab1').classList.add('selected');
    };

    document.getElementById('back2').onclick = function() {
        document.getElementById('form4').style.display = 'none';
        document.getElementById('form3').style.display = 'block';
        document.getElementById('tab4').classList.remove('selected2');
        document.getElementById('tab3').classList.add('selected2');
    };

    // 취소 버튼에 클릭 이벤트 핸들러 등록
    document.querySelectorAll('#cancel').forEach(btn => {
        btn.onclick = function() {
            document.getElementById('modal').style.display = 'none';
            resetForms(); // 취소 버튼을 누를 때 초기화
        }
    });

    document.querySelectorAll('#cancel2').forEach(btn => {
        btn.onclick = function() {
            document.getElementById('modal2').style.display = 'none';
            resetForms(); // 취소 버튼을 누를 때 초기화
        }
    });

    document.getElementById('chargeBtn').onclick = function() {
        const selectedpay = document.querySelector('input[name="payment"]:checked');
        if (!selectedpay) {
            alert("결제 방식을 선택하세요.");
            return;
        }

        var amount = $("input[name='pointAmount']:checked").val();
        $.ajax({
            type: "POST",
            url: "/project/point/charge_process.do",
            data: { pointAmount: amount },
            success: function(response){
                $(".my_point_text").html(response + "P");
                alert("충전되었습니다");
            },
            error: function(){
            }
        });

        document.getElementById('modal').style.display = 'none';
    };

    document.getElementById('chargeBtn2').onclick = function() {
        const accountNumber = document.getElementById('account-number').value;
        if (accountNumber === '') {
            alert("계좌번호를 입력해주세요");
            return;
        }

        const accountHolder = document.getElementById('account-holder').value;
        if (accountHolder === '') {
            alert("예금주명을 입력해주세요");
            return;
        }

        var amount2 = $("input[name='extend']:checked").val();
        $.ajax({
            type: "POST",
            url: "/project/point/exchange_process.do",
            data: { exchangeAmount: amount2 },
            success: function(response){
                $(".my_point_text").html(response + "P");
                alert("환전되었습니다");
            },
            error: function(){
                alert("오류 발생");
            }
        });

        document.getElementById('modal2').style.display = 'none';
    };

    // 폼 전환
    function switchToForm2() {
        const selectedPoint = document.querySelector('input[name="pointAmount"]:checked');
        if (!selectedPoint) {
            alert("충전할 금액을 선택하세요.");
            return;
        }

        form1.style.display = "none";
        form2.style.display = "block";
        tab1.classList.remove("selected");
        tab2.classList.add("selected");
    }

    function switchToForm1() {
        form1.style.display = "block";
        form2.style.display = "none";
        tab1.classList.add("selected");
        tab2.classList.remove("selected");
    }

    function switchToForm4() {
        const selectedPoint = document.querySelector('input[name="extend"]:checked');
        if (!selectedPoint) {
            alert("환전할 금액을 선택하세요.");
            return;
        }

        form3.style.display = "none";
        form4.style.display = "block";
        tab3.classList.remove("selected2");
        tab4.classList.add("selected2");
    }

    function switchToForm3() {
        form3.style.display = "block";
        form4.style.display = "none";
        tab3.classList.add("selected2");
        tab4.classList.remove("selected2");
    }

    tab1.addEventListener("click", switchToForm1);
    tab2.addEventListener("click", switchToForm2);
    tab3.addEventListener("click", switchToForm3);
    tab4.addEventListener("click", switchToForm4);

    // 폼 초기화 함수 호출
    resetForms();
});

function resetForms() {
    document.getElementById('form1').style.display = 'block';
    document.getElementById('form2').style.display = 'none';
    document.getElementById('tab1').classList.add('selected');
    document.getElementById('tab2').classList.remove('selected');

    document.getElementById('form3').style.display = 'block';
    document.getElementById('form4').style.display = 'none';
    document.getElementById('tab3').classList.add('selected2');
    document.getElementById('tab4').classList.remove('selected2');

    document.querySelectorAll('input[type="radio"]').forEach(radio => {
        radio.checked = false;
    });

    document.getElementById('account-number').value = '';
    document.getElementById('account-holder').value = '';
}
//<!-- 탈퇴 팝업 -->
   function deleteAccount() {

      url = "deleteInfo.do"
      open(
            url,
            "confirm",
            "menubar=no, statusbar=no, scrollbar=no, width=450, height=600, left=600, top=350");
   }

   function reload() {
      setTimeout(function() {
         location.reload();
      }, 3000);
   }

   function goHome() {
         window.location.href = "/project/"
   }


   function handleOnInput(el, maxlength) {
        if(el.value.length > maxlength)  {
          el.value 
            = el.value.substr(0, maxlength);
        }
   }


   document.getElementById("account-holder").onkeyup = function(e){
     var v = this.value;
     this.value = v.replace(/[^가-힣a-zA-Z\s]/gi, '');
   }
   
   
      /* 페이징 */
      document.addEventListener("DOMContentLoaded", function() {
       
       // 현재 URL 가져오기
       var currentURL = window.location.href;
   
       // 페이지 번호 클릭 시 bold 스타일 적용
       var pagingLinks = document.querySelectorAll(".paging_i");
       
       function activateLink(link) {
           pagingLinks.forEach(function(link) {
               link.classList.remove("active");
           });
           link.classList.add("active");
       }
   
       pagingLinks.forEach(function(link) {
           link.addEventListener("click", function() {
               activateLink(this);
           });
       });
   
       // 현재 URL에서 페이지 번호를 추출하여 해당 링크에 active 클래스 추가
       var pageParts = currentURL.split("page=");
       if (pageParts.length > 1) {
           var currentPage = pageParts[1].split("&")[0];
           pagingLinks.forEach(function(link) {
               if (link.href.includes("page=" + currentPage)) {
                   link.classList.add("active");
               }
           });
       }
   });
   