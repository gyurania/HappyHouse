<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2c44238e47238c66c7d4d0fa078911a5&libraries=services"></script>
    <script>
        function makeMap(aptnamelist) {
        	let address = $("#dong").val();
        	  var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
        	  var options = {
        	    //지도를 생성할 때 필요한 기본 옵션
        	    center: new kakao.maps.LatLng(37.500723072486, 127.03680544372), //지도의 중심좌표.
        	    level: 4, //지도의 레벨(확대, 축소 정도)
        	  };

        	  var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
        	  // 마커가 표시될 위치입니다
        	  var markerPosition;

        	  // 마커를 생성합니다
        	  let marker;
 
        	  var iwContent = '<div style="padding:5px;">거래내역이 없습니다. 　</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        	    iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        	    iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다


        	  var geocoder = new kakao.maps.services.Geocoder();
        	  if (aptnamelist.length == 0) {
        	    let str = address;
        	    geocoder.addressSearch(str, function (result, status) {
        	      // 정상적으로 검색이 완료됐으면
        	      if (status === kakao.maps.services.Status.OK) {
        	        let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
					map.setCenter(coords);
        	        // 결과값으로 받은 위치를 마커로 표시합니다
        	        marker = new kakao.maps.Marker({
        	          map: map,
        	          position: coords,
        	        });
              	  	
        	        // 인포윈도우로 장소에 대한 설명을 표시합니다
        	        var infowindow = new kakao.maps.InfoWindow({
        	          content: `<div style="width:150px;text-align:center;padding:6px 0;">`+address+`</div>`,
        	        });
        	        infowindow.open(map, marker);

        	      }
        	    });
        	  }
        	  else{
        		  let str;
	        	  for (i in aptnamelist) {
	        	    str = address + " " + aptnamelist[i][0];
	        	    let aptname = aptnamelist[i][1];
	        	  // 주소로 좌표를 검색합니다
	        	    geocoder.addressSearch(str, function (result, status) {
	        	      // 정상적으로 검색이 완료됐으면
	        	      if (status === kakao.maps.services.Status.OK) {
	        	        let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	        	        // 결과값으로 받은 위치를 마커로 표시합니다
	        	        marker = new kakao.maps.Marker({
	        	          map: map,
	        	          position: coords,
	        	        });
	        	        marker.setMap(map);
	        	        // 인포윈도우로 장소에 대한 설명을 표시합니다
	        	        var infowindow = new kakao.maps.InfoWindow({
	        	          content: `<div style="width:150px;text-align:center;padding:6px 0;">`+aptname+`</div>`,
	        	        });
	        	        infowindow.open(map, marker);
	
	        	        kakao.maps.event.addListener(
	        	          marker,
	        	          "click",
	        	          makeClickListener(map, marker, infowindow, aptname)
	        	        );
	        	      }
	        	    });
	        	  }
	        	    geocoder.addressSearch(str, function (result, status) {
		        	      // 정상적으로 검색이 완료됐으면
		        	      if (status === kakao.maps.services.Status.OK) {
		        	        let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		        	        map.setCenter(coords);
		        	      }
	        	    });
        	  }
        	  function makeClickListener(map, marker, infowindow, aptname) {
        	    return function () {
        	      var pos = marker.getPosition();
        	      map.panTo(pos);
        	      detailList(aptname);
        	      map.setLevel(4);
        	    };
        	  }
        	}
</script>