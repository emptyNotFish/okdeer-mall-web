package com.okdeer.mall.utils;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.okdeer.mall.exception.ServiceException;  
/** 
*  
* <b>类说明：文件操作工具类</b> 
* <p> 
 * <b>详细描述：</b> 
 * @author ***** 
 * @since ***** 
 */  
public class FileOperateAssistUtil {  
   
    // 日志记录  
    private static Logger logger = LoggerFactory.getLogger(FileOperateAssistUtil.class);  
  
    /** 
     *  
     * <b>方法说明：</b> 创建文件目录，若路径存在，就不生成 
     *  
     * <p> 
     * <b>详细描述：</b> 
     *  
     * @param 
     * @return 
     * @author ***** 
     * @since ***** 
     */  
    public static void createDocDir(String dirName) {  
        File file = new File(dirName);  
        if (!file.exists()) {  
            file.mkdirs();  
        }  
    }  
   
    /** 
     *  
     * <b>方法说明：</b> 创建文件目录 
     *  
     * <p> 
     * <b>详细描述：</b> 
     *  
     * @param 
     * @return 
     * @author ***** 
     * @since ***** 
     */  
    public static void isExistsMkDir(String dirName){  
        File file = new File(dirName);  
        if (!file.exists()) {  
            file.mkdirs();  
        }  
    }  
  
    /** 
     * <b>方法说明：</b> 本地，在指定路径生成文件。若文件存在，则删除后重建。 
     *  
     * @param dirName 
     *            本地路径名， 
     * @param file 
     *            文件， 
     * @return List<Object> 
     * @throws ServiceException 
     * @author ***** 
     * @since ***** 
     */  
    public static void creatFileByName(File file){  
        try {  
            if (file.exists()) {  
                file.delete();  
                logger.info("发现同名文件：{}，先执行删除，再新建。", file.getAbsolutePath());  
            }  
            file.createNewFile();  
            logger.info("创建文件为：{}", file.getAbsolutePath());  
        }  
        catch (IOException e) {  
            logger.error("创建{}文件失败", file.getAbsolutePath(), e);  
           
        }  
    }  
    /** 
     *  
     * <b>说明：</b> 
     * <b>详细描述：创建新文件，若文件存在则删除再创建，若不存在则直接创建</b> 
     * @param   
     * @returnType File 
     * @since ***** 
      * @author ***** 
     */  
    public static File newFile(String fileName) {  
        File file = new File(fileName);  
        creatFileByName(file);  
        return file;  
    }  
    /** 
     *  
     * <b>说明：</b> 
     * <b>详细描述：关闭写入流</b> 
     * @param   
      * @returnType void 
     * @since ***** 
     * @author ***** 
     */  
    public static void closeWriter(Writer writer) {  
        if (writer != null) {  
            try {  
                writer.close();  
            } catch (IOException e) {  
                // throw new ServiceException(BatchErrorCode.FILE_CLOSE_EXCEPTION, e);  
               logger.error("Close Writer cause Exception:", e);  
            }  
        }  
    }  
    /** 
     *  
     * <b>说明：</b> 
     * <b>详细描述：关闭写入流</b> 
     * @param   
     * @returnType void 
     * @since ***** 
     * @author ***** 
     */  
    public static void closeReader(Reader reader) {  
        if (reader != null) {  
            try {  
                reader.close();  
            } catch (IOException e) {   
                logger.error("Close reader cause Exception:", e);  
            }  
        }  
    }  
    /** 
    *  
    * <b>说明：</b> 
     * <b>详细描述：关闭随机读写流</b> 
     * @param   
     * @returnType void 
     * @since ***** 
     * @author ***** 
      */  
    public static void closeRandomAccessFile(RandomAccessFile raf){  
        if(raf != null){  
            try {  
                raf.close();  
            }catch (IOException e) {  
                throw new ServiceException(******,e, new String[]{"批量"});  
            }  
        }  
    }  
    public static String getBatchNo(String transDate, Long i) {  
         return transDate + getSerialNo(i);  
     }  
    public static String getFileBatchNo(String date) {  
        if(StringUtils.isBlank(date)){  
            return CtsDateUtil.getCurPcDate();  
        }  
        return date;  
     }  
    public static String getSerialNo(Long i) {  
         return CommUtil.LeftFill(String.valueOf(i), '0', 3);  
     }  
    public static String getSerialNo(int i) {  
        return CommUtil.LeftFill(String.valueOf(i), '0', 3);  
    }  
    /** 
     *  
    * <b>方法说明：</b> 创建控制文件 
     *  
      * <p> 
     * <b>详细描述：</b> 
    *  
     * @param 
     * @return 
     * @author ***** 
     * @since***** 
     */  
     public static void createControlFile(File dataFile, Long count) {  
        String controlFileName = dataFile.getAbsolutePath().replace(".DAT", ".CTL");  
        File controlFile = null;  
        BufferedWriter bw = null;  
        try {  
            controlFile = new File(controlFileName);  
            if (controlFile.exists()) {  
                controlFile.delete();  
                controlFile.createNewFile();  
            }  
            // 获取数据文件MD5  
            String dataFileMd5 = MD5EncoderUtil.getFileMd5(dataFile);  
            StringBuilder controlFileContext = new StringBuilder().append(dataFile.getName()).append("\t")  
                    .append(dataFile.length()).append("\t").append(count.toString()).append("\t")  
                    .append(dataFileMd5 == null ? "" : dataFileMd5);  
            // 将MD5写入控制文件  
            bw = new BufferedWriter(new FileWriter(controlFile, true));  
            bw.write(controlFileContext.toString());  
            bw.flush();  
        }  
         catch (Exception e) {  
             throw new ServiceException(*****, e, new String[]{"控制文件"}, "创建控制文件时发生异常");  
         }  
        finally {  
            if (bw != null) {  
                 try {  
                     bw.close();  
                 }  
                catch (IOException e) {  
                     throw new ServiceException(*****, e, new String[]{"控制文件"}, "创建控制文件时发生异常");  
                }  
            }  
         }  
     }  
   
     /** 
      *  
      * <b>方法说明：</b> 校验MD5 
      *  
      * <p> 
      * <b>详细描述：</b> 
      *  
      * @param 
     * @return 
      * @author ***** 
     * @since ***** 
     */  
    public static boolean md5Valid(File dataFile) throws ServiceException {  
        String controlFileName = dataFile.getAbsolutePath().replace(".DAT", ".CTL");  
         // 获取数据文件的MD5  
        String dataFileMd5 = MD5EncoderUtil.getFileMd5(dataFile);  
        String controlFileMd5 = "";  
         BufferedReader reader = null;  
       try {  
   
             reader = new BufferedReader(new FileReader(new File(controlFileName)));  
            String tempString = reader.readLine();  
            // 获取控制文件中的MD5  
            if(StringUtils.isNotBlank(tempString)){  
                controlFileMd5 = tempString.substring(tempString.lastIndexOf("\t") + 1, tempString.length());  
            }else{  
                throw new ServiceException(CtsErrorCode.ERROR_VALIDATE_MD5, new String[]{"文件"}, "校验文件MD5时发生异常");  
            }  
  
        }  
        catch (Exception e) {  
             logger.error("校验文件MD5时发生异常", e);  
             throw new ServiceException(CtsErrorCode.ERROR_VALIDATE_MD5, e, new String[]{"文件"}, "校验文件MD5时发生异常");  
         }  
         finally {  
             if (reader != null) {  
                try {  
                     reader.close();  
                }  
                catch (IOException e) {  
                    throw new ServiceException(CtsErrorCode.ERROR_VALIDATE_MD5, e, new String[]{"文件"}, "校验文件MD5时发生异常");  
                 }  
            }  
         }  
   
         return dataFileMd5.toUpperCase().equals(controlFileMd5.toUpperCase());  
     }  
   
     /** 
      * <b>方法说明：</b> 将字符串拆解按特定标记解析，封装为String[] 
      *  
      * @param String 
      *            tempString 需要拆分的字符串 
      * @param String 
     *            tempString 拆分符号 
      * @param String 
      *            tempString 拆分符号出现次数 
     * @return List<Object> 
      * @throws ServiceException 
      * @author ***** 
      * @since ***** 
      */  
     public static String[] parseStringToStringArray(String tempString, String sign, int num) {  
         List<Object> strlist = new ArrayList<Object>();  
        String[] strList = new String[num + 1];  
         try {  
             int i;  
             for (i = 0; i < num; i++) {  
                 String s1 = tempString.substring(0, tempString.indexOf(sign)).trim();  
                 tempString = tempString.substring(tempString.indexOf(sign) + 1).trim();  
                 strlist.add(s1);  
                strList[i] = s1;  
                 if (i == num - 1) {  
                     strlist.add(tempString);  
                     strList[i + 1] = tempString;  
                     break;  
                 }  
            }  
         }  
         catch (Exception e) {  
             logger.error("解析还款清算文件失败", e);  
            throw new ServiceException(CtsErrorCode.ERROR_PARSE_FILE, e, new String[]{"还款清算"}, "解析还款清算文件失败");  
         }  
         return strList;  
     }  
   
    /** 
      *  
      * <b>方法说明：</b>格式化时间 
     *  
      * <p> 
     * <b>详细描述：</b> 
      *  
      * @param 
      * @return 
      * @author ***** 
      * @since ***** 
      */  
    public static String foamatTime(String transTime) {  
        return CommUtil.LeftFill(transTime, '0', 6);  
    }  
   
     /** 
      * <b>方法说明：</b> 上传文件 
      *  
      * @param transDate 
     *            交易日期 
     * @param localPath 
      *            本地路径 
     * @param regName 
     *            文件名前缀 
     * @param remotePath 
     *            远程路径 
      * @return 
      * @throws ServiceException 
     * @author ***** 
      * @since ***** 
      */  
    public static Long uploadFiles(String transDate, String localPath, String regName, String remotePath) {  
   
         SftpClient sftpClient = new SftpClient();  
         try  
        {  
             sftpClient.connect();  
             File[] fileList = listDataAndControlFile(localPath, regName + transDate);  
            List<String> fileNameList  = new ArrayList<String>();  
            Long count = 0L;  
             for (File file : fileList) {  
                 count++;  
                 fileNameList.add(file.getAbsolutePath());  
            }  
             if(count>0)  
                 sftpClient.uploadBatch(remotePath, fileNameList);  
             return count;  
         }finally  
         {  
             sftpClient.disConnect();  
         }  
     }  
  
     public static void uploadFile(String loaclpath, String fileName, String remotePath) {  
         SftpClient sftpClient = new SftpClient();  
         try  
         {  
             File file = new File(loaclpath, fileName);  
             sftpClient.upload(remotePath, file.getAbsolutePath());  
         }finally  
         {  
             sftpClient.disConnect();  
         }  
    }  
       
     public static void uploadFile(String loaclpath, List<String> fileName, String remotePath) {  
         SftpClient sftpClient = new SftpClient();  
         try  
         {  
             List<String> fileNameList  = new ArrayList<String>();  
            Long count = 0L;  
            for (String item : fileName) {  
                count++;  
                 fileNameList.add(loaclpath+"//"+item);  
             }  
             if(count>0)  
                sftpClient.uploadBatch(remotePath, fileNameList);  
        }finally  
         {  
             sftpClient.disConnect();  
         }  
    }  
   
     /*** 
      * 按照指定格式分隔字符串 
     * @param tempString 
      * @param splitChar 
      * @return 
      * @return String[] 
      */  
     public static String[] splitString(String tempString,String splitChar) {  
         String[] splits = (tempString.replace("||", "| | ") + (" ")).split(splitChar);  
         for(int i=0;i<splits.length;i++){  
             if(null == splits[i]){  
                 splits[i]="";  
             }  
         }  
         return splits;  
     }  
   
     public static String packProperty(String value) {  
         if (value == null) {  
            return "";  
         }  
         return value.trim();  
     }  
   
    public static String packProperty(Integer value) {  
         if (value == null) {  
             return "";  
         }  
         return value.toString();  
     }  
   
     public static String packProperty(BigDecimal value) {  
         if (value == null) {  
             return "";  
         }  
        return value.toString();  
    }  
  
     /** 
      *  
      * <b> 方法说明：</b><BR> 
      * 获取本地目录下过滤后的数据文件列表 
      *  
      * @param localPath 要查询的数据文件的路径 
     * @param namePrefix 要过滤出来的数据文件前缀 
      * @return File[] 文件列表 
      * @author ***** 
      */  
     public static File[] listDataFile(String localPath, final String namePrefix) {  
         FilenameFilter nameFilter = new FilenameFilter() {  
  
             @Override  
             public boolean accept(File dir, String fileName) {  
                 return fileName.startsWith(namePrefix) && (fileName.endsWith(".DAT"));  
             }  
         };  
   
         File[] fileList = new File(localPath).listFiles(nameFilter);  
         return fileList == null ? new File[0] : fileList;  
     }  
   
    /** 
      *  
      * <b>方法说明：</b> 获取本地目录下过滤后的数据文件和控制文件列表 
      *  
      * @param 
      * @return 
      * @author ***** 
      * @since ***** 
      */  
     public static File[] listDataAndControlFile(String localPath, String reg) {  
         final String regName = reg;  
         logger.debug("localPath:"+localPath+",reg:"+reg);  
        FilenameFilter nameFilter = new FilenameFilter() {  
  
             @Override  
             public boolean accept(File dir, String fileName) {  
                 return fileName.indexOf(regName) >= 0 && (fileName.endsWith(".DAT") || fileName.endsWith(".CTL"));  
            }  
         };  
  
        File[] fileList = new File(localPath).listFiles(nameFilter);  
        return fileList;  
    }  
    public static File[] deleteFilesFromDir(String localPath, String reg) {  
         File[] oldFileList = FileOperateAssistUtil.listDataAndControlFile(localPath,reg);  
         for (File file : oldFileList) {  
            file.delete();  
         }  
        return oldFileList;  
     }  
    public static String getBatchNoByFile(File file) {  
        String fileName = file.getName();  
        String str = fileName.substring(fileName.lastIndexOf("_") + 1, fileName.lastIndexOf("."));  
         return str.length() <= 3 ? str : "001";  
     }  
   
} 

