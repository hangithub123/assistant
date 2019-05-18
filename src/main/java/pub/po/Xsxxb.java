package pub.po;


public class Xsxxb {

	private String id = null;

	private String name = null;

	private Integer sex;

	private String xh = null;

	private String sfzh = null;

	private String mobile = null;

	private String fj = null;

	private String xybh = null;

	private String zybh = null;

	private Integer zt;

	private String dlh = null;

	private String email = null;

	private Integer sybz;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXh() {
		return this.xh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getSfzh() {
		return this.sfzh;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	public String getFj() {
		return this.fj;
	}

	public void setXybh(String xybh) {
		this.xybh = xybh;
	}

	public String getXybh() {
		return this.xybh;
	}

	public void setZybh(String zybh) {
		this.zybh = zybh;
	}

	public String getZybh() {
		return this.zybh;
	}

	public void setZt(Integer zt) {
		this.zt = zt;
	}

	public Integer getZt() {
		return this.zt;
	}

	public void setDlh(String dlh) {
		this.dlh = dlh;
	}

	public String getDlh() {
		return this.dlh;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setSybz(Integer sybz) {
		this.sybz = sybz;
	}

	public Integer getSybz() {
		return this.sybz;
	}

	public boolean isEmptyId() {
		return this.id == null || this.id.length() == 0;
	}
}