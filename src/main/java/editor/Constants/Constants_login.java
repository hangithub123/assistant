package editor.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constants_login {
	//默认值
	public final static int MRZ = 9;
	
	//角色类型  1-学生、2-教师、0-管理员
	public final static int CZYB_ROLE_XS = 1;
	public final static int CZYB_ROLE_JS = 2;
	public final static int CZYB_ROLE_GLY = 0;
	public static Map<Integer, String> CZYB_ROLE_Map = new LinkedHashMap<Integer, String>();
	static {
		CZYB_ROLE_Map.put(CZYB_ROLE_XS, "学生");
		CZYB_ROLE_Map.put(CZYB_ROLE_JS, "教师");
		CZYB_ROLE_Map.put(CZYB_ROLE_GLY, "管理员");
	}
	public static final String getCZYB_ROLE_Label(Integer role) {
		return CZYB_ROLE_Map.get(role);
	}
	
	//使用标志  1-可用、2-不可用、0-初始化
	public final static int CZYB_SYBZ_KY = 1;
	public final static int CZYB_SYBZ_BKY = 2;
	public final static int CZYB_SYBZ_CSH = 0;
	public static Map<Integer, String> CZYB_SYBZ_Map = new LinkedHashMap<Integer, String>();
	static {
		CZYB_SYBZ_Map.put(CZYB_SYBZ_KY, "可用");
		CZYB_SYBZ_Map.put(CZYB_SYBZ_BKY, "不可用");
		CZYB_SYBZ_Map.put(CZYB_SYBZ_CSH, "初始化");
	}
	public static final String getCZYB_SYBZ_Label(Integer sybz) {
		return CZYB_SYBZ_Map.get(sybz);
	}
	
}
