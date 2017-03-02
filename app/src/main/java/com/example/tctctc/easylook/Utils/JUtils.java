package com.example.tctctc.easylook.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import com.bumptech.glide.Glide;

/**
 * Created by Administrator on 2016/8/15.
 */
public class JUtils {

    private static Context mApplicationContext;


    public static void initialize(Application app){
        mApplicationContext = app.getApplicationContext();
    }
    /**
     * 取屏幕宽度
     * @return
     */
    public static int getScreenWidth(){
        DisplayMetrics dm = mApplicationContext.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static float getScreenDensity(Context context){
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * 判断当前是否有网络连接
     * @param
     * @return
     */
    public static boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) mApplicationContext
                .getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            return true;
        }
        return false;
    }

    public static int DP2PX(int DPsize) {
        float scale = mApplicationContext.getResources().getDisplayMetrics().density;
        return (int)(scale*DPsize+0.5f*(DPsize>=0?1:-1));
    }

}
