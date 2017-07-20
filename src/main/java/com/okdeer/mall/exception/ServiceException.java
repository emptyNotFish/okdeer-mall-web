package com.okdeer.mall.exception;


import java.text.MessageFormat;  
2.   
3. /** 
4.  *  
5.  * <b>类说明：</b>Service层统一抛出的异常 
10.  * @author ***** 
11.  * @since ***** 
12.  */  
13. public class ServiceException extends RuntimeException {      
14.   
15.     private static final long serialVersionUID = 6514891174875747380L;  
16.   
17.   
18.     /** 异常错误码 **/  
19.     private String code;  
20.   
21.     /** 异常描述 **/  
22.     private String msg;   
23.     /** 扩展异常描述（包括msg） **/  
24.     private String extMsg;  
25.   
26.     /** 
27.      * ServiceException构造方法，有format字符组 
28.      * @param errorCode 错误码 
29.      * @param param     format字符组 
30.      * @param extMsg    扩展信息，给出具体的错误值信息等 
31.      */  
32.     public ServiceException(ErrorCode errorCode,String param[],String ... extMsg) {  
33.         super(null==errorCode ? "" : errorCode.getCode());  
34.         init(errorCode, param,extMsg);  
35.     }  
36.   
37.     /** 
38.      * ServiceException构造方法，有format字符组 
39.      * @param errCode 
40.      * @param paramsList 
41.      */  
42.     public ServiceException(ErrorCode errCode, Object... paramsList) {  
43.         Object[] params = null;  
44.         if ((paramsList != null) && (paramsList.length > 0)   
45.                 && ((paramsList[(paramsList.length - 1)] instanceof Throwable)))   
46.         {  
47.             Object[] newParam = new Object[paramsList.length - 1];  
48.             System.arraycopy(paramsList, 0, newParam, 0, newParam.length);  
49.             params = newParam;  
50.             super.initCause((Throwable)paramsList[(paramsList.length - 1)]);  
51.         }  
52.         else {  
53.             params = paramsList;  
54.             super.initCause(null);  
55.         }  
56.   
57.         this.code = null==errCode ? "" : errCode.getCode();  
58.         this.msg = null==errCode ? "" :  MessageFormat.format(errCode.getMsg(),params);    
59.         this.extMsg = this.msg;  
60.     }  
61.   
62.     private void init(ErrorCode errorCode, String param[], String... extMsg) {  
63.         this.code = null==errorCode ? "" : errorCode.getCode();  
64.         this.msg = null==errorCode ? "" : MessageFormat.format(errorCode.getMsg(),param);  
65.         StringBuilder builder = new StringBuilder(100);  
66.         builder.append(this.msg);  
67.         if(null != extMsg){  
68.             for(String ext : extMsg ){  
69.                 builder.append("[").append(ext).append("]");  
70.             }  
71.         }  
72.         this.extMsg = builder.toString();  
73.     }  
74.   
75.     /** 
76.      *  
77.      * @param code  错误码 
78.      * @param msg 描述信息 
79.      */  
80.     public ServiceException(String code, String msg) {  
81.         super(code+":"+msg);  
82.         this.code = code;  
83.         this.msg = msg;  
84.     }  
85.   
86.     /** 
87.      * 带Exception的构造方法，传format字符数组 
88.      * @param errorCode 错误码基类 
89.      * @param e  异常 
90.      * @param extMsg    扩展信息，给出具体的错误值信息等 
91.      */  
92.     public ServiceException(ErrorCode errorCode, Throwable e,String param[] , String ...extMsg ) {  
93.         super(null==errorCode ? "" : errorCode.getCode(), e);  
94.         init(errorCode, param, extMsg);  
95.     }  
96.   
97.     /** 
98.      *  
99.      * @param code 错误码 
100.      * @param msg 描述信息 
101.      * @param e  异常 
102.      */  
103.     /*public ServiceException(String code, String msg,Throwable e) { 
104.         super(code+":"+msg, e); 
105.         this.code = code; 
106.         this.msg = msg;  
107.     }*/  
108.   
109.     /** 
110.      *  
111.      *  
112.      * 方法说明：异常错误码 
113.      *  
114.      * @return 
115.      */  
116.     public String getCode() {  
117.         return code;  
118.     }  
119.   
120.     /** 
121.      *  
122.      *  
123.      * 方法说明：异常描述信息 
124.      *  
125.      * @return 
126.      */  
127.     public String getMsg() {  
128.         return msg;  
129.     }  
130.   
131.     public String getExtMsg() {  
132.         return extMsg;  
133.     }  
134.   
135.     @Override  
136.     public String getMessage() {          
137.         return super.getMessage() + ","+extMsg;  
138.     }     
139.   
140.     public static void main(String[] args) {  
141.   
142.     }  
143.   
144. }


