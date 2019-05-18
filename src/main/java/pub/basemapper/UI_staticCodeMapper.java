package pub.basemapper;

import pub.po.UI_staticCode;
import java.util.List;

public interface UI_staticCodeMapper {

	void insert(UI_staticCode record);

	void insertSelective(UI_staticCode record);

	int deleteByPrimaryKey(String id);

	UI_staticCode selectByPrimaryKey(String id);

	List<UI_staticCode> queryByExists(UI_staticCode record);

	int updateByPrimaryKeySelective(UI_staticCode record);

	int updateByPrimaryKey(UI_staticCode record);

}