package config.cfg_freemarker;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.cache.TemplateLoader;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 
 * 【业务说明】基础加载ftl指令模板文件类  
 *
 * @工程   myNovel
 * @类名   BaseDirective 
 * 
 * @作者   韩伟其
 * @创建日期  2018年11月18日
 *
 * @修改记录 （修改时间、作者、原因）：
 * 众创小说
 */
public class BaseDirective implements TemplateDirectiveModel {
	private static final Logger LOGGER = LoggerFactory.getLogger(BaseDirective.class);
	@Override
	public void execute(Environment environment, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		TemplateLoader templateLoader = environment.getConfiguration().getTemplateLoader();
		String fullTemplatePath = "/directiveTemplate/baseDirective.ftl";//其他指令文件在该文件内使用<#include>指令即可
		if (templateLoader != null) {
			//LOGGER.info("引用指令模板文件 "+fullTemplatePath);
			environment.include(environment.getTemplateForInclusion(fullTemplatePath, null, true));
			
		} else {
			LOGGER.error("templateLoader为空");
		}
	}

}
