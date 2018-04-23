package com.lh.jnitestapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
 * <p>
 * <p>
 * --------》直接使用.so文件：
 * 1.拷贝.so文件到工程main/jniLibs目录下
 * 2.不用在build.gradle中配置
 * 3.c代码也不用写了，原来的java文件还是可以直接可以调用原c代码
 */
public class CGetJavaActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_call_java_layout);
        findViewById(R.id.sample_text).setOnClickListener(this);
        findViewById(R.id.sample_text1).setOnClickListener(this);
        findViewById(R.id.sample_text2).setOnClickListener(this);
        findViewById(R.id.sample_text3).setOnClickListener(this);
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

    /**
     * c代码调用java代码并更新UI，需要C代码中返回当前调用C代码的对象的实例---》即C方法中的第二个参数(jobject instance)
     */
    public native void callBackShowToast();

    public static void sayHello(String mesg) {
        Log.e("sayHello", "C 调用了静态方法sayHello----》" + mesg);
    }

    public int add(int x, int y) {
        Log.e("add", "C 调用了我-->" + (x + y));
        return x + y;
    }

    public void showToast() {
        Toast.makeText(CGetJavaActivity.this, "C 调用了我-->TOAST--->hahahahhahahahha", Toast.LENGTH_SHORT).show();
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
            case R.id.sample_text3:
                callBackShowToast();
                break;
        }
    }
}
