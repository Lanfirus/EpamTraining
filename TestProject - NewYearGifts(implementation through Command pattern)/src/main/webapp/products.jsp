<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Andrei Dudchenko's New Year gift shop</title>
    <style>
       .leftimg {
        float:left; /* Выравнивание по левому краю */
        margin: 7px 7px 7px 0; /* Отступы вокруг картинки */
       }
       
        body {font: 14px/21px "Lucida Sans", "Lucida Grande", "Lucida Sans Unicode", sans-serif;}
       .premade_order_form h2, .premade_order_form label {font-family:Georgia, Times, "Times New Roman", serif;}
       .form_hint, .required_notification {font-size: 16px;}
       
       .premade_order_form label {
        width:150px;
        margin-top: 3px;
        display:inline-block;
        float:left;
        padding:3px;
       }
       .premade_order_form input {
        height:20px;
        width:220px;
        padding:5px 8px;
       }
       .premade_order_form textarea {padding:8px; width:300px;}
       .premade_order_form button {margin-left:15px;}
       
       .premade_order_form input, .premade_order_form textarea {
        border:1px solid #aaa;
        box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
        border-radius:2px;
       }
       .premade_order_form input:focus, .premade_order_form textarea:focus {
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
       .premade_order_form input, .premade_order_form textarea {
        padding-right:30px;
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

       .premade_order_form input:focus:invalid, .premade_order_form textarea:focus:invalid { /* when a field is considered invalid by the browser */
        background: #fff url(${pageContext.request.contextPath}/images/red_star.png) no-repeat 98% center;
        box-shadow: 0 0 5px #d45252;
        border-color: #b03535
       }
       
       .premade_order_form input:focus:valid, .premade_order_form textarea:focus:valid { /* when a field is considered valid by the browser */
        box-shadow: 0 0 5px #5cd053;
        border-color: #28921f;
       }
       
       input, textarea {
        background: #fff no-repeat 98% center;
        background-image: url(${pageContext.request.contextPath}/images/red_star.png)
       }
       
       .premade_order_form input:focus + .form_hint {display: inline;}
       .premade_order_form input:focus:valid + .form_hint {background: #28921f;} /* change form hint color when valid */
       .premade_order_form input:focus:valid + .form_hint::before {color:#28921f;} /* change form hint arrow color when valid */

       .required_notification {
        color:#d45252;
        margin:5px 0 0 0;
        display:inline;
        float:right;
       }

    </style>
        <link rel="stylesheet" media="screen" href="styles.css" >
    </head>

    <body>
    <form class="premade_order_form" action="premade_order" method="post" name="premade_order_form">

<h1>Welcome to our New Year Sweety Gift shop!</h1>

<c:if test="${sessionContext.role == 'admin'}">
        <h2><a href = "${pageContext.request.contextPath}/app/admin_form"><span class="required_notification">
        Welcome to admin menu</a></h2> </span>
</c:if>

<c:if test="${sessionScope.role != null}">
        <h2><a href = "${pageContext.request.contextPath}/app/logout"><span class="required_notification">
        Logout here</a></h2> </span>
</c:if>

<p>&nbsp;</p>
<h3>As we stated before, we have carefully chosen the very best sweeties from our suppliers and organized them
    in 3 sizes of gift boxes - Small, Medium and Big ones.</h3>

    <h3><p><img src="${pageContext.request.contextPath}/images/small_gift_box.jpg" class="leftimg" align="top"
        alt="Small gift box" width="165" height="165" />Small one could hold up to 20 sweeties and weight up to 300 gramms.
        In premade one you will find inside 5 caramel candies, 4 chocolate candies, 3 jelly candies and 2 lollipops.
        Also you will find inside 2 waffles and 2 marshmallows.fff</p></h3>
        <label for="quantity_to_order_small_boxes">Quantity to order</label>
        <input type="number" min=0 max=999 value=0 name="quantity_to_order_small_boxes" required pattern="^(?![0-9]{4,})[0-9]{0,3}$"/>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<h3><img src="${pageContext.request.contextPath}/images/medium_gift_box.jpg" class="leftimg" align="top"
    alt="Medium gift box" width="165" height="165" />Medium&nbsp;one could hold up to 40 sweeties and weight up to 600 gramms.
    In premade one you will find inside 10 caramel candies, 8 chocolate candies, 6 jelly candies and 4 lollipops.
    Also you will find inside 4 waffles and 4 marshmallows.</h3>
    <label for="quantity_to_order_medium_boxes">Quantity to order</label>
    <input type="number" min=0 max=999 value=0 name="quantity_to_order_medium_boxes" required pattern="^(?![0-9]{4,})[0-9]{0,3}$"/>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<h3><img src="${pageContext.request.contextPath}/images/big_gift_box.jpg" class="leftimg" align="top"
    alt="Box gift box" width="165" height="165" />Big one could hold up to 60 sweeties and weight up to 900 gramms.
    In premade one you will find inside 15 caramel candies, 12 chocolate candies, 9 jelly candies and 6 lollipops.
    Also you will find inside 6 waffles and 6 marshmallows.</h3>
     <label for="quantity_to_order_big_boxes">Quantity to order</label>
     <input type="number" min=0 max=999 value=0 name="quantity_to_order_big_boxes" required pattern="^(?![0-9]{4,})[0-9]{0,3}$"/>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<button class="submit" type="submit">Submit Order</button>
<p>&nbsp;</p>
<p>&nbsp;</p>
<h2><a href = "${pageContext.request.contextPath}/app/custom_order">Go to make a custom order</a></h2>
<h2><a href = "${pageContext.request.contextPath}/app/return_to_main">Go to main page</a></h2>

</body>
</html>