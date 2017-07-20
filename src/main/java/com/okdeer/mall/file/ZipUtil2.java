package com.okdeer.mall.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.mockito.internal.util.io.IOUtil;
import org.mockito.invocation.Location;
/**
 * 对压缩文件进行操作
 * @author Administrator
 *
 */
public class ZipUtil2 {
    
    public static final int DEFAULT_BUFFER_SIZE = 32768;
    /**
     * create empty zip archive
     * @param file
     * @return
     */
    public static boolean createEmptyZip(File file) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            byte[] empty = new byte[22];
            empty[0] = 80; // P
            empty[1] = 75; // K
            empty[2] = 5;
            empty[3] = 6;
            os.write(empty);
        } catch (IOException ioe) {
            throw new BuildException("Could not create empty ZIP archive "
                                     + "(" + ioe.getMessage() + ")", ioe,
                                     Location.UNKNOWN_LOCATION);
        } finally {
            FileUtils.close(os);
        }
        return true;
    }
    
    /**
     * load zip file
     * @param dest
     * @param encoding
     * @param compress
     * @param update
     * @return
     */
    public static Zip loadZipFile(File dest, String encoding, boolean compress, boolean update) {
        Project prj = new Project();
        Zip zip = new Zip();
        zip.setEncoding(encoding);
        zip.setCompress(compress);
        zip.setUpdate(update);
        zip.setProject(prj);
        zip.setDestFile(dest);
        return zip;
    }
    
    /**
     * add zip fileset by zip archive prefix
     * @param zip
     * @param append
     * @param prefix
     */
    public static void addZipFileSet(Zip zip, File append, String prefix) {
        ZipFileSet zfs = new ZipFileSet();
        zfs.setProject(zip.getProject());
//        zfs.setEncoding(encoding);
        zfs.setPrefix(prefix);
        if(append.exists() && append.isDirectory()) {
            zfs.setDir(append);
        } else {
            zfs.setFile(append);
        }
        zip.addFileset(zfs);
        zip.execute();
    }
    
    /**
     * add zip fileset
     * @param zip
     * @param append
     */
    public static void addFileSet(Zip zip, File append) {
        FileSet fs = new FileSet();
        fs.setProject(zip.getProject());
        if(append.exists() && append.isDirectory()) {
            fs.setDir(append);
        } else {
            fs.setFile(append);
        }
        zip.addFileset(fs);
        zip.execute();
    }
    
    /**
     * decompress source file to dest directory
     * @param source
     * @param dest
     * @param encoding
     * @throws IOException
     */
    public static void decompression(File source, File dest, String encoding) throws IOException {
        File file;
        InputStream is;
        ZipFile f = null;
        try {
            f = new ZipFile(source, encoding);
            for(Enumeration entries = f.getEntries(); entries.hasMoreElements();){
                ZipEntry e = (ZipEntry)entries.nextElement();
                file = new File(dest, e.getName());
                if(e.isDirectory()) {
                    file.mkdirs();
                } else {
                    File parent = file.getParentFile();
                    if(!parent.exists())
                        parent.mkdirs();
                    is = f.getInputStream(e);
                    putTransfer(is, new FileOutputStream(file), true);
                    finishedTransfer(is);
                }
            }
        } finally {
            finishedHandler(f);
        }
        
    }
    
    
    protected static void finishedTransfer(InputStream is) {
        IOUtil.close(is);
    }
    
    protected static void finishedHandler(ZipFile f) {
        ZipFile.closeQuietly(f);
    }
    
    protected static void putTransfer(InputStream is, OutputStream os, boolean closeOutput) throws IOException {
        try {
            transfer(is, os, Integer.MAX_VALUE*512L);
        } finally {
            if (closeOutput) {
                IOUtil.close(os);
            }
        }
    }
    
    protected static void transfer(InputStream is, OutputStream os,
            long maxSize) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        long remaining = maxSize;
        while (remaining > 0) {
            int n = is.read(buffer, 0,
                    Math.min(buffer.length, Integer.MAX_VALUE));
            if (n == -1) {
                break;
            }
            os.write(buffer, 0, n);
            remaining -= n;
        }
        os.flush();
    }

