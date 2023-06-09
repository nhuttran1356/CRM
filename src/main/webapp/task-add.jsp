<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <jsp:include page="/layout/link.jsp" />
</head>

<body>
<!-- Preloader -->
<div class="preloader">
    <div class="cssload-speeding-wheel"></div>
</div>
<jsp:include page="/layout/navigation.jsp" />
    <!-- Left navbar-header -->
<jsp:include page="/layout/leftnavbar.jsp"/>
    <!-- Left navbar-header end -->
    <!-- Page Content -->
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row bg-title">
                <div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
                    <h4 class="page-title">Thêm mới công việc</h4>
                </div>
            </div>
            <!-- /.row -->
            <!-- .row -->
            <div class="row">
                <div class="col-md-2 col-12"></div>
                <div class="col-md-8 col-xs-12">
                    <div class="white-box">
                        <form action="<c:url value="/task/add"/>" method="post" class="form-horizontal form-material">
                            <div class="form-group">
                                <label class="col-md-12">Dự án</label>
                                <div class="col-md-12">

                                        <input type="text" placeholder="Tên dự án"
                                               class="form-control form-control-line" name="projectname">

                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Tên công việc</label>
                                <div class="col-md-12">

                                    <select class="form-control form-control-line" name="workname">
                                        <c:forEach items="${listGroup}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>
                                    </select>
<%--                                    <input type="text" placeholder="Tên công việc"--%>
<%--                                           class="form-control form-control-line" name="workname">--%>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Người thực hiện</label>
                                <div class="col-md-12">
                                    <select class="form-control form-control-line" name="userworkname">
                                    <c:forEach items="${listUsers}" var="item">
                                        <option value="${item.id}">${item.fullname}</option>
                                    </c:forEach>
<%--                                        <input type="text" placeholder="Người thực hiện"--%>
<%--                                               class="form-control form-control-line" name="userworkname">--%>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-12">Trạng thái</label>
                                <div class="col-sm-12">
                                    <select class="form-control form-control-line" name="status">

                                        <c:forEach items="${list_Status}" var="item">
                                            <option value="${item.id}">${item.name}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày bắt đầu</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="yyyy-MM-dd"
                                           class="form-control form-control-line" name="start_date">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-12">Ngày kết thúc</label>
                                <div class="col-md-12">
                                    <input type="text" placeholder="yyyy-MM-dd"
                                           class="form-control form-control-line" name="end_date">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <button type="submit" class="btn btn-success">Lưu lại</button>
                                    <a href="task.jsp" class="btn btn-primary">Quay lại</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-md-2 col-12"></div>
            </div>
            <!-- /.row -->
        </div>
        <!-- /.container-fluid -->
        <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<!-- jQuery -->
<script src="<c:url value="/plugins/bower_components/jquery/dist/jquery.min.js"/>"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/bootstrap/dist/js/bootstrap.min.js"/>"></script>
<!-- Menu Plugin JavaScript -->
<script src="<c:url value="/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"/>"></script>
<!--slimscroll JavaScript -->
<script src="<c:url value="/js/jquery.slimscroll.js"/>"></script>
<!--Wave Effects -->
<script src="<c:url value="/js/waves.js"/>"></script>
<!-- Custom Theme JavaScript -->
<script src="<c:url value="/js/custom.min.js"/>"></script>
</body>

</html>