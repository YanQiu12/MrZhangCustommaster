package com.mrzhangcustom_master;

import android.content.Context;

import com.mrzhangcustom_library.protocol.BaseProtocol;

public class TestProtocol extends BaseProtocol {

    public TestProtocol(Context context) {
        super(context);
    }

    @Override
    public String parseJson(String json) {
        return json;
    }
}
