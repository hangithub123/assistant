package pub.po;


public class UI_uicodeStaticCode {

	private String id = null;

	private String uicodeId = null;

	private String staticodeId = null;

	private Long date;

	private Integer sort;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setUicodeId(String uicodeId) {
		this.uicodeId = uicodeId;
	}

	public String getUicodeId() {
		return this.uicodeId;
	}

	public void setStaticodeId(String staticodeId) {
		this.staticodeId = staticodeId;
	}

	public String getStaticodeId() {
		return this.staticodeId;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public Long getDate() {
		return this.date;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

}