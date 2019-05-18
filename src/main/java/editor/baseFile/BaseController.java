package editor.baseFile;

import javax.servlet.http.HttpServletRequest;

import editor.Constants.Constants_xt;
import pub.po.vo.PageParams;

public class BaseController {

	private static HttpServletRequest request;
	private PageParams pageParams;
	public PageParams getPageParams() {
		if(pageParams==null){
			pageParams=new PageParams();
			pageParams.setCheckFlag(true);
			pageParams.setCleanOrderBy(false);
			pageParams.setPage(1);
			pageParams.setPageSize(Constants_xt.SPLITPAGE_LIMIT_TEN);
		}
		return pageParams;
	}

	public void setPageParams(PageParams pageParams) {
		this.pageParams = pageParams;
	}


	public static HttpServletRequest getRequest() {
		return request;
	}

	public static void setRequest(HttpServletRequest request) {
		BaseController.request = request;
	}
	//检查必要数据是否存在,不存在则使用默认值
	public PageParams getSplitPageInfo(PageParams pageParams){
		if(pageParams.getPage()==null || pageParams.getPageSize() ==null){
			pageParams=this.getPageParams();
			return pageParams;
		}
		else{
			return pageParams;
		}
	}
}
