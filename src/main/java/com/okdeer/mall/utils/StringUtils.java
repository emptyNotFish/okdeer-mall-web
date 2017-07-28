package com.okdeer.mall.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 字符串工具类, 继承org.apache.commons.lang3.StringUtils类
 * 
 * @author ThinkGem
 * @version 2013-05-22
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	/** 26 字母数组*/
	private static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

	/**
	 * @Description: 根据数字获取对应排序字母
	 * @param number 数字（从1开始）
	 * @return 对应数字的的排序字母
	 * @author tangzj02
	 * @date 2016年7月22日
	 */
	public static String getSortLetter(int number) {
		if (number <= 0 || number > letters.length) {
			return "";
		} else {
			return letters[number - 1];
		}
	}

	public static String lowerFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
	}

	public static String upperFirst(String str) {
		if (StringUtils.isBlank(str)) {
			return "";
		} else {
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
	}

	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (isBlank(html)) {
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}

	/**
	 * 缩略字符串（不区分中英文字符）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String abbr(String str, int length) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= length - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 缩略字符串（替换html）
	 * 
	 * @param str
	 *            目标字符串
	 * @param length
	 *            截取长度
	 * @return
	 */
	public static String rabbr(String str, int length) {
		return abbr(replaceHtml(str), length);
	}

	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val) {
		if (val == null) {
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}

	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val) {
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val) {
		return toDouble(val).longValue();
	}

	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val) {
		return toLong(val).intValue();
	}

	/**
	 *
	 * @desc 判断一个字符串是否为空或者null
	 *
	 * @param str
	 *            被判断字符串
	 * @return 判断结构
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		}
		if ("".equals(str.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @desc 检测全部参数是否不为空
	 * @author wulm
	 *
	 * @param cs
	 *            多个字符串
	 * @return true：全部参数不为空；false：有参数为空
	 */
	public static boolean isNotEmptyAll(CharSequence... cs) {
		for (CharSequence charSequence : cs) {
			// 出现一个为空就返回false
			if (!isNotEmpty(charSequence)) {
				return false;
			}
		}
		// 全部都不为空则返回true
		return true;
	}

	/**
	 * 
	 * @desc 检测参数是否全部为空
	 * @author wulm
	 *
	 * @param cs
	 *            多个字符串
	 * @return true：全部参数为空；false：有参数不为空
	 */
	public static boolean isEmptyAll(CharSequence... cs) {
		for (CharSequence charSequence : cs) {
			// 出现一个不为空为空就返回false
			if (isNotEmpty(charSequence)) {
				return false;
			}
		}
		// 全部都不为空则返回true
		return true;
	}

	/**
	 * 正则验证 add by Laven
	 *
	 * @param regex
	 *            正则
	 * @param content
	 *            验证内容
	 * @return 验证结果
	 */
	public static boolean checkRegex(String regex, String content) {
		if (StringUtils.isEmpty(content)) {
			return false;
		}
		return Pattern.compile(regex).matcher(content).matches();
	}

	/**
	 * 手机号验证 add by Laven
	 *
	 * @param mobile
	 *            手机号码
	 * @return 验证结果
	 */
	public static boolean checkMobile(String mobile) {
		return checkRegex("^1[0-9]{10}$", mobile);
	}

	/**
	 * 电话号码验证 add by Laven
	 *
	 * @param telplone
	 *            电话号码 区号（可选）-固号-分机号（可选）
	 * @return 验证结果
	 */
	public static boolean checkTelplone(String telplone) {
		return checkRegex("^(0[0-9]{2,3}\\-)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$", telplone);
	}

	/**
	 * 长度验证，如果字符串长度大于length则验证不通过 add by Laven
	 *
	 * @param str
	 *            验证字符串
	 * @param length
	 *            验证长度
	 * @return 验证结果
	 */
	public static boolean checkLength(String str, int length) {
		int strLength = StringUtils.length(str);
		return length >= strLength;
	}

	/**
	 * 
	 * @desc 用一个字符串和多个字符串比较 区分大小写 add by wulm
	 *
	 * @param str
	 *            用户传入的字符
	 * @param equals
	 *            用来比较的字符
	 * @return 存在一个相等则返回True，都不相等则返回false
	 */
	public static boolean equalsOneInAll(String str, String... equals) {
		boolean eq = false;
		if (str != null) {
			for (String s : equals) {
				if (str.equals(s)) {
					eq = true;
					break;
				}
			}
		}
		return eq;
	}

	/**
	 * 
	 * @desc 用一个字符串和多个字符串比较 忽略大小写 add by wulm
	 *
	 * @param str
	 *            用户传入的字符
	 * @param equals
	 *            用来比较的字符
	 * @return 存在一个相等则返回True，都不相等则返回false
	 */
	public static boolean equalsOneInAllIgnoreCase(String str, String... equals) {
		boolean eq = false;
		if (str != null) {
			for (String s : equals) {
				if (str.equalsIgnoreCase(s)) {
					eq = true;
					break;
				}
			}
		}
		return eq;
	}

	/**
	 * 
	 * 根据计费开始结束日期计算费用属期 
	 *
	 * @param begin 计费开始日期
	 * @param end   计费结束日期
	 * @return 费用属期 
	 */
	public static String getFreeTime(Date begin, Date end) {
		String freeTime = null;
		if (begin == null || end == null) {
			return "";
		}
		Calendar beginTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		beginTime.setTime(begin);
		endTime.setTime(end);

		int bMonth = beginTime.get(Calendar.MONTH);
		int eMonth = endTime.get(Calendar.MONTH);
		int bYear = beginTime.get(Calendar.YEAR);
		int eYear = endTime.get(Calendar.YEAR);
		// 本月
		if (bYear == eYear && bMonth == eMonth) {
			freeTime = bYear + "年" + (bMonth < 9 ? "0" : "") + (bMonth + 1) + "月";
		} else {
			freeTime = bYear + "年" + (bMonth < 9 ? "0" : "") + (bMonth + 1) + "月" + " - " + eYear + "年"
					+ (eMonth < 9 ? "0" : "") + (eMonth + 1) + "月";
		}
		return freeTime;
	}

	/**
	 * @Description: 判断文件后缀是否符合图片后缀名
	 * @param fileName 文件名称（例如 test.jpg）
	 * @return true:合法图片名称  false：非法图片名称
	 * @author tangzj02
	 * @date 2016年9月1日
	 */
	public static boolean isImgSuffix(String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return false;
		}
		// 获取文件后缀
		String suffix = fileName.substring(fileName.indexOf(".") + 1);
		if (StringUtils.isBlank(suffix)) {
			return false;
		}
		// 文件后缀必须符文要求
		if (!"jpg".equals(suffix) && !"png".equals(suffix) && !"bmp".equals(suffix) && !"jpeg".equals(suffix)) {
			return false;
		}
		return true;
	}

	/**
	 * 生成固定长度的编码
	 *
	 * @param code 需要生成的编码
	 * @param len 编码长度
	 * @return 编码字符串
	 */
	public static String formatCode(int code, int len) {
		// 超出范围
		if (code < 0 || code >= Math.pow(10, len)) {
			return null;
		}
        StringBuilder sb = new StringBuilder(Integer.toString(code));
        // 不够的位数补0
        while (sb.length() < len){
            sb.insert(0, "0");
        }
        return sb.toString();
	}
	/**
	 * 转码
	 * @param str 需要生成的编码
	 * @return 转码字符串
	 */
	public static String urlEnodeUTF8(String str) {
		String result = str;
		try {
			result = URLEncoder.encode(str, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
    /***
     * @Description: 字符串转码
     * @param string
     * @return
     * @author xuyq
     * @date 2016年12月15日
     */
	public static String string2Unicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append(Integer.toHexString(c));
		}
		return unicode.toString();
	}
}
