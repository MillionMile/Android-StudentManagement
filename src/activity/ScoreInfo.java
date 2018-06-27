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
import model.Tb_score;
import model.Tb_student;

public class ScoreInfo extends Activity {
	TextView TVstu_id;
	TextView TVstu_name;
	TextView TVcal_name;
	EditText ETscore;
	Button btn_save;
	Button btn_cancel;
	ScoreDAO scoreDAO;
	String stu_id;
	String cla_name;
	String tea_id;
	String scorestr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scoreinfo);// ���ò����ļ�
		TVstu_id = (TextView) findViewById(R.id.stu_id);
		TVstu_name = (TextView) findViewById(R.id.stu_name);
		TVcal_name = (TextView) findViewById(R.id.cla_name);
		ETscore = (EditText) findViewById(R.id.ETscore);
		btn_save = (Button) findViewById(R.id.save);
		btn_cancel = (Button) findViewById(R.id.cancel);

		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		stu_id = bundle.getString("stu_id"); // ��ȡ���ݹ�����ѧ��
		cla_name = bundle.getString("cla_name"); // ��ȡ���ݹ����Ŀγ̺�
		tea_id = bundle.getString("tea_id");
		TVstu_id.setText(stu_id);
		TVcal_name.setText(cla_name);
		// TVstu_name.setText(tea_id);
		scoreDAO = new ScoreDAO(ScoreInfo.this);
		TVstu_name.setText(scoreDAO.find(stu_id, tea_id, cla_name).getStu_name());
		ETscore.setText(String.valueOf(scoreDAO.find(stu_id, tea_id, cla_name).getScore()));
		
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Tb_score tb_score = new Tb_score();// ����Tb_score����
				tb_score.setScore_id(scoreDAO.find(stu_id, tea_id, cla_name).getScore_id());// ����ѧ��
				tb_score.setStu_id(Integer.parseInt(stu_id)); // ����ѧ��
				tb_score.setStu_name(scoreDAO.find(stu_id, tea_id, cla_name).getStu_name());
				tb_score.setTea_id(Integer.parseInt(tea_id));
				tb_score.setTea_name(scoreDAO.find(stu_id, tea_id, cla_name).getTea_name());
				tb_score.setCla_name(cla_name);
				scorestr=ETscore.getText().toString();
				if (scorestr.isEmpty()) {
					tb_score.setScore(0.0);
				} else {
					tb_score.setScore(Double.parseDouble(ETscore.getText().toString()));
				}
				if ((tb_score.getScore() >= 0)&& (tb_score.getScore()<= 100)) {
					scoreDAO.updateScore(tb_score);// ����ѧ����Ϣ
					Toast.makeText(ScoreInfo.this, "�ɼ��޸ĳɹ���", Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(ScoreInfo.this, "����Ӧ��0-100֮�䣡", Toast.LENGTH_SHORT).show();
				}
				// ������Ϣ��ʾ

			}
		});

		btn_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
