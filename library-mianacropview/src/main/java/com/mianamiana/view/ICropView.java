package com.mianamiana.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;

/**
 * Created by Mianamiana on 15/10/26.
 */
public interface ICropView {


    boolean isFillMode();

    void setFillMode(boolean isFillMode);

    Bitmap getCroppedBitmap();


    void setCropType(CopyType type);

    void setIsAdvancedMode(boolean isAdvancedMode);


    void setImageBitmap(Bitmap bitmap);

    void setImageDrawable(Drawable drawable);

    void setImageResource(@DrawableRes int resId);

    void setImageUri(Uri uri);

    void setImageFile(String path);

    void setOnFillableChangeListener(OnFillableChangeListener l);

    interface OnFillableChangeListener {
        void onFillableChange(boolean isFillable);
    }
}
