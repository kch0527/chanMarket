function deleteChatRoom(roomId, memberId){
    if(!confirm("삭제 하시겠습니까?")){
        return false;
    }else{
        const xhr1 = new XMLHttpRequest();
        xhr1.open("DELETE", `/chanMarket/chat/${roomId}/delete`, true);
        xhr1.onload = function () {
            location.href = `/chanMarket/chat/${memberId}/ChatList`
        }
        xhr1.send(null);
    }
}