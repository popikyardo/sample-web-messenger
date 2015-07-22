<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- Logo -->
<a href="/" class="logo"><b>Distant</b></a>

<!-- Header Navbar -->
<nav class="navbar navbar-static-top" role="navigation">
    <!-- Sidebar toggle button-->
    <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
    </a>
    <!-- Navbar Right Menu -->
    <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
            <!-- Messages: style can be found in dropdown.less-->
            <li class="dropdown messages-menu">
                <!-- Menu toggle button -->
<%--                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-envelope-o"></i>
                    <span class="label label-success">4</span>
                </a>
                <ul class="dropdown-menu">
                    <li class="header">You have 4 messages</li>
                    <li>
                        <!-- inner menu: contains the messages -->
                        <ul class="menu">
                            <li><!-- start message -->
                                <a href="#">
                                    <div class="pull-left">
                                        <!-- User Image -->
                                        <img src='/images/user/${currentUser.picture}' class="img-circle" alt="User Image"/>
                                    </div>
                                    <!-- Message title and timestamp -->
                                    <h4>
                                        Support Team
                                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                                    </h4>
                                    <!-- The message -->
                                    <p>Why not buy a new awesome theme?</p>
                                </a>
                            </li><!-- end message -->
                        </ul><!-- /.menu -->
                    </li>
                    <li class="footer"><a href="#">See All Messages</a></li>
                </ul>
            </li><!-- /.messages-menu -->
--%>
            <!-- Notifications Menu -->
                <sec:authorize access="hasRole('ROLE_PREVIOUS_ADMINISTRATOR')">
                <li>
                    <a href="${pageContext.request.contextPath}/j_spring_security_exit_user">
                        <spring:message code="SwitchBack"/>
                    </a>
                </li>
                </sec:authorize>

            <li class="dropdown notifications-menu">
                <!-- Menu toggle button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="fa fa-bell-o"></i>
                    <span class="label label-warning">10</span>
                </a>
                <ul class="dropdown-menu">
                    <li class="header">You have 10 notifications</li>
                    <li>
                        <!-- Inner Menu: contains the notifications -->
                        <ul class="menu">
                            <li><!-- start notification -->
                                <a href="#">
                                    <i class="fa fa-users text-aqua"></i> 5 new members joined today
                                </a>
                            </li><!-- end notification -->
                        </ul>
                    </li>
                    <li class="footer"><a href="#">View all</a></li>
                </ul>
            </li>
            <!-- User Account Menu -->
            <li class="dropdown user user-menu">
                <!-- Menu Toggle Button -->
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <!-- The user image in the navbar-->
                    <img src='http://en.rascheskoff.ru/data/uploads/userpic_male.gif' class="user-image" alt="User Image"/>
                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                    <span class="hidden-xs">${currentUser.fullName}</span>
                </a>
                <ul class="dropdown-menu">
                    <!-- The user image in the menu -->
                    <li class="user-header">
                        <img src='http://en.rascheskoff.ru/data/uploads/userpic_male.gif' class="img-circle" alt="User Image" />
                        <p>
                            ${currentUser.fullName} - <c:forEach items="${currentUser.userRole}" var="role">
                                <spring:message code="${role.name}"/>
                            </c:forEach>
                            <small><spring:message code="Header.MemberSince" /> Nov. 2012</small>
                        </p>
                    </li>

                    <!-- Menu Body -->
                    <li class="user-body">
                        <div class="col-xs-4 text-center">
                            <a href="/?lang=en">English</a>
                        </div>
                        <div class="col-xs-4 text-center">
                            <a href="/?lang=ru">Русский</a>
                        </div>
                    </li>
                    <!-- Menu Footer-->
                    <li class="user-footer">
                        <div class="pull-left">
                            <a href="${pageContext.request.contextPath}/user/edit" class="btn btn-default btn-flat"><spring:message code="Profile"/></a>
                        </div>
                        <div class="pull-right">
                            <a href="${pageContext.request.contextPath}/logout" class="btn btn-default btn-flat"><spring:message code="Logout"/></a>
                        </div>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>