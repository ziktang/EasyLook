package com.example.tctctc.easylook.ganhuojizhongying;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.tabs;

/**
 * Created by tctctc on 2016/9/23.
 */

public class GhPagerAdapter extends FragmentPagerAdapter {

    private String[] mTabs;
    private List<Fragment> mFragments;
    public GhPagerAdapter(FragmentManager fm,String[] tabs){
        super(fm);
        mTabs = tabs;
        mFragments = new ArrayList<>();
        for (int i = 0; i < tabs.length; i++) {
            mFragments.add(GhListFragment.newInstance(i));
        }
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mTabs.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabs[position];
    }
}
