package pub.basemapper;

import pub.po.Xszgrz;
import java.util.List;

public interface XszgrzMapper {

	Integer insert(Xszgrz record);

	void insertSelective(Xszgrz record);

	int deleteByPrimaryKey(String id);

	Xszgrz selectByPrimaryKey(String id);

	List<Xszgrz> queryByExists(Xszgrz record);

	int updateByPrimaryKeySelective(Xszgrz record);

	int updateByPrimaryKey(Xszgrz record);

}