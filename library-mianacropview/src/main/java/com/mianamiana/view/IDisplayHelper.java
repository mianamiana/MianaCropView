package com.mianamiana.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.MotionEvent;

/**
 * Created by Mianamiana on 15/10/26.
 */
 interface IDisplayHelper {

    /**
     * draw bitmap , buffer area ,  mesh
     *
     */
    void draw(Canvas canvas);

    boolean processTouchEvent(MotionEvent event);

    void setBitmap(Bitmap bitmap);

    /**
     * 设置剪切的区域
     *
     */
    void setCropArea(RectF rectF);


    boolean isFillMode();

    void setIsFillMode(boolean isFillMode);

    Bitmap getCroppedBitmap();

    void setOnFillableChangeListener(ICropView.OnFillableChangeListener l);

    void setIsAdvancedMode(boolean isAdvancedMode);

}
