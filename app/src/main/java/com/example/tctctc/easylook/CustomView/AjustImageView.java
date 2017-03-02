package com.example.tctctc.easylook.CustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by tctctc on 2016/9/29.
 */

public class AjustImageView extends ImageView {

    private int originalWidth;
    private int originalHeight;

    public AjustImageView(Context context) {
        super(context);
    }

    public AjustImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AjustImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOriginalSize(int originalWidth, int originalHeight) {
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (originalWidth>0&&originalHeight>0){
            float scale = (float) originalWidth/(float)originalHeight;
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);
            if (width>0){
                height = (int) ((float)width/scale);
            }
            setMeasuredDimension(width,height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
