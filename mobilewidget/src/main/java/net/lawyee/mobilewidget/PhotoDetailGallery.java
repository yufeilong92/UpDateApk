package net.lawyee.mobilewidget;

import net.lawyee.mobilelib.utils.ViewUtil;
import net.lawyee.mobilewidget.imagezoom.ImageViewTouchBase;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

@SuppressWarnings("deprecation")
public class PhotoDetailGallery extends Gallery
{
	private int screenWidth;

	private int screenHeight;

	private Context mContext;

	private OnChangePageListener mChangePageListener;

	private OnGalleryClickListener mClickListener;

	private double mDown_time = 0;

	public PhotoDetailGallery(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		mContext = context;
		init();
	}

	private void init()
	{
		screenWidth = ViewUtil.getScreenWidth(mContext);
		screenHeight = ViewUtil.getScreenHeight(mContext);

		// mDetector = new GestureDetector(new MySimpleGesture());
		this.setOnTouchListener(new OnTouchListener()
		{

			float baseValue;
			float originalScale;

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				View view = PhotoDetailGallery.this.getSelectedView();
				if (view instanceof ImageViewTouchBase)
				{
					ImageViewTouchBase detailImageView = (ImageViewTouchBase) view;

					if (event.getAction() == MotionEvent.ACTION_DOWN)
					{
						mDown_time = event.getDownTime();
						baseValue = 0;
						originalScale = detailImageView.getScale();
					}
					if (event.getAction() == MotionEvent.ACTION_MOVE)
					{
						if (event.getPointerCount() == 2)
						{
							float x = event.getX(0) - event.getX(1);
							float y = event.getY(0) - event.getY(1);
							float value = (float) Math.sqrt(x * x + y * y);// 鐠侊紕鐣绘稉銈囧仯閻ㄥ嫯绐涢敓锟�
							// System.out.println("value:" + value);
							if (baseValue == 0)
							{
								baseValue = value;
							} else
							{
								float scale = value / baseValue;// 瑜版挸澧犳稉銈囧仯闂傚娈戠捄婵堫湩闂勩倓浜掗幍瀣瘹閽�垝绗呴弮鏈佃⒈閻愬綊妫块惃鍕獩缁傝姘ㄩ弰顖炴付鐟曚胶缂夐弨鍓ф畱濮ｆ柧绶ラ敓锟�
								// scale the image
								detailImageView.setScaleType(ScaleType.MATRIX);
								float nowscale =Math.max(originalScale * scale,1f);
								nowscale=Math.min(nowscale, detailImageView.getMaxZoom());
								detailImageView.zoomTo(nowscale, x
										+ event.getX(1), y + event.getY(1),500);
							}
						}
					}
				}
				return false;
			}

		});
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY)
	{
		View view = getSelectedView();
		if (view instanceof ImageViewTouchBase)
		{
			ImageViewTouchBase detailImageView = (ImageViewTouchBase) view;

			float v[] = new float[9];
			Matrix m = detailImageView.getImageMatrix();
			m.getValues(v);

			float left, right,top,bottom;

			float width=screenWidth;
			float height=screenHeight;
			if(detailImageView.getBitmap()!=null)
			{
				/**
				 * 图片很小的情况
				 */
//				if (detailImageView.getBitmap().getWidth() < screenWidth/2)
//				{
//					width = detailImageView.getScale()
//							* detailImageView.getBitmap().getWidth() * 2;
//				} else
//				{
					width = detailImageView.getScale() * detailImageView.getWidth();
//				}
				height = detailImageView.getScale() * detailImageView.getHeight();
			}
			if ((int) width <= screenWidth && (int) height <= screenHeight)
			{
				super.onScroll(e1, e2, distanceX, distanceY);
				if (this.getChildCount() > 1)
					invokeChangePageListener(getSelectedItemPosition() + 1,
							detailImageView);
				// mPhotoDetailActivity.changeNowPhoto(getSelectedItemPosition()
				// + 1);
			}  else if((int) width<screenWidth && (int) height > screenHeight){
				top = v[Matrix.MTRANS_Y];
				bottom = top + height;
				Rect r = new Rect();
				detailImageView.getGlobalVisibleRect(r);
				// 一个很高很窄的图片，放大后，高度超出屏幕，宽度未超出
				if (Math.abs(distanceX) > Math.abs(distanceY)) {
				    // 手指横向滑动
				   super.onScroll(e1, e2, distanceX, distanceY);
				} else {
					// 手指纵向滑动，上下移动图片
				   if (distanceY > 0) {// 向上滑动
				       if (bottom <screenHeight/2) {// 判断当前ImageView是否显示完全
				          super.onScroll(e1, e2, distanceX, distanceY);
				       }
				       else {
					      detailImageView.postTranslate(-distanceX, -distanceY);
				       }
				   } else if (distanceY < 0) { // 向下滑动
				       if (top>screenHeight/2) {
				          super.onScroll(e1, e2, distanceX, distanceY);
				       } else {
					        detailImageView.postTranslate(-distanceX, -distanceY);
				       }
				   }
				}
				
			}else
			{
				left = v[Matrix.MTRANS_X];
				right = left + width;
				Rect r = new Rect();
				detailImageView.getGlobalVisibleRect(r);

				if (distanceX > 0)
				{
					if (left > 0)
					{
						super.onScroll(e1, e2, distanceX, distanceY);
						if (this.getChildCount() > 1)
							invokeChangePageListener(
									getSelectedItemPosition() + 1,
									detailImageView);
					} else if (right < screenWidth)
					{
						super.onScroll(e1, e2, distanceX, distanceY);
						if (this.getChildCount() > 1)
							invokeChangePageListener(
									getSelectedItemPosition() + 1,
									detailImageView);
					} else
					{
						detailImageView.postTranslate(-distanceX, -distanceY);
					}
				} else if (distanceX < 0)
				{
					if (right < screenWidth)
					{
						super.onScroll(e1, e2, distanceX, distanceY);
						if (this.getChildCount() > 1)
							invokeChangePageListener(
									getSelectedItemPosition() + 1,
									detailImageView);
					} else if (left > 0)
					{
						super.onScroll(e1, e2, distanceX, distanceY);
						if (this.getChildCount() > 1)
							invokeChangePageListener(
									getSelectedItemPosition() + 1,
									detailImageView);
					} else
					{

						detailImageView.postTranslate(-distanceX, -distanceY);
					}
				}

			}

		} else
		{
			super.onScroll(e1, e2, distanceX, distanceY);
		}
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY)
	{
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		switch (event.getAction())
		{		
		case MotionEvent.ACTION_UP:
			ImageViewTouchBase view = (ImageViewTouchBase) PhotoDetailGallery.this
					.getSelectedView();
			if (event.getEventTime() - mDown_time < 130)
			{
				invokeClickListener(view);
				return true;
			}
			if (view instanceof ImageViewTouchBase)
			{
				ImageViewTouchBase detailImageView = (ImageViewTouchBase) view;
				float width = detailImageView.getScale()
						* detailImageView.getWidth();
				float height = detailImageView.getScale()
						* detailImageView.getHeight();
				if ((int) width <= screenWidth && (int) height <= screenHeight)// 婵″倹鐏夐崶鍓у瑜版挸澧犳径褍鐨�鐏炲繐绠锋径褍鐨敍灞藉灲閺傤叀绔熼敓锟�
				{
					break;
				}
				detailImageView.center(true, true);
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	public void setChangePageListener(OnChangePageListener mChangePageListener)
	{
		this.mChangePageListener = mChangePageListener;
	}

	private void invokeChangePageListener(int nowpage, ImageViewTouchBase iv)
	{
		if (mChangePageListener != null)
			mChangePageListener.onChangedPage(nowpage, iv);
	}

	public void setClickListener(OnGalleryClickListener mClickListener)
	{
		this.mClickListener = mClickListener;
	}

	private void invokeClickListener(ImageViewTouchBase iv)
	{
		if (mClickListener != null)
			mClickListener.onClick(iv);
	}

	public static interface OnChangePageListener
	{
		/**
		 * 
		 * @param nowpage 当前面，从1开始
		 * @param iv
		 */
		public void onChangedPage(int nowpage, ImageView iv);
	}

	public static interface OnGalleryClickListener
	{
		/**
		 * 
		 * @param nowpage
		 * @param iv
		 */
		public void onClick(ImageView iv);
	}
}
