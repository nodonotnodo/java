package com.guthub.nodonotnodo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class JdbcMouldTest {


    public static void main(String[] args) {
        JdbcMould jd = new JdbcMould() {
            @Override
            public ResultSet handle() {
                return getResultSet();
            }
        };
        jd.loadDrive();
        jd.connect("root","1234");
        jd.createSql();
        jd.executeOrder("select * from memo_group");
        ResultSet resultSet = jd.handle();
        try {
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
        jd.myCommit();
        jd.colseAll();
    }
}
