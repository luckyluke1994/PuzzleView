package com.xiaopo.flying.puzzle.special.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;

public class CollageBitmapUtils {
    public static Bitmap getImageFromAssetsFile(Resources res, String fileName) {
        Bitmap image = null;
        try {
            InputStream is = res.getAssets().open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return image;
        }
    }

    public static Bitmap getImageFromResourceFile(Resources res, int fileID) {
        Bitmap image = null;
        try {
            InputStream is = res.openRawResource(fileID);
            image = BitmapFactory.decodeStream(is);
            is.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return image;
        }
    }
}
