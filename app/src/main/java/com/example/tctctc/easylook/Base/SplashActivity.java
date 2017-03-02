package com.example.tctctc.easylook.Base;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.example.tctctc.easylook.R;
import com.example.tctctc.easylook.ganhuojizhongying.GanHuoMainActivity;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash)
    ImageView mSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //友盟禁止默认的页面统计方式，这样将不会再自动统计Activity
        MobclickAgent.openActivityDurationTrack(false);
        //设置是否对日志信息进行加密, 默认false(不加密)
        MobclickAgent.enableEncrypt(true);//6.0.0版本及以后
        MobclickAgent.setDebugMode( true );
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        ViewPropertyAnimation.Animator scaleAnimation = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX",1F,1.2F);
                PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY",1F,1.2F);
                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view,scaleXHolder,scaleYHolder);
                objectAnimator.setDuration(4000).addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        startToMainActivity();
                    }
                });
                objectAnimator.start();
            }

        };


        Glide.with(this)
                .load(R.drawable.splash)
                .animate(scaleAnimation)
                .into(mSplash);
    }

    private void startToMainActivity() {
        startActivity(new Intent(this, GanHuoMainActivity.class));
        finish();
    }
}
