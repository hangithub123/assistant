package editor.Controller;



import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import editor.Constants.Constants_login;
import editor.Constants.Constants_xt;
import editor.baseFile.BaseController;
import pub.basemapper.CzybMapper;
import pub.basemapper.JsxxbMapper;
import pub.po.Czyb;
import pub.po.Jsxxb;
import pub.po.vo.PageParams;
import pub.util.ConvertUtil;
import pub.util.EmailUtil;
import pub.util.MapperUtils;
import pub.util.Uuid;

@Controller
@RequestMapping("/login")
public class CzybController extends BaseController{
	
	CzybMapper czybMapper = (CzybMapper) MapperUtils.getMapper(CzybMapper.class);
	
	/**
	 * 
	 * 功能逻辑：跳转登录页面
	 *
	 * @方法名称：homePage
	 * 
	 * @param request
	 * @param model
	 * @return String
	 *
	 */
	@RequestMapping("/homePage")
	public String homePage(HttpServletRequest request,Model model) {
		return "login";
	}
	
	/**
	 * 
	 * 功能逻辑：登录校验
	 *
	 * @方法名称：userLogin
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param dlh
	 * @param mm
	 * @return String
	 *
	 */
	@RequestMapping("/userLogin")
	public String userLogin(HttpServletRequest request,HttpServletResponse response,ModelMap model,
			@RequestParam(name="dlh") String dlh,@RequestParam(name="mm") String mm) {
		Czyb czy = new Czyb();
		czy.setDlh(dlh);
		czy.setMm(mm);
		List<Czyb> list = czybMapper.queryByExists(czy);
		if (!list.isEmpty() && list.size() > 0) {
			czy = list.get(0);
		}
		//判断用户是否存在
		if (czy != null && !czy.isEmptyId()) {
			if (czy.getSybz()==Constants_login.CZYB_SYBZ_BKY) {
				response.setContentType("text/html;charset=utf-8"); 
				try {
					response.getWriter().write( "<script>alert('账号不可用，请联系管理员！');</script>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				return "login";
			}
			request.getSession().setAttribute("czyId", czy.getId());
			request.getSession().setAttribute("czyDlh", czy.getDlh());
			request.getSession().setAttribute("czyEmail", czy.getEmail());
			request.getSession().setAttribute("czyRole", czy.getRole());
			request.getSession().setAttribute("czySybz", czy.getSybz());
			//判断用户角色
			if (Constants_login.CZYB_ROLE_GLY == czy.getRole()) {
				return "admin/homePage";
			}else if(Constants_login.CZYB_ROLE_JS == czy.getRole()){
				return "teacher/homePage2";
			}else {
				return "student/homePage";
			}
		}else {
			response.setContentType("text/html;charset=utf-8"); 
			try {
				response.getWriter().write( "<script>alert('账号或密码错误，请重新登录！');</script>");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return "login";
		}
		
	}
	
	/**
	 * 
	 * 功能逻辑：退出登录
	 *
	 * @方法名称：exitLogin
	 * 
	 * @param request
	 * @return String
	 *
	 */
	@RequestMapping("/exitLogin")
	public String exitLogin(HttpServletRequest request) {
		request.removeAttribute("czyId");
		request.removeAttribute("czyDlh");
		request.removeAttribute("czyEmail");
		request.removeAttribute("czyRole");
		request.removeAttribute("czySybz");
		return "login";
	}
	
	/**
	 * 
	 * 功能逻辑：跳转注册页面
	 *
	 * @方法名称：registerPage
	 * 
	 * @param request
	 * @return String
	 *
	 */
	@RequestMapping("/registerPage")
	public String registerPage(HttpServletRequest request) {
		return "register";
	}
	
	/**
	 * 
	 * 功能逻辑：判断验证码是否正确
	 *
	 * @方法名称：hqYzm
	 * 
	 * @param request
	 * @param json
	 * @return JSONObject
	 *
	 */
	@ResponseBody
	@RequestMapping("/hqYzm")
	public JSONObject hqYzm(HttpServletRequest request,@RequestBody JSONObject json) {
		String email = json.getString("email");
		String sult = null;
		try {
			sult = EmailUtil.sendEmail(request.getSession(), email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject result= new JSONObject();
		result.put("flag", sult);
		return result;
	}
	
	/**
	 * 
	 * 功能逻辑：判断账户是否存在
	 *
	 * @方法名称：existZH
	 * 
	 * @param request
	 * @param json
	 * @return JSONObject
	 *
	 */
	@ResponseBody
	@RequestMapping("/existZH")
	public JSONObject existZH(HttpServletRequest request,@RequestBody JSONObject json) {
		String dlh = json.getString("dlh");
		JSONObject result= new JSONObject();
		Czyb czy = new Czyb();
		czy.setDlh(dlh);
		List<Czyb> list = czybMapper.queryByExists(czy);
		if (!list.isEmpty() && list.size() > 0) {
			czy = czybMapper.queryByExists(czy).get(0);
			if (czy != null && !czy.isEmptyId()) {
				result.put("flag", "ok");
				return result;
			}
		}
		result.put("flag", "no");
		return result;
	}
	
	/**
	 * 
	 * 功能逻辑：保存注册信息
	 *
	 * @方法名称：register
	 * 
	 * @param request
	 * @param json
	 * @return JSONObject
	 *
	 */
	@ResponseBody
	@RequestMapping("/register")
	public JSONObject register(HttpServletRequest request,@RequestBody JSONObject json) {
		String email = json.getString("email");
		String yzm = json.getString("yzm");
		String hqyzm = ConvertUtil.createString(request.getSession().getAttribute(email));
		JSONObject result= new JSONObject();
		if (hqyzm.equals(yzm)) {
			String dlh = json.getString("dlh");
			String mm = json.getString("mm");
			String role = json.getString("role");
			Czyb czy = new Czyb();
			czy.setId(Uuid.getUuid32());
			czy.setEmail(email);
			czy.setDlh(dlh);
			czy.setMm(mm);
			czy.setRole(ConvertUtil.createInteger(role));
			czy.setSybz(Constants_login.CZYB_SYBZ_CSH);
			czybMapper.insert(czy);
			result.put("flag", "ok");
			return result;
		}else {
			result.put("flag", "no");
			return result;
		}
	}
	
	/**
	 * 操作员信息列表
	 */
	@RequestMapping("/czyList")
	public String czyList(HttpServletRequest request,ModelMap model,PageParams pageParams,Czyb czyb) {
		//初始化页码
		pageParams = this.getSplitPageInfo(pageParams);
		model.put("pageParams", pageParams);
		if (czyb == null) {
			czyb = new Czyb();
		}
		if (czyb != null) {
			if (czyb.getDlh()=="") {
				czyb.setDlh(null);
			}
		}
		List<Czyb> list = czybMapper.queryByExistsWithPage(czyb, pageParams);
		model.put("list", list);
		Map sybzMap = new HashMap<>();
		sybzMap.put(Constants_login.CZYB_SYBZ_CSH, "初始化");
		sybzMap.put(Constants_login.CZYB_SYBZ_KY, "可用");
		sybzMap.put(Constants_login.CZYB_SYBZ_BKY, "不可用");
		model.put("sybzMap", new JSONObject(sybzMap));
		Map roleMap = new HashMap<>();
		roleMap.put(Constants_login.CZYB_ROLE_XS, "学生");
		roleMap.put(Constants_login.CZYB_ROLE_JS, "教师");
		roleMap.put(Constants_login.CZYB_ROLE_GLY, "管理员");
		model.put("mrz", Constants_login.MRZ);
		model.put("roleMap", new JSONObject(roleMap));
		model.put("CZYB_ROLE_Map", Constants_login.CZYB_ROLE_Map);
		model.put("CZYB_SYBZ_Map", Constants_login.CZYB_SYBZ_Map);
		return "admin/userManage/czyList";
	}
	
	/**
	 * 
	 * 功能逻辑：跳转新增或修改操作员信息页面
	 *
	 * @方法名称：editCzyb
	 * 
	 * @param request
	 * @return String
	 *
	 */
	@RequestMapping("/editCzyb")
	public String editCzyb(HttpServletRequest request,ModelMap model,String id) {
		Czyb czyb = new Czyb();
		if (StringUtils.isNotBlank(id)) {
			czyb = czybMapper.selectByPrimaryKey(id);
			model.put("sfcz", Constants_xt.CZ);
		}else {
			model.put("sfcz", Constants_xt.BCZ);
			czyb.setRole(Constants_login.CZYB_ROLE_GLY);
		}
		Map sybzMap = new HashMap<>();
		sybzMap.put(Constants_login.CZYB_SYBZ_CSH, "初始化");
		sybzMap.put(Constants_login.CZYB_SYBZ_KY, "可用");
		sybzMap.put(Constants_login.CZYB_SYBZ_BKY, "不可用");
		model.put("sybzMap", new JSONObject(sybzMap));
		Map roleMap = new HashMap<>();
		roleMap.put(Constants_login.CZYB_ROLE_XS, "学生");
		roleMap.put(Constants_login.CZYB_ROLE_JS, "教师");
		roleMap.put(Constants_login.CZYB_ROLE_GLY, "管理员");
		model.put("mrz", Constants_login.MRZ);
		model.put("roleMap", new JSONObject(roleMap));
		model.put("czyb", czyb);
		return "admin/userManage/modifierCzy";
	}
	
	/**
	 * 保存操作员信息
	 */
	@ResponseBody
	@RequestMapping("/saveCzy")
	public Integer saveCzy(HttpServletRequest request,Czyb czyb) {
		if (!czyb.isEmptyId()) {
			return czybMapper.updateByPrimaryKey(czyb);
		}else {
			czyb.setId(Uuid.getUuid32());
			return czybMapper.insert(czyb);
		}
	}
	
	/**
	 * 
	 * 功能逻辑：删除操作员信息
	 *
	 * @方法名称：deleteCyb
	 * 
	 * @param request
	 * @return String
	 *
	 */
	@RequestMapping("/deleteCyb")
	public String deleteCyb(HttpServletRequest request,String id,ModelMap model,PageParams pageParams) {
		Czyb czyb1 = czybMapper.selectByPrimaryKey(id);
		czyb1.setSybz(Constants_login.CZYB_SYBZ_BKY);
		czybMapper.updateByPrimaryKey(czyb1);
		return czyList(request, model, pageParams, new Czyb());
	}
	
}
