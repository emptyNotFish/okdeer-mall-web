package com.okdeer.mall.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zip文件解压 
 * @author Administrator
 *
 */
public class ZipUtil3 {
	   
 private static final Logger logger = LoggerFactory.getLogger(ZipUtil.class);
 
 static final int BUFFER = 2048;
 
 /**
  * 解压文件操作
  *
  * @param file
  *            源文件对象
  * @param dest
  *            解压的目标路径
  */
 @SuppressWarnings("rawtypes")
	public static void unZip(File file1, String dest) {
      try {
	         ZipFile zipFile = new ZipFile(file1.getAbsolutePath());
	         Enumeration emu = zipFile.entries();
	         while(emu.hasMoreElements()){
	             ZipEntry entry = (ZipEntry)emu.nextElement();
	             //会把目录作为一个file读出一次，所以只建立目录就可以，之下的文件还会被迭代到。
	             if (entry.isDirectory())
	             {
	                 new File(dest +File.separator+ entry.getName().replace("\\",File.separator)).mkdirs();
	                 continue;
	             }
	             BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
	             File file = new File(dest +File.separator+ entry.getName().replace("\\",File.separator));
	             //加入这个的原因是zipfile读取文件是随机读取的，这就造成可能先读取一个文件
	             //而这个文件所在的目录还没有出现过，所以要建出目录来。
	             File parent = file.getParentFile();
	             if(parent != null && (!parent.exists())){
	                 boolean success = parent.mkdirs();
	                 logger.info("create folder {}",success);
	             }
	             FileOutputStream fos = new FileOutputStream(file);
	             BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER);          
	            
	             int count;
	             byte data[] = new byte[BUFFER];
	             while ((count = bis.read(data, 0, BUFFER)) != -1)
	             {
	                 bos.write(data, 0, count);
	             }
	             bos.flush();
	             bos.close();
	             bis.close();
	             fos.close();
	         }
	         zipFile.close();
  } catch (Exception e) {
      e.printStackTrace();
  }
 }
 
}
