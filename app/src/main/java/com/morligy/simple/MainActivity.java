package com.morligy.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import com.morligy.suspendlayout.view.SimpleViewPagerIndicator;
import com.morligy.suspendlayout.view.SuspendLayout;


public class MainActivity extends FragmentActivity implements SuspendLayout.SuspendScrollListener {
    private String[] mTitles = new String[] { "简介", "评价", "相关" };
    private SimpleViewPagerIndicator mIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private TabFragment[] mFragments = new TabFragment[mTitles.length];
    private SuspendLayout suspendLayout;
    private String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initDatas();
        initEvents();
    }

    private void initEvents()
    {
        mViewPager.setOnPageChangeListener(new OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels)
            {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {

            }
        });
        suspendLayout.setSuspendScrollListener(this);
    }

    private void initDatas()
    {
        mIndicator.setTitles(mTitles);

        for (int i = 0; i < mTitles.length; i++)
        {
            mFragments[i] = (TabFragment) TabFragment.newInstance(mTitles[i]);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            @Override
            public int getCount()
            {
                return mTitles.length;
            }

            @Override
            public Fragment getItem(int position)
            {
                return mFragments[position];
            }

        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    private void initViews()
    {
        suspendLayout = (SuspendLayout) findViewById(R.id.id_sticky_layout);
        mIndicator = (SimpleViewPagerIndicator) findViewById(R.id.id_stickynavlayout_indicator);
        mViewPager = (ViewPager) findViewById(R.id.id_stickynavlayout_viewpager);

		/*
		RelativeLayout ll = (RelativeLayout) findViewById(R.id.id_stickynavlayout_topview);
		TextView tv = new TextView(this);
		tv.setText("我的动态添加的");
		tv.setBackgroundColor(0x77ff0000);
		ll.addView(tv, new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, 600));
		*/
    }


    @Override
    public void scroll(double ScrollPercent) {
        Log.i(TAG, "scroll: "+ScrollPercent);
    }
}