package com.xingrui.facerecognition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.VectorEnabledTintResources;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.seeta.sdk.SeetaImageData;
import com.seeta.sdk.SeetaPointF;
import com.seeta.sdk.SeetaRect;
import com.seeta.sdk.util.SeetaHelper;
import com.seeta.sdk.util.SeetaUtil;

import java.util.ArrayList;
import java.util.List;

public class FaceReActivity extends AppCompatActivity {

    private Paint paint;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private SeetaImageData seetaImageData;
    private boolean hasFace = false;
    private SeetaRect[] seetaRects;
    private ProgressDialog pd;
    private List<Float> similarity = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_re);
        initData();
    }

    private void initData() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
        detectFace();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        paint.setColor(Color.RED);
        paint.setTextSize(36.0f);
    }

    public void onClick(View view){
        Log.i("FaceReActivity","clicked...");
        if(view.getId() == R.id.button3){
            if(hasFace){
                Log.i("FaceReActivity","hasFace...");
            }
            hasFace = false;
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .maxSelectNum(1)
                    .forResult(1);
        }
        else if(view.getId() == R.id.button4){
            if (!hasFace){
                Toast.makeText(this, "没有检测到人脸", Toast.LENGTH_SHORT).show();
                return;
            }
            Bitmap out =bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(out);
            for(int i=0;i<seetaRects.length;i++){
                SeetaRect rect = seetaRects[i];
                canvas.drawRect(new Rect(rect.x, rect.y, rect.x+rect.width, rect.y+rect.height), paint);
                canvas.drawText(String.valueOf(i), rect.x + rect.width/2.0f, rect.y-15.0f, paint);
            }
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(out);
        }
        else if(view.getId() == R.id.button5){
            if (!hasFace){
                Toast.makeText(this, "没有检测到人脸", Toast.LENGTH_SHORT).show();
                return;
            }
            Bitmap out =bitmap.copy(Bitmap.Config.ARGB_8888, true);
            Canvas canvas = new Canvas(out);
            for(int i=0;i<seetaRects.length;i++){
                SeetaRect rect = seetaRects[i];
                canvas.drawRect(new Rect(rect.x, rect.y, rect.x+rect.width, rect.y+rect.height), paint);
                //根据检测到的人脸进行关键点定位
                SeetaPointF[] seetaPoints = SeetaHelper.getInstance().pointDetector2.Detect(seetaImageData, rect);
                if(seetaPoints != null && seetaPoints.length >0){
                    paint.setColor(Color.RED);
                    for (int j=0;j<seetaPoints.length;j++) { //绘制面部关键点
                        SeetaPointF seetaPoint = seetaPoints[j];
                        canvas.drawCircle(
                                (float)seetaPoint.x,
                                (float)seetaPoint.y,
                                5.0f,
                                paint
                        );
                    }
                }
            }
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(out);
        }
        else if(view.getId() == R.id.button6){
            PictureSelector.create(this)
                    .openGallery(PictureMimeType.ofImage())
                    .maxSelectNum(1)
                    .forResult(2);
        }
        else if(view.getId() == R.id.button7){
            compareTwo();
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
                    ImageView imageView2 = findViewById(R.id.imageView2);
                    imageView2.setImageBitmap(bitmap2);
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
                } else{
                    String info = "X:"+seetaRects[0].x+",Y:"+seetaRects[0].y;
                    Log.i("seetaRect",info);
                }
                hasFace = true;
            }
        }).start();
    }

    private void compareTwo(){
        TextView textView = findViewById(R.id.textView);
        if (seetaRects == null || seetaRects.length ==0){
            Toast.makeText(FaceReActivity.this, "第一张图片没有检测到人脸", Toast.LENGTH_SHORT).show();
            return;
        }
        SeetaImageData seetaImageData2 = SeetaUtil.ConvertToSeetaImageData(bitmap2);
        SeetaRect[] seetaRects2 = SeetaHelper.getInstance().faceDetector2.Detect(seetaImageData2);
        if (seetaRects2 == null || seetaRects2.length == 0){
            Toast.makeText(FaceReActivity.this, "第二张图片没有检测到人脸", Toast.LENGTH_SHORT).show();
            return;
        }

        showDia();
        Bitmap out = bitmap2.copy(Bitmap.Config.ARGB_8888, true);
        StringBuffer result = new StringBuffer();
        Canvas canvas = new Canvas(out);
        for(int i=0;i< seetaRects2.length;i++){
            SeetaRect rect = seetaRects2[i];
            canvas.drawRect(new Rect(rect.x, rect.y, rect.x + rect.width, rect.y + rect.height), paint);
            canvas.drawText(String.valueOf(i), rect.x + rect.width/2.0f, rect.y-15.0f, paint);
            //根据检测到的人脸进行面部关键点定位
            SeetaPointF[] seetaPoints = SeetaHelper.getInstance().pointDetector2.Detect(seetaImageData, seetaRects[0]); //第一张人脸
            SeetaPointF[] seetaPoints2 = SeetaHelper.getInstance().pointDetector2.Detect(seetaImageData2, rect);
            float sim = SeetaHelper.getInstance().faceRecognizer2.Compare(
                    seetaImageData,
                    seetaPoints,
                    seetaImageData2,
                    seetaPoints2
            );
            Log.i("FaceRecognizer2", "similarity:"+sim);
            similarity.add(sim);
        }
        for(int j=0;j<similarity.size();j++){
            if (result.length()>0)
                result.append(";\n");
            result.append( String.format("第%d 个similarity:%f",j,similarity.get(j)));
            Log.i("FaceRecognizer2", String.format("第%d 个similarity:%f",j,similarity.get(j)));
        }

        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setImageBitmap(out);
        textView.setText(result.toString());
        cancelDia();
        result.setLength(0);

    }

    private void showDia(){
        if (pd!=null && !pd.isShowing()){
            pd.show();
        }
    }

    private void cancelDia(){
        if (pd!=null && pd.isShowing()){
            pd.dismiss();
        }
    }
}