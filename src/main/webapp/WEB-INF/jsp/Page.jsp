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
	<button type="button" id="selectBtn">조회</button> <button type="button" id="logOutBtn" onclick="location.href='/LOGIN/logOut'">로그아웃</button>
	<form method="post" enctype="multipart/form-data" action="/LOGIN/FileUpload" id="addFileUploadForm" style="display:inline">
		<input type="file" id="uploadFile" name="uploadFile" required="required">
	</form>
	<button type="button" id="uploadFileBtn">업로드</button>
	<br>
	<br>
	<div id="grid" style="height: 100%"></div>
</body>
<script type="text/javascript">
$(function(){
	$('#selectBtn').click(function(){
		list();
	});
	$('#uploadFileBtn').click(function(e){
		var fileValue = $('#uploadFile').val();
		if(!/\.(dbfile)$/i.test(fileValue)){ // 정규표현식을 이용한 .dbfile만 파일 업로드가 가능하도록 함
			alert('dbfile 파일만 선택해 주세요.\n\n현재 파일 : ' + fileValue);
		} else{
			upload();
		}
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
    autoWidth: true,
    height: 700	
});
function list() {
	$.ajax({
		type:'get',
		url:'userList',
		success:function(json){
			grid.data.parse(json);
		}
	});
}
function upload(){
	var form = $('#addFileUploadForm')[0];  
    var data = new FormData(form);  
	$.ajax({
		type: "post",          
        enctype: 'multipart/form-data', 
		url:'FileUpload',
		processData: false,    
        contentType: false,      
        cache: false,
		data: data,
		success:function(json){
			alert("업로드 성공"+json.sucessCount+"건 실패"+json.failCount+"건");
			$('#addFileUploadForm')[0].reset();
			grid.data.parse(json.userList);
		}
	});
}
</script>
</html>