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
import dao.TeacherDAO;
import model.Tb_score;
import model.Tb_student;
import model.Tb_teacher;

public class Login extends Activity {
	EditText ETuserid, ETpassword;// 创建EditText对象
	Button btnlogin, btnclose; // 创建两个Button对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);// 设置布局文件

		ETuserid = (EditText) findViewById(R.id.userid);// 获取账户文本框
		ETpassword = (EditText) findViewById(R.id.password);// 获取密码文本框
		btnlogin = (Button) findViewById(R.id.login);// 获取登录按钮
		btnclose = (Button) findViewById(R.id.close);// 获取取消按钮
		btnlogin.setOnClickListener(new OnClickListener() {// 为登录按钮设置监听事件
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ITteacher = new Intent(Login.this, TeacherMainActivity.class);// 创建Intent的teacher对象
				Intent ITstudent = new Intent(Login.this, StudentMainActivity.class);// 创建Intent的student对象
				StudentDAO studentDAO = new StudentDAO(Login.this);// 创建StudentDAO对象
				TeacherDAO teacherDAO = new TeacherDAO(Login.this);// 创建TeacherDAO对象
				String useridStr = ETuserid.getText().toString();
				String passwordStr = ETpassword.getText().toString();
				// 判断是否输入了账号
				if (useridStr.isEmpty()) {
					// 弹出信息提示
					Toast.makeText(Login.this, "请输入正确的账户名！", Toast.LENGTH_SHORT).show();
				} else {
					if (teacherDAO.find(useridStr) == (Tb_teacher) null) { // 若教师表中没有用户，在学生表中查找用户
						if (studentDAO.find(useridStr) == (Tb_student) null) { // 若学生表中也没有用户
							Toast.makeText(Login.this, "没有当前账户！", Toast.LENGTH_SHORT).show();
						} else {// 在学生表中找到用户
								// 判断输入的密码是否与数据库中的密码一致
							if (studentDAO.find(useridStr).getStu_password().equals(passwordStr)) {
								Bundle bundle = new Bundle(); // 创建一个Bundle的对象bundle
								bundle.putString("stu_id", useridStr); // 保存学号
								ITstudent.putExtras(bundle); // 将数据包添加到intent对象中
								startActivity(ITstudent);// 启动主Activity
							}
							// 密码错误
							else {
								// 弹出信息提示
								Toast.makeText(Login.this, "请输入正确的账户名或密码！", Toast.LENGTH_SHORT).show();
							}
						}
					} else { // 在教师中找到用户
						// 判断输入的密码是否与数据库中的密码一致
						if (teacherDAO.find(useridStr).getTea_password().equals(passwordStr)) {
							Bundle bundle = new Bundle(); // 创建一个Bundle的对象bundle
							bundle.putString("tea_id", useridStr); // 保存工号
							ITteacher.putExtras(bundle); // 将数据包添加到intent对象中
							startActivity(ITteacher);// 启动主Activity
						} else {
							// 弹出信息提示
							Toast.makeText(Login.this, "请输入正确的账户名或密码！", Toast.LENGTH_SHORT).show();
						}
					}
					ETuserid.setText("");// 清空账户文本框
					ETpassword.setText("");// 清空密码文本框
				}
			}
		});

		btnclose.setOnClickListener(new OnClickListener() {// 为取消按钮设置监听事件
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();// 退出当前程序
			}
		});

		TeacherDAO teacher = new TeacherDAO(Login.this);
		if (teacher.find("1585") == (Tb_teacher) null) {
			Tb_teacher tb_teacher1 = new Tb_teacher(1585, "赵莲芬", "1585", "13452958203", "zlf@scse.com.cn");
			Tb_teacher tb_teacher2 = new Tb_teacher(1581, "甘卫民", "1581", "15829384900", "gwm@scse.com.cn");
			Tb_teacher tb_teacher3 = new Tb_teacher(1090, "覃忠台", "1090", "15503920472", "qzt@scse.com.cn");
			Tb_teacher tb_teacher4 = new Tb_teacher(1708, "黄小平", "1708", "18792847265", "hxp@scse.com.cn");
			Tb_teacher tb_teacher5 = new Tb_teacher(1813, "刘鸣", "1813", "14356683921", "lm@scse.com.cn");
			teacher.add(tb_teacher1);
			teacher.add(tb_teacher2);
			teacher.add(tb_teacher3);
			teacher.add(tb_teacher4);
			teacher.add(tb_teacher5);
		}
	}
}
