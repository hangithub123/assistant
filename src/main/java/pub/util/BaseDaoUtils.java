package pub.util;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
@Component
public class BaseDaoUtils {
/**
 * 
 * 功能逻辑：返回listMap
 *
 * @方法名称：query
 * @作者:韩伟其
 * @创建日期： 2019年1月27日
 * 
 * @param sql
 * @param param
 * @return List<Map>
 * 
 * @修改记录（修改时间、作者、原因）：
 */
	public List<Map> query(String sql,Map param){
		SqlSessionTemplate sqlSessionTemplate = SqlSessionFactoryUtil.getSqlSessionTemplate();
		List<Map> selectList = sqlSessionTemplate.selectList(sql, param);
		return selectList;
	}
	
}
