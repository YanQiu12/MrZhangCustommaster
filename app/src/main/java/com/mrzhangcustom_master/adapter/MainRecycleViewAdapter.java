package com.mrzhangcustom_master.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mrzhangcustom_library.util.ScreenUtil;
import com.mrzhangcustom_master.R;
import com.mrzhangcustom_master.activity.ImageActivity;
import com.mrzhangcustom_master.bean.News;

import java.util.ArrayList;
import java.util.List;

public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.RecyclerHolder> {

    Context mContext;
    private long[] mHits;
    List<News.DataBean> adapterDataList;
    private static final int ID_RELATIVELAYOUT = 122;
    private static final int ID_IMAGELINNEAR_LAYOUT1 = 123;
    private static final int ID_IMAGELINNEAR_LAYOUT2 = 124;
    private static final int ID_TV_NEW_POSTERSCREENNAME = 125;
    private static final int ID_TV_NEW_DATA_TIME = 126;

    public MainRecycleViewAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
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
        tv_title.setTextSize(16);
        tv_title.setTextColor(Color.parseColor("#000000"));
        LinearLayout.LayoutParams tv1_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1_lp.setMargins(0, 7, 0, 0);
        linearLayout.addView(tv_title, tv1_lp);

        //给图片创建一个预留的线性布局
        LinearLayout imageLinearLayout1 = new LinearLayout(mContext);
        imageLinearLayout1.setId(ID_IMAGELINNEAR_LAYOUT1);
        imageLinearLayout1.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lpImageLinearLayout1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(imageLinearLayout1, lpImageLinearLayout1);
        //给图片再创建一个预留的线性布局
        LinearLayout imageLinearLayout2 = new LinearLayout(mContext);
        imageLinearLayout2.setId(ID_IMAGELINNEAR_LAYOUT2);
        imageLinearLayout2.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lpImageLinearLayout2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        linearLayout.addView(imageLinearLayout2, lpImageLinearLayout2);

        //创建子layout为相对布局
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setId(ID_RELATIVELAYOUT);
        LinearLayout.LayoutParams re_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        re_lp.setMargins(0, 3, 0, 0);
        //创建子layout的第一个TextView，即新闻提供者TextView
        TextView tv_new_posterScreenName = new TextView(mContext);
        tv_new_posterScreenName.setText("腾讯");
        tv_new_posterScreenName.setId(ID_TV_NEW_POSTERSCREENNAME);
        tv_new_posterScreenName.setTextColor(Color.parseColor("#666666"));
        tv_new_posterScreenName.setTextSize(10);
        RelativeLayout.LayoutParams tv2_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayout.addView(tv_new_posterScreenName, tv2_lp);
        //创建子layout的第二个TextView，即新闻日期时间TextView
        TextView tv_new_data_time = new TextView(mContext);
        tv_new_data_time.setText("2014-01-24 15:09");
        tv_new_data_time.setId(ID_TV_NEW_DATA_TIME);
        tv_new_data_time.setTextColor(Color.parseColor("#666666"));
        tv_new_data_time.setTextSize(12);
        RelativeLayout.LayoutParams tv3_lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv3_lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tv3_lp.setMargins(0, 0, 20, 0);
        relativeLayout.addView(tv_new_data_time, tv3_lp);
        //将子布局relativeLayout添加到主布局linearLayout中
        linearLayout.addView(relativeLayout, re_lp);

        //创建分割线，实际上是一个1px高度的LinearLayout
        LinearLayout linearLayout1 = new LinearLayout(mContext);
        LinearLayout.LayoutParams ll_lp1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);
        ll_lp1.setMargins(0, 3, 0, 0);
        linearLayout1.setBackgroundColor(Color.parseColor("#666666"));
        linearLayout.addView(linearLayout1, ll_lp1);

        return new RecyclerHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(RecyclerHolder holder, final int position) {
        holder.tvTitle.setText(adapterDataList.get(position).getTitle().replaceAll("&quot;", "\""));
        holder.tvNeWPosterScreenName.setText(adapterDataList.get(position).getPosterScreenName());
        String strData = adapterDataList.get(position).getPublishDateStr().replace("T", "  ");
        holder.tvNewDataTime.setText(strData);
        LinearLayout imageLinearLayout1 = holder.imageLinearLayout1;
        LinearLayout imageLinearLayout2 = holder.imageLinearLayout2;
        imageLinearLayout1.removeAllViews();
        imageLinearLayout2.removeAllViews();
        if (adapterDataList.get(position).getImageUrls() != null) {
            if (adapterDataList.get(position).getImageUrls().size() > 4) {
                for (int i = 0; i < 4; i++) {
                    ImageView tv_image_url = new ImageView(mContext);
                    final int finalI = i;
                    mHits = new long[3];
                    tv_image_url.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //每点击一次 实现左移一格数据
                            System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
                            //给数组的最后赋当前时钟值
                            mHits[mHits.length - 1] = SystemClock.uptimeMillis();
                            //当0出的值大于当前时间-5时  证明在5秒内点击了7次
                            if (mHits[0] > SystemClock.uptimeMillis() - 1000) {
                                Intent intent = new Intent(mContext, ImageActivity.class);
                                intent.putExtra("imageUrl", adapterDataList.get(position).getImageUrls().get(finalI));
                                mContext.startActivity(intent);
                            }
                        }
                    });
                    Glide
                            .with(mContext)
                            .load(adapterDataList.get(position).getImageUrls().get(i))
                            .placeholder(R.drawable.imageload)
                            .into(tv_image_url);
                    int a = ScreenUtil.px2dip(mContext.getResources().getDimension(R.dimen.px50), mContext);
                    RelativeLayout.LayoutParams tv4_lp = new RelativeLayout.LayoutParams(a, a);
//            tv4_lp.setMargins(0,ScreenUtil.dip2px(7f,mContext),0,0);
                    tv4_lp.addRule(RelativeLayout.BELOW, ID_TV_NEW_POSTERSCREENNAME);
                    if (i != 0) {
                        tv4_lp.setMargins(2, 0, 0, 0);
                    }
                    imageLinearLayout1.addView(tv_image_url, tv4_lp);
                }
                for (int i = 4; i < adapterDataList.get(position).getImageUrls().size(); i++) {
                    ImageView tv_image_url = new ImageView(mContext);
                    final int finalI = i;
                    tv_image_url.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, ImageActivity.class);
                            intent.putExtra("imageUrl", adapterDataList.get(position).getImageUrls().get(finalI));
                            mContext.startActivity(intent);
                        }
                    });
                    Glide
                            .with(mContext)
                            .load(adapterDataList.get(position).getImageUrls().get(i))
                            .placeholder(R.drawable.imageload)
                            .into(tv_image_url);
                    int a = ScreenUtil.px2dip(mContext.getResources().getDimension(R.dimen.px50), mContext);
                    RelativeLayout.LayoutParams tv4_lp = new RelativeLayout.LayoutParams(a, a);
//            tv4_lp.setMargins(0,ScreenUtil.dip2px(7f,mContext),0,0);
                    tv4_lp.addRule(RelativeLayout.BELOW, ID_TV_NEW_POSTERSCREENNAME);
                    if (i != 4) {
                        tv4_lp.setMargins(2, 0, 0, 0);
                    }
                    imageLinearLayout2.addView(tv_image_url, tv4_lp);
                }
            } else {
                for (int i = 0; i < adapterDataList.get(position).getImageUrls().size(); i++) {
                    ImageView tv_image_url = new ImageView(mContext);
                    final int finalI = i;
                    tv_image_url.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, ImageActivity.class);
                            intent.putExtra("imageUrl", adapterDataList.get(position).getImageUrls().get(finalI));
                            mContext.startActivity(intent);
                        }
                    });
                    Glide
                            .with(mContext)
                            .load(adapterDataList.get(position).getImageUrls().get(i))
                            .placeholder(R.drawable.imageload)
                            .into(tv_image_url);
                    int a = ScreenUtil.px2dip(mContext.getResources().getDimension(R.dimen.px50), mContext);
                    RelativeLayout.LayoutParams tv4_lp = new RelativeLayout.LayoutParams(a, a);
//            tv4_lp.setMargins(0,ScreenUtil.dip2px(7f,mContext),0,0);
                    tv4_lp.addRule(RelativeLayout.BELOW, ID_TV_NEW_POSTERSCREENNAME);
                    if (i != 0) {
                        tv4_lp.setMargins(2, 0, 0, 0);
                    }
                    imageLinearLayout1.addView(tv_image_url, tv4_lp);
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (adapterDataList != null) {
            return adapterDataList.size();
        }
        return 0;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvNeWPosterScreenName;
        TextView tvNewDataTime;
        LinearLayout imageLinearLayout1;
        LinearLayout imageLinearLayout2;

        private RecyclerHolder(View itemView) {
            super(itemView);

            tvTitle = (TextView) ((ViewGroup) itemView).getChildAt(0);
            imageLinearLayout1 = itemView.findViewById(ID_IMAGELINNEAR_LAYOUT1);
            imageLinearLayout2 = itemView.findViewById(ID_IMAGELINNEAR_LAYOUT2);
//            RelativeLayout relativeLayout = (RelativeLayout)((ViewGroup) itemView).getChildAt(ID_RELATIVELAYOUT);
//            tvNeWPosterScreenName = (TextView)relativeLayout.getChildAt(ID_TV_NEW_POSTERSCREENNAME);
//            tvNewDataTime = (TextView)relativeLayout.getChildAt(ID_TV_NEW_DATA_TIME);
//            RelativeLayout relativeLayout = (RelativeLayout)itemView.findViewById(ID_RELATIVELAYOUT);
            tvNeWPosterScreenName = itemView.findViewById(ID_TV_NEW_POSTERSCREENNAME);
            tvNewDataTime = itemView.findViewById(ID_TV_NEW_DATA_TIME);
        }
    }

    public void setDataList(List<News.DataBean> dataList) {
        if (dataList != null) {
            if (adapterDataList == null) {
                adapterDataList = new ArrayList<>();
            }
            adapterDataList.clear();
            adapterDataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }
}
