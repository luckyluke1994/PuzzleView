package com.xiaopo.flying.puzzle.special.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

import com.xiaopo.flying.puzzle.special.utils.CollageBitmapUtils;

public abstract class Res {
    protected boolean mAsyncIcon;
    protected Context mContext;
    private Bitmap mIconBitmap;
    private String mIconFileName;
    private int mIconID;
    private LocationType mIconType;
    private boolean mIsCircle;
    private boolean mIsNew;
    private boolean mIsSetTextBgColor;
    private boolean mIsShowText;
    private String mManagerName;
    private String mName;
    private String mShowText;
    private int mTextBgColor;
    private int mTextColor;

    public Res() {
        mAsyncIcon = false;
        mIsNew = false;
        mIsShowText = false;
        mTextColor = 0;
        mTextBgColor = 0;
        mIsSetTextBgColor = false;
        mIsCircle = false;
    }

    public boolean getIsShowText() {
        return mIsShowText;
    }

    public void setIsShowText(boolean flag) {
        mIsShowText = flag;
    }

    public String getShowText() {
        return mShowText;
    }

    public void setShowText(String showText) {
        mShowText = showText;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public void setTextBgColor(int color, boolean isSet) {
        mTextBgColor = color;
        mIsSetTextBgColor = isSet;
    }

    public boolean isSetTextBgColor() {
        return mIsSetTextBgColor;
    }

    public int getTextBgColor() {
        return mTextBgColor;
    }

    public void setTextBgColor(int color) {
        setTextBgColor(color, true);
    }

    public boolean isCircle() {
        return mIsCircle;
    }

    public void setCircle(boolean isCircle) {
        mIsCircle = isCircle;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public Resources getResources() {
        if (mContext != null) {
            return mContext.getResources();
        }
        return null;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getIconFileName() {
        return mIconFileName;
    }

    public void setIconFileName(String icon) {
        mIconFileName = icon;
    }

    public int getIconID() {
        return mIconID;
    }

    public void setIconID(int id) {
        mIconID = id;
    }

    public LocationType getIconType() {
        return mIconType;
    }

    public void setIconType(LocationType iconType) {
        mIconType = iconType;
    }

    public String getType() {
        return "TRes";
    }

    public boolean getAsyncIcon() {
        return mAsyncIcon;
    }

    public Bitmap getIconBitmap() {
        if (mIconFileName == null) {
            return null;
        }
        if (mIconType == LocationType.RES) {
            return CollageBitmapUtils.getImageFromResourceFile(getResources(), mIconID);
        } else if (mIconType != LocationType.ASSERT) {
            return mIconBitmap;
        } else {
            return CollageBitmapUtils.getImageFromAssetsFile(getResources(), mIconFileName);
        }
    }

    public boolean getIsNewValue() {
        return mIsNew;
    }

    public void setIsNewValue(boolean value) {
        mIsNew = value;
    }

    public String getManagerName() {
        return mManagerName;
    }

    public void setManagerName(String name) {
        mManagerName = name;
    }

    public enum LocationType {
        RES,
        ASSERT,
        FILTERED,
        ONLINE,
        CACHE
    }
}
