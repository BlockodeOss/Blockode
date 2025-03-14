package dev.trindadedev.blockode.utils;

/**
 * Originally Decompiled from Sketchware 1.1.13
 * Refactored by Aquiles Trindade (trindadedev)
 */

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LayoutUtil {
  public static View inflate(Context context, int id) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    return inflater.inflate(id, (ViewGroup) null);
  }

  public static View inflate(Context context, ViewGroup root, int id) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    return inflater.inflate(id, root, true);
  }

  public static View inflate(Context context, ViewGroup root, int id, boolean attach) {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService("layout_inflater");
    return inflater.inflate(id, root, attach);
  }

  public static float getDip(Context context, float value) {
    return TypedValue.applyDimension(1, value, context.getResources().getDisplayMetrics());
  }

  public static int getStatusBarHeight(Context context) {
    int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    return identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
  }
}
