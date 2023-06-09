<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/layout/link.jsp"/>
</head>

<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>
<%--navigation--%>
<jsp:include page="/layout/navigation.jsp"/>
<!-- Left navbar-header -->
<jsp:include page="/layout/leftnavbar.jsp"/>
<!-- Left navbar-header end -->
<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div class="row bg-title">
            <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                <h4 class="page-title">Danh sách thành viên</h4>
            </div>
            <div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
                <a href="<c:url value="/user/add"/>" class="btn btn-sm btn-success">Thêm mới</a>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /row -->
        <div class="row">
            <div class="col-sm-12">
                <div class="white-box">
                    <div class="table-responsive">
                        <table class="table" id="example">
                            <thead>
                            <tr>
                                <th>STT</th>
                                <th>Email</th>
                                <th>Full Name</th>
                                <th>Avatar</th>
                                <th>Role</th>
                                <th>#</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${listUsers}">
                                <tr>
                                    <td>${item.getId()}</td>
                                    <td>${item.getEmail()}</td>
                                    <td>${item.getFullname()}</td>
                                    <td>${item.getAvatar()}</td>
                                    <td>${item.getRoleName()}</td>
                                    <td>
                                        <a href="<c:url value="/user/update?id_update=${item.getId()}"/>"
                                           class="btn btn-sm btn-primary btn-update-user">Sửa</a>
<%--<a href="<c:url value="user/delete?id=${item.getId()}"/>" class="btn btn-sm btn-danger">Xóa</a>--%>
                                        <span userid="${item.getId()}"
                                              class="btn btn-sm btn-danger btn-delete-user">Xóa</span>
                                        <a href="user-details.html" class="btn btn-sm btn-info">Xem</a>
                                    </td>
                                </tr>

                            </c:forEach>


                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
    <footer class="footer text-center"> 2018 &copy; myclass.com</footer>
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="plugins/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Menu Plugin JavaScript -->
<script src="plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
<!--slimscroll JavaScript -->
<script src="js/jquery.slimscroll.js"></script>
<script src="js/jquery.dataTables.js"></script>
<!--Wave Effects -->
<script src="js/waves.js"></script>
<!-- Custom Theme JavaScript -->
<script src="js/custom.min.js"></script>
<script src="<c:url value="/js/user-table.js?version=1"/>"></script>
<%--<script src="<c:url value="/js/user-update.js?version=1"/>"></script>--%>
<script>
    $(document).ready(function () {
        $('#example').DataTable();
    });
</script>
</body>

</html>

