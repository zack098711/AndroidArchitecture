package com.demos.zdx.androiddemos.view.adapter.exposure;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.demos.zdx.androiddemos.view.fragment.ExposureFragment1;

import java.util.ArrayList;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public class ExposureFragmentPagerAdapter extends FragmentPagerAdapter{
    ArrayList<ExposureFragment1> mFragments = new ArrayList<>();
    public ExposureFragmentPagerAdapter(FragmentManager fm,ArrayList<ExposureFragment1> mFragments){
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int position) {
        if(position<mFragments.size())
        return mFragments.get(position);
        return null;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
