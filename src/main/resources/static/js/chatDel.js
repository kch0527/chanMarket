function deleteChatRoom(roomId, memberId){
    if(!confirm("삭제 하시겠습니까?")){
        return false;
    }else{
        const xhr2 = new XMLHttpRequest();
        xhr2.open("DELETE", `/chanMarket/chat/${roomId}/ChatList/delete`, true);
        xhr2.onload = function () {
            location.href = `/chanMarket/chat/${memberId}/ChatList`
        }
        xhr2.send(null);
    }
}

