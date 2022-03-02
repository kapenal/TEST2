<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<% response.setHeader("Pragma", "no-cache"); response.setHeader("Cache-Control", "no-cache"); response.setHeader("Cache-Control", "no-store"); %>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="../../codebase/suite.css?v=7.3.0">
<script type="text/javascript" src="../../codebase/suite.js"></script>
<title>메인페이지</title>
</head>
<body style="margin: 20px;">
	<h1>▼ 등록된 유저 조회</h1>
	<button type="button" id="selectBtn" class="dhx_sample-btn dhx_sample-btn--flat">조회</button> <button type="button" id="logOutBtn" onclick="location.href='/LOGIN/logOut'" class="dhx_sample-btn dhx_sample-btn--flat">로그아웃</button>
	<div id="grid" style="height: 800px"></div>
</body>
<script type="text/javascript">
$(function(){
	$('#selectBtn').click(function(){
		list();
	});
});

const grid = new dhx.Grid("grid", {
    columns: [
        { id: "id", header: [{ text: "ID" }],},
        { id: "pwd", header: [{ text: "PASSWORD" }] },
        { id: "name", header: [{ text: "NAME" }] },
        { id: "level", header: [{ text: "LEVEL" }] },
        { id: "desc", header: [{ text: "DESC" }] },
        { id: "regDate", header: [{ text: "REG DATE" }] },
    ],
    editable: true,
    autoWidth: true
});
function list() {
	$.ajax({
		type:'get',
		url:'userList',
		success:function(json){
			console.log(json);
			grid.data.parse(json);
		}
	});
} 
</script>
</html>