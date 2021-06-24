package com.xiaopo.flying.puzzle.special.model;

import android.content.Context;
import android.graphics.Bitmap;

import com.xiaopo.flying.puzzle.special.utils.CollageBitmapUtils;

public class ImageRes extends Res {
    protected LocationType mImageType;
    protected String mImageFileName;
    private FitType mFitType;
    private int mImageID;

    public String getImageFileName() {
        return mImageFileName;
    }

    public void setImageFileName(String image) {
        mImageFileName = image;
    }

    public LocationType getImageType() {
        return mImageType;
    }

    public void setImageType(LocationType imageType) {
        mImageType = imageType;
    }

    public boolean isImageResInLocal(Context context) {
        if (mImageType == LocationType.RES || mImageType == LocationType.ASSERT || mImageType == null || mImageType == LocationType.CACHE) {
            return true;
        }
        return true;
    }

    public void getImageBitmap(Context context, OnResImageLoadListener listener) {
        if (mImageType == null && listener != null) {
            listener.onImageLoadFailed();
        }
        if (mImageType == LocationType.RES) {
            if (listener != null) {
                listener.onImageLoadFinish(CollageBitmapUtils.getImageFromAssetsFile(getResources(), mImageFileName));
            }
        } else if (mImageType == LocationType.ASSERT) {
            if (listener != null) {
                mImageFileName = "emoji/icons/icon_1";
                listener.onImageLoadFinish(CollageBitmapUtils.getImageFromAssetsFile(getResources(), mImageFileName));
            }
        }
    }

    public Bitmap getLocalImageBitmap() {
        if (mImageType == null) {
            return null;
        }
        if (mImageType == LocationType.RES) {
            return CollageBitmapUtils.getImageFromResourceFile(getResources(), mImageID);
        }
        if (mImageType == LocationType.ASSERT) {
            return CollageBitmapUtils.getImageFromAssetsFile(getResources(), mImageFileName);
        }
        return null;
    }

    public FitType getFitType() {
        return mFitType;
    }

    public FitType getScaleType() {
        return mFitType;
    }

    public void setScaleType(FitType fitType) {
        mFitType = fitType;
    }

    public enum FitType {
        TITLE,
        SCALE
    }

    public interface OnResImageLoadListener {
        void onImageLoadFailed();

        void onImageLoadFinish(Bitmap bitmap);
    }
}
