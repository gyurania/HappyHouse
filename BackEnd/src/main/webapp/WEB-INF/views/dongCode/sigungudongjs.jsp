<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script>
        $(document).ready(function(){
        	var paramYear = searchParam('year');
        	var paramMonth = searchParam('month');
        	var paramSido = searchParam('sido');
        	var paramGu = searchParam('gugun');
        	var paramDong = searchParam('dong');
        	if(paramYear){
        		$('#year').val(paramYear).prop('selected',true);
        	}
        	if(paramMonth){
        		$('#month').val(paramMonth).prop('selected',true);
        	}
        	if(paramSido){
        		$('#sido').val(paramSido).prop('selected',true);
        		$.get("http://localhost:8080/sido/"+paramSido
        				,function(data, status){
        					$("#gugun").empty();
        					$("#gugun").append('<option value="0">시/구/군</option>');
        					$.each(data, function(index, sido){
        						$("#gugun").append("<option value='"+sido.gugunCode+"'>"+sido.gugunName+"</option>");
        					}); //each
				        	if(paramGu){
				        		$('#gugun').val(paramGu).prop('selected',true);
				        		$.get("http://localhost:8080/gugun/"+paramGu
				        				,function(data, status){
				        					$("#dong").empty();
				        					$("#dong").append('<option value="0">동</option>');
				        					$.each(data, function(index, gugun){
				        						$("#dong").append("<option value='"+gugun+"'>"+gugun+"</option>");
				        					}); //each
				        					if(paramDong){
				        						$('#dong').val(paramDong).prop('selected',true);
				        					}
				        				} //function
				        				,"json"
				        			); //get
			        		}
        				} //function
        				,"json"
        				
        			); //get
        	}
        	$("#sido").change(function(){
        		$.get("http://localhost:8080/sido/"+$(this).val()
        				,function(data, status){
        					$("#gugun").empty();
        					$("#dong").empty();
        					$("#dong").append('<option value="0">동 선택</option>');
        					$("#gugun").append('<option value="0">시/구/군</option>');
        					$.each(data, function(index, sido){
        						$("#gugun").append("<option value='"+sido.gugunCode+"'>"+sido.gugunName+"</option>");
        					}); //each
        				} //function
        				,"json"
        			); //get
        			
        	}); //change
        	$("#gugun").change(function(){
        		$.get("http://localhost:8080/gugun/"+$(this).val()
        				,function(data, status){
        					$("#dong").empty();
        					$("#dong").append('<option value="0">동 선택</option>');
        					$.each(data, function(index, gugun){
        						$("#dong").append("<option value='"+gugun+"'>"+gugun+"</option>");
        					}); //each
        				} //function
        				,"json"
        			); //get
        	}); // change
        	$("#dong").change(function(){
        		changeDong();
        	}); // change
        });
        function searchParam(key) {
      	  return new URLSearchParams(location.search).get(key);
      };
    </script>
