package com.okdeer.mall.utils;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

public class LogParamUtils
{
    /**
     * 方法类型
     */
    private String methodType = "";
   
    /**
     * 方法描述
     */
    private String methodDesc = "";
   
    /**
     * 功能名称
     */
    private String bundleModuleName = "";
   
    /**
     * 方法名称
     */
    private String methodName = "";
   
    /**
     * 方法输入参数
     */
    private String methodInput = "";
   
    /**
     * 方法输出参数
     */
    private String methodOutput = "";
   
    /**
     * 功能操作结果
     */
    private String operationResult = "";
   
    /**
     * 异常信息
     */
    private String exception = "";
   
    /**
     * 日志缓存
     */
    private StringBuffer buffer = new StringBuffer();
   
    /**
     * 换行符号
     */
    private static final String NEXT_LINE =
        System.getProperty("line.separator");
   
    public LogParamUtils()
    {
        buffer.append(NEXT_LINE).append("{").append(NEXT_LINE);
    }
   
    /**
     * 构造器描述：日志工具类构造函数
     *
     * @param label
     *            日志标签
     */
    public LogParamUtils(String label)
    {
        String title = "title"; //ResourceTool.getMsgLocal(label);
       
        buffer.append(NEXT_LINE).append(title).append(":").append(NEXT_LINE);
        buffer.append("{").append(NEXT_LINE);
    }
   
    /**
     * 增加日志参数
     *
     * @param name
     *            参数名
     * @param value
     *            参数值
     */
    public void addParam(String name, String value)
    {
        buffer.append("    ")
            .append(name)
            .append(" = ")
            .append(value)
            .append(NEXT_LINE);
    }
   
    /**
     * 方法描述：增加日志参数
     *
     * @param name
     *            参数名
     * @param value
     *            参数值
     */
    public void addParam(String name, int value)
    {
        buffer.append("    ")
            .append(name)
            .append(" = ")
            .append(value)
            .append(NEXT_LINE);
    }
   
    /**
     * 方法描述：增加日志参数
     *
     * @param name
     *            参数名
     * @param value
     *            参数值
     */
    public void addParam(String name, Object value)
    {
        if (value == null)
        {
            buffer.append("    ")
                .append(name)
                .append(" = null")
                .append(NEXT_LINE);
        }
        else
        {
            buffer.append("    ")
                .append(name)
                .append(" = ")
                .append(value.toString())
                .append(NEXT_LINE);
        }
    }
   
    /**
     * 方法描述：增加一个参数项
     *
     * @param param
     *            参数名
     * @param value
     *            参数值
     */
    public void addParameter(String name, char[] value)
    {
        buffer.append("    ")
            .append(name)
            .append(" = ")
            .append(value)
            .append(NEXT_LINE);
    }
   
    /**
     * 方法描述：增加一个参数项
     *
     * @param param
     *            参数名
     * @param value
     *            参数值
     */
    public void addParameter(String name, boolean value)
    {
        buffer.append("    ")
            .append(name)
            .append(" = ")
            .append(value)
            .append(NEXT_LINE);
    }
   
    /**
     * 方法描述：增加日志参数
     *
     * @param name
     *            参数名
     * @param value
     *            参数值
     */
    public void addParam(String name, String[] values)
    {
        buffer.append("    ").append(name).append(" = ");
       
        if (values == null)
        {
            buffer.append("null");
        }
        else
        {
            for (int i = 0; i < values.length; i++)
            {
                buffer.append(values[i]).append("#");
            }
        }
       
        buffer.append(NEXT_LINE);
    }
   
    /**
     * 方法描述：增加日志参数
     *
     * @param name
     *            参数名
     * @param value
     *            参数值
     */
    public void addParam(String name, @SuppressWarnings("rawtypes") List list)
    {
        if (list == null)
        {
            buffer.append("    ").append(name).append(" = null");
        }
        else
        {
            buffer.append("    ")
                .append(name)
                .append("(size) = ")
                .append(list.size());
        }
       
        buffer.append(NEXT_LINE);
    }
   
    /**
     * @return 返回日志消息
     */
    public String toString()
    {
        return buffer.append("}").toString();
    }
   
    /**
     * 方法描述：组装打印日志的二维数组
     *
     * @return String 将二维数组拼装字符串
     */
    public String getParams()
    {
        return formAppendInfo(new String[][] {
            {getMethodType() },
            {"methodname", getMethodName() },
            {"methodDesc", getMethodDesc() },
            {"functionmodulename", getFunctionModuleName() },
            {"methodinput", "[" + getMethodInput() + "]" },
            {"methodoutput", getMethodOutput() },
            {"operationresult", getOperationResult() },
            {"exceptioninfo", getException() } });
    }
   
    /**
     * @param exception
     *            the exception to set
     */
    public void setException(String exception)
    {
        this.exception = exception;
    }
   
    public void setMethodType(String methodType)
    {
        this.methodType = methodType;
    }
    public void setMethodDesc(String methodDesc)
    {
        this.methodDesc = methodDesc;
    }
   
    public void setFunctionModuleName(String bundleModuleName)
    {
        this.bundleModuleName = bundleModuleName;
    }
   
    public void setMethodInput(String methodInput)
    {
        this.methodInput = methodInput;
    }
   
    public void setMethodName(String methodName)
    {
        this.methodName = methodName;
    }
   
    public void setMethodOutput(String methodOutput)
    {
        this.methodOutput = methodOutput;
    }
   
    /**
     * @return the exception
     */
    public String getException()
    {
        return exception;
    }
   
    /**
     * @return the methodType
     */
    public String getMethodType()
    {
        if(methodType.equals("begin"))
        {
            methodType = "method begin";
        }
        else if(methodType.equals("end"))
        {
            methodType = "method end";
        }
        else
        {
            methodType = "method in progress";
        }
       
        return methodType;
    }
   
    /**
     * @return the methodDesc
     */
    public String getMethodDesc()
    {
        return methodDesc;
    }
   
    /**
     * @return the functionModuleName
     */
    public String getFunctionModuleName()
    {
        return bundleModuleName;
    }
   
    /**
     * @return the methodInput
     */
    public String getMethodInput()
    {
        return methodInput;
    }
   
    /**
     * @return the methodName
     */
    public String getMethodName()
    {
        return methodName;
    }
   
    /**
     * @return the methodOutput
     */
    public String getMethodOutput()
    {
        return methodOutput;
    }
   
    /**
     * @return the operationResult
     */
    public String getOperationResult()
    {
        return operationResult;
    }
   
    public void setOperationResult(String operationResult)
    {
        this.operationResult = operationResult;
    }
   
    /**
     * 方法描述：组装打印日志的二维数组
     *
     * @param appInfo 参数二维数组
     * @return String 返回拼装好的字符串
     */
    public static String formAppendInfo(Object[][] appInfo)
    {
        if (null == appInfo)
        {
            return "";
        }
       
        String appendInfo = "";
       
        StringBuffer sb = new StringBuffer(2 * appInfo.length);
       
        for (int i = 0; i < appInfo.length; i++)
        {
            int tempSize = appInfo[i].length;
           
            if ((tempSize >= 2) && (appInfo[i][0] != null))
            {
                sb.append(appInfo[i][0].toString());
                sb.append("=");
               
                if (appInfo[i][1] != null)
                {
                    sb.append(appInfo[i][1].toString()).append(",");
                }
                else
                {
                    sb.append("").append(",");
                }
            }
            else
            {
                if ((tempSize != 1) || (appInfo[i][0] == null))
                    continue;
                sb.append(appInfo[i][0].toString()).append(",");
            }
        }
       
        appendInfo = sb.toString();
       
        if (null == appendInfo)
        {
            return "";
        }
       
        if (0 == appendInfo.length())
        {
            return "";
        }
        appendInfo = appendInfo.substring(0, appendInfo.length() - 1);
       
        return appendInfo;
    }
   
    public static void errosMsg(Logger log,String methodName,String functionModuleName,String errorMsg,StackTraceElement[] stackTrace){
        LogParamUtils params = new LogParamUtils();
        params.setMethodName(methodName);
        params.setFunctionModuleName(functionModuleName);
       
        if (null != stackTrace && 0 != stackTrace.length && errorMsg != null){
            params.setException(errorMsg + "\n" +getStackTrace(stackTrace));
        }else{
            params.setException(errorMsg);
        }
       
        params.setMethodInput(params.toString()); 
        log.error(params.getParams());
    }
   
    public static String getStackTrace(StackTraceElement[] stackTrace){
        StringBuffer sb = new StringBuffer();
        for(StackTraceElement msg : stackTrace ){
            sb.append(msg.toString()).append("\n");
        }
        return sb.toString();
    }
   
    public static void infoMsg(Logger log,String methodType,String methodName,String functionModuleName,Map<String, Object> map){
        // 打印方法入口日志
        /*if (log.isInfoEnabled())
        {*/
            LogParamUtils params = new LogParamUtils();
            params.setMethodType(methodType);
            params.setMethodName(methodName);
            params.setFunctionModuleName(functionModuleName);
            if (null != map && 0 != map.size())
            {
                Set<Entry<String, Object>> set = map.entrySet();
                for(Entry<String, Object> entry : set)
                {
                    params.addParam(entry.getKey(), entry.getValue());
                }
            }
           
            if (methodType.equals("begin")){
                params.setMethodInput(params.toString());
            }else if(methodType.equals("end")){
                params.setMethodOutput(params.toString());
            }else{
                params.setOperationResult(params.toString());
            }
           
           
            log.info(params.getParams());
//        }
    }
   
}

