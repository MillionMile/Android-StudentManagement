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
	private double[] score = new double[] { 0, 0, 0 }; // 各项金额的默认值
	private int[] color = new int[] { Color.RED, Color.YELLOW, Color.GREEN }; // 各项颜色
	private final int WIDTH = 28; // 柱型的宽度
	private final int OFFSET = 30; // 间距
	private int x = 70; // 起点x
	private int y = 329; // 终点y
	private int height = 220; // 高度
	String cla_name;
	String tea_id;

	String[] type = null; // 类型

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart); // 设置使用的布局文件
		Intent intent = getIntent(); // 获取Intent对象
		Bundle bundle = intent.getExtras(); // 获取传递的数据包
		cla_name = bundle.getString("cla_name"); // 获取传递过来的课程号
		tea_id = bundle.getString("tea_id");
		Resources res = getResources(); // 获取Resources对象
		type = res.getStringArray(R.array.arr); // 获取收入类型数组
		FrameLayout ll = (FrameLayout) findViewById(R.id.canvas);// 获取布局文件中添加的帧布局管理器
		ll.addView(new MyView(this)); // 将自定义的MyView视图添加到帧布局管理器中
	}

	public class MyView extends View {

		public MyView(Context context) {
			super(context);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawColor(Color.WHITE); // 指定画布的背景色为白色
			Paint paint = new Paint(); // 创建采用默认设置的画笔
			paint.setAntiAlias(true); // 使用抗锯齿功能
			/*********** 绘制坐标轴 **********************/
			paint.setStrokeWidth(1); // 设置笔触的宽度
			paint.setColor(Color.BLACK); // 设置笔触的颜色
			canvas.drawLine(50, 330, 300, 330, paint); // 横
			canvas.drawLine(50, 100, 50, 330, paint); // 竖
			/******************************************/
			/*********** 绘制柱型 **********************/
			paint.setStyle(Style.FILL); // 设置填充样式为填充
			int left = 0; // 每个柱型的起点X坐标

			score = getScore(); // 新获取的金额******************
			double max = maxScore(score);
			for (int i = 0; i < score.length; i++) {
				paint.setColor(color[i]); // 设置笔触的颜色
				left = x + 10 + i * (OFFSET + WIDTH); // 计算每个柱型起点X坐标
				canvas.drawRect(left, (float) (y - height / max * score[i]), left + WIDTH, y, paint);
			}
			/******************************************/
			/*********** 绘制纵轴的刻度 **********************/
			paint.setColor(Color.BLACK); // 设置笔触的颜色
			int tempY = 0;
			for (int i = 0; i < 11; i++) {
				tempY = y - height + height / 10 * i + 1;
				canvas.drawLine(47, tempY, 50, tempY, paint);
				paint.setTextSize(12); // 设置字体大小
				canvas.drawText(String.valueOf((int) (max / 10 * (10 - i))), 15, tempY + 5, paint); // 绘制纵轴题注
			}
			/******************************************/
			/*********** 绘制说明文字 **********************/
			paint.setColor(Color.BLACK); // 设置笔触的颜色
			paint.setTextSize(21); // 设置字体大小
			/****************** 绘制标题 *********************************/
			canvas.drawText("成绩统计图", 40, 55, paint); // 绘制标题
			/***********************************************************/
			paint.setTextSize(16); // 设置字体大小

			String str_type = "";
			for (int i = 0; i < type.length; i++) {
				str_type += type[i] + "   ";
			}
			canvas.drawText(str_type, 68, 350, paint); // 绘制横轴题注
		}
	}

	// 计算最大金额
	double maxScore(double[] score) {
		double max = score[0]; // 将第一个数组元素赋值给变量max
		for (int i = 0; i < score.length - 1; i++) {
			if (max < score[i + 1]) {
				max = score[i + 1]; // 更新max
			}
		}
		return max;
	}

	// 获取收支数据
	double[] getScore() {
		Map mapScore = null;
		// System.out.println(cla_name);
		ScoreDAO scoreDAO = new ScoreDAO(Chart.this);// 创建TotalChart对象
		mapScore = scoreDAO.getTotal(tea_id, cla_name); // 获取成绩汇总信息
		int size = type.length;
		double[] score1 = new double[size];
		for (int i = 0; i < size; i++) {
			score[i] = (mapScore.get(type[i]) != null ? ((double) mapScore.get(type[i])) : 0);
		}
		return score;
		// return new double[]{1.0,30};
	}
}
