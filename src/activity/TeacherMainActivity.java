package activity;

import java.util.ArrayList;
import java.util.List;

import com.example.studentmanagement.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherMainActivity extends Activity {
	GridView gvInfo;// 创建GridView对象
	// 定义字符串数组，存储系统功能
	String[] titles = new String[] { "学生成绩", "学生信息", "增加学生", "我的课程", "增加课程", "成绩录入", "成绩汇总", "我的信息", "退出系统" };
	// 定义int数组，存储功能对应的图标
	int[] images = new int[] { R.drawable.myscore, R.drawable.mystudent, R.drawable.addstudent, R.drawable.myclass,
			R.drawable.addclass, R.drawable.addscore, R.drawable.scoretotal, R.drawable.myinformation,
			R.drawable.exit };

	Bundle bundle2 = new Bundle();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher);

		Intent intent = getIntent(); // 获取Intent对象
		Bundle bundle = intent.getExtras(); // 获取传递的数据包
		final String tea_id = bundle.getString("tea_id"); // 获取传递过来的工号

		// 创建传递的数据包
		bundle2.putString("tea_id", tea_id);// 将工号打包起来

		gvInfo = (GridView) findViewById(R.id.gvInfo);// 获取布局文件中的gvInfo组件
		pictureAdapter adapter = new pictureAdapter(titles, images, this);// 创建pictureAdapter对象
		gvInfo.setAdapter(adapter);// 为GridView设置数据源
		gvInfo.setOnItemClickListener(new OnItemClickListener() {// 为GridView设置项单击事件
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = null;// 创建Intent对象
				switch (arg2) {
				case 0:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// 使用AddOutaccount窗口初始化Intent
					intent.putExtra("type", "studentScore");
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开ClassItem，后跳到对应学生成绩
					break;
				case 1:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// 使用AddOutaccount窗口初始化Intent
					intent.putExtra("type", "studentInformation");
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开student，后跳到对应班级学生信息
					break;

				case 2:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// 使用AddOutaccount窗口初始化Intent
					intent.putExtra("type", "addStudent");
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开ClassItem，后跳到增加学生
					break;
				case 3:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// 使用AddOutaccount窗口初始化Intent
					intent.putExtra("type", "myClass");
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开ClassItem，后跳到修改课程
					break;
				case 4:
					intent = new Intent(TeacherMainActivity.this, AddClass.class);// 使用Showinfo窗口初始化Intent
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开增加课程
					break;
				case 5:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// 使用AddOutaccount窗口初始化Intent
					intent.putExtra("type", "addScore");
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开ClassItem-后选择学生showscore，进入成绩录入
					break;
				case 6:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// 使用AddOutaccount窗口初始化Intent
					intent.putExtra("type", "chart");
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开ClassItem，后跳到成绩汇总
					break;
				case 7:
					intent = new Intent(TeacherMainActivity.this, TeacherInfo.class);// 使用AddOutaccount窗口初始化Intent
					intent.putExtras(bundle2); // 将数据包添加到intent对象中
					startActivity(intent);// 打开我的信息
					break;
				case 8:
					finish();// 关闭当前Activity
					break;
				}
			}
		});

		Toast.makeText(TeacherMainActivity.this, "您已登录到教师端平台！", Toast.LENGTH_SHORT).show();
	}

	class pictureAdapter extends BaseAdapter {// 创建基于BaseAdapter的子类
		private LayoutInflater inflater;// 创建LayoutInflater对象
		private List<Picture> pictures;// 创建List泛型集合

		// 为类创建构造函数
		public pictureAdapter(String[] titles, int[] images, Context context) {
			super();
			pictures = new ArrayList<Picture>();// 初始化泛型集合对象
			inflater = LayoutInflater.from(context);// 初始化LayoutInflater对象
			for (int i = 0; i < images.length; i++) {// 遍历图像数组
				Picture picture = new Picture(titles[i], images[i]);// 使用标题和图像生成Picture对象
				pictures.add(picture);// 将Picture对象添加到泛型集合中
			}
		}

		@Override
		public int getCount() {// 获取泛型集合的长度
			if (null != pictures) {// 如果泛型集合不为空
				return pictures.size();// 返回泛型长度
			} else {
				return 0;// 返回0
			}
		}

		@Override
		public Object getItem(int arg0) {
			return pictures.get(arg0);// 获取泛型集合指定索引处的项
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;// 返回泛型集合的索引
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ViewHolder viewHolder;// 创建ViewHolder对象
			if (arg1 == null) {// 判断图像标识是否为空
				arg1 = inflater.inflate(R.layout.gvitem, null);// 设置图像标识
				viewHolder = new ViewHolder();// 初始化ViewHolder对象
				viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);// 设置图像标题
				viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);// 设置图像的二进制值
				arg1.setTag(viewHolder);// 设置提示
			} else {
				viewHolder = (ViewHolder) arg1.getTag();// 设置提示
			}
			viewHolder.title.setText(pictures.get(arg0).getTitle());// 设置图像标题
			viewHolder.image.setImageResource(pictures.get(arg0).getImageId());// 设置图像的二进制值
			return arg1;// 返回图像标识
		}
	}

	class ViewHolder {// 创建ViewHolder类
		public TextView title;// 创建TextView对象
		public ImageView image;// 创建ImageView对象
	}

	class Picture {// 创建Picture类
		private String title;// 定义字符串，表示图像标题
		private int imageId;// 定义int变量，表示图像的二进制值

		public Picture() {// 默认构造函数
			super();
		}

		public Picture(String title, int imageId) {// 定义有参构造函数
			super();
			this.title = title;// 为图像标题赋值
			this.imageId = imageId;// 为图像的二进制值赋值
		}

		public String getTitle() {// 定义图像标题的可读属性
			return title;
		}

		public void setTitle(String title) {// 定义图像标题的可写属性
			this.title = title;
		}

		public int getImageId() {// 定义图像二进制值的可读属性
			return imageId;
		}

		public void setimageId(int imageId) {// 定义图像二进制值的可写属性
			this.imageId = imageId;
		}
	}
}