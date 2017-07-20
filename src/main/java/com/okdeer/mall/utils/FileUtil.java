package com.okdeer.mall.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
/**
 * 文件操作工具类
 * @author Administrator
 *
 */
public class FileUtil {
	   
    /** log日志对象 */
    private static transient Logger logger = Logger.getLogger(FileUtil.class);
   
    private static final int DEFAULT_BUFF_SIZE=1024;
    private static final int UNIT_LEN=8;
    /**
     * 根据目录，创建文件
     * @param filePath
     * @return
     */
    public static File createDir(String filePath) {
        File file = new File(filePath);
        File parent = file.getParentFile();
        if(parent!=null && !parent.exists()){
            parent.mkdirs();
        }
        try {
            file.createNewFile();
        }catch (IOException e1) {
            e1.printStackTrace();
        }
        return file;
    }
   
    /**
     * 复制文件
     * @param oldPath：原文件路径
     * @param newPath：目标文件路径
     * @return ：true:复制成功 ，false:复制失败
     * @throws IOException
     */
    public static void copyFile(String oldPath, String newPath) throws IOException {
        int bytesum = 0;
        int byteread = 0;
        File oldfile = new File(oldPath);
        //判断原文件是否存在
        if (oldfile.exists()) {
            //读入原文件
            InputStream inStream = new FileInputStream(oldPath);
            File file = new File(newPath);
            file.getParentFile().mkdirs();
            //构造文件输出流
            FileOutputStream fs = new FileOutputStream(newPath);
            byte[] buffer = new byte[DEFAULT_BUFF_SIZE];
            while ( (byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; //字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
            fs.flush();
            fs.close();
        }else{
            throw new IOException("文件路径："+oldfile+" 不存在！");
        }
    }
   
   
    /**
     * 判断是否是一个文件
     * @param filename
     * @return
     */
    public static boolean isFile(String filename) {
        File file;
        if(StringUtils.hasText(filename)) {
            file = new File(filename);
            if(file.exists() && file.isFile()) {
                return true;
            }
        }
        return false;
    }
   
   
    /**
     * 判断是否是一个目录
     * @param filename
     * @return
     */
    public static boolean isDir(String filename) {
        File file;
        if(StringUtils.hasText(filename)) {
            file = new File(filename);
            if(file.exists() && file.isDirectory()) {
                return true;
            }
        }
        return false;
    }
   
    /**
     * 获取文件的扩展名（不包含'.'）
     * @param filename
     */
    public static String extension( String filename ){
        // Ensure the last dot is after the last file separator
        int lastSep = filename.lastIndexOf( File.separatorChar );
        int lastDot;
        if ( lastSep < 0 ){
            lastDot = filename.lastIndexOf( '.' );
        } else {
            lastDot = filename.substring( lastSep + 1 ).lastIndexOf( '.' );
            if ( lastDot >= 0 ){
                lastDot += lastSep + 1;
            }
        }
        if ( lastDot >= 0 && lastDot > lastSep) {
            return filename.substring( lastDot + 1 );
        } else {
            return "";
        }
    }
   
    /**
     * 移除文件的路径，得到文件名称
     * @param filepath
     * @return
     */
    public static String removePath( final String filepath ) {
        return removePath( filepath, File.separatorChar );
    }
   
    public static String removePath( final String filepath, final char fileSeparatorChar ){
        
        final int index = filepath.lastIndexOf( fileSeparatorChar );
        if ( -1 == index ){
            return filepath;
        } else {
            return filepath.substring( index + 1 );
        }
    }
   
    public static String basename( String filename, String suffix ){
        int i = filename.lastIndexOf( File.separator ) + 1;
        int lastDot = ( ( suffix != null ) && ( suffix.length() > 0 ) ) ? filename.lastIndexOf( suffix ) : -1;
        if ( lastDot >= 0 ) {
            return filename.substring( i, lastDot );
        } else if ( i > 0 ) {
            return filename.substring( i );
        } else {
            return filename; // else returns all (no path and no extension)
        }
    }
   
    public static void determineDirectory(String filePath) throws IOException {
        File file;
        if(StringUtils.hasText(filePath)) {
            file = new File(filePath);
            if(file.exists() == true && file.isFile()) {
                file = file.getParentFile();
            }
           
            if(file != null && file.exists() == false) {
                if(file.mkdirs() == false) {
                    throw new IOException("Destination '" + file + "' directory cannot be created");
                }
            }
           
            if (file.exists() && file.canWrite() == false) {
                throw new IOException("Destination '" + file + "' exists but is read-only");
            }
        }
    }
   
    public static String generateTempDir(String filePath, String tempDir) throws IOException {
        String path = "";
        if(StringUtils.hasText(filePath)) {
            String parent = filePath;
            if(isFile(filePath) || filePath.lastIndexOf(".") != -1) {
                parent = new File(filePath).getParentFile().getAbsolutePath();
            }
           
            File newDirectory = new File( parent +  File.separatorChar + tempDir );
            path = newDirectory.getAbsolutePath();
            determineDirectory(path);
        }
        return (StringUtils.hasText(path)) ? path + File.separatorChar : path;
    }
   
    public static boolean determineTempDir(String parentDir, String tempDir) {
        
        if(StringUtils.hasText(parentDir)) {
            File newDirectory = new File( parentDir +  File.separatorChar + tempDir );
            return newDirectory.exists() ? true : false;
        }
        return false;
    }
   
    public static String addFilenameByWord(String filename, String addWord) {
        if(!StringUtils.hasText(addWord) || !isFile(filename)) {
            return filename;
        }
        File file = new File(filename).getParentFile();
        String extSuffix = "." + FileUtil.extension(filename);
        String basename = FileUtil.basename(filename, extSuffix);
        return file.getAbsolutePath() + File.separatorChar + basename + addWord + extSuffix;
    }
   
    /**
     * 创建目录
     * @param dirPath
     * @return
     */
    public static boolean createDirectory(String dirPath)
    {
        File file=new File(dirPath);
        return file.mkdirs();
    }
    /**
     * 删除文件或目录(包括子目录及文件)
     * @param folderPath ：目录路径或文件路径
     * @throws Exception
     */
    public static void delFolder(String folderPath) throws Exception{
        if(folderPath != null) {
            File file = new File(folderPath);
            if(file.exists()) {
                //删除目录中所有子目录和文件
                delAllFile(folderPath);
              
                //删除空文件夹
                File myFilePath = new File(folderPath);
                myFilePath.delete();
            }
        }
     
    }
   
    /**
     * 删除目录中的子目录及文件
     * @param path
     * @throws Exception
     */
    public static void delAllFile(String path) throws Exception
    {
        File file = new File(path);
        if (!file.exists())
        {
            String msg="删除文件时,指定的路径不存在,文件路径："+path;
            throw new Exception(msg);
        }
        if (!file.isDirectory())
        {
            return ;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++)
        {
            if (path.endsWith(File.separator))
            {
                temp = new File(path + tempList[i]);
            }
            else
            {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile())
            {
                temp.delete();
            }
            if (temp.isDirectory())
            {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
            }
        }
    }
    public static void delFile(String path) throws Exception {
        File file = new File(path);
        if (!file.exists())
        {
            String msg="删除文件时,指定的文件不存在,文件路径："+path;
            throw new Exception(msg);
        }
        if (file.isDirectory())
        {
            delAllFile(path);
            return ;
        }else{
            file.delete();
        }
    }
   
  //获得指定文件的byte数组
    public static byte[] getBytes(String filePath,long startPos,long endPos){ 
        byte[] buffer = null; 
        try { 
            File file = new File(filePath); 
            FileInputStream fis = new FileInputStream(file); 
            ByteArrayOutputStream bos = new ByteArrayOutputStream(DEFAULT_BUFF_SIZE*UNIT_LEN); 
            byte[] b = new byte[DEFAULT_BUFF_SIZE*UNIT_LEN];
            int n; 
            while ((n = fis.read(b)) != -1) { 
                bos.write(b, 0, n); 
            } 
            fis.close(); 
            bos.close(); 
            buffer = bos.toByteArray(); 
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return buffer; 
    } 
}
