package com.mrzhangcustom_library.util;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class IOUtil {

    public static final int FILES = 0;
    public static final int CACHE = 1;
    private static final int BUFFER_SIZE = 1024;

    private IOUtil() {}
    /**
     * @param filetype 获取File路径还是Cache路径
     * @param context 上下文
     * @param name 文件名
     */
    public static File getFile(int filetype, Context context, String name) {
        if (filetype == CACHE) {
            return new File(context.getCacheDir(), name);
        } else{
            return new File(context.getFilesDir(), name);
        }
    }

    //将InputStream转换成String
    public static String toString(InputStream is){
        try{
            if(is == null){
                return "";
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int len = -1;
            byte[] buf = new byte[BUFFER_SIZE];
            while ((len = is.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
            is.close();
            return new String(os.toByteArray(), "ISO-8859-1");
        }catch(IOException e){
            e.printStackTrace();
            Log.e(IOUtil.class.getName(),"InputStream转String异常。");
        }
        return null;
    }

    //从网络url获取Srting
    public static String toString(String urlPath){
        try{
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(2, TimeUnit.SECONDS)
                    .connectTimeout(2, TimeUnit.SECONDS)
                    .build();
            Request request = new Request.Builder()
                    .get()
                    .url(urlPath)
                    .build();
            Response response = okHttpClient.newCall(request).execute();//同步方式请求数据
            return response.body().string();
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(IOUtil.class.getName()+"URL获取String异常。");
            return  null;
//            Log.e(IOUtil.class.getName(),"URL获取String异常。");
        }
    }
    //从File里读取String
    public static String toString(File file){
        InputStream is = toInputStream(file);
        return toString(is);
    }

    //将String转换成InputStream
    public static InputStream toInputStream(String str){
        try{
            InputStream is = new ByteArrayInputStream(str.getBytes("UTF-8"));
            return is;
        }catch(UnsupportedEncodingException e){
            e.printStackTrace();
            Log.e(IOUtil.class.getName(),"String转InputStream异常。");
        }
        return null;
    }
    //从网络url获取InputStream
    public static InputStream toInputStream(URL url){
        try{
            /*创建连接*/
            URLConnection conn = url.openConnection();
            conn.connect();
            /*InputStream 下载文件*/
            InputStream is = conn.getInputStream();
            if (is == null) {
                Log.e(IOUtil.class.getName(),"URL获取InputStream异常。");
            }
            return is;
        }catch(IOException e){
            e.printStackTrace();
            Log.e(IOUtil.class.getName(),"URL获取InputStream异常。");
        }
        return null;
    }
    //从File里获取InputStream
    public static InputStream toInputStream(File file){
        try{
            FileInputStream fis = new FileInputStream(file);
            return fis;
        }catch(FileNotFoundException e){
            e.printStackTrace();
            Log.e(IOUtil.class.getName(),"File获取InputStream异常。");
        }
        return null;
    }
    //将InputStream写入File
    public static boolean toFile(InputStream is, File file){
        try{
            //文件输出流
            FileOutputStream fos = new FileOutputStream(file);
            //写数据
            byte buf[] = new byte[1024];
            do {
                int numread = is.read(buf);
                if (numread <= 0) {
                    break;
                }
                fos.write(buf, 0, numread);
            } while (true);
            //关闭文件流
            is.close();
            fos.close();
            return true;
        }catch(IOException e){
            e.printStackTrace();
            Log.e(IOUtil.class.getName(),"InputStream写入File异常");
        }
        return false;
    }
    //将String写入File
    public static boolean toFile(String str, File file){
        InputStream is = toInputStream(str);
        boolean b = toFile(is, file);
        return b;
    }
    //将String写入缓存
    public static boolean toFile(String str,int fileType,Context context,String name){
        File file = getFile(fileType, context, name);
        boolean b = toFile(str, file);
        return b;
    }
}
