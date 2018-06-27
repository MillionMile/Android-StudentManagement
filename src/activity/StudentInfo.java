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
import dao.StudentDAO;
import model.Tb_student;

public class StudentInfo extends Activity {
	TextView stu_id;
	EditText stu_name;
	EditText classify;
	EditText stu_password;
	EditText stu_phone;
	EditText stu_email;
	Button btn_save;
	Button btn_delete;
	Button btn_reset;
	StudentDAO studentDAO;
	ScoreDAO scoreDAO;
	String stu_idStr;
	String tea_id;
	String cla_name;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.studentinformation);

		stu_id = (TextView) findViewById(R.id.TVstu_id);
		stu_name = (EditText) findViewById(R.id.ETstu_name);
		stu_password = (EditText) findViewById(R.id.ETstu_password);
		classify = (EditText) findViewById(R.id.ETclassify);
		stu_phone = (EditText) findViewById(R.id.ETstu_phone);
		stu_email = (EditText) findViewById(R.id.ETstu_email);
		btn_save = (Button) findViewById(R.id.save);
		btn_delete = (Button) findViewById(R.id.delete);
		btn_reset = (Button) findViewById(R.id.reset);

		stu_password.setVisibility(View.GONE);
		findViewById(R.id.textView15).setVisibility(View.GONE);
		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		stu_idStr = bundle.getString("stu_id"); // ��ȡ���ݹ�����ѧ��
		tea_id = bundle.getString("tea_id");
		cla_name = bundle.getString("cla_name");

		studentDAO = new StudentDAO(StudentInfo.this);

		stu_id.setText(stu_idStr);
		stu_name.setText(studentDAO.find(stu_idStr).getStu_name());
		classify.setText(studentDAO.find(stu_idStr).getClassify());
		stu_phone.setText(studentDAO.find(stu_idStr).getStu_phone());
		stu_email.setText(studentDAO.find(stu_idStr).getStu_email());

		btn_save.setOnClickListener(new OnClickListener() {// Ϊ��¼��ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_student tb_student = new Tb_student();// ����tb_student����
				tb_student.setStu_id(Integer.parseInt(stu_idStr)); // ����ѧ��
				tb_student.setStu_name(stu_name.getText().toString()); // ��������
				tb_student.setClassify(classify.getText().toString()); // ����ϵ��
				tb_student.setStu_phone(stu_phone.getText().toString());// ���õ绰
				tb_student.setStu_email(stu_email.getText().toString());// ��������
				tb_student.setStu_password(studentDAO.find(stu_idStr).getStu_password());// ��������

				scoreDAO = new ScoreDAO(StudentInfo.this);
				scoreDAO.updateStudent(stu_idStr, stu_name.getText().toString());
				studentDAO.update(tb_student);// ����ѧ����Ϣ

				// ������Ϣ��ʾ
				Toast.makeText(StudentInfo.this, "�����޸ĳɹ���", Toast.LENGTH_SHORT).show();
			}
		});

		btn_reset.setOnClickListener(new OnClickListener() {// Ϊ���ð�ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				stu_name.setText(studentDAO.find(stu_idStr).getStu_name());
				classify.setText(studentDAO.find(stu_idStr).getClassify());
				stu_phone.setText(studentDAO.find(stu_idStr).getStu_phone());
				stu_email.setText(studentDAO.find(stu_idStr).getStu_email());
			}
		});

		btn_delete.setOnClickListener(new OnClickListener() {// Ϊ���ð�ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = getIntent(); // ��ȡIntent����
				Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�

				scoreDAO = new ScoreDAO(StudentInfo.this);
				scoreDAO.deleteClaStudent(stu_idStr, tea_id,cla_name);
				Toast.makeText(StudentInfo.this, "ɾ��ѧ���γ̳ɹ�", Toast.LENGTH_SHORT).show();
			}
		});

	}
}
