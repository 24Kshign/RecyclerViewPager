package com.example.candyhh.testdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.candyhh.testdemo.R;

/**
 * Created by jack on 17/1/20
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivImage;
    public TextView tvName;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        ivImage = (ImageView) itemView.findViewById(R.id.adapter_iv_icon);
        tvName = (TextView) itemView.findViewById(R.id.adapter_tv_name);
    }

}
