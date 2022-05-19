<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2c44238e47238c66c7d4d0fa078911a5&libraries=services"></script>    
<script type="text/javascript">
$(document).ready(function () {
	//getInterest();
	sortMethod();
	addButton();	
});

	function sortMethod(str){
		if(str == 'default' || str == null) {
			console.log('오래된순 정렬');
			getInterest();
		}
		else {
			console.log('최신순 정렬');
			getInterestNew();
		}
	} 

	function getInterestNew(){
		$.ajax({
			url:'http://localhost:8080/interest/listnewest',
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(addr){
				makeInterest(addr);
				makeInterestMap(addr,7);
			},
			error: function(request, status, error) {
          		console.log("status : " + request.status + "\tmsg : " + error);
          	}
		});
	}  
	
	function getInterest(){
		$.ajax({
			url:'http://localhost:8080/interest/list',
			type:'GET',
			contentType:'application/json;charset=utf-8',
			dataType:'json',
			success:function(addr){
				makeInterest(addr);
				makeInterestMap(addr,7);
			},
			error: function(request, status, error) {
          		console.log("status : " + request.status + "\tmsg : " + error);
          	}
		});
	}    
	
	function makeInterest(addr){
		$("#interest").empty();
		$(addr).each(function(index, address){
			let html = `
				<div>
		          <h3>`+ address.sidoName +`</h3>
		          <h4>`+address.gugunName+` / `+address.dongName+`</h4>
		          <div class="btn btn-warnning btn-sm" onclick="deleteInterest(`+address.dongCode+`)"><i class="fa fa-user pr-2"></i>삭제</div>
	
		  </div>
		  <div class="line"><hr></div>`;
		  $("#interest").append(html);
		});
	}
	
	function deleteInterest(dongCode){
		$.ajax({
			url:'http://localhost:8080/interest/'+dongCode,
			type:'DELETE',
			contentType:'application/json;charset=utf-8',
			success:function(){
				getInterest();
			},error: function(request, status, error) {
          		console.log("status : " + request.status + "\tmsg : " + error);
          	}
		});
	}
	function makeInterestMap(address,size){
		var container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스
		  var options = {
		    //지도를 생성할 때 필요한 기본 옵션
		    center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
		    level: size, //지도의 레벨(확대, 축소 정도)
		  };
		  let centerPosition;
		  var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
		  for(i in address){
			  let dong = address[i].dongName;
			  let coords = new kakao.maps.LatLng(address[i].lat,address[i].lng);
			  centerPosition = coords;
			  marker = new kakao.maps.Marker({
    	          map: map,
    	          position: coords,
    	        }); 
			  marker.setMap(map);
			  var infowindow = new kakao.maps.InfoWindow({
    	          content: `<div style="width:150px;text-align:center;padding:6px 0;">`+dong+`</div>`,
    	        });
    	      infowindow.open(map, marker);
    	      kakao.maps.event.addListener(
	        	          marker,
	        	          "click",
	        	          makeClickListener(map, marker, infowindow, address[i])
	        	        );
		  }
		  map.setCenter(centerPosition);
	}
	
	function makeClickListener(map, marker, infowindow, address) {
	    return function () {
	      var pos = marker.getPosition();
	      map.panTo(pos);
	      makeInterestMap([address],3);
	    $("#reload").empty().append(`<div class='button'><a onClick="window.location.reload()" style="cursor: pointer;">관심지역 목록</a></div>`);
	    };
	  }
	function addButton(){
		$("#addBtn").empty().append('<button type="button" class="btn btn-outline-secondary ml-2" onclick="addInterest()" >추가</button>');
		$("#yearSelect").empty();
		$("#monthSelect").empty();
		
	}
	function addInterest(){
		$.ajax({
			url:'http://localhost:8080/interest/'+$("#gugun").val()+'/'+$("#dong").val(),
			type:'post',
			contentType:'application/json;charset=utf-8',
			success:function(){
				getInterest();
			},error: function(request, status, error) {
          		console.log("status : " + request.status + "\tmsg : " + error);
          	}
		});
	}
</script>
