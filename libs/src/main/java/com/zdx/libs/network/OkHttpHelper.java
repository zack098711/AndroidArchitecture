package com.zdx.libs.network;

import com.zdx.libs.network.interfaces.ProgressListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Describe:获取httpclient，添加访问与下载的进度回调
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :1.0
 */
public class OkHttpHelper {
    private static final int REQUEST_TIMEOUT = 30;
    private static final int READ_TIMEOUT = 60;
    private static final int WRITE_TIMEOUT = 60;

    public static OkHttpClient getOKhttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        return  builder.build();
    }

    /**
     * 获取具有回调进度的 OKhttp
     * @param progressResponseListener
     * @return
     */
    public static OkHttpClient getOKhttpClient(final ProgressListener progressResponseListener){
        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), progressResponseListener))
                        .build();
            }
        }).connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS);
        return builder.build();
    }

    /**
     * 包装请求体用于上传文件的回调
     * @param requestBody 请求体RequestBody
     * @param progressRequestListener 进度回调接口
     * @return 包装后的进度回调请求体
     */
    public static ProgressRequestBody addProgressRequestListener(RequestBody requestBody
            , ProgressListener progressRequestListener){
        //包装请求体
        return new ProgressRequestBody(requestBody,progressRequestListener);
    }
}
