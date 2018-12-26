package com.github.nodonotnodo.coll_over;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Achieve {

    //给文件中添加学生信息
    public static void addStudent(File file, Student student){
        try (
                Writer out = new FileWriter(file,true);
                ){
            char[] ca = ("\n\t"+student.getId()+"\t\t"+student.getName()+"\t\t"+student.getSex()).toCharArray();
            out.write(ca);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //批量添加简单的学生信息
    public static void addBatchStudent(File file, int n){
        if(n<=0){
            System.out.println("请输入大于0的数字");
            return;
        }
        int i = 0;
        while(n != i++){
            String sex = "男";
            if(i%3 == 1){
                sex = "女";
            }
            Student student = new Student(i,"student"+i,sex);
            addStudent(file,student);
        }
    }

    //随机抽出一名学生
    public static void randomStudent(File file){
        Random random = new Random();
        int i = random.nextInt(51)+1;
        try(
                LineNumberReader reader = new LineNumberReader(new FileReader(file));
                ) {
            while(reader.getLineNumber() != i){
                reader.readLine();
            }
            System.out.println("取到的随机数为："+i+"\n学生信息为："+reader.readLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        //向学生文件中添加简单的学生信息
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习与作业\\file\\src\\com\\github\\nodonotnodo\\coll_over\\StudentList").toFile();
//        addBatchStudent(file,50);

        randomStudent(file);
    }

}

class Student{
    private int id;//学号
    private String name;//姓名
    private String sex;//性别

    public Student(int id, String name, String sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
