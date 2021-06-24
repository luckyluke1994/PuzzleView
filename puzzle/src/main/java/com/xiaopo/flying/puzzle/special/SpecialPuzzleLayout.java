package com.xiaopo.flying.puzzle.special;

import android.graphics.PointF;
import android.graphics.RectF;

import com.xiaopo.flying.puzzle.Area;
import com.xiaopo.flying.puzzle.Line;
import com.xiaopo.flying.puzzle.PuzzleLayout;
import com.xiaopo.flying.puzzle.special.model.LibCollageInfo;
import com.xiaopo.flying.puzzle.special.model.TemplateRes;

import java.util.ArrayList;
import java.util.List;

public abstract class SpecialPuzzleLayout implements PuzzleLayout {
    private RectF bounds;
    private TemplateRes mTemplateRes;
    private List<SpecialArea> areas = new ArrayList<>();

    public SpecialPuzzleLayout() {

    }

    @Override
    public void setOuterBounds(RectF bounds) {
        reset();

        this.bounds = bounds;

        PointF one = new PointF(bounds.left, bounds.top);
        PointF two = new PointF(bounds.right, bounds.top);
        PointF three = new PointF(bounds.left, bounds.bottom);
        PointF four = new PointF(bounds.right, bounds.bottom);
    }

    @Override
    public int getAreaCount() {
        return areas.size();
    }

    @Override
    public List<Line> getOuterLines() {
        return new ArrayList<>();
    }

    @Override
    public List<Line> getLines() {
        return new ArrayList<>();
    }

    @Override
    public Area getOuterArea() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public void reset() {

    }

    @Override
    public Area getArea(int position) {
        return areas.get(position);
    }

    @Override
    public float width() {
        return 0;
    }

    @Override
    public float height() {
        return 0;
    }

    @Override
    public void setPadding(float padding) {

    }

    @Override
    public float getPadding() {
        return 0;
    }

    @Override
    public float getRadian() {
        return 0;
    }

    @Override
    public void setRadian(float radian) {

    }

    @Override
    public Info generateInfo() {
        return null;
    }

    @Override
    public void setColor(int color) {

    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public void sortAreas() {

    }

    protected void setTemplateRes(TemplateRes templateRes) {
        mTemplateRes = templateRes;
        addArea();
    }

    private void addArea() {
        if (mTemplateRes == null || bounds == null) return;
        List<LibCollageInfo> listArea = mTemplateRes.getCollageInfo();
        if (listArea == null || listArea.isEmpty()) return;
        int parentWidth = (int) bounds.width();
        int parentHeight = (int) bounds.height();
        areas.clear();
        for (int index = 0; index < listArea.size(); index++) {
            LibCollageInfo libCollageInfo = listArea.get(index);
            SpecialArea area = new SpecialArea(parentWidth, parentHeight);
            area.setLibCollageInfo(libCollageInfo);
            areas.add(area);
        }
    }
}
