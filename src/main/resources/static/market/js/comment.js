$(function() {
    $("#title li").click(function(e) {
        e.stopPropagation();
        var num = $(this).index();
        $("#modal li").eq(num).show();
    });

    $(document).click(function() {
        $("#modal li").hide();
    })
})