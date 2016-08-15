package com.demos.zdx.androiddemos.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.ExposureItemBean;
import com.demos.zdx.androiddemos.view.adapter.exposure.ExposureRecyclerListAdapter;

import java.util.ArrayList;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public class ExposureFragment1 extends Fragment {
    RecyclerView rc_exposure_test;
    ArrayList<ExposureItemBean> exposureItemBeens;
    ExposureRecyclerListAdapter exposureRecyclerListAdapter;
    Context context ;
    public ExposureFragment1(){}
    public ExposureFragment1(Context context,ArrayList<ExposureItemBean> arrayList){
        this.context = context;
        this.exposureItemBeens = arrayList;
//        Log.v("zdxtest"," 构造 ");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exposurefragment1,container,false);
        rc_exposure_test = (RecyclerView)view.findViewById(R.id.rc_exposure_test);
        rc_exposure_test.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//        Log.v("zdxtest","  onCreateView ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        Log.v("zdxtest","  onActivityCreated ");
        exposureRecyclerListAdapter = new ExposureRecyclerListAdapter(context,this.exposureItemBeens);
        rc_exposure_test.setAdapter(exposureRecyclerListAdapter);
    }
}
