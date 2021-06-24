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
        Template2.init(mTemplateResList, mContext, 0, 0, 0, 0, 2);
    }

    @Override
    public int getThemeCount() {
        List<TemplateRes> templateResList = new ArrayList<>();
        Template2.init(templateResList, mContext, 0, 0, 0, 0, 2);
        return templateResList.size();
    }

    @Override
    public void layout() {
        switch (theme) {
            case 0: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 1: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 2: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 3: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 4: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 5: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 6: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 7: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 8: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 9: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 10: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 11: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }

            case 12: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            case 13: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
            default: {
                setTemplateRes(mTemplateResList.get(4));
                break;
            }
        }
    }
}
