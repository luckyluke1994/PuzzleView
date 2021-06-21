package com.xiaopo.flying.puzzle.transform;

public class FormLine {
    public Vector normalVector;
    public PointD point;

    public FormLine(Vector normalVector, PointD point) {
        this.normalVector = normalVector;
        this.point = point;
    }

    public void translateBy(Vector t) {
        double x = point.x + t.x;
        double y = point.y + t.y;
        this.point = new PointD(x, y);
    }
}
