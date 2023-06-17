<%@ page import="model.Users" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<%--
    <%! %>: Thẻ dùng khai báo biến
    <% %>: Thẻ dùng để xử lý logic code
    <%= %>: Xuất giá trị của biến ra màng hình

--%>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>


<div class="container">
    <div class="row mt-5">
        <div class="col-md-5 m-auto mt-5">
            <h3 class="text-center">DANG NHAP HE THONG</h3>
            <div class="p-4 border mt-4">


                <form action="<c:url value="/login"/>" method="post" id="login-form">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="username" value="${username}" id="username">
                    </div>
                    <div class="form-group">
                        <label>Mat Khau</label>
                        <input type="password" class="form-control" name="password" value="${password}" id="password">
                    </div>
                    <div>
                        <input type="checkbox" id="i" name="rememberme">
                        <label for="i">Remember me</label>
                    </div>
<%--                    <a href="<c:url value="/user/update?id_update=${item.getId()}"/>"--%>
<%--                       class="btn btn-sm btn-primary btn-update-user">Sửa</a>--%>
                    <button type="submit" class="btn btn-primary">Dang nhap</button>
                </form>
            </div>
        </div>
    </div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<script> $().ready(function () {
    $("#login-form").validate({
        rules: {
            "username": {
                required: true,
                minlength: 8
            },
            "password": {
                required: true,
                minlength: 6
            }
        },
        messages: {
            "password": {
                required: "Bắt buộc nhập username",
                maxlength: "Hãy nhập tối đa 15 ký tự"
            },
            "username": {
                required: "Bắt buộc nhập username",
                maxlength: "Hãy nhập tối đa 15 ký tự"
            }
        },

    });
});</script>
</body>
</html>
