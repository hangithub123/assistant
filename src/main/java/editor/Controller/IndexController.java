package editor.Controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import editor.Constants.Constants_xt;
import pub.basemapper.BaseDaoMapper;
import pub.basemapper.UI_basePanelMapper;
import pub.basemapper.UI_javaCodeMapper;
import pub.basemapper.UI_pageCodeMapper;
import pub.basemapper.UI_showuiMapper;
import pub.basemapper.UI_staticCodeMapper;
import pub.basemapper.UI_uicodeMapper;
import pub.basemapper.UI_uicodeStaticCodeMapper;
import pub.po.UI_basePanel;
import pub.po.UI_javaCode;
import pub.po.UI_pageCode;
import pub.po.UI_showui;
import pub.po.UI_staticCode;
import pub.po.UI_uicode;
import pub.po.UI_uicodeStaticCode;
import pub.util.ConvertUtil;
import pub.util.DateUtils;
import pub.util.FileUtils;
import pub.util.MapperUtils;
import pub.util.Uuid;

@Controller
@RequestMapping("/index")
public class IndexController { 
	BaseDaoMapper baseDaoMapper=(BaseDaoMapper) MapperUtils.getMapper(BaseDaoMapper.class);
	UI_staticCodeMapper UI_staticCodeMapper=(UI_staticCodeMapper) MapperUtils.getMapper(UI_staticCodeMapper.class);
	UI_basePanelMapper UI_basePanelMapper=(UI_basePanelMapper) MapperUtils.getMapper(UI_basePanelMapper.class);
	UI_uicodeMapper UI_uicodeMapper=(UI_uicodeMapper)MapperUtils.getMapper(UI_uicodeMapper.class);
	UI_showuiMapper UI_showuiMapper=(UI_showuiMapper)MapperUtils.getMapper(UI_showuiMapper.class);
	UI_uicodeStaticCodeMapper UI_uicodeStaticCodeMapper=(UI_uicodeStaticCodeMapper)MapperUtils.getMapper(UI_uicodeStaticCodeMapper.class);
	UI_javaCodeMapper UI_javaCodeMapper=(UI_javaCodeMapper)MapperUtils.getMapper(UI_javaCodeMapper.class);
	UI_pageCodeMapper UI_pageCodeMapper=(UI_pageCodeMapper)MapperUtils.getMapper(UI_pageCodeMapper.class);
	/**
	 * 
	 * 功能逻辑：编辑文件
	 *
	 * @方法名称：createFile
	 * @作者:韩伟其
	 * @创建日期： 2019年2月17日
	 * 
	 * @param request
	 * @param model
	 * @return String
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	@RequestMapping("/createFile")
	public String createFile(HttpServletRequest request,Model model){
		//查询ui代码片段
		Map param=new HashMap();
		param.put("version", 1);
		List<Map> query = baseDaoMapper.queryuicodeListMap(param);
		model.addAttribute("uilist", query);
		return "createFile"; 
	}
	/**
	 * 
	 * 功能逻辑：跳转到addui界面
	 *
	 * @方法名称：
	 * @作者:韩伟其
	 * @创建日期： 2019年2月17日
	 * 
	 * @param request
	 * @param model
	 * @return String
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	@RequestMapping("/addui")
	public String addui(HttpServletRequest request,Model model){  
		return "addui"; 
	}
	/**
	 * 
	 * 功能逻辑：保存ui片段
	 *
	 * @方法名称：saveui
	 * @作者:韩伟其
	 * @创建日期： 2019年2月17日
	 * 
	 * @param request
	 * @param json
	 * @return JSONObject
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	@ResponseBody
	@RequestMapping("/saveui")
	public JSONObject saveui(HttpServletRequest request, @RequestBody JSONObject json)  {
		String uiCode=json.getString("uiCode");
		String jsFun=json.getString("jsFun");
		String staticFile=json.getString("staticFile");
		String showui=json.getString("showui");
		String showui_name=json.getString("showui_name");
		String uicode_name=json.getString("uicode_name");
		String uicode_ms=json.getString("uicode_ms");
		String uicode_type=json.getString("uicode_type");
		JSONObject javaCode=json.getJSONObject("javaCode");
		String pagecode=json.getString("pagecode");
		//保存showui
		String showuiid=Uuid.getUuid32();
		UI_showui showuiobj = new UI_showui();
		showuiobj.setId(showuiid);
		showuiobj.setName(showui_name);
		showuiobj.setShowcode(showui);
		if(StringUtils.isNotBlank(showui)) {
			System.out.println("保存showuiobj");
			UI_showuiMapper.insertSelective(showuiobj);
		}
		//保存staticfile
		String staticCodeid=Uuid.getUuid32();
		UI_staticCode staticCode = new UI_staticCode();
		staticCode.setId(staticCodeid);
		staticCode.setCode(staticFile);
		staticCode.setType(Constants_xt.UI_STATIC_CODE_TYPE_STATIC);
		if(StringUtils.isNotBlank(staticFile)) {
			System.out.println("保存staticfile");
			UI_staticCodeMapper.insertSelective(staticCode);
		}
		//jsFun
		String jsCodeid=Uuid.getUuid32();
		UI_staticCode jsCode = new UI_staticCode();
		jsCode.setId(jsCodeid);
		jsCode.setCode(jsFun);
		jsCode.setType(Constants_xt.UI_STATIC_CODE_TYPE_JS);
		if(StringUtils.isNotBlank(jsFun)) {
			System.out.println("保存jsFun");
			UI_staticCodeMapper.insertSelective(jsCode);
		}
		//保存uicode
		String uicode_id=Uuid.getUuid32();
		UI_uicode uicode=new UI_uicode();
		uicode.setId(uicode_id);
		uicode.setMs(uicode_ms);
		uicode.setShowName(uicode_name);
		uicode.setShowuiId(showuiid);
		uicode.setType(ConvertUtil.createInteger(uicode_type));
		uicode.setUicode(uiCode);
		uicode.setUiname(uicode_name);
		uicode.setVersion(1);
		System.out.println("保存uicode");
		UI_uicodeMapper.insertSelective(uicode);
		//关联uicode所需的静态文件
		UI_uicodeStaticCode usc=new UI_uicodeStaticCode();
		usc.setId(Uuid.getUuid32());
		usc.setStaticodeId(staticCodeid);
		usc.setUicodeId(uicode_id);
		usc.setDate(DateUtils.getLongCurrDateTime14());
		if(StringUtils.isNotBlank(staticFile)) {
			System.out.println("保存关联静态文件");
			UI_uicodeStaticCodeMapper.insertSelective(usc);
		}
		UI_uicodeStaticCode usc2=new UI_uicodeStaticCode();
		usc2.setId(Uuid.getUuid32());
		usc2.setStaticodeId(jsCodeid);
		usc2.setUicodeId(uicode_id);
		usc2.setDate(DateUtils.getLongCurrDateTime14());
		if(StringUtils.isNotBlank(jsFun)) {
			System.out.println("保存关联js函数");
			UI_uicodeStaticCodeMapper.insertSelective(usc2);
		}
		//保存java代码
		UI_javaCode javacode=new UI_javaCode();
		javacode.setId(Uuid.getUuid32());
		javacode.setPropertyname(javaCode.getString("java_propertyname"));
		javacode.setPropertytype(javaCode.getString("java_propertytype"));
		javacode.setIsinit(javaCode.getInteger("java_isinit"));
		javacode.setMethod(javaCode.getString("java_method"));
		javacode.setCreatetime(DateUtils.getLongCurrDateTime14());
		javacode.setUiUicodeId(uicode_id);
		if(javaCode!=null && javaCode.containsKey("java_propertyname") && StringUtils.isNotBlank(javaCode.getString("java_propertyname"))) {
			UI_javaCodeMapper.insertSelective(javacode);
			System.out.println("保存java代码");
		}
		
		//保存page属性
		UI_pageCode page=new UI_pageCode();
		page.setId(Uuid.getUuid32());
		page.setCreatetime(DateUtils.getLongCurrDateTime14());
		page.setPropertyname(pagecode);
		page.setUiUicodeId(uicode_id);
		if(StringUtils.isNotBlank(pagecode)) {
			UI_pageCodeMapper.insertSelective(page);
			System.out.println("保存page属性");
		}
		JSONObject result= new JSONObject();
		result.put("flag", "ok");
		return result;
	}
	/**
	 * 
	 * 功能逻辑：生成文件
	 *
	 * @方法名称：produceFile
	 * @作者:韩伟其
	 * @创建日期： 2019年2月17日
	 * 
	 * @param request
	 * @param json
	 * @return
	 * @throws Exception JSONObject
	 * 
	 * @修改记录（修改时间、作者、原因）：
	 */
	@ResponseBody
	@RequestMapping("/produceFile")
	public JSONObject produceFile(HttpServletRequest request, @RequestBody JSONObject json) throws Exception {
		String htmlpath = (String) json.get("htmlpath");
		String javapath = (String) json.get("javapath");
		List<JSONObject> uicodearr = (ArrayList) json.get("uicodearr");
		//创建文件
		File tempFile =new File(htmlpath);  
		String filename=tempFile.getName();
		String filepath=htmlpath.replace(filename, "");
		FileUtils.creatFile(filepath, filename);
		Map existmap = new HashMap();
		Map newmap = new HashMap();
		//查询基础面板数据
		System.out.println("查询基础面板数据");
		UI_basePanel basepanel=new UI_basePanel();
		basepanel.setBs(1);//信源老框架
		List<UI_basePanel> queryByExists = UI_basePanelMapper.queryByExists(basepanel);
		for (UI_basePanel ui_basePanel : queryByExists) {
			if(ui_basePanel.getType()==1) {existmap.put("html", ui_basePanel.getCode());}
			if(ui_basePanel.getType()==2) {existmap.put("head", ui_basePanel.getCode());}
			if(ui_basePanel.getType()==3) {existmap.put("js", ui_basePanel.getCode());}
			if(ui_basePanel.getType()==4) {existmap.put("body", ui_basePanel.getCode());}
		}
		//查询新增数据所需静态文件及js方法
		System.out.println("查询新增数据所需静态文件及js方法");
		newmap = FileUtils.organizeHeadJsData(uicodearr,baseDaoMapper);
		//拼接新增body数据
		System.out.println("拼接新增body数据");
		StringBuffer uicodeStr = FileUtils.organizeBodyData(uicodearr);
		newmap.put("insertbody", uicodeStr);
		//组装全部数据
		System.out.println("组装全部数据");
		StringBuffer allFiledata = FileUtils.organizeData(existmap, newmap);
		//生成文件
		System.out.println("生成文件");
		//FileUtils.outputFile(new File(htmlpath), uicodearr);
		FileUtils.outputFile(tempFile, allFiledata);
		//对生成的html文件进行排版
		FileUtils.htmlLayout(htmlpath, htmlpath);
		JSONObject result= new JSONObject();
		result.put("flag", "ok");
		return result;
	}
	@RequestMapping("/test")
	public String test(HttpServletRequest request,Model model){
		//查询ui代码片段
		Map param=new HashMap();
		param.put("version", 1);
		List<Map> query = baseDaoMapper.queryuicodeListMap(param);
		model.addAttribute("uilist", query);
		return "test"; 
	}
	@RequestMapping("/index2")
	public String index2(HttpServletRequest request,Model model){
		return "admin/homePage"; 
	}
	
}
