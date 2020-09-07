package com.xingrui.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;
import com.seeta.sdk.util.SeetaHelper;
import com.seeta.sdk.util.SeetaUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaceCompareNActivity extends AppCompatActivity {

    private List<Integer> imgList = null;
    private Map<Integer,Integer> imgRegisterMap = new HashMap<Integer,Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_compare_n);
        init();
    }

    public void onClick(View view){
        if(view.getId() == R.id.raceRecogineerInit){
            register();
        }
        else if(view.getId() == R.id.selectImage){

        }
        else if(view.getId() == R.id.faceRecognition){

        }
    }

    public void init(){
        imgList = new ArrayList<Integer>(){
            {
                add(R.mipmap.dbimg_1);
                add(R.mipmap.dbimg_2);
                add(R.mipmap.dbimg_3);
                add(R.mipmap.dbimg_4);
                add(R.mipmap.dbimg_5);
                add(R.mipmap.dbimg_6);
                add(R.mipmap.dbimg_7);
                add(R.mipmap.dbimg_8);
                add(R.mipmap.dbimg_9);
            }
        };
    }

    /**
     * 注册
     */
    public void register(){
        TextView textView = findViewById(R.id.textView2);
        int i = 0;
        for(Integer id:imgList){
            i++;
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
            SeetaImageData seetaImageData = SeetaUtil.ConvertToSeetaImageData(bitmap);
            // 1. 识别人脸区域
            SeetaRect[] seetaRects = SeetaHelper.getInstance().faceDetector2.Detect(seetaImageData);
            if (seetaRects == null || seetaRects.length == 0){
                textView.setText(String.format("注册第%d个图片,no face...",i));
                Log.e("regiger","未识别到头像...");
                continue;
            }
            // 2. 提取头像关键点（最大头像）
            SeetaPointF[] seetaPoints = SeetaHelper.getInstance().pointDetector2.Detect(seetaImageData, seetaRects[0]);
            // 3. 注册头像
            int registedFaceIndex = SeetaHelper.getInstance().faceRecognizer2.Register(seetaImageData,seetaPoints);
            imgRegisterMap.put(registedFaceIndex,id);
            textView.setText(String.format("注册第%d个图片,ok...",i));
        }
        Toast.makeText(FaceCompareNActivity.this, "初始化完成...", Toast.LENGTH_SHORT).show();
    }
}