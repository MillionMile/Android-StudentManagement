package activity;

import com.example.studentmanagement.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import dao.ScoreDAO;
import dao.TeacherDAO;
import model.Tb_teacher;
public class TeacherInfo extends Activity {
	TextView tea_id;
	EditText tea_name;
	EditText tea_password;
	EditText tea_phone;
	EditText tea_email;
	Button btn_save;
	Button btn_reset;
	TeacherDAO teacherDAO;
	String tea_idStr;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacherinformation);
		
		tea_id=(TextView)findViewById(R.id.TVtea_id);
		tea_name=(EditText)findViewById(R.id.ETtea_name);
		tea_password=(EditText)findViewById(R.id.ETtea_password);
		tea_phone=(EditText)findViewById(R.id.ETtea_phone);
		tea_email=(EditText)findViewById(R.id.ETtea_email);
		btn_save=(Button)findViewById(R.id.save);
		btn_reset=(Button)findViewById(R.id.reset);
		
		Intent intent=getIntent();	//获取Intent对象
		Bundle bundle=intent.getExtras();		//获取传递的数据包
		tea_idStr=bundle.getString("tea_id");	//获取传递过来的工号
		
		
		teacherDAO=new TeacherDAO(TeacherInfo.this);
		
		tea_id.setText(tea_idStr);
		tea_name.setText(teacherDAO.find(tea_idStr).getTea_name());
		tea_password.setText(teacherDAO.find(tea_idStr).getTea_password());
		tea_phone.setText(teacherDAO.find(tea_idStr).getTea_phone());
		tea_email.setText(teacherDAO.find(tea_idStr).getTea_email());
		
		btn_save.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_teacher tb_teacher = new Tb_teacher();// 创建Tb_score对象
				tb_teacher.setTea_id(Integer.parseInt(tea_idStr));	// 设置工号
				tb_teacher.setTea_name(tea_name.getText().toString()); //设置姓名
				tb_teacher.setTea_phone(tea_phone.getText().toString());//设置电话
				tb_teacher.setTea_email(tea_email.getText().toString());//设置邮箱
				tb_teacher.setTea_password(tea_password.getText().toString());//设置密码
				teacherDAO.update(tb_teacher);// 更新学生信息
				
				ScoreDAO scoreDAO=new ScoreDAO(TeacherInfo.this);
				scoreDAO.updateTea(tea_idStr,tea_name.getText().toString());
				// 弹出信息提示
				Toast.makeText(TeacherInfo.this, "数据修改成功！", Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		
		btn_reset.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tea_name.setText(teacherDAO.find(tea_idStr).getTea_name());
				tea_password.setText(teacherDAO.find(tea_idStr).getTea_password());
				tea_phone.setText(teacherDAO.find(tea_idStr).getTea_phone());
				tea_email.setText(teacherDAO.find(tea_idStr).getTea_email());
			}
		});
		
	}
}