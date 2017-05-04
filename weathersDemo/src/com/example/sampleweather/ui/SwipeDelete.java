package com.example.sampleweather.ui;

import android.app.usage.UsageEvents.Event;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v4.widget.ViewDragHelper.Callback;
import android.test.ViewAsserts;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class SwipeDelete extends FrameLayout {
	
	private ViewDragHelper viewDragHelper;
	private View contentview;
	private View deleteview;
	private int deletehight;
	private int deleteWidth;
	private int contentWidth;
	
	
	public SwipeDelete(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init();
	}

	public SwipeDelete(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public SwipeDelete(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	enum SwipState{
		open , close;
	}
//	默认状态为关闭
	private  SwipState currentState = SwipState.close;
	
	private void init() {
		// TODO Auto-generated method stub
		viewDragHelper = ViewDragHelper.create(this,1.0f, callback);
		
	}
//获取子view的引用,按照layout中的摆放顺序进行引用
	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		contentview = getChildAt(0);//获取第一个子view
		deleteview  = getChildAt(1);
	}
	
//	获取各个子view的宽高
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		contentWidth = contentview.getMeasuredWidth();
		deletehight = deleteview.getMeasuredHeight();
		deleteWidth = deleteview.getMeasuredWidth();
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
//		super.onLayout(changed, left, top, right, bottom);
		contentview.layout(0, 0, contentWidth, deletehight);
		deleteview.layout(contentview.getRight(), 0, contentWidth+deleteWidth, deletehight);
	}	
		
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		boolean result = viewDragHelper.shouldInterceptTouchEvent(ev);
//		如果已开则直接交给ontuchEvent
		if( !SwipeLayoutManager.getInstance().isShouldSwipe(this)){
			SwipeLayoutManager.getInstance().closeCurrentDelete();
			result = true ;
		}
		return result;
		
	}
	float downx , downy ;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//		如果已经有打开的，则先关闭已打开的，滑动其他失效
		if( !SwipeLayoutManager.getInstance().isShouldSwipe(this)){
//			SwipeLayoutManager.getInstance().closeCurrentDelete();
			requestDisallowInterceptTouchEvent(true);
			return true;
		}
		switch (event.getAction()) {
		case  MotionEvent.ACTION_DOWN:
			downx = event.getX();
			downy = event.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float movex = event.getX();
			float movey = event.getY();
			float x = Math.abs(movex-downx);
			float y = Math.abs(movey - downy);
			if (x>y) {
//				X滑动距离大于Y请求父控件不要拦截
			 	requestDisallowInterceptTouchEvent(true);
			}
			downx = movex ;
			downy = movey ; 
			break;
			
		case MotionEvent.ACTION_UP:
			
			break ;
		default:
			break;
		}
		viewDragHelper.processTouchEvent(event);
		return true;
	}
	
	
	private ViewDragHelper.Callback callback = new Callback() {

//		确定哪些view可以被拖动
		@Override
		public boolean tryCaptureView(View child, int pointId) {
			// TODO Auto-generated method stub
			return child == contentview || child == deleteview;
		}
//		水平拖拽的长度
		@Override
		public int getViewHorizontalDragRange(View child) {
			// TODO Auto-generated method stub
			return deleteWidth;
		}
//		水平拖动处理，手指触摸移动时回调, left表示要到的x坐标
		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx) {
			// TODO Auto-generated method stub
			if (child == contentview) {
				if (left>0) left = 0 ;//限制contentview左边界值
				if (left< - deleteWidth) left = -deleteWidth ; //限制contentview的最大滑动距离
			}
			
			else if (child == deleteview) {
//				限制deleteview 右边界
				if(left > contentWidth) left =contentWidth ; 
//				限制deleteview左边界
				if(left < contentWidth - deleteWidth) left = contentWidth - deleteWidth; 
			}
			return left;
		}

//		成功捕获到子View时或者手动调用captureChildView()时回调
		@Override
		public void onViewCaptured(View capturedChild, int activePointerId) {
			// TODO Auto-generated method stub
			super.onViewCaptured(capturedChild, activePointerId);
		}

//		伴随移动  被拖拽的View位置变化时回调，changedView为位置变化的view，left、top变化后的x、y坐标，dx、dy为新位置与旧位置的偏移量
		@Override
		public void onViewPositionChanged(View changedView, int left, int top,
				int dx, int dy) {
			super.onViewPositionChanged(changedView, left, top, dx, dy);
			// TODO Auto-generated method stub
			if (changedView == contentview) {
//			跟随滑动重新布局
				deleteview.layout(
						deleteview.getLeft()+dx, 
						deleteview.getTop()+dy ,
						deleteview.getRight()+dx, 
						deleteview.getBottom()+dy);	
			}
			else if (changedView == deleteview) {				
				contentview.layout(
						contentview.getLeft()+dx, 
						contentview.getTop()+dy ,
						contentview.getRight()+dx, 
						contentview.getBottom()+dy);	
			} 
			if (contentview.getLeft() == 0 &&  currentState != SwipState.close) {
//				如果状态已经关闭，则清空记录
				currentState = SwipState.close;
				if (listener !=null) {
					listener.onClose(getTag());
				}
				 SwipeLayoutManager.getInstance().clearCurrentLayout();
			}else if (contentview.getLeft() == -deleteWidth && currentState != SwipState.open) {
//				开启则设置
				currentState = SwipState.open ;
				if (listener !=null) {
					listener.onOpen(getTag());
				}
				SwipeLayoutManager.getInstance().setCurrentDelete(SwipeDelete.this);
			}
		}
		 //手指抬起释放时回调
		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel) {
			// TODO Auto-generated method stub
			super.onViewReleased(releasedChild, xvel, yvel);
			if (contentview.getLeft()<(-deleteWidth/2)) {
//				打开
				open();
			}
			else {
				close();
			}
		}

	};
	private OnSwipeStateChangeListener listener;
//	平滑滚动方法
	public void computeScroll() {
		if (viewDragHelper.continueSettling(true)) {
			ViewCompat.postInvalidateOnAnimation(this);
		}
	};
	
	public void close() {
		viewDragHelper.smoothSlideViewTo(contentview, 0, contentview.getTop());
		ViewCompat.postInvalidateOnAnimation(SwipeDelete.this);
	}
	public void open() {
		viewDragHelper.smoothSlideViewTo(contentview, -deleteWidth, contentview.getTop());
		ViewCompat.postInvalidateOnAnimation(SwipeDelete.this);
	}
	
//	接口
	public interface  OnSwipeStateChangeListener{
		
		void onOpen(Object tag);
		void onClose(Object tag);
		
		
	}
	
	
	public void SetOnSwipeStateChangeListener ( OnSwipeStateChangeListener listener ){
		
		this.listener = listener;
		
		
	}
}
