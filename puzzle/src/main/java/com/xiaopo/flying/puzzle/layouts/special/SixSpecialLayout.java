package com.xiaopo.flying.puzzle.layouts.special;

import com.xiaopo.flying.puzzle.special.model.TemplateRes;
import com.xiaopo.flying.puzzle.special.template.Template6;

import java.util.ArrayList;
import java.util.List;

public class SixSpecialLayout extends NumberSpecialLayout {
    private List<TemplateRes> mTemplateResList;

    public SixSpecialLayout(int theme) {
        super(theme);
    }

    @Override
    public int getThemeCount() {
        mTemplateResList = new ArrayList<>();
        Template6.init(mTemplateResList, 0, 0, 0, 0, 6);
        return mTemplateResList.size();
    }

    @Override
    public void layout() {
        setTemplateRes(mTemplateResList.get(theme));
    }
}
