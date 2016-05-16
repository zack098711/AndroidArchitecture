package com.demos.zdx.androiddemos.view.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demos.zdx.androiddemos.R;
import com.demos.zdx.androiddemos.model.config.BaseTitleViewConfig;

/**
 * TODO: 默认页面头部样式
 * Created by zhaodongxu1 on 16/5/9.
 * Since Version :1.0
 */
public class BaseTitleView extends RelativeLayout {

    TextView tv_leftview;
    ImageView iv_leftview;
    TextView tv_rightview;
    ImageView iv_rightview;
    TextView tv_centerview;

    RelativeLayout rl_leftview, rl_rightview;

    BaseTitleViewConfig titleViewConfig;

    public BaseTitleView(Context context) {
        this(context, null, null);
    }

    public BaseTitleView(Context context, BaseTitleViewConfig titleViewConfig) {
        this(context, null, titleViewConfig);
    }

    public BaseTitleView(Context context, AttributeSet attrs, BaseTitleViewConfig titleViewConfig) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.base_title_view, this, true);
        if (titleViewConfig == null) {
            this.titleViewConfig = new BaseTitleViewConfig();
        } else {
            this.titleViewConfig = titleViewConfig;
        }
        initTitleView();
    }

    /**
     * 根据titleViewConfig初始视图
     */
    private void initTitleView() {
        tv_leftview = (TextView) findViewById(R.id.tv_leftview);
        iv_leftview = (ImageView) findViewById(R.id.iv_leftview);
        tv_rightview = (TextView) findViewById(R.id.tv_rightview);
        iv_rightview = (ImageView) findViewById(R.id.iv_rightview);
        tv_centerview = (TextView) findViewById(R.id.tv_centerview);
        rl_leftview = (RelativeLayout) findViewById(R.id.rl_leftview);
        rl_rightview = (RelativeLayout) findViewById(R.id.rl_rightview);

        setViews(titleViewConfig);
    }

    /**
     * 根据据配置参数设置view的显示状态
     *
     * @param titleViewConfig
     */
    public void setViews(BaseTitleViewConfig titleViewConfig) {
        //左侧图片按钮
        if (titleViewConfig.getLeftResId() > 0) {
            getLeftImageView().setVisibility(VISIBLE);
            getLeftImageView().setImageResource(titleViewConfig.getLeftResId());
        } else {
            getLeftImageView().setVisibility(GONE);
        }
        //左侧文字按钮
        if (!TextUtils.isEmpty(titleViewConfig.getLeftStr())) {
            getLeftTextView().setVisibility(VISIBLE);
            getLeftTextView().setText(titleViewConfig.getLeftStr());
        } else {
            getLeftTextView().setVisibility(GONE);
        }
        //右侧图片按钮
        if (titleViewConfig.getRightResId() > 0) {
            getRightImageView().setVisibility(VISIBLE);
            getRightImageView().setImageResource(titleViewConfig.getRightResId());
        } else {
            getRightImageView().setVisibility(GONE);
        }
        //右侧文字按钮
        if (!TextUtils.isEmpty(titleViewConfig.getRightStr())) {
            getRightTextView().setVisibility(VISIBLE);
            getRightTextView().setText(titleViewConfig.getRightStr());
        } else {
            getRightTextView().setVisibility(GONE);
        }
    }

    /**
     * 设置标题
     *
     * @param titleStr
     */
    public void setTitle(String titleStr) {
        if (!TextUtils.isEmpty(titleStr)) {
            getCenterView().setVisibility(VISIBLE);
            getCenterView().setText(titleStr);
        } else {
            getCenterView().setVisibility(GONE);
        }
    }

    public RelativeLayout getLeftView() {
        if (rl_leftview == null)
            return null;
        return rl_leftview;
    }

    public RelativeLayout getRightView() {
        if (rl_rightview == null)
            return null;
        return rl_rightview;
    }

    public TextView getLeftTextView() {
        return tv_leftview;
    }

    public TextView getRightTextView() {
        return tv_rightview;
    }

    public ImageView getLeftImageView() {
        return iv_leftview;
    }

    public ImageView getRightImageView() {
        return iv_rightview;
    }

    public TextView getCenterView() {
        return tv_centerview;
    }

}
