package com.demos.zdx.androiddemos.model.enums;

/**
 * Describe:
 * Created by ${ZhaoDongXu}
 * Since Date 16/7/25.
 * Since Version :
 */
public enum ExposureItemStyleEnum {
    EXPOSURE_ITEM_STYLE_NORMAL(0),
    EXPOSURE_ITEM_STYLE_TEST(1);
    public int mId ;
    ExposureItemStyleEnum(int id){
        this.mId = id;
    }

    public static ExposureItemStyleEnum getItemStyleEnumById(int id){
        switch (id){
            case 0:
                return EXPOSURE_ITEM_STYLE_NORMAL;
            case 1:
                return EXPOSURE_ITEM_STYLE_TEST;
            default:
                return null;

        }
    }
}
