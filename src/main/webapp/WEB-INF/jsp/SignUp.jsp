<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<% response.setHeader("Pragma", "no-cache"); response.setHeader("Cache-Control", "no-cache"); response.setHeader("Cache-Control", "no-store"); %>
<head>
<meta charset="UTF-8">
<!-- jquery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="../../codebase/suite.css?v=7.3.0">
<script type="text/javascript" src="../../codebase/suite.js"></script>
<title>회원가입</title>
</head>
<body style="margin: 20px;">
	<form id="signUpForm"></form>
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
            validation: function(value) {
                return value && value.length > 2;
            },
            errorMessage: "3글자 이하입니다",
            successMessage: "3글자 이상입니다",
            preMessage: "3글자 이상 입력해주세요",
            required: true
        },
        {
        	id: "pwd",
        	name: "pwd",
            type: "input",
            inputType: "password",
            label: "PASSWORD",
            placeholder: "********",
            validation: function(value) {
                return value && value.length > 2;
            },
            errorMessage: "3글자 이하입니다",
            successMessage: "3글자 이상입니다",
            preMessage: "비밀번호를 입력해주세요",
            required: true
        },
        {
        	id: "name",
        	name: "name",
            type: "input",
            inputType: "input",
            label: "NAME",
            placeholder: "홍길동",
            validation: function(value) {
                return value.length > 1;
            },
            errorMessage: "이름을 입력해주세요",
            successMessage: "",
            required: true
        },
		{
        	id: "level",
            name: "level",
            type: "select",
            label: "LEVEL",
            labelPosition: "top",
            labelWidth: 140,
            required: true,
            value: "",
            options: [
                {
                	content: "A",
                    value: "A"
                },
                {
                    value: "B",
                    content: "B"
                },
                {
                    value: "C",
                    content: "C"
                }
            ]
        },
        {
        	id: "desc",
            name: "desc",
            type: "textarea",
            label: "DESC",
            helpMessage: "Help information",
            labelPosition: "top",
            labelWidth: 140,
        },
        {	
        	name: "signBtn",
			type: "button",
			text: "회원가입",
			size: "medium",
			view: "flat",
			color: "primary"
		},
    ]
});

$(function() {
	form.getItem("signBtn").events.on("click", function () {
		console.log(form.getValue());
		console.log(form.validate());
		if(form.validate() == true){
			signUpAjax();
			/* form.send("/NotLogin/insertSignUp", "POST", "user"); */
		}else{
			alert("필수사항을 입력해주세요");
		}
	});
});
function signUpAjax() {
	$.ajax({
	    type : "POST",
	    url : "/NotLogin/insertSignUp",
	    data : {
	  	  id : form.getValue()['id'],
	  	  pwd : form.getValue()['pwd'],
	  	  name : form.getValue()['name'],
	  	  level : form.getValue()['level'],
	  	  desc : form.getValue()['desc']
	    },
	    success : function(result) {
			if(result == 0){
				alert("중복되는 ID입니다");
				form.getItem("id").clear();
			} else{
				window.location.href = '/NotLogin/getLogin';
			}
		},
	    error : function() {
			alert("에러가 발생했습니다");
	    }
	});
}
</script>
</html>