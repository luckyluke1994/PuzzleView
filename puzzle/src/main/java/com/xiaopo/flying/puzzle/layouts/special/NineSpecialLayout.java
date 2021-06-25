package com.xiaopo.flying.puzzle.layouts.special;

import com.xiaopo.flying.puzzle.special.model.TemplateRes;
import com.xiaopo.flying.puzzle.special.template.Template9;

import java.util.ArrayList;
import java.util.List;

public class NineSpecialLayout extends NumberSpecialLayout {
    private List<TemplateRes> mTemplateResList;

    public NineSpecialLayout(int theme) {
        super(theme);
    }

    @Override
    public int getThemeCount() {
        mTemplateResList = new ArrayList<>();
        Template9.init(mTemplateResList, 0, 0, 0, 0, 9);
        return mTemplateResList.size();
    }

    @Override
    public void layout() {
        setTemplateRes(mTemplateResList.get(theme));
    }
}
