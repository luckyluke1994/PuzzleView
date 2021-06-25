package com.xiaopo.flying.puzzle.layouts.special;

import android.util.Log;

import com.xiaopo.flying.puzzle.special.SpecialPuzzleLayout;

public abstract class NumberSpecialLayout extends SpecialPuzzleLayout {
    static final String TAG = "NumberSpecialLayout";

    protected int theme;

    public NumberSpecialLayout(int theme) {
        initThemes();
        if (theme >= getThemeCount()) {
            Log.e(TAG, "NumberSpecialLayout: the most theme count is "
                    + getThemeCount()
                    + " ,you should let theme from 0 to "
                    + (getThemeCount() - 1)
                    + " .");
        }
        this.theme = theme;
    }

    public abstract int getThemeCount();

    public int getTheme() {
        return theme;
    }

    protected void initThemes() {
    }
}
