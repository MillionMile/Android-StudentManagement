package activity;

import java.util.List;

import com.example.studentmanagement.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dao.ScoreDAO;

public class ClassItem extends Activity {
	ListView lvinfo;// ����ListView����
	String tea_id;// �����ַ�������¼��ת����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);// ���ò����ļ�
		TextView title=(TextView)findViewById(R.id.title);
		title.setGravity(Gravity.LEFT);
		title.setText("��ѡ��γ̣�");
		title.setTextSize(20);
		lvinfo = (ListView) findViewById(R.id.lvstudentinfo);// ��ȡ�����ļ��е�ListView���

		Intent intent = getIntent();// ����Intent����
		final Bundle bundle = intent.getExtras();// ��ȡ��������ݣ���ʹ��Bundle��¼
		tea_id = bundle.getString("tea_id");// �����Ŵ������

		ShowInfo();// �����Զ��巽����ʾѧ����Ϣ

		lvinfo.setOnItemClickListener(new OnItemClickListener() {
			// // ΪListView�������¼�
			// // ��дonItemClick����
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// ��¼�γ�����
				String strCla = String.valueOf(((TextView) view).getText());
				Intent intent;
				// ��֮ǰ�������͵�activity
				switch (bundle.getString("type")) {
				case "studentScore":
					intent = new Intent(ClassItem.this, StudentScore.class);// ����Intent����
					intent.putExtra("cla_name", strCla);// ���ô�������
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// ִ��Intent����
					break;
				case "addStudent":
					intent = new Intent(ClassItem.this, AddStudent.class);// ����Intent����
					intent.putExtra("cla_name", strCla);// ���ô�������
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// ִ��Intent����
					break;
				case "myClass":
					intent = new Intent(ClassItem.this, ClassInfo.class);// ����Intent����
					intent.putExtra("cla_name", strCla);// ���ô�������
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// ִ��Intent����
					break;
				case "addScore":
					intent = new Intent(ClassItem.this, ShowScore.class);// ����Intent����
					intent.putExtra("cla_name", strCla);// ���ô�������
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// ִ��Intent����
					break;
				case "studentInformation":
					intent = new Intent(ClassItem.this, MyStudent.class);// ����Intent����
					intent.putExtra("cla_name", strCla);// ���ô�������
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// ִ��Intent����
					break;
				case "chart":
					intent = new Intent(ClassItem.this, Chart.class);// ����Intent����
					intent.putExtra("cla_name", strCla);// ���ô�������
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// ִ��Intent����
					break;
				}
			}
		});
	}

	private void ShowInfo() {// �������ݴ���Ĺ������ͣ���ʾ��Ӧ����Ϣ
		ArrayAdapter<String> arrayAdapter = null;// ����ArrayAdapter����
		ScoreDAO scoreDAO = new ScoreDAO(ClassItem.this);// ����ScoreDAO����
		// ��ȡ����������Ϣ�����洢��List���ͼ�����
		List<String> listinfos = scoreDAO.getScrollClaData(tea_id, 0, (int) scoreDAO.getClaCount(tea_id));
		// ʹ���ַ��������ʼ��ArrayAdapter����
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listinfos);
		lvinfo.setAdapter(arrayAdapter);// ΪListView�б���������Դ
	}
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();// ʵ�ֻ����еķ���
		ShowInfo();
	}
}
