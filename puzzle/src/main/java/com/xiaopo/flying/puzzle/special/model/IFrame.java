package com.xiaopo.flying.puzzle.special.model;

public class IFrame {
    private int mTempRes;
    private boolean mIsSelected;
    private boolean mIsOriginal;

    public IFrame(int tempRes) {
        mTempRes = tempRes;
        mIsOriginal = false;
        mIsSelected = false;
    }

    public IFrame(int tempRes, boolean isOriginal, boolean isSelected) {
        mTempRes = tempRes;
        mIsOriginal = isOriginal;
        mIsSelected = isSelected;
    }

    public int getTempRes() {
        return mTempRes;
    }

    public boolean isSelected() {
        return mIsSelected;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }

    public boolean isOriginal() {
        return mIsOriginal;
    }
}
