package com.xiaopo.flying.puzzle.special.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

import com.xiaopo.flying.puzzle.special.utils.CollageBitmapUtils;

import java.util.ArrayList;
import java.util.List;

public class LibCollageInfo {
    private int mOriginCoordinateWidth = 3060;
    private int mOriginCoordinateHeight = 3060;
    private PointF mCenterPoint;
    private List<PointF> mVerticalPointFs;
    private boolean isCanCorner;
    private boolean isCanShadow;
    private Context mContext;
    private float mInnerFrameWidth;
    private List<Boolean> mLstIsOutLine;
    private String mMaskUri;
    private List<LibCollagePoint> mOldPointList;
    private int mOutFrameWidth;
    private int mRadius;

    public LibCollageInfo() {
        mRadius = 0;
        mOutFrameWidth = 0;
        mInnerFrameWidth = 0.0f;
        isCanShadow = true;
        isCanCorner = true;
        mLstIsOutLine = new ArrayList<>();
        mVerticalPointFs = null;
    }

    public LibCollageInfo(List<LibCollagePoint> oldPointList, int radius, int outFrameWidth, int innerFrameWidth) {
        mRadius = radius;
        mOutFrameWidth = outFrameWidth;
        mInnerFrameWidth = (float) innerFrameWidth;
        isCanShadow = true;
        isCanCorner = true;
        mLstIsOutLine = new ArrayList<>();
        mVerticalPointFs = null;
        mOldPointList = oldPointList;
    }

    public LibCollageInfo(List<LibCollagePoint> oldPointList) {
        mRadius = 0;
        mOutFrameWidth = 0;
        mInnerFrameWidth = 0;
        isCanShadow = true;
        isCanCorner = true;
        mLstIsOutLine = new ArrayList<>();
        mVerticalPointFs = null;
        mOldPointList = oldPointList;
    }

    public LibCollageInfo(List<LibCollagePoint> oldPointList, int radius, int outFrameWidth, int innerFrameWidth, String maskUri, Context context) {
        mRadius = radius;
        mOutFrameWidth = outFrameWidth;
        mInnerFrameWidth = (float) innerFrameWidth;
        isCanShadow = true;
        isCanCorner = true;
        mLstIsOutLine = new ArrayList<>();
        mVerticalPointFs = null;
        mOldPointList = oldPointList;
        mMaskUri = maskUri;
        mContext = context;
    }

    public void setOriginCoordinateWidth(int originCoordinateWidth) {
        mOriginCoordinateWidth = originCoordinateWidth;
    }

    public void setOriginCoordinateHeight(int originCoordinateHeight) {
        mOriginCoordinateHeight = originCoordinateHeight;
    }

    public int getOriginCoordinateWidth() {
        return mOriginCoordinateWidth;
    }

    public int getOriginCoordinateHeight() {
        return mOriginCoordinateHeight;
    }

    public List<LibCollagePoint> getOldpointList() {
        return mOldPointList;
    }

    public void setOldpointList(List<LibCollagePoint> oldPointList) {
        mOldPointList = oldPointList;
        mCenterPoint = getCenterPoint();
    }

    public int getOutFrameWidth() {
        return mOutFrameWidth;
    }

    public void setOutFrameWidth(int outFrameWidth) {
        mOutFrameWidth = outFrameWidth;
    }

    public int getInnerFrameWidth() {
        return (int) (mInnerFrameWidth + 0.5f);
    }

    public void setInnerFrameWidth(int innerFrameWidth) {
        mInnerFrameWidth = (float) innerFrameWidth;
    }

    public int getRadius() {
        return mRadius;
    }

    public void setRadius(int radius) {
        mRadius = radius;
    }

    public boolean getIsCanShadow() {
        return isCanShadow;
    }

    public void setIsCanShadow(boolean value) {
        isCanShadow = value;
    }

    public boolean getIsCanCorner() {
        return isCanCorner;
    }

    public void setIsCanCorner(boolean value) {
        isCanCorner = value;
    }

    public String getMaskUri() {
        return mMaskUri;
    }

    public void setMaskUri(String uri) {
        mMaskUri = uri;
    }

    public Bitmap getMaskBitmap(Context context) {
        if (mMaskUri != null) {
            return CollageBitmapUtils.getImageFromAssetsFile(context.getResources(), mMaskUri);
        }
        return null;
    }

    public List<Point> GetPoints(float scale) {
        List<Point> points = new ArrayList<>();
        List<PointF> vPoints = GetVerticaScalePoints(scale);
        if (mOutFrameWidth > -1) {
            float f1 = ((float) (3060 - (mOutFrameWidth * 2))) / 3060.0f;
            for (int i = 0; i < vPoints.size(); i++) {
                PointF point = vPoints.get(i);
                Point p = new Point();
                if (point.x < mCenterPoint.x) {
                    p.x = ((int) ((point.x * f1) - 0.5f)) + mOutFrameWidth;
                } else {
                    p.x = ((int) ((point.x * f1) + 1.91f)) + mOutFrameWidth;
                }
                if (point.y < mCenterPoint.y) {
                    p.y = (int) ((((point.y * f1) * scale) - 0.5f) + (((float) mOutFrameWidth) * scale));
                } else {
                    p.y = (int) ((((point.y * f1) * scale) + 1.91f) + (((float) mOutFrameWidth) * scale));
                }
                points.add(p);
            }
        }
        return points;
    }

    public List<PointF> getVerticalPointList() {
        PointF pF;
        if (mCenterPoint == null) {
            mCenterPoint = getCenterPoint();
        }
        List<PointF> points = new ArrayList<>();
        mLstIsOutLine.clear();
        int i = 0;
        while (i < mOldPointList.size() - 1) {
            pF = getVerticalPoint(changePointToFPointF(mOldPointList.get(i).getOriPoint()), changePointToFPointF(mOldPointList.get(i + 1).getOriPoint()), mCenterPoint);
            if (mOldPointList.get(i).getIsOutRectLinePoint() && mOldPointList.get(i + 1).getIsOutRectLinePoint()) {
                mLstIsOutLine.add(Boolean.TRUE);
            } else {
                mLstIsOutLine.add(Boolean.FALSE);
            }
            points.add(pF);
            i++;
        }
        pF = getVerticalPoint(
                changePointToFPointF(mOldPointList.get(0).getOriPoint()),
                changePointToFPointF(mOldPointList.get(mOldPointList.size() - 1).getOriPoint()), mCenterPoint);
        if (mOldPointList.get(0).getIsOutRectLinePoint() && mOldPointList.get(mOldPointList.size() - 1).getIsOutRectLinePoint()) {
            mLstIsOutLine.add(Boolean.TRUE);
        } else {
            mLstIsOutLine.add(Boolean.FALSE);
        }
        points.add(pF);
        return points;
    }

    public List<Point> getVerticaPointList(float wScale, float hScale, int x, int y) {
        PointF pF;
        if (mCenterPoint == null) {
            mCenterPoint = getCenterPoint();
        }
        List<Point> points = new ArrayList();
        for (int i = 0; i < mOldPointList.size() - 1; i++) {
            pF = getVerticalPoint(changePointToFPointF(mOldPointList.get(i).getOriPoint()), changePointToFPointF(mOldPointList.get(i + 1).getOriPoint()), mCenterPoint);
            points.add(new Point((int) (((pF.x - ((float) x)) * wScale) + 0.5f), (int) (((pF.y - ((float) y)) * hScale) + 0.5f)));
        }
        pF = getVerticalPoint(changePointToFPointF(mOldPointList.get(0).getOriPoint()), changePointToFPointF(mOldPointList.get(mOldPointList.size() - 1).getOriPoint()), mCenterPoint);
        points.add(new Point((int) (((pF.x - ((float) x)) * wScale) + 0.5f), (int) (((pF.y - ((float) y)) * hScale) + 0.5f)));
        return points;
    }

    public List<PointF> GetVerticaScalePoints(float scale) {
        return GetVerticaScalePoints(1.0f, 1.0f, 0, 0, scale);
    }

    public List<PointF> GetVerticaScalePoints(float wScale, float hScale, int x, int y, float scale) {
        int i;
        if (mCenterPoint == null) {
            mCenterPoint = getCenterPoint();
        }
        if (mVerticalPointFs == null) {
            mVerticalPointFs = getVerticalPointList();
        }
        List<PointF> points = mVerticalPointFs;
        List<PointF> ps = new ArrayList<>();
        List<PointF> ps1 = new ArrayList<>();
        List<LibCollageLineInfo> cLineInfoList = new ArrayList<>();
        for (i = 0; i < points.size(); i++) {
            double k;
            double a = getCosRatio(mCenterPoint, points.get(i));
            double A = lineSpace(mCenterPoint, points.get(i));
            float x2 = mCenterPoint.x;
            if (a != 0.0d) {
                if (mLstIsOutLine.get(i)) {
                    x2 = ((float) ((((double) mInnerFrameWidth) + A) * a)) + mCenterPoint.x;
                } else {
                    x2 = ((float) ((A - ((double) mInnerFrameWidth)) * a)) + mCenterPoint.x;
                }
            }
            float y2 = getPointYByLine(x2, mCenterPoint, points.get(i));
            if (a == 0.0d) {
                if (y2 > 0.0f) {
                    if (mLstIsOutLine.get(i)) {
                        y2 = points.get(i).y + (mInnerFrameWidth / scale);
                    } else {
                        y2 = points.get(i).y - (mInnerFrameWidth / scale);
                    }
                } else {
                    if (mLstIsOutLine.get(i)) {
                        y2 = points.get(i).y - (mInnerFrameWidth / scale);
                    } else {
                        y2 = points.get(i).y + (mInnerFrameWidth / scale);
                    }
                }
            }
            ps.add(new PointF((x2 - ((float) x)) * wScale, (y2 - ((float) y)) * wScale));
            Point p3 = mOldPointList.get(i).getOriPoint();
            PointF pA = points.get(i);
            double c = 0.0d;
            if (((float) p3.x) - pA.x != 0.0f) {
                c = (double) (((((float) p3.x) * pA.y) - (pA.x * ((float) p3.y))) / (((float) p3.x) - pA.x));
            }
            double d;
            if (p3.x != 0) {
                d = (double) p3.y;
                k = (d - c) / ((double) p3.x);
            } else {
                d = (double) pA.y;
                k = (d - c) / ((double) pA.x);
            }
            double c1 = ((double) y2) - (((double) x2) * k);
            LibCollageLineInfo cLineInfo = new LibCollageLineInfo();
            cLineInfo.setLineA(k);
            cLineInfo.setLineB(c1);
            cLineInfo.setVerPointF(new PointF(x2, y2));
            if (((float) p3.x) - pA.x == 0.0f) {
                cLineInfo.setIsXFunction(true);
            }
            cLineInfoList.add(cLineInfo);
        }
        if (cLineInfoList.size() > 0) {
            cLineInfoList.add(cLineInfoList.get(0));
        }
        for (i = 0; i < cLineInfoList.size() - 1; i++) {
            float x3;
            float y3;
            LibCollageLineInfo cLineInfo = cLineInfoList.get(i);
            LibCollageLineInfo cLineInfoNext = cLineInfoList.get(i + 1);
            if (cLineInfo.getIsXFunction()) {
                x3 = cLineInfo.getVerPointF().x;
                y3 = (float) ((cLineInfoNext.getLineA() * ((double) x3)) + cLineInfoNext.getLineB());
            } else if (cLineInfoNext.getIsXFunction()) {
                x3 = cLineInfoNext.getVerPointF().x;
                y3 = (float) ((((double) x3) * cLineInfo.getLineA()) + cLineInfo.getLineB());
            } else if (cLineInfoNext.getLineA() == 0.0d) {
                y3 = (float) cLineInfoNext.getLineB();
                x3 = (float) ((((double) y3) - cLineInfo.getLineB()) / cLineInfo.getLineA());
            } else if (cLineInfo.getLineA() == 0.0d) {
                y3 = (float) cLineInfo.getLineB();
                x3 = (float) ((((double) y3) - cLineInfoNext.getLineB()) / cLineInfoNext.getLineA());
            } else {
                x3 = (float) ((cLineInfoNext.getLineB() - cLineInfo.getLineB()) / (cLineInfo.getLineA() - cLineInfoNext.getLineA()));
                y3 = (float) ((((double) x3) * cLineInfo.getLineA()) + cLineInfo.getLineB());
            }
            ps1.add(new PointF((x3 - ((float) x)) * wScale, (y3 - ((float) y)) * wScale));
        }
        return ps1;
    }

    public PointF changePointToFPointF(Point point) {
        return new PointF(point);
    }

    private double getCosRatio(PointF p1, PointF p2) {
        return ((double) Math.round(p2.x - p1.x)) /
                Math.sqrt((double) (((p2.x - p1.x) * (p2.x - p1.x)) + ((p2.y - p1.y) * (p2.y - p1.y))));
    }

    private float getPointYByLine(float x, PointF p1, PointF p2) {
        float x0 = p1.x;
        float y0 = p1.y;
        float x1 = p2.x;
        float y1 = p2.y;
        float b = x1 - x0;
        float a = y0 - y1;
        float c = (x0 * y1) - (x1 * y0);
        if (((double) Math.abs(b)) > 0.01d) {
            return (-(c / b)) - ((a / b) * x);
        }
        return -a;
    }

    public List<Point> getOriPoints() {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < mOldPointList.size(); i++) {
            mOldPointList.get(i).setInnerFrameWidth(0);
            mOldPointList.get(i).setOutFrameWidth(0);
            points.add(mOldPointList.get(i).GetPoint());
        }
        return points;
    }

    public Rect getRect(float scale) {
        if (mCenterPoint == null) {
            mCenterPoint = getCenterPoint();
        }
        List<Point> list = GetPoints(scale);
        int xmin = list.get(0).x;
        int ymin = list.get(0).y;
        int xmax = xmin;
        int ymax = ymin;
        for (int j = 0; j < list.size(); j++) {
            int x = list.get(j).x;
            int y = list.get(j).y;
            if (xmax < x) {
                xmax = x;
            }
            if (ymax < y) {
                ymax = y;
            }
            if (xmin > x) {
                xmin = x;
            }
            if (ymin > y) {
                ymin = y;
            }
        }
        return new Rect(xmin, ymin, xmax, ymax);
    }

    public Rect getOriRect() {
        List<Point> list = getOriPoints();
        int xmin = list.get(0).x;
        int ymin = list.get(0).y;
        int xmax = xmin;
        int ymax = ymin;
        for (int j = 0; j < list.size(); j++) {
            int x = list.get(j).x;
            int y = list.get(j).y;
            if (xmax < x) {
                xmax = x;
            }
            if (ymax < y) {
                ymax = y;
            }
            if (xmin > x) {
                xmin = x;
            }
            if (ymin > y) {
                ymin = y;
            }
        }
        return new Rect(xmin, ymin, xmax, ymax);
    }

    public Path getPath(float wScale, float hScale) {
        Path path = new Path();
        List<Point> points = GetPoints(1.0f);
        if (points.size() < 3) {
            return null;
        }
        for (int i = 0; i < points.size(); i++) {
            int w = (int) ((((float) points.get(i).x) * wScale) + 0.5f);
            int h = (int) ((((float) points.get(i).y) * hScale) + 0.5f);
            if (i == 0) {
                path.moveTo((float) w, (float) h);
            } else {
                path.lineTo((float) w, (float) h);
            }
        }
        path.close();
        return path;
    }

    public Path getPath(float wScale, float hScale, int x, int y, float scale) {
        Path path = new Path();
        List<Point> points = GetPoints(scale);
        if (points.size() < 3) {
            return null;
        }
        int i = 0;
        while (i < points.size()) {
            int w = (int) ((((float) (points.get(i).x - x)) * wScale) + 0.5f);
            int h = (int) ((((float) (points.get(i).y - y)) * hScale) + 0.5f);
            if (i == 0) {
                path.moveTo((float) w, (float) h);
            } else if (mOldPointList.get(i).getIsArcPoint()) {
                path.quadTo((float) w, (float) h,
                            (float) ((int) ((((float) (points.get(i + 1).x - x)) * wScale) + 0.5f)),
                            (float) ((int) ((((float) (points.get(i + 1).y - y)) * hScale) + 0.5f)));
                i++;
            } else {
                path.lineTo((float) w, (float) h);
            }
            i++;
        }
        path.close();
        return path;
    }

    public PointF getScaleCenterPoint(float wScale, float hScale, int x, int y) {
        if (mCenterPoint == null) {
            mCenterPoint = getCenterPoint();
        }
        PointF pointF = mCenterPoint;
        PointF pointF2 =
                new PointF((pointF.x - ((float) x)) * wScale, (pointF.y - ((float) y)) * hScale);
        return pointF2;
    }

    public PointF getCenterPoint() {
        int i;
        int N = mOldPointList.size();
        PointF[] p = new PointF[(N + 1)];
        for (i = 0; i < N; i++) {
            p[i] = new PointF((float) mOldPointList.get(i).getOriPoint().x, (float) mOldPointList.get(i).getOriPoint().y);
        }
        p[N] = new PointF();
        p[N].x = p[0].x;
        p[N].y = p[0].y;
        float A = 0.0f;
        for (i = 0; i < N; i++) {
            A = (float) (((double) A) + cal(i, i + 1, p));
        }
        A /= 2.0f;
        float cy = 0.0f;
        float cx = 0.0f;
        for (i = 0; i < N; i++) {
            cx = (float) (((double) cx) + (((double) (p[i].x + p[i + 1].x)) * cal(i, i + 1, p)));
            cy = (float) (((double) cy) + (((double) (p[i].y + p[i + 1].y)) * cal(i, i + 1, p)));
        }
        return new PointF((float) Math.round(cx / (6.0f * A)), (float) Math.round(cy / (6.0f * A)));
    }

    public double cal(int i, int j, PointF[] p) {
        return (double) ((p[i].x * p[j].y) - (p[j].x * p[i].y));
    }

    private double lineSpace(PointF p1, PointF p2) {
        return Math.sqrt((double) (((p1.x - p2.x) * (p1.x - p2.x)) + ((p1.y - p2.y) * (p1.y - p2.y))));
    }

    public PointF getVerticalPoint(PointF pt1, PointF pt2, PointF pt3) {
        return getVerticalPoint(pt3.x, pt3.y, pt1.x, pt1.y, pt2.x, pt2.y);
    }

    private PointF getVerticalPoint(float x, float y, float startX, float startY, float endX,
                                    float endY) {
        float r = (((x - startX) * (endX - startX)) + ((y - startY) * (endY - startY))) / (((startX - endX) * (startX - endX)) + ((startY - endY) * (startY - endY)));
        return new PointF(((endX - startX) * r) + startX, ((endY - startY) * r) + startY);
    }
}
