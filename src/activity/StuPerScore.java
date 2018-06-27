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
	public static final String FLAG = "id";// 定义一个常量，用来作为请求码
	ListView lvinfo;// 创建ListView对象
	String stu_idStr;
	TextView stu_name, cla_name, score, stu_id;
	Double scoredoule;
	ScoreDAO scoreDAO;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stu_perscore);

//		// 获取表单数据
		stu_name = (TextView) findViewById(R.id.stu_name);
		stu_id = (TextView) findViewById(R.id.stu_id);
//
//		// 获取登录id的学生成绩
		Bundle intent = this.getIntent().getExtras(); // 获取Intent对象
		stu_idStr = intent.getString("stu_id");
		stu_id.setText(stu_idStr);
		lvinfo=(ListView)findViewById(R.id.lvstudentinfo);
		scoreDAO=new ScoreDAO(StuPerScore.this);
		StudentDAO studentDAO=new StudentDAO(StuPerScore.this);
		stu_name.setText(studentDAO.find(stu_idStr).getStu_name());
		if(scoreDAO.findS(stu_idStr)==(Tb_score)null)
		{
			Toast.makeText(StuPerScore.this, "暂时无您的成绩信息", Toast.LENGTH_SHORT).show();
		}
		else{
		stu_name.setText(scoreDAO.findS(stu_idStr).getStu_name());
		ShowInfo();
		}
	}

	private void ShowInfo() {// 用来根据传入的管理类型，显示相应的信息
		String[] strInfos = null;// 定义字符串数组，用来存储信息
		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		ScoreDAO scoreDAO = new ScoreDAO(StuPerScore.this);// 创建ScoreDAO对象
		// 获取所有收入信息，并存储到List泛型集合中
		List<Tb_score> listinfos = scoreDAO.getScrollStuData(stu_idStr);
		strInfos = new String[listinfos.size()];// 设置字符串数组的长度
		int m = 0;// 定义一个开始标识
		for (Tb_score tb_score : listinfos) {// 遍历List泛型集合
			// 将收入相关信息组合成一个字符串，存储到字符串数组的相应位置
			strInfos[m] = "科目：" + tb_score.getCla_name() + "\n" + "教师：" + tb_score.getTea_name() + "\n" + "分数："
					+ tb_score.getScore();
			m++;// 标识加1
		}
		// 使用字符串数组初始化ArrayAdapter对象
		arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strInfos);
		lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
	}
}
