package com.xiaopo.flying.puzzle.layouts.special;

import com.xiaopo.flying.puzzle.special.model.TemplateRes;
import com.xiaopo.flying.puzzle.special.template.Template7;

import java.util.ArrayList;
import java.util.List;

public class SevenSpecialLayout extends NumberSpecialLayout {
    private List<TemplateRes> mTemplateResList;

    public SevenSpecialLayout(int theme) {
        super(theme);
    }

    @Override
    public int getThemeCount() {
        mTemplateResList = new ArrayList<>();
        Template7.init(mTemplateResList, 0, 0, 0, 0, 7);
        return mTemplateResList.size();
    }

    @Override
    public void layout() {
        setTemplateRes(mTemplateResList.get(theme));
    }
}
