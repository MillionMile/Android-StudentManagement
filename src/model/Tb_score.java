package model;

public class Tb_score {
	private int score_id;
	private int stu_id;
	private String stu_name;
	private int tea_id;
	private String tea_name;
	private String cla_name;
	private double score;
	public Tb_score() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tb_score(int score_id, int stu_id, String stu_name, int tea_id, String tea_name, String cla_name,
			double score) {
		super();
		this.score_id = score_id;
		this.stu_id = stu_id;
		this.stu_name = stu_name;
		this.tea_id = tea_id;
		this.tea_name = tea_name;
		this.cla_name = cla_name;
		this.score = score;
	}
	
	public Tb_score(int tea_id, String tea_name, String cla_name) {
		super();
		this.tea_id = tea_id;
		this.tea_name = tea_name;
		this.cla_name = cla_name;
	}
	
	
	
	public int getScore_id() {
		return score_id;
	}
	public void setScore_id(int score_id) {
		this.score_id = score_id;
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
	public String getCla_name() {
		return cla_name;
	}
	public void setCla_name(String cla_name) {
		this.cla_name = cla_name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}


}
