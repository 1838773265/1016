package com.example.p2p.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p2p.R;
import com.example.p2p.utils.BitmapUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MoreActivity extends AppCompatActivity {

    private ImageView imgMore;
    private ImageView imgTouxiang;
    private TextView ttvChange;
    private Button btnOuto;

    private static final int PICTURE = 100;
    private static final int CAMERA = 200;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        imgMore = findViewById(R.id.img_more);
        imgTouxiang = findViewById(R.id.img_touxiang);
        ttvChange = findViewById(R.id.ttv_change);
        btnOuto = findViewById(R.id.btn_outo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"
            },1000);
        }

        ttvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String[] strings = new String[]{"图库","相机"};

                new AlertDialog.Builder(MoreActivity.this)
                        .setTitle("选择来源")
                        .setItems(strings, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                switch (which){
                                    case 0:
                                        Intent picture = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

//                                        Intent intent = new Intent();
//                                        intent.setAction(Intent.ACTION_PICK);//选择图片
//                                        intent.setType("image/*");//设置类型 所有图片


//                                        Intent intent = new Intent(Intent.ACTION_PICK, null);
//                                        intent.setDataAndType(
//                                                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                                                "image*/*");
//                                        //intent.setType("image/*");//开始没有添加这句报错


                                        startActivityForResult(picture,PICTURE);

                                        break;

                                    case 1:
                                        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(camera,CAMERA);
                                        break;
                                }

                            }
                        }).setCancelable(false)
                        .show();

            }
        });

        btnOuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //1.将保存在sp中的数据清除
                SharedPreferences sp = getSharedPreferences("1802a", Context.MODE_PRIVATE);
                sp.edit().clear().commit();//清除数据操作必须提交；提交以后，文件仍存在，只是文件中的数据被清除了
                //2.将本地保存的图片的file删除
                File filesDir;
                if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断sd卡是否挂载
                    //路径1：storage/sdcard/Android/data/包名/files
                    filesDir = getExternalFilesDir("");

                }else{//手机内部存储
                    //路径：data/data/包名/files
                    filesDir = getFilesDir();

                }
                File file = new File(filesDir,"icon.png");
                if(file.exists()){
                    file.delete();//删除存储中的文件
                }
                finish();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 1000 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "同意", Toast.LENGTH_SHORT).show();
        }

    }

    //onRequestPermissionsResult

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA && resultCode == RESULT_OK && data != null){
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            Bitmap zoom = BitmapUtils.zoom(bitmap, imgTouxiang.getWidth(), imgTouxiang.getHeight());

            Bitmap bitmap1 = BitmapUtils.circleBitmap(zoom);

            imgTouxiang.setImageBitmap(bitmap1);
            Log.e("111",bitmap1.toString());
            saveImage(bitmap1);

        }else if (requestCode == PICTURE && resultCode == RESULT_OK && data != null){

            Uri uri = data.getData();

            String path = getPath(uri);

            Bitmap decodeFile = BitmapFactory.decodeFile(path);

            Bitmap zoom = BitmapUtils.zoom(decodeFile, imgTouxiang.getWidth(), imgTouxiang.getHeight());

            Bitmap bitmap = BitmapUtils.circleBitmap(zoom);
            Log.e("111",bitmap.toString());
            imgTouxiang.setImageBitmap(bitmap);

            saveImage(bitmap);


        }

    }

    private void saveImage(Bitmap bitmap){

        File filesDir;
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){//判断sd卡是否挂载
            //路径1：storage/sdcard/Android/data/包名/files
            filesDir = this.getExternalFilesDir("");

        }else{//手机内部存储
            //路径：data/data/包名/files
            filesDir = this.getFilesDir();

        }
        FileOutputStream fos = null;
        try {
            File file = new File(filesDir,"icon.png");
            fos = new FileOutputStream(file);

            bitmap.compress(Bitmap.CompressFormat.PNG, 100,fos);

            SharedPreferences sp = getSharedPreferences("1802a", Context.MODE_PRIVATE);

            sp.edit().putString("img","1").commit();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally{
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private String getPath(Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        //高于4.4.2的版本
        if (sdkVersion >= 19) {
            Log.e("TAG", "uri auth: " + uri.getAuthority());
            if (isExternalStorageDocument(uri)) {
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(id));
                return getDataColumn(this, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(this, contentUri, selection, selectionArgs);
            } else if (isMedia(uri)) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor actualimagecursor = this.managedQuery(uri, proj, null, null, null);
                int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                actualimagecursor.moveToFirst();
                return actualimagecursor.getString(actual_image_column_index);
            }


        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();
            return getDataColumn(this, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }


    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isMedia(Uri uri) {
        return "media".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


}
