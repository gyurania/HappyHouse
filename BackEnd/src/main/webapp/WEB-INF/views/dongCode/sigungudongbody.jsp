<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="search">
	<div class="sorting-filters text-center mb-20 d-flex justify-content-center">
		<form class="form-inline" id="frm" action="#">
			<div class="form-group md-1" name="yearSelect" id="yearSelect">
				<select class="form-control" name="year" id="year">
					<option value="2015">2015년</option>
					<option value="2016">2016년</option>
					<option value="2017">2017년</option>
					<option value="2018">2018년</option>
					<option value="2019">2019년</option>
					<option value="2020">2020년</option>
					<option value="2021">2021년</option>
				</select>
			</div>
			<div class="form-group md-1" name="monthSelect" id="monthSelect">
				<select class="form-control" name="month" id="month">
					<option value="01">1월</option>
					<option value="02">2월</option>
					<option value="03">3월</option>
					<option value="04">4월</option>
					<option value="05">5월</option>
					<option value="06">6월</option>
					<option value="07">7월</option>
					<option value="08">8월</option>
					<option value="09">9월</option>
					<option value="10">10월</option>
					<option value="11">11월</option>
					<option value="12">12월</option>
				</select>
			</div>
			<div class="form-group md-1">
				<select class="form-control" name="sido" id="sido">
					<option value="all">도/광역시</option>
					<option value="11">서울시</option>
					<option value="41">경기도</option>
					<option value="28">인천광역시</option>
					<option value="26">부산광역시</option>
					<option value="30">대전광역시</option>
					<option value="27">대구광역시</option>
					<option value="31">울산광역시</option>
					<option value="29">광주광역시</option>
					<option value="42">강원도</option>
					<option value="43">충청북도</option>
					<option value="44">경상북도</option>
					<option value="48">경상남도</option>
					<option value="45">전라북도</option>
					<option value="46">전라남도</option>
					<option value="50">제주도</option>
				</select>
			</div>
			<div class="form-group md-1">
				<select class="form-control" name="gugun" id="gugun">
					<option value="all">시/구/군</option>
				</select>
			</div>
			<div class="form-group md-1">
				<select class="form-control" name="dong" id="dong" >
					<option value="all">동선택</option>
				</select>
			</div>
			<div class="form-group md-1" name="addBtn" id="addBtn" >
			</div>
		</form>
	</div>
</div>