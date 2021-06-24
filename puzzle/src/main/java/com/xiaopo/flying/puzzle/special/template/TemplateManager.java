package com.xiaopo.flying.puzzle.special.template;

import android.content.Context;
import android.graphics.Point;

import com.xiaopo.flying.puzzle.special.model.ImageRes;
import com.xiaopo.flying.puzzle.special.model.LibCollageInfo;
import com.xiaopo.flying.puzzle.special.model.LibCollagePoint;
import com.xiaopo.flying.puzzle.special.model.Res;
import com.xiaopo.flying.puzzle.special.model.TemplateRes;
import com.xiaopo.flying.puzzle.special.utils.ScreenInfoUtil;

import java.util.ArrayList;
import java.util.List;

public class TemplateManager {
    private int mCustomResHigh;
    private int mCustomResWidth;
    private Context mContext;
    private List<TemplateRes> mResList;

    public TemplateManager(Context context) {
        mResList = new ArrayList();
        mCustomResWidth = 0;
        mCustomResHigh = 0;
        mContext = context;
        int radius = ScreenInfoUtil.dip2px(context, 0.0f);
        int frameWidth = ScreenInfoUtil.dip2px(context, 10.0f);
        createResList(frameWidth, frameWidth, radius, 2);
    }

    public TemplateManager(Context context, int imgCount) {
        mResList = new ArrayList();
        mCustomResWidth = 0;
        mCustomResHigh = 0;
        mContext = context;
        int radius = ScreenInfoUtil.dip2px(context, 0.0f);
        int frameWidth = ScreenInfoUtil.dip2px(context, 10.0f);
        createResList(frameWidth, frameWidth, radius, imgCount);
    }

    public TemplateManager(Context context, int imgCount, int width, int height) {
        int widthCustom = width;
        int heightCustom = height;
        mResList = new ArrayList();
        mCustomResWidth = 0;
        mCustomResHigh = 0;
        mContext = context;
        int radius = ScreenInfoUtil.dip2px(context, 0.0f);
        int frameWidth = ScreenInfoUtil.dip2px(context, 10.0f);
        if (imgCount == 1) {
            if (width == 0 || height == 0) {
                widthCustom = 300;
                heightCustom = 400;
            }
            setCustomRes(widthCustom, heightCustom);
        }
        createResList(frameWidth, frameWidth, radius, imgCount);
    }

    public TemplateManager(Context context, int frameWidth, int imgCount) {
        mResList = new ArrayList();
        mCustomResWidth = 0;
        mCustomResHigh = 0;
        mContext = context;
        createResList(frameWidth, frameWidth, ScreenInfoUtil.dip2px(context, 0.0f), imgCount);
    }

    public List<TemplateRes> getResList() {
        return mResList;
    }

    private void setCustomRes(int width, int height) {
        mCustomResWidth = width;
        mCustomResHigh = height;
    }

    public void createResList(int innerFrameWidth, int outTerFrameWidth, int radius, int imgCount) {
        if (mResList == null) return;
        mResList.clear();
        switch (imgCount) {
            case 1: {
                Template1.init(mResList, mContext, radius, mCustomResWidth, mCustomResHigh, outTerFrameWidth, imgCount);
                break;
            }
            case 2: {
                Template2.init(mResList, mContext, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 3: {
                Template3.init(mResList, mContext, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 4: {
                Template4.init(mResList, mContext, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 5: {
                Template5.init(mResList, mContext, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 6: {
                Template6.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 7: {
                Template7.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 8: {
                Template8.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 9: {
                Template9.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 10: {
                Template10.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 11: {
                Template11.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 12: {
                Template12.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 13: {
                Template13.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 14: {
                Template14.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 15: {
                Template15.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 16: {
                Template16.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 17: {
                Template17.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
            case 18: {
                Template18.init(mResList, innerFrameWidth, outTerFrameWidth, radius, outTerFrameWidth, imgCount);
                break;
            }
        }
        for (int index = 0; index < mResList.size(); index++) {
            TemplateRes templateRes = mResList.get(index);
            templateRes.setIndex(index);
        }
    }

    public static TemplateRes initAssetItem(String name, ImageRes.FitType fitType, String iconName, List<LibCollageInfo> collageInfo, int radius, int frameWidth, int number) {
        TemplateRes item = new TemplateRes();
        item.setName(name);
        item.setIconFileName(iconName);
        item.setIconType(Res.LocationType.ASSERT);
        item.setOutFrameWidth(frameWidth);
        item.setInnerFrameWidth(frameWidth);
        item.setCollageInfo(collageInfo);
        item.setRoundRadius(radius);
        item.setFrameWidth(frameWidth);
        item.setImage(iconName);
        item.setImageType(Res.LocationType.ASSERT);
        item.setScaleType(fitType);
        item.setNumber(number);
        return item;
    }

    public static LibCollagePoint initCollagePoint1024(int xP, int yP) {
        int x = xP;
        int y = yP;
        x = (int) (x * 2.9882812f + 0.5f);
        y = (int) (y * 2.9882812f + 0.5f);
        LibCollagePoint cp = new LibCollagePoint();
        cp.setOriPoint(new Point(x, y));
        return cp;
    }

    public static LibCollagePoint initCollagePoint(int xP, int yP) {
        int x = xP;
        int y = yP;
        x = (int) (x * 1.0f + 0.5f);
        y = (int) (y * 1.0f + 0.5f);
        LibCollagePoint cp = new LibCollagePoint();
        cp.setOriPoint(new Point(x, y));
        return cp;
    }

    public static LibCollagePoint initCollagePoint1024(int xP, int yP, boolean isOutRectLinePoint) {
        int x = xP;
        int y = yP;
        x = (int) (x * 2.9882812f + 0.5f);
        y = (int) (y * 2.9882812f + 0.5f);
        LibCollagePoint cp = new LibCollagePoint();
        Point point = new Point(x, y);
        cp.setIsOutRectLinePoint(isOutRectLinePoint);
        cp.setOriPoint(point);
        return cp;
    }

    public static LibCollagePoint initCollagePoint(int xP, int yP, boolean isOutRectLinePoint) {
        int x = xP;
        int y = yP;
        x = (int) (x * 1.0f + 0.5f);
        y = (int) (y * 1.0f + 0.5f);
        LibCollagePoint cp = new LibCollagePoint();
        Point point = new Point(x, y);
        cp.setIsOutRectLinePoint(isOutRectLinePoint);
        cp.setOriPoint(point);
        return cp;
    }

    public static LibCollagePoint initCollagePoint(Point point, LibCollagePoint.LibCollagePointState xState, LibCollagePoint.LibCollagePointState yState) {
        LibCollagePoint cp = new LibCollagePoint();
        cp.setOriPoint(point);
        cp.setXState(xState);
        cp.setYState(yState);
        return cp;
    }

    public static LibCollagePoint initCollagePoint(int x, int y, LibCollagePoint.LibCollagePointState xState, LibCollagePoint.LibCollagePointState yState) {
        LibCollagePoint cp = new LibCollagePoint();
        cp.setOriPoint(new Point(x, y));
        cp.setXState(xState);
        cp.setYState(yState);
        return cp;
    }
}
