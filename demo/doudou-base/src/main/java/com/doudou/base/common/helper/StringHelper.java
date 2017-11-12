package com.doudou.base.common.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

/**
 * 说明:字符串处理工具集
 */
public class StringHelper {
  /**
   *将空字符串转换为""
   * @param 原始字符串
   * @return 返回转换后的字符串
   */
  public static final String convertStringNull(String strOrig) {
    String strReturn= "";
    if (strOrig == null || strOrig.equals(null) || strOrig.equals("null")) {
      strReturn= "";
    } else {
      strReturn= strOrig.trim();
    }
    return strReturn;
  }

  public static String doWithNull(Object o) {
		if(o==null) 
			return "";
		else 
			return o.toString().replaceAll("&amp;", "&");		
	}
	
	 public static String[] split(String strSource, String strDiv) {
       int arynum = 0, intIdx = 0, intIdex = 0;
       int div_length = strDiv.length();
       if (strSource.compareTo("") != 0) {
               if (strSource.indexOf(strDiv) != -1) {
                       intIdx = strSource.indexOf(strDiv);
                       for (int intCount = 1; ; intCount++) {
                               if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                                       intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                                       arynum = intCount;
                               }
                               else {
                                       arynum += 2;
                                       break;
                               }
                       }
               }
               else {
                       arynum = 1;
               }
       }
       else {
               arynum = 0;

       }
       intIdx = 0;
       intIdex = 0;
       String[] returnStr = new String[arynum];

       if (strSource.compareTo("") != 0) {
               if (strSource.indexOf(strDiv) != -1) {
                       intIdx = (int) strSource.indexOf(strDiv);
                       returnStr[0] = (String) strSource.substring(0, intIdx);
                       for (int intCount = 1; ; intCount++) {
                               if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                                       intIdex = (int) strSource.indexOf(strDiv, intIdx + div_length);
                                       returnStr[intCount] = (String) strSource.substring(intIdx + div_length,
                                               intIdex);
                                       intIdx = (int) strSource.indexOf(strDiv, intIdx + div_length);
                               }
                               else {
                                       returnStr[intCount] = (String) strSource.substring(intIdx + div_length,
                                               strSource.length());
                                       break;
                               }
                       }
               }
               else {
                       returnStr[0] = (String) strSource.substring(0, strSource.length());
                       return returnStr;
               }
       }
       else {
               return returnStr;
       }
       return returnStr;
}
	 
public static String[] div(String strSource,String strDiv){
	if(strSource.indexOf(strDiv)==-1){
		String []simpleArr={strDiv};
		return simpleArr;
	}
	String sourceArr[]=null;
	String tarArr[]=null;
	if(!"".equals(strDiv)){
		sourceArr=strSource.split(strDiv);
			if(null!=sourceArr){
				tarArr=new String[sourceArr.length];
					for(int i=0;sourceArr.length>0&&i<sourceArr.length;i++){
						tarArr[i]=sourceArr[i].trim();
					}
			}	
			return tarArr;
	}else{
		String []sourArr={strDiv};
		return sourArr;
	}

}	 
		public  static String arrToString(String [] arr) 
		{
			if(arr==null) return "";
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<arr.length;i++)
			{
				if(arr[i]!=null&&!arr[i].equals(""))
					sb.append(arr[i]+",");
			}
			String returnValue=sb.toString();
			if(returnValue.endsWith(",")) returnValue=returnValue.substring(0,returnValue.length()-1);
			return returnValue;
		}
		
		
		
		public  static String arrToString(String [] arr,String splitChar) 
		{
			if(splitChar==null) return arrToString(arr);
			if(arr==null) return "";
			StringBuffer sb=new StringBuffer();
			for(int i=0;i<arr.length;i++)
			{
				if(arr[i]!=null&&!arr[i].equals(""))
					sb.append(arr[i]+splitChar);
			}
			String returnValue=sb.toString();
			if(returnValue.endsWith(splitChar)) returnValue=returnValue.substring(0,returnValue.length()-splitChar.length());
			return returnValue;
		}
		
		
		public  static String objectToString(Object object) 
		{
			if(object==null) return "";
			String returnValue="";
			if(object instanceof String)
			{
				returnValue=(String)object;
			}
			else if(object instanceof String[])
			{
				returnValue=StringHelper.arrToString((String []) object);
			}
			else
			{
				returnValue=object.toString();
			}
			return returnValue;
		}
		
		public  static String objectToString(Object object,String splitChar) 
		{
			if(object==null) return "";
			String returnValue="";
			if(object instanceof String)
			{
				returnValue=(String)object;
			}
			else if(object instanceof String[])
			{
				returnValue=StringHelper.arrToString((String []) object,splitChar);
			}
			return returnValue;
		}
		
//		Replace the specified string "strSub" with string "strRpl".
		//eg:replace(txt,"\n","<BR>");
		public static String replace(String str, String strSub, String strRpl) {
			String[] tmp = split(str, strSub);
			String returnstr = "";
			if (tmp.length != 0) {
				returnstr = tmp[0];
				for (int i = 0; i < tmp.length - 1; i++) {
					returnstr = doWithNull(returnstr) + strRpl + tmp[i + 1];
				}
			}
			return doWithNull(returnstr);
		}
		
		public static String htmlEncode(String txt) {
			if (txt != null) {
				txt = replace(txt, "&", "&amp;");
				txt = replace(txt, "&amp;amp;", "&amp;");
				txt = replace(txt, "&amp;quot;", "&quot;");
				txt = replace(txt, "\"", "&quot;");
				txt = replace(txt, "&amp;lt;", "&lt;");
				txt = replace(txt, "<", "&lt;");
				txt = replace(txt, "&amp;gt;", "&gt;");
				txt = replace(txt, ">", "&gt;");
				txt = replace(txt, "&amp;nbsp;", "&nbsp;");
				//txt = replace(txt," ","&nbsp;");
			}
			return txt;
		}
		
		//Restore string include HTML tags from storage.
		public static String unHtmlEncode(String txt) {
			if (txt != null) {
				txt = replace(txt, "&amp;", "&");
				txt = replace(txt, "&quot;", "\"");
				txt = replace(txt, "&lt;", "<");
				txt = replace(txt, "&gt;", ">");
				txt = replace(txt, "&nbsp;", " ");
			}
			return txt;
		}
		
		//Encode the string by GBK
		public static String toGBK(String str) {
			if (str != null) {
				byte[] tmpbyte = null;
				try {
					tmpbyte = str.getBytes("ISO8859_1");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toGBK :" + e.getMessage());
				}
				try {
					str = new String(tmpbyte, "GBK");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toGBK :" + e.getMessage());
				}
			}
			return str;
		}
		
		//Encode the string by ISO8859_1
		public static String toISO(String str) {
			if (str != null) {
				byte[] tmpbyte = null;
				try {
					tmpbyte = str.getBytes("GBK");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toISO :" + e.getMessage());
				}
				try {
					str = new String(tmpbyte, "ISO8859_1");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toISO :" + e.getMessage());
				}
			}
			return str;
		}
		
		//Encode the string by UTF-8
		public static String toUTF8(String str) {
			if (str != null) {
				byte[] tmpbyte = null;
				try {
					tmpbyte = str.getBytes("ISO-8859-1");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
				}
				try {
					str = new String(tmpbyte, "UTF-8");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
				}
			}
			return str;
		}
		
		
//		Encode the string by UTF-8
		public static String utf8ToGBK(String str) {
			if (str != null) {
				byte[] tmpbyte = null;
				try {
					tmpbyte = str.getBytes("UTF-8");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
				}
				try {
					str = new String(tmpbyte, "ISO-8859-1");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
				}
			}
			return str;
		}
		
//		Encode the string by UTF-8
		public static String utf8ToISO(String str) {
			if (str != null) {
				byte[] tmpbyte = null;
				try {
					tmpbyte = str.getBytes("UTF-8");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
				}
				try {
					str = new String(tmpbyte, "GBK");
				}
				catch (Exception e) {
					//System.out.println("Error: Method: StringUtil.toUTF8 :" + e.getMessage());
				}
			}
			return str;
		}
		
		public static String[] strToArrTo(String src,String delim){
			StringTokenizer s=new StringTokenizer(src,delim);
			List<String> list=new ArrayList<String>();
			while(s.hasMoreElements()){
				list.add((String)s.nextElement());
			}
			Object obj[]=list.toArray();
			String[] arr=new String[obj.length];
			for(int i=0;i<obj.length;i++){
				arr[i]=(String)obj[i];
			}
			return arr;
		}
		
		/**
		 * 过虑特殊字符
		  * @param str
		  * @return String
	   */
		public static  String replace(String str){
			if(str!=null){
				str = str.replaceAll("<","");
				str = str.replaceAll(">","");
				str = str.replaceAll("\r\n","");
				str = str.replaceAll("\n\r","");
				str = str.replaceAll("\n", "");
				str = str.replaceAll("\r", "");
				str = str.replaceAll("'","");
				str = str.replaceAll("\\*","");
				str = str.replaceAll("\\?","");
				str = str.replaceAll("\"","");
				str = str.replaceAll(":","");
				str = str.replaceAll("\\\\","");
				str = str.replaceAll("；","；<br>&nbsp;&nbsp;&nbsp;&nbsp;");
				str = str.replaceAll("。","。<br>&nbsp;&nbsp;&nbsp;&nbsp;");
				str = str.replaceAll(",",",<br>&nbsp;&nbsp;&nbsp;&nbsp;");
				str = str.replaceAll("!","!<br>&nbsp;&nbsp;&nbsp;&nbsp;");
				
				
				
				return str;
			}else{
				return "";
			}
		}
		
		/**
		 * 判断字符串是否是数字
		 * @param str
		 * @return boolean 返回 true是数字
		 */
		public static boolean isNumeric(String str){
			if(str==null || str.length()==0){
				return false;
			}
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str);
			if(!isNum.matches()){
				return false;
			}
			return true;
		}
		/**
		 * 转换成HTML Code
		 * @param s
		 * @return String
		 */
		public static String convertToTHML(String s){
			if(s==null){
				return "";
			}
			return s.replaceAll("\n","<br>");
		}
		
		public static void main(String[] s){
			if(StringHelper.isNumeric("")){
				System.out.println("是数字");
			}else{
				System.out.println("不是数字");
			}
		}
		/**
		 * 截取字符20
		 * @param str
		 * @return
		 */
		public static String replaceLongChart20(String str){
			if(str.length()>18){
				str=str.substring(0, 20);
				str+="...";
			}
			return str;
		}
		/**
		 * 截取字符10
		 * @param str
		 * @return
		 */
		public static String replaceLongChart10(String str){
			if(str.length()>10){
				str=str.substring(0, 10);
				str+="...";
			}
			return str;
		}
		
		/**
		 * Double类型的数字转换成百分比
		 * @param d : 输入参数
		 * @return : 百分比字符串
		 */
		public static String converDoubleToPercent( double d ){
			String percent = "0.00%";
			if( 0 == d || new Double(d).isNaN() ){
				return percent;
			}
			//四舍五入后小数点后两位小数
			d = d+0.00005;
			percent = String.valueOf( d*100 );
			percent = percent.substring( 0, percent.indexOf(".")+3 )+ "%";
			return percent;
		}

		/**
		 * 几个2的n次方和的分解
		 * @param m : 输入参数(几个2的n次方和)
		 * @return s : 整数字符串,格式如:"0,2,6".
		 */
		public static String dichotomy(int m){
			int i = 0;
			int j = 0;
			String s = "";
			if(m > 0){
				do{
					j = m % 2;//取余(余数)
					if(j == 1){
						s += ("".equals(s) ? "" : ",") + i;
					}
					m = m/2;//取模(商数)
					i++;
				}while(m>0);
			}
			return s;
		}
		
		public static String getFileType(String extendName) throws ServletException {
			HashMap<String,String> fileType = new HashMap<String,String>();//文件类型
			fileType.put("html", "text/html");
			fileType.put("xml", "text/xml");
			fileType.put("hta", "application/hta");
			fileType.put("doc", "application/msword");
			fileType.put("wps", "application/vnd.ms-works");
			fileType.put("xls", "application/vnd.ms-excel");
			fileType.put("htm", "text/html");
			fileType.put("gif", "image/gif");
			fileType.put("jpeg", "image/jpeg");
			fileType.put("jpg", "image/jpeg");
			fileType.put("mht", "message/rfc822");
			fileType.put("mhtml", "message/rfc822");
			fileType.put("pdf", "application/pdf");
			fileType.put("ppt", "application/vnd.ms-powerpoint");
			fileType.put("pps", "application/vnd.ms-powerpoint");
			fileType.put("tif", "image/tiff");
			fileType.put("tiff", "image/tiff");
			fileType.put("txt", "text/plain");
			fileType.put("zip", "application/zip");
			fileType.put("rar", "application/rar");
			fileType.put("class", "application/x-java-vm");
			fileType.put("jar", "application/x-java-archive");
			fileType.put("ser", "application/x-java-serialized");
			fileType.put("exe", "application/octet-stream");
			fileType.put("hdml", "text/x-hdml");
			fileType.put("bmp", "image/bmp");
			fileType.put("ico", "image/x-icon");
			fileType.put("wml", "text/vnd.wap.wml");
			fileType.put("wmls", "text/vnd.wap.wmlscript");
			fileType.put("wmlc", "application/vnd.wap.wmlc");
			fileType.put("wmlsc", "application/vnd.wap.wmlscript");
			fileType.put("wbmp", "image/vnd.wap.wbmp");
			fileType.put("csv", "application/msexcel");
			fileType.put("vsd", "application/vnd.visio");
			fileType.put("p7b", "application/x-pkcs7-certificates");
			fileType.put("cer", "application/x-x509-ca-cert");
			fileType.put("der", "application/x-x509-ca-cert");
			
			return (String) fileType.get(extendName);
		}
		
		/**
		 * 根据文件名获得文件的扩展名
		 * @param fileName
		 * @return
		 */
		public static String getFileExtName(String fileName){
			int i = fileName.lastIndexOf(".");
			if(i == -1) return "";
			return fileName.substring(i+1, fileName.length()).toLowerCase();
		}
		/**
		 * 获取文件名字
		 * @param fileName
		 * @return
		 */
		public static String getFileShowName(String fileName){
			int i = fileName.lastIndexOf(".");
			if(i == -1) return "";
			return fileName.substring(0, i).toLowerCase();
		}
		
		/**
		 * 编码方式转换方法
		 * @param str 被转换字符
		 * @param encode1 当前编码方式
		 * @param encode2 目的编码方式
		 * @return
		 */
		public static String changeEncode(String str, String encode1, String encode2) {
			if (str != null) {
				try {
					str = new String(str.getBytes(encode1), encode2);
				} catch (Exception e) {
				}
			}
			return str;
		}
		
		/**
		 * 截取字符的自定义长度,以".."结尾.
		 * @param str String 被处理字符
		 * @param leaveLength int 留下的长度
		 * @return String
		 */
		public static String replaceLongChart(String str, int leaveLength){
			if(str.length() > leaveLength){
				str = str.substring(0, leaveLength);
				str += "..";
			}
			return str;
		}
		
	/**
	 * Double类型的数字四舍五入
	 * @param d : 输入参数(要被处理的数据)
	 * @param n : 输入参数(精度)
	 * @return : 
	 */
	public static double doubleRound(double d, int n){
		String percent = "0.00";
		if(0 == d || new Double(d).isNaN()){
			return 0.00;
		}
		//四舍五入后小数点后n位小数
		String s = "0.";
		for (int i = 0; i < n; i++) {
			s += "0";
		}
		s += "5";
		d += Double.parseDouble(s);
		percent = String.valueOf(d);
		percent = percent.substring(0, percent.indexOf(".") + n + 1);
		return Double.parseDouble(percent);
	}
	
	/**
	 * @param FormatStr
	 * @return
	 */
	public static String dValue(String Value, String FormatStr){
		String returnVar="";
		String StrFormat=FormatStr;
		if(StrFormat.length()==0){
			  StrFormat="#0.00";
		}
		java.text.DecimalFormat sF=new java.text.DecimalFormat(StrFormat);
		returnVar=sF.format(Double.parseDouble(Value));
		return returnVar;
	}
	
//		/**
//		 * @param Value
//		 * @param FormatStr
//		 * @return
//		 */
//		public static String dValue(double Value, String FormatStr){
//			String returnVar="";
//			String StrFormat=FormatStr;
//			if(StrFormat.length()==0){
//				  StrFormat="#0.00";
//			}
//			java.text.DecimalFormat sF=new java.text.DecimalFormat(StrFormat);
//			returnVar=sF.format(Value);
//			return returnVar;
//		}
	
	/**
	 * Double类型的数字四舍五入
	 * @param s : 输入参数(要被处理的数据)
	 * @param n : 输入参数(精度)
	 * @return : 
	 */
	public static double doubleRound(String s, int n){
		String percent = "0.00";
		double d = Double.parseDouble(s);
		if(0 == d || new Double(d).isNaN()){
			return 0.00;
		}
		//四舍五入后小数点后n位小数
		String str = "0.";
		for (int i = 0; i < n; i++) {
			str += "0";
		}
		str += "5";
		d += Double.parseDouble(str);
		percent = String.valueOf(d);
		percent = percent.substring(0, percent.indexOf(".") + n + 1);
		return Double.parseDouble(percent);
	}
	
	
	/**
	 * 取第一个数组中有但第二个中没有的数据.
	 * @param front
	 * @param back
	 * @return
	 */
	public static String[] frontDif(String[] front, String[] back){
		String[] frontDif = null;
		List list = new ArrayList();
		for (int i = 0; i < front.length; i++) {
			boolean flag = true;
			for (int j = 0; j < back.length; j++) {
				if(front[i].equals(back[j])){
					flag = false;
					continue;
				}
			}
			if(flag){
				list.add(front[i]);
			}
		}
		frontDif = new String[list.size()];
		for (int i = 0; i < frontDif.length; i++) {
			frontDif[i] = (String)list.get(i);
		}
		return frontDif;
	}
	
	
	/**
	 * 根据自定义长度来取随机数.
	 * @param size 随机数的长度
	 * @return
	 */
	public static String random(int size){
		String str = "";
		for (int i = 0; i < size; i++) {
			str += Math.round(Math.random()*9);
		}
		return str;
	}


	/**
	 * 文件的下载
	 * @param response HttpServletResponse
	 * @param filePath String 文件的物理路径
	 */
	public static void downFile(HttpServletResponse response, String filePath) {
		String direcotry = filePath;
		//先取文件名(带扩展名)
		String fileName = direcotry.substring(direcotry.lastIndexOf("/")+1);
		if (fileName != null && !"".equals(fileName)){
			
			try {
				response.setHeader("Cache-Control", "no-cache");
				response.setContentType("application/x-download;charset=GBK");
				String headerValue = "attachment;filename=" + java.net.URLEncoder.encode(fileName,"UTF-8");
				response.setHeader("Content-Disposition", headerValue);
				
				FileInputStream ins = new FileInputStream(direcotry);
				OutputStream outs = response.getOutputStream();
				
				byte[] readBytes=new byte[128];
				while(ins.read(readBytes)!=-1){
					outs.write(readBytes);
				}
				outs.close();
				ins.close();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}
			
		} else {
			//System.out.println("文件不存在!");
		}
	}
	
	/**
     * 判断为空串
     * @param arg
     * @return
     */
    public static boolean isEmpty(String arg) {
        return (null == arg || "null".equalsIgnoreCase(arg.trim()) || arg.trim().length() < 1);
    }

    /**
     * 判断为非空串
     * @param arg
     * @return
     */
    public static boolean isNotEmpty(String arg) {
        return !isEmpty(arg);
    }
}