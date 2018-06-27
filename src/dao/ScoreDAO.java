package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import model.Tb_score;
import model.Tb_student;

public class ScoreDAO {
	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public ScoreDAO(Context context) {
		helper = new DBOpenHelper(context);
		db = helper.getWritableDatabase();
	}


	/**
	 * 
	 * 添加课程信息
	 */
	public void addClass(Tb_score tb_score) {

		db.execSQL("insert into tb_score (tea_id,tea_name,cla_name)" + "values (?,?,?)",
				new Object[] { tb_score.getTea_id(), tb_score.getTea_name(), tb_score.getCla_name() });
	}

	/**
	 * 
	 * 查看指定课程
	 */
	public Tb_score findClass(Tb_score tb_score) {
		Cursor cursor = db.rawQuery("select distinct(cla_name) from tb_score where cla_name=? and tea_id=?",
				new String[] { tb_score.getCla_name(), String.valueOf(tb_score.getTea_id()) });

		if (cursor.moveToNext()) {
			return new Tb_score(tb_score.getTea_id(), cursor.getString(cursor.getColumnIndex("cla_name")),
					cursor.getString(cursor.getColumnIndex("cla_name")));
		}
		cursor.close();
		return null;
	}

	/**
	 * 
	 * 通过tea_id,cla_name,stu_id找出成绩
	 */
	public Tb_score find(String stu_id, String tea_id, String cla_name) {
		Cursor cursor = db.rawQuery("select * from tb_score where stu_id=? and tea_id=? and cla_name=?",
				new String[] { stu_id, tea_id, cla_name });

		if (cursor.moveToNext()) {
			return new Tb_score(cursor.getInt(cursor.getColumnIndex("score_id")),
					cursor.getInt(cursor.getColumnIndex("stu_id")), cursor.getString(cursor.getColumnIndex("stu_name")),
					cursor.getInt(cursor.getColumnIndex("tea_id")), cursor.getString(cursor.getColumnIndex("tea_name")),
					cursor.getString(cursor.getColumnIndex("cla_name")),
					cursor.getDouble(cursor.getColumnIndex("score")));

		}
		cursor.close();
		return null;
	}

	/**
	 * 
	 * 更新成绩信息
	 */
	public void updateScore(Tb_score tb_score) {
		db.execSQL("update tb_score set score = ?" + "where score_id = ?",
				new Object[] { tb_score.getScore(), tb_score.getScore_id() });
	};

	/**
	 * 
	 * 更改课程信息
	 */
	public void updateCla(Tb_score tb_score, String yuanCla_name) {
		db.execSQL("update tb_score set cla_name = ?" + "where cla_name = ? and tea_id=?",
				new Object[] { tb_score.getCla_name(), yuanCla_name, tb_score.getTea_id() });
	};

	/**
	 * 
	 * 删除课程信息
	 */
	public void deleteCla(Tb_score tb_score, String yuanCla_name) {
		db.execSQL("delete from tb_score where cla_name = ? and tea_id=?",
				new Object[] { yuanCla_name, tb_score.getTea_id() });
	};


	/**
	 * 获取课程信息
	 * 
	 * @param start
	 *            起始位置
	 * @param count
	 *            每页显示数量
	 * @return
	 */
	public List<String> getScrollClaData(String tea_id, int start, int count) {
		List<String> tb_scores = new ArrayList<String>();// 创建集合对象
		// 获取所有课程信息
		Cursor cursor = db.rawQuery("select distinct(cla_name) from tb_score where tea_id=? limit ?,?",
				new String[] { tea_id, String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext()) {// 遍历所有的课程信息
			// 将遍历到的信息添加到集合中
			tb_scores.add(cursor.getString(cursor.getColumnIndex("cla_name")));
		}
		cursor.close();// 关闭游标
		return tb_scores;// 返回集合
	}

	/**
	 * 获取课程对应的信息
	 * 
	 * @param start
	 *            起始位置
	 * @param count
	 *            每页显示数量
	 * @return
	 */
	public List<Tb_score> getScrollCla_StuData(String tea_id, String cla_name, int start, int count, String addedview) {
		List<Tb_score> tb_scores = new ArrayList<Tb_score>();// 创建集合对象

		String rawquery;
		if (addedview.equals("addedview")) {
			rawquery = "select * from tb_score where tea_id=? and cla_name=? and stu_id is not null limit ?,?";
		} else {
			rawquery = "select * from tb_score where tea_id=? and cla_name=? and stu_id is not null and score=0.0 limit ?,?";
		}
		// 获取所有收入信息
		Cursor cursor = db.rawQuery(rawquery,
				new String[] { tea_id, cla_name, String.valueOf(start), String.valueOf(count) });
		while (cursor.moveToNext()) {// 遍历所有的收入信息
			// 将遍历到的信息添加到集合中
			tb_scores.add(new Tb_score(cursor.getInt(cursor.getColumnIndex("score_id")),
					cursor.getInt(cursor.getColumnIndex("stu_id")), cursor.getString(cursor.getColumnIndex("stu_name")),
					cursor.getInt(cursor.getColumnIndex("tea_id")), cursor.getString(cursor.getColumnIndex("tea_name")),
					cursor.getString(cursor.getColumnIndex("cla_name")),
					cursor.getDouble(cursor.getColumnIndex("score"))));
		}
		cursor.close();// 关闭游标
		return tb_scores;// 返回集合
	}

	/**
	 * 获取当前教师的课程记录数
	 * 
	 * @return
	 */
	public long getClaCount(String tea_id) {
		Cursor cursor = db.rawQuery("select count(distinct(cla_name)) from tb_score where tea_id=?",
				new String[] { tea_id });// 获取课程信息的记录数
		if (cursor.moveToNext()) {// 判断Cursor中是否有数据
			return cursor.getLong(0);// 返回总记录数
		}
		cursor.close();// 关闭游标
		return 0;// 如果没有数据，则返回0
	}

	/**
	 * 获取课程对应学生总记录数
	 * 
	 * @return
	 */
	public long getCla_StuCount(String tea_id, String cla_name, String addedview) {
		String rawquery;
		if (addedview.equals("addedview")) {
			rawquery = "select count(score_id) from tb_score where tea_id=? and cla_name=? and stu_id is not null";
		} else {
			rawquery = "select count(score_id) from tb_score where tea_id=? and cla_name=? and stu_id is not null and (score=0.0 or score is null)";
		}
		Cursor cursor = db.rawQuery(rawquery, new String[] { tea_id, cla_name });// 获取课程对应学生信息的记录数
		if (cursor.moveToNext()) {// 判断Cursor中是否有数据
			return cursor.getLong(0);// 返回总记录数
		}
		cursor.close();// 关闭游标
		return 0;// 如果没有数据，则返回0
	}

	public void addStudent(Tb_score tb_score) {
		// TODO Auto-generated method stub
		db.execSQL("insert into tb_score (stu_id,stu_name,tea_id,tea_name,cla_name,score)" + "values (?,?,?,?,?,0.0)",
				new Object[] { tb_score.getStu_id(), tb_score.getStu_name(), tb_score.getTea_id(),
						tb_score.getTea_name(), tb_score.getCla_name() });
	}

	public void updateStudent(String stu_id, String stu_name) {
		// TODO Auto-generated method stub
		db.execSQL("update tb_score set stu_name = ?" + "where stu_id=?", new Object[] { stu_name, stu_id });
	}

	public void deleteClaStudent(String stu_id, String tea_id, String cla_name) {
		// TODO Auto-generated method stub
		db.execSQL("delete from tb_score where stu_id=? and tea_id = ? and cla_name=?",
				new Object[] { stu_id, tea_id, cla_name });
	}

	/**
	 * 成绩信息汇总
	 * 
	 * @return
	 */
	public Map<String, Double> getTotal(String tea_id, String cla_name) {
		// 获取当前课程的汇总信息
		Cursor cursor_min = db.rawQuery("select min(score) " + "from tb_score where tea_id=? and cla_name=?",
				new String[] { tea_id, cla_name });
		Cursor cursor_avg = db.rawQuery("select avg(score) " + "from tb_score where tea_id=? and cla_name=?",
				new String[] { tea_id, cla_name });
		Cursor cursor_max = db.rawQuery("select max(score) " + "from tb_score where tea_id=? and cla_name=?",
				new String[] { tea_id, cla_name });
		//
		cursor_min.moveToFirst();
		cursor_avg.moveToFirst();
		cursor_max.moveToFirst();
		Map<String, Double> map = new HashMap<String, Double>(); // 创建一个Map对象
		map.put("最低分", cursor_min.getDouble(0));
		map.put("平均分", cursor_avg.getDouble(0));
		map.put("最高分", cursor_max.getDouble(0));
		cursor_min.close();// 关闭游标
		cursor_avg.close();// 关闭游标
		cursor_max.close();// 关闭游标
		return map;// 返回Map对象
	}

	public Tb_score findS(String id) {
		Cursor cursor = db.rawQuery("select * from tb_score where stu_id=?", new String[] { id });
		if (cursor.moveToNext()) {
			return new Tb_score(
					cursor.getInt(cursor.getColumnIndex("score_id")),
					cursor.getInt(cursor.getColumnIndex("stu_id")), cursor.getString(cursor.getColumnIndex("stu_name")),
					cursor.getInt(cursor.getColumnIndex("tea_id")), cursor.getString(cursor.getColumnIndex("tea_name")),
					cursor.getString(cursor.getColumnIndex("cla_name")),
					cursor.getDouble(cursor.getColumnIndex("score"))
					);
		}
		cursor.close();
		return null;
	}

	public void updateTea(String tea_id, String new_tea_name) {
		// TODO Auto-generated method stub
		db.execSQL("update tb_score set tea_name = ?" + "where tea_id=?", new Object[] { new_tea_name, tea_id });

	}

	/**
	 * 获取课程对应的信息
	 * 
	 * @param start
	 *            起始位置
	 * @param count
	 *            每页显示数量
	 * @return
	 */
	public List<Tb_score> getScrollStuData(String stu_id) {
		List<Tb_score> tb_scores = new ArrayList<Tb_score>();// 创建集合对象
		// 获取所有课程信息
		Cursor cursor = db.rawQuery("select * from tb_score where stu_id=? and score<>0.0", new String[] { stu_id });
		while (cursor.moveToNext()) {// 遍历所有的课程信息
			// 将遍历到的课程信息添加到集合中
			tb_scores.add(new Tb_score(cursor.getInt(cursor.getColumnIndex("score_id")),
					cursor.getInt(cursor.getColumnIndex("stu_id")), cursor.getString(cursor.getColumnIndex("stu_name")),
					cursor.getInt(cursor.getColumnIndex("tea_id")), cursor.getString(cursor.getColumnIndex("tea_name")),
					cursor.getString(cursor.getColumnIndex("cla_name")),
					cursor.getDouble(cursor.getColumnIndex("score"))));
		}
		cursor.close();// 关闭游标
		return tb_scores;// 返回集合
	}
}
