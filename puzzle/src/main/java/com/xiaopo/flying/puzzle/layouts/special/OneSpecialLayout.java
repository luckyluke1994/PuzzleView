package com.xiaopo.flying.puzzle.layouts.special;

import com.xiaopo.flying.puzzle.special.model.TemplateRes;
import com.xiaopo.flying.puzzle.special.template.Template1;

import java.util.ArrayList;
import java.util.List;

public class OneSpecialLayout extends NumberSpecialLayout {
    private List<TemplateRes> mTemplateResList;

    public OneSpecialLayout(int theme) {
        super(theme);
    }

    @Override
    public int getThemeCount() {
        mTemplateResList = new ArrayList<>();
        Template1.init(mTemplateResList, null, 0, 0, 0, 0, 1);
        return mTemplateResList.size();
    }

    @Override
    public void layout() {
        setTemplateRes(mTemplateResList.get(theme));
    }
}
