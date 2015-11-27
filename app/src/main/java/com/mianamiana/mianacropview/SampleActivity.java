package com.mianamiana.mianacropview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SampleActivity extends AppCompatActivity {

    private Bitmap mCropBitmap;
    public static final int CONTAINER = R.id.fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(CONTAINER, new CropFragment())
                .commitAllowingStateLoss();
    }


    public Bitmap getCropBitmap() {
        return mCropBitmap;
    }

    public void setCropBitmap(Bitmap bm) {
        mCropBitmap = bm;
    }
}

