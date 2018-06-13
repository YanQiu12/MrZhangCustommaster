package com.mrzhangcustom_master.protocol;

import android.content.Context;

import com.google.gson.Gson;
import com.mrzhangcustom_library.protocol.BaseProtocol;
import com.mrzhangcustom_library.util.JsonUtil;
import com.mrzhangcustom_master.bean.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TestProtocol extends BaseProtocol {

    public TestProtocol(Context context) {
        super(context);
    }

    @Override
    public News getData(String url, int index) {
        return (News) super.getData(url, index);
    }

    public News parseJson(String responnse) {
        News aNews = new Gson().fromJson(responnse, News.class);
//        page = Integer.valueOf(aNews.getPageToken());
        return aNews;
    }
}
