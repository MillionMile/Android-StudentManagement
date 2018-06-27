package activity;

import java.util.List;

import com.example.studentmanagement.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import dao.ScoreDAO;
import dao.StudentDAO;
import model.Tb_score;

public class StuPerScore extends Activity {
	public static final String FLAG = "id";// ����һ��������������Ϊ������
	ListView lvinfo;// ����ListView����
	String stu_idStr;
	TextView stu_name, cla_name, score, stu_id;
	Double scoredoule;
	ScoreDAO scoreDAO;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stu_perscore);

//		// ��ȡ������
		stu_name = (TextView) findViewById(R.id.stu_name);
		stu_id = (TextView) findViewById(R.id.stu_id);
//
//		// ��ȡ��¼id��ѧ���ɼ�
		Bundle intent = this.getIntent().getExtras(); // ��ȡIntent����
		stu_idStr = intent.getString("stu_id");
		stu_id.setText(stu_idStr);
		lvinfo=(ListView)findViewById(R.id.lvstudentinfo);
		scoreDAO=new ScoreDAO(StuPerScore.this);
		StudentDAO studentDAO=new StudentDAO(StuPerScore.this);
		stu_name.setText(studentDAO.find(stu_idStr).getStu_name());
		if(scoreDAO.findS(stu_idStr)==(Tb_score)null)
		{
			Toast.makeText(StuPerScore.this, "��ʱ�����ĳɼ���Ϣ", Toast.LENGTH_SHORT).show();
		}
		else{
		stu_name.setText(scoreDAO.findS(stu_idStr).getStu_name());
		ShowInfo();
		}
	}

	private void ShowInfo() {// �������ݴ���Ĺ������ͣ���ʾ��Ӧ����Ϣ
		String[] strInfos = null;// �����ַ������飬�����洢��Ϣ
		ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
		ScoreDAO scoreDAO = new ScoreDAO(StuPerScore.this);// ����ScoreDAO����
		// ��ȡ����������Ϣ�����洢��List���ͼ�����
		List<Tb_score> listinfos = scoreDAO.getScrollStuData(stu_idStr);
		strInfos = new String[listinfos.size()];// �����ַ�������ĳ���
		int m = 0;// ����һ����ʼ��ʶ
		for (Tb_score tb_score : listinfos) {// ����List���ͼ���
			// �����������Ϣ��ϳ�һ���ַ������洢���ַ����������Ӧλ��
			strInfos[m] = "��Ŀ��" + tb_score.getCla_name() + "\n" + "��ʦ��" + tb_score.getTea_name() + "\n" + "������"
					+ tb_score.getScore();
			m++;// ��ʶ��1
		}
		// ʹ���ַ��������ʼ��ArrayAdapter����
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// ΪListView�б���������Դ
	}
}
