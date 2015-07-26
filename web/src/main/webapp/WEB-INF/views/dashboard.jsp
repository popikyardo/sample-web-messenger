<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!-- bootstrap wysihtml5 - text editor -->
<link href="${pageContext.request.contextPath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" />

<style type="text/css">
    .modal-dialog {
        width: 700px;
    }
</style>

<div class='row'>
<div class='col-md-4'>
    <!-- USERS LIST -->
    <div class="box box-danger">
        <div class="box-header with-border">
            <h3 class="box-title"><spring:message code="Contacts"/></h3>
        </div><!-- /.box-header -->
        <div class="box-body no-padding">
            <div>
                <ul id="contactsList" class='contacts-list'>

                </ul><!-- /.contacts-list -->
            </div><!-- /.direct-chat-pane -->
        </div><!-- /.box-body -->
        <div class="box-footer text-center">
            <a href="#" class="uppercase" data-toggle="modal" data-target="#contactsModal"><spring:message code="UI.Labels.Contacts.AddContact"/></a>
        </div><!-- /.box-footer -->
    </div><!--/.box -->
</div><!-- /.col -->
<div class='col-md-8'>
    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title"><spring:message code="Messages"/></h3>
        </div><!-- /.box-header -->
        <div class="box-body">
            <table id="messages" class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th><spring:message code="UI.Labels.Message.Datetime"/></th>
                    <th><spring:message code="UI.Labels.Message.FromUser"/></th>
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <th><spring:message code="UI.Labels.Message.ToUser"/></th>
                </sec:authorize>
                    <th><spring:message code="UI.Labels.Message.Subject"/></th>
                </tr>
                </thead>

            </table>
        </div><!-- /.box-body -->
    </div><!-- /.box -->
</div><!-- /.col -->
</div><!-- /.row -->



<div id="contactsModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><spring:message code="UI.Labels.Contacts.FindContacts"/></h4>
                <div class="has-feedback">
                    <input id="findContacts" type="text" class="form-control input-sm" placeholder="Search"/>
                    <span class="glyphicon glyphicon-search form-control-feedback"></span>
                </div>
            </div>
            <div class="modal-body">
                <div>
                    <ul id="contactsSearchList" class='contacts-list'>
                    </ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal"><spring:message code="Close"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="messageModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><spring:message code="UI.Labels.Message.MessageTitle"/></h4>
            </div>
            <div class="modal-body">
                <input type="hidden" name="contactId" id="contactId" value=""/>
                <div class="form-group">
                    <input class="form-control" id="msgSubject" placeholder="<spring:message code="UI.Labels.Message.Subject"/>:"/>
                </div>
                <div class="form-group">
                    <textarea id="compose-textarea" class="form-control" style="height: 200px">
                    </textarea>
                </div>
            </div>
            <div class="modal-footer">
                <div class="pull-right">
                    <button id="sendMessage" type="submit" class="btn btn-primary"><spring:message code="Send"/></button>
                </div>
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal"><spring:message code="Close"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div id="messageViewModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title"><spring:message code="UI.Labels.Message.MessageTitle"/></h4>
                <div class="box-tools pull-right">
                    <div id="msgViewDate"></div>
                </div>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <div id="msgViewFrom"></div>
                </div>
                <div class="form-group">
                    <div id="msgViewSubject"></div>
                </div>
                <div class="form-group">
                    <div id="msgViewPayload"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal"><spring:message code="Close"/></button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!-- DATA TABES SCRIPT -->
<script src="${pageContext.request.contextPath}/resources/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/plugins/datatables/dataTables.bootstrap.js" type="text/javascript"></script>
<!-- SlimScroll -->
<script src="${pageContext.request.contextPath}/resources/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<!-- Bootstrap WYSIHTML5 -->
<script src="${pageContext.request.contextPath}/resources/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>

<script type="text/javascript">
    function drawContacts(){
        $("#contactsList").empty();
        $.ajax( "/user/contacts" )
                .done(function(data) {
                    $.each(data, function(i, contact) {
                        $("#contactsList").append("<li>" +
                                "<img class='contacts-list-img' src='/user/userpic?email="+contact.email+"'/>" +
                                "<div style='margin-left:45px;'>" +
                                "<span class='contacts-list-name'>" +
                                contact.firstName + " " + contact.lastName +
                                "<small class='contacts-list-date pull-right'>" +
                                "<button contact_id='"+contact.id+"' data-id='"+contact.id+"' class='btn btn-box-tool msg_button' data-toggle='modal' data-target='#messageModal'><i class='fa fa-envelope'></i></button>" +
                                "<button contact_id='"+contact.id+"' class='btn btn-box-tool delete_button'><i class='fa fa-times'></i></button>" +
                                "</small>" +
                                "</span>" +
                                "</div>" +
                                "</li>");
                    });
                });
    }

    $(document).ready(function() {
        drawContacts();

        $( document ).on( 'click', '.add_contact', function(){
            var el = $(this);
            var id = el.attr('contact_id');
            $.ajax( "/user/contact/add?userId="+id )
                    .done(function(data) {
                        el.replaceWith("<i class='fa fa-check'>");
                        drawContacts();
                    });
        });

        $( document ).on( 'click', '.delete_button', function(){
            var el = $(this);
            var id = el.attr('contact_id');
            $.ajax( "/user/contact/delete?userId="+id )
                    .done(function(data) {
                        drawContacts();
                    });
        });

        $(document).on("click", ".msg_button", function () {
            var id = $(this).data('id');
            $(".modal-body #contactId").val( id );
        });

        $( "#findContacts" ).keyup(function() {
            $.ajax( "/user/search?query="+$(this).val() )
                    .done(function(data) {
                        $("#contactsSearchList").empty();
                        $.each(data, function(i, contact) {
                            $("#contactsSearchList").append("<li>" +
                                    "<img class='contacts-list-img' src='/user/userpic?email="+contact.email+"'/>" +
                                    "<div style='margin-left:45px;'>" +
                                    "<span class='contacts-list-name'>" +
                                    contact.firstName + " " + contact.lastName +
                                    "<small class='contacts-list-date pull-right'>" +
                                    "<button contact_id='"+contact.id+"' class='btn btn-box-tool add_contact'><i class='fa  fa-user-plus'></i></button>" +
                                    "</small>" +
                                    "</span>" +
                                    "</div>" +
                                    "</li>");
                        });
                    });
        });

        $("#addContacts").click(function(event){
            drawContacts();
        });

        var msgTable = $('#messages').dataTable( {
            "bServerSide": true,
            "bProcessing": true,
            "sAjaxSource": "/messages/data",
            "sPaginationType": "bootstrap",
            "aoColumns": [
                { "mData": "date",
                    "mRender": function( data, type, full) {
                        return (new Date(data)).toUTCString();
                    }
                },
                { "mData": "fromUser",
                    "mRender": function(data) {
                        return "<img class='contacts-list-img' src='/user/userpic?email="+data.email+"'/>"+
                        "<div style=\"margin-left:45px;\">"+
                                "<span class='contacts-list-name'>"+
                                data.firstName+" "+data.lastName+
                        "</span>"+
                        "</div>";
                    }},
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    { "mData": "toUser",
                        "mRender": function(data) {
                            return "<img class='contacts-list-img' src='/user/userpic?email="+data.email+"'/>"+
                                    "<div style=\"margin-left:45px;\">"+
                                    "<span class='contacts-list-name'>"+
                                    data.firstName+" "+data.lastName+
                                    "</span>"+
                                    "</div>";
                        }
                    },
                </sec:authorize>
                { "mData": "subject"}
            ]
        } );

        $("#compose-textarea").wysihtml5();

        $("#sendMessage").click(function(){
            $.ajax({
                method: "POST",
                url: "/messages/send",
                data: {
                    contactId: $("#contactId").val(),
                    subject: $("#msgSubject").val(),
                    message: $("#compose-textarea").val()
                }
            });
            $('#messageModal').modal('hide');
            msgTable.fnDraw();
        });
    } );
</script>