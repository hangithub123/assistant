package pub.util;

import editor.Constants.Constants_xt;
import pub.basemapper.TsxxbMapper;
import pub.po.Tsxxb;

public class TsxxUtil {
	
	//保存提示消息
	public static void saveTsxx(String btsr,String tsnr,String shdz) {
		TsxxbMapper tsxxbMapper = (TsxxbMapper) MapperUtils.getMapper(TsxxbMapper.class);
		Tsxxb tsxxb = new Tsxxb();
		tsxxb.setId(Uuid.getUuid32());
		tsxxb.setBtsr(btsr);
		tsxxb.setTsnr(tsnr);
		if (shdz!=null) {
			tsxxb.setShdz(shdz);
		}
		tsxxb.setZt(Constants_xt.TSXXB_ZT_WD);
		tsxxbMapper.insertSelective(tsxxb);
	}
}
