<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="hotelCheckinAndCheckout">
		<div class="date_col">
			<strong class="date_title">체크인</strong> <span class="date_day">10월24일</span>
			<span class="date_month">(화)</span>
		</div>
		<span class="date_stay"> <span class="night"></span> 박
		</span>
		<div class="date_col">
			<strong class="date_title">체크아웃</strong> <span class="date_day">10월25일</span>
			<span class="date_month">(수)</span>
		</div>

		<a href="" title="레이어팝업" class="date_anchor"></a>
	</div>
	<div class="sertchPerson">
		<div class="person_col">
			<strong class="person_text">객실수</strong> <span class="person_num">1</span>
		</div>
		<div class="person_col">
			<strong class="person_text">성인</strong> <span class="person_num">2</span>
		</div>
		<div class="person_col">
			<strong class="person_text">어린이</strong> <span class="person_num">0</span>
		</div>

		<a href="" title="레이어팝업" class="date_anchor"></a>
	</div>
	<div class="item_edit">
		<button type="submit" class="sertchButton"
			onclick="location.href='HotelServlet?command=search_room'">검색</button>
	</div>

	<div class="dateInput">
		<div>
			<input type="hidden" id="input-id" name="input-id">
		</div>
	</div>
</body>
</html>