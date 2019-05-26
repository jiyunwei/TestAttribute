package com.jhcms.testattribute;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity {

    private MyViewPager myViewPager;
    private int images[]={R.mipmap.guideone,R.mipmap.guidetwo,R.mipmap.guidethree,R.mipmap.guidefour};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        myViewPager = findViewById(R.id.myviewpager);
        for(int i=0;i<images.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(images[i]);
            myViewPager.addView(imageView);
        }

    }
}
