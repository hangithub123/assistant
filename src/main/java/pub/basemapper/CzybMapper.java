package pub.basemapper;

import pub.po.Czyb;
import pub.po.vo.PageParams;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface CzybMapper {

	Integer insert(Czyb record);

	void insertSelective(Czyb record);

	int deleteByPrimaryKey(String id);

	Czyb selectByPrimaryKey(String id);

	List<Czyb> queryByExists(Czyb record);

	int updateByPrimaryKeySelective(Czyb record);

	int updateByPrimaryKey(Czyb record);

	List<Czyb> queryByExistsWithPage(@Param("record")Czyb record, @Param("pageParams")PageParams pageParams);

}