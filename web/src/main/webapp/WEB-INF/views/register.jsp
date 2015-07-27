<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>AdminLTE 2 | Registration Page</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <!-- Bootstrap 3.3.2 -->
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- iCheck -->
    <link href="${pageContext.request.contextPath}/resources/plugins/iCheck/square/blue.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
</head>
<body class="register-page">
<div class="register-box">
    <div class="register-logo">
        <a href="/"><b>Intech</b>Web Messenger</a>
    </div>

    <div class="register-box-body">
        <p class="login-box-msg"><spring:message code="UI.Labels.Register.Registration"/></p>
        <form:form id="edit-form" action="${pageContext.request.contextPath}/user/create" method="post" commandName="user">
            <div class="form-group has-feedback">
                <spring:message code="UI.Labels.User.FirstName" var="firstNamePlaceholder"/>
                <form:input type="text" path="firstName" cssClass="form-control" placeholder='${firstNamePlaceholder}'/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <form:errors path="firstName" cssClass="help-inline" cssStyle="color: red;"/>
            </div>
            <div class="form-group has-feedback">
                <spring:message code='UI.Labels.User.LastName' var="lastNamePlaceholder"/>
                <form:input type="text" path="lastName" cssClass="form-control" placeholder="${lastNamePlaceholder}"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <form:errors path="lastName" cssClass="help-inline" cssStyle="color: red;"/>
            </div>
            <div class="form-group has-feedback">
                <spring:message code='UI.Labels.User.Email' var="emailPlaceholder"/>
                <form:input type="text" path="email" cssClass="form-control" placeholder="${emailPlaceholder}"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <form:errors path="email" cssClass="help-inline" cssStyle="color: red;"/>
            </div>
            <div class="form-group has-feedback">
                <spring:message code='UI.Labels.User.Password' var="passwordPlaceholder"/>
                <form:input type="text" path="password" cssClass="form-control" placeholder="${passwordPlaceholder}"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
                <form:errors path="password" cssClass="help-inline" cssStyle="color: red;"/>
            </div>
            <div class="row">
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">Register</button>
                </div><!-- /.col -->
            </div>
        </form:form>


        <a href="/login" class="text-center"><spring:message code="UI.Labels.Register.AlreadyHaveRegistered"/></a>
    </div><!-- /.form-box -->
</div><!-- /.register-box -->

<!-- jQuery 2.1.3 -->
<script src="${pageContext.request.contextPath}/resources/plugins/jQuery/jQuery-2.1.3.min.js"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="${pageContext.request.contextPath}/resources/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- iCheck -->
<script src="${pageContext.request.contextPath}/resources/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
<script>
    $(function () {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>