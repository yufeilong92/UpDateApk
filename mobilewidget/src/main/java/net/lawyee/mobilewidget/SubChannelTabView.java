package net.lawyee.mobilewidget;

import net.lawyee.mobilelib.utils.ViewUtil;
import net.lawyee.mobilelib.vo.BaseVO;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SubChannelTabView extends RelativeLayout {
	private BaseVO mDataVO;
	private int mIndex;
	private int mMinwidth;
	private TextView mTabText;
	private ImageView mTabImg;

	public SubChannelTabView(Context context) {
		this(context, null);
	}

	public SubChannelTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.widget_subchannel_tab,
				this, true);
		mTabImg = (ImageView) this.findViewById(R.id.subchannel_tab_icon);
		mTabText = (TextView) this.findViewById(R.id.subchannel_tab_text);
	}

	/**
	 * 记算最小宽度
	 * 
	 * @param c
	 * @param maxtextlen
	 * @return
	 */
	public static int getMinWidth(Context c, int maxtextlen) {
		/*
		 * int width = metric.widthPixels; // 宽度（PX） int height =
		 * metric.heightPixels; // 高度（PX）
		 * 
		 * float density = metric.density; // 密度（0.75 / 1.0 / 1.5） int
		 * densityDpi = metric.densityDpi; // 密度DPI（120 / 160 / 240）
		 */
		DisplayMetrics metric = new DisplayMetrics();
		WindowManager windowManager = (WindowManager) c
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.getDefaultDisplay().getMetrics(metric);
		float width = (ViewUtil.getScreenWidth(c)) / 4.0f;
		float tmp;
		// 长度1~2->40 3个及以上字数字->20*个数

		if (maxtextlen < 3)
			tmp = 40 * metric.density;
		else
			tmp = 20 * maxtextlen * metric.density;
		return tmp < width ? (int) width : (int) tmp;

	}

	public ImageView getTabImg() {
		return mTabImg;
	}

	public void setTabImg(ImageView tabImg) {
		this.mTabImg = tabImg;
	}

	public int getIndex() {
		return mIndex;
	}

	public void setIndex(int index) {
		this.mIndex = index;
	}

	public TextView getTabTextView() {
		return mTabText;
	}

	public void setTabText(String tabText) {
		this.mTabText.setText(tabText);
	}

	public BaseVO getDataVO() {
		return mDataVO;
	}

	public void setDataVO(BaseVO datavo) {
		this.mDataVO = datavo;
	}
	
	

	public int getmMinwidth() {
		return mMinwidth;
	}

	public void setmMinwidth(int mMinwidth) {
		this.mMinwidth = mMinwidth;
	}

	@Override
	public void setSelected(boolean selected) {
		super.setSelected(selected);
		mTabImg.setSelected(selected);
		mTabText.setSelected(selected);
		mTabText.setTextColor(getContext().getResources().getColor(
				selected ? R.color.subchanneltab_sel_textcolor
						: R.color.subchanneltab_textcolor));
	}

}
