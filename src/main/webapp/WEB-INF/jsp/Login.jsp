<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="../../codebase/suite.css?v=7.3.0">
<script type="text/javascript" src="../../codebase/suite.js"></script>
<title>로그인</title>
</head>
<body style="margin: 20px;">
	<form id="loginForm"></form>
</body>
<script type="text/javascript">
const form = new dhx.Form("form", {
    css: "dhx_layout-cell--bordered",
    padding: 40,
    rows: [
        {
        	id: "id",
            name: "id",
            type: "input",
            label: "ID",
            placeholder: "ID",
            validation: "text",
            required: true
        },
        {
        	id: "pwd",
        	name: "pwd",
            type: "input",
            inputType: "password",
            label: "PASSWORD",
            placeholder: "********",
            validation: "password",
            required: true
        },
        {
			cols: [
				{	
	             	name: "loginBtn",
	     			type: "button",
	     			text: "로그인",
	     			size: "medium",
	     			view: "flat",
	     			color: "primary"
	     		},
	     		{	
	             	name: "signUpBtn",
	     			type: "button",
	     			text: "회원가입",
	     			size: "medium",
	     			view: "flat",
	     			color: "success",
	     			padding: "0px 10px"
     			},
        	]
        }
    ]
});

$(function() {
	form.getItem("loginBtn").events.on("click", function () {
		loginAjax();
	});
	form.getItem("signUpBtn").events.on("click", function () {
		window.location.href = '/NotLogin/getSignUp'
	});
});

function loginAjax() {
	$.ajax({
	    type : "POST",
	    url : "/NotLogin/login",
	    data : {
	  	  id : form.getValue()['id'],
	  	  pwd : form.getValue()['pwd'],
	    },
	    success : function(ID) {
	  	  console.log(ID + "성공");
	       if (ID == '') {
	            alert('ID와 비밀번호를 확인해주십쇼');
	        }else {
	            window.location.href = '/LOGIN/page';
	        }
	    },
	    error : function() {
	        alert("에러가 발생했습니다");
	    }
	});
}
</script>
</html>