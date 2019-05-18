package pub.basemapper;

import pub.po.UI_showui;
import java.util.List;

public interface UI_showuiMapper {

	void insert(UI_showui record);

	void insertSelective(UI_showui record);

	int deleteByPrimaryKey(String id);

	UI_showui selectByPrimaryKey(String id);

	List<UI_showui> queryByExists(UI_showui record);

	int updateByPrimaryKeySelective(UI_showui record);

	int updateByPrimaryKey(UI_showui record);

}