package com.github.nodonotnodo.practice;


import java.io.*;
import java.nio.file.Paths;
import java.util.Date;

//简单的File类使用
public class Practice2 {

    //简单的创建文件流，查看文件是否存在以及创建文件。
    public static void code1(){
        //File file1 = new File("F:\\比特\\39班学习\\java\\java练习文件夹\\practice2");
        //这种代码在写文件分隔符时，由于Windows和linux，Unix系统的分隔符不一致，所以不支持跨平台性。

        //File file1 = new File("F:"+File.separator+"比特"+File.separator+"39班学习"+File.separator+"java" + File.separator +"java练习文件夹"+File.separator+"practice2");
        //调用了File.separator,有jvm自己判断需要哪种分隔符。但是代码量十分庞大

        File file1 = Paths.get("F:","比特","39班学习","java","java练习文件夹","practice2").toFile();
        //使用了Paths类，比较简便，以后都这样使用。

        if(file1.exists()){
            System.out.println("文件"+file1+"存在，无法创建");
        }else{
            try{
                file1.createNewFile();
                System.out.println("文件创建成功");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    //创建文件流，删除文件
    public static void code2(){
        //此时文件practice2已经存在
        File file1 = Paths.get("F:","比特","39班学习","java","java练习文件夹","practice2").toFile();

        if(file1.exists()){
            file1.delete();
            System.out.println("文件存在，删除成功");
        }else{
            System.out.println("文件不存在，无法删除");
        }
    }

    //文件类型查看以及文件的路径查看和最后修改日期查看
    public static void code3(){
        File file1 = Paths.get("F:","比特","39班学习","java","java练习文件夹","practice2").toFile();
        //使用了Paths类，比较简便，以后都这样使用。

        if(file1.exists()){
            System.out.println("文件"+file1+"存在，无法创建");
        }else{
            try{
                file1.createNewFile();
                System.out.println("文件创建成功");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        System.out.println("文件的名称"+file1.getName());
        System.out.println(file1.getName()+"文件的父目录名称"+file1.getParent());
        System.out.println(file1.getName()+"是否是一个文件："+file1.isFile());
        System.out.println(file1.getName()+"是否是一个文件夹："+file1.isDirectory());
        System.out.println(file1.getName()+"文件最后一次修改时间："+new Date(file1.lastModified()));
    }

    //文件路径的创建
    public static void code4(){
        File file1 = Paths.get("F:","比特","39班学习","java","java练习文件夹","java练习文件夹(1)","java练习文件夹(2)","practice2(1)").toFile();
        //使用了Paths类，比较简便，以后都这样使用。

        if(file1.exists()){
            System.out.println("文件"+file1+"存在，无法创建");
            file1.delete();
            System.out.println("无法创建那么删除，删除成功");
        }else{
            try{
                file1.createNewFile();
                System.out.println("文件创建成功");
            }catch(IOException e){
                file1.getParentFile().mkdirs();
                try {
                    file1.createNewFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    //文件名的修改
    public static void code5(){
        File file1 = Paths.get("F:","比特","39班学习","java","java练习文件夹","java练习文件夹(1)","java练习文件夹(2)","practice2(1)").toFile();
        //使用了Paths类，比较简便，以后都这样使用。

        String pathName = "F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(1)\\java练习文件夹(2)\\practice2(3)";
        System.out.println(file1.getPath());
        File file2 = Paths.get(pathName).toFile();
        file1.renameTo(file2);
    }

    //字节输入流 InputStream 和 FileInputStream
    public static void code6(){
        File file1 = Paths.get("F:","比特","39班学习","java","java练习文件夹","practice2").toFile();
        InputStream in = null;
        try{
            try {
                in = new FileInputStream(file1);
                int leng = -1;
                //byte[] buffer = new byte[4];
                //缓冲区设定为4个字节时，打印结果为：一�����单的�����文件

                byte[] buffer = new byte[3];
                //缓冲区设定为4个字节时，打印结果为：一个简单的实验文件
                //可见在Java中一个汉字是占三个字节的。
                while((leng = in.read(buffer)) != -1){
                    System.out.print(new String(buffer,0,leng));
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        } finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //输出字节流及文件内容的修改和添加
    public static void code7(){
        File file1 = Paths.get("F:","比特","39班学习","java","java练习文件夹","practice2").toFile();
        OutputStream out = null;
        try{
            out = new FileOutputStream(file1);
            //在实例化一个FileOutputStream类的对象时，有一个构造方法参数为(File file,boolean append)
            //若赋值输入的append为true,则在写入文件时则会在原有文件上添加。

            String str = "这是一个新的文件内容";
            byte[] buffer = str.getBytes();
            out.write(buffer);
            //此时practice2文件已经被修改为新的内容，并且原来的内容已经被删除。

            out = new FileOutputStream(file1,true);
            str = "这是要在原文件内容后面添加的内容";
            buffer = str.getBytes();
            out.write(buffer);
            //此时文件内容为：这是一个新的文件内容这是要在原文件内容后面添加的内容
        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //code1();
        //code2();
        //code3();
        //code4();
        code7();
    }

}
