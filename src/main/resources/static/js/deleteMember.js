function deleteMember(){
    if(!confirm("탈퇴 하시겠습니까?")){
        return false;
    }else{
        const xhr2 = new XMLHttpRequest();
        xhr2.open("DELETE", `/chanMarket/myInfo`, true);
        xhr2.onload = function () {
            location.href = `/chanMarket/board`
        }
        xhr2.send(null);
    }
}
