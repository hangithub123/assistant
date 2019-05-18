package pub.basemapper;

import pub.po.Xsxxb;
import pub.po.vo.PageParams;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface XsxxbMapper {

	Integer insert(Xsxxb record);

	void insertSelective(Xsxxb record);

	int deleteByPrimaryKey(String id);

	Xsxxb selectByPrimaryKey(String id);

	List<Xsxxb> queryByExists(Xsxxb record);

	int updateByPrimaryKeySelective(Xsxxb record);

	int updateByPrimaryKey(Xsxxb record);

	List<Xsxxb> queryByExistsWithPage(@Param("record")Xsxxb record, @Param("pageParams")PageParams pageParams);

}