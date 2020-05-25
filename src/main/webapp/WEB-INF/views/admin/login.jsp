<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="wrapper">
	<div class="login-wrap">
		<h1><img src="/resources/static/images/log/img_logo.png" alt="콜뷰티 로고"></h1>
		<div class="login-box">
		<h2>관리자 로그인</h2>
			<div class="input-box">
				<input type="text" name="admId" id="admId" value="admin">
				<label for="logId" class="log-tit">아이디를 입력하세요</label>
			</div>
			<div class="input-box">
				<input type="password" name="admPw" id="admPw" class="view-pw" value="1234">
				<label for="logPw" class="log-tit">비밀번호를 입력하세요</label>
				<span class="checkbox-area">
					<input type="checkbox" id="ckViewPw">
<!-- 					<label for="ckViewPw" title="비밀번호표시"><span></span></label> -->
				</span>
			</div>
			<div class="btn-area"><button onclick="fn_login();" value="로그인">LOGIN</button></div>
			<address>Copyright @ ㈜시스터즈 All Rights Reserved</address>
		</div>
	</div>
</div>
<script>
	$(".input-box > input").on("focus input",function(){
		if($(this).is(":focus") || $(this).val().length) {
			$(this).next().addClass("active")
		} else {
			$(this).next().removeClass("active")
		}
	});

	function fn_login(){
		var admId = $("#admId").val();
		var admPw = $("#admPw").val();

		$.ajax({
			url : "/admin/loginProc"
			,data : {admId , admPw}
			,type : "post"
			,success: function(r){
				console.log(r);
				if(r.code == 0){
					location.href = "/admin/accIpList";
				}else{
					alert(r.msg);
				}
			}
		});
	}

</script>