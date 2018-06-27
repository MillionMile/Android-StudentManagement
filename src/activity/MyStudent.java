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
	ListView lvinfo;// 创建ListView对象
	String tea_idStr;// 创建字符串，记录跳转类型
	String cla_name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);// 设置布局文件
		lvinfo = (ListView) findViewById(R.id.lvstudentinfo);// 获取布局文件中的ListView组件

		Intent intent = getIntent(); // 获取Intent对象
		Bundle bundle = intent.getExtras(); // 获取传递的数据包
		cla_name = bundle.getString("cla_name"); // 获取传递过来的课程号
		tea_idStr=bundle.getString("tea_id");
		ShowInfo();

		lvinfo.setOnItemClickListener(new OnItemClickListener(){// 为ListView添加项单击事件
			// 重写onItemClick方法
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strInfo = String.valueOf(((TextView) view).getText());// 记录学生信息
				String stu_id = strInfo.substring(strInfo.indexOf('：')+1, strInfo.indexOf("\n"));// 从学生信息中截取收入编号
				Intent intent = new Intent(MyStudent.this, StudentInfo.class);// 创建Intent对象
				intent.putExtra("stu_id", stu_id);// 设置传递数据
				intent.putExtra("cla_name", cla_name);// 设置传递数据
				intent.putExtra("tea_id", tea_idStr);
				startActivity(intent);// 执行Intent操作
			}
		});
	}

	private void ShowInfo() {// 用来根据传入的管理类型，显示相应的信息
		String[] strInfos = null;// 定义字符串数组，用来存储学生信息
		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		ScoreDAO scoreDAO = new ScoreDAO(MyStudent.this);// 创建ScoreDAO对象
		// 获取所有收入信息，并存储到List泛型集合中
		List<Tb_score> listinfos = scoreDAO.getScrollCla_StuData(tea_idStr, cla_name,0, (int) scoreDAO.getCla_StuCount(tea_idStr,cla_name,"addedview"),"addedview");
		strInfos = new String[listinfos.size()];// 设置字符串数组的长度
		
		int m = 0;// 定义一个开始标识
		for (Tb_score tb_score : listinfos) {// 遍历List泛型集合
			// 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
			strInfos[m] =  "学号："+tb_score.getStu_id() + "\n" +"姓名："+ tb_score.getStu_name();
			m++;// 标识加1
		}
		// 使用字符串数组初始化ArrayAdapter对象
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
	}

	 @Override
	 protected void onRestart() {
	 // TODO Auto-generated method stub
	 super.onRestart();// 实现基类中的方法
	 ShowInfo();
	 }
}