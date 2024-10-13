
document.addEventListener("DOMContentLoaded", function() {
    var routeItems = document.querySelectorAll('td.route-info');
	
    routeItems.forEach(function(item) {
        //console.log("test");
       	const textBox = item.querySelector('.route-name');
       	const text = item.querySelector('.route-name span');

       	const textBoxW = textBox.offsetWidth;
       	const textW = text.offsetWidth;
       	
       	const wpqkftq = textBoxW - textW;
       	
       	item.addEventListener('mouseenter', function() {
       	    console.log("textBoxW : " + textBoxW);
       	    console.log("textW : " + textW);
       	    console.log("wpqkftq : " + wpqkftq);

       	    if (wpqkftq < 0) {
       	        text.style.transform = `translateX(${wpqkftq}px)`;
       	        
       	    } else {
       	        text.style.transform = `translateX(0px)`;
       	    }
       	});
       	item.addEventListener('mouseleave', function() {
		    text.style.transform = `translateX(0px)`;
		});
    });
});

    
    
    var localBtns = document.querySelectorAll('.local');
    var themeBtns = document.querySelectorAll('.theme');
    var tourdaysBtns = document.querySelectorAll('.tourdays');
    var resetBtn = document.querySelector('#resetBtn');
    var searchBtn = document.querySelector('#searchBtn');

    var selectedRegion = null;
    var selectedTheme = null;
    var selectedTourdays = null;

    var previousLocalBtn = null;
    var previousthemeBtn = null;
    var previoustourdaysBtn = null;
    
    
    function localBtnEvent() {
        this.classList.add('clickEvent');
        selectedRegion = this.id.replace('local', '');
        if (previousLocalBtn && previousLocalBtn !== this) {
            previousLocalBtn.classList.remove('clickEvent');
        }

        previousLocalBtn = this;

    }

    function themeBtnEvent() {
        this.classList.add('clickEvent');
        selectedTheme = this.id.replace('theme', ''); 
        if (previousthemeBtn && previousthemeBtn !== this) {
           previousthemeBtn.classList.remove('clickEvent');
        }

        previousthemeBtn = this;
    }

    function tourdaysBtnEvent() {
        this.classList.add('clickEvent');
        selectedTourdays = this.id.replace('tourdays', '');
        if (previoustourdaysBtn && previoustourdaysBtn !== this) {
           previoustourdaysBtn.classList.remove('clickEvent');
        }

        previoustourdaysBtn = this;
    }

    function resetBtnEvent() {
       window.location.href = '/project/board/route';

        selectedRegion = null;
        selectedTheme = null;
        selectedTourdays = null;
    }

    function searchBtnEvent() {
        var url = "/project/board/route?";
        if (selectedRegion) {
            url += "region=" + selectedRegion + "&";
        } else {
           url += "region=&";   
        }
        
        if (selectedTheme) {
            url += "theme=" + selectedTheme + "&";
        } else {
           url += "theme=&";
        }
        if (selectedTourdays) {
            url += "tourdays=" + selectedTourdays;
        } else {
           url += "tourdays=&";
        }
        if (url.endsWith("&")) {
            url = url.slice(0, -1); // 맨 끝의 & 제거
        }
        window.location.href = url;
    }

    localBtns.forEach(function(btn) {
        btn.addEventListener("click", localBtnEvent);
    });

    themeBtns.forEach(function(btn) {
        btn.addEventListener("click", themeBtnEvent);
    });

    tourdaysBtns.forEach(function(btn) {
        btn.addEventListener("click", tourdaysBtnEvent);
    });

    resetBtn.addEventListener("click", resetBtnEvent);
    searchBtn.addEventListener("click", searchBtnEvent);
    
    function searchCheck() {
        var condition = document.getElementsByName("condition")[0].value;
        if (condition === "") {
            alert("검색 조건을 선택해주세요.");
            return false;
        }
        return true;
    }

    