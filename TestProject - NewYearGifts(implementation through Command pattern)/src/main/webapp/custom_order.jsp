<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="productRelated"/>
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

table {
    border-spacing: 0;
    width: 40%;
    border: 1px solid #ddd;
}

th, td {
    text-align: left;
    padding: 16px;
}

th {
    cursor: pointer;
}

tr:nth-child(even) {
    background-color: #f2f2f2
}

th form {
    display : table-cell;
    vertical-align: middle;
    margin : 0;
    padding : 0;
}
    </style>
        <link rel="stylesheet" media="screen" href="styles.css" >
    </head>

    <body onload="getTotalWeightAndDisplayIt()">

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


    <form class="custom_order_form" action="custom_order_processing" method="post" name="custom_order_form">

<h1><fmt:message key="product.title" /></h1>

<c:if test="${sessionContext.role == 'admin'}">
        <h2><a href = "${pageContext.request.contextPath}/app/admin_form"><span class="required_notification">
        <fmt:message key="product.adminMenu" /></a></h2> </span>
</c:if>
<p></p>
<c:if test="${sessionScope.role != null}">
        <h2><a href = "${pageContext.request.contextPath}/app/logout"><span class="required_notification">
        <fmt:message key="product.logout" /></a></h2> </span>
</c:if>

<br>
<h3><fmt:message key="custom.product.startMessage" /></h3>

    <h3><p><img src="${pageContext.request.contextPath}/images/small_gift_box.jpg" class="leftimg" align="top"
        alt="Small gift box" width="180" height="180" /><fmt:message key="custom.product.boxSelect" />
        <li><fmt:message key="custom.product.smallBoxCapacity" /></li>
        <li><fmt:message key="custom.product.mediumBoxCapacity" /></li>
        <li><fmt:message key="custom.product.bigBoxCapacity" /></li>
        <br>
        <fmt:message key="custom.product.selectChoice" /></h3>
        <br>
        <select name="boxType" id="box" onchange = "getTotalWeightAndDisplayIt()">
          <option value="1" <c:if test="${sessionScope.boxType == 1}">selected="selected"</c:if>>
            <fmt:message key="custom.product.smallBox" /></option>
          <option value="2" <c:if test="${sessionScope.boxType == 2}">selected="selected"</c:if>>
            <fmt:message key="custom.product.mediumBox" /></option>
          <option value="3" <c:if test="${sessionScope.boxType == 3}">selected="selected"</c:if>>
            <fmt:message key="custom.product.bigBox" /></option>
        </select>
<br>
<br>

<br>
<h3>
    <img src="${pageContext.request.contextPath}/images/sweeties.jpg" class="leftimg" align="top"
        alt="Medium gift box" width="180" height="180" />
        <fmt:message key="custom.product.itemsSelect" />
        <br>
        <li><fmt:message key="custom.product.caramel" /></li>
        <li><fmt:message key="custom.product.chocolate" /></li>
        <li><fmt:message key="custom.product.jelly" /></li>
        <li><fmt:message key="custom.product.lollipop" /></li>
        <li><fmt:message key="custom.product.waffle" /></li>
        <li><fmt:message key="custom.product.marshmallow" /></li>
</h3>

<div>
            <display:table name="sessionScope.sweeties"
                           sort="list" uid="one" requestURI = "">
                <display:column property="name" title="Name"
                                sortable="true" headerClass="sortable" />
                <display:column property="weight" title="Weight"
                                sortable="true" headerClass="sortable" />
                <display:column property="sugarValue" title="Sugar %"
                                sortable="true" headerClass="sortable" />
            </display:table>
        </div>
<br>
<label><fmt:message key="custom.product.filtering" /></label>
        <input type="number" min=0 max=100 name="sugarFrom" pattern="^(?![0-9]{4,})[0-9]{1,3}$"
            value="${sessionScope.sugarFrom}" />

        <input type="number" min=0 max=100 name="sugarTo" pattern="^(?![0-9]{4,})[0-9]{1,3}$"
            value="${sessionScope.sugarTo}" />

<button class="submit" type="submit" onclick='this.form.action="sugar_filter";'>
    <fmt:message key="custom.product.sort" /></button>

<br>
<br>
<br>

<br>
    <label><fmt:message key="custom.product.caramelQty" /></label>
        <input type="number" min=0 max=999 name="caramel_qty" id="caramel" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
            onchange = "getTotalWeightAndDisplayIt()"
            <c:choose>

                 <c:when test = "${not empty sessionScope.caramelQty}">
                    value="${sessionScope.caramelQty}"
                 </c:when>

                 <c:otherwise>
                    value=0
                 </c:otherwise>

            </c:choose>
         />

            <br>
   <label><fmt:message key="custom.product.chocolateQty" /></label>
        <input type="number" min=0 max=999 name="chocolate_qty" id="chocolate" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
            onchange = "getTotalWeightAndDisplayIt()"
             <c:choose>

                <c:when test = "${not empty sessionScope.chocolateQty}">
                    value="${sessionScope.chocolateQty}"
                </c:when>

                <c:otherwise>
                    value=0
                </c:otherwise>

              </c:choose>
        />
            <br>
   <label><fmt:message key="custom.product.jellyQty" /></label>
           <input type="number" min=0 max=999 name="jelly_qty" id="jelly" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"
                <c:choose>

                    <c:when test = "${not empty sessionScope.jellyQty}">
                        value="${sessionScope.jellyQty}"
                    </c:when>

                    <c:otherwise>
                        value=0
                    </c:otherwise>

                </c:choose>
           />
            <br>
   <label><fmt:message key="custom.product.lollipopQty" /></label>
           <input type="number" min=0 max=999 name="lollipop_qty" id="lollipop" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"
               <c:choose>

                    <c:when test = "${not empty sessionScope.lollipopQty}">
                        value="${sessionScope.lollipopQty}"
                    </c:when>

                    <c:otherwise>
                        value=0
                    </c:otherwise>

               </c:choose>
           />
            <br>
   <label><fmt:message key="custom.product.waffleQty" /></label>
           <input type="number" min=0 max=999 name="waffle_qty" id="waffle" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"
               <c:choose>

                    <c:when test = "${not empty sessionScope.waffleQty}">
                        value="${sessionScope.waffleQty}"
                    </c:when>

                    <c:otherwise>
                        value=0
                    </c:otherwise>

               </c:choose>
           />
            <br>
   <label><fmt:message key="custom.product.marshmallowQty" /></label>
           <input type="number" min=0 max=999 name="marshmallow_qty" id="marshmallow" required pattern="^(?![0-9]{4,})[0-9]{1,3}$"
               onchange = "getTotalWeightAndDisplayIt()"
               <c:choose>

                    <c:when test = "${not empty sessionScope.marshmallowQty}">
                        value="${sessionScope.marshmallowQty}"
                    </c:when>

                    <c:otherwise>
                        value=0
                    </c:otherwise>

               </c:choose>
           />

        <b><p id="weight"><fmt:message key="custom.product.totalSweetiesWeight" /></p></b>
        <b><p id="box_total_weight"><fmt:message key="custom.product.boxWeight" /></p></b>

<br>
<br>
<br>

<h3><p><img src="${pageContext.request.contextPath}/images/NYgifts.png" class="leftimg" align="top"
        alt="Small gift box" width="180" height="180" /><fmt:message key="custom.product.qtySelect" />
         <br>
        <fmt:message key="custom.product.qtySelectMessage" /></h3>
        <br>
        <label><fmt:message key="custom.product.qtySelectMessage2" /></label>
                <input type="number" min=1 max=999 name="custom_order_qty" pattern="^(?![0-9]{4,})[0-9]{1,3}$"
                    <c:choose>

                        <c:when test = "${not empty sessionScope.customOrderQty}">
                            value="${sessionScope.customOrderQty}"
                        </c:when>

                        <c:otherwise>
                            value=1
                        </c:otherwise>

                    </c:choose>
                />
        <br>

<button class="submit" type="submit" onclick="return checkFunction();"><fmt:message key="product.submitOrder" /></button>
<br>
<br>
<br>
<br>
<h2>
    <a href = "${pageContext.request.contextPath}/app/products">
        <fmt:message key="custom.product.linkToPremadeGifts" />
    </a>
</h2>
<h2>
    <a href = "${pageContext.request.contextPath}/app/return_to_main">
        <fmt:message key="product.linkToMainPage" />
    </a>
</h2>

</form>
</body>
</html>