package config.cfg_freemarker;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.SimpleHash;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateModelException;
/**
 * 
 * 【业务说明】指令共享加载器  
 *
 * @工程   myNovel
 * @类名   FreeMarkerExtendHandler 
 * 
 * @作者   韩伟其
 * @创建日期  2018年11月18日
 *
 * @修改记录 （修改时间、作者、原因）：
 * 众创小说
 */
@Component
public class FreeMarkerExtendHandler implements ApplicationContextAware {
	@Autowired
	private Map<String, TemplateDirectiveModel> directiveMap;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	private static final Logger LOGGER = LoggerFactory.getLogger(FreeMarkerExtendHandler.class);
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		LOGGER.info("搜集指令并共享...开始");
		Map<String, Object> freemarkerVariables = new HashMap<String, Object>();
		StringBuffer directives = new StringBuffer();
		for (Entry<String, TemplateDirectiveModel> entry : directiveMap.entrySet()) {
			freemarkerVariables.put(entry.getKey(), entry.getValue());
			if (0 != directives.length())
				directives.append(",");
			directives.append(entry.getKey());
		}

		try {
			//freeMarkerConfigurer.getConfiguration().setSharedVariable("novel", new UpperDirective());
			freeMarkerConfigurer.getConfiguration().setAllSharedVariables(
					new SimpleHash(freemarkerVariables, freeMarkerConfigurer.getConfiguration().getObjectWrapper()));
		} catch (TemplateModelException  e) {

		}
		LOGGER.info("搜集指令并共享...结束");
	}

}
