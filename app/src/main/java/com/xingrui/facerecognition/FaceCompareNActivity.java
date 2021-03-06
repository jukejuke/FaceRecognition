package com.xingrui.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;
import com.seeta.sdk.util.SeetaHelper;
import com.seeta.sdk.util.SeetaUtil;
import com.xingrui.facerecognition.storage.RegisterInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaceCompareNActivity extends AppCompatActivity {

    private List<Integer> imgList = null;
    //private Map<Integer,Integer> imgRegisterMap = new HashMap<Integer,Integer>();
    private Bitmap bitmapM;
    private Bitmap bitMapS;
    private ProgressBar pb;
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_compare_n);
        init();
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        tvInfo = findViewById(R.id.textView2);
    }

    public void onClick(View view){
        if(view.getId() == R.id.raceRecogineerInit){
            register2();
        }
        else if(view.getId() == R.id.selectImage){
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .maxSelectNum(1)
                    .forResult(1);
        }
        else if(view.getId() == R.id.faceRecognition){
            recognize();
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
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
//                add(R.mipmap.dbimg_1);
//                add(R.mipmap.dbimg_2);
//                add(R.mipmap.dbimg_3);
//                add(R.mipmap.dbimg_4);
//                add(R.mipmap.dbimg_5);
//                add(R.mipmap.dbimg_6);
//                add(R.mipmap.dbimg_7);
//                add(R.mipmap.dbimg_8);
//                add(R.mipmap.dbimg_9);
                add(R.mipmap.dbimg_01);
                add(R.mipmap.dbimg_02);
                add(R.mipmap.dbimg_10);
            }
        };
        bitmapM = BitmapFactory.decodeResource(getResources(),R.mipmap.timg);
        ImageView imageView = findViewById(R.id.imageView4);
        imageView.setImageBitmap(bitmapM);
    }

    /**
     * 注册2
     */
    public void register2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.VISIBLE);
                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FaceCompareNActivity.this, "init...", Toast.LENGTH_SHORT).show();
                    }
                });
                int i = 0;
                for(Integer id:imgList) {
                    i++;
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), id);
                    SeetaImageData seetaImageData = SeetaUtil.ConvertToSeetaImageData(bitmap);
                    // 1. 识别人脸区域
                    SeetaRect[] seetaRects = SeetaHelper.getInstance().faceDetector2.Detect(seetaImageData);
                    int imgId = i;
                    if (seetaRects == null || seetaRects.length == 0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tvInfo.setText(String.format("注册第%d个图片,no face...",imgId));
                            }
                        });
                        //Log.e("regiger","未识别到头像...");
                        continue;
                    }
                    // 2. 提取头像关键点（最大头像）
                    SeetaPointF[] seetaPoints = SeetaHelper.getInstance().pointDetector2.Detect(seetaImageData, seetaRects[0]);
                    // 3. 注册头像
                    int registedFaceIndex = SeetaHelper.getInstance().faceRecognizer2.Register(seetaImageData,seetaPoints);
                    RegisterInfo.imgRegisterMap.put(registedFaceIndex,id);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvInfo.setText(String.format("注册第%d个图片,Index:%d,ok...",imgId,registedFaceIndex));
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pb.setVisibility(View.GONE);
                    }
                });
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(FaceCompareNActivity.this, "初始化完成...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    /**
     * 注册
     */
    public void register(){
        pb.setVisibility(View.VISIBLE);
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
            RegisterInfo.imgRegisterMap.put(registedFaceIndex,id);
            textView.setText(String.format("注册第%d个图片,Index:%d,ok...",i,registedFaceIndex));
        }
        Toast.makeText(FaceCompareNActivity.this, "初始化完成...", Toast.LENGTH_SHORT).show();
        pb.setVisibility(View.GONE);
    }

    /**
     * 识别
     */
    public void recognize(){
        TextView textView = findViewById(R.id.textView2);
        long l = System.currentTimeMillis();
        float[] similarity = new float[1];//save the most similar face similarity value
        SeetaImageData seetaImageData = SeetaUtil.ConvertToSeetaImageData(bitmapM);
        SeetaRect[] seetaRects = SeetaHelper.getInstance().faceDetector2.Detect(seetaImageData);
        if (seetaRects == null || seetaRects.length == 0){
            textView.setText("未识别到头像...");
            Log.e("regiger","未识别到头像...");
            return;
        }
        SeetaPointF[] seetaPoints = SeetaHelper.getInstance().pointDetector2.Detect(seetaImageData, seetaRects[0]);
        int targetIndex = SeetaHelper.getInstance().faceRecognizer2.Recognize(seetaImageData,seetaPoints,similarity);
        long timeDiff = (System.currentTimeMillis() - l);
        if(similarity.length>0) {
            textView.setText(String.format("识别头像ID：%d,similarity:%f,时间：%d毫秒", targetIndex, similarity[0],timeDiff));
        }else{
            textView.setText(String.format("识别头像ID：%d,时间：%d毫秒", targetIndex, timeDiff));
        }
        int imgId = RegisterInfo.imgRegisterMap.get(targetIndex);
        bitMapS = BitmapFactory.decodeResource(getResources(),imgId);
        ImageView imageView = findViewById(R.id.imageView5);
        imageView.setImageBitmap(bitMapS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    bitmapM = BitmapFactory.decodeFile(selectList.get(0).getPath());
                    ImageView imageView = findViewById(R.id.imageView4);
                    imageView.setImageBitmap(bitmapM);
                    break;
            }
        }
    }
}