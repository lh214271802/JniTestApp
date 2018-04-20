package com.lh.jnitestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @author liaohui
 * @date 2018/4/20
 */
public class CGetJavaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_call_java_layout);
    }


    /**
     * 当执行这个方法的时候，通知C来调用add方法
     */
    public native void callBackAdd();

    public int add(int x, int y) {
        Log.e("add", "C 调用了我-->" + (x + y));
        return x + y;
    }
}
