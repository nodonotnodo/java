package com.guthub.nodonotnodo.jdbc;

import java.sql.*;

//开启事务

public class JdbcTest4 {

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
            int i1 = statetment.executeUpdate("update memo_group set name='家庭3' where id=4");

            //i2是一条异常语句，此时由于连接开启事务，所以i2异常导致整个sql都没有执行，数据库数据无变化
            int i2 = statetment.executeUpdate("insert into memo_group(id,name,created_time) values (8,'学习2',now(),8,2)");
            if(i1 == 1 && i2 == 1){
                connection.commit();
            }else{
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
