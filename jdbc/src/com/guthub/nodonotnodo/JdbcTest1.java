package com.guthub.nodonotnodo;

import java.sql.*;

public class JdbcTest1 {

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
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/memo?user=root&password=1234&useSSL=false");

            //3.创建命令
            Statement statement = connection.createStatement();

            //4.创建SQL语句，并执行
//            ResultSet resultSet = statement.executeQuery("select id,name,created_time,modify_time from memo_group");
            ResultSet resultSet = statement.executeQuery("select id,name,created_time,modify_time from memo_group");

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

            //6.关闭结果集
            resultSet.close();

            //7.关闭命令
            statement.close();

            //8.关闭连接
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
