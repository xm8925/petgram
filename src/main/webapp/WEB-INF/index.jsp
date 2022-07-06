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
<title> 🐱 Login or Register 🐶 </title>
<!-- for Bootstrap CSS -->
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<!-- YOUR own local CSS -->
<!-- CSS LINK -->
<link rel="stylesheet" type="text/css" href="/css/style.css">
<!-- For any Bootstrap that uses JS or jQuery-->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<!-- JAVASCRIPT LINK -->
<script type="text/javascript" src="/js/app.js"></script>
</head>
<body class="container-fluid bg-secondary">

<h1 class="text-center roses"><span>I</span><span>s</span><span>s</span><span>a</span><span>P</span><span>e</span><span>t</span></h1>

<div class="bg"></div>
<div class="bg bg2"></div>
<div class="bg bg3"></div>
<div class="content">

</div>

<div class="d-flex justify-content-evenly">
<!-- REGISTER FORM -->
<div class="container">
  <section id="content">
 
<form:form class="" action="/register" method="post" modelAttribute="newUser">
      <h1>Register Form</h1>
<div >
<p > 
<form:label class="form-label" path="firstName">First Name: </form:label><span> <form:errors class="text-danger" path="firstName" /> </span>
<form:input class="ms-5 form-control"  type="text" path="firstName" />

</p>
</div>

<div >
<p > 
<form:label class="form-label" path="lastName">Last Name: </form:label><span> <form:errors class="text-danger" path="lastName" /> </span>
<form:input class="ms-5 form-control"  type="text" path="lastName" />

</p>
</div>
<div >
<p > 
<form:label class="form-label" path="email">Email:  </form:label><span> <form:errors class="text-danger" path="email" /></span>
<form:input class="ms-5 form-control" type="text" path="email" />

</p>
</div>
<div >
<p > 
<form:label class="form-label" path="password">Password: </form:label> <span> <form:errors class="text-danger" path="password" /> </span>
<form:input class="ms-5 form-control" type="text" path="password" />
</p>
</div>

<div >
<p > 
<form:label  class="form-label" path="confirmPassword">Confirm Password: </form:label> <span> <form:errors class="text-danger" path="confirmPassword" /> </span>
<form:input class="ms-5 form-control" type="text" path="confirmPassword" />

</p>
</div>
      <div>
        <input type="submit" value="Register" class="">
      </div>
</form:form>
 
  </section><!-- content -->
</div><!-- container -->


<!-- LOGIN FORM -->
<div class="container">
  <section id="content">
 
<form:form class="" action="/login" method="post" modelAttribute="newLogin">
      <h1>Login Form</h1>
<div >
<p > 
<form:label class="form-label" path="email">Email:  </form:label><span> <form:errors class="text-danger" path="email" /></span>
<form:input class="ms-5 form-control" type="text" path="email" />
</p>
</div>
<div >
<p > 
<form:label class="form-label" path="password">Password: </form:label> <span> <form:errors class="text-danger" path="password" /> </span>
<form:input class="ms-5 form-control" type="text" path="password" />

</p>
</div>
      <div>
        <input type="submit" value="Login" class="">
      </div>
</form:form>
 
  </section><!-- content -->
</div><!-- container -->
</div>
</body>
</html>