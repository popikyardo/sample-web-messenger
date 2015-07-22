<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="error-page">
    <h2 class="headline text-yellow">403</h2>
    <div class="error-content">
        <h3><i class="fa fa-warning text-yellow"></i> Oops! Page not found.</h3>
        <p>
            К сожалению, вы не авторизованы. Попробуйте <a href='${pageContext.request.contextPath}/login'>войти</a>.
        </p>
    </div><!-- /.error-content -->
</div><!-- /.error-page -->