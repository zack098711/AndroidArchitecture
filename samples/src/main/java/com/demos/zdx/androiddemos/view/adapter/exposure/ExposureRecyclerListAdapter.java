package com.demos.zdx.androiddemos.view.adapter.exposure;

import android.content.Context;
import android.view.View;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.ExposureItemBean;
import com.demos.zdx.androiddemos.model.enums.ExposureItemStyleEnum;
import com.zdx.libs.multiadapter.CommonViewHolder;
import com.zdx.libs.multiadapter.recyclerview.MultiBaseRecyclerAdapter;

import java.util.ArrayList;

/**
 * Describe:曝光统计的适配器
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public class ExposureRecyclerListAdapter extends MultiBaseRecyclerAdapter<ExposureItemBean> {

    public ExposureRecyclerListAdapter(Context context, ArrayList<ExposureItemBean> exposureItemBeens){
        super(context,-1,exposureItemBeens);
    }
    @Override
    public int getLayoutId(int viewType) {
        ExposureItemStyleEnum exposureItemStyleEnum = ExposureItemStyleEnum.getItemStyleEnumById(viewType);
        if(exposureItemStyleEnum == null)
            return -1;
        if(exposureItemStyleEnum.equals(ExposureItemStyleEnum.EXPOSURE_ITEM_STYLE_NORMAL)){
            return R.layout.exposure_item_style_1;
        }else if(exposureItemStyleEnum.equals(ExposureItemStyleEnum.EXPOSURE_ITEM_STYLE_TEST)){
            return R.layout.exposure_item_style_2;
        }
        return 0;
    }

    @Override
    public int getItemViewTypes(int position, ExposureItemBean item) {
        if(item.mStyleEnum!=null)
            return item.mStyleEnum.mId;
        return -1;
    }

    @Override
    public void convert(CommonViewHolder holder, ExposureItemBean exposureItemBean) {
        if(exposureItemBean.mStyleEnum!=null
                && exposureItemBean.equals(ExposureItemStyleEnum.EXPOSURE_ITEM_STYLE_NORMAL)){
            holder.getView(R.id.id_item_style_1).setVisibility(View.VISIBLE);
        }else if(exposureItemBean.mStyleEnum != null
                && exposureItemBean.mStyleEnum.equals(ExposureItemStyleEnum.EXPOSURE_ITEM_STYLE_TEST)){
            holder.getView(R.id.test_exposureView).setVisibility(View.VISIBLE);
        }
    }
}
