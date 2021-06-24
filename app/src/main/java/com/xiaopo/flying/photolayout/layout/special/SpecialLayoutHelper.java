package com.xiaopo.flying.photolayout.layout.special;

import android.content.Context;

import com.xiaopo.flying.photolayout.layout.slant.OneSlantLayout;
import com.xiaopo.flying.photolayout.layout.slant.ThreeSlantLayout;
import com.xiaopo.flying.photolayout.layout.slant.TwoSlantLayout;
import com.xiaopo.flying.puzzle.PuzzleLayout;
import com.xiaopo.flying.puzzle.layouts.special.TwoSpecialLayout;

import java.util.ArrayList;
import java.util.List;

public class SpecialLayoutHelper {
    public static List<PuzzleLayout> getAllThemeLayout(Context context, int pieceCount) {
        List<PuzzleLayout> puzzleLayouts = new ArrayList<>();
        switch (pieceCount) {
            case 1:
                for (int i = 0; i < 4; i++) {
                    //puzzleLayouts.add(new OneSlantLayout(i));
                }
                break;
            case 2:
                for (int i = 0; i < 13; i++) {
                    puzzleLayouts.add(new TwoSpecialLayout(context, i));
                }
                break;
            case 3:
                for (int i = 0; i < 6; i++) {
                    //puzzleLayouts.add(new ThreeSlantLayout(i));
                }
                break;
            //case 4:
            //  for (int i = 0; i < 8; i++) {
            //    puzzleLayouts.add(new FourStraightLayout(i));
            //  }
            //  break;
            //case 5:
            //  for (int i = 0; i < 17; i++) {
            //    puzzleLayouts.add(new FiveStraightLayout(i));
            //  }
            //  break;
            //case 6:
            //  for (int i = 0; i < 12; i++) {
            //    puzzleLayouts.add(new SixStraightLayout(i));
            //  }
            //  break;
            //case 7:
            //  for (int i = 0; i < 9; i++) {
            //    puzzleLayouts.add(new SevenStraightLayout(i));
            //  }
            //  break;
            //case 8:
            //  for (int i = 0; i < 11; i++) {
            //    puzzleLayouts.add(new EightStraightLayout(i));
            //  }
            //  break;
            //case 9:
            //  for (int i = 0; i < 8; i++) {
            //    puzzleLayouts.add(new NineStraightLayout(i));
            //  }
            //  break;
        }

        return puzzleLayouts;
    }
}
