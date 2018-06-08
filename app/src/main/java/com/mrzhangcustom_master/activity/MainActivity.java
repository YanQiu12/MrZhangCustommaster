package com.mrzhangcustom_master.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.mrzhangcustom_library.activity.ExitActivityUtil;
import com.mrzhangcustom_master.R;
import com.mrzhangcustom_master.adapter.MainRecycleViewAdapter;
import com.mrzhangcustom_master.bean.People;
import com.mrzhangcustom_master.protocol.TestProtocol;
import com.mrzhangcustom_master.util.Constant;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import java.util.ArrayList;

public class MainActivity extends ExitActivityUtil {

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                tvData.setText((String)msg.obj);
            }
        }
    };

    private TestProtocol testProtocol;
    private String data;
    private TextView tvData;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private TestProtocol testProtocol1;
    private ArrayList<People> dataList;
    private ArrayList<People> newDataList;
    private MainRecycleViewAdapter mainRecycleViewAdapter;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tv_data);
        initView();
        firstLoad();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshlayout) {
                new Thread(){
                    @Override
                    public void run() {
                        page = 1;
                        newDataList = testProtocol1.getData(Constant.BASE_URL + Constant.JSON_URL, page);
                        if(newDataList!=null&&newDataList.size()>0){
                            dataList.clear();
                            dataList.addAll(newDataList);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainRecycleViewAdapter.setDataList(dataList);
                                }
                            });
                            page++;
                            refreshlayout.finishRefresh(true);
                        }else{
                            refreshlayout.finishRefresh(1000);
                        }
                    }
                }.start();
//                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(final RefreshLayout refreshlayout) {
                new Thread(){
                    @Override
                    public void run() {
                        newDataList = testProtocol1.getData(Constant.BASE_URL + Constant.JSON_URL, page);
                        if(newDataList!=null&&newDataList.size()>0){
                            dataList.addAll(newDataList);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    mainRecycleViewAdapter.setDataList(dataList);
                                }
                            });
                            page++;
                            refreshlayout.finishLoadMore(true);
                        }else{
                            refreshlayout.finishLoadMore(1000);
                        }
                    }
                }.start();
//                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        refreshLayout = (RefreshLayout)findViewById(R.id.refreshLayout);
//设置 Header 为 贝塞尔雷达 样式
//        refreshLayout.setRefreshHeader(new ClassicsHeader(this).setLastUpdateTime(new Date(System.currentTimeMillis())).setAccentColor(Color.RED));
////设置 Footer 为 球脉冲 样式
//        refreshLayout.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAccentColor(Color.RED));
        mainRecycleViewAdapter = new MainRecycleViewAdapter(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainRecycleViewAdapter);
    }

    private void firstLoad() {
        page = 1;
        testProtocol1 = new TestProtocol(this);
        new Thread(){
            @Override
            public void run() {
                dataList = testProtocol1.getData(Constant.BASE_URL + Constant.JSON_URL, page);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                            mainRecycleViewAdapter.setDataList(dataList);
                    }
                });
                page++;
            }
        }.start();
    }

    public void btn1(View view) {
//        if(testProtocol == null){
//            testProtocol = new TestProtocol(this);
//        }
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 1; i < 6; i++) {
//                    data = (String)testProtocol.getData(Constant.BASE_URL+Constant.JSON_URL, i);
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Message message = Message.obtain();
//                    message.obj = data;
//                    message.what = 1;
//                    mHandler.sendMessage(message);
//                }
//            }
//        }.start();
    }
}
