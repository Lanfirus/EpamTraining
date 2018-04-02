<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <title>Login_error Form</title>
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
 background: #fff url(${pageContext.request.contextPath}/images/red_star.png) no-repeat 98% center;
 box-shadow: 0 0 5px #d45252;
 border-color: #b03535
}

.contact_form input:required:valid, .contact_form textarea:required:valid { /* when a field is considered valid by the browser */
 box-shadow: 0 0 5px #5cd053;
 border-color: #28921f;
}

input:required, textarea:required {
 background: #fff no-repeat 98% center;
 background-image: url(${pageContext.request.contextPath}/images/red_star.png)
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
<form class="contact_form" action="registration" method="post" name="contact_form">

    <ul>
        <li>
            <h2>Registration form</h2>
            <span class="required_notification">* Denotes Required Field</span>
        </li>
<li>
            <h2>Problem with login</h2>

</li>
        <li>
            <label for="surname">Surname:</label>
            <input type="text" name="surname" placeholder="Ivan" required value=<%= request.getAttribute("surnameUser")%>
                            pattern="^(?!.*?([ `'\-]{2,}|[A-Z]{2,}|[A-Z `'\-]$))[A-Z]{1}[a-z\- '`A-Z]+$"/>
            <span class="form_hint">Please, enter Surname. It should begin from capital letter and have at least one letter.
                                                Allowed characters: Latin alphabet and special characters [- ']. Surname can't begin from special
                                                character. It also can't end on special character or capital letter. Surname can't have two or more
                                                consecutive special characters or capital letters.</span>

        </li>

        <li>
            <label for="name">Name:</label>
            <input type="text" name="name" placeholder="Ivan" required value=<%= request.getAttribute("nameUser")%>
                        pattern="^(?!.*?([ `'\-]{2,}|[A-Z]{2,}|[A-Z `'\-]$))[A-Z]{1}[a-z\- '`A-Z]+$"/>
            <span class="form_hint">Please, enter Name. It should begin from capital letter and have at least one letter.
                                    Allowed characters: Latin alphabet and special characters [- ']. Name can't begin from special
                                    character. It also can't end on special character or capital letter. Name can't have two or more
                                    consecutive special characters or capital letters.</span>
        </li>

        <li>
            <label for="patronymic">Patronymic:</label>
            <input type="text" name="patronymic" placeholder="Ivanovich" value="<%= request.getAttribute("patronymicUser")%>"
                    pattern="^(?!.*?([ `'\-]{2,}|[A-Z]{2,}|[A-Z `'\-]$))[A-Z]{1}[a-z\- '`A-Z]+|$"/>
            <span class="form_hint">Please, enter Patronymic. It should begin from capital letter and have at least one letter.
                                    Allowed characters: Latin alphabet and special characters [- ']. Surname can't begin from special
                                    character. It also can't end on special character or capital letter. Surname can't have two or more
                                    consecutive special characters or capital letters. There could be no patronymic.</span>
        </li>

        <li>
            <label for="login">Login:</label>
            <input type="text" name="login" placeholder="login" required value=<%= request.getAttribute("loginUser")%>
                    pattern="^(?!<%= request.getAttribute("loginUser")%>).+"/>
            <span class="form_hint">Please, enter login that is differ from this one!. This login has been already used
                    in our system by someone else. It should have at least one symbol. All symbols are allowed.</span>
                    <p>Warning: You have to choose another login!</p>
        </li>

        <li>
                    <label for="password">Password:</label>
                    <input type="password" name="password" placeholder="password" required
                            pattern="^(?!<%= request.getAttribute("loginUser")%>).+"/>
                    <span class="form_hint">It should have at least one symbol. All symbols are allowed.</span>
                </li>

        <li>
            <label for="comment">Comment:</label>
            <input type="text" name="comment" placeholder="Any comment" value=<%= request.getAttribute("commentUser")%>
                    pattern=".+"/>
            <span class="form_hint">Please, enter comment. It should have at least one symbol. All symbols are allowed.</span>
        </li>

        <li>
            <label for="homephonenumber">Home phone number:</label>
            <input type="text" name="homephonenumber" placeholder="380441234567"
            value=<%= request.getAttribute("homephonenumberUser")%> pattern="^(?:380\d{9}|\d{10,12})$"/>
            <span class="form_hint">Please, enter home phone number. It should be a set from 10, 11 or 12 digits.
                                    For example, 380441234567.</span>
        </li>

        <li>
            <label for="mobilephonenumber">Mobile phone number:</label>
            <input type="text" name="mobilephonenumber" placeholder="380501234567" required
                    value=<%= request.getAttribute("mobilehonenumberUser")%> pattern="^(?:380\d{9}|\d{10,12})$"/>
            <span class="form_hint">Please, enter mobile phone number. It should be a set from 10, 11 or 12 digits.
                                    For example, 380501234567.</span>
        </li>

        <li>
            <label for="email">Email:</label>
            <input type="text" name="email" placeholder="email@email.com" required value=<%= request.getAttribute("emailUser")%>
                    pattern="^(?!.*?[._\-]{2,})[a-zA-Z0-9]{1}[a-zA-Z0-9._\-]{0,62}[a-zA-Z0-9]?@{1}(?!.{256,})([a-zA-Z0-9]{1}[a-zA-Z0-9._\-]{1,254}[a-zA-Z0-9]{1}|\[{1}\d{1,3}\.{1}\d{1,3}\.{1}\d{1,3}\.{1}\d{1,3}\]{1})$"/>
            <span class="form_hint">Please, enter email. First part (before @) could have length from 1 to 64 characters.
                                    It can consist of Latin alphabet's characters and special characters [._-]. This part can't begin or end
                                    from special character. Two or more consecutive special characters are not allowed.
                                    Second part (after @) could have length from 3 to 254 characters, consist of characters of Latin alphabet
                                    digits and special characters [.-]. This part can't begin or end from special character
                                    Two or more consecutive special characters are not allowed.
                                    It's allowed to use IP-address as a second part of email. For example, [128.0.0.1]</span>
        </li>

        <li>
            <button class="submit" type="submit">Submit Form</button>
        </li>

    </ul>

</form>
</body>
</html>