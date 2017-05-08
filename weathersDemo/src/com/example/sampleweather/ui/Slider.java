package com.example.sampleweather.ui;

import com.example.sampleweather.R;
import com.example.sampleweather.R.drawable;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class Slider extends View {

	
	private String [] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I",  
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
            "W", "X", "Y", "Z", "#" };
	private int choose = -1;
	private Paint paint  = new Paint();
	private TextView mTextDialog;
	private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	
	
	public void setTextView(TextView mTextDialog) {  
        this.mTextDialog = mTextDialog;  
    }
	public Slider(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Slider(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public Slider(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int height = getHeight()-5;
		System.out.println(height);
		int width = getWidth();
		float singleHeight =  height/(float)letter.length;
//		int singleHeight =  height/letter.length;
		for (int i = 0; i < letter.length; i++) {
			
			paint.setColor(Color.parseColor("#90FFFFFF"));
//			setBackgroundDrawable(new ColorDrawable(0x00000000));  
			setBackgroundResource(R.drawable.sidebar_background);  
			paint.setTypeface(Typeface.DEFAULT);
			paint.setAntiAlias(true);
			paint.setTextSize(15);
			paint.setFakeBoldText(true);
			 if (i == choose) {  
	                paint.setColor(Color.parseColor("#3399ff"));  
	                paint.setFakeBoldText(true);  
	            }  
	            // x坐标等于中间-字符串宽度的一半.  
	            float xPos = width / 2 - paint.measureText(letter[i]) / 2;  
	            float yPos = singleHeight * i+ + singleHeight; 
	           
//	            System.out.println("xPos"+xPos+"yPos"+yPos+"SingleHeight"+singleHeight);
	            canvas.drawText(letter[i], xPos, yPos, paint);  
//	            canvas.drawLine(xPos, yPos, 10,yPos, paint);
	            paint.reset();// 重置画笔  
			
		}
		
	}
	 @Override  
	    public boolean dispatchTouchEvent(MotionEvent event) {  
	        final int action = event.getAction();  
	        final float y = event.getY();// 点击y坐标  
	        System.out.println("y"+y);
	        final int oldChoose = choose;  
	        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;  
	        final int c = (int) (y / getHeight() * letter.length);// 点击y坐标所占总高度的比例*b数组的长度就等于点击b中的个数.  
	  
	        switch (action) {  
	        case MotionEvent.ACTION_UP:   
	            choose = -1;//  
	            invalidate();  
	            if (mTextDialog != null) {  
	                mTextDialog.setVisibility(View.INVISIBLE);  
	            }  
	            break;  
//	       按下时定义背景色
	        default:  
//	            setBackgroundResource(R.drawable.sidebar_background);  
	            if (oldChoose != c) {  
	                if (c >= 0 && c < letter.length) {  
	                    if (listener != null) {  
	                        listener.onTouchingLetterChanged(letter[c]);  
	                    }  
	                    if (mTextDialog != null) { 
	                    	mTextDialog.setBackgroundColor(Color.GRAY);
	                        mTextDialog.setText(letter[c]);  
	                        mTextDialog.setVisibility(View.VISIBLE);  
	                    }  
	                      
	                    choose = c;  
	                    invalidate();  
	                }  
	            }  
	  
	            break;  
	        }  
	        return true;  
	    }  
	  
	    /** 
	     * 向外公开的方法 
	     *  
	     * @param onTouchingLetterChangedListener 
	     */  
	    public void setOnTouchingLetterChangedListener(  
	            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {  
	        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;  
	    }  
	  
	    /** 
	     * 接口 
	     *  
	     * @author coder 
	     *  
	     */  
	    public interface OnTouchingLetterChangedListener {  
	        public void onTouchingLetterChanged(String s);  
	    }  
	  
}



