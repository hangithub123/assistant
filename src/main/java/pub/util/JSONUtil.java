package pub.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JSONUtil {
	private static final Logger logger = LoggerFactory.getLogger(JSONUtil.class);
	/**
	 * convertJsonToList 
	 * 解析JSON转换为列表对象
	 * @author qiwei
	 * @param content JSON对象内容
	 * @return 列表对象
	 */
	public static List<Map<String,Object>> convertJsonToList(String content)
	{
		List<Map<String,Object>> listResult=new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		
		try {
			
			JSONArray jsonArray=new JSONArray(content);
			for(int i=0;i<jsonArray.length();i++)
			{
				JSONObject item=jsonArray.getJSONObject(i);
				
				
				Iterator<String> it=item.keys();
		
				map =new HashMap<String, Object>();
				
				//循环放每一个JSON对象中MAP的KEY值 
	        	while(it.hasNext())
	        	{
	        		String key=it.next();
	        		map.put(key, item.get(key));
	        	}
	        	listResult.add(map);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return listResult;
		
	}

	public static String convertListToJson(List<Map<String,Object>> list)
	{
		String result="";

		JSONArray jarry=new JSONArray(list);
		
		if (jarry!=null)
		{
			result=jarry.toString();
		}
		

		return result;
		
	}
	
	
	public static String convertMapToJson(Map map)
	{
	

		JSONObject json=new JSONObject();
		
		try {
			if(map != null){
		
				Iterator<String> it=map.keySet().iterator();
				
				//循环放每一个JSON对象中MAP的KEY值 
		    	while(it.hasNext())
		    	{
		    			String key=it.next();
		    			json.put(key, map.get(key));
		    	}
			}

		} catch (JSONException e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return json.toString();

	}
	
	public static Map<String, Object> convertJsonToMap(String json)
	{
	

		Map<String,Object> map =new HashMap<String, Object>();

		try {
		
			JSONObject jobject=new JSONObject(json);
	
	
			Iterator<String> it=jobject.keys();
			
			//循环放每一个JSON对象中MAP的KEY值 
	    	while(it.hasNext())
	    	{
	    		String key=it.next();
    			Object value = jobject.get(key);
    			if(value instanceof JSONArray) {
    				map.put(key, convertJsonToList((String)value));
    			} else {
    				map.put(key, value);
				}
	    	}
		} catch (JSONException e) {
			e.printStackTrace();
			logger.error(e.getMessage(),e);
		}
		return map;
	}
}
