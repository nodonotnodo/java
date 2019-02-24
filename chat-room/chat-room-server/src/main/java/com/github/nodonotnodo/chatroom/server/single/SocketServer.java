package com.github.nodonotnodo.chatroom.server.single;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class SocketServer {

    public static void main(String[] args) {

        //默认的端口号
        int port = 8888;
        try {
            //1.创建一个SocketServer服务器
            if(args.length > 0){
                port = Integer.valueOf(args[0]);
            }
            ServerSocket socketServer = new ServerSocket(port);
            System.out.println(socketServer.getLocalPort());
            //2.服务器监听
            System.out.println("服务器等待用户连接。。。" + socketServer.getLocalPort());
            Socket user = socketServer.accept();
            System.out.println(user.getInetAddress().toString() + "连接到服务器" + socketServer.getLocalPort());

            //3.服务器发送数据用户
            OutputStreamWriter serverdata = new OutputStreamWriter(user.getOutputStream());
            serverdata.write("你好，这是服务器给你发送的消息，表示你正常连接到服务器，不用回复\n");
            serverdata.flush();

            //4.服务器接收用户的数据
            final InputStream inputStream = user.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String userdata = scanner.next();
            System.out.println("用户" + user.getInetAddress().toString() + "的信息为：" + userdata);

            Scanner interactive = new Scanner(System.in);//这个是为了给用户交互

            //这个线程是用来打印用户之间的信息
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Scanner scanner1 = new Scanner(inputStream);
                    String userdata = null;
                    while(true){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if(scanner1.hasNext()){
                            userdata = scanner1.next();
                            System.out.println("用户:" + userdata);
                        }
                    }
                }
            },"Thread-getdata");
            System.out.println("线程启动");
            thread.setDaemon(true);
            thread.start();

            //这里是为了让用户输入信息
            while(true){
                System.out.println("请输入信息");
                String info = interactive.next() + "\n";
                serverdata.write(info);
                serverdata.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
