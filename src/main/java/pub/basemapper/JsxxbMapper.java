package pub.basemapper;

import pub.po.Jsxxb;
import pub.po.vo.PageParams;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface JsxxbMapper {

	Integer insert(Jsxxb record);

	void insertSelective(Jsxxb record);

	int deleteByPrimaryKey(String id);

	Jsxxb selectByPrimaryKey(String id);
	
	Jsxxb selectByPrimaryDlh(String dlh);

	List<Jsxxb> queryByExists(Jsxxb record);
	
	//分页查询
	List<Jsxxb> queryByExistsWithPage(@Param("record")Jsxxb record,@Param("pageParams")PageParams pageParams);

	int updateByPrimaryKeySelective(Jsxxb record);

	int updateByPrimaryKey(Jsxxb record);

}