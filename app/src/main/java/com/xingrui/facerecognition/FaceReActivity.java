package com.xingrui.facerecognition;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaRect;
import com.seeta.sdk.util.SeetaHelper;
import com.seeta.sdk.util.SeetaUtil;

import java.util.List;

public class FaceReActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private Bitmap bitmap2;
    private SeetaImageData seetaImageData;
    private boolean hasFace = false;
    private SeetaRect[] seetaRects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_re);
    }

    public void onClick(View view){
        Log.i("FaceReActivity","clicked...");
        if(view.getId() == R.id.button3){
            if(hasFace){
                Log.i("FaceReActivity","hasFace...");
            }
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .maxSelectNum(1)
                    .forResult(1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case 1:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    bitmap = BitmapFactory.decodeFile(selectList.get(0).getPath());
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageBitmap(bitmap);
                    detectFace();
                    break;
                case 2: {
                    List<LocalMedia> selectList2 = PictureSelector.obtainMultipleResult(data);
                    bitmap2 = BitmapFactory.decodeFile(selectList2.get(0).getPath());
                    //img2.setImageBitmap(bitmap2)
                    break;
                }
            }
        }
    }

    private void detectFace(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                seetaImageData = SeetaUtil.ConvertToSeetaImageData(bitmap);
                seetaRects = SeetaHelper.getInstance().faceDetector2.Detect(seetaImageData);
                if (seetaRects == null || seetaRects.length == 0) {
                    Log.i("detectFace","没有检测到人脸");
                    hasFace = false;
                }
                hasFace = true;
            }
        }).start();
    }
}