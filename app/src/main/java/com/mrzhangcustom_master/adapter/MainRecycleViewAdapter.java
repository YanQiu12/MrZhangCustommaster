package com.mrzhangcustom_master.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mrzhangcustom_master.R;
import com.mrzhangcustom_master.bean.People;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.RecyclerHolder> {

    Context mContext;
    ArrayList<People> adapterDataList;

    public MainRecycleViewAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_recycleview, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.tvLeft.setText((adapterDataList.get(position).getName()));
        holder.tvRight.setText((adapterDataList.get(position).getPhone()));
    }

    @Override
    public int getItemCount() {
        if(adapterDataList!=null){
            return adapterDataList.size();
        }
        return 0;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView tvLeft;
        TextView tvRight;

        private RecyclerHolder(View itemView) {
            super(itemView);
            tvLeft = (TextView) itemView.findViewById(R.id.tv_left);
            tvRight = (TextView) itemView.findViewById(R.id.tv_right);
        }
    }

    public void setDataList(ArrayList<People> dataList) {
        if (dataList!=null) {
            if(adapterDataList == null){
                adapterDataList = new ArrayList<>();
            }
            adapterDataList.clear();
            adapterDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }
}
