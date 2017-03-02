package com.example.tctctc.easylook.Utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by tctctc on 2016/9/23.
 */

public class ActivityUtils {
    public static void addFragmentToActivity (FragmentManager fragmentManager,Fragment fragment, int frameId) {
        if (fragmentManager!=null&&fragment!=null){
            fragmentManager.beginTransaction().add(frameId,fragment).commit();
        }
    }
}
