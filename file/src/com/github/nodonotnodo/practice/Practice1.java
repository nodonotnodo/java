package com.github.nodonotnodo.practice;

//打印一个文件夹下的文件信息

import java.io.File;
import java.util.Date;

public class Practice1 {

    public static void main(String[] args) {

        try{
            //创建一个文件流
            File file = new File(args[0]);
            //检查这个文件流是否为空或者不是文件夹
            checkFile(file);
            //打印文件夹下的文件信息
            printDirectoryInfo(file);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("请按照要求输入参数：String fileName");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //检查这个文件流是否为空或者不是文件夹
    private static void checkFile(File fileName) throws Exception {
        if(null == fileName || !fileName.isDirectory() || !fileName.exists()){
            throw new Exception("请检查输入的参数是否正确");
        }
    }

    //打印文件夹下的文件信息
    private static void printDirectoryInfo(File fileNmae){
        File[] files = fileNmae.listFiles();
        System.out.printf("文件名%40c\t文件大小%40c\t最后修改时间\n",' ',' ',' ');
        int i = 0;
        while(i<files.length){
            System.out.printf("%-40s\t%-40s\t%40s\n",files[i].toString(),files[i].length(),new Date(files[i].lastModified()));
            i++;
        }
    }

    //得到一个文件的类型，文件返回file,文件夹返回dir
    private static String typeOfFile(File fileName){
        if(fileName.isDirectory()){
            return "dir";
        }
        return "file";
    }
}
