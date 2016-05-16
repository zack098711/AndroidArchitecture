package com.zdx.libs.multiadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Describe:通用类型的viewHolder ,会缓存不同样式的 view
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version :1.0
 */
public class CommonViewHolder extends RecyclerView.ViewHolder{
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;
    private int mLayoutId;

    public CommonViewHolder(Context context, View itemView , ViewGroup parent,int position){
        super(itemView);
        mContext = context;
        mPosition = position;
        mViews = new SparseArray<View>();
        mConvertView = itemView;
        mConvertView.setTag(this);
    }

    /**
     * find view by id
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int resId){
        View view = mViews.get(resId);
        if(view == null){
            view = mConvertView.findViewById(resId);
            mViews.put(resId,view);
        }
        return (T)view;
    }

    /**
     * 获取holder
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static CommonViewHolder get(Context context, View convertView,ViewGroup parent, int layoutId, int position){
        if(convertView == null){
            View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
            CommonViewHolder holder  = new CommonViewHolder(context,itemView,parent,position);
            holder.mLayoutId = layoutId;
            return holder;
        }else{
            CommonViewHolder holder = (CommonViewHolder) convertView.getTag();
            holder.mPosition = position;
            return holder;
        }

    }

    public CommonViewHolder setTag(int viewId, Object tag)
    {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public CommonViewHolder setTag(int viewId, int key, Object tag) {
        View view = getView(viewId);
        view.setTag(key, tag);
        return this;
    }


    public View getConvertView() {
        return mConvertView;
    }

    public void updatePosition(int position) {
        mPosition = position;
    }

    public int getLayoutId() {
        return mLayoutId;
    }
}
