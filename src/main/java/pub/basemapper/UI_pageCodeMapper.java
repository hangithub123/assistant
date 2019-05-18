package pub.basemapper;

import pub.po.UI_pageCode;
import java.util.List;

public interface UI_pageCodeMapper {

	void insert(UI_pageCode record);

	void insertSelective(UI_pageCode record);

	int deleteByPrimaryKey(String id);

	UI_pageCode selectByPrimaryKey(String id);

	List<UI_pageCode> queryByExists(UI_pageCode record);

	int updateByPrimaryKeySelective(UI_pageCode record);

	int updateByPrimaryKey(UI_pageCode record);

}