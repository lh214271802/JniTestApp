package com.lh.jnitestapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Arrays;

/**
 * java 调用C代码
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private TextView sampleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        sampleText = (TextView) findViewById(R.id.sample_text);
        sampleText.setText(stringFromJNI());
        sampleText.setOnClickListener(this);
        findViewById(R.id.sample_text1).setOnClickListener(this);
        findViewById(R.id.sample_text2).setOnClickListener(this);
        findViewById(R.id.sample_text3).setOnClickListener(this);
        findViewById(R.id.sample_text4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sample_text:
                int add = add(4, 7);
                sampleText.setText("add(4, 7)=" + add);
                break;
            case R.id.sample_text1:
                String hello = sayHello("i am from java");
                sampleText.setText("sayHello-->" + hello);
                break;
            case R.id.sample_text2:
                int[] intArray = {1, 2, 57, 96, 5, 3, 64, 75};
                increaseArrayEles(intArray);
                sampleText.setText(Arrays.toString(intArray));
                break;
            case R.id.sample_text3:
                int result = checkPwd("123456");
                sampleText.setText("checkPwd(\"132456\")" + result);
                break;
            case R.id.sample_text4:
                startActivity(new Intent(this,CGetJavaActivity.class));
                break;
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    /**
     * 让C代码做加法运算，把结果返回
     */
    public native int add(int a, int b);


    /**
     * 从java传入字符串，C代码进程拼接
     *
     * @param s i am from java
     * @return i am from add i am from c
     */
    public native String sayHello(String s);

    /**
     * 让C代码给每个元素加上10然后返回
     *
     * @param intArray
     * @return
     */
    public native int[] increaseArrayEles(int[] intArray);

    /**
     * 应用：检查密码是否正确，如果正确返回200，否则返回400
     */
    public native int checkPwd(String pwd);

}
