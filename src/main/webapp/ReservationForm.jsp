<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 조회</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/Reservation.css">
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<link rel="stylesheet"
	href="hotel-datepicker-main/dist/css/hotel-datepicker.css">
<script src="hotel-datepicker-main/dist/fecha.js"></script>
<script src="hotel-datepicker-main/fecha-4.2.1/dist/fecha.min.js"></script>
<script src="hotel-datepicker-main/dist/js/hotel-datepicker.js"></script>
<script src="js/search.js"></script>
<script src="js/ReservationCheck.js" type="text/javascript"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="stepProcess">
		<ul class="process_col">
			<li class="list_first"><a href="#">호텔 선택</a></li>
			<li class="list_second"><a href="#">객실 선택</a></li>
			<li class="list_third"><a href="#">예약정보 입력</a></li>
		</ul>
	</div>

	<div style="margin: 60px auto 0; width: 1320px;">

		<form name="frm" method="post" action="HotelServlet">
			<input type="hidden" name="command" value="Reservation_complete">
			<input type="hidden" name="originCheckIn" value="${originCheckIn}" />
			<input type="hidden" name="originCheckOut" value="${originCheckOut}" />
			<input type="hidden" name="adult" value="${adult}" /> <input
				type="hidden" name="child" value="${child}" /> <input type="hidden"
				name="bak" value="${bak}" /> <input type="hidden" name="roomNum"
				value="${roomNum}" />

			<div class="reservation-room-info">
				<img src="${img}" alt="룸 사진" class="pic">
				<div class="txt-area">
					<ul>
						<li><span class="type">호텔 및 객실</span> <span
							class="info htlAndRms">${roomName}</span></li>

						<li><span class="type">투숙 날짜</span> <span class="info">
								<span data-target="conversionDateFormat">${originCheckIn}</span>
								- <span data-target="conversionDateFormat">${originCheckOut}</span>(
								<span class="night-days" data-rdays="1">${bak}</span>)
						</span></li>
						<li><span class="type">객실 및 인원</span> <span class="info">객실
								1, 성인${adult} , 어린이${child} </span></li>
					</ul>
				</div>
			</div>
			<div class="totalprice">
				<span class="total-price">총 요금</span> <span class="total-number">${total}</span>
				<span class="won">KRW</span>
			</div>

			<div class="reservation-title">
				<h3>예약자 정보</h3>

			</div>

			<div class="col-box">
				<label for="name" class="input-title">예약자 이름 *</label><span></span><br>
				<input class="reservationBox" type="text" name="name" size="20"
					title="예약자 이름" placeholder="예약자 이름을 입력하세요." id="name" required>
				<hr class="inquiry-hr">

				<label for="phone" class="input-title">예약자 전화번호 *</label><br> <input
					class="reservationBox" type="text" name="phone" size="20"
					title="예약자 전화번호" placeholder="ex)010********" id="phone" required>

				<hr class="inquiry-hr">

				<label for="email" class="input-title">예약자 이메일 *</label><br> <input
					class="reservationBox" type="text" name="email" size="20"
					title="예약자 이메일" placeholder="ex)abc@naver.com" id="email" required>

				<hr class="inquiry-hr">

				<label for="pwd" class="input-title">예약자 비밀번호 *</label><br> <input
					class="reservationBox" type="password" name="pwd" size="20"
					title="예약자 비밀번호" placeholder="4자리비밀번호" id="pwd" required
					maxlength="4">
			</div>

			<button type="submit" name="confirm" value="확인"
				onclick="return ReservationValue()" class="submitButton">
				<span>확인</span>
			</button>
		</form>
	</div>
</body>
</html>