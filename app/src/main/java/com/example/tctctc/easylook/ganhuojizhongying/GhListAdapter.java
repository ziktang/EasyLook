package com.example.tctctc.easylook.ganhuojizhongying;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tctctc.easylook.CustomView.AjustImageView;
import com.example.tctctc.easylook.R;
import com.example.tctctc.easylook.Utils.FontUtils;
import com.example.tctctc.easylook.ganhuojizhongying.Model.CateGanHuo;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import rx.functions.Action1;



/**
 * Created by tctctc on 2016/9/23.
 */

public class GhListAdapter extends RecyclerView.Adapter<GhListAdapter.BaseViewholder> {

    private String[] categarys = {"Android", "iOS", "休息视频", "拓展资源", "福利", "瞎推荐", "前端"};
    private String[] iconString = {"\ue603", "\ue605", "\ue606", "\ue607", "\ue604", "\ue608", "\ue602"};
    private String[] iconColor = {"#11cd6e", "#eb4f38", "#605858", "#9d55b8", "#ea8010", "#33475f", "#9d55b8"};

    private int mType;
    private List<CateGanHuo.CateBean> mGanHuoBeanList;
    private GanHuoItemListener mGanHuoItemListener;
    private Context mContext;
    private boolean animationsLocked = false;
    private int lastAnimatedPositio = -1;
    private boolean delayEnterAnimation = true;

    public GhListAdapter(List<CateGanHuo.CateBean> cateBeanList, Context context, int type, GanHuoItemListener ganHuoItemListener) {
        mGanHuoBeanList = cateBeanList;
        mType = type;
        mGanHuoItemListener = ganHuoItemListener;
        mContext = context;
    }

    @Override
    public BaseViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mType == 8) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mz, parent, false);
            return new GhFlViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gan_huo_item, parent, false);
            return new GhViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final BaseViewholder holder, final int position) {
        holder.binData(mGanHuoBeanList.get(position));
        RxView.clicks(holder.itemView).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                GhFlViewHolder ghFlViewHolder = null;
                if (holder instanceof GhFlViewHolder){
                    ghFlViewHolder = (GhFlViewHolder) holder;
                    mGanHuoItemListener.itemClick(ghFlViewHolder.mImageView,mType,mGanHuoBeanList.get(position));
                }else{
                    mGanHuoItemListener.itemClick(holder.itemView,mType,mGanHuoBeanList.get(position));
                }
            }
        });
        runEnterAnimation(holder.itemView,position);
    }

    @Override
    public int getItemCount() {
        return mGanHuoBeanList.size();
    }

    private void runEnterAnimation(View view, int position) {
//        if (animationsLocked) return;//animationsLocked是布尔类型变量，一开始为false，确保仅屏幕一开始能够显示的item项才开启动画
//
//        if (position > lastAnimatedPositio) {//lastAnimatedPosition是int类型变量，一开始为-1，这两行代码确保了recycleview滚动式回收利用视图时不会出现不连续的效果
//            lastAnimatedPositio = position;
//            PropertyValuesHolder scaleXHolder = PropertyValuesHolder.ofFloat("scaleX",0.6F,1F);
//            PropertyValuesHolder scaleYHolder = PropertyValuesHolder.ofFloat("scaleY",0.6F,1F);
//            ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view,scaleXHolder,scaleYHolder);
//            objectAnimator.setDuration(800).addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                    Log.i("eee","onAnimationEnd");
//                    animationsLocked = true;//确保仅屏幕一开始能够显示的item项才开启动画，也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
//                }
//            });
//            objectAnimator.setInterpolator(new DecelerateInterpolator(0.5f));//设置动画效果为在动画开始的地方快然后慢
//            objectAnimator.start();
//        }
    }


    @Override
    public void onViewDetachedFromWindow(BaseViewholder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    public abstract class BaseViewholder extends RecyclerView.ViewHolder {
        public BaseViewholder(View itemView) {
            super(itemView);
        }
        public abstract void binData(CateGanHuo.CateBean cateBean);
    }

    public class GhViewHolder extends BaseViewholder {
        TextView mCateIcon;
        TextView mCateText;
        TextView mDecs;
        TextView mWho;
        TextView mDate;
        Typeface mTypeface;
        ImageView mImageView;
        LinearLayout mLinearLayout;

        public GhViewHolder(View itemView) {
            super(itemView);
            mLinearLayout = (LinearLayout) itemView.findViewById(R.id.cate_ll);
            mTypeface = FontUtils.getTypeface(itemView.getContext());
            mCateIcon = (TextView) itemView.findViewById(R.id.cateIcon);
            mCateText = (TextView) itemView.findViewById(R.id.cateText);
            mDecs = (TextView) itemView.findViewById(R.id.decs);
            mWho = (TextView) itemView.findViewById(R.id.who);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mImageView = (ImageView) itemView.findViewById(R.id.ghImg);
            if (mType < GhListFragment.ANDROID) {
                mLinearLayout.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void binData(CateGanHuo.CateBean cateBean) {
            if (mType < GhListFragment.ANDROID) {
                String type = cateBean.getType();
                for (int i = 0; i < categarys.length; i++) {
                    if (type.equals(categarys[i])) {
                        mCateIcon.setText(iconString[i]);
                        mCateIcon.setTextColor(Color.parseColor(iconColor[i]));
                        mCateText.setText(cateBean.getType());
                    }
                }
                mCateIcon.setTypeface(mTypeface);
            }
            String url = "";
            if (cateBean.getImages()!=null){
                url = cateBean.getImages().get(0);
            }
            Glide.with(mContext)
                    .load(url)
                    .crossFade()
                    .placeholder(R.drawable.place)
                    .error(R.drawable.place)
                    .into(mImageView);

            mDecs.setText(cateBean.getDesc());
            mWho.setText(cateBean.getWho());
            mDate.setText(cateBean.getCreatedAt().substring(0,10));
        }
    }

    public class GhFlViewHolder extends BaseViewholder {

        public AjustImageView mImageView;

        public GhFlViewHolder(View itemView) {
            super(itemView);
            mImageView = (AjustImageView) itemView.findViewById(R.id.mzImg);
            mImageView.setOriginalSize(1,1);
        }


        @Override
        public void binData(CateGanHuo.CateBean cateBean) {
            Glide.with(mContext)
                    .load(cateBean.getUrl())
                    .crossFade()
                    .animate(R.anim.mz)
                    .into(mImageView);
        }
    }

    public void add(List<CateGanHuo.CateBean> cateBeanList){
        mGanHuoBeanList.addAll(cateBeanList);
    }


    public interface GanHuoItemListener {
        void itemClick(View view,int type,CateGanHuo.CateBean cateBean);
    }

    public void clear(){
        mGanHuoBeanList.clear();
    }
}


