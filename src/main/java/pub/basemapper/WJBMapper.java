package pub.basemapper;

import pub.po.WJB;
import java.util.List;

public interface WJBMapper {

	void insert(WJB record);

	void insertSelective(WJB record);

	int deleteByPrimaryKey(String id);

	WJB selectByPrimaryKey(String id);

	List<WJB> queryByExists(WJB record);

	int updateByPrimaryKeySelective(WJB record);

	int updateByPrimaryKey(WJB record);

}