package com.wangmingqiang.money.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by wangmingqiang on 2017/3/13.
 */

public class MyScrollView extends ScrollView {


    private int lastY;
    private View childView;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(getChildCount()==0) {
            return super.onTouchEvent(ev);
        }
        /**
         * getY(); 相对于父布局的Y值
         * getrawY(); 相对于屏幕的Y值
         *
         */
        int eventY = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :
                //保存第一次触摸到的点
                lastY =eventY;
                break;


            case MotionEvent.ACTION_MOVE:
                if(isNeedMove()) {
                    int dy = eventY - lastY;//移动的量
                    childView.layout(childView.getLeft(),childView.getTop()+dy/2,childView.getRight(),childView.getBottom()+dy/2);
                    lastY = eventY;

                }
                break;


            case MotionEvent.ACTION_UP :

                break;

        }
        return super.onTouchEvent(ev);
    }

    public boolean isNeedMove(){
        //scrollView的高度
        int measuredHeight = this.getMeasuredHeight();
        //子视图的高度
        int childViewMeasuredHeight = childView.getMeasuredHeight();
        
        int dy=measuredHeight-childViewMeasuredHeight;

        //拿到滑动的距离  往下滑动是变小 往上滑动是变大
        int scrollY = getScrollY();
        if(scrollY<=0||scrollY>=dy) {
            return true;
        }
        return false;
    }

    //这个方法是在布局加载完成后调用的
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if(getChildCount()>0) {
            childView=getChildAt(0);
        }
    }
}
