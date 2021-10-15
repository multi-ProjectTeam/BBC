'use strict'

function checkId() {
	let email = $('input[type=email]').val();
	
	if (email == "" || email.trim().length == 0) {
		$('#idcheck').css("color", "black");
		$('#idcheck').text('Email Address');
	} else {
		$.ajax({
			type: "post",
			dataType: "text",
			async: true,
			url: "checkid",
			data: { email: email },
			success: function(data, textStatus) {
				if (data == "usable") {
					$('#idcheck').css("color", "green");
					$('#idcheck').text('사용할 수 있는 이메일입니다.');
					$('#signupbutton').prop('disabled', false);
				}  else {
					$('#idcheck').css("color", "red");
					$('#idcheck').text('이메일에 공백을 사용할 수 없습니다.');
					$('#signupbutton').prop('disabled', true);
				}
			},
			error: function(data, textStatus) {
				alert('오류');
			},
			complete: function(data, textStatus) { }
		});
	}
}

function checkNickname() {
	let nickname = $('input[placeholder=Nickname]').val();
	console.log(nickname);

	if (nickname == "" || nickname.trim().length == 0) {
		$('#nicknamecheck').css("color", "black");
		$('#nicknamecheck').text('Nickname');
	} else {
		$.ajax({
			type: "post",
			dataType: "text",
			async: true,
			url: "checknickname",
			data: { nickname: nickname },
			success: function(data, textStatus) {
				if (data == "usable") {
					$('#nicknamecheck').css("color", "green");
					$('#nicknamecheck').text('사용할 수 있는 닉네임입니다.');
					$('#signupbutton').prop('disabled', false);
				} else {
					$('#nicknamecheck').css("color", "red");
					$('#nicknamecheck').text('사용할 수 없는 닉네임입니다.');
					$('#signupbutton').prop('disabled', true);
				}
			},
			error: function(data, textStatus) {
				alert('오류');
			},
			complete: function(data, textStatus) { }
		});
	}
}

function sendSignup() {
	let email = $('input[type=email]').val();
	let nickname = $('input[placeholder=Nickname]').val();
	let password = $('input[placeholder=Password]').val();
	let idCheck = $('#idcheck').val();
	let nicknameCheck = $('#nicknamecheck').val();

	if (idcheck == '사용할 수 없는 아이디입니다.' || nicknamecheck == '사용할 수 없는 닉네임입니다.') {
		alert('이메일 또는 닉네임 중복 체크가 필요합니다.');
	}

	if (email == "" || email.trim().length == 0) {
		alert('이메일을 입력해주세요.');
	} else if (password == "" || password.trim().length == 0) {
		alert('비밀번호를 입력해주세요.');
	} else if (nickname == "" || nickname.trim().length == 0) {
		alert('닉네임을 입력해주세요.');
	} else {
		$('#signuptextbox').submit();
	}
}