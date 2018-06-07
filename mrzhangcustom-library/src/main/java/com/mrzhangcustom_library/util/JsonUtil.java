package com.mrzhangcustom_library.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private JsonUtil() {}

    public static Map<String, Object> jsonToMap(String jsonString) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            JSONObject json = new JSONObject(jsonString);
            Iterator<?> it = json.keys();
            String key = null;
            Object value = null;
            while (it.hasNext()) {
                key = it.next().toString();
                value = json.get(key);

                if (value.toString().equals("null") || value.toString().equals("")) {
                    value = "";
                }
                map.put(key, value);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        return map;
    }

    public static JSONObject mapToJson(@SuppressWarnings("rawtypes") Map map) {
        JSONObject json = null;
        json = new JSONObject(map);
        return json;
    }

    public static List<Map<String, Object>> jsonToList(String jsonArrayString) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            JSONArray array = new JSONArray(jsonArrayString);
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                Map<String, Object> map = jsonToMap(json.toString());
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * @param json
     * @return
     */
    public static Map<String, Object> jsonToMap(JSONObject json) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Iterator<?> it = json.keys();
            String key = null;
            Object value = null;
            while (it.hasNext()) {
                key = it.next().toString();
                value = json.get(key);
                if (value.toString().equals("null") || value.toString().equals("")) {
                    value = "";
                }
                map.put(key, value);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return map;
    }

    /**
     * @param array
     * @return
     */
    public static List<Map<String, Object>> jsonToList(JSONArray array) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            for (int i = 0; i < array.length(); i++) {
                JSONObject json = array.getJSONObject(i);
                Map<String, Object> map = jsonToMap(json.toString());
                list.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    /**
     * @param json
     * @param name
     * @return
     */
    public static int getInt(JSONObject json, String name) {
        if (json.has(name)) {
            try {
                if (!json.getString(name).equals("null") & json.getString(name) != "")
                    return json.getInt(name);
                return -100;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     * @param array
     * @param index
     * @return
     */
    public static int getInt(JSONArray array, int index) {
        try {
            return array.getInt(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param json
     * @param name
     * @return
     */
    public static String getString(JSONObject json, String name) {
        if (json.has(name)) {
            try {
                if (json.has(name) && !json.getString(name).equals("null") && json.getString(name) != "")
                    return json.getString(name);
                return "";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    /**
     * @param array
     * @param index
     * @return
     */
    public static String getString(JSONArray array, int index) {
        String s = "";
        try {
            s = array.getString(index);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * @param json
     * @param arrayName
     * @return
     */
    public static JSONArray getJSONArray(JSONObject json, String arrayName) {
        JSONArray array = new JSONArray();
        try {
            if (json.has(arrayName))
                array = json.getJSONArray(arrayName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * @param array
     * @param index
     * @return
     */
    public static JSONObject getJSONObject(JSONArray array, int index) {
        JSONObject json = new JSONObject();
        try {
            json = array.getJSONObject(index);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }

    /**
     * @param json
     * @param name
     * @return
     */
    public static JSONObject getJSONObject(JSONObject json, String name) {
        JSONObject json2 = new JSONObject();
        try {
            if (json.has(name))
                json2 = json.getJSONObject(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json2;
    }

}
