package com.mrzhangcustom_master.protocol;

import android.content.Context;

import com.mrzhangcustom_library.protocol.BaseProtocol;
import com.mrzhangcustom_library.util.JsonUtil;
import com.mrzhangcustom_master.bean.People;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestProtocol extends BaseProtocol {

    public TestProtocol(Context context) {
        super(context);
    }

    @Override
    public ArrayList<People> getData(String url, int index) {
        return (ArrayList<People>) super.getData(url, index);
    }

    @Override
    public ArrayList<People> parseJson(String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject jsonPeopleObject;
        People people;
        ArrayList<People> peoples = new ArrayList<>();
        String strPeople;
        for (int i = 1; i < 11; i++) {
            strPeople= jsonObject.getString("people"+i);
            jsonPeopleObject = new JSONObject(strPeople);
            people = new People();
            people.setName((String)jsonPeopleObject.get("name"));
            people.setPhone((String)jsonPeopleObject.get("phone"));
            peoples.add(people);
        }
        return peoples;
    }
}
