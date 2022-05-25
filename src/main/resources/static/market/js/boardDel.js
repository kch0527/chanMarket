function deleteConfirm(boardId){
    if(!confirm("삭제 하시겠습니까?")){
        return false;
    }else{
        const xhr = new XMLHttpRequest();
        xhr.open("DELETE", `/chanMarket/board/${boardId}/delete/`, true);
        xhr.onload = function () {
            location.href = `/chanMarket/board`
        }
        xhr.send(null);
    }
}