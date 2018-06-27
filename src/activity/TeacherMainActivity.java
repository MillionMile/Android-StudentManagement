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
	GridView gvInfo;// ����GridView����
	// �����ַ������飬�洢ϵͳ����
	String[] titles = new String[] { "ѧ���ɼ�", "ѧ����Ϣ", "����ѧ��", "�ҵĿγ�", "���ӿγ�", "�ɼ�¼��", "�ɼ�����", "�ҵ���Ϣ", "�˳�ϵͳ" };
	// ����int���飬�洢���ܶ�Ӧ��ͼ��
	int[] images = new int[] { R.drawable.myscore, R.drawable.mystudent, R.drawable.addstudent, R.drawable.myclass,
			R.drawable.addclass, R.drawable.addscore, R.drawable.scoretotal, R.drawable.myinformation,
			R.drawable.exit };

	Bundle bundle2 = new Bundle();

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teacher);

		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		final String tea_id = bundle.getString("tea_id"); // ��ȡ���ݹ����Ĺ���

		// �������ݵ����ݰ�
		bundle2.putString("tea_id", tea_id);// �����Ŵ������

		gvInfo = (GridView) findViewById(R.id.gvInfo);// ��ȡ�����ļ��е�gvInfo���
		pictureAdapter adapter = new pictureAdapter(titles, images, this);// ����pictureAdapter����
		gvInfo.setAdapter(adapter);// ΪGridView��������Դ
		gvInfo.setOnItemClickListener(new OnItemClickListener() {// ΪGridView��������¼�
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = null;// ����Intent����
				switch (arg2) {
				case 0:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					intent.putExtra("type", "studentScore");
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// ��ClassItem����������Ӧѧ���ɼ�
					break;
				case 1:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					intent.putExtra("type", "studentInformation");
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// ��student����������Ӧ�༶ѧ����Ϣ
					break;

				case 2:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					intent.putExtra("type", "addStudent");
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// ��ClassItem������������ѧ��
					break;
				case 3:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					intent.putExtra("type", "myClass");
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// ��ClassItem���������޸Ŀγ�
					break;
				case 4:
					intent = new Intent(TeacherMainActivity.this, AddClass.class);// ʹ��Showinfo���ڳ�ʼ��Intent
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// �����ӿγ�
					break;
				case 5:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					intent.putExtra("type", "addScore");
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// ��ClassItem-��ѡ��ѧ��showscore������ɼ�¼��
					break;
				case 6:
					intent = new Intent(TeacherMainActivity.this, ClassItem.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					intent.putExtra("type", "chart");
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// ��ClassItem���������ɼ�����
					break;
				case 7:
					intent = new Intent(TeacherMainActivity.this, TeacherInfo.class);// ʹ��AddOutaccount���ڳ�ʼ��Intent
					intent.putExtras(bundle2); // �����ݰ���ӵ�intent������
					startActivity(intent);// ���ҵ���Ϣ
					break;
				case 8:
					finish();// �رյ�ǰActivity
					break;
				}
			}
		});

		Toast.makeText(TeacherMainActivity.this, "���ѵ�¼����ʦ��ƽ̨��", Toast.LENGTH_SHORT).show();
	}

	class pictureAdapter extends BaseAdapter {// ��������BaseAdapter������
		private LayoutInflater inflater;// ����LayoutInflater����
		private List<Picture> pictures;// ����List���ͼ���

		// Ϊ�ഴ�����캯��
		public pictureAdapter(String[] titles, int[] images, Context context) {
			super();
			pictures = new ArrayList<Picture>();// ��ʼ�����ͼ��϶���
			inflater = LayoutInflater.from(context);// ��ʼ��LayoutInflater����
			for (int i = 0; i < images.length; i++) {// ����ͼ������
				Picture picture = new Picture(titles[i], images[i]);// ʹ�ñ����ͼ������Picture����
				pictures.add(picture);// ��Picture������ӵ����ͼ�����
			}
		}

		@Override
		public int getCount() {// ��ȡ���ͼ��ϵĳ���
			if (null != pictures) {// ������ͼ��ϲ�Ϊ��
				return pictures.size();// ���ط��ͳ���
			} else {
				return 0;// ����0
			}
		}

		@Override
		public Object getItem(int arg0) {
			return pictures.get(arg0);// ��ȡ���ͼ���ָ������������
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;// ���ط��ͼ��ϵ�����
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			ViewHolder viewHolder;// ����ViewHolder����
			if (arg1 == null) {// �ж�ͼ���ʶ�Ƿ�Ϊ��
				arg1 = inflater.inflate(R.layout.gvitem, null);// ����ͼ���ʶ
				viewHolder = new ViewHolder();// ��ʼ��ViewHolder����
				viewHolder.title = (TextView) arg1.findViewById(R.id.ItemTitle);// ����ͼ�����
				viewHolder.image = (ImageView) arg1.findViewById(R.id.ItemImage);// ����ͼ��Ķ�����ֵ
				arg1.setTag(viewHolder);// ������ʾ
			} else {
				viewHolder = (ViewHolder) arg1.getTag();// ������ʾ
			}
			viewHolder.title.setText(pictures.get(arg0).getTitle());// ����ͼ�����
			viewHolder.image.setImageResource(pictures.get(arg0).getImageId());// ����ͼ��Ķ�����ֵ
			return arg1;// ����ͼ���ʶ
		}
	}

	class ViewHolder {// ����ViewHolder��
		public TextView title;// ����TextView����
		public ImageView image;// ����ImageView����
	}

	class Picture {// ����Picture��
		private String title;// �����ַ�������ʾͼ�����
		private int imageId;// ����int��������ʾͼ��Ķ�����ֵ

		public Picture() {// Ĭ�Ϲ��캯��
			super();
		}

		public Picture(String title, int imageId) {// �����вι��캯��
			super();
			this.title = title;// Ϊͼ����⸳ֵ
			this.imageId = imageId;// Ϊͼ��Ķ�����ֵ��ֵ
		}

		public String getTitle() {// ����ͼ�����Ŀɶ�����
			return title;
		}

		public void setTitle(String title) {// ����ͼ�����Ŀ�д����
			this.title = title;
		}

		public int getImageId() {// ����ͼ�������ֵ�Ŀɶ�����
			return imageId;
		}

		public void setimageId(int imageId) {// ����ͼ�������ֵ�Ŀ�д����
			this.imageId = imageId;
		}
	}
}