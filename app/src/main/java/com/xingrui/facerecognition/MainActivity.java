package com.xingrui.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");
    }

    public void btn1Click(View view) {
        Toast.makeText(MainActivity.this, "打开第二页面...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);
    }

    public void btn2Click(View view){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri data = Uri.parse("http://www.baidu.com");
        intent.setData(data);
        startActivity(intent);
    }

    /**
     * 打开相册
     */
    public void toPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
        intent.setType("image/*");
        startActivityForResult(intent,100);
        Log.e("","跳转相册成功");
    }

    /**
     * 打开相机
     * @param view
     */
    public void bntOpenCamera(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //跳转到 ACTION_IMAGE_CAPTURE
        //判断内存卡是否可用，可用的话就进行存储
        //putExtra：取值，Uri.fromFile：传一个拍照所得到的文件，fileImg.jpg：文件名
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"fileImg.jpg")));
        startActivityForResult(intent,101); // 101: 相机的返回码参数（随便一个值就行，只要不冲突就好）
        Log.e("","跳转相机成功");
    }

    /**
     * 打开人脸识别
     * @param view
     */
    public void btnOpenFaceRecongnition(View view){
        Toast.makeText(MainActivity.this, "打开人脸识别页面...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,FaceRecognitionActivity.class);
        startActivity(intent);
    }

    /**
     * 打开基础页面
     * @param view
     */
    public void btnOpenBaseActivity(View view){
        Toast.makeText(MainActivity.this, "打开基础页面...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this,MainActivity2.class);
        startActivity(intent);
    }
}