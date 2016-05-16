package com.zdx.libs.network;

import android.content.ContentValues;
import android.util.Log;

import com.zdx.libs.config.AppInfo;
import com.zdx.libs.network.interfaces.NetWorkCallback;
import com.zdx.libs.network.interfaces.ProgressListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.GZIPInputStream;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Describe:单例，网络访问模块
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :1.0
 */
public class NetApi {
    private static  NetApi mInstance = null;

    private NetApi(){}
    public static NetApi getInstance(){
        if(mInstance == null){
            mInstance = new NetApi();
        }
        return mInstance;
    }

    public void apiAsync(String url, ContentValues params, ArrayList<File> uploadFiles
            , NetWorkCallback netWorkCallBack){
        new GetJSON(netWorkCallBack).execute(url,params,uploadFiles);
    }

    /**
     * 获取 JSON 类型的接口数据
     * @param url
     * @param params
     * @param uploadFiles
     * @param requestProgressListener,resebonseProgressListener
     * @return
     * @throws NetWorkException
     */
    public JSONObject api(String url , ContentValues params, ArrayList<File> uploadFiles
            , ProgressListener requestProgressListener
            , ProgressListener resebonseProgressListener)throws NetWorkException {
        JSONObject jsonObject = null;
        StringBuilder t = new StringBuilder("");
        Exception localException = null;
        Response response = null;

        RequestBody multipartBody = null;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        boolean ismultipartBody = false;
        //上传文件
        if (uploadFiles != null) {
            for (File file : uploadFiles) {
                builder.addFormDataPart("pics", file.getName()+file.hashCode()
                        , RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), file));
                ismultipartBody = true;
            }
        }

        //上传参数
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> item : params.valueSet()) {
                builder.addFormDataPart(item.getKey().toString(), item.getValue().toString());
                ismultipartBody = true;
            }
        }
        if (ismultipartBody) {
            multipartBody = builder.build();
        }

        Request.Builder requestBuilder = new Request.Builder()
                .header("Accept-Encoding", "gzip")
                .url(url);

        //从新包装okHttpClient 以监听下载文件的进度
        try {
            if (multipartBody != null && multipartBody.contentLength() != 0) {
                //重新封装requestBody,以对上传进度进行监听
                requestBuilder.post(OkHttpHelper.addProgressRequestListener(multipartBody
                        , requestProgressListener));
            }
            response = OkHttpHelper.getOKhttpClient(resebonseProgressListener)
                    .newCall(requestBuilder.build()).execute();
            Headers headers = response.headers();
            if (response.code() == 302) {
                // 五次未返回跳出循环
                JSONObject json302 = getLocation302(headers.get("Location"), 5);
                return json302;
            }

            BufferedReader reader;
            // 如果是压缩，则解压
            InputStreamReader streamReader = null;
            if (headers != null && "gzip".equals(headers.get("content-encoding"))) {
                streamReader = new InputStreamReader(new GZIPInputStream(response.body().byteStream()));
                reader = new BufferedReader(streamReader);
            } else {
                streamReader = new InputStreamReader(response.body().byteStream(), "UTF-8");
                reader = new BufferedReader(streamReader, 8192);
            }

            String line = "";
            while ((line = reader.readLine()) != null) {
                t.append(line);
            }
            reader.close();
            if (streamReader != null)
                streamReader.close();
            Log.v("zdxtest", "   解压后结果   " + t.toString());
            jsonObject = new JSONObject(t.toString());
            return jsonObject;

        } catch (IOException e) {
            localException = e;
            e.printStackTrace();
        }catch (JSONException e){
            localException = e;
            e.printStackTrace();
        }

            if (response == null) {
                // 网络错误
                String exception = "";
                if (localException != null) {
                    exception = localException.toString();
                }
                NetWorkException serviceException = new NetWorkException(
                        url, paramToString(params), "网络问题来自本地(非服务器反馈):" + url);
                serviceException.exceptionStr = exception;
                throw serviceException;
            } else {
                // 接口问题
                if (AppInfo.getDebug()) {
                    try {
                        if (!t.equals("")) {
                            Log.v("服务器返回非json格式：", t.toString());
                        } else {
                            Log.v("服务器返回非json格式：", response.toString());
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
//                    MiscUtil.alert("接口问题查看log");
                }
                throw new NetWorkException(url, paramToString(params), "接口问题来服务器:" + url);
            }
    }

    /**
     * 302跳转
     *
     * @param url
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public JSONObject getLocation302(String url, int n) throws  IOException, JSONException {
        if (n <= 0) {
            return null;
        }
        String t = "";
        Request request = new Request.Builder()
                .addHeader("Accept-Encoding", "gzip")
                .url(url).build();
        Response response = OkHttpHelper.getOKhttpClient().newCall(request).execute();
        if(response.code() == 302){
            return  getLocation302(response.headers().get("Location"),--n);
        }
        BufferedReader reader;
        // 如果是压缩，则解压
        InputStreamReader streamReader = null;
        if(response!=null && response.headers()!=null
                && "gzip".equals(response.headers().get("content-encoding"))){
            streamReader = new InputStreamReader(new GZIPInputStream(response.body().byteStream()));
            reader = new BufferedReader(streamReader);
        }else{
            streamReader = new InputStreamReader(response.body().byteStream(), "UTF-8");
            reader = new BufferedReader(streamReader, 8192);
        }
        String line = "";
        while ((line = reader.readLine()) != null) {
            t += line;
        }
        reader.close();
        if (streamReader != null)
            streamReader.close();
        JSONObject json = new JSONObject(t);
        return json;
    }

    /**
     * 将参数转换为字符串
     *
     * @param params
     * @return
     */
    public StringBuilder paramToString(ContentValues params) {
        StringBuilder builder = new StringBuilder();
        if (params != null && params.size()>0) {
            for(Map.Entry<String ,Object> value:params.valueSet()){
                builder.append(value.getKey().toString());
                builder.append(":");
                builder.append(value.getValue().toString());
                builder.append("     ");
            }
        } else {
            builder = new StringBuilder();
        }
        return builder;
    }

}

