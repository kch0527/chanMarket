$("#commentCreate").click(function () {
    if ($.trim($("#text").val()) == '') {
        alert("내용을 입력해주세요.");
        return false;
    }
    $("#commentPost").submit();
});