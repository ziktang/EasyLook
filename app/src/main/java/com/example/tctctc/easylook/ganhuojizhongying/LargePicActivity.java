package com.example.tctctc.easylook.ganhuojizhongying;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.tctctc.easylook.Base.BaseActivity;
import com.example.tctctc.easylook.CustomView.AjustImageView;
import com.example.tctctc.easylook.R;
import com.example.tctctc.easylook.ganhuojizhongying.Model.CateGanHuo;
import com.jakewharton.rxbinding.view.RxView;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.R.attr.factor;
import static android.R.attr.path;
import static android.R.attr.type;
import static com.example.tctctc.easylook.ganhuojizhongying.GhListFragment.cates;

public class LargePicActivity extends BaseActivity {

    private static final int IMG_SHARE = 1;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img)
    AjustImageView mImg;
    private CateGanHuo.CateBean cateBean;
    private String share_path;
    private File filePath;
    private File shareFile;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.picture_bg));
        setContentView(R.layout.activity_large_pic);
        ButterKnife.bind(this);
        cateBean = (CateGanHuo.CateBean) getIntent().getSerializableExtra("bean");
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(cateBean.getDesc());

        Glide.with(this)
                .load(cateBean.getUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        mImg.setOriginalSize(resource.getWidth(), resource.getHeight());
                        mImg.setImageBitmap(resource);
                    }
                });

        RxView.clicks(mImg).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.large_pic_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.keep:
                keepImg();
                return true;
            case R.id.share_img:
                shareImg();
                return true;
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public boolean copyImage(File file, File newFile) {
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            int byteread = 0;
            if (file.exists()) { //文件存在时
                inStream = new FileInputStream(file); //读入原文件
                fs = new FileOutputStream(newFile);
                byte[] buffer = new byte[1024];
                while ((byteread = inStream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void shareIntent(Activity activity, ArrayList<Uri> uriList) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND_MULTIPLE);
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uriList);
        shareIntent.setType("image/*");
        activity.startActivityForResult(shareIntent, IMG_SHARE);
    }


    public void keepImg() {
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                try {
                    File file = Glide.with(LargePicActivity.this).load(cateBean.getUrl()).downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    if (file.exists() && file.isFile()) {
                        File newFile = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), file.getName() + ".jpg");
                        if (newFile.exists()){
                            subscriber.onNext(newFile);
                            return;
                        }
                        if (copyImage(file, newFile)) {
                            subscriber.onNext(newFile);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<File>() {
            @Override
            public void onCompleted() {}
            @Override
            public void onError(Throwable e) {
                Toast.makeText(LargePicActivity.this, "图片保存失败", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNext(File file) {
                if (file.exists()){
                    Toast.makeText(LargePicActivity.this, "图片保存在" + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void shareImg(){
        Observable.create(new Observable.OnSubscribe<File>() {
            @Override
            public void call(Subscriber<? super File> subscriber) {
                try {
                    File file = Glide.with(LargePicActivity.this)
                            .load(cateBean.getUrl())
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                    if (file.exists() && file.isFile()) {
                        shareFile = new File(getExternalCacheDir(), file.getName() + ".jpg");
                        if (shareFile.exists()){
                            subscriber.onNext(shareFile);
                            return;
                        }
                        if (copyImage(file, shareFile)) {
                            subscriber.onNext(shareFile);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<File>() {
            @Override
            public void onCompleted() {}
            @Override
            public void onError(Throwable e) {
                Toast.makeText(LargePicActivity.this, "分享失败", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNext(File file) {
                if (file.exists()){
                    Uri uri = Uri.fromFile(file);
                    ArrayList<Uri> uris = new ArrayList<Uri>();
                    uris.add(uri);
                    shareIntent(LargePicActivity.this, uris);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMG_SHARE){
            shareFile.delete();
        }
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("LargePicActivity"); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("LargePicActivity");
    }
}
