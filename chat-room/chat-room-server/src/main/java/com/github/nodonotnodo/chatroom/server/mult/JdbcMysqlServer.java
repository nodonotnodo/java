package com.github.nodonotnodo.chatroom.server.mult;

import java.sql.*;

/**
 * 这个类是为了将用户的注册信息存在数据库的
 */

public abstract class JdbcMysqlServer {

    private String user;
    private String password;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private int result = -1;

    public void setResult(int result) {
        this.result = result;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public int getResult() {
        return result;
    }

    public JdbcMysqlServer(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public void runModel(String sql) {

        /*
        先简单的做一个JDBC模板
         */

        //1.加载数据库驱动

        loadDriver();
        //2.建立数据库连接（开启事物）
        //数据库的URL格式定义：
        //  jdbc:databaseType://host:port/databaseName?p1=v1&p2=v2...(databaseName?后面的为用户自己设定的参数)
        createConnection(this.user, this.password);

        //3.创建命令
        createSql();

        //4.执行命令
        execureSql(sql);

        //5.处理结果集
        try {
            dealResult();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //提交
        commit();

        //6.关闭所有流
        closeAllStream();

    }

    //提交（结束事物）
    private void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //rollback(比较简单，没有创建保存点，所以回滚也就只能直接回滚到头)
    public final void myrollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //6.关闭所有流
    private void closeAllStream() {
        try {
            if(this.resultSet != null){
                this.resultSet.close();
            }
            this.result = -1;
            this.statement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //5.处理结果集
    abstract void dealResult() throws SQLException;

    //4.执行命令
    private void execureSql(String sql) {
        try {
            if (sql.startsWith("select")) {
                this.resultSet = this.statement.executeQuery(sql);
            } else {
                this.result = this.statement.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //3.创建命令
    private void createSql() {
        try {
            Statement statement = this.connection.createStatement();
            this.statement = statement;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //2.建立数据库连接（开启事物）
    //数据库的URL格式定义：
    //  jdbc:databaseType://host:port/databaseName?p1=v1&p2=v2...(databaseName?后面的为用户自己设定的参数)
    private void createConnection(String user, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_room?useSSL=false", user, password);
            connection.setAutoCommit(false);
            this.connection = connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //加载驱动程序
    private void loadDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
