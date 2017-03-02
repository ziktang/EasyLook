package com.example.tctctc.easylook.ganhuojizhongying;


import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.example.tctctc.easylook.R;
import com.example.tctctc.easylook.ganhuojizhongying.Model.CateGanHuo;
import com.example.tctctc.easylook.ganhuojizhongying.Model.DayGanHuo;
import com.google.gson.Gson;
import java.util.List;
import rx.Observer;

/**
 * Created by tctctc on 2016/9/23.
 */

public class GanHuoPresenter implements GanHuoContract.Presenter {

    private GanHuoContract.GanHuoData mGanHuoSource;
    private GanHuoContract.View mView;
    private int page = 1;
    private Observer<CateGanHuo> observer;
    private Gson mGson;
    private int type;
    public GanHuoPresenter(GanHuoContract.GanHuoData ganHuoSource, GanHuoContract.View view, final int type){
        mGanHuoSource = ganHuoSource;
        mView = view;
        mView.setPresenter(this);
        mGson = new Gson();
        this.type = type;
        observer = new Observer<CateGanHuo>() {
            @Override
            public void onCompleted() {}
            @Override
            public void onError(Throwable e) {
                    mView.NetError();
            }
            @Override
            public void onNext(CateGanHuo cateGanHuo) {
                mView.show(cateGanHuo.getResults());
                String data = mGson.toJson(cateGanHuo);
                mGanHuoSource.saveCacheData(GhListFragment.cates[type],data);
            }
        };
    }

    @Override
    public void loadMore() {
        if (type < 2){
            return;
        }
        page++;
        String cate = GhListFragment.cates[type];
        mGanHuoSource.getCateList(cate,10,page)
                .subscribe(new Observer<CateGanHuo>() {
                    @Override
                    public void onCompleted() {}
                    @Override
                    public void onError(Throwable e) {
                            mView.NetError();
                    }

                    @Override
                    public void onNext(CateGanHuo cateGanHuo) {
                        mView.showMoreCate(cateGanHuo.getResults());
                    }
                });

    }

    @Override
    public void RefreshData() {
        mView.startRefresh();
        switch (type){
            case 0:
                mGanHuoSource.getDayList()
                        .subscribe(new Observer<DayGanHuo>() {
                            @Override
                            public void onCompleted() {}
                            @Override
                            public void onError(Throwable e) {
                                    mView.NetError();
                            }
                            @Override
                            public void onNext(DayGanHuo dayGanHuo) {
                                mView.show(dayGanHuo.getResults());
                                String data = mGson.toJson(dayGanHuo);
                                mGanHuoSource.saveCacheData(GhListFragment.cates[type],data);
                            }
                        });
                break;
            case 1:
                mGanHuoSource.getRandomList("all",10)
                        .subscribe(observer);
                break;
            default:
                page = 1;
                String cate = GhListFragment.cates[type];
                mGanHuoSource.getCateList(cate,10,1)
                        .subscribe(observer);
                break;
        }
    }

    @Override
    public void initData() {
        mView.startRefresh();
        String key = GhListFragment.cates[type];
        List<CateGanHuo.CateBean> cateBeanList = mGanHuoSource.getCacheData(key);
        if (cateBeanList!=null){
            mView.show(cateBeanList);
        }
        RefreshData();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void GoToDetail(View view, Context context, CateGanHuo.CateBean cateBean) {
        if (type == 8){
            Intent intent = new Intent(context,LargePicActivity.class);
            intent.putExtra("bean",cateBean);
            context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation((GanHuoMainActivity)context,view, "mzImg").toBundle());
        }else{
            Intent intent = new Intent(context,DetailActivity.class);
            intent.putExtra("bean",cateBean);
            context.startActivity(intent);
            ((Activity)context).overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
        }
    }
    @Override
    public void start() {

    }
}
