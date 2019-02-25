package com.github.nodonotnodo.chatroom.user.mult;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class MultChatroomUserReadFromServer implements Runnable {

    private Socket socket;

    public MultChatroomUserReadFromServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {
            Scanner scanner = new Scanner(this.socket.getInputStream());
            while(scanner.hasNext()){
                String serverData = scanner.nextLine();
                if("请关机".equals(serverData)){
                    System.out.println("你已经断开连接");
                    return;
                }
                System.out.println(serverData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
