package dto;

public class FishDto {
	private String id = null;
	private String pwd = null;
	private String indate = null;
	
	public String getId() {
		return id;
	}
	public String getPwd() {
		return pwd;
	}
	public String getIndate() {
		return indate;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	// DTO 정보 디버깅 메서드 
	@Override
	public String toString() {
		return "FishDto [id=" + id + ", pwd=" + pwd + ", indate=" + indate + "]";
	}

}
