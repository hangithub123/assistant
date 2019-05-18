package pub.po;


public class Xszgrz {

	private String id = null;

	private String zjmc = null;

	private String fj = null;

	private String zjsm = null;

	private String xsbh = null;

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

	public void setXsbh(String xsbh) {
		this.xsbh = xsbh;
	}

	public String getXsbh() {
		return this.xsbh;
	}

	public boolean isEmptyId() {
		return this.id == null || this.id.length() == 0;
	}

}