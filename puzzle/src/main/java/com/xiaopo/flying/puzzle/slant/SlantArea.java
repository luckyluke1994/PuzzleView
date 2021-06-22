package com.xiaopo.flying.puzzle.slant;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;

import com.xiaopo.flying.puzzle.Area;
import com.xiaopo.flying.puzzle.Line;
import com.xiaopo.flying.puzzle.transform.FormLine;
import com.xiaopo.flying.puzzle.transform.PaddingUtils;
import com.xiaopo.flying.puzzle.transform.PointD;
import com.xiaopo.flying.puzzle.transform.Vector;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static com.xiaopo.flying.puzzle.slant.SlantUtils.distance;
import static com.xiaopo.flying.puzzle.slant.SlantUtils.getPoint;
import static com.xiaopo.flying.puzzle.slant.SlantUtils.intersectionOfLines;

/**
 * @author wupanjie
 */
class SlantArea implements Area {
    SlantLine lineLeft;
    SlantLine lineTop;
    SlantLine lineRight;
    SlantLine lineBottom;

    CrossoverPointF leftTop;
    CrossoverPointF leftBottom;
    CrossoverPointF rightTop;
    CrossoverPointF rightBottom;

    private PointF tempPoint;

    private float paddingLeft;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;
    private float radian;

    private Path areaPath = new Path();
    private RectF areaRect = new RectF();
    private PointF[] handleBarPoints = new PointF[2];

    SlantArea() {
        handleBarPoints[0] = new PointF();
        handleBarPoints[1] = new PointF();

        leftTop = new CrossoverPointF();
        leftBottom = new CrossoverPointF();
        rightTop = new CrossoverPointF();
        rightBottom = new CrossoverPointF();

        tempPoint = new PointF();
    }

    SlantArea(SlantArea src) {
        this();
        this.lineLeft = src.lineLeft;
        this.lineTop = src.lineTop;
        this.lineRight = src.lineRight;
        this.lineBottom = src.lineBottom;

        this.leftTop = src.leftTop;
        this.leftBottom = src.leftBottom;
        this.rightTop = src.rightTop;
        this.rightBottom = src.rightBottom;

        updateCornerPoints();
    }

    @Override
    public float left() {
        return Math.min(leftTop.x, leftBottom.x) + paddingLeft;
    }

    @Override
    public float top() {
        return Math.min(leftTop.y, rightTop.y) + paddingTop;
    }

    @Override
    public float right() {
        return Math.max(rightTop.x, rightBottom.x) - paddingRight;
    }

    @Override
    public float bottom() {
        return Math.max(leftBottom.y, rightBottom.y) - paddingBottom;
    }

    @Override
    public float centerX() {
        return (left() + right()) / 2;
    }

    @Override
    public float centerY() {
        return (top() + bottom()) / 2;
    }

    @Override
    public float width() {
        return right() - left();
    }

    @Override
    public float height() {
        return bottom() - top();
    }

    @Override
    public PointF getCenterPoint() {
        return new PointF(centerX(), centerY());
    }

    public Path getAreaPath() {
        areaPath.reset();

        FormLine topLine = PaddingUtils.formLine(leftTop, rightTop, paddingTop);
        FormLine rightLine = PaddingUtils.formLine(rightTop, rightBottom, paddingRight);
        FormLine bottomLine = PaddingUtils.formLine(leftBottom, rightBottom, paddingBottom);
        FormLine leftLine = PaddingUtils.formLine(leftTop, leftBottom, paddingLeft);

        Vector translateTopLine = topLine.normalVector;
        Vector translateRightLine = rightLine.normalVector;
        Vector translateBottomLine = bottomLine.normalVector;
        Vector translateLeftLine = leftLine.normalVector;

        if (translateTopLine.y < 0) {
            translateTopLine = new Vector(translateTopLine.x, -1 * translateTopLine.y);
        }
        if (translateRightLine.x > 0) {
            translateRightLine = new Vector(-1 * translateRightLine.x, translateRightLine.y);
        }
        if (translateBottomLine.y > 0) {
            translateBottomLine = new Vector(translateBottomLine.x, -1 * translateBottomLine.y);
        }
        if (translateLeftLine.x < 0) {
            translateLeftLine = new Vector(-1 * translateLeftLine.x, translateLeftLine.y);
        }
        if (paddingTop > 0) {
            topLine.translateBy(translateTopLine);
        }
        if (paddingRight > 0) {
            rightLine.translateBy(translateRightLine);
        }
        if (paddingBottom > 0) {
            bottomLine.translateBy(translateBottomLine);
        }
        if (paddingLeft > 0) {
            leftLine.translateBy(translateLeftLine);
        }

        PointD newLeftTop = PaddingUtils.getCrossPoint(leftLine, topLine);
        PointD newRightTop = PaddingUtils.getCrossPoint(topLine, rightLine);
        PointD newRightBottom = PaddingUtils.getCrossPoint(rightLine, bottomLine);
        PointD newLeftBottom = PaddingUtils.getCrossPoint(leftLine, bottomLine);

        PointF newLeftTopF = new PointF((float) newLeftTop.x, (float) newLeftTop.y);
        PointF newRightTopF = new PointF((float) newRightTop.x, (float) newRightTop.y);
        PointF newRightBottomF = new PointF((float) newRightBottom.x, (float) newRightBottom.y);
        PointF newLeftBottomF = new PointF((float) newLeftBottom.x, (float) newLeftBottom.y);

        if (radian > 0) {
//      float tempRatio = radian / distance(leftTop, leftBottom);
//      getPoint(tempPoint, leftTop, leftBottom, Line.Direction.VERTICAL, tempRatio);
//      tempPoint.offset(paddingLeft, paddingTop);
//      areaPath.moveTo(tempPoint.x, tempPoint.y);
//
//      tempRatio = radian / distance(leftTop, rightTop);
//      getPoint(tempPoint, leftTop, rightTop, Line.Direction.HORIZONTAL, tempRatio);
//      tempPoint.offset(paddingLeft, paddingTop);
//      areaPath.quadTo(leftTop.x + paddingLeft, leftTop.y + paddingTop, tempPoint.x, tempPoint.y);
//
//      tempRatio = 1 - tempRatio;
//      getPoint(tempPoint, leftTop, rightTop, Line.Direction.HORIZONTAL, tempRatio);
//      tempPoint.offset(-paddingRight, paddingTop);
//      areaPath.lineTo(tempPoint.x, tempPoint.y);
//
//      tempRatio = radian / distance(rightTop, rightBottom);
//      getPoint(tempPoint, rightTop, rightBottom, Line.Direction.VERTICAL, tempRatio);
//      tempPoint.offset(-paddingRight, paddingTop);
//      areaPath.quadTo(rightTop.x - paddingLeft, rightTop.y + paddingTop, tempPoint.x, tempPoint.y);
//
//      tempRatio = 1 - tempRatio;
//      getPoint(tempPoint, rightTop, rightBottom, Line.Direction.VERTICAL, tempRatio);
//      tempPoint.offset(-paddingRight, -paddingBottom);
//      areaPath.lineTo(tempPoint.x, tempPoint.y);
//
//      tempRatio = 1 - radian / distance(leftBottom, rightBottom);
//      getPoint(tempPoint, leftBottom, rightBottom, Line.Direction.HORIZONTAL, tempRatio);
//      tempPoint.offset(-paddingRight, -paddingBottom);
//      areaPath.quadTo(rightBottom.x - paddingRight, rightBottom.y - paddingTop, tempPoint.x, tempPoint.y);
//
//      tempRatio = 1 - tempRatio;
//      getPoint(tempPoint, leftBottom, rightBottom, Line.Direction.HORIZONTAL, tempRatio);
//      tempPoint.offset(paddingLeft, -paddingBottom);
//      areaPath.lineTo(tempPoint.x, tempPoint.y);
//
//      tempRatio = 1 - radian / distance(leftTop, leftBottom);
//      getPoint(tempPoint, leftTop, leftBottom, Line.Direction.VERTICAL, tempRatio);
//      tempPoint.offset(paddingLeft, -paddingBottom);
//      areaPath.quadTo(leftBottom.x + paddingLeft, leftBottom.y - paddingBottom, tempPoint.x, tempPoint.y);
//
//      tempRatio = 1 - tempRatio;
//      getPoint(tempPoint, leftTop, leftBottom, Line.Direction.VERTICAL, tempRatio);
//      tempPoint.offset(paddingLeft, paddingTop);
//      areaPath.lineTo(tempPoint.x, tempPoint.y);

            float tempRatio = radian / distance(newLeftTopF, newLeftBottomF);
            getPoint(tempPoint, newLeftTopF, newLeftBottomF, Line.Direction.VERTICAL, tempRatio);
            //tempPoint.offset(paddingLeft, paddingTop);
            areaPath.moveTo(tempPoint.x, tempPoint.y);

            tempRatio = radian / distance(newLeftTopF, newRightTopF);
            getPoint(tempPoint, newLeftTopF, newRightTopF, Line.Direction.HORIZONTAL, tempRatio);
            //tempPoint.offset(paddingLeft, paddingTop);
            areaPath.quadTo(newLeftTopF.x, newLeftTopF.y, tempPoint.x, tempPoint.y);

            tempRatio = 1 - tempRatio;
            getPoint(tempPoint, newLeftTopF, newRightTopF, Line.Direction.HORIZONTAL, tempRatio);
            //tempPoint.offset(-paddingRight, paddingTop);
            areaPath.lineTo(tempPoint.x, tempPoint.y);

            tempRatio = radian / distance(newRightTopF, newRightBottomF);
            getPoint(tempPoint, newRightTopF, newRightBottomF, Line.Direction.VERTICAL, tempRatio);
            //tempPoint.offset(-paddingRight, paddingTop);
            areaPath.quadTo(newRightTopF.x, newRightTopF.y, tempPoint.x, tempPoint.y);

            tempRatio = 1 - tempRatio;
            getPoint(tempPoint, newRightTopF, newRightBottomF, Line.Direction.VERTICAL, tempRatio);
            //tempPoint.offset(-paddingRight, -paddingBottom);
            areaPath.lineTo(tempPoint.x, tempPoint.y);

            tempRatio = 1 - radian / distance(newLeftBottomF, newRightBottomF);
            getPoint(tempPoint, newLeftBottomF, newRightBottomF, Line.Direction.HORIZONTAL, tempRatio);
            //tempPoint.offset(-paddingRight, -paddingBottom);
            areaPath.quadTo(newRightBottomF.x, newRightBottomF.y, tempPoint.x, tempPoint.y);

            tempRatio = 1 - tempRatio;
            getPoint(tempPoint, newLeftBottomF, newRightBottomF, Line.Direction.HORIZONTAL, tempRatio);
            //tempPoint.offset(paddingLeft, -paddingBottom);
            areaPath.lineTo(tempPoint.x, tempPoint.y);

            tempRatio = 1 - radian / distance(newLeftTopF, newLeftBottomF);
            getPoint(tempPoint, newLeftTopF, newLeftBottomF, Line.Direction.VERTICAL, tempRatio);
            //tempPoint.offset(paddingLeft, -paddingBottom);
            areaPath.quadTo(newLeftBottomF.x, newLeftBottomF.y, tempPoint.x, tempPoint.y);

            tempRatio = 1 - tempRatio;
            getPoint(tempPoint, newLeftTopF, newLeftBottomF, Line.Direction.VERTICAL, tempRatio);
            //tempPoint.offset(paddingLeft, paddingTop);
            areaPath.lineTo(tempPoint.x, tempPoint.y);
        } else {
            areaPath.moveTo(newLeftTopF.x, newLeftTopF.y);
            areaPath.lineTo(newRightTopF.x, newRightTopF.y);
            areaPath.lineTo(newRightBottomF.x, newRightBottomF.y);
            areaPath.lineTo(newLeftBottomF.x, newLeftBottomF.y);
            areaPath.lineTo(newLeftTopF.x, newLeftTopF.y);
        }
        return areaPath;
    }

    @Override
    public RectF getAreaRect() {
        areaRect.set(left(), top(), right(), bottom());
        return areaRect;
    }

    public boolean contains(float x, float y) {
        return SlantUtils.contains(this, x, y);
    }

    @Override
    public boolean contains(Line line) {
        return lineLeft == line || lineTop == line || lineRight == line || lineBottom == line;
    }

    @Override
    public boolean contains(PointF point) {
        return contains(point.x, point.y);
    }

    @Override
    public List<Line> getLines() {
        return Arrays.asList((Line) lineLeft, lineTop, lineRight, lineBottom);
    }

    @Override
    public PointF[] getHandleBarPoints(Line line) {
        if (line == lineLeft) {
            getPoint(handleBarPoints[0], leftTop, leftBottom, line.direction(), 0.25f);
            getPoint(handleBarPoints[1], leftTop, leftBottom, line.direction(), 0.75f);
            handleBarPoints[0].offset(paddingLeft, 0);
            handleBarPoints[1].offset(paddingLeft, 0);
        } else if (line == lineTop) {
            getPoint(handleBarPoints[0], leftTop, rightTop, line.direction(), 0.25f);
            getPoint(handleBarPoints[1], leftTop, rightTop, line.direction(), 0.75f);
            handleBarPoints[0].offset(0, paddingTop);
            handleBarPoints[1].offset(0, paddingTop);
        } else if (line == lineRight) {
            getPoint(handleBarPoints[0], rightTop, rightBottom, line.direction(), 0.25f);
            getPoint(handleBarPoints[1], rightTop, rightBottom, line.direction(), 0.75f);
            handleBarPoints[0].offset(-paddingRight, 0);
            handleBarPoints[1].offset(-paddingRight, 0);
        } else if (line == lineBottom) {
            getPoint(handleBarPoints[0], leftBottom, rightBottom, line.direction(), 0.25f);
            getPoint(handleBarPoints[1], leftBottom, rightBottom, line.direction(), 0.75f);
            handleBarPoints[0].offset(0, -paddingBottom);
            handleBarPoints[1].offset(0, -paddingBottom);
        }
        return handleBarPoints;
    }

    @Override
    public float radian() {
        return radian;
    }

    @Override
    public void setRadian(float radian) {
        this.radian = radian;
    }

    @Override
    public float getPaddingLeft() {
        return paddingLeft;
    }

    @Override
    public float getPaddingTop() {
        return paddingTop;
    }

    @Override
    public float getPaddingRight() {
        return paddingRight;
    }

    @Override
    public float getPaddingBottom() {
        return paddingBottom;
    }

    @Override
    public void setPadding(float padding) {
        setPadding(padding, padding, padding, padding);
    }

    @Override
    public void setPadding(float paddingLeft, float paddingTop, float paddingRight, float paddingBottom) {
        this.paddingLeft = paddingLeft;
        this.paddingTop = paddingTop;
        this.paddingRight = paddingRight;
        this.paddingBottom = paddingBottom;
    }

    void updateCornerPoints() {
        intersectionOfLines(leftTop, lineLeft, lineTop);
        intersectionOfLines(leftBottom, lineLeft, lineBottom);
        intersectionOfLines(rightTop, lineRight, lineTop);
        intersectionOfLines(rightBottom, lineRight, lineBottom);
    }

    static class AreaComparator implements Comparator<SlantArea> {

        @Override
        public int compare(SlantArea one, SlantArea two) {
            if (one.leftTop.y < two.leftTop.y) {
                return -1;
            } else if (one.leftTop.y == two.leftTop.y) {
                if (one.leftTop.x < two.leftTop.x) {
                    return -1;
                } else if (one.leftTop.x == two.leftTop.x) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }
    }
}
