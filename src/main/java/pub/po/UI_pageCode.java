package pub.po;


public class UI_pageCode {

	private String id = null;

	private String propertyname = null;

	private String uiUicodeId = null;

	private Long createtime;

	private Integer sort;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}

	public String getPropertyname() {
		return this.propertyname;
	}

	public void setUiUicodeId(String uiUicodeId) {
		this.uiUicodeId = uiUicodeId;
	}

	public String getUiUicodeId() {
		return this.uiUicodeId;
	}

	public void setCreatetime(Long createtime) {
		this.createtime = createtime;
	}

	public Long getCreatetime() {
		return this.createtime;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

}