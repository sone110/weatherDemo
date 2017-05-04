package com.example.sampleweather.ui;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.webkit.WebView;
import android.widget.ScrollView;
import android.widget.Scroller;

public class MyScrollView extends ScrollView {

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr, int
            defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private float mDownPosX = 0;
    private float mDownPosY = 0;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final float x = ev.getX();
        final float y = ev.getY();

        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownPosX = x;
                mDownPosY = y;

                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaX = Math.abs(x - mDownPosX);
                final float deltaY = Math.abs(y - mDownPosY);
                // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
                if (deltaX > deltaY) {
                	System.out.println("1231233211");
                    return false;
                }
               
        }

        System.out.println("66666");
        return super.onInterceptTouchEvent(ev);
    }
}
