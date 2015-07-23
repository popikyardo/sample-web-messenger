<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

        <div class="col-md-5">
            <form:form id="edit-form" action="${pageContext.request.contextPath}/user/edit" method="post" commandName="user">
                <form:input type="hidden" path="id" />

                <div class="box box-solid">
                <div class="box-header with-border">
                    <h3><spring:message code="UI.Labels.User.General"/></h3>
                </div>
                <div class="box-body">
                    <table class="b-table table table-striped">
                        <tbody>
                            <tr>
                                <td>
                                    <spring:message code="UI.Labels.User.FirstName"/>
                                </td>
                                <td><form:input type="text" path="firstName" cssClass="form-control" />
                                    <form:errors path="firstName" cssClass="help-inline" cssStyle="color: red;"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="UI.Labels.User.LastName"/>
                                </td>
                                <td><form:input type="text" path="lastName" cssClass="form-control" />
                                    <form:errors path="lastName" cssClass="help-inline" cssStyle="color: red;"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <spring:message code="UI.Labels.User.Email"/>
                                </td>
                                <td><form:input type="text" path="email" cssClass="form-control" />
                                    <form:errors path="email" cssClass="help-inline" cssStyle="color: red;"/>
                                </td>
                            </tr>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <tr>
                                    <td>
                                        <spring:message code="UI.Labels.User.Role"/>
                                    </td>
                                    <td>
                                        <form:select path="userRole" cssClass="form-control">
                                            <c:forEach items="${roles}" var="role">
                                                <option value="${role.id}" <c:forEach items="${user.userRole}" var="uRole"> <c:if test="${role.id == uRole.id}">selected</c:if></c:forEach>><spring:message code="${role.name}"/></option>
                                            </c:forEach>
                                        </form:select>
                                        <form:errors path="userRole" cssClass="help-inline" cssStyle="color: red;"/>
                                    </td>
                                </tr>
                            </sec:authorize>
                            <tr>
                                <td>
                                    <spring:message code="UI.Labels.User.Password"/>
                                </td>
                                <td><form:input type="password" path="password" cssClass="form-control" />
                                    <form:errors path="password" cssClass="help-inline" cssStyle="color: red;"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <button class="btn btn-primary" type="submit" name="submit">
                        <spring:message code="UI.Labels.User.Submit"/>
                    </button>
                </div>
                </div>

            </form:form>
        </div>
