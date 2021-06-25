package com.xiaopo.flying.puzzle.layouts.special;

import android.content.Context;

import com.xiaopo.flying.puzzle.special.model.TemplateRes;
import com.xiaopo.flying.puzzle.special.template.Template4;

import java.util.ArrayList;
import java.util.List;

public class FourSpecialLayout extends NumberSpecialLayout {
    private Context mContext;
    private List<TemplateRes> mTemplateResList;

    public FourSpecialLayout(Context context, int theme) {
        super(theme);
        mContext = context;
    }

    @Override
    protected void initThemes() {
        mTemplateResList = new ArrayList<>();
        Template4.init(mTemplateResList, mContext, 0, 0, 0, 0, 4);
    }

    @Override
    public int getThemeCount() {
        return mTemplateResList.size();
    }

    @Override
    public void layout() {
        setTemplateRes(mTemplateResList.get(theme));
    }
}
