package pub.po;


public class Jxzgrz {

	private String id = null;

	private String zjmc = null;

	private String fj = null;

	private String zjsm = null;

	private String jsbh = null;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setZjmc(String zjmc) {
		this.zjmc = zjmc;
	}

	public String getZjmc() {
		return this.zjmc;
	}

	public void setFj(String fj) {
		this.fj = fj;
	}

	public String getFj() {
		return this.fj;
	}

	public void setZjsm(String zjsm) {
		this.zjsm = zjsm;
	}

	public String getZjsm() {
		return this.zjsm;
	}

	public void setJsbh(String jsbh) {
		this.jsbh = jsbh;
	}

	public String getJsbh() {
		return this.jsbh;
	}

	public boolean isEmptyId() {
		return this.id == null || this.id.length() == 0;
	}

}