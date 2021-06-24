package com.xiaopo.flying.puzzle.special.model;

import android.graphics.Point;

public class LibCollagePoint {
    private Point mOriPoint;
    private LibCollagePointState mMState;
    private LibCollagePointState mYState;
    private int mHLineMode;
    private int mInnerFrameWidth;
    private boolean mIsArcPoint;
    private boolean mIsOutRectLinePoint;
    private int mOutFrameWidth;
    private int mVLineMode;

    public LibCollagePoint() {
        mOutFrameWidth = 0;
        mInnerFrameWidth = 0;
        mVLineMode = 0;
        mHLineMode = 0;
        mMState = LibCollagePointState.innerAdd;
        mYState = LibCollagePointState.innerAdd;
        mIsArcPoint = false;
        mIsOutRectLinePoint = false;
    }

    public int getvLineMode() {
        return mVLineMode;
    }

    public void setvLineMode(int vLineMode) {
        mVLineMode = vLineMode;
    }

    public int gethLineMode() {
        return mHLineMode;
    }

    public void sethLineMode(int hLineMode) {
        mHLineMode = hLineMode;
    }

    public boolean getIsArcPoint() {
        return mIsArcPoint;
    }

    public void setIsArcPoint(boolean value) {
        mIsArcPoint = value;
    }

    public boolean getIsOutRectLinePoint() {
        return mIsOutRectLinePoint;
    }

    public void setIsOutRectLinePoint(boolean state) {
        mIsOutRectLinePoint = state;
    }

    public LibCollagePointState getXState() {
        return mMState;
    }

    public void setXState(LibCollagePointState state) {
        mMState = state;
    }

    public LibCollagePointState getYState() {
        return mYState;
    }

    public void setYState(LibCollagePointState state) {
        mYState = state;
    }

    public Point getOriPoint() {
        return mOriPoint;
    }

    public void setOriPoint(Point point) {
        mOriPoint = point;
    }

    public int getOutFrameWidth() {
        return mOutFrameWidth;
    }

    public void setOutFrameWidth(int outFrameWidth) {
        mOutFrameWidth = outFrameWidth;
    }

    public int getInnerFrameWidth() {
        return mInnerFrameWidth;
    }

    public void setInnerFrameWidth(int innerFrameWidth) {
        mInnerFrameWidth = innerFrameWidth;
    }

    public Point GetPoint() {
        Point point = new Point();
        int x = mOriPoint.x;
        int y = mOriPoint.y;
        if (mMState == LibCollagePointState.innerAdd) {
            x += (int) ((((float) mInnerFrameWidth) / 2.0f) + 0.5f);
        } else if (mMState == LibCollagePointState.innerDec) {
            x -= (int) ((((float) mInnerFrameWidth) / 2.0f) + 0.5f);
        } else if (mMState == LibCollagePointState.outerAdd) {
            x += mOutFrameWidth;
        } else if (mMState == LibCollagePointState.outerDec) {
            x -= mOutFrameWidth;
        }
        if (mYState == LibCollagePointState.innerAdd) {
            y += (int) ((((float) mInnerFrameWidth) / 2.0f) + 0.5f);
        } else if (mYState == LibCollagePointState.innerDec) {
            y -= (int) ((((float) mInnerFrameWidth) / 2.0f) + 0.5f);
        } else if (mYState == LibCollagePointState.outerAdd) {
            y += mOutFrameWidth;
        } else if (mYState == LibCollagePointState.outerDec) {
            y -= mOutFrameWidth;
        }
        point.x = x;
        point.y = y;
        return point;
    }

    public enum LibCollagePointState {
        innerAdd,
        innerDec,
        outerAdd,
        outerDec
    }
}
