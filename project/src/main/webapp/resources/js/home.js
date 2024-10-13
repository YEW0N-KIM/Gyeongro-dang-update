// 상단 지역 슬라이드 코드 //
let localPage = 0;
let localValue = 0;
const localWidth = 110; //수정

const localBackBtn = document.querySelector(".local-back");
const localNextBtn = document.querySelector(".local-next");
const localDivs = document.querySelector(".section-slide-items");

function localBack() {
    const maxLocalPages = window.innerWidth <= 1200 ? 11 : 8; // 화면 크기에 따라 다른 최대 페이지 수 설정
    if (localPage < maxLocalPages) {
        localBackBtn.removeAttribute('disabled');
        localValue -= localWidth;
        localDivs.style.transform = `translateX(${localValue}px)`;
        localPage += 1;
    }
    if (localPage === maxLocalPages) {
        localNextBtn.setAttribute('disabled', 'true');
    }
}

function localNext() {
    if (localPage > 0) {
        localNextBtn.removeAttribute('disabled');
        localValue += localWidth;
        localDivs.style.transform = `translateX(${localValue}px)`;
        localPage -= 1;
    }
    if (localPage == 0) {
        localBackBtn.setAttribute('disabled', 'true');
    }
}


// 상단 지역 아이콘 클릭 효과 //
document.addEventListener('DOMContentLoaded', function () {
        const slideItems = document.querySelectorAll('.slide-item img');

        slideItems.forEach(item => {
            item.addEventListener('click', function () {
                document.querySelectorAll('.slide-item img.clicked').forEach(img => img.classList.remove('clicked'));
                this.classList.add('clicked');
            });
        });
        
        // Set the first image as clicked by default
        slideItems[0].classList.add('clicked');
    });

document.addEventListener('DOMContentLoaded', function () {
    const slideItems = document.querySelectorAll('.slide-item');
    const localNames = document.querySelectorAll('.local-name');

    slideItems.forEach(item => {
        item.addEventListener('click', function () {
            const areaName = item.getAttribute('data-name');
            localNames.forEach(localName => {
                localName.textContent = areaName + ',';
            });
        });
    });
});


//section 2에 쓸 달력 코드
/* 현재 월 효과 입히기 */
var previousMonthBtn = null;

var now = new Date();
var month = now.getMonth() + 1;   

var monthClassName = "month-" + month;

var monthElement = document.querySelector("." + monthClassName);
if (monthElement) {
    monthElement.classList.add('clickEvent');
}
/* 클릭 월 효과 입히기*/
const months = document.querySelectorAll('.month');

months.forEach(month => {
    month.addEventListener('click', function() {
        months.forEach(m => m.classList.remove('clickEvent'));

        
        var monthId = this.id;
        this.classList.add('clickEvent');
    });
});


// 경로 제목 슬라이더 //
document.addEventListener("DOMContentLoaded", function() {

    const routeItems = document.querySelectorAll('.route-item');
    
    routeItems.forEach(function(item) {
        const routeImgBox = item.querySelector('.boardImg');
        const routeNameBox = item.querySelector('.route-name-box');
        const routeName = item.querySelector('.route-name');

        const routeNameWidth = routeName.offsetWidth;
        const routeNameBoxWidth = routeNameBox.offsetWidth;
        
        const moveDistance = routeNameBoxWidth - routeNameWidth;

        routeImgBox.addEventListener('mouseenter', function() {
        	if ( moveDistance < 0 ) {
            	routeName.style.transform = `translateX(${moveDistance}px)`;
        	} else {
            	routeName.style.transform = `translateX(0px)`;
        	}
        });
    });
});


function init() {  //초기 화면 상태
    localBackBtn.setAttribute('disabled', 'true');
    localBackBtn.addEventListener("click", localNext);
    localNextBtn.addEventListener("click", localBack);
}
init();
    