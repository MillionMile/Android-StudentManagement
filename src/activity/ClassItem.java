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
	ListView lvinfo;// 创建ListView对象
	String tea_id;// 创建字符串，记录跳转类型

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);// 设置布局文件
		TextView title=(TextView)findViewById(R.id.title);
		title.setGravity(Gravity.LEFT);
		title.setText("请选择课程：");
		title.setTextSize(20);
		lvinfo = (ListView) findViewById(R.id.lvstudentinfo);// 获取布局文件中的ListView组件

		Intent intent = getIntent();// 创建Intent对象
		final Bundle bundle = intent.getExtras();// 获取传入的数据，并使用Bundle记录
		tea_id = bundle.getString("tea_id");// 将工号打包起来

		ShowInfo();// 调用自定义方法显示学生信息

		lvinfo.setOnItemClickListener(new OnItemClickListener() {
			// // 为ListView添加项单击事件
			// // 重写onItemClick方法
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// 记录课程名称
				String strCla = String.valueOf(((TextView) view).getText());
				Intent intent;
				// 从之前决定传送的activity
				switch (bundle.getString("type")) {
				case "studentScore":
					intent = new Intent(ClassItem.this, StudentScore.class);// 创建Intent对象
					intent.putExtra("cla_name", strCla);// 设置传递数据
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// 执行Intent操作
					break;
				case "addStudent":
					intent = new Intent(ClassItem.this, AddStudent.class);// 创建Intent对象
					intent.putExtra("cla_name", strCla);// 设置传递数据
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// 执行Intent操作
					break;
				case "myClass":
					intent = new Intent(ClassItem.this, ClassInfo.class);// 创建Intent对象
					intent.putExtra("cla_name", strCla);// 设置传递数据
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// 执行Intent操作
					break;
				case "addScore":
					intent = new Intent(ClassItem.this, ShowScore.class);// 创建Intent对象
					intent.putExtra("cla_name", strCla);// 设置传递数据
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// 执行Intent操作
					break;
				case "studentInformation":
					intent = new Intent(ClassItem.this, MyStudent.class);// 创建Intent对象
					intent.putExtra("cla_name", strCla);// 设置传递数据
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// 执行Intent操作
					break;
				case "chart":
					intent = new Intent(ClassItem.this, Chart.class);// 创建Intent对象
					intent.putExtra("cla_name", strCla);// 设置传递数据
					intent.putExtra("tea_id", tea_id);
					startActivity(intent);// 执行Intent操作
					break;
				}
			}
		});
	}

	private void ShowInfo() {// 用来根据传入的管理类型，显示相应的信息
		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		ScoreDAO scoreDAO = new ScoreDAO(ClassItem.this);// 创建ScoreDAO对象
		// 获取所有收入信息，并存储到List泛型集合中
		List<String> listinfos = scoreDAO.getScrollClaData(tea_id, 0, (int) scoreDAO.getClaCount(tea_id));
		// 使用字符串数组初始化ArrayAdapter对象
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listinfos);
		lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
	}
	
	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();// 实现基类中的方法
		ShowInfo();
	}
}
