package com.xiaopo.flying.photolayout.layout.special;

import android.content.Context;

import com.xiaopo.flying.photolayout.layout.slant.OneSlantLayout;
import com.xiaopo.flying.photolayout.layout.slant.ThreeSlantLayout;
import com.xiaopo.flying.photolayout.layout.slant.TwoSlantLayout;
import com.xiaopo.flying.puzzle.PuzzleLayout;
import com.xiaopo.flying.puzzle.layouts.special.FiveSpecialLayout;
import com.xiaopo.flying.puzzle.layouts.special.FourSpecialLayout;
import com.xiaopo.flying.puzzle.layouts.special.NineSpecialLayout;
import com.xiaopo.flying.puzzle.layouts.special.OneSpecialLayout;
import com.xiaopo.flying.puzzle.layouts.special.SevenSpecialLayout;
import com.xiaopo.flying.puzzle.layouts.special.SixSpecialLayout;
import com.xiaopo.flying.puzzle.layouts.special.ThreeSpecialLayout;
import com.xiaopo.flying.puzzle.layouts.special.TwoSpecialLayout;

import java.util.ArrayList;
import java.util.List;

public class SpecialLayoutHelper {
    private static final int ONE_SPECIAL_LAYOUT_COUNT = 11;
    private static final int TWO_SPECIAL_LAYOUT_COUNT = 13;
    private static final int THREE_SPECIAL_LAYOUT_COUNT = 8;
    private static final int FOUR_SPECIAL_LAYOUT_COUNT = 4;
    private static final int FIVE_SPECIAL_LAYOUT_COUNT = 6;
    private static final int SIX_SPECIAL_LAYOUT_COUNT = 2;
    private static final int SEVEN_SPECIAL_LAYOUT_COUNT = 3;
    private static final int NINE_SPECIAL_LAYOUT_COUNT = 1;
    public static List<PuzzleLayout> getAllThemeLayout(Context context, int pieceCount) {
        List<PuzzleLayout> puzzleLayouts = new ArrayList<>();
        switch (pieceCount) {
            case 1:
                for (int i = 0; i < ONE_SPECIAL_LAYOUT_COUNT; i++) {
                    puzzleLayouts.add(new OneSpecialLayout(i));
                }
                break;
            case 2:
                for (int i = 0; i < TWO_SPECIAL_LAYOUT_COUNT; i++) {
                    puzzleLayouts.add(new TwoSpecialLayout(context, i));
                }
                break;
            case 3:
                for (int i = 0; i < THREE_SPECIAL_LAYOUT_COUNT; i++) {
                    puzzleLayouts.add(new ThreeSpecialLayout(context, i));
                }
                break;
            case 4:
              for (int i = 0; i < FOUR_SPECIAL_LAYOUT_COUNT; i++) {
                puzzleLayouts.add(new FourSpecialLayout(context, i));
              }
              break;
            case 5:
              for (int i = 0; i < FIVE_SPECIAL_LAYOUT_COUNT; i++) {
                puzzleLayouts.add(new FiveSpecialLayout(context, i));
              }
              break;
            case 6:
              for (int i = 0; i < SIX_SPECIAL_LAYOUT_COUNT; i++) {
                puzzleLayouts.add(new SixSpecialLayout(i));
              }
              break;
            case 7:
              for (int i = 0; i < SEVEN_SPECIAL_LAYOUT_COUNT; i++) {
                puzzleLayouts.add(new SevenSpecialLayout(i));
              }
              break;
            case 9:
              for (int i = 0; i < NINE_SPECIAL_LAYOUT_COUNT; i++) {
                puzzleLayouts.add(new NineSpecialLayout(i));
              }
              break;
            //case 9:
            //  for (int i = 0; i < 8; i++) {
            //    puzzleLayouts.add(new NineStraightLayout(i));
            //  }
            //  break;
        }

        return puzzleLayouts;
    }
}
