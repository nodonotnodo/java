package com.github.nodonotnodo.homework;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

import java.io.*;
import java.nio.file.*;
import java.util.Date;

/**
 * 文件处理工具
 * <p>
 * Author: secondriver
 * Created: 2018/10/13
 */
public final class FileUtil {
    
    
    private FileUtil() {
    }

    //检查文件是否存在,存在返回true,不存在返回false
    public static boolean checkExists(File file){
        if(!file.exists()){
            System.out.println("文件不存在，请输入正确的文件名称");
            return false;
        }
        return true;
    }

    //检查文件是否是目录，不是返回false,不是在返回true
    public static boolean cheakDir(File file){
        if(!file.isDirectory()){
            System.out.println("请输入一个目录名");
            return false;
        }
        return true;
    }

    //检查文件是否是目录，不是返回false,不是在返回true
    public static boolean checkFile(File file){
        if(!file.isDirectory()){
            System.out.println("请输入一个文件名");
            return false;
        }
        return true;
    }

    /**
     * 是否为Windows环境
     *
     * @return 是否为Windows环境
     */
    public static boolean isWindows() {
        char c = File.separatorChar;
        if('\\' == c){
            return true;
        }
        return false;
    }

    /**
     * 列出目录文件<br>
     * 给定的绝对路径不能是压缩包中的路径
     *
     * @param path 目录绝对路径或者相对路径
     * @return 文件列表（包含目录）
     */
    public static File[] ls(String path) {
        File file = Paths.get(path).toFile();
        if((!checkExists(file)) || (!cheakDir(file))){
            return null;
        }
        String[] str = file.list();
        if(str != null){
            System.out.println("要查看内容的目录："+path);
            for(String v:str){
                System.out.printf("%-30s",v);
            }
            System.out.println();
        }
        return null;
    }

    /**
     * 目录是否为空
     *
     * @param dirPath 目录
     * @return 是否为空
     */
    public static boolean isDirEmpty(Path dirPath) throws Exception {
        File file = dirPath.toFile();
        if((!checkExists(file)) || (!cheakDir(file))){
            throw new Exception();
        }
        if(null == file.list()) {
            return true;
        }
        return false;
    }

    /**
     * 获取标准的绝对路径
     *
     * @param file 文件
     * @return 绝对路径
     */
    public static String getAbsolutePath(File file) throws Exception {
        if((!checkExists(file)) || (!checkFile(file))){
            throw new Exception();
        }
        return file.getAbsolutePath();
    }

    /**
     * 给定路径已经是绝对路径<br>
     * 此方法并没有针对路径做标准化
     *
     * @param path 需要检查的Path
     * @return 是否已经是绝对路径
     */
    public static boolean isAbsolutePath(String path) throws Exception {
        File file = Paths.get(path).toFile();
        if(!checkExists(file)){
            throw new Exception();
        }
        return file.isAbsolute();
    }


    /**
     * 获取临时文件路径（绝对路径）
     *
     * @return 临时文件路径
     */
    public static String getTmpDirPath() {
        //TODO
        return null;
    }

    /**
     * 获取用户路径（绝对路径）
     *
     * @return 用户路径
     */
    public static String getUserHomePath() {
        //TODO
        File file = Paths.get("").toFile();
        return file.getAbsolutePath();
    }

    /**
     * 判断文件是否存在，如果file为null，则返回false
     *
     * @param file 文件
     * @return 如果存在返回true
     */
    public static boolean exist(File file) {
        if(null != file && file.exists()){
            return true;
        }
        return false;
    }


    /**
     * 是否存在匹配文件
     *
     * @param directory 文件夹路径
     * @param regexp    文件夹中所包含文件名的正则表达式
     * @return 如果存在匹配文件返回true
     */
    public static boolean exist(String directory, String regexp) {
        //TODO
        return false;
    }

    /**
     * 指定文件最后修改时间
     *
     * @param file 文件
     * @return 最后修改时间
     */
    public static Date lastModifiedTime(File file) throws Exception {
        if(file == null || (!file.exists())){
            throw new Exception();
        }
        return new Date(file.lastModified());
    }


    /**
     * 计算目录或文件的总大小<br>
     * 当给定对象为文件时，直接调用 {@link File#length()}<br>
     * 当给定对象为目录时，遍历目录下的所有文件和目录，递归计算其大小，求和返回
     *
     * @param file 目录或文件
     * @return 总大小，bytes长度
     */
    public static long size(File file) {
        if(file == null){
            return 0L;
        }
        if(file.isFile()){
            return file.length();
        }
        long result = 0;
        File[] files = file.listFiles();
        for(File f:files){
            result += (size(f));
        }
        return result;
    }

    /**
     * 给定文件或目录的最后修改时间是否晚于给定时间
     *
     * @param file      文件或目录
     * @param reference 参照文件
     * @return 是否晚于给定时间
     */
    public static boolean newerThan(File file, File reference) throws Exception {
        if(file == null || reference == null || (!file.exists()) || (!reference.exists())){
            throw new Exception();
        }
        Date fdate = new Date(file.lastModified());
        Date fedate = new Date(reference.lastModified());
        return fdate.after(fedate);
    }

    /**
     * 给定文件或目录的最后修改时间是否晚于给定时间
     *
     * @param file       文件或目录
     * @param timeMillis 做为对比的时间
     * @return 是否晚于给定时间
     */
    public static boolean newerThan(File file, long timeMillis) throws Exception {
        if(file == null || (!file.exists())){
            throw new Exception();
        }
        Date fdate = new Date(file.lastModified());
        Date tmDate = new Date(timeMillis);
        return fdate.after(tmDate);
    }

    /**
     * 创建所给文件或目录的父目录
     *
     * @param file 文件或目录
     * @return 父目录
     */
    public static File mkParentDirs(File file) throws Exception {
        if(file == null){
            throw new Exception();
        }
        File pafile = file.getParentFile();
        pafile.mkdirs();
        return pafile;
    }

    /**
     * 创建文件及其父目录，如果这个文件存在，直接返回这个文件<br>
     * 此方法不对File对象类型做判断，如果File不存在，无法判断其类型
     *
     * @param file 文件对象
     * @return 文件，若路径为null，返回null
     */
    public static File touch(File file) throws Exception {
        if(file == null) {
            throw new Exception();
        }
        if(file.exists()){
            return file;
        }else{
             file.getParentFile().mkdirs();
             file.createNewFile();
             return null;
        }
    }


    /**
     * 删除文件或者文件夹<br>
     * 注意：删除文件夹时不会判断文件夹是否为空，如果不空则递归删除子文件或文件夹<br>
     * 某个文件删除失败会终止删除操作
     *
     * @param file 文件对象
     * @return 成功与否
     */
    public static boolean del(File file) throws Exception {
        if(file == null){
            throw new Exception();
        }
        if(file.isFile()) {
            file.delete();
            return true;
        }
        if(file.isDirectory()){
            File[] wdel = file.listFiles();
            for(File v:wdel){
                if(!del(v)){
                    return false;
                }
            }
            file.delete();
        }
        return true;
    }

    /**
     * 清空文件夹<br>
     * 注意：清空文件夹时不会判断文件夹是否为空，如果不空则递归删除子文件或文件夹<br>
     * 某个文件删除失败会终止删除操作
     *
     * @param directory 文件夹
     * @return 成功与否
     */
    public static boolean clean(File directory) throws Exception {
        if(directory == null || (!directory.isDirectory())){
            throw new Exception();
        }
        File[] wdel = directory.listFiles();
        for(File v:wdel){
            if(!del(v)){
                return false;
            }
        }
        return true;
    }

    /**
     * 通过JDK7+的 {@link Files#copy(Path, Path, CopyOption...)} 方法拷贝文件
     *
     * @param src  源文件
     * @param dest 目标文件或目录，如果为目录使用与源文件相同的文件名
     * @return 目标文件
     */
    public static File copyFile(File src, File dest) throws Exception {
        if(src == null || dest == null){
            throw new Exception();
        }
        if(dest.isDirectory()){
            File ndest = Paths.get(dest.toString(),src.getName()).toFile();
            return Files.copy(src.toPath(),ndest.toPath(),StandardCopyOption.COPY_ATTRIBUTES).toFile();
        }else{
            Path psrc = src.toPath();
            Path pdest = dest.toPath();
            return Files.copy(psrc,pdest,StandardCopyOption.COPY_ATTRIBUTES).toFile();
        }
    }


    /**
     * 移动文件或者目录
     *
     * @param src        源文件或者目录
     * @param dest       目标文件或者目录
     * @param isOverride 是否覆盖目标，只有目标为文件才覆盖
     */
    public static void move(File src, File dest, boolean isOverride) throws Exception {
        if(src == null || dest == null || (!src.exists())){
            throw new Exception();
        }
        Path psrc = src.toPath();
        Path pdest = dest.toPath();
        if(src.isFile()){//源文件是一个文件
            if(!dest.exists()){
                if(isOverride) {
                    dest.getParentFile().mkdirs();
                    dest.createNewFile();
                }
            }
            if(dest.isDirectory()){
                pdest = Paths.get(dest.toString(),src.getName());
                Files.move(psrc,pdest,StandardCopyOption.REPLACE_EXISTING);
                return;
            }
            if(isOverride){
                Files.move(psrc,pdest,StandardCopyOption.REPLACE_EXISTING);
                return;
            }else{
                System.out.println("移动失败！！目标是一个文件,不对文件进行覆盖。");
                return;
            }
        }
        if(src.isDirectory()){//源文件是一个目录
            if(!dest.exists()){
                dest.mkdirs();
            }
            if(!dest.isDirectory()){
                System.out.println("移动失败！！源是一个目录，目标是一个文件，目录无法移动到文件中");
                return;
            }
            if(!dest.exists()){//目标目录不存在
                dest.mkdir();
            }
            pdest = Paths.get(dest.toString(),src.getName());
            Files.move(psrc,pdest,StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * 修改文件或目录的文件名，不变更路径，只是简单修改文件名<br>
     * 重命名有两种模式：<br>
     * 1、isRetainExt为true时，保留原扩展名：
     *
     * <pre>
     * FileUtil.rename(file, "aaa", true) xx/xx.png =》xx/aaa.png
     * </pre>
     * <p>
     * 2、isRetainExt为false时，不保留原扩展名，需要在newName中
     *
     * <pre>
     * FileUtil.rename(file, "aaa.jpg", false) xx/xx.png =》xx/aaa.jpg
     * </pre>
     *
     * @param file        被修改的文件
     * @param newName     新的文件名，包括扩展名
     * @param isRetainExt 是否保留原文件的扩展名，如果保留，则newName不需要加扩展名
     * @param isOverride  是否覆盖目标文件
     * @return 目标文件
     */
    public static File rename(File file, String newName, boolean isRetainExt, boolean isOverride) throws Exception {
        //TODO
        if(file == null || (!file.exists())){
            throw new Exception();
        }
        File destFile = null;
        if(isRetainExt){//保留扩展名
            destFile = Paths.get(file.getParent(),newName).toFile();
        }else{//不保留扩展名
            String[] fileNameArr = file.getName().split("\\.");
            String newFileName = "";
            for(int i = 0; i<fileNameArr.length-1; i++){
                newFileName = newFileName+fileNameArr[i];
            }
            destFile = Paths.get(file.getParent().toString(),newFileName).toFile();
            System.out.println(destFile.toString());
        }

        if(destFile.exists()){//目标文件已经存在
            if(isOverride){//允许覆盖
                file.renameTo(destFile);
                return destFile;
            }else{//不允许覆盖
                System.out.println("目标文件已经存在，无法覆盖");
                return null;
            }
        }else{//目标文件不存在
            file.renameTo(destFile);
            return destFile;
        }
    }


    /**
     * 比较两个文件内容是否相同<br>
     * 首先比较长度，长度一致再比较内容<br>
     * 此方法来自Apache Commons io
     *
     * @param file1 文件1
     * @param file2 文件2
     * @return 两个文件内容一致返回true，否则false
     */
    public static boolean contentEquals(File file1, File file2) throws Exception {
        if(file1 == null || file2 == null || (!file1.exists()) || (!file2.exists())){
            throw new Exception();
        }
        try(
                InputStream in1 = new FileInputStream(file1);
                InputStream in2 = new FileInputStream(file2);
                ){
            if(file1.length() == file2.length()){
                long length1 = 0L;
                long length2 = 0L;
                byte[] buffer1 = new byte[5];
                byte[] buffer2 = new byte[5];
                while((length1 = in1.read(buffer1)) == (length2 = in2.read(buffer2)) && (length1 != -1)){
                    for(int i = 0; i<length1; i++){
                        if(buffer1[i] != buffer2[i]){
                            return false;
                        }
                    }
                }
                return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 文件路径是否相同<br>
     * 取两个文件的绝对路径比较，在Windows下忽略大小写，在Linux下不忽略。
     *
     * @param file1 文件1
     * @param file2 文件2
     * @return 文件路径是否相同
     */
    public static boolean pathEquals(File file1, File file2) throws Exception {
        if(file1 == null || file2 == null || (!file1.exists()) || (!file2.exists())){
            throw new Exception();
        }
        String way1 = file1.getParent();
        String way2 = file2.getParent();
        char c = File.separatorChar;
        if('\\' == c){//Windows环境
            return way1.equalsIgnoreCase(way2);
        }else{//Linux环境
            return way1.equals(way2);
        }
    }


    /**
     * 检查两个文件是否是同一个文件<br>
     * 所谓文件相同，是指File对象是否指向同一个文件或文件夹
     *
     * @param file1 文件1
     * @param file2 文件2
     * @return 是否相同
     * @throws RuntimeException IO异常
     * @see Files#isSameFile(Path, Path)
     */
    public static boolean equals(File file1, File file2) throws Exception {
        if(file1 == null || file2 == null || (!file1.exists()) || (!file2.exists())){
            throw new Exception();
        }
        Path path1 = file1.toPath();
        Path path2 = file2.toPath();
        return Files.isSameFile(path1,path2);
    }


    /**
     * 获取文件扩展名，扩展名不带“.”
     *
     * @param file 文件
     * @return 扩展名
     */
    public static String extName(File file) throws Exception {
        if(file == null){
            throw new Exception();
        }
        if(file.isFile()){
            String fname = file.getName();
            String[] name = fname.split("\\.");
            return name[name.length-1];
        }
        return null;
    }


    /**
     * 获取指定层级的父路径
     *
     * <pre>
     * getParent(file("d:/aaa/bbb/cc/ddd", 0)) -> "d:/aaa/bbb/cc/ddd"
     * getParent(file("d:/aaa/bbb/cc/ddd", 2)) -> "d:/aaa/bbb"
     * getParent(file("d:/aaa/bbb/cc/ddd", 4)) -> "d:/"
     * getParent(file("d:/aaa/bbb/cc/ddd", 5)) -> null
     * </pre>
     *
     * @param file  目录或文件
     * @param level 层级
     * @return 路径File，如果不存在返回null
     */
    public static File getParent(File file, int level) throws Exception {
        if(file == null){
            throw new Exception();
        }
        File result = file;
        for(int i = 0; i<level; i++){
            result = result.getParentFile();
        }
        return result;
    }

    /**
     * 检查父完整路径是否为自路径的前半部分，如果不是说明不是子路径，可能存在slip注入。
     * <p>
     * 见http://blog.nsfocus.net/zip-slip-2/
     *
     * @param parentFile 父文件或目录
     * @param file       子文件或目录
     * @return 子文件或目录
     * @throws IllegalArgumentException 检查创建的子文件不在父目录中抛出此异常
     */
    public static File checkSlip(File parentFile, File file) throws IllegalArgumentException {
        //TODO
        return null;
    }

    //判断当前系统是否为Windows
    public static void code1(){
        System.out.println("当前系统是否是Windows系统："+isWindows());
    }

    //打印目录下内容信息
    public static void code2(){
        ls("F:\\比特\\39班学习\\java");
        ls("F:\\比特\\39班学习\\jav");
        ls("F:\\比特\\39班学习\\java\\java练习文件夹\\practice2");
    }

    //判断一个文件夹是否为空
    public static void code3() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java").toFile();
        System.out.println(file.toString()+"目录是否是一个空目录："+isDirEmpty(file.toPath()));
    }

    //获取一个文件的绝对路径
    public static void code4() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java").toFile();
        System.out.println(file.toString()+"的绝对路径为："+getAbsolutePath(file));
    }

    //判断一个File类对象是否是绝对目录
    public static void code5() throws Exception {
        System.out.println(".是否是绝对目录："+isAbsolutePath("."));
        System.out.println("F:\\比特\\39班学习\\java是否是绝对目录："+isAbsolutePath("F:\\比特\\39班学习\\java"));
        //System.out.println("F:\\比特\\39班学习\\jav是否是绝对目录："+isAbsolutePath("F:\\比特\\39班学习\\jav"));
    }

    //获取用户当前路径
    public static void code6(){
        System.out.println("当前路径："+getUserHomePath());
    }

    //判断文件是否存在，如果file为null，则返回false
    public static void code7(){
        File file = Paths.get("F:\\比特\\39班学习\\java").toFile();
        System.out.println(file.toString()+"文件是否存在:"+exist(file));
        file = Paths.get("F:\\比特\\39班学习\\jav").toFile();
        System.out.println(file.toString()+"文件是否存在:"+exist(file));
    }

    //返回指定文件的最后修改时间
    public static void code8() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\practice2").toFile();
        System.out.println(file.toString()+"文件的最后修改时间为："+lastModifiedTime(file));
        try(
                OutputStream out = new FileOutputStream(file,true);
        ){
            String str = "这是为了测试修改时间所修改的内容";
            byte[] bs = str.getBytes();
            out.write(bs);
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(file.toString()+"文件的最后修改时间为："+lastModifiedTime(file));
    }

    //计算目录或文件的总大小
    public static void code9(){
        System.out.println(size(null));
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\practice2").toFile();
        System.out.println(file.toString()+"-文件的总大小为："+size(file));
        file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹").toFile();
        System.out.println(file.toString()+"-文件的总大小为："+size(file));
    }

    //给定文件或目录的最后修改时间是否晚于给定文件最后修改时间
    public static void code10() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹").toFile();
        File refile = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\practice2").toFile();
        System.out.println(refile.toString()+"-文件的最后修改时间："+new Date(refile.lastModified()));
        System.out.println(file.toString()+"-文件的最后修改时间："+new Date(file.lastModified()));
        System.out.println(file.toString()+"-文件的最后修改时间是否晚于-"+refile.toString()+"-文件："+newerThan(file,refile));
        System.out.println(refile.toString()+"-文件的最后修改时间是否晚于-"+file.toString()+"-文件："+newerThan(refile,file));
    }

    //给定文件或目录的最后修改时间是否晚于给定文件最后修改时间
    public static void code11() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹").toFile();
        File refile = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\practice2").toFile();
        System.out.println(newerThan(file,refile.lastModified()));
        System.out.println(newerThan(refile,file.lastModified()));
    }

    //创建所给文件或目录的父目录
    public static void code12() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(3)\\practice2_F").toFile();
        System.out.println(mkParentDirs(file));
    }

    //创建文件及其父目录
    public static void code13() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(4)\\practice2_F").toFile();
        System.out.println(touch(file));
    }

    //删除文件或者文件夹
    public static void code14() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(4)\\practice2_F").toFile();
        System.out.println(del(file));
        System.out.println(touch(file));
        //file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(4)").toFile();
        //System.out.println(del(file));
    }

    //清空文件夹
    public static void code15() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(4)").toFile();
        System.out.println(clean(file));
    }

    //通过JDK7+的 {@link Files#copy(Path, Path, CopyOption...)} 方法拷贝文件
    public static void code16() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(4)\\practice2_F").toFile();
        System.out.println(touch(file));
        File destfile = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(3)\\practice2_copyF").toFile();
        System.out.println("copyFile(file,destfile):"+copyFile(file,destfile));
        destfile = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(5)").toFile();
        System.out.println("copyFile(file,destfile):"+copyFile(file,destfile));
    }

    //移动文件或者目录
    public static void code17() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(4)\\practice2_copyF").toFile();
        File destFile = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(6)").toFile();
//        move(file,destFile,true);
        file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(6)").toFile();
        destFile = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)").toFile();
        move(file,destFile,true);
    }

    //比较两个文件内容是否相同
    public static void code18() throws Exception {
        File file1 = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(5)\\practice2_F").toFile();
        File file2 = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\java练习文件夹(6)").toFile();
        System.out.println(contentEquals(file1,file2));
//        System.out.println(contentEquals(null,file2));
        file2 = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\test.txt").toFile();
        System.out.println(contentEquals(file1,file2));
    }

    //文件路径是否相同
    public static void code19() throws Exception {
        File file1 = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\test.txt").toFile();
        File file2 = Paths.get("F:\\比特\\39班学习\\JAVA\\java练习文件夹\\JAVA练习文件夹(7)\\java练习文件夹(6)").toFile();
        System.out.println(pathEquals(file1,file2));
    }

    //文件路径是否相同
    public static void code20() throws Exception {
        File file1 = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\test.txt").toFile();
        File file2 = Paths.get("F:\\比特\\39班学习\\JAVA\\java练习文件夹\\JAVA练习文件夹(7)\\test - 副本.txt").toFile();
        System.out.println(equals(file1,file2));
    }

    //获取文件扩展名，扩展名不带“.”
    public static void code21() throws Exception {
        File file1 = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\test.txt").toFile();
        System.out.println(file1.toString()+"文件的扩展名为："+extName(file1));
    }

    //获取指定层级的父路径
    public static void code22() throws Exception {
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\test.txt").toFile();
        System.out.println(file.toString()+"\ngetParent(file,3):"+getParent(file,3));
        System.out.println(file.toString()+"\ngetParent(file,7):"+getParent(file,7));
    }

    public static void main(String[] args) throws Exception {

        //获取临时文件路径（绝对路径）

        //获取用户路径（绝对路径）


//        是否存在匹配文件
//     @param directory 文件夹路径
//     @param regexp    文件夹中所包含文件名的正则表达式

        /**
         * 修改文件或目录的文件名，不变更路径，只是简单修改文件名<br>
         * 重命名有两种模式：<br>
         * 1、isRetainExt为true时，保留原扩展名：
         *
         * <pre>
         * FileUtil.rename(file, "aaa", true) xx/xx.png =》xx/aaa.png
         * </pre>
         * <p>
         * 2、isRetainExt为false时，不保留原扩展名，需要在newName中
         *
         * <pre>
         * FileUtil.rename(file, "aaa.jpg", false) xx/xx.png =》xx/aaa.jpg
         * </pre>
         *
         * @param file        被修改的文件
         * @param newName     新的文件名，包括扩展名
         * @param isRetainExt 是否保留原文件的扩展名，如果保留，则newName不需要加扩展名
         * @param isOverride  是否覆盖目标文件
         * @return 目标文件
         */
        File file = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\test - 副本.txt").toFile();
        File destFile = Paths.get("F:\\比特\\39班学习\\java\\java练习文件夹\\java练习文件夹(7)\\test_new.txt").toFile();
        System.out.println(rename(file,"test_new.txt",false,false));

    }

}
