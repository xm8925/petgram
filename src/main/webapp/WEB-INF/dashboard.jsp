<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- Functions -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> ♡ PetGram ♥ </title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<!-- CSS LINK -->
<link rel="stylesheet" type="text/css" href="/css/dashboard.css">
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- JAVASCRIPT LINK -->
<script type="text/javascript" src="/js/app.js"></script>
</head>
<body class="container-fluid">


<div class="d-flex flex-row justify-content-between">

<div class="stay">
<a href="/petgram/new" class="button-53 ">New Post</a>

</div>


<div class="container d-flex flex-column align-items-center mid ">
<h1 class="text-center roses"><span>I</span><span>s</span><span>s</span><span>a</span><span>P</span><span>e</span><span>t</span></h1>

<div class="box d-flex flex-column align-items-center">


<c:forEach var="post" items="${posts }"> 
<div class="one">


<h6 class="name text-start"><c:out value="${post.creator.firstName}"/> <c:out value="${post.creator.lastName}"/> </h6>

<img src="${post.file}" class="img-fluid" />

<div class="d-flex justify-content-between">
<c:choose>

<c:when test = "${post.users.contains(user)== false}"> 


<a href="/petgram/like/${post.id}" class="ms-2">  <span class="heart2 mt-3"></span>  </a>

</c:when>

<c:otherwise> 
<a href="/petgram/unlike/${post.id}"><span class="heart"></span> </a> 
</c:otherwise>
</c:choose>

<h3 class="text-center name mt-2"> <c:out value="${post.content}"/> </h3>

<div></div>
</div>



<h6 class="name text-end"> <c:out value="${post.createdAt}"/> </h6>
</div>
</c:forEach>

</div>


</div>

<div class=" stay ">
<a href="/logout" class=" button-54 mb-3">Logout</a>
<a href="/petgram/old" class="button-53 ">Old Posts</a>
</div>

</div>



</body>
</html>