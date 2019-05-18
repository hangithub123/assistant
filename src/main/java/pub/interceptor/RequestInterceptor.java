package pub.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * 
 * 【业务说明】拦截器 判断请求是否合法，判断用户是否登录或者可以自动登录。  
 *
 * @工程   myNovel
 * @类名   LoginInterceptor 
 * 
 * @作者   韩伟其
 * @创建日期  2018年12月16日
 *
 * @修改记录 （修改时间、作者、原因）：
 * 众创小说
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {
	Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String url = request.getRequestURI();
		logger.debug("请求路径="+url);
		//首次访问，跳转到首页
		if(url.equals("/UIEditor/")){
			response.sendRedirect("login/homePage");
			return false;
		}
		return true;
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView!=null && modelAndView.getModelMap()!=null)
		modelAndView.getModelMap().addAttribute("ctx",request.getContextPath());
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
