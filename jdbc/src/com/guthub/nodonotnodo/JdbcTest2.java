package com.guthub.nodonotnodo;

import java.sql.*;

//实现自动关闭

public class JdbcTest2 {

    public static void main(String[] args) {

        //1.加载JDBC驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2.创建数据库连接
        //数据库的URL格式定义：
        //  jdbc:databaseType://host:port/databaseName?p1=v1&p2=v2...(databaseName?后面的为用户自己设定的参数)
        try (
                //它们都实现了AutoCloseable接口,支持自动关闭

                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/memo?user=root&password=1234&useSSL=false");

                //3.创建命令
                Statement statetment = connection.createStatement();

                //4.创建SQL语句，并执行
                ResultSet resultSet = statetment.executeQuery("select id,name,created_time,modify_time from memo_group");
                ){


            //5.结果集处理
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Timestamp createdTime = resultSet.getTimestamp("created_time");
                Timestamp modifyTime = resultSet.getTimestamp("modify_time");
                System.out.println(
                        String.format("id:%d\t名称:%s\t创建时间:%s\t最后修改时间:%s"
                        ,id,name,createdTime.toString(),modifyTime.toString())
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
