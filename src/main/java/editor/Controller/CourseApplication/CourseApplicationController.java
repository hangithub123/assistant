package editor.Controller.CourseApplication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 
 * @description 课程申请管理
 *
 */
@Component
@RequestMapping("/courseApplication")
public class CourseApplicationController {
	
    @RequestMapping
	public String courseApplicationList(HttpServletRequest request,Model model) {
    	
		return "teacher/applicationManagement/courseApplyList";
	}
}
