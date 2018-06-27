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
import dao.StudentDAO;
import model.Tb_score;
import model.Tb_student;

public class ClassInfo extends Activity {

	String yuanCla_nameStr;// 创建字符串，记录跳转类型
	String tea_idStr;
	EditText ETcla_name;
Button btn_save;
	Button btn_delete;
	ScoreDAO scoreDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classinfo);// 设置布局文件

		ETcla_name=(EditText)findViewById(R.id.ETcla_name);
		btn_save=(Button)findViewById(R.id.save);
		btn_delete=(Button)findViewById(R.id.delete);		
		scoreDAO = new ScoreDAO(ClassInfo.this);
		
		Intent intent = getIntent(); // 获取Intent对象
		Bundle bundle = intent.getExtras(); // 获取传递的数据包
		yuanCla_nameStr = bundle.getString("cla_name"); // 获取传递过来的课程号
		tea_idStr=bundle.getString("tea_id");
		
		ETcla_name.setText(yuanCla_nameStr);
		
		
		btn_save.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
			@Override
			public void onClick(View v) {
				
				;
				// TODO Auto-generated method stub
				Tb_score tb_score = new Tb_score();// 创建tb_student对象
				tb_score.setCla_name(ETcla_name.getText().toString());// 设置课程名
				tb_score.setTea_id(Integer.parseInt(tea_idStr)); // 设置姓名
				tb_score.setTea_name(""); // 设置系别
				scoreDAO.updateCla(tb_score,yuanCla_nameStr);// 更新学生信息
				// 弹出信息提示
				Toast.makeText(ClassInfo.this, "课程数据修改成功！", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		btn_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_score tb_score = new Tb_score();// 创建tb_student对象
				tb_score.setCla_name(ETcla_name.getText().toString());// 设置课程名
				tb_score.setTea_id(Integer.parseInt(tea_idStr)); // 设置姓名
				tb_score.setTea_name(""); // 设置系别
				scoreDAO.deleteCla(tb_score,yuanCla_nameStr);// 更新学生信息
				// 弹出信息提示
				Toast.makeText(ClassInfo.this, "删除课程成功！", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
