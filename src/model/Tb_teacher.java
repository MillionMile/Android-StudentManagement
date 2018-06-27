package model;

public class Tb_teacher {
	private int tea_id;
	private String tea_name;
	private String tea_password;
	private String tea_phone;
	private String tea_email;
	public Tb_teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tb_teacher(int tea_id, String tea_name, String tea_password, String tea_phone, String tea_email) {
		super();
		this.tea_id = tea_id;
		this.tea_name = tea_name;
		this.tea_password = tea_password;
		this.tea_phone = tea_phone;
		this.tea_email = tea_email;
	}

	public int getTea_id() {
		return tea_id;
	}
	public void setTea_id(int tea_id) {
		this.tea_id = tea_id;
	}
	public String getTea_name() {
		return tea_name;
	}
	public void setTea_name(String tea_name) {
		this.tea_name = tea_name;
	}
	public String getTea_password() {
		return tea_password;
	}
	public void setTea_password(String tea_password) {
		this.tea_password = tea_password;
	}
	public String getTea_phone() {
		return tea_phone;
	}
	public void setTea_phone(String tea_phone) {
		this.tea_phone = tea_phone;
	}
	public String getTea_email() {
		return tea_email;
	}
	public void setTea_email(String tea_email) {
		this.tea_email = tea_email;
	}
	
	
}
