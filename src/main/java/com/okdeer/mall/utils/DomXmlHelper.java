package com.okdeer.mall.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

import javax.swing.text.Document;

import org.apache.catalina.util.XMLWriter;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * dom解析xml文件
 * @author Administrator
 *
 */
public class DomXmlHelper {
    private static final Logger logger = Logger.getLogger(DomXmlHelper.class);
    
    /**
     * 将xml文档内容转为字符串
     * @param doc xml文档对象
     * @param charsetName 转换字符编码
     * @return 字符串
     */
    public static String doc2Str(Document doc, String charsetName) throws XmlParseException {
        Assert.notNull(doc, "document object is null");
        String result = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            OutputFormat format = new OutputFormat("  ", true, charsetName);
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(doc);
            result = out.toString(charsetName);
        } catch (Exception e) {
            logger.error("document convert String error", e);
            throw new XmlParseException("document convert String error", e);
        }
        return result;
    }
    
    /**
     * 将符合XML格式的String 转化为XML Document
     * @param text 字符串格式xml内容
     * @return Document
     */
    public static Document str2Doc(String text) throws XmlParseException {
        if(!StringUtils.hasText(text)) return null;
        Document result = null;
        try {
            result = DocumentHelper.parseText(text);
        } catch (Exception e) {
            logger.error("xml string convert document error", e);
            throw new XmlParseException("xml string convert document error", e);
        }
        return result;
    }
    
    /**
     *  将Document对象保存为一个xml文件到本地
     * @param doc document
     * @param localeFile 本地文件
     * @param charsetName 保存字符编码
     */
    public static void saveFile(Document doc, File localeFile, String charsetName) throws XmlParseException {
        try {
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding(charsetName);
            XMLWriter writer = new XMLWriter(new FileWriter(localeFile), format);
            writer.write(doc);
            writer.close();
        } catch (Exception e) {
            logger.error("document convert locale file error", e);
            throw new XmlParseException("document convert locale file error", e);
        }
    }
    
    /**
     * 将xml格式的字符串保存为本地文件
     * @param text xml string
     * @param localeFile 本地文件
     * @param charsetName 保存字符编码
     */
    public static void saveStr(String text, File localeFile, String charsetName) throws XmlParseException {
        try {
            Document doc =  DocumentHelper.parseText(text);
            saveFile(doc, localeFile, charsetName);
        } catch (Exception e) {
            logger.error("xml string convert locale file error", e);
            throw new XmlParseException("xml string convert locale file error", e);
        }
    }
    
    /**
     * 载入一个xml文档
     * @param filename 文件路径
     * @return document
     */
    public static Document load(String filename) throws XmlParseException {
        Document result = null;
        try {
            SAXReader saxReader = new SAXReader();
            result = saxReader.read(new File(filename));
        } catch (Exception e) {
            logger.error("load doc by filename:" + filename + " fail", e);
            throw new XmlParseException("load doc by filename:" + filename + " fail", e);
        }
        return result;
    }
}

