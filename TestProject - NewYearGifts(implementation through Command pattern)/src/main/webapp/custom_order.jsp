<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Custom Sweet Gift Creation</title>
    <style>
       .leftimg {
        float:left; /* Выравнивание по левому краю */
        margin: 7px 7px 7px 0; /* Отступы вокруг картинки */
       }

        body {font: 14px/21px "Lucida Sans", "Lucida Grande", "Lucida Sans Unicode", sans-serif;}
       .custom_order_form h2, .custom_order_form label {font-family:Georgia, Times, "Times New Roman", serif;}
       .form_hint, .required_notification {font-size: 16px;}

       .custom_order_form label {
        width:150px;
        margin-top: 3px;
        display:inline-block;
        padding:3px;
       }
       .custom_order_form input {
        height:20px;
        width:220px;
        padding:5px 8px;
       }
       .custom_order_form textarea {padding:8px; width:300px;}
       .custom_order_form button {margin-left:15px;}

       .custom_order_form input, .custom_order_form textarea {
        border:1px solid #aaa;
        box-shadow: 0px 0px 3px #ccc, 0 10px 15px #eee inset;
        border-radius:2px;
       }
       .custom_order_form input:focus, .custom_order_form textarea:focus {
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
       .custom_order_form input, .custom_order_form textarea {
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

       .custom_order_form input:focus:invalid, .custom_order_form textarea:focus:invalid { /* when a field is considered invalid by the browser */
        background: #fff url(${pageContext.request.contextPath}/images/red_star.png) no-repeat 98% center;
        box-shadow: 0 0 5px #d45252;
        border-color: #b03535
       }

       .custom_order_form input:focus:valid, .custom_order_form textarea:focus:valid { /* when a field is considered valid by the browser */
        box-shadow: 0 0 5px #5cd053;
        border-color: #28921f;
       }

       input, textarea {
        background: #fff no-repeat 98% center;
        background-image: url(${pageContext.request.contextPath}/images/red_star.png)
       }

       .custom_order_form input:focus + .form_hint {display: inline;}
       .custom_order_form input:focus:valid + .form_hint {background: #28921f;} /* change form hint color when valid */
       .custom_order_form input:focus:valid + .form_hint::before {color:#28921f;} /* change form hint arrow color when valid */

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

    <script>

    function getTotalWeightAndDisplayIt(){
        var weight = getTotalWeight();
        returnTotalWeight(weight);
    }

    function getTotalWeight(){
        var weight = caramelCandyTotalWeight() + chocolateCandyTotalWeight() + jellyCandyTotalWeight() +
            lollipopCandyTotalWeight() + waffleTotalWeight() + marshmallowTotalWeight();
        return weight;
    }

    function caramelCandyTotalWeight() {
        var caramelWeight = 0;
        caramelWeight = document.getElementById('caramel').value * 12;
        return caramelWeight;
    }

    function chocolateCandyTotalWeight() {
        var chocolatelWeight = 0;
        chocolateWeight = document.getElementById('chocolate').value * 13;
        return chocolateWeight;
    }

    function jellyCandyTotalWeight() {
        var jellylWeight = 0;
        jellyWeight = document.getElementById('jelly').value * 11;
        return jellyWeight;
    }

    function lollipopCandyTotalWeight() {
        var lollipopWeight = 0;
        lollipopWeight = document.getElementById('lollipop').value * 16;
        return lollipopWeight;
    }

    function waffleTotalWeight() {
        var waffleWeight = 0;
        waffleWeight = document.getElementById('waffle').value * 18;
        return waffleWeight;
    }

    function marshmallowTotalWeight() {
        var marshmallowWeight = 0;
        marshmallowWeight = document.getElementById('marshmallow').value * 9;
        return marshmallowWeight;
    }


    function returnTotalWeight(value){
        document.getElementById("weight").innerHTML = "Total sweeties weight: " + value + " gramms";
        var boxWeight = getBoxWeight();
        document.getElementById("box_total_weight").innerHTML = "You have selected box that can hold up to: " + boxWeight+ " gramms";
    }

    function checkFunction() {
    if (getTotalWeight() > getBoxWeight()) {
    alert("You have chosen more candies than box can hold");
    return false;
    }
    else{
    return true;
    }
    }

function getBoxWeight(){
        var boxWeight = 0;
        boxWeight = document.getElementById('box').value * 300;
        return boxWeight;
    }

    </script>

    <form class="custom_order_form" action="custom_order_process" method="post" name="custom_order_form">

<h1>Welcome to our New Year Sweety Gift shop!</h1>

<c:if test="${sessionContext.role == 'admin'}">
        <h2><a href = "${pageContext.request.contextPath}/app/admin_form"><span class="required_notification">
        Welcome to admin menu</a></h2> </span>
</c:if>
<p></p>
<c:if test="${sessionScope.role != null}">
        <h2><a href = "${pageContext.request.contextPath}/app/logout"><span class="required_notification">
        Logout here</a></h2> </span>
</c:if>

<br>
<h3>Here you can make your own custom New Year Sweet Gift following our easy steps below.</h3>

    <h3><p><img src="${pageContext.request.contextPath}/images/small_gift_box.jpg" class="leftimg" align="top"
        alt="Small gift box" width="180" height="180" />Lets start from choosing gift box. There are 3 possible types:
        <li>Small box could weight up to 300 gramms.</li>
        <li>Medium box could weight up to 600 gramms.</li>
        <li>Big box could weight up to 900 gramms.</li>
        <br>
        Please, select box type you would like to have.</h3>
        <br>
        <select name="boxType" id="box" onchange = "getTotalWeightAndDisplayIt()">
          <option value="1">Small</option>
          <option value="2">Medium</option>
          <option value="3">Big</option>
        </select>
<br>
<br>
<br>
<h3><img src="${pageContext.request.contextPath}/images/sweeties.jpg" class="leftimg" align="top"
    alt="Medium gift box" width="180" height="180" />Now lets choose what will be inside our gift box.
    <p>There are the following possible sweeties:</p>
        <li>Caramel candy that weights 12  gramms and consists of sugar on 80%</li>
        <li>Chocolate candy that weights 13  gramms and consists of sugar on 60%</li>
        <li>Jelly candy that weights 11  gramms and consists of sugar on 40%</li>
        <li>Lollipop candy that weights 16  gramms and consists of sugar on 90%</li>
        <li>Waffle that weights 18  gramms and consists of sugar on 20%</li>
        <li>Marshmallow that weights 9  gramms and consists of sugar on 30%</li>
            </h3>
    <label>Number of Caramel candies:</label>
        <input type="number" min=0 max=999 value=0 name="caramel_qty" id="caramel" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
        onchange = "getTotalWeightAndDisplayIt()"/>
            <br>
   <label>Number of Chocolate candies</label>
        <input type="number" min=0 max=999 value=0 name="chocolate_qty" id="chocolate" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
            onchange = "getTotalWeightAndDisplayIt()"/>
            <br>
   <label>Number of Jelly candies</label>
           <input type="number" min=0 max=999 value=0 name="jelly_qty" id="jelly" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"/>
            <br>
   <label>Number of Lollipop candies</label>
           <input type="number" min=0 max=999 value=0 name="lollipop_qty" id="lollipop" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"/>
            <br>
   <label>Number of Waffles</label>
           <input type="number" min=0 max=999 value=0 name="waffle_qty" id="waffle" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"/>
            <br>
   <label>Number of Marshmallows</label>
           <input type="number" min=0 max=999 value=0 name="marshmallow_qty" id="marshmallow" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"/>
        <b><p id="weight">Total sweeties weight: 0 gramms</p></b>
        <b><p id="box_total_weight">You have selected box that can hold up to: 300 gramms</p></b>

<br>
<br>
<br>

<h3><p><img src="${pageContext.request.contextPath}/images/NYgifts.png" class="leftimg" align="top"
        alt="Small gift box" width="180" height="180" />Now, all you need to do is to put number of such custom New Year
        Gifts you would like to order.
         <br>
        Please, select required quantity of gifts</h3>
        <br>
        <label>Number of Custom gifts to order:</label>
                <input type="number" min=1 max=999 value=1 name="custom_order_qty" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"/>
        <br>

<button class="submit" type="submit" onclick="return checkFunction();">Submit Order</button>
<br>
<br>
<br>
<br>
<h2><a href = "${pageContext.request.contextPath}/app/products">Go to see our premade offers</a></h2>
<h2><a href = "${pageContext.request.contextPath}/app/return_to_main">Go to main page</a></h2>

</body>
</html>