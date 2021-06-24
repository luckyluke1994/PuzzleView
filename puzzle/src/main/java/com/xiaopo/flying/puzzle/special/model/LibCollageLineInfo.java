package com.xiaopo.flying.puzzle.special.model;

import android.graphics.PointF;

public class LibCollageLineInfo {
    private double mA;
    private double mB;
    private boolean mIsXFunction;
    private PointF mVerPointF;

    public LibCollageLineInfo() {
        mIsXFunction = false;
    }

    public PointF getVerPointF() {
        return mVerPointF;
    }

    public void setVerPointF(PointF pf) {
        mVerPointF = pf;
    }

    public double getLineA() {
        return mA;
    }

    public void setLineA(double a) {
        mA = a;
    }

    public double getLineB() {
        return mB;
    }

    public void setLineB(double b) {
        mB = b;
    }

    public boolean getIsXFunction() {
        return mIsXFunction;
    }

    public void setIsXFunction(boolean b) {
        mIsXFunction = b;
    }
}
