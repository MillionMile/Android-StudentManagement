package activity;

import java.util.Map;

import com.example.studentmanagement.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import dao.ScoreDAO;

public class Chart extends Activity {
	private double[] score = new double[] { 0, 0, 0 }; // �������Ĭ��ֵ
	private int[] color = new int[] { Color.RED, Color.YELLOW, Color.GREEN }; // ������ɫ
	private final int WIDTH = 28; // ���͵Ŀ��
	private final int OFFSET = 30; // ���
	private int x = 70; // ���x
	private int y = 329; // �յ�y
	private int height = 220; // �߶�
	String cla_name;
	String tea_id;

	String[] type = null; // ����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart); // ����ʹ�õĲ����ļ�
		Intent intent = getIntent(); // ��ȡIntent����
		Bundle bundle = intent.getExtras(); // ��ȡ���ݵ����ݰ�
		cla_name = bundle.getString("cla_name"); // ��ȡ���ݹ����Ŀγ̺�
		tea_id = bundle.getString("tea_id");
		Resources res = getResources(); // ��ȡResources����
		type = res.getStringArray(R.array.arr); // ��ȡ������������
		FrameLayout ll = (FrameLayout) findViewById(R.id.canvas);// ��ȡ�����ļ�����ӵ�֡���ֹ�����
		ll.addView(new MyView(this)); // ���Զ����MyView��ͼ��ӵ�֡���ֹ�������
	}

	public class MyView extends View {

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE); // ָ�������ı���ɫΪ��ɫ
			Paint paint = new Paint(); // ��������Ĭ�����õĻ���
			paint.setAntiAlias(true); // ʹ�ÿ���ݹ���
			/*********** ���������� **********************/
			paint.setStrokeWidth(1); // ���ñʴ��Ŀ��
			paint.setColor(Color.BLACK); // ���ñʴ�����ɫ
			canvas.drawLine(50, 330, 300, 330, paint); // ��
			canvas.drawLine(50, 100, 50, 330, paint); // ��
			/******************************************/
			/*********** �������� **********************/
			paint.setStyle(Style.FILL); // ���������ʽΪ���
			int left = 0; // ÿ�����͵����X����

			score = getScore(); // �»�ȡ�Ľ��******************
			double max = maxScore(score);
			for (int i = 0; i < score.length; i++) {
				paint.setColor(color[i]); // ���ñʴ�����ɫ
				left = x + 10 + i * (OFFSET + WIDTH); // ����ÿ���������X����
				canvas.drawRect(left, (float) (y - height / max * score[i]), left + WIDTH, y, paint);
			}
			/******************************************/
			/*********** ��������Ŀ̶� **********************/
			paint.setColor(Color.BLACK); // ���ñʴ�����ɫ
			int tempY = 0;
			for (int i = 0; i < 11; i++) {
				tempY = y - height + height / 10 * i + 1;
				canvas.drawLine(47, tempY, 50, tempY, paint);
				paint.setTextSize(12); // ���������С
				canvas.drawText(String.valueOf((int) (max / 10 * (10 - i))), 15, tempY + 5, paint); // ����������ע
			}
			/******************************************/
			/*********** ����˵������ **********************/
			paint.setColor(Color.BLACK); // ���ñʴ�����ɫ
			paint.setTextSize(21); // ���������С
			/****************** ���Ʊ��� *********************************/
			canvas.drawText("�ɼ�ͳ��ͼ", 40, 55, paint); // ���Ʊ���
			/***********************************************************/
			paint.setTextSize(16); // ���������С

			String str_type = "";
			for (int i = 0; i < type.length; i++) {
				str_type += type[i] + "   ";
			}
			canvas.drawText(str_type, 68, 350, paint); // ���ƺ�����ע
		}
	}

	// ���������
	double maxScore(double[] score) {
		double max = score[0]; // ����һ������Ԫ�ظ�ֵ������max
		for (int i = 0; i < score.length - 1; i++) {
			if (max < score[i + 1]) {
				max = score[i + 1]; // ����max
			}
		}
		return max;
	}

	// ��ȡ��֧����
	double[] getScore() {
		Map mapScore = null;
		// System.out.println(cla_name);
		ScoreDAO scoreDAO = new ScoreDAO(Chart.this);// ����TotalChart����
		mapScore = scoreDAO.getTotal(tea_id, cla_name); // ��ȡ�ɼ�������Ϣ
		int size = type.length;
		double[] score1 = new double[size];
		for (int i = 0; i < size; i++) {
			score[i] = (mapScore.get(type[i]) != null ? ((double) mapScore.get(type[i])) : 0);
		}
		return score;
		// return new double[]{1.0,30};
	}
}
