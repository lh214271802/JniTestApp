package com.lh.jnitestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * @author liaohui
 * @date 2018/4/20
 * <p>
 * java中的native方法编译后在（build/intermediates/classes/debug/包名+类名）中得到class文件；
 * 可以调用javah -全类名得到c代码的头文件；
 * 方法签名可以在cmd中用javap -s 字节码对应的类，显示方法签名
 * C调用java主要使用的是反射机制，步骤如下：
 * 1.得到字节码
 * 2.得到方法
 * 3.实例化该类(静态方法直接用该类class调用)
 * 4.调用方法（注意需要取到方法签名）
 */
public class CGetJavaActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_call_java_layout);
        findViewById(R.id.sample_text).setOnClickListener(this);
        findViewById(R.id.sample_text1).setOnClickListener(this);
        findViewById(R.id.sample_text2).setOnClickListener(this);
    }


    /**
     * 当执行这个方法的时候，通知C来调用add方法
     */
    public native void callBackAdd();

    /**
     * 执行这个方法的时候，让C代码调用
     */
    public native void callBackHelloFromJava();


    /**
     * 执行这个方法的时候，让C代码调用静态方法
     */
    public native void callBackStaticSayHello();

    public static void sayHello(String mesg) {
        Log.e("sayHello", "C 调用了静态方法sayHello----》" + mesg);
    }

    public int add(int x, int y) {
        Log.e("add", "C 调用了我-->" + (x + y));
//        Toast.makeText(CGetJavaActivity.this,"C 调用了我-->"+(x+y),Toast.LENGTH_SHORT).show();
        return x + y;
    }

    public String helloFromJava() {
        Log.e("helloFromJava", "C 调用了我-->helloFromJava");
        return "hello from java";
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_text:
                callBackAdd();
                break;
            case R.id.sample_text1:
                callBackHelloFromJava();
                break;
            case R.id.sample_text2:
                callBackStaticSayHello();
                break;
        }
    }
}
