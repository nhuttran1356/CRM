// Khi nào trang html nội dung đã nạp vào trình duyệt
// Thì sẽ chạy code bên trong function
$(document).ready(function () {
    // id la # class la .
    // Lắng nghe sự kiện click cho thẻ có id là btn-delete-user
    $(".btn-delete-group").click(function () {
        var id = $(this).attr("groupid")
        var This = $(this)
        $.ajax({
            method: "POST",
            url: "http://localhost:8081/demoservlet_cybersoft/groupwork/delete?id=" + id,
            // data: {name: "John", location: "Boston"}
        })
            .done(function (result) {
                This.closest("tr").remove()
                console.log("Ket qua", result)
            });
    })

})