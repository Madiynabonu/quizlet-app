<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--<%@ include file="/fragments/css.jsp" %>--%>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <style>
        body, html {
            height: 100%;
            background: #ffffff;
            overflow-x: hidden;
            font-family: 'Dosis', sans-serif;
        }

        btn {
            border-radius: 0
        }

        .btn:focus, .btn:active, .btn.active, .btn:active:focus {
            outline: 0;
            border-radius: 0
        }

        .btn-larger {
            padding: 15px 40px !important;
            border: 2px solid #18f7c3 !important;;
            border-radius: 0px !important;;
            text-transform: uppercase;
            font-family: 'Dosis', sans-serif;
            font-size: 18px;
            font-weight: 300;
            color: #F7CA18;
            background-color: transparent;
            -webkit-transition: all .6s;
            -moz-transition: all .6s;
            transition: all .6s;
        }

        .btn-larger:hover, .btn-larger:focus, .btn-larger:active, .btn-larger.active, .open .dropdown-toggle.btn-larger {
            border-color: #F7CA18;
            color: #fff;
            background-color: #F7CA18;
            border-radius: 0
        }

        .btn-larger:active, .btn-larger.active, .open .dropdown-toggle.btn-larger {
            background-image: none;
        }

        .btn-larger.disabled, .btn-larger[disabled], fieldset[disabled] .btn-larger, .btn-larger.disabled:hover, .btn-larger[disabled]:hover, fieldset[disabled] .btn-larger:hover, .btn-larger.disabled:focus, .btn-larger[disabled]:focus, fieldset[disabled] .btn-larger:focus, .btn-larger.disabled:active, .btn-larger[disabled]:active, fieldset[disabled] .btn-larger:active, .btn-larger.disabled.active, .btn-larger[disabled].active, fieldset[disabled] .btn-larger.active {
            border-color: #AEA8D3;
            background-color: #AEA8D3;
        }

        .btn-larger .badge {
            color: #AEA8D3;
            background-color: #fff;
        }

        div#form {
            color: #fff;
            background-attachment: scroll;
            /*background: #1c1e21 url(https://static.pexels.com/photos/8819/warsaw.jpg);*/
            background-position: center center;
            background-repeat: no-repeat;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            background-size: cover;
            -o-background-size: cover;
            min-height: 100%;
        }

        #userform p {
            font-size: 14px;
            margin-bottom: 5px;
        }

        #userform ul {
            list-style-type: none;
            padding: 0;
            margin-bottom: 0px;
        }

        #userform {
            background: rgba(0, 0, 0, 0.8);
            margin: 20px 0 20px 0
        }

        @media (min-width: 768px) {
            #userform {
                background: rgba(0, 0, 0, 0.8);
                margin: 50px 0 20px 0
            }
        }

        #userform .nav-tabs.nav-justified > li > a {
            text-transform: uppercase;
            font-size: 20px;
            color: #F7CA18;
            background-color: rgba(90, 90, 90, 0.5);
        }

        #userform .nav-tabs.nav-justified > .active > a, #userform .nav-tabs.nav-justified > .active > a:hover, #userform .nav-tabs.nav-justified > .active > a:focus {
            border: 0;
            background: #F7CA18;
            color: white;
            border-radius: 0;
        }

        #userform .nav-justified > li > a {
            margin-bottom: 0;
            -webkit-transition: all .6s;
            -moz-transition: all .6s;
            transition: all .6s;
        }

        #userform .nav-justified > li > a:hover {
            background: #AEA8D3;
            color: #FFF;
        }

        #userform .nav-tabs > li > a {
            border: 0px solid transparent;
            border-radius: 0
        }

        #userform .nav-tabs.nav-justified > li > a:hover {
            background: #F7CA18;
            color: #FFF;
            border-radius: 0;
            border: 0;
            -webkit-transition: all .6s;
            -moz-transition: all .6s;
            transition: all .6s;
        }

        #userform .nav-tabs > li.active > a, #userform .nav-tabs > li.active > a:hover, #userform .nav-tabs > li.active > a:focus {
            color: #F7CA18;
            cursor: default;
            background-color: transparent;
            border: 0;
            -webkit-transition: all .6s;
            -moz-transition: all .6s;
            transition: all .6s;
        }

        @media (min-width: 768px) {
            #userform .nav-tabs.nav-justified > li > a {
                border: 0;
                -webkit-transition: all .6s;
                -moz-transition: all .6s;
                transition: all .6s;
            }

            #userform .nav-tabs.nav-justified > li > a:hover {
                background-color: #F7CA18;
                border-color: transparent;
                border: 0;
                -webkit-transition: all .6s;
                -moz-transition: all .6s;
                transition: all .6s;
            }
        }

        @media (max-width: 768px) {
            .nav-justified > li {
                display: table-cell !important;
                width: 1% !important;
            }
        }

        #userform .nav-tabs {
            border-bottom: 0px solid #ddd;
        }

        #userform .tab-pane h2 {
            margin: 10px 0;
            color: #FFF;
        }

        #userform .tab-pane p.lead {
            margin-top: 20px;
        }

        #userform .tab-content {
            padding: 20px
        }

        #userform .form-group {
            margin-bottom: 0px;
            color: #FFF;
        }

        #userform .form-group input, #userform .form-group textarea {
            padding: 10px;
        }

        #userform .form-group input.form-control {
            height: auto;
            background-color: rgba(237, 235, 250, 0.1);
            color: #FFF;
        }

        #userform .form-control {
            border-radius: 0;
            border: 1px solid #fff;
        }

        #userform .form-control:focus {
            border-color: #F7CA18;
            box-shadow: none;
        }

        #userform::-webkit-input-placeholder {
            text-transform: uppercase;
            font-family: 'Dosis', sans-serif;
            font-weight: 700;
            color: #bbb;
        }

        #userform #signup .form-group label {
            position: relative;
            -webkit-transform: translateY(35px);
            -ms-transform: translateY(35px);
            transform: translateY(35px);
            left: 10px;
            top: 0px;
            color: rgba(255, 255, 255, 0.5);
            -webkit-transition: all 0.25s ease;
            transition: all 0.25s ease;
            -webkit-backface-visibility: hidden;
            pointer-events: none;
            font-size: 12px;
            font-weight: 300
        }

        #userform #signup .form-group label .req {
            margin: 2px;
            color: #F7CA18;
        }

        #userform #signup .form-group label.active {
            -webkit-transform: translateY(0px);
            -ms-transform: translateY(0px);
            transform: translateY(0px);
            left: 2px;
            font-size: 12px;
        }

        #userform #signup .form-group label.active .req {
            opacity: 0;
        }

        #userform label.highlight {
            color: #ffffff;
        }

        #userform #login .form-group label {
            position: relative;
            -webkit-transform: translateY(35px);
            -ms-transform: translateY(35px);
            transform: translateY(35px);
            left: 10px;
            top: 0px;
            color: rgba(255, 255, 255, 0.5);
            -webkit-transition: all 0.25s ease;
            transition: all 0.25s ease;
            -webkit-backface-visibility: hidden;
            pointer-events: none;
            font-size: 12px;
            font-weight: 300
        }

        #userform #login .form-group label .req {
            margin: 2px;
            color: #F7CA18;
        }

        #userform #login .form-group label.active {
            -webkit-transform: translateY(0px);
            -ms-transform: translateY(0px);
            transform: translateY(0px);
            left: 2px;
            font-size: 12px;
        }

        #userform #login .form-group label.active .req {
            opacity: 0;
        }

        .mrgn-30-top {
            margin-top: 30px
        }
    </style>
</head>
<body>
<div id="form">
    <div class="container">
        <div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-md-8 col-md-offset-2">
            <div id="userform">
                <ul class="nav nav-tabs nav-justified" role="tablist">
                    <li><a href="#signup" role="tab" data-toggle="tab">Sign up</a></li>
                    <li class="active"><a href="#login" role="tab" data-toggle="tab">Log in</a></li>
                </ul>
                <div class="tab-content">

                    <div class="tab-pane fade active in" id="login">
                        <h2 class="text-uppercase text-center"> Log in Page</h2>
                        <form id="login" method="post" action="/login?next=${next}">
                            <div class="form-group">
                                <label> Your Email<span class="req">*</span> </label>
                                <input type="email" class="form-control" id="email" required
                                       data-validation-required-message="Please enter your email address."
                                       autocomplete="on" name="loginemail">
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <label> Password<span class="req">*</span> </label>
                                <input type="password" class="form-control" id="password" required
                                       data-validation-required-message="Please enter your password" autocomplete="off"
                                       name="loginpassword">
                                <p class="help-block text-danger"></p>
                            </div>
                            <span style="color: red">${login_credentials}</span>
                            <div class="mrgn-30-top">
                                <button type="submit" class="btn btn-larger btn-block"/>
                                Log in
                                </button>
                                <span style="color: red;margin-top: 5px">Doesn't have account <a
                                        href="/signup">Register</a></span>
                            </div>
                        </form>
                    </div>

                    <div class="tab-pane fade in" id="signup">
                        <h2 class="text-uppercase text-center"> Sign Up Page</h2>
                        <form id="signup" method="post" action="/signup">
                            <div class="form-group">
                                <label> Your Email <span class="req">*</span> </label>
                                <input type="email" class="form-control" id="email" required
                                       data-validation-required-message="Please enter your email address."
                                       autocomplete="on" name="signupemail">
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <label> Your username <span class="req">*</span> </label>
                                <input type="text" class="form-control" id="phone" required
                                       data-validation-required-message="Please enter your username."
                                       autocomplete="on" name="signupusername">
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group">
                                <label> Password<span class="req">*</span> </label>
                                <input type="password" class="form-control" id="password" required
                                       data-validation-required-message="Please enter your password" autocomplete="off"
                                       name="signuppassword">
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="mrgn-30-top">
                                <button type="submit" class="btn btn-larger btn-block"/>
                                Sign up
                                </button>
                                <span style="color: red;margin-top: 5px">Already have account <a href="/login">Login</a></span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/jquery.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<%--<%@ include file="/fragments/js.jsp" %>--%>

</body>
</html>