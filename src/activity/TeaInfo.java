package activity;

import java.util.List;

import com.example.studentmanagement.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dao.TeacherDAO;
import model.Tb_teacher;

public class TeaInfo extends Activity {
	public static final String FLAG = "id";// ����һ��������������Ϊ������
	ListView lvinfo;// ����ListView����
	TextView title;
	String strType = "";// �����ַ�������¼��������
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		title=(TextView)findViewById(R.id.title);
		title.setText("��ʦ������Ϣ");
		lvinfo = (ListView)findViewById(R.id.lvstudentinfo);
		ShowInfo();// �����Զ��巽����ʾѧ����Ϣ
	}
	private void ShowInfo() {// �������ݴ���Ĺ������ͣ���ʾ��Ӧ����Ϣ
		String[] strInfos;// �����ַ������飬�����洢������Ϣ
		ArrayAdapter<String> arrayAdapter;// ����ArrayAdapter����
		TeacherDAO teacherDAO = new TeacherDAO(TeaInfo.this);// ����TeacherDAO����
		// ��ȡ����������Ϣ�����洢��List���ͼ�����
		List<Tb_teacher> listinfos = teacherDAO.getScrollData(0, (int) teacherDAO.getCount());
		strInfos = new String[listinfos.size()];// �����ַ�������ĳ���
		int m = 0;// ����һ����ʼ��ʶ
		for (Tb_teacher tb_teacher : listinfos) {// ����List���ͼ���
			// �����������Ϣ��ϳ�һ���ַ������洢���ַ����������Ӧλ��
			strInfos[m] = "����:"+tb_teacher.getTea_id() + "\n����: " + tb_teacher.getTea_name() +  "\n�ֻ�:" + tb_teacher.getTea_phone() + "\n����: " + tb_teacher.getTea_email();
			m++;// ��ʶ��1
		}
		// ʹ���ַ��������ʼ��ArrayAdapter����
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// ΪListView�б���������Դ
	}
}
