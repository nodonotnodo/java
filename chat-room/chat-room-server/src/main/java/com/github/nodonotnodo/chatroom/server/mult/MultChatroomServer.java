package com.github.nodonotnodo.chatroom.server.mult;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.*;

public class MultChatroomServer {

    //这是聊天室注册的用户map集合
    private final static ConcurrentHashMap<String, String> userMap = new ConcurrentHashMap<>(5000);

    //这是在线用户的一个map集合
    private final static ConcurrentHashMap<String, Socket> onlineUserMap = new ConcurrentHashMap<>(50);

    public static ConcurrentHashMap<String, Socket> getOnlineUserMap() {
        return onlineUserMap;
    }

    public static ConcurrentHashMap<String, String> getUserMap() {
        return userMap;
    }

    public static void main(String[] args) {

        //连接数据库
        JdbcMysqlServer jdbcMysqlServer = new JdbcMysqlServer("root","1234") {
            @Override
            void dealResult() throws SQLException {
                ResultSet resultSet = this.getResultSet();
                while(resultSet.next()){
                    String userName = resultSet.getNString("user_name");
                    String userPassword = resultSet.getNString("user_password");
                    userMap.put(userName,userPassword);
                }
            }
        };
        jdbcMysqlServer.runModel("select * from user_info;");



        //这是默认端口号
        int port = 8888;

        if (args != null) {
            if (args.length == 1) {
                port = Integer.valueOf(args[0]);
            }
        }

        //创建一个线程池
//        synchronized ()
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue<>(100);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 100, 1
                , TimeUnit.MINUTES, linkedBlockingQueue);

        //创建ServerSocket服务器
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {

                System.out.println("服务器等待客户端连接。。。");
                Socket socket = serverSocket.accept();
                System.out.println("客户端" + socket.getLocalAddress() + "\t连接到了服务器");
                threadPoolExecutor.submit(new ServerMultRunnable(socket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
