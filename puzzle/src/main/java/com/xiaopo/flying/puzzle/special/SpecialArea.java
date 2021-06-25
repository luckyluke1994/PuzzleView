package com.xiaopo.flying.puzzle.special;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.widget.FrameLayout;

import com.xiaopo.flying.puzzle.Area;
import com.xiaopo.flying.puzzle.Line;
import com.xiaopo.flying.puzzle.PuzzlePiece;
import com.xiaopo.flying.puzzle.special.model.LibCollageInfo;
import com.xiaopo.flying.puzzle.special.model.LibCollagePoint;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SpecialArea implements Area {
    private int mParentWidth;
    private int mParentHeight;
    private LibCollageInfo mLibCollageInfo;
    private Path areaPath = new Path();
    private RectF areaRect = new RectF();
    private Region areaRegion = new Region();

    private int glowRadius = 10;
    private int innerSpaceBorder = 0;
    private Paint mGlowPaint;
    private Bitmap mMaskBitmap = null;

    public SpecialArea(int parentWidth, int parentHeight) {
        mParentWidth = parentWidth;
        mParentHeight = parentHeight;
    }

    @Override
    public float left() {
        return areaRect.left;
    }

    @Override
    public float top() {
        return areaRect.top;
    }

    @Override
    public float right() {
        return areaRect.right;
    }

    @Override
    public float bottom() {
        return areaRect.bottom;
    }

    @Override
    public float centerX() {
        return 0;
    }

    @Override
    public float centerY() {
        return 0;
    }

    @Override
    public float width() {
        return areaRect.width();
    }

    @Override
    public float height() {
        return areaRect.height();
    }

    @Override
    public PointF getCenterPoint() {
        return null;
    }

    @Override
    public boolean contains(PointF point) {
        return false;
    }

    @Override
    public boolean contains(float x, float y) {
        if (isDrawByPath()) {
            return areaRegion.contains((int) x, (int) y);
        } else {
            if (mMaskBitmap != null) {
                try {
                    float relativeX = x - areaRect.left;
                    float relativeY = y - areaRect.top;
                    int shapePointX = (int) (relativeX / (width() / mMaskBitmap.getWidth()));
                    int shapePointY = (int) (relativeY / (height() / mMaskBitmap.getHeight()));
                    if (shapePointX < 0) {
                        shapePointX = 0;
                    }
                    if (shapePointY < 0) {
                        shapePointY = 0;
                    }
                    return mMaskBitmap.getPixel(shapePointX, shapePointY) != 0;
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public boolean contains(Line line) {
        return false;
    }

    @Override
    public Path getAreaPath() {
        return areaPath;
    }

    @Override
    public RectF getAreaRect() {
        return areaRect;
    }

    @Override
    public List<Line> getLines() {
        return new ArrayList<>();
    }

    @Override
    public PointF[] getHandleBarPoints(Line line) {
        return new PointF[0];
    }

    @Override
    public float radian() {
        return 0;
    }

    @Override
    public void setRadian(float radian) {

    }

    @Override
    public float getPaddingLeft() {
        return 0;
    }

    @Override
    public float getPaddingTop() {
        return 0;
    }

    @Override
    public float getPaddingRight() {
        return 0;
    }

    @Override
    public float getPaddingBottom() {
        return 0;
    }

    @Override
    public void setPadding(float padding) {

    }

    @Override
    public void setPadding(float paddingLeft, float paddingTop, float paddingRight, float paddingBottom) {

    }

    @Override
    public PuzzlePiece createPuzzlePiece(Context context, Drawable drawable, Matrix matrix) {
        return new SpecialPuzzlePiece(context, drawable, this, matrix);
    }

    @Override
    public boolean isDrawByPath() {
        return (mLibCollageInfo != null && mLibCollageInfo.getMaskUri() == null);
    }

    @Override
    public Bitmap getAreaMask(Context context) {
        return (mLibCollageInfo == null) ? null : getMaskBitmap(mLibCollageInfo.getMaskBitmap(context));
    }

    public void setLibCollageInfo(LibCollageInfo libCollageInfo) {
        mLibCollageInfo = libCollageInfo;
        updateSizeAndPath();
        updateRegion();
    }

    private void updateSizeAndPath() {
        if (mParentHeight == 0 || mParentWidth == 0 || mLibCollageInfo == null) return;
        List<LibCollagePoint> listPoint = mLibCollageInfo.getOldpointList();
        if (listPoint == null || listPoint.isEmpty()) return;
        int minX = listPoint.get(0).getOriPoint().x;
        int maxX = listPoint.get(0).getOriPoint().x;
        int minY = listPoint.get(0).getOriPoint().y;
        int maxY = listPoint.get(0).getOriPoint().y;
        for (int index = 1; index < listPoint.size(); index++) {
            LibCollagePoint point = listPoint.get(index);
            if (minX > point.getOriPoint().x) {
                minX = point.getOriPoint().x;
            }
            if (maxX < point.getOriPoint().x) {
                maxX = point.getOriPoint().x;
            }
            if (minY > point.getOriPoint().y) {
                minY = point.getOriPoint().y;
            }
            if (maxY < point.getOriPoint().y) {
                maxY = point.getOriPoint().y;
            }
        }
        float minXRelative = minX * 1f / mLibCollageInfo.getOriginCoordinateWidth() * mParentWidth;
        float maxXRelative = maxX * 1f / mLibCollageInfo.getOriginCoordinateWidth() * mParentWidth;
        float minYRelative = minY * 1f / mLibCollageInfo.getOriginCoordinateHeight() * mParentHeight;
        float maxYRelative = maxY * 1f / mLibCollageInfo.getOriginCoordinateHeight() * mParentHeight;
        areaRect.set(minXRelative, minYRelative, maxXRelative, maxYRelative);

        float rotationW = mParentWidth * 1f / mLibCollageInfo.getOriginCoordinateWidth();
        float rotationH =
                mParentHeight * 1f / (mLibCollageInfo.getOriginCoordinateHeight() * (mParentHeight * 1f / mParentWidth));
        float mScale = mParentHeight * 1f / mParentWidth;
        Rect rect = mLibCollageInfo.getRect(mScale);
        areaPath.reset();
        areaPath.set(mLibCollageInfo.getPath(rotationW, rotationH, rect.left, rect.top, mScale));

        int x1 = (int) (((((float) rect.left) * 1.0f) * rotationW) + 0.5f);
        int y1 = (int) (((((float) rect.top) * 1.0f) * rotationH) + 0.5f);

        Matrix translateMatrix = new Matrix();
        translateMatrix.postTranslate(x1, y1);
        areaPath.transform(translateMatrix);
    }

    private void updateRegion() {
        RectF regionRect = new RectF();
        areaPath.computeBounds(regionRect, true);
        areaRegion.setPath(areaPath,
                new Region(
                        Math.round(regionRect.left + 0.5f),
                        Math.round(regionRect.top + 0.5f),
                        Math.round(regionRect.right + 0.5f),
                        Math.round(regionRect.bottom + 0.5f)));
    }

    private Bitmap getMaskBitmap(Bitmap bitmap) {
        if (bitmap == null) return null;
        if (mMaskBitmap == null) {
            int width = Math.round(width() + 0.5f);
            int height = Math.round(height() + 0.5f);
            Bitmap scaleMaskBitmap = Bitmap.createScaledBitmap(
                    bitmap,
                    width - 2 * innerSpaceBorder - 2 * glowRadius,
                    height - 2 * innerSpaceBorder - 2 * glowRadius,
                    true
            );

            // extract the alpha from the source image
            Bitmap alpha = scaleMaskBitmap.extractAlpha(
                    mGlowPaint,
                    new int[]{(int) (scaleMaskBitmap.getWidth() / 2f), (int) (scaleMaskBitmap.getHeight() / 2f)}
            );

            // The output bitmap (with the icon + glow)
            Bitmap bmp = Bitmap.createBitmap(
                    width,
                    height, Bitmap.Config.ARGB_8888
            );

            // The canvas to paint on the image
            Canvas canvas = new Canvas(bmp);

            canvas.drawBitmap(
                    alpha,
                    innerSpaceBorder + glowRadius,
                    innerSpaceBorder + glowRadius,
                    mGlowPaint
            );

            // original icon
            canvas.drawBitmap(
                    scaleMaskBitmap,
                    innerSpaceBorder + glowRadius,
                    innerSpaceBorder + glowRadius,
                    null
            );
            mMaskBitmap = bmp;
        }
        return mMaskBitmap;
    }
}
