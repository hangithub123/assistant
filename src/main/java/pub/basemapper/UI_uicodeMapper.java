package pub.basemapper;

import pub.po.UI_uicode;
import java.util.List;

public interface UI_uicodeMapper {

	void insert(UI_uicode record);

	void insertSelective(UI_uicode record);

	int deleteByPrimaryKey(String id);

	UI_uicode selectByPrimaryKey(String id);

	List<UI_uicode> queryByExists(UI_uicode record);

	int updateByPrimaryKeySelective(UI_uicode record);

	int updateByPrimaryKey(UI_uicode record);

}