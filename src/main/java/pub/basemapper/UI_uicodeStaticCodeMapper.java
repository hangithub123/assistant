package pub.basemapper;

import pub.po.UI_uicodeStaticCode;
import java.util.List;

public interface UI_uicodeStaticCodeMapper {

	void insert(UI_uicodeStaticCode record);

	void insertSelective(UI_uicodeStaticCode record);

	int deleteByPrimaryKey(String id);

	UI_uicodeStaticCode selectByPrimaryKey(String id);

	List<UI_uicodeStaticCode> queryByExists(UI_uicodeStaticCode record);

	int updateByPrimaryKeySelective(UI_uicodeStaticCode record);

	int updateByPrimaryKey(UI_uicodeStaticCode record);

}