package com.morligy.simple.suspendsimple;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.gxz.PagerSlidingTabStrip;
import com.jsonmo.pulltofreshandload.PullToRefreshBase;
import com.morligy.simple.R;
import com.morligy.simple.stickynavsimple.adapter.FragmentsViewPagerAdapter;
import com.morligy.simple.stickynavsimple.fragments.BaseFragment;
import com.morligy.simple.stickynavsimple.fragments.EmptyListViewFragment;
import com.morligy.simple.stickynavsimple.fragments.GridViewWithHeaderAndFooterFragment;
import com.morligy.simple.stickynavsimple.fragments.ListViewFragment;
import com.morligy.simple.stickynavsimple.fragments.RecycleViewFragment;
import com.morligy.simple.stickynavsimple.fragments.ScrollViewFragment;
import com.morligy.simple.view.MPullToRefreshScrollView;
import com.morligy.suspendlayout.view.SuspendLayout;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SuspendsActivity extends AppCompatActivity {
    @Bind(R.id.refreshview)
    MPullToRefreshScrollView refreshScrollView;
    @Bind(R.id.id_stickynavlayout_indicator)
    PagerSlidingTabStrip pagerSlidingTabStrip;
    @Bind(R.id.id_stickynavlayout_viewpager)
    ViewPager viewPager;
    @Bind(R.id.id_stick)
    SuspendLayout stickyNavLayout;
    @Bind(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspends);
        ButterKnife.bind(this);

        ViewHelper.setAlpha(title,0);

        refreshScrollView.setPullRefreshEnabled(true);
        refreshScrollView.setPullLoadEnabled(true);
        refreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<View>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<View> refreshView) {
                refreshScrollView.onPullDownRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<View> refreshView) {
                refreshScrollView.onPullUpRefreshComplete();
            }
        });

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
    private SuspendLayout.onStickStateChangeListener onStickStateChangeListener = new SuspendLayout.onStickStateChangeListener() {
        @Override
        public void isStick(boolean isStick) {
            if (lastIsTopHidden != isStick) {
                lastIsTopHidden = isStick;
                if (isStick) {
//                    Toast.makeText(SimpleStickActivity.this, "悬浮了", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(SimpleStickActivity.this, "又不悬浮了", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        public void scrollPercent(float percent) {
            ViewHelper.setAlpha(title,percent);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


}