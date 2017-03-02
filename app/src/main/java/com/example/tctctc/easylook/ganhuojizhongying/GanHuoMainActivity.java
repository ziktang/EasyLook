package com.example.tctctc.easylook.ganhuojizhongying;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tctctc.easylook.Base.BaseActivity;
import com.example.tctctc.easylook.Config.MyApplication;
import com.example.tctctc.easylook.R;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.R.attr.type;
import static com.example.tctctc.easylook.ganhuojizhongying.GhListFragment.cates;

public class GanHuoMainActivity extends AppCompatActivity {

    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    String[] tabs;
    @BindView(R.id.ghTab)
    TabLayout mGhTab;
    @BindView(R.id.ghViewPager)
    ViewPager mGhViewPager;
    private GhPagerAdapter mGhPagerAdapter;
    private long lastBackTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gan_huo_main);
        ButterKnife.bind(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);

        setDrawerContent(mNavView);
        setViewPager();
    }

    private void setViewPager() {
        tabs = getResources().getStringArray(R.array.ghTab);
        mGhTab.setSelectedTabIndicatorColor(getResources().getColor(R.color.deep_orange));
        mGhPagerAdapter = new GhPagerAdapter(getSupportFragmentManager(),tabs);
        mGhViewPager.setOffscreenPageLimit(tabs.length);
        mGhViewPager.setAdapter(mGhPagerAdapter);
        mGhTab.setupWithViewPager(mGhViewPager);
    }


    private void setDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.head:
                        Snackbar.make(mToolbar, "首页", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.sea_gh:
                        Snackbar.make(mToolbar, "搜索", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.person_like:
                        Snackbar.make(mToolbar, "收藏", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.set_up:
                        Snackbar.make(mToolbar, "设置", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.share:
                        Snackbar.make(mToolbar, "分享", Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.Feedback:
                        Snackbar.make(mToolbar, "反馈", Snackbar.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                mDrawer.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - lastBackTime) > 2000){
            Toast.makeText(this,"再点一次退出", Toast.LENGTH_SHORT).show();
            lastBackTime = System.currentTimeMillis();
        }else{
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
