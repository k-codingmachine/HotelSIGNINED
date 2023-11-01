function QnACheck() {
    // 오류 메시지 초기화
    $('.titleCheck, .optionCheck, .textareaCheck, .emailCheck, .pwdCheck').text('');

    if ($('#title').val() == "") {
        $('.titleCheck').text("제목은 필수입력 항목입니다.").css({ color: "#ef6600", fontSize: "14px", paddingTop: "10px" });
        $('#title').focus();
        return false;
    }

    if ($('#QnAcategory').val() == "") {
        $('.optionCheck').text("질문분류2선택 항목 입력이 필요합니다.").css({ color: "#ef6600", fontSize: "14px", paddingTop: "10px" });
        $('#QnAcategory').focus();
        return false;
    }

    if ($('#content').val() == "") {
        $('.textareaCheck').text("contents 항목 입력이 필요합니다.").css({ color: "#ef6600", fontSize: "14px", paddingTop: "10px" });
        $('#content').focus();
        return false;
    }

    if ($('#email').val() == "") {
        $('.emailCheck').text("이메일 항목 입력이 필요합니다.").css({ color: "#ef6600", fontSize: "14px", paddingTop: "10px" });
        $('#email').focus();
        return false;
    }

    if ($('#pwd').val() == "") {
        $('.pwdCheck').text("패스워드 항목 입력이 필요합니다.").css({ color: "#ef6600", fontSize: "14px", paddingTop: "10px" });
        $('#pwd').focus();
        return false;
    }

    return true;
}