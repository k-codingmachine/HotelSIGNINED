function open_win(url, name){
	window.open(url, name, "width=500, height=300");
}

function passCheck(){
	if(document.frm.pass.value.length == 0){
		alert("비밀번호를 입력하세요.");
		return false;
	}	
	return true;
}

function replyCheck(){
	if($('#replyContent').val() == ""){
		alert("답글은 무조건 입력해야합니다.");
		$('#replyContent').focus();
		return false;
	}
	return true;
}