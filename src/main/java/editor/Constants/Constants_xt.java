package editor.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants_xt {

	public final static String DOCTYPE1="<!DOCTYPE html PUBLIC \\\"-//W3C//DTD XHTML 1.0 Transitional//EN\\\"\\\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\\\">";
	public final static int UI_STATIC_CODE_TYPE_STATIC=1;//引入的静态文件
	public final static int UI_STATIC_CODE_TYPE_JS=2;//js函数
	public static final Integer SPLITPAGE_LIMIT_TEN = 10;//页码
	
	//邮箱服务器
    public static String QQ_sendService="1466391990@qq.com";
    public static String QQ_Service_SQM="npuvtjzqfamobafh";
    public static String QQ_ServiceHOST="smtp.qq.com";
	
    //性别 1-男、2-女
  	public final static int SEX_MAN = 1;
  	public final static int SEX_WOMAN = 2;
  	public static Map<Integer, String> SEX_Map = new LinkedHashMap<Integer, String>();
  	static {
  		SEX_Map.put(SEX_MAN, "男");
  		SEX_Map.put(SEX_WOMAN, "女");
  	}
  	public static final String getSEX_Map_Label(Integer sex) {
  		return SEX_Map.get(sex);
  	}
  	
  	//管理员标识
  	public final static String GLYBS = "GLYBS";
  	
  	//是否存在用户 1-是、2-否
  	public final static int CZ = 1;
  	public final static int BCZ = 2;
  	
  	//提示信息状态 1-未读、2-已读
  	public final static int TSXXB_ZT_WD = 1;
  	public final static int TSXXB_ZT_YD = 2;
  	public static Map<Integer, String> TSXXB_ZT_Map = new LinkedHashMap<Integer, String>();
  	static {
  		TSXXB_ZT_Map.put(TSXXB_ZT_WD, "未读");
  		TSXXB_ZT_Map.put(TSXXB_ZT_YD, "已读");
  	}
  	public static final String getTSXXB_ZT_Map_Label(Integer tszt) {
  		return TSXXB_ZT_Map.get(tszt);
  	}
  	
  	//课程状态   1-有效、2-无效
  	public final static int KCZT_ZT_YX = 1;
  	public final static int KCZT_ZT_WX = 2;
  	
  	
}
