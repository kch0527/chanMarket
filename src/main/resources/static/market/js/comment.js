
function deleteComment(boardId, commentId){
    if(!confirm("삭제 하시겠습니까?")){
        return false;
    }else{
        const xhr1 = new XMLHttpRequest();
        xhr1.open("DELETE", `/chanMarket/board/${commentId}`, true);
        xhr1.onload = function () {
            location.href = `/chanMarket/board/${boardId}`
        }
        xhr1.send(null);
    }
}

// $(document).ready(function() {
//     var msg = "${msg}";
//     if(msg != ""){
//         alert(msg);
//     }
// });