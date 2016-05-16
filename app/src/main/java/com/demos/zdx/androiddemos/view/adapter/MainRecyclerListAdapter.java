package com.demos.zdx.androiddemos.view.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.MainItemBean;
import com.zdx.libs.multiadapter.CommonViewHolder;
import com.zdx.libs.multiadapter.recyclerview.MultiBaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Describe:
 * Created by ZhaoDongXu on 16/5/12.
 * Since Version :
 */
public class MainRecyclerListAdapter extends MultiBaseRecyclerAdapter<MainItemBean> {

    public MainRecyclerListAdapter(Context context, ArrayList<MainItemBean> datas){
        super(context,-1,datas);
    }
    @Override
    public void convert(CommonViewHolder holder, MainItemBean mainItemBean) {
        if(mainItemBean.mType == mainItemBean.STYLE_0){
            ((ImageView) holder.getView(R.id.iv_image)).setBackgroundResource(mainItemBean.getDrawableID());
            ((TextView)holder.getView(R.id.tv_title)).setText(mainItemBean.getTitle());
            ((TextView)holder.getView(R.id.tv_desc)).setText(mainItemBean.getDesc());
        }else if(mainItemBean.mType == mainItemBean.STYLE_1){
            ((TextView)holder.getView(R.id.tv_title_1)).setText(mainItemBean.getTitle());
            ((TextView)holder.getView(R.id.tv_desc_1)).setText(mainItemBean.getDesc());
        }
    }

    @Override
    public int getItemViewTypes(int position, MainItemBean item) {
        return item.getType();
    }

    @Override
    public int getLayoutId(int viewType) {
        if(viewType == MainItemBean.STYLE_0){
            return R.layout.main_list_item_type0;
        }else {
            return R.layout.main_list_item_type1;
        }
    }
}
