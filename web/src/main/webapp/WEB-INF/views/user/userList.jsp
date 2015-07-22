<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<link href="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />

<div class="col-xs-12">
    <div class="box">
        <div class="box-header">
            <h3 class="box-title"><spring:message code="User.Users"/></h3>
        </div><!-- /.box-header -->
        <div class="box-body">
            <table id="courses" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th></th>
                    <th><spring:message code="UI.Labels.User.FirstName"/></th>
                    <th><spring:message code="UI.Labels.User.LastName"/></th>
                    <th><spring:message code="UI.Labels.User.Created"/></th>
                    <th><spring:message code="UI.Labels.User.Email"/></th>
                    <th><spring:message code="UI.Labels.User.Role"/></th>
                    <th></th>
                </tr>
                </thead>

            </table>
        </div><!-- /.box-body -->
    </div><!-- /.box -->
</div><!-- /.col -->

<!-- DATA TABES SCRIPT -->
<script src="${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>

<script>
    $(document).ready(function() {
        $('#courses').dataTable( {
            "bServerSide": true,
            "bProcessing": true,
            "sAjaxSource": "/user/data",
            "sPaginationType": "bootstrap",
            "aoColumns": [
                { "mData": "picture",
                    "mRender": function( data, type, full) {
                        return '<img src="/images/user/'+data+'" class="img-thumbnail">';
                    }
                },
                { "mData": "firstName" },
                { "mData": "lastName" },
                { "mData": "createAt",
                    "mRender": function( data, type, full) {
                        return (new Date(data)).toLocaleDateString();
                    }
                },
                { "mData": "email",
                    "mRender": function( data, type, full) {
                        var res = '';
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        res += '<a href="/j_spring_security_switch_user?j_username='+data+'"><i class="fa fa-mail-forward"></i></a>';
                        </sec:authorize>
                        res += '&nbsp;&nbsp;&nbsp;' + data;
                        return res;
                    }
                },
                { "mData": "id",
                    "mRender": function( data, type, full) {
                        var res =  '<a href="/user/'+data+'/view"><i class="fa fa-eye"></i></a>';
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                        res += '&nbsp;&nbsp;&nbsp;<a href="/user/edit?userId='+data+'"><i class="fa fa-pencil"></i></a>';
                        </sec:authorize>
                        return res;
                    }
                }
            ]
        } );
    } );
</script>