package com.zdx.libs.network.interfaces;

/**
 * Describe:进度回调
 * Created by ZhaoDongXu on 16/5/13.
 * Since Version :1.00
 */
public interface ProgressListener {
    void onProgress(long currentBytes, long contentLength, boolean done);
}
