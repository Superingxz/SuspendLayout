package com.morligy.simple.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jsonmo.pulltofreshandload.PullToRefreshBase;

/**
 * Created by Administrator on 2017/6/2.
 */

public class MPullToRefreshScrollView extends PullToRefreshBase<View> {
    public MPullToRefreshScrollView(Context context) {
        super(context);
    }

    public MPullToRefreshScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MPullToRefreshScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected boolean isReadyForPullDown() {
        return true;
    }

    @Override
    protected boolean isReadyForPullUp() {
        return true;
    }

    @Override
    protected View createRefreshableView(Context context, AttributeSet attrs) {
        View freshView = getChildAt(0);
        return freshView;
    }
}
