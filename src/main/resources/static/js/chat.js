let socket = new WebSocket("ws://localhost:8080/chat");

socket.onopen = function (e) {  //서버 연결될 때, 발생하는 함수
    console.log('open server!')
};

socket.onerror = function (e){  //에러 발생 함수
    console.log(e);
}

socket.onmessage = function (e) {  //메시지 받을 때 함수
    console.log(e.data);
    let msgArea = document.querySelector('.msgArea');
    let newMsg = document.createElement('div');
    newMsg.innerText=e.data;
    msgArea.append(newMsg);
}

function sendMsg() {
    let content=document.querySelector('.content').value;
    socket.send(content);
}