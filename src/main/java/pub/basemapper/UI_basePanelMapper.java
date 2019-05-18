package pub.basemapper;

import pub.po.UI_basePanel;
import java.util.List;

public interface UI_basePanelMapper {

	void insert(UI_basePanel record);

	void insertSelective(UI_basePanel record);

	int deleteByPrimaryKey(String id);

	UI_basePanel selectByPrimaryKey(String id);

	List<UI_basePanel> queryByExists(UI_basePanel record);

	int updateByPrimaryKeySelective(UI_basePanel record);

	int updateByPrimaryKey(UI_basePanel record);

}