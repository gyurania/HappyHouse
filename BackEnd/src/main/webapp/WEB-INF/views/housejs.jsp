<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
var xdata;
var addresslist =[];
$(document).ready(function(){
		var ServiceKey =
		    "RsHwMANprTqKL4KpkQHjpzW2cGro8QOWdN3Pn9tgwlx4F6XwRQ3TzyXGMHZezGl3iG1gjLKjUIHPjeL39gOqhg==";
		  var pageNo = "1";
		  var numOfRows = "10000";
		  var LAWD_CD = searchParam('gugun');
		  var DEAL_YMD = searchParam('year')+searchParam('month');
		  // server에서 넘어온 data   실거래 상세자료
		  $.ajax({
		    url: "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev",
		    type: "GET",
		    data: {
		      ServiceKey: ServiceKey,
		      pageNo: pageNo,
		      numOfRows: numOfRows,
		      LAWD_CD: LAWD_CD,
		      DEAL_YMD: DEAL_YMD,
		    },
		    dataType: "xml",
		    success: function (response) {
		      xdata = response;
		      makeList(response, searchParam('dong'));
		    },
		    error: function (xhr, status, msg) {
		      xdata = msg;
		      console.log("상태값 : " + status + " Http에러메시지 : " + msg);
		    }
		  });
});

function makeSidebar(list){
	  	let aptnamelist = [];
		var apartlist = ``;
	  	for (var key in list) {
	    	var apart = list[key];
	    	var tmp = 3;
	    	apartlist += `<div>
	    	      <a href='javascript: void(0);' onclick="detailList('`+key+`')";><h4>`+key+`</h4></a>
	  	<h6>거래금액 : `+$(apart).find("거래금액").text()+ `만원</h6>
	  	<h6>면적 : `+$(apart).find("전용면적").text()+`</h6>
	  	<h6>날짜 : <i>`+$(apart).find("년").text()+`.</i>
	  	<i>`+$(apart).find("월").text()+`.</i>
	  	<i>`+$(apart).find("일").text()+`</i></h6>
	  	</div>
	  	<div class="line"><hr></div>`;
	  	  aptnamelist.push( [$(apart).find("지번").text(),$(apart).find("아파트").text()]);
	  	}
	  	$("#apartinfo").append(apartlist);
	  	makeMap(aptnamelist);
}

function makeList(data, dong, searchTarget="") {
	  $("#apartinfo").empty();
	  var list = [];
	  addresslist= [];
	  $(data)
	    .find("item")
	    .each(function () {
	      if ($(this).find("법정동").text().includes(dong) && $(this).find("아파트").text().includes(searchTarget)) {
	        let apt = $(this).find("아파트").text();
	        list[apt] = this;
	      }
	    });
	   
	  makeSidebar(list);
	  
}


function detailList(key) {
	$("#reload").empty().append(`<div class='button'><a onClick="window.location.reload()" style="cursor: pointer;">전체 아파트 목록</a></div>`);
	$("#apartinfo").empty();
	let apartnamelist = [];
	$(xdata)
	.find("item")
	.each(function () {
		var apartlist = ``;
		if (
		$(this).find("법정동").text().includes(dong.options[dong.selectedIndex].value) &&
		$(this).find("아파트").text() == key
		) {
			apartlist += `<div>
				<h4>`+key+`</h4></a>
				<h6>거래금액 : `+$(this).find("거래금액").text()+ `만원</h6>
				<h6>면적 : `+$(this).find("전용면적").text()+`</h6>
				<h6>거래날짜 : <i>`+$(this).find("년").text()+`</i>
				<i>`+$(this).find("월").text()+`</i>
				<i>`+$(this).find("일").text()+`</i></h6>
				</div>
				<div class="line"><hr></div>`;
			addresslist = [];
			apartnamelist.push([$(this).find("지번").text(),key]);
			$("#apartinfo").append(apartlist);
		}
	});
	makeMap(apartnamelist);
}
function changeDong(){
	var formData = document.getElementById("frm");
	formData.setAttribute('method', 'get');
	formData.action='http://localhost:8080/dong/';
	formData.submit();
}
function search(){
	$("#reload").empty().append(`<div class='button'><a onClick="window.location.reload()" style="cursor: pointer;">전체 아파트 목록</a></div>`);
	let searchTarget=$("#search").val();
	makeList(xdata,searchParam('dong'),searchTarget);
	
}

</script>