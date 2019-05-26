package com.jhcms.testattribute;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyCustomView extends View {
    private static final String TAG = "MyCustomView";
    private String name;
    private String age;
    private Bitmap bd;
    private Paint paint;

    public MyCustomView(Context context) {
        this(context,null);
    }

    public MyCustomView(Context context,  AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();

        //通过命名空间获取属性值
        String my_name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_name");
        String my_age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_age");
        String my_bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "my_bg");

        Log.d(TAG, "MyCustomView: "+my_name);
        Log.d(TAG, "MyCustomView: "+my_age);
        Log.d(TAG, "MyCustomView: "+my_bg);

        for(int i=0;i<attrs.getAttributeCount();i++){
            String attributeValue = attrs.getAttributeValue(i);
            Log.e(TAG, "MyCustomView: "+attributeValue );
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyCustomView);
        int indexCount = typedArray.getIndexCount();
        for(int i=0;i<indexCount;i++){
           switch (typedArray.getIndex(i)){
               case R.styleable.MyCustomView_my_name:
                   name = typedArray.getString(i);
                   break;

               case R.styleable.MyCustomView_my_age:
                   age = typedArray.getString(i);
                   break;

               case R.styleable.MyCustomView_my_bg:
                   BitmapDrawable drawable  = (BitmapDrawable) typedArray.getDrawable(i);
                  bd = drawable.getBitmap();
                   break;
           }
        }

        typedArray.recycle();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setTextSize(14*getContext().getResources().getDisplayMetrics().density);
        paint.setAntiAlias(true);
        canvas.drawText(name,50,50,paint);
        canvas.drawText(age,100,100,paint);
        canvas.drawBitmap(bd,150,150,paint);
    }
}
