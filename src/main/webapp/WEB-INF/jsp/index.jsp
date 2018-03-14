<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<%-- <jsp:include page="/WEB-INF/views/common/css_include.jsp"></jsp:include>
<link rel="stylesheet" href="<c:url value='/resources/css/error.css'/>">
<link rel="icon" type="image/png"  href="/advertisement/동글이.png"/> --%>
<title>홈페이지</title>
</head>
<body>
	<section class="err-content-div">
	    <section class="err-contents">
	        <div class="err-main-contents">
	            <div class="err-contents-image">
	                <script src="http://localhost:8080/AdServer?areacode=A"></script>
	                <script src="http://localhost:8080/AdServer?areacode=B"></script> 
	            </div>
	            <script src="http://localhost:8080/AdServer?areacode=C"></script> 
	        </div>
	    </section>
    </section>
    <%-- <jsp:include page="/WEB-INF/views/common/js_include.jsp"></jsp:include>--%>
</body>
</html>