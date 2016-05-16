package com.zdx.libs.multiadapter;

import android.view.View;
import android.view.ViewGroup;

/**
 * Describe:
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version :
 */
public interface OnItemClickListener<T> {
    void onItemClick(ViewGroup parent, View view, T t, int position);
    boolean onItemLongClick(ViewGroup parent, View view, T t, int position);
}
