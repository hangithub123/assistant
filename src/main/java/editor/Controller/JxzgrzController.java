package editor.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import editor.baseFile.BaseController;
import pub.basemapper.JsxxbMapper;
import pub.basemapper.JxzgrzMapper;
import pub.basemapper.WJBMapper;
import pub.po.Jsxxb;
import pub.po.Jxzgrz;
import pub.po.WJB;
import pub.po.vo.PageParams;
import pub.util.ConvertUtil;
import pub.util.MapperUtils;
import pub.util.Uuid;

@Controller
@RequestMapping("/jxzgrz")
public class JxzgrzController extends BaseController{
	
	JxzgrzMapper jxzgrzMapper = (JxzgrzMapper) MapperUtils.getMapper(JxzgrzMapper.class);
	JsxxbMapper jsxxbMapper = (JsxxbMapper) MapperUtils.getMapper(JsxxbMapper.class);
	WJBMapper wjbMapper = (WJBMapper) MapperUtils.getMapper(WJBMapper.class);
	/**
	 * 
	 * 功能逻辑：跳转到资格认证页面
	 *
	 * @方法名称：modifierJxzgrz
	 * 
	 * @return String
	 *
	 */
	@RequestMapping("/modifierJxzgrz")
	public String modifierJxzgrz(HttpServletRequest request,ModelMap model) {
		String dlh = ConvertUtil.createString(request.getSession().getAttribute("czyDlh"));
		model.put("czyDlh", dlh);
		Jsxxb jsxxb = new Jsxxb();
		jsxxb.setDlh(dlh);
		List<Jsxxb> list = jsxxbMapper.queryByExists(jsxxb);
		if (!list.isEmpty() && list.size()>0) {
			jsxxb = list.get(0);
			Jxzgrz jxzgrz = new Jxzgrz();
			jxzgrz.setJsbh(jsxxb.getId());
			List<Jxzgrz> list2 = jxzgrzMapper.queryByExists(jxzgrz);
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
		return "teacher/personalCenter/modifierJxzgrz";
	}
	
	/**
	 * 
	 * 功能逻辑：跳转到教学资格认证的增加和修改页面
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
		Jxzgrz jxzgrz = new Jxzgrz();
		if (StringUtils.isNotBlank(id)) {
			jxzgrz.setId(id);
			List<Jxzgrz> list = jxzgrzMapper.queryByExists(jxzgrz);
			if (!list.isEmpty() && list.size()>0) {
				jxzgrz = list.get(0);
				WJB wjb = new WJB();
				wjb.setBz(jxzgrz.getFj());
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
		model.put("jxzgrz", jxzgrz);
		return "teacher/personalCenter/editZgrz";
	}
	
	/**
	 * 
	 * 功能逻辑：保存教学资格认证信息
	 *
	 * @方法名称：saveJxzgrz
	 * 
	 * @param jxzgrz
	 * @return Integer
	 *
	 */
	@ResponseBody
	@RequestMapping("/saveJxzgrz")
	public Integer saveJxzgrz(HttpServletRequest request,Jxzgrz jxzgrz) {
		if (!jxzgrz.isEmptyId()) {
			return jxzgrzMapper.updateByPrimaryKey(jxzgrz);
		}else {
			jxzgrz.setId(Uuid.getUuid32());
			String dlh = ConvertUtil.createString(request.getSession().getAttribute("czyDlh"));
			Jsxxb jsxxb = new Jsxxb();
			jsxxb.setDlh(dlh);
			List<Jsxxb> list = jsxxbMapper.queryByExists(jsxxb);
			if (!list.isEmpty() && list.size()>0) {
				jsxxb = list.get(0);
				jxzgrz.setJsbh(jsxxb.getId());
			}
			return jxzgrzMapper.insert(jxzgrz);
		}
	}
	
	/**
	 * 
	 * 功能逻辑：删除教学资格认证信息
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
		Jxzgrz jxzgrz = jxzgrzMapper.selectByPrimaryKey(id);
		WJB wjb = new WJB();
		wjb.setBz(jxzgrz.getFj());
		List<WJB> list = wjbMapper.queryByExists(wjb);
		wjbMapper.deleteByPrimaryKey(list.get(0).getId());
		jxzgrzMapper.deleteByPrimaryKey(id);
		return modifierJxzgrz(request, model);
	}
	
	

}
