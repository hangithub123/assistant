package pub.po;


public class Czyb {

	private String id = null;

	private String dlh = null;

	private String mm = null;

	private String email = null;

	private Integer role;

	private Integer sybz;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setDlh(String dlh) {
		this.dlh = dlh;
	}

	public String getDlh() {
		return this.dlh;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getMm() {
		return this.mm;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getRole() {
		return this.role;
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