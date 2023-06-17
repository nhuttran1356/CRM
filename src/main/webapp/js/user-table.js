// Khi nào trang html nội dung đã nạp vào trình duyệt
// Thì sẽ chạy code bên trong function
$(document).ready(function () {
    // id la # class la .
    // Lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-user").click(function () {
        var id = $(this).attr("userid")
        var This = $(this)
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/demoservlet_cybersoft/user/delete?id=" + id,
            // data: {name: "John", location: "Boston"}
        })
            .done(function (result) {
                This.closest("tr").remove()
                console.log("Ket qua", result)
            });
    })

})