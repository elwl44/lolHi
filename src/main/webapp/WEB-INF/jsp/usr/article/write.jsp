<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="title" value="게시물 작성" />
<%@include file="../part/head.jspf" %>
	<form action="doWrite">
		<div>
			제목: <input type="text" maxlength="30" name="title" placeholder="제목">
		</div>
		<div>
			내용: <input type="text" maxlength="30" name="body" placeholder="내용">
		</div>
		<div>
			제출 : <input type="submit" value="작성" />
		</div>
	</form>
	<div>
		<a href="list">리스트</a>
	</div>
<%@include file="../part/foot.jspf" %>