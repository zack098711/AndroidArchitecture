package com.demos.zdx.androiddemos.view.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.MainItemBean;
import com.demos.zdx.androiddemos.model.enums.MainItemStyleEnum;
import com.zdx.libs.multiadapter.CommonViewHolder;
import com.zdx.libs.multiadapter.abslistview.MultiBaseAbsListAdapter;

import java.util.ArrayList;

/**
 * Describe:abslistview test
 * Created by ZhaoDongXu on 16/5/12.
 * Since Version :
 */
public class MainAbsListAdapter extends MultiBaseAbsListAdapter<MainItemBean> {
    public MainAbsListAdapter(Context context, ArrayList<MainItemBean> sourceData){
        super(context, -1,sourceData);

    }

    @Override
    protected int getItemViewTypes(int position, MainItemBean item) {
        return item.getMainItemStyleEnum().mId;
    }

    @Override
    protected int getLayoutId(int position, MainItemBean item) {
        MainItemStyleEnum mainItemStyleEnum = item.getMainItemStyleEnum();
        if(mainItemStyleEnum == null){
            return -1;
        }
        if(mainItemStyleEnum.equals(MainItemStyleEnum.ITEM_STYLE_WITH_PIC)){
            return R.layout.main_list_item_type0;
        }else if(mainItemStyleEnum.equals(MainItemStyleEnum.ITEM_STYLE_WITH_NO_PIC)){
            return R.layout.main_list_item_type1;
        }
        return -1;
    }

    @Override
    protected int getViewTypeCounts() {
        return 2;
    }

    @Override
    public void convert(CommonViewHolder holder, MainItemBean mainItemBean) {
        if(mainItemBean.mMainItemStyleEnum!=null
                && MainItemStyleEnum.ITEM_STYLE_WITH_PIC.equals(mainItemBean.mMainItemStyleEnum)){
            ((ImageView) holder.getView(R.id.iv_image)).setBackgroundResource(mainItemBean.getDrawableID());
            ((TextView)holder.getView(R.id.tv_title)).setText(mainItemBean.getTitle());
            ((TextView)holder.getView(R.id.tv_desc)).setText(mainItemBean.getDesc());
        }else if(mainItemBean.mMainItemStyleEnum != null
                && MainItemStyleEnum.ITEM_STYLE_WITH_NO_PIC.equals(mainItemBean.getMainItemStyleEnum())){
            ((TextView)holder.getView(R.id.tv_title_1)).setText(mainItemBean.getTitle());
            ((TextView)holder.getView(R.id.tv_desc_1)).setText(mainItemBean.getDesc());
        }
    }
}
