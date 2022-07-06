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
<title>⚠ New Post ⚠</title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<!-- CSS LINK -->
<link rel="stylesheet" type="text/css" href="/css/newpost.css">
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- JAVASCRIPT LINK -->
<script type="text/javascript" src="/js/app.js"></script>
</head>
<body class="container-fluid">

<div class="d-flex flex-row justify-content-between">

<div class="stay">
</div>


<div class="container d-flex flex-column align-items-center mid">
<h1 class="text-center roses"><span>I</span><span>s</span><span>s</span><span>a</span><span>P</span><span>e</span><span>t</span></h1>


<div class="box d-flex flex-column align-items-center">

<!-- LOGIN FORM -->
<div class="container1">
  <section id="content">
 
<form:form class="" action="/photo/new" method="post" modelAttribute="newPhoto" enctype="multipart/form-data">
<form:input type="hidden" path="creator.id" value="${creator}"/>
<div >
<p > 
<form:label class="form-label" path="file">Upload Image:  </form:label><span> <form:errors class="text-danger" path="file" /></span>
<form:input class="ms-5 " type="file" path="file" />
</p>
</div>
<div >
<p > 
<form:label class="form-label" path="content">Add a few words..</form:label> <span> <form:errors class="text-danger" path="content" /> </span>
<form:input class="ms-5 form-control" type="text" path="content" />

</p>
</div>
      <div>
        <input type="submit" value="Create" class="">
        <a href="/petgram" class="" >Cancel</a>
      </div>
</form:form>
 
  </section><!-- content -->
</div><!-- container -->

</div>
</div>

<div class=" stay ">
</div>

</div>

</body>
</html>