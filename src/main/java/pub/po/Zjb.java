package pub.po;


public class Zjb {

	private String id = null;

	private String zjmc = null;

	private String zjjj = null;

	private String kcbh = null;

	private Long cshrq;

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

	public void setZjjj(String zjjj) {
		this.zjjj = zjjj;
	}

	public String getZjjj() {
		return this.zjjj;
	}

	public void setKcbh(String kcbh) {
		this.kcbh = kcbh;
	}

	public String getKcbh() {
		return this.kcbh;
	}

	public void setCshrq(Long long1) {
		this.cshrq = long1;
	}

	public Long getCshrq() {
		return this.cshrq;
	}

	public boolean isEmptyId() {
		return this.id == null || this.id.length() == 0;
	}

}