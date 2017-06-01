package com.morligy.simple.stickynavsimple.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gxz.PagerSlidingTabStrip;
import com.morligy.simple.R;
import com.morligy.simple.stickynavsimple.adapter.FragmentsViewPagerAdapter;
import com.morligy.simple.stickynavsimple.fragments.BaseFragment;
import com.morligy.simple.stickynavsimple.fragments.EmptyListViewFragment;
import com.morligy.simple.stickynavsimple.fragments.GridViewWithHeaderAndFooterFragment;
import com.morligy.simple.stickynavsimple.fragments.ListViewFragment;
import com.morligy.simple.stickynavsimple.fragments.RecycleViewFragment;
import com.morligy.simple.stickynavsimple.fragments.ScrollViewFragment;
import com.morligy.suspendlayout.view.StickyNavLayout;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author 顾修忠-guxiuzhong@youku.com/gfj19900401@163.com
 * @Title: BaseFragment
 * @Package com.gxz.stickynavlayout.fragments
 * @Description: 基类Fragment
 * @date 16/1/1
 * @time 下午13:17
 */
public class SimpleStickActivity extends AppCompatActivity {

    @Bind(R.id.id_stickynavlayout_indicator)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;
    @Bind(R.id.id_stick)
    StickyNavLayout stickyNavLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_stick);
        ButterKnife.bind(this);

        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add(ListViewFragment.newInstance());
        fragments.add(RecycleViewFragment.newInstance());
        fragments.add(EmptyListViewFragment.newInstance());
        fragments.add(ScrollViewFragment.newInstance());
        fragments.add(GridViewWithHeaderAndFooterFragment.newInstance());

        FragmentsViewPagerAdapter adapter = new FragmentsViewPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setOnPageChangeListener(mPageChangeListener);

        ViewHelper.setAlpha(floatingActionButton, 0f);
        stickyNavLayout.setOnStickStateChangeListener(onStickStateChangeListener);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
        }
    };

    private boolean lastIsTopHidden;//记录上次是否悬浮
    private StickyNavLayout.onStickStateChangeListener onStickStateChangeListener = new StickyNavLayout.onStickStateChangeListener() {
        @Override
        public void isStick(boolean isStick) {
            if (lastIsTopHidden != isStick) {
                lastIsTopHidden = isStick;
                if (isStick) {
//                    Toast.makeText(SimpleStickActivity.this, "本宝宝悬浮了", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(SimpleStickActivity.this, "本宝宝又不悬浮了", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void scrollPercent(float percent) {
            ViewHelper.setAlpha(floatingActionButton, percent);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
