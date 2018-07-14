package com.star.demo.camera;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.star.demo.R;

import java.io.File;

public class CameraDemoActivity extends Activity implements OnClickListener {
    private Button takePhoto, choosePhoto;

    private ImageView imgView;

    private Uri imgUri;

    private String imgPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        takePhoto = (Button) findViewById(R.id.take_photo);
        choosePhoto = (Button) findViewById(R.id.choose_photo);
        imgView = (ImageView) findViewById(R.id.photo_to_dis);

        takePhoto.setOnClickListener(this);
        choosePhoto.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (1 == resultCode) {
                String sdStatus = Environment.getExternalStorageState();
                if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
                    // 检测sd是否可用
                    Log.i("TestFile",
                            "SD card is not avaiable/writeable right now.");
                    return;
                }
                imgView.setImageURI(imgUri);// 将图片显示在ImageView里
            } else if (2 == requestCode) {
                Uri uri = data.getData();
                imgView.setImageURI(uri);// 将图片显示在ImageView里
                Cursor cursor = this.getContentResolver().query(uri,
                        null,
                        null,
                        null,
                        null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                imgPath = cursor.getString(columnIndex);
                cursor.close();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                String rootPath = Environment.getExternalStorageDirectory()
                        + "/IMG_DEMO/";
                String imgName = System.currentTimeMillis() + ".jpg";
                imgPath = rootPath + imgName;

                File root = new File(rootPath);
                File img = new File(imgPath);

                imgUri = Uri.fromFile(img);

                if (!root.exists() || !root.isDirectory()) {
                    root.mkdirs();
                }

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imgUri);//如果传入输出路径uri则onaciivityResult中不返回数据
                startActivityForResult(intent, 1);
                break;
            case R.id.choose_photo:
                Intent cIntent = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(cIntent, 2);
                break;
            default:
                break;
        }
    }
}
