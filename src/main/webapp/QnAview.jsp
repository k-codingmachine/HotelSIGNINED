<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/QnAview.css" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<link rel="stylesheet"
	href="hotel-datepicker-main/dist/css/hotel-datepicker.css">
<script src="hotel-datepicker-main/dist/fecha.js"></script>
<script src="hotel-datepicker-main/fecha-4.2.1/dist/fecha.min.js"></script>
<script src="hotel-datepicker-main/dist/js/hotel-datepicker.js"></script>
<script src="js/search.js"></script>
<script src="js/QnAview.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="QnAform">
		<h3>
			상담내용
		</h3>
		<form action="HotelServlet" method="post" name="frm">
			<input type="hidden" name="command" value="" />
			<div class="row">
				<label for="QnAcategory">제목</label>
				<div class="custInfo">
					<input type="text" name="title" id="title" value="${QnAview.qnaTitle}" readonly="readonly"/>
				</div>
				<p class="titleCheck"></p>
			</div>
			<div class="row">
				<label for="QnAcategory">상담유형</label>
				<div class="selectWrap">
					<select name="QnAcategory" id="QnAcategory">
						<option value="${QnAview.qnaCategory}">${QnAview.qnaCategory}</option>
					</select>
				</div>
				<p class="optionCheck"></p>
			</div>
			<div class="row">
				<label for="QnAcategory">내용</label>
				<div>
					<textarea name="content" id="content" class="QnAtextarea" readonly="readonly">${QnAview.qnaContent}</textarea>
				</div>
				<p class="textareaCheck"></p>
			</div>

			<div class="title_line">
				<h3>고객정보</h3>
			</div>

			<div class="row">
				<label for="QnAcategory">이메일</label>
				<div class="custInfo">
					<input type="text" name="email" id="email" value="${QnAview.email}" readonly="readonly"/>
				</div>
				<p class="emailCheck"></p>
			</div>

			<div class="row">
				<label for="QnAcategory">문의 비밀번호
				</label>
				<div class="custInfo">
					<input type="password" name="pwd" id="pwd" maxlength="8" value="${QnAview.qnaPwd}" readonly="readonly" />
				</div>
				<p class="pwdCheck"></p>
			</div>

			<div class="formSubmit">
				<span class="button_item">
					<button type="button" onclick="open_win('HotelServlet?command=QnA_check&num=${QnAview.qnaNum}', 'delete')">삭제</button>
				</span> 
				<span class="button_item">
					<button type="button" onclick="open_win('HotelServlet?command=QnA_admin_check_form&num=${QnAview.qnaNum}', 'admin')">답글달기</button>
				</span>
				<span class="button_item">
					<button type="button" onclick="location.href='HotelServlet?command=QnAList'">취소</button>
				</span>
			</div>
		</form>
	</div>
</body>
</html>