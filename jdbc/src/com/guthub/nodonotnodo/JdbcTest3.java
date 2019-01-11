package com.guthub.nodonotnodo;

import java.sql.*;

//判断是否支持事务

public class JdbcTest3 {

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
            int i1 = statetment.executeUpdate("update memo_group set name='家庭2' where id=4");

            //i2是一条异常语句，但是i1语句却成功更新了数据库数据，所以此时不支持事物（实际上是此时每执行一条语句都会自动提交commit）
            int i2 = statetment.executeUpdate("insert into memo_group(id,name,created_time) values (7,'学习2',now(),8,2)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
