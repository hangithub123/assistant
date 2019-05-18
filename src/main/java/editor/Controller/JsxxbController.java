package editor.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import editor.Constants.Constans_js;
import editor.Constants.Constants_login;
import editor.Constants.Constants_xt;
import editor.baseFile.BaseController;
import pub.basemapper.CzybMapper;
import pub.basemapper.JsxxbMapper;
import pub.basemapper.JxzgrzMapper;
import pub.basemapper.TsxxbMapper;
import pub.basemapper.WJBMapper;
import pub.po.Czyb;
import pub.po.Jsxxb;
import pub.po.Jxzgrz;
import pub.po.Tsxxb;
import pub.po.WJB;
import pub.po.vo.PageParams;
import pub.util.ConvertUtil;
import pub.util.MapperUtils;
import pub.util.TsxxUtil;
import pub.util.Uuid;

/**
 * 
 * 【业务编码】：教师基本信息表中的操作
 *
 * @工程： UIEditor
 * @类名： JsxxbController
 * @模块： 
 * 
 *
 */
@Controller
@RequestMapping("/jsxxb")
public class JsxxbController extends BaseController{
	
	JsxxbMapper jsxxbMapper = (JsxxbMapper) MapperUtils.getMapper(JsxxbMapper.class);
	CzybMapper czybMapper = (CzybMapper) MapperUtils.getMapper(CzybMapper.class);
	JxzgrzMapper jxzgrzMapper = (JxzgrzMapper) MapperUtils.getMapper(JxzgrzMapper.class);
	WJBMapper wjbMapper = (WJBMapper) MapperUtils.getMapper(WJBMapper.class);
	TsxxbMapper tsxxbMapper = (TsxxbMapper) MapperUtils.getMapper(TsxxbMapper.class);
	
	/**
	 * 
	 * 功能逻辑：跳转资料完善与修改界面
	 *
	 * @方法名称：modifierJsxx
	 * 
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping("/modifierJsxx")
	public String modifierJsxx(HttpServletRequest request,ModelMap model) {
		//获取操作员信息
		Czyb czyb = new Czyb();
		czyb = czybMapper.selectByPrimaryKey(ConvertUtil.createString(request.getSession().getAttribute("czyId")));
		//教师信息是否完善
		Jsxxb jsxxb = new Jsxxb();
		jsxxb.setDlh(czyb.getDlh());
		jsxxb.setEmail(czyb.getEmail());
		jsxxb.setSybz(czyb.getSybz());
		List<Jsxxb> list = jsxxbMapper.queryByExists(jsxxb);
		if (!list.isEmpty() && list.size()>0) {
			jsxxb = list.get(0);
			WJB wjb = new WJB();
			wjb.setBz(jsxxb.getFj());
			List<WJB> wjbList = wjbMapper.queryByExists(wjb);
			if (!wjbList.isEmpty() && wjbList.size()>0) {
				wjb = wjbList.get(0);
				model.put("wjb", wjb);
				model.put("wdid", wjb.getBz());
			}
		}else {
			jsxxb.setZt(Constans_js.JSXXB_ZT_WSH);
			model.put("wdid", Uuid.getUuid32());
		}
		Map sexMap = new HashMap<>();
		int a = 1;
		int b = 2;
		sexMap.put(a, "男");
		sexMap.put(b, "女");
		model.put("sexMap", sexMap);
		model.put("JSXXB_ZT_SHZ", Constans_js.JSXXB_ZT_SHZ);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		model.put("jsxxb", jsxxb);
		return "teacher/personalCenter/modifierJsxx";
	}
	
	/**
	 * 
	 * 功能逻辑：保存教师基本信息
	 *
	 * @方法名称：saveJsxx
	 * 
	 * @param jsxxb
	 * @return Integer
	 *
	 */
	@RequestMapping("/saveJsxx")
	@ResponseBody
	public Integer saveJsxx(Jsxxb jsxxb) {
		if (!jsxxb.isEmptyId()) {
			jsxxb.setZt(Constans_js.JSXXB_ZT_WSH);
			return jsxxbMapper.updateByPrimaryKey(jsxxb);
		}else {
			jsxxb.setId(Uuid.getUuid32());
			jsxxb.setZt(Constans_js.JSXXB_ZT_WSH);
			return jsxxbMapper.insert(jsxxb);
		}
	}
	
	//跳转到展示教师资料页面
	@RequestMapping("/showjszl")
	public String showjszl(HttpServletRequest request,ModelMap model,String dlh) {
		Jsxxb jsxxb = new Jsxxb();
		jsxxb.setDlh(dlh);
		List<Jsxxb> list = jsxxbMapper.queryByExists(jsxxb);
		if (!list.isEmpty() && list.size()>0) {
			jsxxb=list.get(0);
			WJB wjb2 = new WJB();
			wjb2.setBz(jsxxb.getFj());
			List<WJB> wjbList = wjbMapper.queryByExists(wjb2);
			if (!wjbList.isEmpty() && wjbList.size()>0) {
				wjb2 = wjbList.get(0);
				model.put("wjb2", wjb2);
			}
			if (jsxxb!=null && !jsxxb.isEmptyId()) {
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
		}
		model.put("SEX_Map", Constants_xt.SEX_Map);
		model.put("JSXXB_ZT_WSH", Constans_js.JSXXB_ZT_WSH);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		model.put("jsxxb", jsxxb);
		return "teacher/personalCenter/showjszl";
	}
	
	@RequestMapping("/showjszl2")
	public String showjszl2(HttpServletRequest request,ModelMap model,String dlh) {
		Jsxxb jsxxb = new Jsxxb();
		jsxxb.setDlh(dlh);
		List<Jsxxb> list = jsxxbMapper.queryByExists(jsxxb);
		if (!list.isEmpty() && list.size()>0) {
			jsxxb=list.get(0);
			WJB wjb2 = new WJB();
			wjb2.setBz(jsxxb.getFj());
			List<WJB> wjbList = wjbMapper.queryByExists(wjb2);
			if (!wjbList.isEmpty() && wjbList.size()>0) {
				wjb2 = wjbList.get(0);
				model.put("wjb2", wjb2);
			}
			if (jsxxb!=null && !jsxxb.isEmptyId()) {
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
		}
		model.put("SEX_Map", Constants_xt.SEX_Map);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		model.put("JSXXB_ZT_SHZ", Constans_js.JSXXB_ZT_SHZ);
		model.put("jsxxb", jsxxb);
		return "admin/userManage/showjszl2";
	}
	
	/**
	 * 教师信息列表
	 */
	@RequestMapping("/jsList")
	public String jsList(HttpServletRequest request,ModelMap model,PageParams pageParams,Jsxxb jsxxb) {
		//初始化页码
		pageParams = this.getSplitPageInfo(pageParams);
		model.put("pageParams", pageParams);
		if (jsxxb == null) {
			jsxxb = new Jsxxb();
		}
		if (jsxxb != null) {
			if (jsxxb.getDlh()=="") {
				jsxxb.setDlh(null);
			}
			if (jsxxb.getName()=="") {
				jsxxb.setName(null);
			}
			if (jsxxb.getXybh()=="") {
				jsxxb.setXybh(null);
			}
			if (jsxxb.getZybh()=="") {
				jsxxb.setZybh(null);
			}
		}
		List<Jsxxb> list = jsxxbMapper.queryByExistsWithPage(jsxxb, pageParams);
		model.put("list", list);
		Map sybzMap = new HashMap<>();
		sybzMap.put("可用",Constants_login.CZYB_SYBZ_KY);
		sybzMap.put("不可用", Constants_login.CZYB_SYBZ_BKY);
		sybzMap.put("初始化",Constants_login.CZYB_SYBZ_CSH );
		model.put("sybzMap", sybzMap);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		return "admin/userManage/jsList";
	}
	
	//提交教师信息资料
	@RequestMapping("/tjJsxx")
	public String tjJsxx(HttpServletRequest request,ModelMap model,String jsId) {
		//修改jsxxb中状态字段
		Jsxxb jsxxb = jsxxbMapper.selectByPrimaryKey(jsId);
		if (jsxxb != null && !jsxxb.isEmptyId()) {
			jsxxb.setZt(Constans_js.JSXXB_ZT_SHZ);
			int j = jsxxbMapper.updateByPrimaryKey(jsxxb);
			//给管理员发审核信息
			Tsxxb tsxxb = new Tsxxb();
			tsxxb.setId(Uuid.getUuid32());
			tsxxb.setBtsr(Constants_xt.GLYBS);
			tsxxb.setTsnr("[信息审核]"+jsxxb.getName()+"老师信息资料审核");
			tsxxb.setShdz("/UIEditor/jsxxb/showjszl2?dlh="+jsxxb.getDlh());
			tsxxb.setZt(Constants_xt.TSXXB_ZT_WD);
			int i = tsxxbMapper.insert(tsxxb);
			if (i==1 && j==1) {
				model.put("Message", "提交成功");
				model.put("colseId", "PopDialogtest");
				return "inforFtl";
			}
		}
		return "";
	}
	
	//管理员审核通过
	@RequestMapping("/shtgOperation")
	public String shtgOperation(HttpServletRequest request,ModelMap model,String id,String dlh) {
		//修改使用标志为可用
		Jsxxb jsxxb = jsxxbMapper.selectByPrimaryKey(id);
		jsxxb.setSybz(Constants_login.CZYB_SYBZ_KY);
		jsxxb.setZt(Constans_js.JSXXB_ZT_SHTG);
		int i = jsxxbMapper.updateByPrimaryKey(jsxxb);
		Czyb czyb = new Czyb();
		czyb.setDlh(dlh);
		List<Czyb> list = czybMapper.queryByExists(czyb);
		czyb = list.get(0);
		czyb.setSybz(Constants_login.CZYB_SYBZ_KY);
		int j = czybMapper.updateByPrimaryKey(czyb);
		if (i==1 && j==1) {
			//给教师发送审核通过的提示信息
			String btsr = jsxxb.getDlh();
			String tsnr = "[通知]"+jsxxb.getName()+"老师您的信息资料审核通过！";
			TsxxUtil.saveTsxx(btsr, tsnr, null);
			model.put("Message", "提交成功");
			model.put("colseId", "PopDialogtest");
			return "inforFtl";
		}else {
			model.put("Message", "提交失败");
			model.put("colseId", "PopDialogtest");
			return "inforFtl";
		}
	}
	
	//管理员审核不通过
	@RequestMapping("/shbtgOperation")
	public String shbtgOperation(HttpServletRequest request,ModelMap model,String id,String dlh) {
		//修改审核状态为不通过
		Jsxxb jsxxb = jsxxbMapper.selectByPrimaryKey(id);
		jsxxb.setZt(Constans_js.JSXXB_ZT_SHBTG);
		int i = jsxxbMapper.updateByPrimaryKey(jsxxb);
		if (i==1) {
			//给教师发送审核不通过的提示信息
			String btsr = jsxxb.getDlh();
			String tsnr = "[通知]"+jsxxb.getName()+"老师您的信息资料审核不通过，请完善资料再重新提交！";
			TsxxUtil.saveTsxx(btsr, tsnr, null);
			model.put("Message", "提交成功");
			model.put("colseId", "PopDialogtest");
			return "inforFtl";
		}else {
			model.put("Message", "提交失败");
			model.put("colseId", "PopDialogtest");
			return "inforFtl";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
