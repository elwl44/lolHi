<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="title" value="게시물 상세페이지" />
<%@include file="../part/head.jspf"%>

<div>번호 : ${article.id}</div>

<div>작성자 : ${article.extra.writer}</div>

<div>제목 : ${article.title}</div>

<div>내용 : ${article.body}</div>

<div>${age}</div>

<div>
	<a href="${listUrl}">리스트</a>
	<c:if test="${article.extra.actorCanDelete}">
		<a onclick="if ( confirm('삭제하시겠습니까?') == false ) return false;"
			href="doDelete?id=${article.id}">삭제</a>
	</c:if>
	<c:if test="${article.extra.actorCanDelete}">
		<a href="modify?id=${article.id}">수정</a>
	</c:if>
</div>

<h2>댓글 작성</h2>

<form action="/usr/reply/doWrite" method="POST">
	<input type="hidden" name="redirectUrl" value="${currentUri}" />
	<input type="hidden" name="relTypeCode" value="article" />
	<input type="hidden" name="relId" value="${param.id}" />

	<div>
		<textarea rows="10" name="body" placeholder="내용"></textarea>
	</div>

	<div>
		<input type="submit" value="작성" />
	</div>
</form>

<h2>댓글 리스트</h2>

<c:forEach items="${replies}" var="reply">
	<div>번호 : ${reply.id}</div>
	<div>작성날짜 : ${reply.regDate}</div>
	<div>수정날짜 : ${reply.updateDate}</div>
	<div>작성자 : ${reply.extra.writer}</div>
	<div>내용 : ${reply.body}</div>

	<div>
		<c:if test="${reply.extra.actorCanModify}">
			<a href="/usr/reply/modify?id=${reply.id}&redirectUrl=${encodedCurrentUri}">수정</a>
		</c:if>
		<c:if test="${reply.extra.actorCanDelete}">
			<a href="/usr/reply/doDelete?id=${reply.id}&redirectUrl=${encodedCurrentUri}">삭제</a>
		</c:if>
	</div>
	
	<hr />
</c:forEach>

<%@include file="../part/foot.jspf"%>