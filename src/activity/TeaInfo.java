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
	public static final String FLAG = "id";// 定义一个常量，用来作为请求码
	ListView lvinfo;// 创建ListView对象
	TextView title;
	String strType = "";// 创建字符串，记录管理类型
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		title=(TextView)findViewById(R.id.title);
		title.setText("老师个人信息");
		lvinfo = (ListView)findViewById(R.id.lvstudentinfo);
		ShowInfo();// 调用自定义方法显示学生信息
	}
	private void ShowInfo() {// 用来根据传入的管理类型，显示相应的信息
		String[] strInfos;// 定义字符串数组，用来存储收入信息
		ArrayAdapter<String> arrayAdapter;// 创建ArrayAdapter对象
		TeacherDAO teacherDAO = new TeacherDAO(TeaInfo.this);// 创建TeacherDAO对象
		// 获取所有收入信息，并存储到List泛型集合中
		List<Tb_teacher> listinfos = teacherDAO.getScrollData(0, (int) teacherDAO.getCount());
		strInfos = new String[listinfos.size()];// 设置字符串数组的长度
		int m = 0;// 定义一个开始标识
		for (Tb_teacher tb_teacher : listinfos) {// 遍历List泛型集合
			// 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
			strInfos[m] = "工号:"+tb_teacher.getTea_id() + "\n姓名: " + tb_teacher.getTea_name() +  "\n手机:" + tb_teacher.getTea_phone() + "\n邮箱: " + tb_teacher.getTea_email();
			m++;// 标识加1
		}
		// 使用字符串数组初始化ArrayAdapter对象
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
	}
}
