package com.mrzhangcustom_master;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tv_data);
    }

    public void btn1(View view) {
        if(testProtocol == null){
            testProtocol = new TestProtocol(this);
        }
        new Thread(){
            @Override
            public void run() {
                for (int i = 1; i < 6; i++) {
                    data = (String)testProtocol.getData(Constant.BASE_URL+Constant.JSON_URL, i);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message message = Message.obtain();
                    message.obj = data;
                    message.what = 1;
                    mHandler.sendMessage(message);
                }
            }
        }.start();
    }
}
