package com.zdx.libs.multiadapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zdx.libs.multiadapter.CommonViewHolder;
import com.zdx.libs.multiadapter.OnItemClickListener;

import java.util.List;

/**
 * Describe:为 RecyclerView 定制adapter
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version :1.0
 */
public abstract class MultiBaseRecyclerAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas ;
    protected LayoutInflater mInflater;

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public MultiBaseRecyclerAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        int layoutId = getLayoutId(viewType);
        CommonViewHolder holder = CommonViewHolder.get(mContext, null, parent, layoutId, -1);
        setListener(parent, holder, viewType);
        return holder;
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected boolean isEnabled(int viewType)
    {
        return true;
    }


    protected void setListener(final ViewGroup parent, final CommonViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewTypes(position,mDatas.get(position));
    }

    public abstract void convert(CommonViewHolder holder, T t);
    public abstract int getItemViewTypes(int position , T item);
    public abstract int getLayoutId(int viewType);
}
