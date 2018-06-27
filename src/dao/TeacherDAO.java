package dao;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.Tb_teacher;;

public class TeacherDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;
	
	public TeacherDAO(Context context){// ���幹�캯��
		helper = new DBOpenHelper(context);// ��ʼ��DBOpenHelper����
		db = helper.getWritableDatabase();// ��ʼ��SQLiteDatabase����
	}
	/**
	 * 
	 * ��ӽ�ʦ��Ϣ
	 */
	public void add(Tb_teacher tb_teacher){
		db.execSQL(
				"insert into tb_teacher (tea_id,tea_name,tea_password,tea_phone,tea_email)"
				+ "values (?,?,?,?,?)",new Object[]{
						tb_teacher.getTea_id(),
						tb_teacher.getTea_name(),
						tb_teacher.getTea_password(),
						tb_teacher.getTea_phone(),
						tb_teacher.getTea_email()});
	}
	/**
	 * ���ҽ�ʦ��Ϣ
	 */
	public Tb_teacher find(String id){
		Cursor cursor = db.rawQuery("select tea_id,tea_name,tea_password,tea_phone,tea_email from tb_teacher "
	                                 +" where tea_id =?", new String[] {id});
		if (cursor.moveToNext()) {
			return new Tb_teacher(
					cursor.getInt(cursor.getColumnIndex("tea_id")),
					cursor.getString(cursor.getColumnIndex("tea_name")),
					cursor.getString(cursor.getColumnIndex("tea_password")),
					cursor.getString(cursor.getColumnIndex("tea_phone")),
					cursor.getString(cursor.getColumnIndex("tea_email"))
					);
		}
		cursor.close();
		return null;
	}
	
	/**
	 * 
	 * ���½�ʦ��Ϣ
	 */
	public void update(Tb_teacher tb_teacher){
		db.execSQL(
				"update tb_teacher set tea_name = ?,tea_password = ?,tea_phone = ?,tea_email = ?"
				+"where tea_id = ?",new Object[]{
						tb_teacher.getTea_name(),
						tb_teacher.getTea_password(),
						tb_teacher.getTea_phone(),
						tb_teacher.getTea_email(),
						tb_teacher.getTea_id()
				}
				);
	}
	
	/**
	 * ɾ����ʦ��Ϣ
	 * 
	 */
	public void detele(Integer... ids){
		if (ids.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				sb.append('?').append(',');
			}
			sb.deleteCharAt(sb.length() - 1);
			db.execSQL("delete from tb_teacher where tea_id in (" + sb + ")",(Object[]) ids);
		}
	}
	
	
	public long getCount() {
		Cursor cursor = db.rawQuery("select count(tea_password) from tb_teacher", null);// ��ȡ������Ϣ�ļ�¼��
		if (cursor.moveToNext()){// �ж�Cursor���Ƿ�������
			return cursor.getLong(0);// �����ܼ�¼��
		}
		cursor.close();// �ر��α�
		return 0;// ���û�����ݣ��򷵻�0
	}
	
	public List<Tb_teacher> getScrollData(int start,int count){
		List<Tb_teacher> tb_teacher = new ArrayList<Tb_teacher>();
		
		Cursor cursor = db.rawQuery("select * from tb_teacher limit ?,?", new String[]{String.valueOf(start),String.valueOf(count)});
		while(cursor.moveToNext()){
			tb_teacher.add(new Tb_teacher(cursor.getInt(cursor.getColumnIndex("tea_id")),
					                      cursor.getString(cursor.getColumnIndex("tea_name")),
					                      cursor.getString(cursor.getColumnIndex("tea_password")),
					                      cursor.getString(cursor.getColumnIndex("tea_phone")),
					                      cursor.getString(cursor.getColumnIndex("tea_email"))));
		}
		cursor.close();
		return tb_teacher;
	}
	
}
