<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar">

    <!-- Sidebar user panel (optional) -->
    <div class="user-panel">
        <div class="pull-left image">
            <img src='/user/userpic?email=${currentUser.email}' class="img-circle" alt="User Image" />
        </div>
        <div class="pull-left info">
            <p>${currentUser.fullName}</p>
            <!-- Status -->
            <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
    </div>


    <!-- Sidebar Menu -->
    <ul class="sidebar-menu">
        <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
            <li class="header"><spring:message code="Control"/></li>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li><a href="/admin/users"><spring:message code="User.Users"/></a></li>
            </sec:authorize>
        </sec:authorize>

        <li class="header"><spring:message code="Preferences"/></li>
        <li><a href="/user/edit"><spring:message code="Profile"/></a></li>

    </ul><!-- /.sidebar-menu -->
</section>