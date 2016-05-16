package com.zdx.libs.multiadapter.abslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zdx.libs.multiadapter.CommonViewHolder;

import java.util.ArrayList;

/**
 * Describe:为 ListView 定制 adapter
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version :1.0
 */
public abstract class MultiBaseAbsListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected ArrayList<T> mDatas = new ArrayList<T>();
    protected LayoutInflater mInflater;
    private int layoutId;

    public MultiBaseAbsListAdapter(Context context, int layoutId, ArrayList<T> datas ) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = datas;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int layoutId = getLayoutId(position, getItem(position));
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();

    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewTypes(position,getItem(position));
    }

    @Override
    public int getViewTypeCount() {
        return getViewTypeCounts();
    }

    public abstract void convert(CommonViewHolder holder, T t);

    /**
     * 设置不同视图类型数量
     * @return
     */
    protected abstract int getViewTypeCounts();

    /**
     * 获取 item 的视图类型
     * @return
     */
    protected abstract int getItemViewTypes(int position, T item);

    /**
     * 获取布局文件的 id
     * @return
     */
    protected abstract int getLayoutId(int position, T item);

}
