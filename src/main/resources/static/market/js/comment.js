// $(document).ready(function() {
//     var msg = "${msg}";
//     if(msg != ""){
//         alert(msg);
//     }
// });
function deleteConfirm(itemId, commentId){
    if(!confirm("삭제 하시겠습니까?")){
        return false;
    }else{
        const xhr = new XMLHttpRequest();
        xhr.open("DELETE", `/chanMarket/itemList/delete/${commentId}`, true);
        xhr.onload = function () {
            location.href = `/chanMarket/itemList/${itemId}`
        }
        xhr.send(null);
    }
}