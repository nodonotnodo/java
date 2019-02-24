package com.github.nodonotnodo.chatroom.user.mult;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class MultchatroomUser {

    public static void main(String[] args) {

        //这是默认端口号和IP地址
        String adressIP = "127.0.0.1";
        int port = 8888;

        if (args != null) {
            if (args.length == 1) {
                port = Integer.valueOf(args[0]);
            }
        }

        try {

            Socket socket = new Socket(adressIP,port);
            Thread thread1 = new Thread(new MultChatroomUserReadFromServer(socket),"Thread-ReadFromServer");
            thread1.setDaemon(true);
            thread1.start();

            Scanner scanner = new Scanner(System.in);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            while(true){
                if(scanner.hasNext()){
                    String userData = scanner.nextLine();
                    if("3".equals(userData)){
                        return;
                    }
                    outputStreamWriter.write(userData + "\n");
                    outputStreamWriter.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
