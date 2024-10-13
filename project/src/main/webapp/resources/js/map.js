
// html button

var line = document.getElementById('line');
line.addEventListener('click',function(){
    lineCheck(markers);
});

var linedelete = document.getElementById('linedelete');
linedelete.addEventListener('click',function(){
    deleteAll();
});


// 검색 버튼 애니메이션
// DOM 요소를 가져옵니다.
const myButton = document.getElementById('myButton');
const searchTab = document.getElementById('searchTab');

// myButton을 클릭할 때 실행되는 함수를 정의합니다.
myButton.addEventListener('click', () => {
    // searchTab이 보이는 상태라면
    if (window.getComputedStyle(searchTab).display !== 'none') {
        // 애니메이션을 적용하여 숨깁니다.
        searchTab.style.opacity = '0';
        searchTab.style.transform = 'translateX(-100%)';
        // 애니메이션이 완료된 후에 요소를 숨깁니다.
        setTimeout(() => {
            searchTab.style.display = 'none';
        }, 500);
    } else {
        // searchTab이 숨겨져 있다면 보이도록 애니메이션을 적용합니다.
        searchTab.style.display = 'block';
        setTimeout(() => {
            searchTab.style.opacity = '1';
            searchTab.style.transform = 'translateX(0)';
        }, 10);
    }
});





// 카카오 맵 API
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
var ps = new kakao.maps.services.Places();   // 장소 검색 객체를 생성합니다

var linePath = [];
var polylines = [];
var polyline;

var newMarkers=[];
var markers = [];       //마커 객체 배열 생성
var selectedMarkers = []; //이전에 선택한 마커들을 저장할 배열
var listmarkers = [];   // 검색 목록 배열
var infowindow = new kakao.maps.InfoWindow({zIndex:1});    // 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
var infowindows = [];

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다


kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    var position = mouseEvent.latLng;
    addMarkerArr(position);
    console.log('position :' + position);

    addInfoWindowArr(position);
    

    // 마커와 관련된 작업 수행
    for (let i = 0; i < markers.length; i++) {
        console.log(i + "번째 : " + markers[i]);
        console.log(infowindows[i]);
    }   
});



function addMarkerArr(position){
    // 위치 값이 이미 markers 배열에 있는지 확인
    var positionExists = markers.some(function(markerPosition) {
        return markerPosition.equals(position);
    });

    if (positionExists) {
        // 이미 있는 위치 값이면 알림 창 띄우기
        alert("해당 위치의 마커가 이미 추가되어 있습니다!");
    } else {
        var emptyIndices = findEmptyIndices(markers); // 삭제된 위치의 인덱스를 찾습니다
        var emptyInfoWindowIndex = findEmptyIndices(infowindows);

        if (emptyIndices.length > 0) {
            // 삭제된 마커 위치가 있는 경우
            var emptyIndex = emptyIndices[0]; // 첫 번째 삭제된 위치를 사용합니다
            markers[emptyIndex] = position; // 해당 인덱스에 새로운 position을 할당합니다
            addMarker(position); // 해당 위치에 마커를 추가합니다
        } else {
            // 삭제된 마커 위치가 없는 경우
            markers.push(position); // 배열에 그냥 추가합니다
            addMarker(position); // 해당 위치에 마커를 추가합니다
        }
    }
}

function addInfoWindowArr(position) {
    // 이미 존재하는지 확인
    var alreadyExists = infowindows.some(info => info.latitude === position.getLat() && info.longitude === position.getLng());
    
    // 이미 존재하면 추가하지 않음
    if (alreadyExists) {
        return;
    }

    // 배열의 빈 요소를 찾고 값을 할당
    var emptyIndex = infowindows.findIndex(element => typeof element === 'undefined');
    if (emptyIndex !== -1) {
        infowindows[emptyIndex] = { title: "", latitude: position.getLat(), longitude: position.getLng() };
        positionInfo(emptyIndex);
    } else {
        infowindows.push({ title: "", latitude: position.getLat(), longitude: position.getLng() });
        positionInfo(infowindows.length - 1);
    }
    
    displayPositionInfo(position);
}


function addInfoWindowArr2(position, title) {
    // 이미 존재하는지 확인
    var alreadyExists = infowindows.some(info => info.latitude === position.getLat() && info.longitude === position.getLng());
    
    // 이미 존재하면 추가하지 않음
    if (alreadyExists) {
        return;
    }

    // 배열의 빈 요소를 찾고 값을 할당
    var emptyIndex = infowindows.findIndex(element => typeof element === 'undefined');
    if (emptyIndex !== -1) {
        infowindows[emptyIndex] = { title: title, latitude: position.getLat(), longitude: position.getLng() };
        positionInfo(emptyIndex);
    } else {
        infowindows.push({ title: title, latitude: position.getLat(), longitude: position.getLng() });
        positionInfo(infowindows.length - 1);
    }
}


function addMarker(position) {
    var index = markers.indexOf(position); // 배열에서 해당 마커의 인덱스 찾기
    var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
        imgOptions =  {
            spriteSize : new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
            spriteOrigin : new kakao.maps.Point(0, (index*46)+10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
            offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage 
        });

    displayPositionInfo(position,marker);
    marker.setMap(map); // 지도 위에 마커를 표출합니다
    newMarkers.push(marker);
    //positionInfo(index);

    // 마커에 마우스 이벤트 추가
    kakao.maps.event.addListener(marker, 'mouseover', function() {
    var markerIndex = markers.indexOf(position); // 해당 마커의 인덱스 찾기
   
    if (markerIndex !== -1) {
        var infowindow = infowindows[markerIndex]; // 해당 인덱스의 인포윈도우 가져오기
        var positionLatLng = new kakao.maps.LatLng(position.getLat(), position.getLng());
        // Reverse Geocoding을 통해 주소 가져오기
        reverseGeocoding(positionLatLng, function(address) {
            var title = infowindow.title;
            var content = title ? title : address; // title이 있으면 title, 없으면 주소 표시
            displayInfowindow(marker, content); // 인포윈도우 열기
        });
    }
});



    kakao.maps.event.addListener(marker, 'mouseout', function() {
        infowindow.close(); // 마우스가 마커에서 벗어나면 인포윈도우 닫기
    });

    kakao.maps.event.addListener(marker, 'click', function(mouseEvent) {  

        if (index !== -1) {
            marker.setMap(null); // 지도에서 숨기기
            delete markers[index]; // 해당 인덱스의 요소를 삭제
            delete infowindows[index];
            positionInfoDel(index);
            console.log("제거 후:"+markers)
            clearLines();
        }
});

    return marker;
}


function findEmptyIndices(arr) {
    var emptyIndices = [];
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] === undefined || arr[i] === null || arr[i] === '') {
            emptyIndices.push(i);
        }
    }
    return emptyIndices;
}







// 목록 검색
// 키워드로 장소를 검색합니다
searchPlaces();

// 키워드 검색을 요청하는 함수입니다
function searchPlaces() {

    var keyword = document.getElementById('keyword').value;

    if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
    }

    // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
    ps.keywordSearch( keyword, placesSearchCB); 
}

// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
    if (status === kakao.maps.services.Status.OK) {

        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);

        // 페이지 번호를 표출합니다
        displayPagination(pagination);

    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {

        alert('검색 결과가 존재하지 않습니다.');
        return;

    } else if (status === kakao.maps.services.Status.ERROR) {

        alert('검색 결과 중 오류가 발생했습니다.');
        return;

    }
}

// 검색 결과 목록과 마커를 표출하는 함수입니다
function displayPlaces(places) {

    var listEl = document.getElementById('placesList'), 
    menuEl = document.getElementById('searchTab'),
    fragment = document.createDocumentFragment(), 
    bounds = new kakao.maps.LatLngBounds(), 
    listStr = '';
    
    // 검색 결과 목록에 추가된 항목들을 제거합니다
    removeAllChildNods(listEl);
    
    for ( var i=0; i<places.length; i++ ) {

        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
            marker = addList(placePosition, i), 
            itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        // infowindows.push({ title: title, latitude: position.getLat(), longitude: position.getLng() });
        (function(marker, title) {
            kakao.maps.event.addListener(marker, 'mouseover', function() {
                displayInfowindow(marker, title);
        
            });

            kakao.maps.event.addListener(marker, 'mouseout', function() {
                infowindow.close();
            });

            marker.onmouseover = function(){
                displayInfowindow(marker,title);
            }
            marker.onmouseout= function(){
                infowindow.close();
            }

            itemEl.onmouseover =  function () {
                displayInfowindow(marker, title);
                marker.setMap(map);
            };

            itemEl.onmouseout =  function () {
                marker.setMap(null);
                infowindow.close();
            };

            itemEl.onclick = function(){
                var clickedIndex = Array.prototype.indexOf.call(this.parentNode.children, this);
                displaySelectedMarker(clickedIndex, places); // 클릭한 항목의 인덱스와 장소 배열을 함께 전달합니다
            }

        })(marker, places[i].place_name);

        fragment.appendChild(itemEl);
    }

    // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
    listEl.appendChild(fragment);
    menuEl.scrollTop = 0;

    // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
    map.setBounds(bounds);
}

// 검색결과 항목을 클릭했을 때 호출되는 함수입니다
function displaySelectedMarker(index, places) {
    var markerPosition = new kakao.maps.LatLng(places[index].y, places[index].x);
    var marker = addMarkerArr(markerPosition);
    var title = places[index].place_name; // 클릭한 항목의 제목 추출
    addInfoWindowArr2(markerPosition, title);
}

// 검색결과 항목을 Element로 반환하는 함수입니다
function getListItem(index, places) {

    var el = document.createElement('li'),
    itemStr = '<span class="markerbg marker_' + (index+1) + '"></span>' +
                '<div class="info">' +
                '   <h5>' + places.place_name + '</h5>';

    if (places.road_address_name) {
        itemStr += '    <span>' + places.road_address_name + '</span>' +
                    '   <span class="jibun gray">' +  places.address_name  + '</span>';
    } else {
        itemStr += '    <span>' +  places.address_name  + '</span>'; 
    }
                 
      itemStr += '  <span class="tel">' + places.phone  + '</span>' +
                '</div>';           

    el.innerHTML = itemStr;
    el.className = 'item';

    return el;
}


// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
function addList(position, idx, title) {
    var marker = new kakao.maps.Marker({
        position: position
    });
    return marker;
}

// 검색결과 목록 하단에 페이지번호를 표시하는 함수입니다
function displayPagination(pagination) {
    var paginationEl = document.getElementById('pagination'),
        fragment = document.createDocumentFragment(),
        i; 

    // 기존에 추가된 페이지번호를 삭제합니다
    while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild (paginationEl.lastChild);
    }

    for (i=1; i<=pagination.last; i++) {
        var el = document.createElement('a');
        el.href = "#";
        el.innerHTML = i;

        if (i===pagination.current) {
            el.className = 'on';
        } else {
            el.onclick = (function(i) {
                return function() {
                    pagination.gotoPage(i);
                }
            })(i);
        }

        fragment.appendChild(el);
    }
    paginationEl.appendChild(fragment);
}

// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
// 인포윈도우에 장소명을 표시합니다
function displayInfowindow(marker, title) {
    var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

    infowindow.setContent(content);
    infowindow.open(map, marker);
}


function displayPositionInfo(position,marker) {
    // Reverse Geocoding 함수를 호출하고, 콜백 함수를 이용하여 주소를 처리합니다.
    reverseGeocoding(position, function(address) {
        var content = '<div style="padding:5px;z-index:1;"  >주소: ' + address + '</div>';
        infowindow.setContent(content);
        infowindow.setPosition(position);
        infowindow.open(map,marker);
    });
}


// 검색결과 목록의 자식 Element를 제거하는 함수입니다
function removeAllChildNods(el) {   
    while (el.hasChildNodes()) {
        el.removeChild (el.lastChild);
    }
}

// Reverse Geocoding 함수
function reverseGeocoding(position, callback) {
    var geocoder = new kakao.maps.services.Geocoder();
    geocoder.coord2Address(position.getLng(), position.getLat(), function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var address = result[0].address.address_name;
            // 콜백 함수를 호출하여 주소를 반환합니다.
            callback(address);
        } else {
            console.error('Reverse geocoding 실패:', status);
            // 실패 시 콜백 함수를 호출하여 빈 주소를 반환합니다.
            callback('');
        }
    });
}


// 처음 버튼 클릭 시 호출되는 함수
function lineCheck() {
    if (polylines.length === 0) {
        // polylines 배열이 비어있으면 선을 그립니다.
        drawLineOnMap(markers);
    } else {
        // polylines 배열에 선이 존재하면 선을 지웁니다.
        clearLines();
    }
}

// 선 버튼 클릭시 배열의 순서대로 선 나타내기
function drawLineOnMap(markers) {

    var emptyIndices = [];

    // 빈 인덱스 찾기
    for (var i = 0; i < markers.length; i++) {
        if (markers[i] === undefined || markers[i] === null) {
            emptyIndices.push(i);
        }
    }

    // 빈 인덱스가 있을 경우 알림 띄우기
    if (emptyIndices.length > 0) {
        var message;
        for (var j = 0; j < emptyIndices.length; j++) {
            message = (emptyIndices[j]+1)+"번을 선택하세요.";
        }
        alert(message);
    } else {
        for (var i = 0; i < markers.length; i++) {
            linePath.push(markers[i]);
        }
    
        var polyline = new kakao.maps.Polyline({
            path: linePath, // 선을 구성하는 좌표배열 입니다
            strokeWeight: 5, // 선의 두께 입니다
            strokeColor: '#8ac0ec', // 선의 색깔입니다
            strokeOpacity: 0.7, // 선의 불투명도입니다
            strokeStyle: 'solid' // 선의 스타일입니다
        });
    
        // 지도에 선을 표시합니다 
        polyline.setMap(map);
        polylines.push(polyline);
    }

    

    for (var i = 0; i < linePath.length; i++) {
       delete linePath[i];
    }
}

function clearLines() {
    
    for (var i = 0; i < polylines.length; i++) {
        polylines[i].setMap(null);
    }   
    polylines = [];
}

function deleteAll(){
    for(var i =0; i<newMarkers.length;i++){
        newMarkers[i].setMap(null);
        positionInfoDel(i);

    }
    markers=[]
    infowindows=[]

    for (var i = 0; i < polylines.length; i++) {
        polylines[i].setMap(null);
    }
    polylines = [];
    linePath=[];
    document.getElementById("clickLatlng").innerHTML = '';
}

function positionInfoDel(markerIndex) {
    var clickLatlngDiv = document.getElementById("clickLatlng");
    
    // 해당 위치 정보를 표시하는 div 요소가 있는지 확인
    var existingDiv = document.getElementById("positionInfo_" + markerIndex);
    if (existingDiv) {
        // 이미 해당 위치 정보를 표시하는 div가 있으면 내용을 업데이트
            existingDiv.innerHTML = `
                <span class="number">${markerIndex+1}.</span>
                <div class="inputs">
                    <input type="text" class="map-input markerTitle" name="title" value="없음" placeholder="명소의 이름을 작성해주세요.">
                    <input type="hidden" name="latitude" value="없음">
                    <input type="hidden" name="longitude" value="없음">
                    <input type="text" class="map-input" name="position" value="없음" readonly>
                    <input type="text"class="map-input explain" name="explain" placeholder="간단한 설명을 작성해주세요." maxlength="30">
                </div>
            `;
    
    } else {
        console.log("해당 인덱스가 없습니다.")
    }
}


function positionInfo(markerIndex) {
    var clickLatlngDiv = document.getElementById("clickLatlng");
    
    // 해당 위치 정보를 표시하는 div 요소가 있는지 확인
    var existingDiv = document.getElementById("positionInfo_" + markerIndex);
    if (existingDiv) {
        // 이미 해당 위치 정보를 표시하는 div가 있으면 내용을 업데이트
        getAddress(infowindows[markerIndex].latitude, infowindows[markerIndex].longitude, function(address) {
      existingDiv.innerHTML = `
          <span class="number">${markerIndex + 1}.</span>
          <div class="inputs">
              <input type="text" class="map-input markerTitle" name="days[${dayIndex - 1}].markers[${markerIndex}].title" id="markerTitle_${markerIndex}" value="${infowindows[markerIndex].title}" placeholder="명소의 이름을 작성해주세요." required>
              <input type="hidden" name="days[${dayIndex - 1}].markers[${markerIndex}].latitude" id="latitude_${markerIndex}" value="${infowindows[markerIndex].latitude}" required>
              <input type="hidden" name="days[${dayIndex - 1}].markers[${markerIndex}].longitude" id="longitude_${markerIndex}" value="${infowindows[markerIndex].longitude}" required>
              <input type="text" class="map-input" name="days[${dayIndex - 1}].markers[${markerIndex}].position" id="position_${markerIndex}" value="${address}" readonly>
              <input type="text" class="map-input explain" name="days[${dayIndex - 1}].markers[${markerIndex}].explain" id="explain_${markerIndex}" placeholder="\>\> 간단한 설명을 작성해주세요." maxlength="30" required>
          </div>
      `;

        });
    } else {
        // 해당 위치 정보를 표시하는 div가 없으면 새로 생성
        getAddress(infowindows[infowindows.length - 1].latitude, infowindows[infowindows.length - 1].longitude, function(address) {
           if( infowindows.length > 15) {
                 alert("최대 15개까지만 추가할 수 있습니다.");
              return;
          }
          
         var div = document.createElement("div");
         div.className = "positionInfo";
         div.id = "positionInfo_" + markerIndex;
         div.innerHTML = `
             <span class="number">${infowindows.length}.</span>
             <div class="inputs">
                 <input type="text" class="map-input markerTitle" name="days[${dayIndex - 1}].markers[${markerIndex}].title" id="markerTitle_${markerIndex}" value="${infowindows[infowindows.length - 1].title}" placeholder="명소의 이름을 작성해주세요." required>
                 <input type="hidden" name="days[${dayIndex - 1}].markers[${markerIndex}].latitude" id="latitude_${markerIndex}" value="${infowindows[infowindows.length - 1].latitude}" required>
                 <input type="hidden" name="days[${dayIndex - 1}].markers[${markerIndex}].longitude"id="longitude_${markerIndex}" value="${infowindows[infowindows.length - 1].longitude}" required>
                 <input type="text" class="map-input" name="days[${dayIndex - 1}].markers[${markerIndex}].position" id="position_${markerIndex}"value="${address}" readonly>
                 <input type="text" class="map-input explain" name="days[${dayIndex - 1}].markers[${markerIndex}].explain" id="explain_${markerIndex}"placeholder="\>\> 간단한 설명을 작성해주세요." maxlength="30" required>
             </div>
         `;


            clickLatlngDiv.appendChild(div);
        });
    }
}

function getAddress(latitude, longitude, callback) {
    var geocoder = new kakao.maps.services.Geocoder();
    var coord = new kakao.maps.LatLng(latitude, longitude);
    geocoder.coord2Address(coord.getLng(), coord.getLat(), function(result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var address = result[0].address.address_name;
            callback(address);
        } else {
            console.error('Reverse geocoding 실패:', status);
            callback('');
        }
    });
}