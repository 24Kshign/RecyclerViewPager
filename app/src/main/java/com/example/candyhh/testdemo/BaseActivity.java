package com.example.candyhh.testdemo;

import android.app.Activity;

import com.example.candyhh.testdemo.adapter.RecyclerViewAdapter;
import com.example.candyhh.testdemo.bean.RecyclerViewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jack on 17/1/21
 */

public class BaseActivity extends Activity {

    protected RecyclerViewAdapter mAdapter;

    private String[] strImgs = {"http://image.mifengkong.cn/qianba/organization_id_45/57c3fa36f38c1957331842.png",
            "http://image.mifengkong.cn/qianba/organization_id_58/585a26cada217270984611.png",
            "http://image.mifengkong.cn/qianba/organization_id_/57a1ccec91fe4958589462.png",
            "http://image.mifengkong.cn/qianba/organization_id_36/579f00415e54f658392338.png",
            "http://image.mifengkong.cn/qianba/organization_id_45/57c3fa36f38c1957331842.png",
            "http://image.mifengkong.cn/qianba/organization_id_58/585a26cada217270984611.png",
            "http://image.mifengkong.cn/qianba/organization_id_/57a1ccec91fe4958589462.png",
            "http://image.mifengkong.cn/qianba/organization_id_36/579f00415e54f658392338.png"};

    private String[] names = {"张三", "李四", "王五", "李刚", "小明", "小红", "24K纯帅", "豆豆"};

    public List<RecyclerViewBean> getData() {
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
}
