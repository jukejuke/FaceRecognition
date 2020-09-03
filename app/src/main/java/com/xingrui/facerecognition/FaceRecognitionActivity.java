package com.xingrui.facerecognition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.luck.picture.lib.PictureSelector;
import com.seeta.sdk.util.SeetaHelper;

import java.util.ArrayList;
import java.util.List;

public class FaceRecognitionActivity extends Activity {

    String msg = "Android(FaceRecognitionActivity) : ";

    private List<String> pers = new ArrayList<String>(){{
        add(Manifest.permission.READ_EXTERNAL_STORAGE);
        add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognition);
        initPer();
    }

    private void initPer(){
        Log.e(msg,"initPer");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //判断是否具有权限
            for(int i=0;i<pers.size();i++){
                if (ContextCompat.checkSelfPermission(this, pers.get(i)) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{pers.get(i)}, 1);
                }else{
                    initData();
                }
            }
        }
    }

    private void initData() {
//        SeetaHelper.ROOT_CACHE = Environment.getExternalStorageDirectory().toString() + File.separator + "seetaface"
//        SeetaHelper.ROOT_ASSETS = "seetaface"
        Log.e(msg,"initData");
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SeetaHelper.copyAts(FaceRecognitionActivity.this)) { //将assets目录中的模型文件拷贝到SD卡
                    SeetaHelper.getInstance().init();
                }
            }
        }).start();
    }
}