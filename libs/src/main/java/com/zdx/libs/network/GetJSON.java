package com.zdx.libs.network;

import android.content.ContentValues;
import android.os.AsyncTask;

import com.zdx.libs.config.AppInfo;
import com.zdx.libs.network.interfaces.NetWorkCallback;
import com.zdx.libs.network.interfaces.ProgressListener;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

/**
 * Describe:异步任务的封装
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :1.00
 */
public class GetJSON extends AsyncTask<Object,Object,JSONObject>{

    NetWorkCallback mNetWorkCallback;
    public GetJSON(NetWorkCallback mNetWorkCallback){
        this.mNetWorkCallback = mNetWorkCallback;
    }
    @Override
    protected JSONObject doInBackground(Object[] params){
        String param0 = "";
        ContentValues param2 = null;
        ProgressListener requestProgressListener = null;
        ProgressListener reseponseProgressListener = null;
        ArrayList<File> uploadFiles = new ArrayList<File>();

        try{

            if (params.length > 0 && params[0] != null) {
                param0 = (String) params[0];
            }
            if (params.length > 2 && params[2] != null) {
                param2 = (ContentValues) params[2];
            } else {
                param2 = new ContentValues();
            }
            if(params.length > 3 && params[3] != null){
                requestProgressListener = (ProgressListener) params[3];
            }

            if(params.length >4 && params[4] != null){
                reseponseProgressListener = (ProgressListener) params[4];
            }

            JSONObject json = NetApi.getInstance().api(param0, param2,
                    uploadFiles,requestProgressListener,reseponseProgressListener);
            return json;
        }catch (NetWorkException e){
            if(AppInfo.getDebug()){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {

        super.onPostExecute(jsonObject);
    }


}
