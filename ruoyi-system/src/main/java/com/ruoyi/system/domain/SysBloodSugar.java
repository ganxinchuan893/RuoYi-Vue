package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 血糖记录实体
 */
public class SysBloodSugar extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 血糖ID */
    private Long sugarId;

    /** 用户ID */
    private Long userId;

    /** 血糖值(mmol/L) */
    @Excel(name = "血糖值", prompt = "mmol/L")
    private String sugarValue;

    /** 测量时间 */
    @Excel(name = "测量时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private String measureTime;

    /** 餐前/餐后（0空腹 1餐前 2餐后） */
    @Excel(name = "餐前/餐后", readConverterExp = "0=空腹,1=餐前,2=餐后")
    private String mealType;

    /** 备注 */
    private String remark;

    public Long getSugarId() {
        return sugarId;
    }

    public void setSugarId(Long sugarId) {
        this.sugarId = sugarId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSugarValue() {
        return sugarValue;
    }

    public void setSugarValue(String sugarValue) {
        this.sugarValue = sugarValue;
    }

    public String getMeasureTime() {
        return measureTime;
    }

    public void setMeasureTime(String measureTime) {
        this.measureTime = measureTime;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SysBloodSugar{" +
                "sugarId=" + sugarId +
                ", userId=" + userId +
                ", sugarValue='" + sugarValue + '\'' +
                ", measureTime='" + measureTime + '\'' +
                ", mealType='" + mealType + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
