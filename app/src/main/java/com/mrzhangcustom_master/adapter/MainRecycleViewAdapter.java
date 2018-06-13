package com.mrzhangcustom_master.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrzhangcustom_library.util.ScreenUtil;
import com.mrzhangcustom_master.R;
import com.mrzhangcustom_master.bean.News;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.RecyclerHolder> {

    Context mContext;
    List<News.DataBean> adapterDataList;

    public MainRecycleViewAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
//        long[] mHits = new long[7];
//        //每点击一次 实现左移一格数据
//        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
//        //给数组的最后赋当前时钟值
//        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
//        //当0出的值大于当前时间-5时  证明在5秒内点击了7次
//        if (mHits[0] > SystemClock.uptimeMillis() - 5000) {
//
//        }
    }

    @Override
    public RecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_recycleview, parent, false);
        //创建Linearlayout布局
        LinearLayout linearLayout = new LinearLayout(mContext);
        //设置Linearlayout布局方向为竖直
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(ll_lp);
        //创建title的TextView
        TextView tv_title = new TextView(mContext);
        tv_title.setText("AAAAA");
        tv_title.setTextSize(ScreenUtil.dip2px(18f,mContext));
        tv_title.setTextColor(Color.parseColor("#000000"));
        LinearLayout.LayoutParams tv1_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1_lp.setMargins(0,ScreenUtil.dip2px(7f,mContext),0,0);
        linearLayout.addView(tv_title,tv1_lp);

        //创建子layout为相对布局
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        LinearLayout.LayoutParams re_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        re_lp.setMargins(0,ScreenUtil.dip2px(3f,mContext),0,0);
        //创建子layout的第一个TextView，即新闻提供者TextView
        TextView tv_new_posterScreenName = new TextView(mContext);
        tv_new_posterScreenName.setText("腾讯");
        tv_new_posterScreenName.setId(123);
        tv_new_posterScreenName.setTextColor(Color.parseColor("#666666"));
        tv_new_posterScreenName.setTextSize(ScreenUtil.dip2px(10f,mContext));
        RelativeLayout.LayoutParams tv2_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.addView(tv_new_posterScreenName,tv2_lp);
        //创建子layout的第二个TextView，即新闻日期时间TextView
        TextView tv_new_data_time = new TextView(mContext);
        tv_new_data_time.setText("2014-01-24 15:09");
        tv_new_data_time.setTextColor(Color.parseColor("#666666"));
        tv_new_data_time.setTextSize(ScreenUtil.dip2px(12f,mContext));
        RelativeLayout.LayoutParams tv3_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv3_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tv3_lp.setMargins(0,0,ScreenUtil.dip2px(20f,mContext),0);
        relativeLayout.addView(tv_new_data_time,tv3_lp);
        //将子布局relativeLayout添加到主布局linearLayout中
        linearLayout.addView(relativeLayout,re_lp);

        //创建分割线，实际上是一个1px高度的LinearLayout
        LinearLayout linearLayout1 = new LinearLayout(mContext);
        LinearLayout.LayoutParams ll_lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        ll_lp1.setMargins(0,3,0,0);
        linearLayout1.setBackgroundColor(Color.parseColor("#666666"));
        linearLayout.addView(linearLayout1,ll_lp1);

        return new RecyclerHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, int position) {
        holder.tvTitle.setText(adapterDataList.get(position).getTitle());
        holder.tvNeWPosterScreenName.setText(adapterDataList.get(position).getPosterScreenName());
        String strData = adapterDataList.get(position).getPublishDateStr().replace("T", "  ");
        holder.tvNewDataTime.setText(strData);
        if(adapterDataList.get(position).getImageUrls()!=null){
            RelativeLayout relativeLayout = (RelativeLayout)((ViewGroup) holder.itemView).getChildAt(1);
            ImageView tv_image_url = new ImageView(mContext);
            Glide.with(mContext).load(adapterDataList.get(position).getImageUrls().get(0)).into(tv_image_url);
//            tv_image_url.setText("AAAAA");
//            tv_image_url.setTextSize(ScreenUtil.dip2px(18f,mContext));
//            tv_image_url.setTextColor(Color.parseColor("#000000"));
            RelativeLayout.LayoutParams tv4_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//            tv4_lp.setMargins(0,ScreenUtil.dip2px(7f,mContext),0,0);
            tv4_lp.addRule(RelativeLayout.BELOW, 123);
            relativeLayout.addView(tv_image_url,tv4_lp);
        }
    }

    @Override
    public int getItemCount() {
        if(adapterDataList!=null){
            return adapterDataList.size();
        }
        return 0;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvNeWPosterScreenName;
        TextView tvNewDataTime;

        private RecyclerHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView)((ViewGroup) itemView).getChildAt(0);
            RelativeLayout relativeLayout = (RelativeLayout)((ViewGroup) itemView).getChildAt(1);
            tvNeWPosterScreenName = (TextView)relativeLayout.getChildAt(0);
            tvNewDataTime = (TextView)relativeLayout.getChildAt(1);
        }
    }

    public void setDataList(List<News.DataBean> dataList) {
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
