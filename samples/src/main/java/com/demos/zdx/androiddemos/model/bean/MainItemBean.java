package com.demos.zdx.androiddemos.model.bean;

import com.demos.zdx.androiddemos.model.enums.MainItemFlagEnum;
import com.demos.zdx.androiddemos.model.enums.MainItemStyleEnum;

/**
 * Describe:mainItem 样式
 * Created by ZhaoDongXu on 16/5/12.
 * Since Version :1.0
 */
public class MainItemBean {
//    public static final int STYLE_0 = 0;
//    public static final int STYLE_1 = 1;
    public int mDrawableID ;
    public String mDesc;
//    public int mType ;
    public String mTitle;
    /**
     * 当前 item 的功能类型
     */
    public MainItemFlagEnum mMainItemFlagEnum;

    /**
     * 当前 item 的视图样式
     */
    public MainItemStyleEnum mMainItemStyleEnum;
    public MainItemBean(){}
    public MainItemBean(String title,String desc,int drawableID ,MainItemStyleEnum mainItemStyleEnum
            ,MainItemFlagEnum mainItemFlagEnum){
        this.mTitle = title;
        this.mDesc = desc;
        this.mDrawableID = drawableID;
        this.mMainItemStyleEnum = mainItemStyleEnum;
        this.mMainItemFlagEnum = mainItemFlagEnum;
    }

    public MainItemBean withTitle(String title){
        this.mTitle = title;
        return this;
    }

    public MainItemBean withDrawableID(int drawableID){
        this.mDrawableID = drawableID;
        return this;
    }

    public MainItemBean withDesc(String desc){
        this.mDesc = desc;
        return this;
    }

    public MainItemBean withFlagEnum(MainItemFlagEnum mainItemFlagEnum){
        this.mMainItemFlagEnum = mainItemFlagEnum;
        return this;
    }

    public MainItemBean withItemStyleEnum(MainItemStyleEnum mainItemStyleEnum){
        this.mMainItemStyleEnum = mainItemStyleEnum;
        return this;
    }

    public MainItemBean withItemFlagEnum(MainItemFlagEnum mainItemFlagEnum){
        this.mMainItemFlagEnum = mainItemFlagEnum;
        return this;
    }

    @Override
    public String toString() {
        return mDrawableID+" "+mDesc+"  "+" "+mTitle+"  "
                +mMainItemStyleEnum.toString()+" "+mMainItemFlagEnum.toString();
    }

    public int getDrawableID(){
        return mDrawableID;
    }

    public String getDesc(){
        return mDesc;
    }


    public String getTitle(){
        return mTitle;
    }

    public MainItemFlagEnum getMainItemFlagEnum(){
        return this.mMainItemFlagEnum;
    }

    public MainItemStyleEnum getMainItemStyleEnum(){
        return this.mMainItemStyleEnum;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public void setDrawableID(int drawableID){
        this.mDrawableID = drawableID;
    }

    public void setDesc(String desc){
        this.mDesc = desc;
    }

    public void setMainItemFlagEnum(MainItemFlagEnum mainItemFlagEnum){
        this.mMainItemFlagEnum = mainItemFlagEnum;
    }

    public void setmMainItemStyleEnum(MainItemStyleEnum mainItemStyleEnum){
        this.mMainItemStyleEnum = mainItemStyleEnum;
    }

}
