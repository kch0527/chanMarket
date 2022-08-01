function chatRoomCreate(boardId, memberId){
    if(!confirm("채팅 하시겠습니까?")){
        return false;
    }else{
        const xhr = new XMLHttpRequest();
        xhr.open("POST", `/chanMarket/chat/${boardId}/ChatCreate`, true);
        xhr.onload = function () {
            location.href = `/chanMarket/chat/${memberId}/ChatList`
        }
        xhr.send(null);
    }
}