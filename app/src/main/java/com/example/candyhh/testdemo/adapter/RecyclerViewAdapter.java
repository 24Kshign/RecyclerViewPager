package com.example.candyhh.testdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.candyhh.testdemo.R;
import com.example.candyhh.testdemo.bean.RecyclerViewBean;

import java.util.List;

/**
 * Created by jack on 17/1/20
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<RecyclerViewBean> mList;
    private LayoutInflater mInflater;
    private Context mContext;

    public RecyclerViewAdapter(Context context, List<RecyclerViewBean> mList) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mList = mList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(mInflater.inflate(R.layout.item_recyclerview, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position % mList.size()).getImageUrl()).placeholder(R.mipmap.ic_launcher).into(holder.ivImage);
        holder.tvName.setText(mList.get(position % mList.size()).getName());
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}
