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

	String yuanCla_nameStr;// �����ַ�������¼��ת����
	String tea_idStr;
	EditText ETcla_name;
Button btn_save;
	Button btn_delete;
	ScoreDAO scoreDAO;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classinfo);// ���ò����ļ�

		ETcla_name=(EditText)findViewById(R.id.ETcla_name);
		btn_save=(Button)findViewById(R.id.save);
		btn_delete=(Button)findViewById(R.id.delete);		
		scoreDAO = new ScoreDAO(ClassInfo.this);
		
		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		yuanCla_nameStr = bundle.getString("cla_name"); // ��ȡ���ݹ����Ŀγ̺�
		tea_idStr=bundle.getString("tea_id");
		
		ETcla_name.setText(yuanCla_nameStr);
		
		
		btn_save.setOnClickListener(new OnClickListener() {// Ϊ��¼��ť���ü����¼�
			@Override
			public void onClick(View v) {
				
				;
				// TODO Auto-generated method stub
				Tb_score tb_score = new Tb_score();// ����tb_student����
				tb_score.setCla_name(ETcla_name.getText().toString());// ���ÿγ���
				tb_score.setTea_id(Integer.parseInt(tea_idStr)); // ��������
				tb_score.setTea_name(""); // ����ϵ��
				scoreDAO.updateCla(tb_score,yuanCla_nameStr);// ����ѧ����Ϣ
				// ������Ϣ��ʾ
				Toast.makeText(ClassInfo.this, "�γ������޸ĳɹ���", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		btn_delete.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_score tb_score = new Tb_score();// ����tb_student����
				tb_score.setCla_name(ETcla_name.getText().toString());// ���ÿγ���
				tb_score.setTea_id(Integer.parseInt(tea_idStr)); // ��������
				tb_score.setTea_name(""); // ����ϵ��
				scoreDAO.deleteCla(tb_score,yuanCla_nameStr);// ����ѧ����Ϣ
				// ������Ϣ��ʾ
				Toast.makeText(ClassInfo.this, "ɾ���γ̳ɹ���", Toast.LENGTH_SHORT).show();
			}
		});
		
	}

}
