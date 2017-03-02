package com.example.tctctc.easylook.ganhuojizhongying;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.example.tctctc.easylook.Config.MyApplication;
import com.example.tctctc.easylook.CustomView.PullLoadRecycleView;
import com.example.tctctc.easylook.R;
import com.example.tctctc.easylook.ganhuojizhongying.Model.CateGanHuo;
import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.view.RxView;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.plugins.RxAndroidPlugins;
import rx.functions.Action1;

import static android.R.attr.delay;
import static android.R.attr.duration;
import static com.example.tctctc.easylook.R.id.fab;

/**
 * Created by tctctc on 2016/9/23.
 */

public class GhListFragment extends Fragment implements GanHuoContract.View, SwipeRefreshLayout.OnRefreshListener,PullLoadRecycleView.PullLoadListenner{

    public static int DAY = 0;
    public static int RANDOM = 1;
    public static int RECOMM = 2;
    public static int ANDROID = 3;
    public static int IOS = 4;
    public static int RELAX_VEDIO = 5;
    public static int EXTRA_RESOURCE = 6;
    public static int QD = 7;
    public static int WELFARE = 8;

    public static String[] cates = {"每日", "彩蛋", "all", "Android", "iOS", "休息视频", "拓展资源", "前端", "福利"};

    public int type;
    @BindView(R.id.ganHuoRecycle)
    PullLoadRecycleView mGanHuoRecycle;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    private GhListAdapter mGhListAdapter;
    private GanHuoContract.Presenter mGanHuoPresenter;
    private List<CateGanHuo.CateBean> mCateBeanList;
    private Context mContext;


    public static GhListFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt("type", type);
        GhListFragment fragment = new GhListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt("type");
        }
        mContext = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_han_huo, container, false);
        ButterKnife.bind(this, view);
        new GanHuoPresenter(new GanHuoSource(getContext()), this,type);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        mGanHuoPresenter.start();
        mGanHuoPresenter.initData();
    }

    @Override
    public void showMoreCate(List<CateGanHuo.CateBean> ganHuoBeanList) {
        mGhListAdapter.add(ganHuoBeanList);
        mGhListAdapter.notifyDataSetChanged();
        stopRefresh();
    }

    @Override
    public void show(List<CateGanHuo.CateBean> ganHuoBeanList) {
        mGhListAdapter.clear();
        mGhListAdapter.add(ganHuoBeanList);
        mGhListAdapter.notifyDataSetChanged();
        stopRefresh();
            mGanHuoRecycle.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopRefresh() {
        mSwipeRefresh.setRefreshing(false);
        mGanHuoRecycle.setLoading(false);
    }

    @Override
    public void startRefresh() {
        mSwipeRefresh.setRefreshing(true);
        mGanHuoRecycle.setLoading(true);
    }


    @Override
    public void NetError() {
        Snackbar.make(mGanHuoRecycle,"请检查网络",Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(GanHuoContract.Presenter presenter) {
        mGanHuoPresenter = presenter;
    }

    @Override
    public void onRefresh() {
        mGanHuoPresenter.RefreshData();
    }

    private void initView() {
        final FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
//        RxView.clicks(fab).subscribe(new Action1<Void>() {
//            @Override
//            public void call(Void aVoid) {
//
//
//            }
//        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mRecycleView.smoothScrollBy(distance,duration);mRecycleView.smoothScrollOffset(offset);这两种也可以
                mGanHuoRecycle.smoothScrollToPosition(0);
                Log.i("zzz","smoothScrol66666lToPosition"+cates[type]);
            }
        });
        GhListAdapter.GanHuoItemListener itemListener = new GhListAdapter.GanHuoItemListener() {
            @Override
            public void itemClick(View view,int type,CateGanHuo.CateBean cateBean) {
                    mGanHuoPresenter.GoToDetail(view,getContext(),cateBean);
            }
        };

        mGanHuoRecycle.setLoadListenner(this);
        mSwipeRefresh.setOnRefreshListener(this);
        mSwipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright, android
                .R.color.holo_orange_light, android.R.color.holo_green_light, android.R.color
                .holo_red_dark);
        mCateBeanList = new ArrayList<>();
        mGhListAdapter = new GhListAdapter(mCateBeanList,getContext(), type, itemListener);
        if (type == 8){
            mGanHuoRecycle.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        }else{
            mGanHuoRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mGanHuoRecycle.setAdapter(mGhListAdapter);
    }

    @Override
    public void pullLoadMore() {
        mGanHuoPresenter.loadMore();
    }

    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("fragment_"+cates[type]); //统计页面，"MainScreen"为页面名称，可自定义
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("fragment_"+cates[type]);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        MyApplication.getRefWatcher(getActivity()).watch(this);
    }
}
