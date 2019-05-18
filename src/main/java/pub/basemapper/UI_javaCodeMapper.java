package pub.basemapper;

import pub.po.UI_javaCode;
import java.util.List;

public interface UI_javaCodeMapper {

	void insert(UI_javaCode record);

	void insertSelective(UI_javaCode record);

	int deleteByPrimaryKey(String id);

	UI_javaCode selectByPrimaryKey(String id);

	List<UI_javaCode> queryByExists(UI_javaCode record);

	int updateByPrimaryKeySelective(UI_javaCode record);

	int updateByPrimaryKey(UI_javaCode record);

}