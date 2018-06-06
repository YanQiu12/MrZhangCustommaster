package com.mrzhangcustom_library.protocol;

import android.content.Context;
import android.text.TextUtils;
import com.mrzhangcustom_library.Constant;
import com.mrzhangcustom_library.util.IOUtil;
import com.mrzhangcustom_library.util.InternetUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 许佛 on 2017/12/23.
 */

public abstract class BaseProtocol<T> {

    private Context context;
    public BaseProtocol(Context context){
        this.context = context;
    }
    public T getData(String url, int index){
//        String json = getDataFromLocal(url,index);//从缓存中获取数据
        File file = IOUtil.getFile(IOUtil.FILES, context, index + ".json");
        String json = getDataFromLocal(file);
        String result;
        if(!TextUtils.isEmpty(json)){
            result = json;//json数据未过期，将其赋值给要解析的result
        }else{
            result = getDataFromNet(url,index);//如果缓存数据过期，从网络获取数据赋值给result
        }
        return parseJson(result);//解析result
    }

    private String getDataFromNet(String url,int index){
        String s = IOUtil.toString(url + index + ".json");
        writeToLocal(s,index);
        return s;
//        try {
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .readTimeout(2, TimeUnit.SECONDS)
//                    .connectTimeout(2, TimeUnit.SECONDS)
//                    .build();
//            Request request = new Request.Builder()
//                    .get()
//                    .url(Constant.BASE_URL+url+index+".json")//http://
//                    .build();
//            Response response = okHttpClient.newCall(request).execute();//同步方式请求数据
//            String json = response.body().string();
//            if(!TextUtils.isEmpty(json)){
//                writeToLocal(json,url,index);//缓存数据方法
//                return json;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
    }
    private String getDataFromLocal(File file){
        String s = IOUtil.toString(file);
        if(s!=null&&s.length()>0){
            if(InternetUtil.checkInternet(context)){
                String strInvalidTime = s.substring(0,13);
                long invalidTime = Long.parseLong(strInvalidTime);
                long currentTimeMillis = System.currentTimeMillis();
                if(currentTimeMillis>invalidTime){
                    return "";
                }else{
                    return s.substring(13,s.length());
                }
            }else{
                return s.substring(13,s.length());
            }
        }
        return "";
//        try {
//            File file = new File(context.getCacheDir(), url + index+".json");
//            FileReader fileReader = new FileReader(file.getAbsolutePath());
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//            String lineOneStr = bufferedReader.readLine();
//            long invalidTime = Long.parseLong(lineOneStr);
//            long currentTime = System.currentTimeMillis();
//            if(invalidTime < currentTime){
//                String temp;
//                StringBuilder stringBuilder = new StringBuilder();
//                while((temp = bufferedReader.readLine())!=null){
//                    stringBuilder.append(temp);
//                }
//                return stringBuilder.toString();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "";
    }

    public void writeToLocal(String json, int index){
        if(json!=null){
            long currentTimeMillis = System.currentTimeMillis();
            long invalidTime = currentTimeMillis + 1 * 60 * 1000;//有效时间5分钟
            IOUtil.StringToFile(invalidTime+json,IOUtil.FILES,context,index + ".json");
        }
//        FileWriter fileWriter = null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            File file = new File(context.getCacheDir(), url + index+".json");
////            IOUtil.StringToFile(json,IOUtil.CACHE,context,url + index+".json");
//            fileWriter = new FileWriter(file.getAbsolutePath());
//            bufferedWriter = new BufferedWriter(fileWriter);
//            //获取当前时间
//            long currentTimeMillis = System.currentTimeMillis();
//            long invalidTime = currentTimeMillis + 5 * 60 * 1000;//有效时间5分钟
//            bufferedWriter.write(invalidTime+"\r\n");//写入有效时间戳
//            bufferedWriter.write(json.toCharArray());//写入json串
//            bufferedWriter.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(fileWriter != null && bufferedWriter != null){
//                try {
//                    fileWriter.close();
//                    bufferedWriter.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    //因为每个模块的json中内容不一样，所以无法编写具体的javabean进行解析，因此让子类实现
    public abstract T parseJson(String json);
}
