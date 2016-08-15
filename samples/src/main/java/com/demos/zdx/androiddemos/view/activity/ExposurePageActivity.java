package com.demos.zdx.androiddemos.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.presenter.ExposurePresenter;
import com.demos.zdx.androiddemos.presenter.interfaces.IExposureInterface;
import com.demos.zdx.androiddemos.view.adapter.exposure.ExposureFragmentPagerAdapter;
import com.demos.zdx.androiddemos.view.fragment.ExposureFragment1;

import java.util.ArrayList;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public class ExposurePageActivity extends BaseActivity<IExposureInterface,ExposurePresenter>
        implements IExposureInterface ,ViewPager.OnPageChangeListener{
    private ArrayList<ExposureFragment1> mFragments = new ArrayList<>() ;
    private ViewPager mViewPager;
    private ExposureFragmentPagerAdapter mFragmentPagerAdapter;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        initView();
        initData();

    }

    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.vp_test_exposure);
    }

    private void initData(){
        mPresenter.setFragments(context);

        mFragmentPagerAdapter = new ExposureFragmentPagerAdapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(mFragmentPagerAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(this);

    }

    @Override
    public void onPageSelected(int position) {
        baseTitleView.setTitle("Fragmnet "+position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public ExposurePresenter createPresenter() {
        return new ExposurePresenter(this);
    }

    @Override
    public void setFragment(ArrayList<ExposureFragment1> fragments) {
        this.mFragments.clear();
        this.mFragments.addAll(fragments);

    }
}
