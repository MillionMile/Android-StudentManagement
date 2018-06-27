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
	EditText ETuserid, ETpassword;// ����EditText����
	Button btnlogin, btnclose; // ��������Button����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);// ���ò����ļ�

		ETuserid = (EditText) findViewById(R.id.userid);// ��ȡ�˻��ı���
		ETpassword = (EditText) findViewById(R.id.password);// ��ȡ�����ı���
		btnlogin = (Button) findViewById(R.id.login);// ��ȡ��¼��ť
		btnclose = (Button) findViewById(R.id.close);// ��ȡȡ����ť
		btnlogin.setOnClickListener(new OnClickListener() {// Ϊ��¼��ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ITteacher = new Intent(Login.this, TeacherMainActivity.class);// ����Intent��teacher����
				Intent ITstudent = new Intent(Login.this, StudentMainActivity.class);// ����Intent��student����
				StudentDAO studentDAO = new StudentDAO(Login.this);// ����StudentDAO����
				TeacherDAO teacherDAO = new TeacherDAO(Login.this);// ����TeacherDAO����
				String useridStr = ETuserid.getText().toString();
				String passwordStr = ETpassword.getText().toString();
				// �ж��Ƿ��������˺�
				if (useridStr.isEmpty()) {
					// ������Ϣ��ʾ
					Toast.makeText(Login.this, "��������ȷ���˻�����", Toast.LENGTH_SHORT).show();
				} else {
					if (teacherDAO.find(useridStr) == (Tb_teacher) null) { // ����ʦ����û���û�����ѧ�����в����û�
						if (studentDAO.find(useridStr) == (Tb_student) null) { // ��ѧ������Ҳû���û�
							Toast.makeText(Login.this, "û�е�ǰ�˻���", Toast.LENGTH_SHORT).show();
						} else {// ��ѧ�������ҵ��û�
								// �ж�����������Ƿ������ݿ��е�����һ��
							if (studentDAO.find(useridStr).getStu_password().equals(passwordStr)) {
								Bundle bundle = new Bundle(); // ����һ��Bundle�Ķ���bundle
								bundle.putString("stu_id", useridStr); // ����ѧ��
								ITstudent.putExtras(bundle); // �����ݰ���ӵ�intent������
								startActivity(ITstudent);// ������Activity
							}
							// �������
							else {
								// ������Ϣ��ʾ
								Toast.makeText(Login.this, "��������ȷ���˻��������룡", Toast.LENGTH_SHORT).show();
							}
						}
					} else { // �ڽ�ʦ���ҵ��û�
						// �ж�����������Ƿ������ݿ��е�����һ��
						if (teacherDAO.find(useridStr).getTea_password().equals(passwordStr)) {
							Bundle bundle = new Bundle(); // ����һ��Bundle�Ķ���bundle
							bundle.putString("tea_id", useridStr); // ���湤��
							ITteacher.putExtras(bundle); // �����ݰ���ӵ�intent������
							startActivity(ITteacher);// ������Activity
						} else {
							// ������Ϣ��ʾ
							Toast.makeText(Login.this, "��������ȷ���˻��������룡", Toast.LENGTH_SHORT).show();
						}
					}
					ETuserid.setText("");// ����˻��ı���
					ETpassword.setText("");// ��������ı���
				}
			}
		});

		btnclose.setOnClickListener(new OnClickListener() {// Ϊȡ����ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();// �˳���ǰ����
			}
		});

		TeacherDAO teacher = new TeacherDAO(Login.this);
		if (teacher.find("1585") == (Tb_teacher) null) {
			Tb_teacher tb_teacher1 = new Tb_teacher(1585, "������", "1585", "13452958203", "zlf@scse.com.cn");
			Tb_teacher tb_teacher2 = new Tb_teacher(1581, "������", "1581", "15829384900", "gwm@scse.com.cn");
			Tb_teacher tb_teacher3 = new Tb_teacher(1090, "����̨", "1090", "15503920472", "qzt@scse.com.cn");
			Tb_teacher tb_teacher4 = new Tb_teacher(1708, "��Сƽ", "1708", "18792847265", "hxp@scse.com.cn");
			Tb_teacher tb_teacher5 = new Tb_teacher(1813, "����", "1813", "14356683921", "lm@scse.com.cn");
			teacher.add(tb_teacher1);
			teacher.add(tb_teacher2);
			teacher.add(tb_teacher3);
			teacher.add(tb_teacher4);
			teacher.add(tb_teacher5);
		}
	}
}
