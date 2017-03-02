package com.example.tctctc.easylook.Utils;


import java.text.SimpleDateFormat;

/**
 * Created by tctctc on 2016/9/24.
 */

public class TransDate {

    private static SimpleDateFormat mDateFormat;

    private static SimpleDateFormat getDateFormat(){
        if (mDateFormat == null){
            mDateFormat = new SimpleDateFormat("yyyy--MM-dd");
        }
        return mDateFormat;
    }

}
