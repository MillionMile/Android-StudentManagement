package activity;


import com.example.studentmanagement.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import dao.ScoreDAO;
import dao.TeacherDAO;
import model.Tb_score;

public class AddClass extends Activity {
	EditText cla_name;
	ScoreDAO scoreDAO;
	String tea_idStr;
	Button btn_insert;
	Button btn_cancel;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addclass);

		cla_name = (EditText) findViewById(R.id.ETclaname);
		btn_insert = (Button) findViewById(R.id.insert);
		btn_cancel = (Button) findViewById(R.id.cancel);
//		cla_name.setText("");
		Intent intent = getIntent(); // 获取Intent对象
		Bundle bundle = intent.getExtras(); // 获取传递的数据包
		tea_idStr = bundle.getString("tea_id"); // 获取传递过来的工号

		scoreDAO = new ScoreDAO(AddClass.this);

		btn_insert.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TeacherDAO teacherDAO;
				teacherDAO = new TeacherDAO(AddClass.this);
				String cla_nameStr = cla_name.getText().toString();
				if (cla_nameStr.length()==0) {
					// 弹出信息提示
					Toast.makeText(AddClass.this, "请输入正确的课程名称！", Toast.LENGTH_SHORT).show();
				} else {
					Tb_score tb_score = new Tb_score(Integer.parseInt(tea_idStr),
							teacherDAO.find(tea_idStr).getTea_name(), cla_name.getText().toString());

					if (scoreDAO.findClass(tb_score) == (Tb_score) null) {

						scoreDAO.addClass(tb_score);// 更新学生信息
						// 弹出信息提示
						Toast.makeText(AddClass.this, "课程添加成功！", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(AddClass.this, "您已有该课程！", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
