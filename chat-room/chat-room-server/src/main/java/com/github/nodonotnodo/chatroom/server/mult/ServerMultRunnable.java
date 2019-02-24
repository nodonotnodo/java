package com.github.nodonotnodo.chatroom.server.mult;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ServerMultRunnable implements Runnable {

    private String userName;
    private Socket userSocket;

    //表示用户当前处于什么状态
    private enum chatModule {
        DEFAULT,//主页-无目标态
        PRIVATELY_CHAT,//私聊状态
        GROUP_CHAT,//群聊状态
    }

    private chatModule userModule = chatModule.DEFAULT;

    public chatModule getUserModule() {
        return userModule;
    }

    public void setUserModule(chatModule userModule) {
        this.userModule = userModule;
    }

    public ServerMultRunnable(Socket userSocket) {
        this.userSocket = userSocket;
    }

    @Override
    public void run() {

        try {
            ConcurrentHashMap<String,String> userMap = MultChatroomServer.getUserMap();
            ConcurrentHashMap<String,Socket> onlineUserMap = MultChatroomServer.getOnlineUserMap();
            InputStream inputStream = this.userSocket.getInputStream();
            Scanner scanner1 = new Scanner(inputStream);

            //刚刚连接，请用户选择：登录/注册
            signChose(scanner1);

            Scanner scanner = new Scanner(inputStream);
            while(scanner.hasNext()){

                if(this.userModule == chatModule.DEFAULT){//主页
                    //用户提示信息
                    sendDataToUser("***************************************主页************************************");
                    sendDataToUser("****请输入您想要进行的操作：1.私聊——/——2.群聊——/——3.退出****");

                    //接收用户信息
                    String userData = scanner.nextLine();
                    if(!("1".equals(userData) || "2".equals(userData) || "3".equals(userData))){
                        sendDataToUser("********输入信息格式错误**********");
                        continue;
                    }
                    //处理用户信息
                    //3种状态：1.私聊；2.群聊；3，退出
                    if("1".equals(userData)){//私聊
                        this.userModule = chatModule.PRIVATELY_CHAT;
                    }else if("2".equals(userData)){//群聊
                        this.userModule = chatModule.GROUP_CHAT;
                    }else{//退出
                        sendDataToUser("****成功退出****");
                        try {
                            this.userSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }else if(this.userModule == chatModule.PRIVATELY_CHAT){//私聊
                    privatelyChat();
                }else{//群聊
                    groupChat();
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //群聊
    private void groupChat() {
        try {
            ConcurrentHashMap<String,Socket> onlineUserMap = MultChatroomServer.getOnlineUserMap();
            InputStream inputStream = this.userSocket.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            while(scanner.hasNext()){
                sendDataToUser("***************************群聊***\"退出\"退出群聊**************************");
                sendDataToUser("*****输入你要发送的信息*******");
                String userData = scanner.nextLine();
                if("退出".equals(userData)){
                    return;
                }
                userData = this.userName + "的全服消息：" + userData;
                for(Map.Entry<String,Socket> entry : onlineUserMap.entrySet()){
                    sendDataToUser(userData,entry.getValue());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //私聊
    private void privatelyChat() {
        try {
            ConcurrentHashMap<String,String> userMap = MultChatroomServer.getUserMap();
            ConcurrentHashMap<String,Socket> onlineUserMap = MultChatroomServer.getOnlineUserMap();
            InputStream inputStream = this.userSocket.getInputStream();
            Scanner scanner = new Scanner(inputStream);

            Scanner scannerPrivately = new Scanner(inputStream);
            sendDataToUser("********************************私聊***\"退出\"退出私聊***********************");
            sendDataToUser("******请选择你要私聊的对象******");
            while(scannerPrivately.hasNext()){
                String wantChatName = scannerPrivately.nextLine();
                if(userMap.get(wantChatName) == null){
                    sendDataToUser("*****对不起，你要私聊的对象不存在,请重新选择私聊对象*****");
                }else{
                    if(onlineUserMap.get(wantChatName) == null){
                        sendDataToUser("*****对不起，你要私聊的对象不在线，请重新选择私聊对象*****");
                    }else{
                        Socket wantChatUser = onlineUserMap.get(wantChatName);
                        sendDataToUser("*****************************\"私聊\"***\"退出\"退出私聊***********************");
                        sendDataToUser("*********输入你要发送的信息************");
                        while(scanner.hasNext()){
                            sendDataToUser("*****************************\"私聊\"***\"退出\"退出私聊***********************");
                            String userData = this.userName + scanner.nextLine();
                            if("退出".equals(userData)){
                                return;
                            }
                            sendDataToUser(userData,wantChatUser);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //刚刚连接，请用户选择：登录/注册
    private void signChose(Scanner scanner) {
        sendDataToUser("您好，欢迎使用零距离聊天室，请选择：1.注册——/——2.登录");

        ConcurrentHashMap<String,String> userMap = MultChatroomServer.getUserMap();
        ConcurrentHashMap<String,Socket> onlineUserMap = MultChatroomServer.getOnlineUserMap();

        while (scanner.hasNext()){

            String userData = scanner.nextLine();

            if("1".equals(userData)){//注册
                sendDataToUser("********************欢迎加入零距离聊天室***********************");
                sendDataToUser("*******请输入您想要注册的用户名和密码,例如：张三 jdascfs*******");
                sendDataToUser("***********！！！注意：姓名和密码请控制在15个字符以内**********");

                if(scanner.hasNext()){
                    //去掉左右空格
                    String userDataSignIn = scanner.nextLine().trim();

                    if(userDataSignIn.split(" ").length == 2
                            && userDataSignIn.split(" ")[0].length()<15
                            && userDataSignIn.split(" ")[1].length()<15){

                        String userName = userDataSignIn.split(" ")[0];
                        String userPassword = userDataSignIn.split(" ")[1];

                        if(userMap.size() < 1000){
                            userMap.put(userName,userPassword);
                            sendDataToUser("****注册成功****");
                        }

                        if(onlineUserMap.size() < 50){
                            if(userMap.get(userName) != null){
                                onlineUserMap.put(userName,this.userSocket);
                                sendDataToUser("**********登录成功!!!************");
                                return;
                            }else{
                                sendDataToUser("*******用户名或密码错误***********");
                            }
                        }else{
                            sendDataToUser("**********当前登录人数过多，请稍后重试*************");
                            try {
                                this.userSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }else{
                        sendDataToUser("***********输入信息格式错误**************");
                    }
                }
                break;
            }else if("2".equals(userData)){//登录
                signUP(scanner,onlineUserMap);
                break;
            }else {
                sendDataToUser("***********输入信息格式错误，请输入：1.注册——/——2.登录*********");
                sendDataToUser("*******请输入您想要注册的用户名和密码,例如：张三 jdascfs*******");
                sendDataToUser("***********！！！注意：姓名和密码请控制在15个字符以内**********");
            }
        }


    }

    //登录
    private void signUP(Scanner scanner, ConcurrentHashMap<String,Socket> onlineUserMap) {
        sendDataToUser("*******请输入您的用户名和密码,例如：张三 jdascfs*******");
        while(scanner.hasNext()){
            String userData = scanner.nextLine().trim();
            if(userData.split(" ").length == 2
                    && userData.split(" ")[0].length()<15
                    && userData.split(" ")[1].length()<15){
                if(onlineUserMap.size() < 50){
                    onlineUserMap.put(userData.split(" ")[0],this.userSocket);
                    this.userName = userData.split(" ")[0];
                    sendDataToUser("************登录成功**************");
                    System.out.println("当前在线人数：" + MultChatroomServer.getOnlineUserMap().size());
                }else{
                    sendDataToUser("******抱歉，当前在线用户过多，请稍后再试*******");
                    try {
                        this.userSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }else{
                sendDataToUser("******输入的信息格式不正确******");
                sendDataToUser("*******请输入您的用户名和密码,例如：张三 jdascfs*******");
            }
        }
    }

    //服务器发送信息给客户端
    private void sendDataToUser(String data){//发送给当前对象用户
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.userSocket.getOutputStream());
            data = data + "\n";
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendDataToUser(String data, Socket socket){//发送用户想要私聊对象
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            data = data + "\n";
            outputStreamWriter.write(data);
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //服务器显示当前在线人数
    private void showOnlineUserNum() {

        System.out.println("当前在线人数：" + MultChatroomServer.getOnlineUserMap().size());

    }

}
