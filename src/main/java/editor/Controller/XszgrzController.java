package editor.Controller;
/**
 * 
 * 【业务编码】：学生资格信息表的操作
 *
 * @工程： UIEditor
 * @类名： XszgrzController
 * @模块： 
 * 
 *
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pub.basemapper.WJBMapper;
import pub.basemapper.XsxxbMapper;
import pub.basemapper.XszgrzMapper;
import pub.po.Jsxxb;
import pub.po.Jxzgrz;
import pub.po.WJB;
import pub.po.Xsxxb;
import pub.po.Xszgrz;
import pub.util.ConvertUtil;
import pub.util.MapperUtils;
import pub.util.Uuid;

/**
 * 
 * 【业务编码】：学生资格认证表操作
 *
 * @工程： UIEditor
 * @类名： XszgrzController
 * @模块： 
 * 
 *
 */
@Controller
@RequestMapping("xszgrz")
public class XszgrzController {
	XszgrzMapper xszgrzMapper = (XszgrzMapper) MapperUtils.getMapper(XszgrzMapper.class);
	XsxxbMapper xsxxbMapper = (XsxxbMapper) MapperUtils.getMapper(XsxxbMapper.class);
	WJBMapper wjbMapper = (WJBMapper) MapperUtils.getMapper(WJBMapper.class);

	//调整学生资格认证页面
	@RequestMapping("/modifierXszgrz")
	public String modifierXszgrz(HttpServletRequest request,ModelMap model) {
		String dlh = ConvertUtil.createString(request.getSession().getAttribute("czyDlh"));
		model.put("czyDlh", dlh);
		Xsxxb xsxxb = new Xsxxb();
		xsxxb.setDlh(dlh);
		List<Xsxxb> list = xsxxbMapper.queryByExists(xsxxb);
		if (!list.isEmpty() && list.size()>0) {
			xsxxb = list.get(0);
			Xszgrz xszgrz = new Xszgrz();
			xszgrz.setXsbh(xsxxb.getId());
			List<Xszgrz> list2 = xszgrzMapper.queryByExists(xszgrz);
			if (!list2.isEmpty() && list2.size()>0) {
				model.put("list2", list2);
				Map fjMap = new HashMap<>();
				for (int i = 0; i < list2.size(); i++) {
					WJB wjb = new WJB();
					wjb.setBz(list2.get(i).getFj());
					List<WJB> list3 = wjbMapper.queryByExists(wjb);
					wjb=list3.get(0);
					fjMap.put(list2.get(i).getFj(), wjb.getWjm());
				}
				model.put("fjMap", fjMap);
			}
		}
		return "student/personalCenter/modifierXszgrz";
	}
	
	/**
	 * 
	 * 功能逻辑：跳转到学生资格认证的增加和修改页面
	 *
	 * @方法名称：editZgrz
	 * 
	 * @param request
	 * @param model
	 * @param id
	 * @return String
	 *
	 */
	@RequestMapping("/editZgrz")
	public String editZgrz(HttpServletRequest request,ModelMap model,String id) {
		Xszgrz xszgrz = new Xszgrz();
		if (StringUtils.isNotBlank(id)) {
			xszgrz.setId(id);
			List<Xszgrz> list = xszgrzMapper.queryByExists(xszgrz);
			if (!list.isEmpty() && list.size()>0) {
				xszgrz = list.get(0);
				WJB wjb = new WJB();
				wjb.setBz(xszgrz.getFj());
				List<WJB> wjbList = wjbMapper.queryByExists(wjb);
				if (!wjbList.isEmpty() && wjbList.size()>0) {
					wjb = wjbList.get(0);
					model.put("wjb", wjb);
					model.put("wdid", wjb.getBz());
				}
			}
		}else {
			model.put("wdid", Uuid.getUuid32());
		}
		model.put("xszgrz", xszgrz);
		return "student/personalCenter/editZgrz";
	}
	
	/**
	 * 
	 * 功能逻辑：保存学生资格认证信息
	 *
	 * @方法名称：saveXszgrz
	 * 
	 * @param jxzgrz
	 * @return Integer
	 *
	 */
	@ResponseBody
	@RequestMapping("/saveXszgrz")
	public Integer saveXszgrz(HttpServletRequest request,Xszgrz xszgrz) {
		if (!xszgrz.isEmptyId()) {
			return xszgrzMapper.updateByPrimaryKey(xszgrz);
		}else {
			xszgrz.setId(Uuid.getUuid32());
			String dlh = ConvertUtil.createString(request.getSession().getAttribute("czyDlh"));
			Xsxxb xsxxb = new Xsxxb();
			xsxxb.setDlh(dlh);
			List<Xsxxb> list = xsxxbMapper.queryByExists(xsxxb);
			if (!list.isEmpty() && list.size()>0) {
				xsxxb = list.get(0);
				xszgrz.setXsbh(xsxxb.getId());
			}
			return xszgrzMapper.insert(xszgrz);
		}
	}
	
	/**
	 * 
	 * 功能逻辑：删除学生资格认证信息
	 *
	 * @方法名称：deleteZgrz
	 * 
	 * @param request
	 * @param model
	 * @param pageParams
	 * @param id
	 * @return String
	 *
	 */
	@RequestMapping("/deleteZgrz")
	public String deleteZgrz(HttpServletRequest request,ModelMap model,String id) {
		//首先删除该证件材料下的文件，再删除证件信息
		Xszgrz xszgrz = xszgrzMapper.selectByPrimaryKey(id);
		WJB wjb = new WJB();
		wjb.setBz(xszgrz.getFj());
		List<WJB> list = wjbMapper.queryByExists(wjb);
		wjbMapper.deleteByPrimaryKey(list.get(0).getId());
		xszgrzMapper.deleteByPrimaryKey(id);
		return modifierXszgrz(request, model);
	}
	
	
	
}
