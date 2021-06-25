package com.xiaopo.flying.puzzle.layouts.special;

import android.content.Context;

import com.xiaopo.flying.puzzle.special.model.TemplateRes;
import com.xiaopo.flying.puzzle.special.template.Template2;

import java.util.ArrayList;
import java.util.List;

public class TwoSpecialLayout extends NumberSpecialLayout {
    private Context mContext;
    private List<TemplateRes> mTemplateResList;

    public TwoSpecialLayout(Context context, int theme) {
        super(theme);
        mContext = context;
        mTemplateResList = new ArrayList<>();
        mTemplateResList = new ArrayList<>();
        Template2.init(mTemplateResList, mContext, 10, 0, 0, 0, 2);
    }

    @Override
    public int getThemeCount() {
        List<TemplateRes> templateResList = new ArrayList<>();
        Template2.init(templateResList, mContext, 0, 0, 0, 0, 2);
        return templateResList.size();
    }

    @Override
    public void layout() {
        setTemplateRes(mTemplateResList.get(theme));
    }
}
