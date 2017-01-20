package com.example.candyhh.testdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.candyhh.testdemo.adapter.RecyclerViewAdapter;
import com.example.candyhh.testdemo.bean.RecyclerViewBean;
import com.example.candyhh.testdemo.speed.ScrollSpeedLinearLayoutManger;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_SETTLING;

/**
 * Created by jack on 17/1/20
 */

public class CenterActivity extends Activity {

    private static final String TAG = "CenterActivity";

    private static final int CHANGE_ITEM = 0x001;

    @BindView(R.id.al_recyclerview)
    RecyclerView alRecyclerview;

    private RecyclerViewAdapter mAdapter;

    private Timer timer;

    private MyHandler myHandler;

    private static String[] strImgs = {"http://image.mifengkong.cn/qianba/organization_id_45/57c3fa36f38c1957331842.png",
            "http://image.mifengkong.cn/qianba/organization_id_58/585a26cada217270984611.png",
            "http://image.mifengkong.cn/qianba/organization_id_/57a1ccec91fe4958589462.png",
            "http://image.mifengkong.cn/qianba/organization_id_36/579f00415e54f658392338.png",
            "http://image.mifengkong.cn/qianba/organization_id_45/57c3fa36f38c1957331842.png",
            "http://image.mifengkong.cn/qianba/organization_id_58/585a26cada217270984611.png",
            "http://image.mifengkong.cn/qianba/organization_id_/57a1ccec91fe4958589462.png",
            "http://image.mifengkong.cn/qianba/organization_id_36/579f00415e54f658392338.png"};

    private static String[] names = {"张三", "李四", "王五", "李刚", "小明", "小红", "24K纯帅", "豆豆"};

    private int cnt = 2;    //表示当前最右边显示的item的position
    private boolean isSlidingByHand = false;  //表示是否是手在滑动
    private boolean isSlidingAuto = true;   //表示是否自动滑动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_left);
        ButterKnife.bind(this);

        initView();
    }

    private List<RecyclerViewBean> getData() {
        List<RecyclerViewBean> list = new ArrayList<>();
        RecyclerViewBean data;
        for (int i = 0; i < 16; i++) {
            data = new RecyclerViewBean();
            data.setId(i);
            data.setImageUrl(strImgs[i % 8]);
            data.setName(names[i % 8]);
            list.add(data);
        }
        return list;
    }

    private void initView() {
        timer = new Timer();
        myHandler = new MyHandler(this);
        mAdapter = new RecyclerViewAdapter(this, getData());
        alRecyclerview.setAdapter(mAdapter);
        initListener();
        ScrollSpeedLinearLayoutManger scrollSpeedLinearLayoutManger=new ScrollSpeedLinearLayoutManger(this, LinearLayoutManager.HORIZONTAL, false);
        scrollSpeedLinearLayoutManger.setSpeedSlow();
        alRecyclerview.setLayoutManager(scrollSpeedLinearLayoutManger);
        LinearSnapHelper mLinearSnapHelper = new LinearSnapHelper();
        mLinearSnapHelper.attachToRecyclerView(alRecyclerview);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isSlidingAuto) {
                    myHandler.sendEmptyMessage(CHANGE_ITEM);
                }
            }
        }, 1000, 3500);
    }

    private void initListener() {
        alRecyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                switch (newState) {
                    case SCROLL_STATE_IDLE:  //（静止没有滚动）
                        if (isSlidingByHand) {
                            Message msg = myHandler.obtainMessage();
                            msg.arg1 = firstVisibleItemPosition;
                            msg.what = CHANGE_ITEM;
                            myHandler.sendMessage(msg);
                        }
                        break;
                    case SCROLL_STATE_DRAGGING:  //（正在被外部拖拽,一般为用户正在用手指滚动）
                        isSlidingByHand = true;
                        isSlidingAuto = false;
                        break;
                    case SCROLL_STATE_SETTLING:  //（自动滚动）
                        if (isSlidingByHand) {
                            isSlidingAuto = false;
                        } else {
                            isSlidingAuto = true;
                        }
                        break;
                }
            }
        });
    }

    private void stopTimer() {
        if (null != timer) {
            timer.cancel();
            timer = null;
        }
        cnt = 0;
    }

    private static class MyHandler extends Handler {
        WeakReference<CenterActivity> weakReference;

        public MyHandler(CenterActivity mActivity) {
            this.weakReference = new WeakReference<>(mActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            final CenterActivity mActivity = weakReference.get();
            Log.d(TAG, "handleMessage: " + "handler is running");
            if (mActivity.isSlidingByHand) {
                mActivity.cnt = msg.arg1;
                mActivity.isSlidingByHand = false;
                mActivity.isSlidingAuto = true;
                mActivity.cnt+=2;
                mActivity.alRecyclerview.smoothScrollToPosition(++mActivity.cnt);
            } else {
                mActivity.alRecyclerview.smoothScrollToPosition(++mActivity.cnt);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}