package editor.Constants;

import java.util.LinkedHashMap;
import java.util.Map;

public class Constans_js {
	
	//状态  1-未审核、2-审核中、3-审核通过、4-审核不通过
	public final static int JSXXB_ZT_WSH = 1;
	public final static int JSXXB_ZT_SHZ = 2;
	public final static int JSXXB_ZT_SHTG = 3;
	public final static int JSXXB_ZT_SHBTG = 4;
	public static Map<Integer, String> JSXXB_ZT_Map = new LinkedHashMap<Integer, String>();
	static {
		JSXXB_ZT_Map.put(JSXXB_ZT_WSH, "未审核");
		JSXXB_ZT_Map.put(JSXXB_ZT_SHZ, "审核中");
		JSXXB_ZT_Map.put(JSXXB_ZT_SHTG, "审核通过");
		JSXXB_ZT_Map.put(JSXXB_ZT_SHBTG, "审核不通过");
	}
	public static final String getJSXXB_ZT_Label(Integer zt) {
		return JSXXB_ZT_Map.get(zt);
	}
		
		
}
