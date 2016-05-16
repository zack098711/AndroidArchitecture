package com.demos.zdx.androiddemos.model.bean;

/**
 * Describe:mainItem 样式
 * Created by ZhaoDongXu on 16/5/12.
 * Since Version :1.0
 */
public class MainItemBean {
    public static final int STYLE_0 = 0;
    public static final int STYLE_1 = 1;
    public int mDrawableID ;
    public String mDesc;
    public int mType ;
    public String mTitle;
    public MainItemBean(int drawableID,String desc,int mType,String title){
        this.mDrawableID = drawableID;
        this.mDesc = desc;
        this.mType = mType;
        this.mTitle = title;
    }

    @Override
    public String toString() {
        return mDrawableID+" "+mDesc+"  "+mType+" "+mTitle;
    }

    public int getDrawableID(){
        return mDrawableID;
    }

    public String getDesc(){
        return mDesc;
    }

    public int getType(){
        return mType;
    }

    public String getTitle(){
        return mTitle;
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

    public void setType(int mType){
        this.mType = mType;
    }
}
