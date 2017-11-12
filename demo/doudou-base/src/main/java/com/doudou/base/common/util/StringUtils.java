package com.doudou.base.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.alibaba.fastjson.JSONObject;
import com.doudou.base.common.helper.StringHelper;


public class StringUtils extends StringHelper {

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		return str != null && str.trim().length() > 0 && !str.toLowerCase().equals("null");
	}

	/**
	 * 判断字符串是否不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String[] str) {
		return str != null && str.length > 0;
	}

	/**
	 * 转换掉特殊支付
	 * 
	 * @param arg
	 * @return
	 */
	public static String transChart(String arg) {
		if (arg == null) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < arg.length(); ++i) {
			switch (arg.charAt(i)) {
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '"':
				result.append("&quot;");
				break;
			case '\'':
				result.append("&#39;");
				break;
			case '%':
				result.append("&#37;");
				break;
			case ';':
				result.append("&#59;");
				break;
			case '(':
				result.append("&#40;");
				break;
			case ')':
				result.append("&#41;");
				break;
//			case '&':
//				result.append("&amp;");
//				break;
			case '+':
				result.append("&#43;");
				break;
			default:
				result.append(arg.charAt(i));
				break;
			}
		}
		return result.toString();
	}

	/**
	 * 还原转义后的字符串
	 * 
	 * @param arg
	 * @return
	 */
	public static String restoreTransChart(String arg) {
		if (arg == null) {
			return null;
		}

		if (arg.indexOf("&lt;") > -1) {
			arg = arg.replace("&lt;", "<");
		}
		if (arg.indexOf("&gt;") > -1) {
			arg = arg.replace("&gt;", ">");
		}
		if (arg.indexOf("&quot;") > -1) {
			arg = arg.replace("&quot;", "\"");
		}
		if (arg.indexOf("&#39;") > -1) {
			arg = arg.replace("&#39;", "\'");
		}
		if (arg.indexOf("&#37;") > -1) {
			arg = arg.replace("&#37;", "%");
		}
		if (arg.indexOf("&#59;") > -1) {
			arg = arg.replace("&#59;", ";");
		}
		if (arg.indexOf("&#40;") > -1) {
			arg = arg.replace("&#40;", "(");
		}
		if (arg.indexOf("&#41;") > -1) {
			arg = arg.replace("&#41;", ")");
		}
		if (arg.indexOf("&amp;") > -1) {
			arg = arg.replace("&amp;", "&");
		}
		if (arg.indexOf("&#43;") > -1) {
			arg = arg.replace("&#43;", "+");
		}
		
		return arg;
	}

	/**
	 * 转换对象为字符串，如果为空就转成空字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String convertNull(Object obj) {
		if (obj == null || obj.equals("null")) {
			return "";
		}
		return obj.toString();
	}

	// 过滤特殊字符
	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

 

	/**
	 * 清除html标签
	 * 
	 * @param htmlStr
	 * @return
	 */
	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签
		htmlStr = m_html.replaceAll("&nbsp;"); // 过滤html标签
		htmlStr = m_html.replaceAll("&"); // 过滤html标签
		//htmlStr = m_html.replaceAll("</br>"); // 过滤html标签
		return htmlStr.trim(); // 返回文本字符串
	}
	/**
	 * 移除html标签
	 * removeHtml 方法
	 * @descript：TODO
	 * @param htmlStr
	 * @return
	 * @return String
	 * @author jieguiWU
	 * @date 2016年12月21日-上午10:49:16
	 */
	public static String removeHtml(String htmlStr){
        String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
        String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
        String regEx_html = "<[^>]+>";

        java.util.regex.Pattern p_script = java.util.regex.Pattern.compile(regEx_script, 2);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");

        java.util.regex.Pattern p_style = java.util.regex.Pattern.compile(regEx_style, 2);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");

        java.util.regex.Pattern p_html = java.util.regex.Pattern.compile(regEx_html, 2);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        
        htmlStr = htmlStr.replaceAll("&nbsp;", "");

        return htmlStr.trim();
    }
	
	/**
	 * 清除转义后HTML delHTMLTagAfter 方法
	 * 
	 * @descript：TODO
	 * @param htmlStr
	 * @return
	 * @return String
	 * @date Nov 6, 2015-1:43:53 PM
	 */
	public static String delHTMLTagAfter(String htmlStr) {
		if (htmlStr == null) {
			return "";

		}
		htmlStr = htmlStr.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&quot;", "\"").replaceAll("&#39;",
				"\'");
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		return htmlStr.trim(); // 返回文本字符串
	}

	/**
	 * 如果原有字符串为空就转换成缺省的字符串
	 * 
	 * @param oldString
	 *            原有字符串
	 * @param defaultStr
	 *            缺省字符串
	 * @return
	 */
	public static String isNull(String oldString, String defaultStr) {
		if (isEmpty(oldString)) {
			return defaultStr;
		}
		return transChart(oldString);
	}

	/**
	 * 如果原有字符串为空就转换成缺省的字符串
	 * 
	 * @param oldString
	 *            原有字符串
	 * @param defaultStr
	 *            缺省字符串
	 * @return
	 */
	public static String isNull(Object oldString, String defaultStr) {
		if (oldString == null || isEmpty((String) oldString)) {
			return defaultStr;
		}
		return (String) oldString;
	}

	/**
	 * 将数组按指定的间隔符排列成字符串
	 * 
	 * @param arrayStr
	 *            字符串数据
	 * @param space
	 *            间隔符
	 * @param isSql
	 *            是否试用与sql(每个字符串都用单引号包起来)
	 * @return String
	 */
	/*public static String join(String[] arrayStr, String space, boolean isSql) {
		if (BeanUtils.isNotEmpty(arrayStr) && StringUtils.isNotEmpty(space)) {
			// 取数组第一位
			String returnStr = isSql ? "'".concat(arrayStr[0]).concat("'") : arrayStr[0];
			;
			// 循环拼接数组
			for (int i = 1; i < arrayStr.length; i++) {
				String temp = isSql ? "'".concat(arrayStr[i]).concat("'") : arrayStr[i];
				returnStr += space.concat(temp);
			}
			return returnStr;
		}

		return null;
	}*/

	/**
	 * 将数组按指定的间隔符排列成字符串
	 * 
	 * @param arrayStr
	 *            字符串数据
	 * @param space
	 *            间隔符
	 * @param isSql
	 *            是否试用与sql(每个字符串都用单引号包起来)
	 * @return String
	 */
	/*public static String join(Object[] arrayStr, String space, boolean isSql) throws Exception {
		if (BeanUtils.isNotEmpty(arrayStr) && StringUtils.isNotEmpty(space)) {
			// 取数组第一位
			String returnStr = isSql ? ("'" + arrayStr[0] + "'") : arrayStr[0] + "";
			// 循环拼接数组
			for (int i = 1; i < arrayStr.length; i++) {
				String temp = isSql ? ("'" + arrayStr[i] + "'") : arrayStr[i] + "";
				returnStr += space.concat(temp);
			}
			return returnStr;
		}

		return null;
	}*/
	/**
	 * 
	 * 将字符串从Index位开始替换成*
	 * @descript：TODO
	 * @param oldStr
	 * @param index
	 * @return
	 * @return String
	 * @date 2016年10月25日-下午6:36:38
	 */
	public static String strToAsterisk(String oldStr,int index,boolean isXm) {
	    String returnStr = oldStr ;
	    if(StringUtils.isNotEmpty(oldStr) && oldStr.length() > index) {
	        returnStr = oldStr.substring(0, index) ;
	        if(isXm) {
	            returnStr += "(先生/女士)" ;
	        } else {
	            for(int i=0 ; i< oldStr.length() - index ; i++) {
	                returnStr += "*" ;
	            }
	        }
	        
	    }
	    
	    
	    return returnStr ;
	} 
	/**
	 * 简单判断一个字符串是否为JOSN格式
	 * isJOSNFormat 方法
	 * @descript：TODO
	 * @param string 需要判读是否为JSON格式的字符串
	 * @return boolean 判断结果
	 * @author doudou
	 * @date 2017年1月4日-下午2:38:08
	 */
	public static boolean isJSONFormat(String string){
	    boolean flag = true;
	    if(StringUtils.isEmpty(string)){
	        flag = false;
	        return flag;
	    }
	    JSONObject josnObject = new JSONObject();
	    try{
	        josnObject.parseObject(string);
	    }catch(Exception e){
	        e.printStackTrace();
	        flag = false;
	    }
	    return flag;	    
	}
	
}
