package com.example.candyhh.testdemo.snap_helper;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jack on 17/1/21
 * 右对齐
 */

public class RightSnapHelper extends LinearSnapHelper {

    private OrientationHelper mHorizontalHelper;

    /**
     * 当拖拽或滑动结束时会回调该方法,该方法返回的是一个长度为2的数组,out[0]表示横轴,x[1]表示纵轴,这两个值就是你需要修正的位置的偏移量
     *
     * @param layoutManager
     * @param targetView
     * @return
     */
    @Override
    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View targetView) {
        //注:由于是横向滚动,在这里我们只考虑横轴的值
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToEnd(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        return out;
    }

    /**
     * 这个方法是计算偏移量
     *
     * @param targetView
     * @param helper
     * @return
     */
    private int distanceToEnd(View targetView, OrientationHelper helper) {
        return helper.getDecoratedEnd(targetView) - helper.getEndAfterPadding();
    }

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return findEndView(layoutManager, getHorizontalHelper(layoutManager));
    }

    /**
     * 找到第一个显示的view
     *
     * @param layoutManager
     * @param helper
     * @return
     */
    private View findEndView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        if (layoutManager instanceof LinearLayoutManager) {
            int lastChild = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            if (lastChild == RecyclerView.NO_POSITION) {
                return null;
            }

            View child = layoutManager.findViewByPosition(lastChild);

            //得到此时需要右对齐显示的条目
            if (helper.getDecoratedStart(child) >= helper.getDecoratedMeasurement(child) / 2
                    && helper.getDecoratedStart(child) > 0) {
                return child;
            } else {
                return layoutManager.findViewByPosition(lastChild - 1);
            }
        }
        return super.findSnapView(layoutManager);
    }

    /**
     * 获取视图的方向
     *
     * @param layoutManager
     * @return
     */
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
