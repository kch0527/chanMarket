package com.example.market.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTest {
    Socket socket = null;
    BufferedReader in = null;
    BufferedReader in2 = null;
    PrintWriter out = null;
    InetAddress ia = null;

    public void client(String email, String opponent){
        try{
            ia = InetAddress.getByName("localhost");    //서버로 접속
            socket = new Socket(ia,9001);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            in2 = new BufferedReader(new InputStreamReader(System.in));

            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            System.out.println(socket.toString());
        }catch (IOException e){}
        try{
            System.out.print(email + ": ");
            String data = in2.readLine();            //키보드로부터 입력
            out.println(data);                        //서버로 데이터 전송
            out.flush();

            String str2 = in.readLine();            //서버로부터 되돌아오는 데이터 읽어들임
            System.out.println(opponent +" : " + str2);
            socket.close();
        }catch (IOException e){

        }
    }
}