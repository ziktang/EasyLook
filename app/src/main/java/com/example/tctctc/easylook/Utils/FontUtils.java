package com.example.tctctc.easylook.Utils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by tctctc on 2016/9/21.
 */
public class FontUtils {
    private static Typeface typeface;
    public static Typeface getTypeface(Context context){
        if (typeface == null){
            typeface = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        }
        return typeface;
    }

}
