package com.example.market.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    Socket socket = null;  //Client와 통신하기 위한 Socket
    ServerSocket serverSocket = null;  //서버 생성을 위한 ServerSocket
    BufferedReader in = null;  //Client로부터 데이터를 읽어들이기 위한 입력스트림
    PrintWriter out = null;  //Client로 데이터를 내보내기 위한 출력 스트림

    public void server() {
        try {
            serverSocket = new ServerSocket(8080);

        } catch (IOException e) {
            System.out.println("포트사용가능");
        }
        try {
            System.out.println("서버오픈");
            socket = serverSocket.accept();   //서버 생성 , Client 접속 대기
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //입력스트림 생성

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

            String str = null;
            str = in.readLine();

            System.out.println("message: " + str);

            out.write(str);
            out.flush();
            socket.close();
        }catch (IOException e){

        }
    }
}
