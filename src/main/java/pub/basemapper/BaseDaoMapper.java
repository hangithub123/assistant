package pub.basemapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface BaseDaoMapper {
	/**
	 * 
	 * 功能逻辑：根据type获取uicode及其展示ui
	 *
	 * @方法名称：queryuicodeListMap
	 * @作者:韩伟其
	 * @创建日期： 2019年1月27日
	 * 
	 * @param map
	 * @return List<Map>
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	public List<Map> queryuicodeListMap(@Param("map")Map map);
	/**
	 * 
	 * 功能逻辑：根据uicode的id获取所需静态文件及js函数
	 *
	 * @方法名称：querybaseCodeListMap
	 * @作者:韩伟其
	 * @创建日期： 2019年1月27日
	 * 
	 * @param uiid
	 * @return List<Map>
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	public List<Map> querybaseCodeListMap(@Param("uiid")String uiid);
}
