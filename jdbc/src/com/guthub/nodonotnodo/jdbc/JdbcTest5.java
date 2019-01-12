package com.guthub.nodonotnodo.jdbc;

import java.sql.*;

//开启事务,字符串拼接创建SQL命令

public class JdbcTest5 {

    public static void QueryOfGroupName(String groupName){

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
                ResultSet resultSet = statetment.executeQuery("select id,name,created_time,modify_time from memo_group where name="+"'"+groupName+"'");
        ){

            //设置开启事物，实际上就是关掉连接的自动提交
            connection.setAutoCommit(false);

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

    public static void QueryOfGroupName2(String groupName){

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
                PreparedStatement statetment = connection.prepareStatement("select id,name,created_time,modify_time from memo_group where name=?");

        ){

            //设置开启事物，实际上就是关掉连接的自动提交
            connection.setAutoCommit(false);

            //补全SQL命令
            statetment.setString(1,groupName);

            //4.创建SQL语句，并执行
            ResultSet resultSet = statetment.executeQuery();

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

            //关闭结果集
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        QueryOfGroupName("工作");
        QueryOfGroupName("学习");
        //下面这种情况有可能导致库泄露，所以字符串拼接的创建SQL命令是很危险的
        QueryOfGroupName("' or name='家庭2' or name='娱乐");
        QueryOfGroupName2("工作");
        QueryOfGroupName2("学习");
        //使用预编译后，这种情况就无法查询到内容了。参数只能按照规定的格式来给定
        QueryOfGroupName2("' or name='家庭2' or name='娱乐");
    }
}
