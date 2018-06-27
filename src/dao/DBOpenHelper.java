package dao;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	private static final String DBNAME = "stu_MS.db";
	
	public DBOpenHelper(Context context) {
		// TODO Auto-generated constructor stub
		super(context, DBNAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table tb_teacher (tea_id integer primary key,tea_name varchar(10),tea_password varchar(10),tea_phone varchar(20),tea_email varchar(20))");// 创建教师信息表
		db.execSQL("create table tb_student (stu_id integer primary key,stu_name varchar(10),stu_password varchar(10),classify varchar(20),stu_phone varchar(20),stu_email varchar(20))");// 创建学生信息表
		db.execSQL("create table tb_score (score_id integer primary key AUTOINCREMENT,stu_id integer,stu_name varchar(10),cla_name varchar(10),tea_id integer,tea_name varchar(10),score double)");// 创建成绩表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
