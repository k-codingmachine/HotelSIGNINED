<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객의 소리 - 시그니드 고객센터</title>
<link rel="stylesheet" href="css/QnA.css" />
<link rel="stylesheet" href="css/main.css" />
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script>
<script src="js/QnACheck.js" type="text/javascript"></script>
<script src="js/sertch.js" type="text/javascript"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans&display=swap"
	rel="stylesheet">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="QnAWrap">
		<h1>고객의 소리</h1>
		<p>
			호텔 이용과 관련된 궁금한 사항이 있으시면 문의를 남겨주세요. <br /> 가능한 빨리 답변 드리겠습니다.
		</p>

		<div class="QnAform">
			<h3>
				상담내용<span class="essential">*필수입력</span>
			</h3>
			<form action="HotelServlet" method="post" name="frm">
				<input type="hidden" name="command" value="hotelQnA"/>
				<div class="row">
					<label for="QnAcategory">제목 <span class="essential">*</span></label>
					<div class="custInfo">
						<input type="text" name="title" id="title" />
					</div>
					<p class="titleCheck"></p>
				</div>
				<div class="row">
					<label for="QnAcategory">상담유형 <span class="essential">*</span></label>
					<div class="selectWrap">
						<select name="QnAcategory" id="QnAcategory">
							<option value="">직접선택</option>
							<option value="칭찬">칭찬</option>
							<option value="질문">질문</option>
							<option value="불만">불만</option>
							<option value="기타">기타</option>
						</select>
					</div>
					<p class="optionCheck"></p>
				</div>
				<div class="row">
					<label for="QnAcategory">내용 <span class="essential">*</span></label>
					<div>
						<textarea name="content" id="content"
							class="QnAtextarea"></textarea>
					</div>
					<p class="textareaCheck"></p>
				</div>

				<div class="title_line">
					<h3>고객정보</h3>
				</div>

				<div class="row">
					<label for="QnAcategory">이메일 <span class="essential">*</span></label>
					<div class="custInfo">
						<input type="text" name="email" id="email" />
					</div>
					<p class="emailCheck"></p>
				</div>

				<div class="row">
					<label for="QnAcategory">문의 비밀번호 (문의사항을 확인하거나 삭제할 때 필요합니다.)
						<span class="essential">*</span>
					</label>
					<div class="custInfo">
						<input type="password" name="pwd" id="pwd" maxlength="8"
							placeholder="최대 8자리" />
					</div>
					<p class="pwdCheck"></p>
				</div>

				<div class="formSubmit">
					<span class="button_item"><button type="submit" onclick="return QnACheck()">확인</button></span>
					<span class="button_item"><button type="button" onclick="location.href='HotelServlet?command=QnAList'">취소</button></span>
				</div>
			</form>
		</div>
	</div>
</body>
</html>