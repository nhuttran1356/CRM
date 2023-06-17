// Khi nào trang html nội dung đã nạp vào trình duyệt
// Thì sẽ chạy code bên trong function
$(document).ready(function () {
    // id la # class la .
    // Lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-role").click(function () {
        var id = $(this).attr("roleid")
        var This = $(this)
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/demoservlet_cybersoft/role/delete?id=" + id,

        })
            .done(function (result) {
                This.closest("tr").remove()
                console.log("Ket qua", result)
            });
    })

})