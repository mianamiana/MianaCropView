package com.mianamiana.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class PxUtil {

	public static int getDip(Context context, int resId) {
		return Math.round(context.getResources().getDimension(resId));
	}

	public static int getDip(Resources res, int resId) {
		return Math.round(res.getDimension(resId));
	}

	public static int dip2Px(Resources res, int dip) {

		DisplayMetrics dm = res.getDisplayMetrics();
		return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, dm));
	}

}
