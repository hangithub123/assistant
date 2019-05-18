package pub.po;


public class UI_basePanel {

	private String id = null;

	private Integer type;

	private String name = null;

	private String code = null;

	private Integer sort;

	private Integer bs;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return this.type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setBs(Integer bs) {
		this.bs = bs;
	}

	public Integer getBs() {
		return this.bs;
	}

}