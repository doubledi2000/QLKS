<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${not empty message }">
	<div class="alert alert-success">${message }</div>
</c:if>
<c:if test="${ not empty error}">
	<div class="alert alert-warning">${error }</div>
</c:if>