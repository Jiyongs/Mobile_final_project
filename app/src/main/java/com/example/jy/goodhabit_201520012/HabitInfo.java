package com.example.jy.goodhabit_201520012;

/**
 * Created by JY on 2017-12-02.
 */

public class HabitInfo {

    private String HName;
    private String HTarget;
    private String IColor;
    private String DoDay;
    private String HCount;

    //그리드뷰에 올리기 위한 함수
    public HabitInfo(String hname, String htarget, String icolor, String doDay, String hcount){
        this.HName = hname;
        this.HTarget = htarget;
        this.IColor = icolor;
        this.DoDay = doDay;
        this.HCount = hcount;
    }

    //get
    public String getHName() {
        return HName;
    }

    public String getHTarget() {
        return HTarget;
    }

    public String getIColor() {
        return IColor;
    }

    public String getDoDay() {
        return DoDay;
    }

    public String getHCount() {
        return HCount;
    }

    //set
    public void setHName(String HName) {
        this.HName = HName;
    }

    public void setHTarget(String HTarget) {
        this.HTarget = HTarget;
    }

    public void setIColor(String IColor) {
        this.IColor = IColor;
    }

    public void setDoDay(String doDay) {
        this.DoDay = doDay;
    }

    public void setHCount(String HCount) {
        this.HCount = HCount;
    }
}
