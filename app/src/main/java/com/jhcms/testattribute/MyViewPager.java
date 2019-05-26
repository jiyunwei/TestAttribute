package com.jhcms.testattribute;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

public class MyViewPager extends ViewGroup {
    private static final String TAG = "MyViewPager";

    private final Context context;
    private GestureDetector gestureDetector;
    private float startX;
    private int mCurrentPageIndex = 0;
    private int mRealPageIndex = 0;
    private MyScrollor myScrollor;

    public MyViewPager(Context context) {
        this(context, null);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    private void initView() {
        myScrollor = new MyScrollor();
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                Log.d(TAG, "onLongPress: 长按");

            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                scrollBy((int) distanceX, getScrollY());
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Log.d(TAG, "onDoubleTap: 双击");
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Log.d(TAG, "onSingleTapConfirmed: 点击");
                return super.onSingleTapConfirmed(e);
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                float endX = event.getX();
                //计算滑动是否大于整个屏幕的一半 如果大于让视图移动到下一个视图
                int distanceX = (int) (startX - endX);


                if (distanceX < 0) {
                    //右滑手势 index--
                    if (Math.abs(distanceX) > getWidth() / 2) {
                        mCurrentPageIndex--;
                        if (mCurrentPageIndex < 0) {
                            mCurrentPageIndex = 0;
                        }
                    }
                } else {
                    //左滑手势 index++
                    if (Math.abs(distanceX) > getWidth() / 2) {
                        mCurrentPageIndex++;
                        if (mCurrentPageIndex > getChildCount()) {
                            mCurrentPageIndex = getChildCount() - 1;
                        }
                    }

                }

                scrollPageToIndex(mCurrentPageIndex);

                break;
        }

        return true;
    }

    private void scrollPageToIndex(int mCurrentPageIndex) {
        if (mCurrentPageIndex <= 0) {
            mCurrentPageIndex = 0;
        } else if (mCurrentPageIndex >= getChildCount()) {
            mCurrentPageIndex = getChildCount() - 1;
        }

        mRealPageIndex = mCurrentPageIndex;

//        scrollTo(mRealPageIndex*getWidth(),0);
        int totalDistanceX = mRealPageIndex * getWidth() - getScrollX();
        myScrollor.startScroll(getScrollX(),getScrollY(),totalDistanceX,0);

        invalidate();

    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(myScrollor.cuputeScrollOffset()){
            float currX = myScrollor.getCurrX();
            scrollTo((int) currX,0);
            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }
    }


    @Override
    protected Parcelable onSaveInstanceState() {
        Log.d(TAG, "onSaveInstanceState: ");
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        Log.d(TAG, "onRestoreInstanceState: ");
    }
}
