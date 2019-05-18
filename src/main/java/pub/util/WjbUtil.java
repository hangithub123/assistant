package pub.util;

import java.util.List;

import pub.basemapper.WJBMapper;
import pub.po.WJB;

public class WjbUtil {
	
	public static void saveFj(String fileName,String fileLj,String bz) {
		WJBMapper wjbMapper = (WJBMapper) MapperUtils.getMapper(WJBMapper.class);
		WJB wjb = new WJB();
		wjb.setId(Uuid.getUuid32());
		wjb.setWjm(fileName);
		wjb.setWjlx(fileLj);
		wjb.setBz(bz);
		wjbMapper.insert(wjb);
	}
	
	public static void deleteFj(String bz) {
		WJBMapper wjbMapper = (WJBMapper) MapperUtils.getMapper(WJBMapper.class);
		WJB wjb = new WJB();
		wjb.setBz(bz);
		List<WJB> wjList = wjbMapper.queryByExists(wjb);
		if (!wjList.isEmpty() && wjList.size()>0) {
			for (int i = 0; i < wjList.size(); i++) {
				wjbMapper.deleteByPrimaryKey(wjList.get(i).getId());
			}
		}
		
	}
}
