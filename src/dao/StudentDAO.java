package dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.Tb_student;

public class StudentDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public StudentDAO(Context context) {
		helper = new DBOpenHelper(context);
		db = helper.getWritableDatabase();
	}

	/**
	 * 
	 * 添加学生信息
	 */
	public void add(Tb_student tb_student) {
		db.execSQL(
				"insert into tb_student (stu_id,stu_name,stu_password,classify,stu_phone,stu_email)"
						+ "values (?,?,?,?,?,?)",
				new Object[] { tb_student.getStu_id(), tb_student.getStu_name(), tb_student.getStu_password(),
						tb_student.getClassify(), tb_student.getStu_phone(), tb_student.getStu_email() });
	}

	/**
	 * 
	 * 更新学生信息
	 */
	public void update(Tb_student tb_student) {
		db.execSQL(
				"update tb_student set stu_name = ?,stu_password = ?,classify = ?,stu_phone = ?,stu_email = ?"
						+ "where stu_id = ?",
				new Object[] { tb_student.getStu_name(), tb_student.getStu_password(), tb_student.getClassify(),
						tb_student.getStu_phone(), tb_student.getStu_email(), tb_student.getStu_id()});
	};
	
	/**
	 * 
	 * 查询学生信息
	 */
	public Tb_student find(String id) {
		Cursor cursor = db.rawQuery("select stu_id,stu_name,stu_password,classify,stu_phone,stu_email from tb_student "
				+ "where stu_id = ?", new String[] { id });
		if (cursor.moveToNext()) {
			return new Tb_student(cursor.getInt(cursor.getColumnIndex("stu_id")),
					cursor.getString(cursor.getColumnIndex("stu_name")),
					cursor.getString(cursor.getColumnIndex("stu_password")),
					cursor.getString(cursor.getColumnIndex("classify")),
					cursor.getString(cursor.getColumnIndex("stu_phone")),
					cursor.getString(cursor.getColumnIndex("stu_email")));
		}
		cursor.close();
		return null;
	}


}