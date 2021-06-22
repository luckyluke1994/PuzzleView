package com.xiaopo.flying.puzzle.transform;

import android.graphics.PointF;

public class PaddingUtils {
    public static FormLine formLine(PointF A, PointF B, float lengthOfNormalVector) {
        double k = Math.sqrt(lengthOfNormalVector * lengthOfNormalVector / ((A.y - B.y) * (A.y - B.y) + (B.x - A.x) * (B.x - A.x)));
        if (k == 0) {
            k = 1;
        }
        Vector normalVector = new Vector();
        normalVector.x = k * (A.y - B.y);
        normalVector.y = k * (B.x - A.x);
        return new FormLine(normalVector, new PointD(A.x, A.y));
    }

    public static PointD getCrossPoint(FormLine a, FormLine b) {
        /**
         * n(u;v) A(a;b)
         * n'(p;q) A'(a';b')
         * x = [q (u.a + v.b) - v (p.a' + q.b')] / (u.q - p.v)
         * y = [p (u.a + v.b) - u (p.a' + q.b')] / (v.p - u.q)
         */
        double x = (b.normalVector.y * (a.normalVector.x * a.point.x + a.normalVector.y * a.point.y) - a.normalVector.y * (b.normalVector.x * b.point.x + b.normalVector.y * b.point.y)) / (a.normalVector.x * b.normalVector.y - b.normalVector.x * a.normalVector.y);
        double y = (b.normalVector.x * (a.normalVector.x * a.point.x + a.normalVector.y * a.point.y) - a.normalVector.x * (b.normalVector.x * b.point.x + b.normalVector.y * b.point.y)) / (a.normalVector.y * b.normalVector.x - a.normalVector.x * b.normalVector.y);
        return new PointD(x, y);
    }
}
