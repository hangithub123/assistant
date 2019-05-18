package editor.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import editor.Constants.Constants_login;
import editor.Constants.Constants_xt;
import pub.basemapper.TsxxbMapper;
import pub.po.Tsxxb;
import pub.util.ConvertUtil;
import pub.util.MapperUtils;
import pub.util.Uuid;

/**
 * 
 * 【业务编码】：提示信息表中的操作
 *
 * @工程： UIEditor
 * @类名： TsxxbController
 * @模块： 
 * 
 *
 */
@Controller
@RequestMapping("/tsxxb")
public class TsxxbController {
	
	TsxxbMapper tsxxbMapper = (TsxxbMapper) MapperUtils.getMapper(TsxxbMapper.class);
	
	//获取未读的提示信息
	@RequestMapping("/messgePage")
	public String messagePage(HttpServletRequest request,Model model) {
		//获取角色
		int role = ConvertUtil.createInteger(request.getSession().getAttribute("czyRole"));
		String dlh = ConvertUtil.createString(request.getSession().getAttribute("czyDlh"));
		Tsxxb tsxxb = new Tsxxb();
		if (role==Constants_login.CZYB_ROLE_GLY) {
			tsxxb.setBtsr(Constants_xt.GLYBS);
			tsxxb.setZt(Constants_xt.TSXXB_ZT_WD);
			List<Tsxxb> list = tsxxbMapper.queryByExists(tsxxb);
			model.addAttribute("list", list);
			return "admin/message";
		}else {
			tsxxb.setBtsr(dlh);
			tsxxb.setZt(Constants_xt.TSXXB_ZT_WD);
			List<Tsxxb> list = tsxxbMapper.queryByExists(tsxxb);
			model.addAttribute("list", list);
			if (role==Constants_login.CZYB_ROLE_JS) {
				return "teacher/message";
			}else {
				return "student/message";
			}
		}
		
		
	}
	
	//移除的提示状态更改为已读
	@RequestMapping("/ycTsxx")
	public String ycTsxx(HttpServletRequest request,Model model,String tsId) {
		Tsxxb tsxxb = tsxxbMapper.selectByPrimaryKey(tsId);
		tsxxb.setZt(Constants_xt.TSXXB_ZT_YD);
		tsxxbMapper.updateByPrimaryKeySelective(tsxxb);
		return messagePage(request,model);
	}
	
	
}
