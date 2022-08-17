$("#register, #update").click(function () {
    if ($.trim($("#title").val()) == '') {
        alert("상품명을 입력해주세요.");
        return false;
    }
    if ($.trim($("#price").val()) == '') {
        alert("가격을 입력해주세요.");
        return false;
    }
    if ($.trim($("#category").val()) == '') {
        alert("카테고리를 선택해주세요.");
        return false;
    }
    if ($.trim($("#itemInformation").val()) == '') {
        alert("설명을 입력해주세요.");
        return false;
    }
    $("#create_Form, #update_Form").submit();
});


