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

import java.util.List;

public class FaceReActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private Bitmap bitmap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_re);
    }

    public void onClick(View view){
        Log.i("FaceReActivity","clicked...");
        if(view.getId() == R.id.button3){
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
                    //detectFace()
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
}