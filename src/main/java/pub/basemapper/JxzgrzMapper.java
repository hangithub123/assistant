package pub.basemapper;

import pub.po.Jxzgrz;
import java.util.List;

public interface JxzgrzMapper {

	Integer insert(Jxzgrz record);

	void insertSelective(Jxzgrz record);

	int deleteByPrimaryKey(String id);

	Jxzgrz selectByPrimaryKey(String id);

	List<Jxzgrz> queryByExists(Jxzgrz record);

	int updateByPrimaryKeySelective(Jxzgrz record);

	int updateByPrimaryKey(Jxzgrz record);

}