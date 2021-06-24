package com.xiaopo.flying.puzzle.special.model;


import java.util.List;

public class TemplateRes extends ImageRes {
    private int mAspectRatio;
    private int mFrameWidth;
    private List<LibCollageInfo> mLibCollageInfo;
    private int mRadius;
    private int mIndex;
    private String mImage;
    private int mInnerFrameWidth;
    private int mOutFrameWidth;
    private int mNumber;
    private boolean mIsSelected;

    public TemplateRes() {
        mRadius = 0;
        mAspectRatio = 1;
        mIndex = 0;
        mLibCollageInfo = null;
        mFrameWidth = 12;
        mOutFrameWidth = 0;
        mInnerFrameWidth = 0;
        mNumber = 1;
    }

    public TemplateRes(int index, int radius, int aspectRatio, List<LibCollageInfo> LibCollageInfo, int number) {
        mRadius = radius;
        mAspectRatio = aspectRatio;
        mIndex = index;
        mLibCollageInfo = null;
        mFrameWidth = 12;
        mOutFrameWidth = 0;
        mInnerFrameWidth = 0;
        mLibCollageInfo = LibCollageInfo;
        mNumber = number;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        mImage = image;
    }

    public int getFrameWidth() {
        return mFrameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        mFrameWidth = frameWidth;
    }

    public int getAspectRatio() {
        return mAspectRatio;
    }

    public void setAspectRatio(int aspectRatio) {
        mAspectRatio = aspectRatio;
    }

    public List<LibCollageInfo> getCollageInfo() {
        return mLibCollageInfo;
    }

    public void setCollageInfo(List<LibCollageInfo> libCollageInfo) {
        mLibCollageInfo = libCollageInfo;
    }

    public int getRoundRadius() {
        return mRadius;
    }

    public void setRoundRadius(int radius) {
        mRadius = radius;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public int getIndex() {
        return mIndex;
    }

    public int getOutFrameWidth() {
        return mOutFrameWidth;
    }

    public void setOutFrameWidth(int outFrameWidth) {
        if (mLibCollageInfo != null) {
            for (int i = 0; i < mLibCollageInfo.size(); i++) {
                mLibCollageInfo.get(i).setOutFrameWidth(outFrameWidth);
            }
        }
        mOutFrameWidth = outFrameWidth;
    }

    public int getInnerFrameWidth() {
        return mInnerFrameWidth;
    }

    public void setInnerFrameWidth(int innerFrameWidth) {
        if (mLibCollageInfo != null) {
            for (int i = 0; i < mLibCollageInfo.size(); i++) {
                mLibCollageInfo.get(i).setInnerFrameWidth(innerFrameWidth);
            }
        }
        mInnerFrameWidth = innerFrameWidth;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setSelected(boolean selected) {
        mIsSelected = selected;
    }

    public boolean isSelected() {
        return mIsSelected;
    }
}
