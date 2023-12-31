<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="header">
	<a href="index.jsp"><img src="hotel_logo.png" alt="호텔로고사진"></a>

	<nav>
		<ul>
			<li class="sertchClick"><a href="">객실예약</a></li>
			<li><a href="ReservationInquiry.jsp">예약조회</a></li>
			<li><a href="HotelServlet?command=QnAList&pageNum=1">QnA</a></li>
		</ul>
	</nav>

	<div class="sertchWrap">
		<div class="hotelSertch">
			<form action="HotelServlet" name="frm" method="post" id="myForm">
				<input type="hidden" name="command" value="search_room" class="command"/>
				<div class="hotelName">
					<label for="name">호텔</label>
					<div class="input">
						<input type="text" readonly="readonly" value="시그니드 서울" name="name"
							id="name">
					</div>
				</div>
				<div class="hotelCheckinAndCheckout">
					<div class="date_col">
						<strong class="date_title">체크인</strong> <span class="date_day"></span>
					</div>
					<span class="date_stay"> <span class="night">1 박</span> 
					</span>
					<div class="date_col">
						<strong class="date_title">체크아웃</strong> <span class="date_day"></span>
					</div>

					<a href="" title="레이어팝업" class="date_anchor"></a>
				</div>
				<div class="sertchPerson">
					<div class="person_col">
						<strong class="person_text">객실수</strong> <span class="person_num">1</span>
					</div>
					<div class="person_col">
						<strong class="person_text">성인</strong> <span class="person_num">1</span>
					</div>
					<div class="person_col">
						<strong class="person_text">어린이</strong> <span class="person_num">0</span>
					</div>

					<a href="" title="레이어팝업" class="date_anchor"></a>
				</div>
				<div class="item_edit">
					<button type="submit" class="sertchButton" onclick="amountCount()">검색</button>
				</div>

				<div class="dateInput">
					<div>
						<input type="hidden" id="input-id" name="input-id" placeholder="Enter ID">
					</div>
					
					<div class="datePerson">
						어른
						<!-- 어른의 수를 조절하는 input -->
						<input type="button" value=" - " onclick="del('adultAmount')" class="amounts">
						<input type="text" name="adultAmount" id="adultAmount" value="1" min="1" size="3">
						<input type="button" value=" + " onclick="add('adultAmount')" class="amounts">
						&nbsp;&nbsp;&nbsp; 어린이
						<!-- 어린이의 수를 조절하는 input -->
						<input type="button" value=" - " onclick="del('childAmount');" class="amounts">
						<input type="text" name="childAmount" id="childAmount" value="0" size="3">
						<input type="button" value=" + " onclick="add('childAmount');" class="amounts">
					</div>
					
				</div>
			</form>
		</div>
	</div>
</div>