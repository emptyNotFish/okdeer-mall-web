package com.okdeer.mall.common.jxls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.jxls.area.Area;
import org.jxls.area.XlsArea;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xls.XlsCommentAreaBuilder;
import org.jxls.command.Command;
import org.jxls.command.EachCommand;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;


/**
 * 导出Excel工具类beta
 * @author Administrator
 *
 */
public class ReportExcelUtil {

    /**
     * 设置响应前缀
     */
    private final static String REPORT_HEADER = "Content-Disposition";

    /**
     * 设置响应后缀
     */
    private final static String REPORT_HEADER_TWO = "attachment;filename=";

    /**
     * 导出为xlsx格式
     */
    public final static String REPORT_XLSX = ".xlsx";

    /**
     * 导出为xls格式
     */
    public final static String REPORT_XLS = ".xls";
    /**
     * 导出每页的大小
     */
    public static final int EXPORT_PAGE_SIZE = 1000;

    /**
     * 简单的List导出Excel文件
     *
     * @param response
     *            HttpServletResponse 输出响应
     * @param is
     *            InputStream
     *            读取模版文件的文件输入流，一般为“类.class.getResourceAsStream("模版名称.xlsx")”
     * @param reportFileName
     *            String 导出的文件名称
     * @param reportSuffix
     *            String 导出的文件后缀
     * @param reportList
     *            List 需要导出的List集合
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public static void reportExcelToList(HttpServletResponse response,
                                   InputStream is, String reportFileName, String reportSuffix,
                                   List reportList) throws IOException {
        response.setHeader(
                REPORT_HEADER,
                REPORT_HEADER_TWO
                        + URLEncoder.encode(reportFileName + reportSuffix,
                        "UTF-8"));
        Context context = new Context();
        context.putVar("reportList", reportList);
        reportToContext(response, is, reportFileName, reportSuffix, context);
    }

    
    /**
     * 简单的Map导出Excel文件
     *
     * @param response
     *            HttpServletResponse 输出响应
     * @param is
     *            InputStream
     *            读取模版文件的文件输入流，一般为“类.class.getResourceAsStream("模版名称.xlsx")”
     * @param reportFileName
     *            String 导出的文件名称
     * @param reportSuffix
     *            String 导出的文件后缀
     * @param reportMap
     *            HashMap 需要导出的Map集合
     * @throws IOException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void reportExcelToMap(HttpServletResponse response,
                                        InputStream is, String reportFileName, String reportSuffix,
                                        Map reportMap) throws IOException {
        Context context = new Context(reportMap);
        reportToContext(response, is, reportFileName, reportSuffix, context);
    }

    @SuppressWarnings("rawtypes")
    public static void reportExcelPage(HttpServletResponse response,
                                       InputStream is, String reportFileName,
                                       List reportList) throws IOException{
    	reportExcelPage(response,is,reportFileName,REPORT_XLSX,reportList,EXPORT_PAGE_SIZE);
    }
    /**
     * 分页导出Excel文件
     *
     * @param response       HttpServletResponse 输出响应
     * @param is             InputStream 读取模版文件的文件输入流，一般为“类.class.getResourceAsStream("模版名称.xlsx")”
     * @param reportFileName String 导出文件的名称
     * @param reportSuffix   String 导出文件的后缀
     * @param reportList     List 需要导出的List集合
     * @param pageSize       Integer 每页显示大小
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public static void reportExcelPage(HttpServletResponse response,
                                       InputStream is, String reportFileName, String reportSuffix,
                                       List reportList, Integer pageSize) throws IOException {
        response.setHeader(
                REPORT_HEADER,
                REPORT_HEADER_TWO
                        + URLEncoder.encode(reportFileName + reportSuffix,
                        "UTF-8"));
        OutputStream os = response.getOutputStream();
        Transformer transformer = TransformerFactory.createTransformer(is, os);
        AreaBuilder areaBuilder = new XlsCommentAreaBuilder(transformer);
        List<Area> xlsAreaList = areaBuilder.build();
        Area xlsArea = xlsAreaList.get(0);
        Area perArea = xlsAreaList.get(1);

        XlsArea sheetArea = new XlsArea(xlsArea.getAreaRef(), transformer);
        EachCommand sheetEachCommand = new EachCommand("sheet", "sheets",
                sheetArea, new SimpleCellRefGenerator());
        xlsArea.addCommand(xlsArea.getAreaRef(), sheetEachCommand);

        XlsArea personArea = new XlsArea(perArea.getAreaRef(), transformer);
        Command personEachCommand = new EachCommand("obj", "sheet.reportList",
                personArea);
        sheetArea.addCommand(perArea.getAreaRef(), personEachCommand);

        Context context = transformer.createInitialContext();
        List<ReportExcelList> sheetList = createReportExcelSheet(reportList,
                pageSize);
        context.putVar("sheets", sheetList);
        xlsArea.applyAt(xlsArea.getStartCellRef(), context);
        xlsArea.processFormulas();
        transformer.write();
        is.close();
        os.close();
    }

    /**
     * 将list集合转成分页list对象以便分页使用
     *
     * @param reportList List 需要导出的数据
     * @param pageSize   Integer 每个sheet的数据条数
     * @return List<ReportExcelList>
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private static List<ReportExcelList> createReportExcelSheet(
            List reportList, Integer pageSize) {
        Integer no = reportList.size() % pageSize == 0 ? reportList.size()
                / pageSize : ((reportList.size() / pageSize) + 1);
        List<ReportExcelList> lists = new ArrayList<ReportExcelList>();
        for (int i = 0; i < no; i++) {
            ReportExcelList pl = new ReportExcelList();
            if ((i + 1) == no) {
                pl.setReportList(reportList.subList(i * pageSize,
                        reportList.size()));
                lists.add(pl);
            } else {
                pl.setReportList(reportList.subList(i * pageSize, (i + 1)
                        * pageSize));
                lists.add(pl);
            }

        }
        return lists;
    }

    /**
     * 对于数据量不大的简单导出功能，提供公共的导出方法，只需要提供Context内容
     *
     * @param response
     *            HttpServletResponse 输出响应
     * @param is
     *            InputStream
     *            读取模版文件的文件输入流，一般为“类.class.getResourceAsStream("模版名称.xlsx")”
     * @param reportFileName
     *            String 导出的文件名称
     * @param reportSuffix
     *            String 导出的文件后缀
     * @param reportContext
     *            Context 输出到Excel上面的内容数据
     * @throws IOException
     */
    private static void reportToContext(HttpServletResponse response,
                                        InputStream is, String reportFileName, String reportSuffix,
                                        Context reportContext) throws IOException {
        // 设置响应
        response.setHeader(
                REPORT_HEADER,
                REPORT_HEADER_TWO
                        + URLEncoder.encode(reportFileName + reportSuffix,
                        "UTF-8"));
        // 设置输出流，这里使用response对象的输出流，提供web下载
        OutputStream os = response.getOutputStream();
        JxlsHelper.getInstance().processTemplate(is, os, reportContext);
        is.close();
        os.close();
    }

    /**
     * 将需要导出的数据转换成分页展示对象，为适应分页导出功能
     *
     * @param <T>
     */
    @SuppressWarnings("serial")
    public static class ReportExcelList<T> implements Serializable {
        /**
         * 需要转换的分页数据集合
         */
        private List<T> reportList;

        public List<T> getReportList() {
            return reportList;
        }

        public void setReportList(List<T> reportList) {
            this.reportList = reportList;
        }
    }
}
