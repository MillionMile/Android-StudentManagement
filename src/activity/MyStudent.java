package activity;

import java.util.List;

import com.example.studentmanagement.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dao.ScoreDAO;
import dao.StudentDAO;
import model.Tb_score;
import model.Tb_student;

public class MyStudent extends Activity {
	ListView lvinfo;// ����ListView����
	String tea_idStr;// �����ַ�������¼��ת����
	String cla_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);// ���ò����ļ�
		lvinfo = (ListView) findViewById(R.id.lvstudentinfo);// ��ȡ�����ļ��е�ListView���

		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		cla_name = bundle.getString("cla_name"); // ��ȡ���ݹ����Ŀγ̺�
		tea_idStr=bundle.getString("tea_id");
		ShowInfo();

		lvinfo.setOnItemClickListener(new OnItemClickListener(){// ΪListView�������¼�
			// ��дonItemClick����
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strInfo = String.valueOf(((TextView) view).getText());// ��¼ѧ����Ϣ
				String stu_id = strInfo.substring(strInfo.indexOf('��')+1, strInfo.indexOf("\n"));// ��ѧ����Ϣ�н�ȡ������
				Intent intent = new Intent(MyStudent.this, StudentInfo.class);// ����Intent����
				intent.putExtra("stu_id", stu_id);// ���ô�������
				intent.putExtra("cla_name", cla_name);// ���ô�������
				intent.putExtra("tea_id", tea_idStr);
				startActivity(intent);// ִ��Intent����
			}
		});
	}

	private void ShowInfo() {// �������ݴ���Ĺ������ͣ���ʾ��Ӧ����Ϣ
		String[] strInfos = null;// �����ַ������飬�����洢ѧ����Ϣ
		ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
		ScoreDAO scoreDAO = new ScoreDAO(MyStudent.this);// ����ScoreDAO����
		// ��ȡ����������Ϣ�����洢��List���ͼ�����
		List<Tb_score> listinfos = scoreDAO.getScrollCla_StuData(tea_idStr, cla_name,0, (int) scoreDAO.getCla_StuCount(tea_idStr,cla_name,"addedview"),"addedview");
		strInfos = new String[listinfos.size()];// �����ַ�������ĳ���
		
		int m = 0;// ����һ����ʼ��ʶ
		for (Tb_score tb_score : listinfos) {// ����List���ͼ���
			// �����������Ϣ��ϳ�һ���ַ������洢���ַ����������Ӧλ��
			strInfos[m] =  "ѧ�ţ�"+tb_score.getStu_id() + "\n" +"������"+ tb_score.getStu_name();
			m++;// ��ʶ��1
		}
		// ʹ���ַ��������ʼ��ArrayAdapter����
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// ΪListView�б���������Դ
	}

	 @Override
	 protected void onRestart() {
	 // TODO Auto-generated method stub
	 super.onRestart();// ʵ�ֻ����еķ���
	 ShowInfo();
	 }
}