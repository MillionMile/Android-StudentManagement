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

public class AddStudent extends Activity {

	EditText stu_id;
	EditText stu_name;
	EditText classify;
	EditText stu_phone;
	EditText stu_email;
	Button btn_insert;
	Button btn_cancel;
	StudentDAO studentDAO;
	ScoreDAO scoreDAO;
	String cla_name;
	String tea_idStr;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addstudent);

		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		cla_name = bundle.getString("cla_name"); // ��ȡ���ݹ����Ŀγ̺�
		tea_idStr = bundle.getString("tea_id");

		scoreDAO = new ScoreDAO(AddStudent.this);
		studentDAO = new StudentDAO(AddStudent.this);

		stu_id = (EditText) findViewById(R.id.ETstu_id);
		stu_name = (EditText) findViewById(R.id.ETstu_name);
		classify = (EditText) findViewById(R.id.ETclassify);
		stu_phone = (EditText) findViewById(R.id.ETstu_phone);
		stu_email = (EditText) findViewById(R.id.ETstu_email);
		btn_insert = (Button) findViewById(R.id.insert);
		btn_cancel = (Button) findViewById(R.id.cancel);

		btn_insert.setOnClickListener(new OnClickListener() {// Ϊ��¼��ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String stu_idStr = stu_id.getText().toString();

				if (stu_idStr.length() == 0) {
					// ������Ϣ��ʾ
					Toast.makeText(AddStudent.this, "��������ȷ��ѧ�ţ�", Toast.LENGTH_SHORT).show();
				} else {

					if (studentDAO.find(stu_idStr) != (Tb_student) null
							&& scoreDAO.find(stu_idStr, tea_idStr, cla_name) != (Tb_score) null) {
						Toast.makeText(AddStudent.this, "���и�ѧ����", Toast.LENGTH_SHORT).show();
					} else {

						if (scoreDAO.find(stu_idStr, tea_idStr, cla_name) == (Tb_score) null) {
							Tb_score tb_score = new Tb_score();
							tb_score.setStu_id(Integer.parseInt(stu_idStr)); // ����ѧ��

							if (studentDAO.find(stu_idStr) == (Tb_student) null) {
								tb_score.setStu_name(stu_name.getText().toString()); // ��������
							}

							else {
								tb_score.setStu_name(studentDAO.find(stu_idStr).getStu_name());
							}
							tb_score.setTea_id(Integer.parseInt(tea_idStr));

							TeacherDAO teacherDAO = new TeacherDAO(AddStudent.this);
							tb_score.setTea_name(teacherDAO.find(tea_idStr).getTea_name());
							tb_score.setCla_name(cla_name);
							scoreDAO.addStudent(tb_score);// ����ѧ����Ϣ
						}

						if (studentDAO.find(stu_idStr) == (Tb_student) null) {
							Tb_student tb_student = new Tb_student();
							tb_student.setStu_id(Integer.parseInt(stu_idStr)); // ����ѧ��
							tb_student.setStu_name(stu_name.getText().toString()); // ��������
							tb_student.setStu_password(stu_idStr); // ��������
							tb_student.setClassify(classify.getText().toString()); // ����ϵ��
							tb_student.setStu_phone(stu_phone.getText().toString());// ���õ绰
							tb_student.setStu_email(stu_email.getText().toString());// ��������
							studentDAO.add(tb_student);// ���ѧ����Ϣ
						}

						// ������Ϣ��ʾ
						Toast.makeText(AddStudent.this, "���ѧ���ɹ���", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {// Ϊ���ð�ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}
}
