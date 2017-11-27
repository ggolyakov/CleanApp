package com.woolf.cleanapp.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.annotation.DrawableRes;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;

import java.util.Arrays;
import java.util.List;

public class ResUtils {

    private Context context;

    public ResUtils(Context context) {
        this.context = context;
    }

    public Bitmap getBitmapFromDrawable(@DrawableRes int drawableId) {
        Drawable drawable = getDrawable(drawableId);

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof VectorDrawable || drawable instanceof VectorDrawableCompat) {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return bitmap;
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }


    public String getString(int resId) {
        return context.getResources().getString(resId);
    }

    public boolean getBoolean(int resId) {
        return context.getResources().getBoolean(resId);
    }

    public List<String> getStringList(int resId) {
        return Arrays.asList(context.getResources().getStringArray(resId));
    }

    public int getInt(int resId) {
        return context.getResources().getInteger(resId);
    }

    public int getColor(int resId) {
        return ContextCompat.getColor(context, resId);
    }

    public int getDimenInPx(int resId) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    public Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(context, resId);
    }
}
