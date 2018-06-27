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

public class StudentMainActivity extends Activity {
	String stu_idStr;
	GridView gvInfo;
	String[] titles = new String[] { "个人信息", "我的成绩单", "查看老师", "退出" };
	int[] images = new int[] { R.drawable.myinformation, R.drawable.myscore, R.drawable.mystudent, R.drawable.exit };

	// 创建数据包
	Bundle bundle_stuId = new Bundle();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.student);

		// 获取登录id-传递信息
		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		final String stu_id = bundle.getString("stu_id");

		// 数据包再次传递
		bundle_stuId.putString("stu_id", stu_id);

		gvInfo = (GridView) findViewById(R.id.gvInfo);
		pictureAdapter adapter = new pictureAdapter(titles, images, this);
		gvInfo.setAdapter(adapter);
		gvInfo.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent intent;
				switch (arg2) {
				case 0:
					intent = new Intent(StudentMainActivity.this, StuInfo.class);
					intent.putExtras(bundle_stuId);
					startActivity(intent);
					break;
				case 1:
					intent = new Intent(StudentMainActivity.this, StuPerScore.class);
					intent.putExtras(bundle_stuId);
					startActivity(intent);
					break;
				case 2:
					intent = new Intent(StudentMainActivity.this, TeaInfo.class);
					startActivity(intent);
					break;
				case 3:
					finish();
				}
			}
		});
		
		
		Toast.makeText(StudentMainActivity.this, "您已登录到学生端平台！", Toast.LENGTH_SHORT).show();
	}
}

class pictureAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<Picture> pictures;

	// 构造函数
	public pictureAdapter(String[] titles, int[] images, Context context) {
		super();
		pictures = new ArrayList<Picture>();
		inflater = LayoutInflater.from(context);
		// 遍历图像和数组
		for (int i = 0; i < images.length; i++) {
			Picture picture = new Picture(titles[i], images[i]);
			pictures.add(picture);
		}
	}

	@Override
	public int getCount() {
		if (null != pictures) {
			return pictures.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return pictures.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		ViewHolder viewHolder;
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.gvitem, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);
			viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);
			arg1.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) arg1.getTag();
		}
		viewHolder.title.setText(pictures.get(arg0).getTitle());
		viewHolder.image.setImageResource(pictures.get(arg0).getImageId());
		return arg1;
	}
}

class ViewHolder {
	public TextView title;
	public ImageView image;
}

class Picture {
	private String title;
	private int imageId;

	public Picture() {
		super();
	}

	public Picture(String title, int imageId) {
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public int getImageId() {
		return imageId;
	}

	public void setimageId(int imageId) {
		this.imageId = imageId;
	}
}
