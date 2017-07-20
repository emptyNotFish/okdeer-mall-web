package com.okdeer.mall.file;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.util.StringUtils;


/**
 * 生成文件路径和图片格式工具类
 * @author Administrator
 *
 */
public class FileUtils {
	   
    public static String buildFilePath(String uuid){
        if(!StringUtils.isBlank(uuid)){
            long t = Math.abs((long)uuid.hashCode());
            return java.io.File.separator + t%256 + java.io.File.separator + ((t>>8) % 256) +java.io.File.separator + ((t>>16) % 256) + java.io.File.separator;
        }else{
            throw new RuntimeException("必须生成一个UUID");
        }
    }
    public static String getSystemDir(){
        //return System.getProperty("user.dir")+File.separator+"upload";
        return "Z:"+File.separator;
    }
   
    public static String buildWebPath(String uuid) {
        if(!StringUtils.isBlank(uuid)){
            long t = Math.abs((long)uuid.hashCode());
            return t%256 + "/" + ((t>>8) % 256) + "/" + ((t>>16) % 256);
        }else{
            throw new RuntimeException("必须生成一个UUID");
        }
    }
    /**
     * 获取图片流的具体格式
     * @param o
     * @return
     * @throws IOException
     */
    private static String getFormatName(Object o) throws IOException{ 
            ImageInputStream iis = ImageIO.createImageInputStream(o); 
 
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis); 
            if (!iter.hasNext()) { 
                return null; 
            } 
 
            ImageReader reader = iter.next(); 
 
            iis.close(); 
            return reader.getFormatName(); 
    }
    /**
     * 规定的图片格式
     * @param fileFormat
     * @return
     * @throws IOException
     */
    public static boolean isImage(Object o) throws IOException {
        String fileFormat = getFormatName(o);
        List<String> allowType = Arrays.asList("png","jpg","jpeg","bmp");
        return fileFormat != null ? allowType.contains(fileFormat.toLowerCase()) : false;
    } 
}

