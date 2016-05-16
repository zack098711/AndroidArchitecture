package com.demos.zdx.androiddemos.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.bean.MainItemBean;
import com.demos.zdx.androiddemos.presenter.MainPagePresenter;
import com.demos.zdx.androiddemos.presenter.interfaces.IMainInterface;
import com.demos.zdx.androiddemos.view.adapter.MainAbsListAdapter;
import com.demos.zdx.androiddemos.view.adapter.MainRecyclerListAdapter;
import com.zdx.libs.multiadapter.OnItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Describe: 主页面
 * Created by ZhaoDongXu on 16/5/11.
 * Since Version : 1.0
 */
public class MainPageActivity extends BaseActivity<IMainInterface,MainPagePresenter> implements IMainInterface {
    Context context;
    @BindView(R.id.rv_recyclerview)
    public RecyclerView rv_recyclerview;
    @BindView(R.id.lv_listview)
    public ListView lv_listview;
    private MainRecyclerListAdapter mainRecyclerListAdapter;
    private MainAbsListAdapter absListAdapter;

    private ArrayList<MainItemBean> dataSource = new ArrayList<MainItemBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initView();
        initListener();
        mPresenter.setAdapterData();

    }

    @Override
    public MainPagePresenter createPresenter() {
        return new MainPagePresenter(this);
    }

    /**
     * 初始化数据
     */
    private void initView(){
        rv_recyclerview = (RecyclerView) findViewById(R.id.rv_recyclerview);
        lv_listview = (ListView) findViewById(R.id.lv_listview);
        rv_recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mainRecyclerListAdapter = new MainRecyclerListAdapter(this,this.dataSource);
        rv_recyclerview.setAdapter(mainRecyclerListAdapter);
    }

    /**
     * 初始化监听
     */
    private void initListener(){

        mainRecyclerListAdapter.setOnItemClickListener(new OnItemClickListener<MainItemBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, MainItemBean mainItemBean, int position) {
                Log.v("zdxtest1","  onitemclick position-> "+position+"  "+mainItemBean.toString());
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, MainItemBean mainItemBean, int position) {
                return false;
            }
        });


        getBaseTitleView().getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setAdapterData();
            }
        });
    }

    @Override
    public void setAdapterData(ArrayList<MainItemBean> dataSource) {
        this.dataSource.addAll(dataSource);
        Log.v("zdxtest1", "   this size  "+dataSource.size()+"    all size ->"+this.dataSource.size());
        if(absListAdapter == null){
            absListAdapter = new MainAbsListAdapter(this,this.dataSource);
            lv_listview.setAdapter(absListAdapter);
        }else{
            absListAdapter.notifyDataSetChanged();
        }

        mainRecyclerListAdapter.notifyDataSetChanged();

    }
}
