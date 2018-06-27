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
		
		Intent intent=getIntent();	//��ȡIntent����
		Bundle bundle=intent.getExtras();		//��ȡ���ݵ����ݰ�
		tea_idStr=bundle.getString("tea_id");	//��ȡ���ݹ����Ĺ���
		
		
		teacherDAO=new TeacherDAO(TeacherInfo.this);
		
		tea_id.setText(tea_idStr);
		tea_name.setText(teacherDAO.find(tea_idStr).getTea_name());
		tea_password.setText(teacherDAO.find(tea_idStr).getTea_password());
		tea_phone.setText(teacherDAO.find(tea_idStr).getTea_phone());
		tea_email.setText(teacherDAO.find(tea_idStr).getTea_email());
		
		btn_save.setOnClickListener(new OnClickListener() {// Ϊ��¼��ť���ü����¼�
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_teacher tb_teacher = new Tb_teacher();// ����Tb_score����
				tb_teacher.setTea_id(Integer.parseInt(tea_idStr));	// ���ù���
				tb_teacher.setTea_name(tea_name.getText().toString()); //��������
				tb_teacher.setTea_phone(tea_phone.getText().toString());//���õ绰
				tb_teacher.setTea_email(tea_email.getText().toString());//��������
				tb_teacher.setTea_password(tea_password.getText().toString());//��������
				teacherDAO.update(tb_teacher);// ����ѧ����Ϣ
				
				ScoreDAO scoreDAO=new ScoreDAO(TeacherInfo.this);
				scoreDAO.updateTea(tea_idStr,tea_name.getText().toString());
				// ������Ϣ��ʾ
				Toast.makeText(TeacherInfo.this, "�����޸ĳɹ���", Toast.LENGTH_SHORT)
						.show();
			}
		});
		
		
		btn_reset.setOnClickListener(new OnClickListener() {// Ϊ��¼��ť���ü����¼�
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