package com.demos.zdx.androiddemos.model.config;

/**
 * TODO: 工厂模式配置 title 参数
 * Created by zhaodongxu1 on 16/5/9.
 * Since Version : 1.0
 */
public class BaseTitleViewConfig {
    /**
     * leftview为imageview时的image source id
     */
    private int leftResId = 0;
    /**
     * leftview 为textview时String id
     */
    private String leftStr = "";
    /**
     * rightview 为imageview时的image source id
     */
    private int rightResId = 0;
    /**
     * rightview 为textview时的String id
     */
    private String rightStr = "";
    /**
     * centerview为textview时的string id
     */
    private String centerStr = "";
    /**
     * centerveiw 为自定义view 时的layout id
     */
    private int centerLayoutId = 0;

    public BaseTitleViewConfig setLeftResId(int leftResId ){
        this.leftResId = leftResId;
        return this;
    }

    public BaseTitleViewConfig setLeftStr(String leftStr){
        this.leftStr = leftStr;
        return this;
    }

    public BaseTitleViewConfig setRightResId(int rightResId){
        this.rightResId = rightResId;
        return this;
    }

    public BaseTitleViewConfig setRightStr(String rightStr){
        this.rightStr = rightStr;
        return this;
    }

    public BaseTitleViewConfig setCenterStr(String centerStr){
        this.centerStr = centerStr;
        return this;
    }

    public BaseTitleViewConfig setCenterLayoutId(int centerLayoutId){
        this.centerLayoutId = centerLayoutId;
        return this;
    }

    public String getCenterStr(){
        return this.centerStr;
    }

    public int getLeftResId(){
        return leftResId;
    }

    public String getLeftStr(){
        return leftStr;
    }

    public int getRightResId(){
        return rightResId;
    }

    public String getRightStr(){
        return rightStr;
    }

}
