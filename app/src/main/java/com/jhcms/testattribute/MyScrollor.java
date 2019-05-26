package com.jhcms.testattribute;

import android.os.SystemClock;

public class MyScrollor {
    private  int startX;
    private float totalDistanceX;
    private int startY;
    private float totalDistanceY;
    private boolean isFinish = false;
    private long startTime;
    private long durationTime = 500;
    private float currX;

    //得到坐标
    public float getCurrX() {
        return currX;
    }

    public  void startScroll(int startX, int startY, float totalDistanceX, float totalDistanceY) {
        this.startX = startX;
        this.startY = startY;
        this.totalDistanceX = totalDistanceX;
        this.totalDistanceY = totalDistanceY;
        this.startTime = SystemClock.uptimeMillis();//开始移动的时间
        this.isFinish = false;
    }


    public boolean cuputeScrollOffset(){
        if(isFinish){
            return false;
        }
        long endTime = SystemClock.uptimeMillis();//获取系统的开机时间
        long passTime = endTime - startTime;
        if(passTime<durationTime){
            //移动一小段的距离
            float distanceSmallX = passTime*totalDistanceX/durationTime;
            currX = startX+distanceSmallX;
        }else{
            isFinish = true;
            currX = startX+totalDistanceX;
        }
        return true;
    }
}
