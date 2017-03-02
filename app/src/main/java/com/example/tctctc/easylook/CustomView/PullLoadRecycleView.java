package com.example.tctctc.easylook.CustomView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by tctctc on 2016/9/28.
 */

public class PullLoadRecycleView extends RecyclerView {


    private PullLoadListenner mLoadListenner;
    private boolean isLoading;

    public PullLoadRecycleView(Context context) {
        super(context);
    }

    public PullLoadRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PullLoadRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public interface PullLoadListenner{
        void pullLoadMore();
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);
        //最后可见item的位置
        int lastVisiblePosition = 0;
        //判断布局管理
        if (getLayoutManager() instanceof StaggeredGridLayoutManager){
            StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) getLayoutManager();
            int[] position = manager.findLastCompletelyVisibleItemPositions(new int[manager.getSpanCount()]);
            lastVisiblePosition = position[position.length-1];
            Log.i("bbb","position:"+lastVisiblePosition);
        }else if (getLayoutManager() instanceof LinearLayoutManager){
            LinearLayoutManager manager = (LinearLayoutManager) getLayoutManager();
            lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition();
        }
        if (lastVisiblePosition == getAdapter().getItemCount() - 3&&!isLoading&&mLoadListenner!=null&&dy>0){
            Log.i("bbb","loadmore:");
            isLoading = true;
            mLoadListenner.pullLoadMore();
        }
    }

    public void setLoadListenner(PullLoadListenner loadListenner) {
        mLoadListenner = loadListenner;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }
}