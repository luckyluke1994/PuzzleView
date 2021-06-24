package com.xiaopo.flying.puzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

import java.util.List;

/**
 * @author wupanjie
 */
public interface Area {
  float left();

  float top();

  float right();

  float bottom();

  float centerX();

  float centerY();

  float width();

  float height();

  PointF getCenterPoint();

  boolean contains(PointF point);

  boolean contains(float x, float y);

  boolean contains(Line line);

  Path getAreaPath();

  RectF getAreaRect();

  List<Line> getLines();

  PointF[] getHandleBarPoints(Line line);

  float radian();

  void setRadian(float radian);

  float getPaddingLeft();

  float getPaddingTop();

  float getPaddingRight();

  float getPaddingBottom();

  void setPadding(float padding);

  void setPadding(float paddingLeft, float paddingTop, float paddingRight, float paddingBottom);

  PuzzlePiece createPuzzlePiece(Context context, Drawable drawable, Matrix matrix);

  boolean isDrawByPath();

  Bitmap getAreaMask(Context context);
}





