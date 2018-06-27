package model;

public class Tb_student {
	private int stu_id;
	private String stu_name;
	private String stu_password;
	private String classify;
	private String stu_phone;
	private String stu_email;
	public Tb_student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tb_student(int stu_id, String stu_name, String stu_password, String classify, String stu_phone,
			String stu_email) {
		super();
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.stu_password = stu_password;
		this.classify = classify;
		this.stu_phone = stu_phone;
		this.stu_email = stu_email;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	public String getStu_password() {
		return stu_password;
	}
	public void setStu_password(String stu_password) {
		this.stu_password = stu_password;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getStu_phone() {
		return stu_phone;
	}
	public void setStu_phone(String stu_phone) {
		this.stu_phone = stu_phone;
	}
	public String getStu_email() {
		return stu_email;
	}
	public void setStu_email(String stu_email) {
		this.stu_email = stu_email;
	}
}
