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
            <table id="users" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th><spring:message code="UI.Labels.User.Photo"/></th>
                    <th><spring:message code="UI.Labels.User.FirstName"/></th>
                    <th><spring:message code="UI.Labels.User.LastName"/></th>
                    <th><spring:message code="UI.Labels.User.Created"/></th>
                    <th><spring:message code="UI.Labels.User.Email"/></th>
                    <th><spring:message code="UI.Labels.User.Actions"/></th>
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
        $('#users').dataTable( {
            "bServerSide": true,
            "bProcessing": true,
            "sAjaxSource": "/user/data",
            "sPaginationType": "bootstrap",
            "aoColumns": [
                { "mData": "email",
                    "mRender": function( data, type, full) {
                        return '<img src="/user/userpic?email='+data+'" class="img-thumbnail">';
                    }
                },
                { "mData": "firstName" },
                { "mData": "lastName" },
                { "mData": "createAt",
                    "mRender": function( data, type, full) {
                        return (new Date(data)).toLocaleDateString();
                    }
                },
                { "mData": "email" },
                { "mData": "id",
                    "mRender": function( data, type, full) {
                        var res = '&nbsp;&nbsp;&nbsp;<a href="/user/edit?userId='+data+'"><i class="fa fa-pencil"></i></a>';
                        res += '&nbsp;&nbsp;&nbsp;<a href="/j_spring_security_switch_user?j_username='+full.email+'"><i class="fa fa-mail-forward"></i></a>';
                        return res;
                    }
                }
            ]
        } );
    } );
</script>