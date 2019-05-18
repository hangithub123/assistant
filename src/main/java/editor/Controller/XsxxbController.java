package editor.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import editor.Constants.Constans_js;
import editor.Constants.Constants_login;
import editor.Constants.Constants_xt;
import editor.baseFile.BaseController;
import pub.basemapper.CzybMapper;
import pub.basemapper.TsxxbMapper;
import pub.basemapper.WJBMapper;
import pub.basemapper.XsxxbMapper;
import pub.basemapper.XszgrzMapper;
import pub.po.Czyb;
import pub.po.Jsxxb;
import pub.po.Tsxxb;
import pub.po.WJB;
import pub.po.Xsxxb;
import pub.po.Xszgrz;
import pub.po.vo.PageParams;
import pub.util.ConvertUtil;
import pub.util.MapperUtils;
import pub.util.TsxxUtil;
import pub.util.Uuid;

/**
 * 
 * 【业务编码】：学生信息操作
 *
 * @工程： UIEditor
 * @类名： XsxxbController
 * @模块： 
 * 
 *
 */
@Controller
@RequestMapping("/xsxxb")
public class XsxxbController extends BaseController{

	XsxxbMapper xsxxbMapper = (XsxxbMapper) MapperUtils.getMapper(XsxxbMapper.class);
	CzybMapper czybMapper = (CzybMapper) MapperUtils.getMapper(CzybMapper.class);
	WJBMapper wjbMapper = (WJBMapper) MapperUtils.getMapper(WJBMapper.class);
	XszgrzMapper xszgrzMapper = (XszgrzMapper) MapperUtils.getMapper(XszgrzMapper.class);
	TsxxbMapper tsxxbMapper = (TsxxbMapper) MapperUtils.getMapper(TsxxbMapper.class);
	
	//跳转学生信息修改界面
	@RequestMapping("/modifierXsxx")
	public String modifierXsxx(HttpServletRequest request,ModelMap model) {
		//获取操作员信息
		Czyb czyb = new Czyb();
		czyb = czybMapper.selectByPrimaryKey(ConvertUtil.createString(request.getSession().getAttribute("czyId")));
		//学生信息是否存在
		Xsxxb xsxxb = new Xsxxb();
		xsxxb.setDlh(czyb.getDlh());
		xsxxb.setEmail(czyb.getEmail());
		xsxxb.setSybz(czyb.getSybz());
		List<Xsxxb> list = xsxxbMapper.queryByExists(xsxxb);
		if (!list.isEmpty() && list.size()>0) {
			xsxxb = list.get(0);
			WJB wjb = new WJB();
			wjb.setBz(xsxxb.getFj());
			List<WJB> wjbList = wjbMapper.queryByExists(wjb);
			if (!wjbList.isEmpty() && wjbList.size()>0) {
				wjb = wjbList.get(0);
				model.put("wjb", wjb);
				model.put("wdid", wjb.getBz());
			}
		}else {
			xsxxb.setZt(Constans_js.JSXXB_ZT_WSH);
			model.put("wdid", Uuid.getUuid32());
		}
		Map sexMap = new HashMap<>();
		int a = 1;
		int b = 2;
		sexMap.put(a, "男");
		sexMap.put(b, "女");
		model.put("sexMap", sexMap);
		Map sybzMap = new HashMap<>();
		sybzMap.put(Constants_login.CZYB_SYBZ_CSH, "初始化");
		sybzMap.put(Constants_login.CZYB_SYBZ_KY, "可用");
		sybzMap.put(Constants_login.CZYB_SYBZ_BKY, "不可用");
		model.put("sybzMap", new JSONObject(sybzMap));
		model.put("JSXXB_ZT_SHZ", Constans_js.JSXXB_ZT_SHZ);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		model.put("xsxxb", xsxxb);
		return "student/personalCenter/modifierXsxx";
	}
	
	/**
	 * 
	 * 功能逻辑：保存学生基本信息
	 *
	 * @方法名称：saveJsxx
	 * 
	 * @param jsxxb
	 * @return Integer
	 *
	 */
	@RequestMapping("/saveJsxx")
	@ResponseBody
	public Integer saveJsxx(Xsxxb xsxxb) {
		if (!xsxxb.isEmptyId()) {
			xsxxb.setZt(Constans_js.JSXXB_ZT_WSH);
			return xsxxbMapper.updateByPrimaryKey(xsxxb);
		}else {
			xsxxb.setId(Uuid.getUuid32());
			xsxxb.setZt(Constans_js.JSXXB_ZT_WSH);
			return xsxxbMapper.insert(xsxxb);
		}
	}
	
	//跳转到展示学生资料页面
	@RequestMapping("/showXszl")
	public String showXszl(HttpServletRequest request,ModelMap model,String dlh) {
		//学生基本信息
		Xsxxb xsxxb = new Xsxxb();
		xsxxb.setDlh(dlh);
		List<Xsxxb> list = xsxxbMapper.queryByExists(xsxxb);
		if (!list.isEmpty() && list.size()>0) {
			xsxxb = list.get(0);
			if (xsxxb!=null && !xsxxb.isEmptyId()) {
				WJB wjb2 = new WJB();
				wjb2.setBz(xsxxb.getFj());
				List<WJB> wjbList = wjbMapper.queryByExists(wjb2);
				if (!wjbList.isEmpty() && wjbList.size()>0) {
					wjb2 = wjbList.get(0);
					model.put("wjb2", wjb2);
				}
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
		}
		model.put("SEX_Map", Constants_xt.SEX_Map);
		model.put("JSXXB_ZT_WSH", Constans_js.JSXXB_ZT_WSH);
		model.put("JSXXB_ZT_SHZ", Constans_js.JSXXB_ZT_SHZ);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		model.put("xsxxb", xsxxb);
		return "student/personalCenter/showXszl";
	}
	
	//提交学生信息
	@RequestMapping("/tjxsxx")
	public String tjJsxx(HttpServletRequest request,ModelMap model,String xsId) {
		//修改xsxxb中状态字段
		Xsxxb xsxxb = xsxxbMapper.selectByPrimaryKey(xsId);
		if (xsxxb != null && !xsxxb.isEmptyId()) {
			xsxxb.setZt(Constans_js.JSXXB_ZT_SHZ);
			int j = xsxxbMapper.updateByPrimaryKey(xsxxb);
			//给管理员发审核信息
			Tsxxb tsxxb = new Tsxxb();
			tsxxb.setId(Uuid.getUuid32());
			tsxxb.setBtsr(Constants_xt.GLYBS);
			tsxxb.setTsnr("[信息审核]"+xsxxb.getName()+"学生信息资料审核");
			tsxxb.setShdz("/UIEditor/xsxxb/showXszl?dlh="+xsxxb.getDlh());
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
	
	/**
	 * 学生信息列表
	 */
	@RequestMapping("/xsList")
	public String xsList(HttpServletRequest request,ModelMap model,PageParams pageParams,Xsxxb xsxxb) {
		//初始化页码
		pageParams = this.getSplitPageInfo(pageParams);
		model.put("pageParams", pageParams);
		if (xsxxb == null) {
			xsxxb = new Xsxxb();
		}
		if (xsxxb != null) {
			if (xsxxb.getDlh()=="") {
				xsxxb.setDlh(null);
			}
			if (xsxxb.getName()=="") {
				xsxxb.setName(null);
			}
			if (xsxxb.getXybh()=="") {
				xsxxb.setXybh(null);
			}
			if (xsxxb.getZybh()=="") {
				xsxxb.setZybh(null);
			}
		}
		List<Xsxxb> list = xsxxbMapper.queryByExistsWithPage(xsxxb, pageParams);
		model.put("list", list);
		Map sybzMap = new HashMap<>();
		sybzMap.put("可用",Constants_login.CZYB_SYBZ_KY);
		sybzMap.put("不可用", Constants_login.CZYB_SYBZ_BKY);
		sybzMap.put("初始化",Constants_login.CZYB_SYBZ_CSH );
		model.put("sybzMap", sybzMap);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		return "admin/userManage/xsList";
	}
	
	//管理员审核通过
	@RequestMapping("/shtgOperation")
	public String shtgOperation(HttpServletRequest request,ModelMap model,String xsId,String dlh) {
		//修改使用标志为可用，状态更改为审核通过
		Xsxxb xsxxb = xsxxbMapper.selectByPrimaryKey(xsId);
		xsxxb.setSybz(Constants_login.CZYB_SYBZ_KY);
		xsxxb.setZt(Constans_js.JSXXB_ZT_SHTG);
		int i = xsxxbMapper.updateByPrimaryKey(xsxxb);
		Czyb czyb = new Czyb();
		czyb.setDlh(dlh);
		List<Czyb> list = czybMapper.queryByExists(czyb);
		czyb = list.get(0);
		czyb.setSybz(Constants_login.CZYB_SYBZ_KY);
		int j = czybMapper.updateByPrimaryKey(czyb);
		if (i==1 && j==1) {
			//给学生发送审核通过的提示信息
			String btsr = xsxxb.getDlh();
			String tsnr = "[通知]"+xsxxb.getName()+"学生您的信息资料审核通过！";
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
	public String shbtgOperation(HttpServletRequest request,ModelMap model,String xsId,String dlh) {
		//修改状态为审核不通过
		Xsxxb xsxxb = xsxxbMapper.selectByPrimaryKey(xsId);
		xsxxb.setZt(Constans_js.JSXXB_ZT_SHBTG);
		int i = xsxxbMapper.updateByPrimaryKey(xsxxb);
		if (i==1) {
			//给学生发送审核不通过的提示信息
			String btsr = xsxxb.getDlh();
			String tsnr = "[通知]"+xsxxb.getName()+"学生您的信息资料审核不通过，请完善资料再重新提交！";
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
