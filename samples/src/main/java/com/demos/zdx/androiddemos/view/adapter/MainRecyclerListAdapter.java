package com.demos.zdx.androiddemos.view.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.MainItemBean;
import com.demos.zdx.androiddemos.model.enums.MainItemStyleEnum;
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
        if(mainItemBean.mMainItemStyleEnum!=null
                && mainItemBean.mMainItemStyleEnum.equals(MainItemStyleEnum.ITEM_STYLE_WITH_PIC)){
            ((ImageView) holder.getView(R.id.iv_image)).setBackgroundResource(mainItemBean.getDrawableID());
            ((TextView)holder.getView(R.id.tv_title)).setText(mainItemBean.getTitle());
            ((TextView)holder.getView(R.id.tv_desc)).setText(mainItemBean.getDesc());
        }else if(mainItemBean.mMainItemStyleEnum!=null
                && mainItemBean.mMainItemStyleEnum.equals(MainItemStyleEnum.ITEM_STYLE_WITH_NO_PIC)){
            ((TextView)holder.getView(R.id.tv_title_1)).setText(mainItemBean.getTitle());
            ((TextView)holder.getView(R.id.tv_desc_1)).setText(mainItemBean.getDesc());
        }
    }

    @Override
    public int getItemViewTypes(int position, MainItemBean item) {
        return item.getMainItemStyleEnum().mId;
    }

    @Override
    public int getLayoutId(int viewType) {
        MainItemStyleEnum mainItemStyleEnum = MainItemStyleEnum.getItemStyleEnumById(viewType);
        if(mainItemStyleEnum == null){
            return -1;
        }
        if(mainItemStyleEnum.equals(MainItemStyleEnum.ITEM_STYLE_WITH_PIC)){
            return R.layout.main_list_item_type0;
        }else if(mainItemStyleEnum.equals(MainItemStyleEnum.ITEM_STYLE_WITH_NO_PIC)){
            return  R.layout.main_list_item_type1;
        }
        return -1;
    }
}
