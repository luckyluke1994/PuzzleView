package com.xiaopo.flying.puzzle.special;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.xiaopo.flying.puzzle.Area;
import com.xiaopo.flying.puzzle.PuzzlePiece;

public class SpecialPuzzlePiece extends PuzzlePiece {
    protected static final Xfermode DST_IN = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

    public SpecialPuzzlePiece(Context context, Drawable drawable, Area area, Matrix matrix) {
        super(context, drawable, area, matrix);
    }

    @Override
    public void draw(Canvas canvas, boolean quickMode) {
        Log.i("TAG", "draw: special piece");
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);
//        paint.setColor(Color.GREEN);
//        canvas.drawPath(area.getAreaPath(), paint);
        draw(canvas, 255, true, quickMode);
    }

    @Override
    public void draw(Canvas canvas, int alpha, boolean quickMode) {
        draw(canvas, alpha, false, quickMode);
    }

    private void draw(Canvas canvas, int alpha, boolean needClip, boolean quickMode) {
        if ((drawable instanceof BitmapDrawable) && !quickMode) {
            int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);

            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                Paint paint = ((BitmapDrawable) drawable).getPaint();

                paint.setColor(Color.WHITE);
                paint.setAlpha(alpha);
                if (needClip) {
                    if (area.isDrawByPath()) {
                        canvas.drawPath(area.getAreaPath(), paint);
                        paint.setXfermode(SRC_IN);
                        canvas.drawBitmap(bitmap, matrix, paint);
                    } else {
                        Bitmap maskBitmap = area.getAreaMask(mContext);
                        RectF areaRect = area.getAreaRect();
                        if (maskBitmap != null) {
                            canvas.drawPath(area.getAreaPath(), paint);
                            paint.setXfermode(SRC_IN);
                            canvas.drawBitmap(bitmap, matrix, paint);
                            paint.setXfermode(DST_IN);
                            canvas.drawBitmap(maskBitmap, (int) areaRect.left, (int) areaRect.top, paint);
                        }
                    }
                }

                paint.setXfermode(null);
            }
            canvas.restoreToCount(saved);
        } else {
            canvas.save();
            if (needClip) {
                canvas.clipPath(area.getAreaPath());
            }
            canvas.concat(matrix);
            drawable.setBounds(drawableBounds);
            drawable.setAlpha(alpha);
            drawable.draw(canvas);

            canvas.restore();
        }
    }
}
