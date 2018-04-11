<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>

    <c:when test = "${not empty sessionScope.language}">
        <c:set var="language" value="${sessionScope.language}" />
    </c:when>

    <c:otherwise>
        <c:set var="language" value="en" scope="session"/>
    </c:otherwise>

</c:choose>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="textOnPages"/>

<!DOCTYPE html>
<html lang="${language}">
<html>
<head>
    <meta charset="utf-8">
    <title>Andrei Dudchenko's New Year gift shop</title>
    <style>
    *:focus {outline: none;}

   ::-webkit-input-placeholder {
    color: blue;
   }

   body {font: 14px/21px "Lucida Sans", "Lucida Grande", "Lucida Sans Unicode", sans-serif;}
.contact_form h2, .contact_form label {font-family:Georgia, Times, "Times New Roman", serif;}
.form_hint, .required_notification {font-size: 11px;}

.contact_form ul {
 width:750px;
 list-style-type:none;
 list-style-position:outside;
 margin:0px;
 padding:0px;
}
.contact_form li{
 padding:12px;
 border-bottom:1px solid #eee;
 position:relative;
}

.contact_form li:first-child, .contact_form li:last-child {
 border-bottom:1px solid #777;
}

.contact_form h2 {
 margin:0;
 display: inline;
}
.required_notification {
 color:#d45252;
 margin:5px 0 0 0;
 display:inline;
 float:right;
}

.contact_form label {
 width:150px;
 margin-top: 3px;
 display:inline-block;
 float:left;
 padding:3px;
}
.contact_form input {
 height:20px;
 width:220px;
 padding:5px 8px;
}
.contact_form textarea {padding:8px; width:300px;}
.contact_form button {margin-left:156px;}

.contact_form input, .contact_form textarea {
 border:1px solid #aaa;
 box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
 border-radius:2px;
}
.contact_form input:focus, .contact_form textarea:focus {
 background: #fff;
 border:1px solid #555;
 box-shadow: 0 0 3px #aaa;
 padding-right:70px;
  -moz-transition: padding .25s;
 -webkit-transition: padding .25s;
 -o-transition: padding .25s;
 transition: padding .25s;
}
/* Button Style */
button.submit {
 background-color: #68b12f;
 background: -webkit-gradient(linear, left top, left bottom, from(#68b12f), to(#50911e));
 background: -webkit-linear-gradient(top, #68b12f, #50911e);
 background: -moz-linear-gradient(top, #68b12f, #50911e);
 background: -ms-linear-gradient(top, #68b12f, #50911e);
 background: -o-linear-gradient(top, #68b12f, #50911e);
 background: linear-gradient(top, #68b12f, #50911e);
 border: 1px solid #509111;
 border-bottom: 1px solid #5b992b;
 border-radius: 3px;
 -webkit-border-radius: 3px;
 -moz-border-radius: 3px;
 -ms-border-radius: 3px;
 -o-border-radius: 3px;
 box-shadow: inset 0 1px 0 0 #9fd574;
 -webkit-box-shadow: 0 1px 0 0 #9fd574 inset ;
 -moz-box-shadow: 0 1px 0 0 #9fd574 inset;
 -ms-box-shadow: 0 1px 0 0 #9fd574 inset;
 -o-box-shadow: 0 1px 0 0 #9fd574 inset;
 color: white;
 font-weight: bold;
 padding: 6px 20px;
 text-align: center;
 text-shadow: 0 -1px 0 #396715;
}
button.submit:hover {
 opacity:.85;
 cursor: pointer;
}
button.submit:active {
 border: 1px solid #20911e;
 box-shadow: 0 0 10px 5px #356b0b inset;
 -webkit-box-shadow:0 0 10px 5px #356b0b inset ;
 -moz-box-shadow: 0 0 10px 5px #356b0b inset;
 -ms-box-shadow: 0 0 10px 5px #356b0b inset;
 -o-box-shadow: 0 0 10px 5px #356b0b inset;
}
.contact_form input, .contact_form textarea {
 padding-right:30px;
}

.contact_form input:focus:invalid, .contact_form textarea:focus:invalid { /* when a field is considered invalid by the browser */
 background: #fff url(images/red_star.png) no-repeat 98% center;
 box-shadow: 0 0 5px #d45252;
 border-color: #b03535
}

.contact_form input:required:valid, .contact_form textarea:required:valid { /* when a field is considered valid by the browser */
 box-shadow: 0 0 5px #5cd053;
 border-color: #28921f;
}

input:required, textarea:required {
 background: #fff no-repeat 98% center;
 background-image: url(images/red_star.png)
}

.form_hint {
 background: #d45252;
 border-radius: 3px 3px 3px 3px;
 color: white;
 margin-left:8px;
 padding: 1px 6px;
 z-index: 999; /* hints stay above all other elements */
 position: absolute; /* allows proper formatting if hint is two lines */
 display: none;
}

.form_hint::before {
 content: "\25C0"; /* треугольник, указующий влево, в  escaped unicode */
 color:#d45252;
 position: absolute;
 top:1px;
 left:-6px;
}

.contact_form input:focus + .form_hint {display: inline;}
.contact_form input:required:valid + .form_hint {background: #28921f;} /* change form hint color when valid */
.contact_form input:required:valid + .form_hint::before {color:#28921f;} /* change form hint arrow color when valid */
  </style>
    <link rel="stylesheet" media="screen" href="styles.css" >
</head>

<body>
<form action="${pageContext.request.contextPath}/app/language" method="post">
            <select name="language">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
            </select>
            <button class="submit" type="submit"><fmt:message key="index.select" /></button>
</form>


<form class="contact_form">

<c:if test="${not empty sessionScope.full_name}">
        <h1><span>${full_name},</span></h1>
</c:if>
<h1><fmt:message key="index.initialGreetings" /></h1>



<br>
<h3><fmt:message key="index.mainText" /></h3>

<p>&nbsp;</p>

<c:if test="${empty sessionScope.full_name}">
<h2><a href = "${pageContext.request.contextPath}/app/registration_form"><fmt:message key="index.registration" /></a></h2>
<p>&nbsp;</p>
<h2><a href = "${pageContext.request.contextPath}/app/login_form"><fmt:message key="index.login" /></a></h2>
</c:if>

<c:if test="${not empty sessionScope.full_name}">
        <h2><a href = "${pageContext.request.contextPath}/app/logout"><span><fmt:message key="index.logout" /></span></a></h2>
        <p></p>
        <h2><a href = "${pageContext.request.contextPath}/app/login"><fmt:message key="index.productsPage" /></a></h2>
</c:if>
<br>
<br>


</form>
</body>
</html>