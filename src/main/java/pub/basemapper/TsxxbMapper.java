package pub.basemapper;

import pub.po.Tsxxb;
import java.util.List;

public interface TsxxbMapper {

	Integer insert(Tsxxb record);

	void insertSelective(Tsxxb record);

	int deleteByPrimaryKey(String id);

	Tsxxb selectByPrimaryKey(String id);

	List<Tsxxb> queryByExists(Tsxxb record);

	int updateByPrimaryKeySelective(Tsxxb record);

	int updateByPrimaryKey(Tsxxb record);

}