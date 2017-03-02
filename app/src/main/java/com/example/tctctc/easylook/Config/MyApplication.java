package com.example.tctctc.easylook.Config;

import android.app.Application;
import android.content.Context;

import com.example.tctctc.easylook.Utils.JUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by tctctc on 2016/9/24.
 */

public class MyApplication extends Application {

    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
        JUtils.initialize(this);
    }
}
