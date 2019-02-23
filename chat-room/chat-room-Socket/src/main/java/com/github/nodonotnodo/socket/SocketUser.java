package com.github.nodonotnodo.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class SocketUser {

    public static void main(String[] args) {

        //默认的IP地址和端口号
        String host = "127.0.0.1";
        int port = 8889;

        try {
            //1.创建客户端连接服务器

            if(args.length > 1){
                if(args[0].split("\\.").length == 4){
                    host = args[0];
                }else{
                    System.out.println("你输入的IP地址错误，使用默认的IP地址");
                }
                port = Integer.valueOf(args[1]);
            }

//            final Socket socket = new Socket(host,port);

            //设置一个超时时长
            Socket socket = new Socket();
            SocketAddress address = new InetSocketAddress(host,port);
            socket.connect(address,60000);

            System.out.println("用户信息" + "\tIP地址：" + socket.getInetAddress() + "\t端口号：" + socket.getPort());

            Scanner interactive = new Scanner(System.in);//这个是为了给用户交互

            //2.用户向服务器发送数据
            OutputStreamWriter outputStream = new OutputStreamWriter(socket.getOutputStream());
            outputStream.write("我是用户，这是我发送的数据\n");
            outputStream.flush();

            //3.用户接受服务器发送的数据
            final InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(socket.getInputStream());
            String serverdata = scanner.next();
            System.out.println("这是服务器发送的数据：" + serverdata );

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
                            System.out.println("服务器:" + userdata);
                        }
                    }
                }
            },"Thread-getdata");
            System.out.println("线程启动");
            thread.setDaemon(true);
            thread.start();

            //这里是为了让用户输入信息
            String flagForClose = "no";
            while(!"close".equalsIgnoreCase(flagForClose)){
                System.out.println("请输入信息");
                String info = interactive.next() + "\n";
                if("close".equalsIgnoreCase(info)){
                    flagForClose = "close";
                }
                outputStream.write(info);
                outputStream.flush();
            }


            //关闭客户端
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
