package com.example.francisco.finalprojectweek1;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

;

public class CameraProject extends AppCompatActivity {
    public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 1777;
    private static final String TAG = "TAG";
    private ImageButton imageView;
    Bitmap bitmap;
    String filepath = "";

    String source = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cameraproject);

        imageView = (ImageButton) findViewById(R.id.imageView1);

        //Change Title
        setTitle("Camera project");

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(intent, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(bitmap!=null) {
            outState.putString("img", "uploaded");
            outState.putString("source", source);
            outState.putString("filepath", filepath);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        try {
            if (savedInstanceState.getString("img").equals("uploaded")) {
                filepath = savedInstanceState.getString("filepath");
                File file = new File(filepath);
                source = savedInstanceState.getString("source");
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //Landscape do some stuff

                    if(!savedInstanceState.getString("source").equals("landscape")){
                        bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 768, 1024);
                        Matrix matrix = new Matrix();
                        matrix.postRotate(90);
                        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        imageView.setImageBitmap(bitmap2);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                    else {
                        bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1024, 768);
                        imageView.setImageBitmap(bitmap);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                } else {
                    //portrait

                    //rotates img
                    if(savedInstanceState.getString("source").equals("portrait")) {
                        bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 768, 1024);
                        Matrix matrix = new Matrix();
                        matrix.postRotate(90);
                        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                        imageView.setImageBitmap(bitmap2);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                    else {
                        bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1024 , 768);
                        imageView.setImageBitmap(bitmap);
                        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    }
                }
            }
        }catch(Exception ex){}
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE) {
            //Get our saved file into a bitmap object:

            File file = new File(Environment.getExternalStorageDirectory() + File.separator +
                    "image.jpg");
            filepath = file.getAbsolutePath();
            Log.d(TAG, "onActivityResult: "+file.getAbsolutePath());

            if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                //Landscape do some stuff
                source = "landscape";
                bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 768, 1024);
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }
            else{
                //portrait
                bitmap = decodeSampledBitmapFromFile(file.getAbsolutePath(), 1024 , 768);
                //rotates img
                source = "portrait";
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                imageView.setImageBitmap(bitmap2);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            }



        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight)
    { // BEST QUALITY MATCH

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth)
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }
}
