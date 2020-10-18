<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성</title>
</head>
<body>
	<h1>게시물 작성</h1>
	<form action="doWrite">
	<div>제목: <input type="text" maxlength="30" name="title"placeholder = "제목"></div>
	<div>내용: <input type="text" maxlength="30" name="body"placeholder = "내용"></div>
	<div>제출 : <input type="submit" value="작성" /></div>
	</form>
	<div>
		<a href="list">리스트</a>
	</div>
</body>
</html>