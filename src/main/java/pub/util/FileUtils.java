package pub.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import editor.Constants.Constants_xt;
import pub.basemapper.BaseDaoMapper;

public class FileUtils {
	static String newline=System.getProperty("line.separator");
	public static void creatFile(String filePath, String fileName) throws IOException {
        File folder = new File(filePath);
        //文件夹路径不存在
        if (!folder.exists() && !folder.isDirectory()) {
            System.out.println("文件夹路径不存在，创建路径:" + filePath);
            folder.mkdirs();
        } else {
            System.out.println("文件夹路径存在:" + filePath);
        }

        // 如果文件不存在就创建
        File file = new File(filePath + fileName);
        if (!file.exists()) {
            System.out.println("文件不存在，创建文件:" + filePath + fileName);
            
                file.createNewFile();
        } else {
        	file.delete();
        	file.createNewFile();
            System.out.println("文件已存在，删除重建文件为:" + filePath + fileName);
        }
    }
	//输出文件内容
	public static void outputFile(File file, List content)  {
		FileOutputStream outFile = null;
		try {
			outFile=new FileOutputStream(file);
		for (Object obj : content) {
			byte data[] = ((String) obj).getBytes();
				outFile.write(data);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(outFile!=null)
				try {
					outFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	//输出文件内容
	public static void outputFile(File file, StringBuffer content)  {
		FileWriter fw = null;
		try {
			 fw = new FileWriter(file);
			fw.write(ConvertUtils.convert(content));
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fw!=null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	/**
	 * 
	 * 功能逻辑：组织数据
	 *
	 * @方法名称：organizeData
	 * @作者:韩伟其
	 * @创建日期： 2019年1月27日
	 * 
	 * @param html
	 * @param head
	 * @param js
	 * @param body void
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	public static StringBuffer organizeData(Map existmap,Map newmap) {
		String html = ConvertUtils.convert(existmap.get("html"));
		String head = ConvertUtils.convert(existmap.get("head"));
		String js = ConvertUtils.convert(existmap.get("js"));
		String body = ConvertUtils.convert(existmap.get("body"));
		//html数据分割
		int index_html = html.lastIndexOf("<");
		String firstPart_html = html.substring(0, index_html);
		String endPart_html = html.substring(index_html, html.length());
		//head数据分割
		int index_head = head.lastIndexOf("<");
		String firstPart_head = head.substring(0, index_head);
		String endPart_head = head.substring(index_head, head.length());
		//js数据分割
		int index_js = js.lastIndexOf("<");
		String firstPart_js = js.substring(0, index_js);
		String endPart_js = js.substring(index_js, js.length());
		//body数据分割，要在倒数第二个<符号处分割。切分form标签
		int index_body = body.lastIndexOf("<");
		String tempstr= body.substring(0, index_body);
		index_body=tempstr.lastIndexOf("<");
		String firstPart_body = body.substring(0, index_body);
		String endPart_body = body.substring(index_body, body.length());
		//需要插入的数据
		String inserthead = ConvertUtils.convert(newmap.get("inserthead"));
		String insertjs = ConvertUtils.convert(newmap.get("insertjs"));
		String insertbody = ConvertUtils.convert(newmap.get("insertbody"));
		//拼接数据
		StringBuffer newhead = insertMiddle(firstPart_head,endPart_head,inserthead);
		StringBuffer newjs = insertMiddle(firstPart_js,endPart_js,insertjs);
		StringBuffer newbody = insertMiddle(firstPart_body,endPart_body,insertbody);
		StringBuffer hea_js_body =appendStr(appendStr(newhead,newjs),newbody);
		StringBuffer newdate = insertMiddle(firstPart_html,endPart_html,hea_js_body);
		return newdate;
	}
	/**
	 * 
	 * 功能逻辑：插入标签内容  <firstpart>insertpart</endpart>
	 *
	 * @方法名称：insertMiddle
	 * @作者:韩伟其
	 * @创建日期： 2019年1月28日
	 * 
	 * @param firstpart
	 * @param endpart
	 * @param insertpart
	 * @return StringBuffer
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	public static  StringBuffer insertMiddle(String firstpart,String endpart,Object insertpart) {
		StringBuffer newdate=new StringBuffer();
		newdate.append(firstpart);
		//newdate.append(newline);注意数据中标签已默认换行了。
		newdate.append(insertpart);
		newdate.append(newline);
		newdate.append(endpart);
		return newdate;
	}
	//拼接body数据
	public static StringBuffer organizeBodyData(List<JSONObject> arrdata) {
		StringBuffer newdate=new StringBuffer();
		for (int i=0;i<arrdata.size();i++) {
			Map json=arrdata.get(i);
			appendStr(newdate,json.get("uicode"));
		}
		return newdate;
	}
	//拼接head和js数据
	public static Map organizeHeadJsData(List<JSONObject> uicodearr,BaseDaoMapper baseDaoMapper) {
		Map existsMap = new HashMap();//用于过滤掉重复的代码
		Map newmap=new HashMap();
		StringBuffer inserthead=new StringBuffer();
		StringBuffer insertjs=new StringBuffer();
		for (int i=0;i<uicodearr.size();i++) {
			Map jsonobj=uicodearr.get(i);
			String uiid = ConvertUtils.convert(jsonobj.get("id"));
			List<Map> querybaseCodeListMap = baseDaoMapper.querybaseCodeListMap(uiid);
			for (Map map : querybaseCodeListMap) {
				if(!existsMap.containsKey(map.get("CODE"))) {
					if(map.get("TYPE").equals(1)) {appendStr(inserthead,map.get("CODE"));}//head类型
					if(map.get("TYPE").equals(2)) {appendStr(insertjs,map.get("CODE"));}//js类型
					existsMap.put(map.get("CODE"),null);
				}
			}
		}
		newmap.put("inserthead", inserthead);
		newmap.put("insertjs", insertjs);
		return newmap;
	}
	//拼接上下字符串
	public static StringBuffer appendStr(StringBuffer oldstr,Object newstr) {
		if(oldstr.length()>0) {//是否需要换行
			oldstr.append(newline);
			oldstr.append(newstr);
		}else {
			oldstr.append(newstr);
		}
		return oldstr;
	}
	/**
	 * 
	 * 功能逻辑：对文件排版，
	 * 取出文件每一行
	 * 以<开始空格结束的字符，视作标签标识。查询该行前，开始标签 - 结束标签的个数*2，就是该行前所需添加的空格个数。
	 * @throws IOException 
	 * @方法名称：pageLayout
	 * @作者:韩伟其
	 * @创建日期： 2019年1月28日
	 *  void
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	public static  void  htmlLayout(String filepath,String layoutfilepath) throws IOException {
		FileReader file = new FileReader(filepath);
		List<String> cacheList = new LinkedList();
		if(file.ready()) {
			 BufferedReader br=new BufferedReader(file);
			 
			 try {//读出每一行代码
		        String line="";
		        while ((line=br.readLine())!=null) {
		        	cacheList.add(line.trim());
		        }
		        System.out.println("读取文件");
			 }catch(IOException e) {
				e.printStackTrace();
			 }finally {
				 file.close();
				 br.close();
			 }
			 //为每行代码，前置合适的空格。
			 System.out.println("文件进行排版。。。");
			 List<String> newcodelist=new LinkedList();
			 int num=0,input=0;
			for(String line:cacheList) {
				int blankNum=tagNum(cacheList,num);
				//如果当前行是个结束标签，父标签数量-1
				if(StringUtils.isNotBlank(line) && line.trim().charAt(0)=='<' && line.trim().charAt(1)=='/') {
					blankNum--;
				}
				//如果当前行是个input,该标签的父标签+1，其他标签的父标签-1,浏览器把<input></input> 改成<input/>或<input> 导致判断出错，所以特殊处理下input。
				if(line.indexOf("input")>0) {input++;blankNum++;}//
				blankNum-=input;
				System.out.println("父标签数量="+(blankNum));
				String blank="";
				for(int i=0;i<blankNum*2;i++) {
					blank+=" ";
				}
				newcodelist.add(blank+line);
				num++;
			}
			
			//输出已排版的文件
			FileWriter layoutfile = new FileWriter(layoutfilepath);
			BufferedWriter bw = new BufferedWriter(layoutfile);
			try {
				boolean flag=true;
				for(String line:newcodelist) {
					if(flag) {
						bw.write(Constants_xt.DOCTYPE1);
						bw.write(newline);
						flag=false;
					}
					if(StringUtils.isNotBlank(line)) {
						bw.write(line);
						bw.write(newline);
					}
				}
				bw.flush();
				System.out.println("文件已重新排版");
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				layoutfile.close();
				bw.close();
			}
		}
	}
	//返回空格数量
	public static int tagNum(List<String> list,int lastindex) {
		if(lastindex==0)return 0;
		//统计lastindex之前的开始结束标签之差
		int startTag=0,endTag=0;
		for(int k=0;k<lastindex;k++) {
			String line = list.get(k);
			int len=line.length();
			char flag='<';
			for(int i=0;i<len;i++) {
				char c = line.charAt(i);
				if(flag==c) {
					//开始标签以<开头第二个字符不为/
					int y=i+1;
					char c2 = line.charAt(y);
					if(!('/'==c2)) {
						startTag++;
					}else {
						//结束标签以<开头第二字符为/
						startTag--;
					}
					
				}
			}
		}
		return (startTag);
	}
	
	public static void main(String[] args) throws IOException {
		htmlLayout("D:/aa.html","D:aa.html");
	}
}
