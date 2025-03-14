package dev.trindadedev.blockode.ui.activities.editor.palette;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import dev.trindadedev.blockode.R;
import dev.trindadedev.blockode.base.Contextualizable;
import dev.trindadedev.blockode.ui.activities.editor.components.ViewLogicEditor;
import dev.trindadedev.blockode.utils.LayoutUtil;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PaletteAnimator extends Contextualizable {
  private ObjectAnimator paletteOpenAnimator;
  private ObjectAnimator paletteCloseAnimator;
  private ObjectAnimator blockPaneOpenAnimator;
  private ObjectAnimator blockPaneCloseAnimator;
  public FloatingActionButton fabTogglePalette;
  public ViewLogicEditor editor;
  public LinearLayout layoutPalette;
  public LinearLayout areaPalette;
  public boolean isPaletteOpen = false;
  public boolean adjustLayout4Runned = false;

  public PaletteAnimator(final Context context) {
    super(context);
  }

  /**
   * Shows or hides the palette according to the current state.
   *
   * @param isPaletteOpen Defines whether the palette should be shown (true) or hidden (false).
   */
  public final void showHidePalette(final boolean isPaletteOpen) {
    if (this.isPaletteOpen == isPaletteOpen) {
      return;
    }
    this.isPaletteOpen = isPaletteOpen;

    if (isPaletteOpen) {
      adjustLayout5(false);
    }

    var animator = isPaletteOpen ? paletteOpenAnimator : paletteCloseAnimator;
    animator.start();
    adjustLayout(context.getResources().getConfiguration().orientation);
  }

  /**
   * Adjusts the layout of the view based on the screen orientation and whether the palette is open.
   * Depending on the orientation (portrait or landscape), the layout width and height are
   * dynamically adjusted. If the palette is open, the width and height are calculated considering
   * the screen dimensions and additional spaces such as the status bar. If the palette is not open,
   * the layout will be set to fill the entire screen.
   *
   * @param orientation The current screen orientation (e.g., 1 for portrait, 2 for landscape).
   */
  public void adjustLayout(final int orientation) {
    LinearLayout.LayoutParams layoutParams;
    int layoutHeight;
    int layoutWidth = ViewGroup.LayoutParams.MATCH_PARENT;

    if (isPaletteOpen) {
      int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
      int screenHeight = context.getResources().getDisplayMetrics().heightPixels;

      if (screenWidth <= screenHeight) {
        screenWidth = screenHeight;
      }

      if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
        layoutWidth = screenWidth - ((int) LayoutUtil.getDip(context, 320.0f));
        layoutHeight = ViewGroup.LayoutParams.MATCH_PARENT;
      } else {
        layoutHeight =
            (screenWidth
                - ((int) LayoutUtil.getDip(context, 48.0f))
                - LayoutUtil.getStatusBarHeight(context)
                - ((int) LayoutUtil.getDip(context, 240.0f)));
      }

      layoutParams = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);
    } else {
      layoutParams =
          new LinearLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
    editor.setLayoutParams(layoutParams);
    editor.requestLayout();
  }

  public void adjustLayout2(final int orientation) {
    RelativeLayout.LayoutParams layoutParams;
    int layoutOrientation;
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      areaPalette.setLayoutParams(
          new LinearLayout.LayoutParams(
              (int) LayoutUtil.getDip(context, 320.0f), ViewGroup.LayoutParams.MATCH_PARENT));
      var params =
          new LinearLayout.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      params.gravity = Gravity.CENTER | Gravity.BOTTOM;
      var dimension = (int) context.getResources().getDimension(R.dimen.action_button_margin);
      params.setMargins(dimension, dimension, dimension, dimension);
      fabTogglePalette.setLayoutParams(params);
      layoutParams =
          new RelativeLayout.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
      layoutParams.topMargin = LayoutUtil.getStatusBarHeight(context);
      layoutOrientation = LinearLayout.HORIZONTAL;
    } else {
      areaPalette.setLayoutParams(
          new LinearLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, (int) LayoutUtil.getDip(context, 240.0f)));
      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(
              ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      params.gravity = Gravity.CENTER | Gravity.RIGHT;
      var dimension = (int) context.getResources().getDimension(R.dimen.action_button_margin);
      params.setMargins(dimension, dimension, dimension, dimension);
      fabTogglePalette.setLayoutParams(params);
      layoutParams =
          new RelativeLayout.LayoutParams(
              ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
      layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
      layoutOrientation = LinearLayout.VERTICAL;
    }
    layoutPalette.setOrientation(layoutOrientation);
    layoutPalette.setLayoutParams(layoutParams);
    adjustLayout3(orientation);
    adjustLayout(orientation);
  }

  public void adjustLayout3(int orientation) {
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      if (!isPaletteOpen) {
        layoutPalette.setTranslationX(LayoutUtil.getDip(context, 320.0F));
      } else {
        layoutPalette.setTranslationX(0.0F);
      }
      layoutPalette.setTranslationY(0.0F);
    } else {
      if (!isPaletteOpen) {
        layoutPalette.setTranslationX(0.0F);
        layoutPalette.setTranslationY(LayoutUtil.getDip(context, 240.0F));
      } else {
        layoutPalette.setTranslationX(0.0F);
        layoutPalette.setTranslationY(0.0F);
      }
    }

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      paletteOpenAnimator = ObjectAnimator.ofFloat(layoutPalette, View.TRANSLATION_X, 0.0F);
      paletteCloseAnimator =
          ObjectAnimator.ofFloat(
              layoutPalette, View.TRANSLATION_X, LayoutUtil.getDip(context, 320.0F));
    } else {
      paletteOpenAnimator = ObjectAnimator.ofFloat(layoutPalette, View.TRANSLATION_Y, 0.0F);
      paletteCloseAnimator =
          ObjectAnimator.ofFloat(
              layoutPalette, View.TRANSLATION_Y, LayoutUtil.getDip(context, 240.0F));
    }

    paletteOpenAnimator.setDuration(500L);
    paletteOpenAnimator.setInterpolator(new DecelerateInterpolator());
    paletteCloseAnimator.setDuration(300L);
    paletteCloseAnimator.setInterpolator(new DecelerateInterpolator());
  }

  public void adjustLayout4() {
    blockPaneOpenAnimator = ObjectAnimator.ofFloat(editor.getBlockPane(), View.TRANSLATION_X, 0.0f);
    blockPaneOpenAnimator.setDuration(500L);
    blockPaneOpenAnimator.setInterpolator(new DecelerateInterpolator());
    blockPaneCloseAnimator =
        ObjectAnimator.ofFloat(
            editor.getBlockPane(), View.TRANSLATION_X, editor.getBlockPane().getHeight());
    blockPaneCloseAnimator.setDuration(300L);
    blockPaneCloseAnimator.setInterpolator(new DecelerateInterpolator());
    adjustLayout4Runned = true;
  }

  public void adjustLayout5(boolean isPaletteOpen) {
    if (!adjustLayout4Runned) {
      adjustLayout4();
    }
    if (isPaletteOpen != isPaletteOpen) {
      adjustLayout6();
      (isPaletteOpen ? blockPaneOpenAnimator : blockPaneCloseAnimator).start();
    }
  }

  public void adjustLayout6() {
    if (blockPaneOpenAnimator.isRunning()) {
      blockPaneOpenAnimator.cancel();
    }
    if (blockPaneCloseAnimator.isRunning()) {
      blockPaneCloseAnimator.cancel();
    }
  }

  /**
   * Sets animators based on screen orientation.
   *
   * @param orientation Current screen orientation (portrait or landscape).
   */
  public final void configureAnimators(int orientation) {
    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      paletteOpenAnimator = ObjectAnimator.ofFloat(layoutPalette, View.TRANSLATION_X, 0f);
      paletteCloseAnimator =
          ObjectAnimator.ofFloat(
              layoutPalette, View.TRANSLATION_X, LayoutUtil.getDip(context, 320f));
    } else {
      paletteOpenAnimator = ObjectAnimator.ofFloat(layoutPalette, View.TRANSLATION_Y, 0f);
      paletteCloseAnimator =
          ObjectAnimator.ofFloat(
              layoutPalette, View.TRANSLATION_Y, LayoutUtil.getDip(context, 240f));
    }

    paletteOpenAnimator.setDuration(500L);
    paletteOpenAnimator.setInterpolator(new DecelerateInterpolator());
    paletteCloseAnimator.setDuration(300L);
    paletteCloseAnimator.setInterpolator(new DecelerateInterpolator());
  }

  /**
   * Updates the palette's starting position based on the screen orientation.
   *
   * @param orientation Current screen orientation.
   */
  public final void updatePalettePosition(int orientation) {
    float translationX = 0f;
    float translationY = 0f;

    if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
      translationX = isPaletteOpen ? 0f : LayoutUtil.getDip(context, 320f);
    } else {
      translationY = isPaletteOpen ? 0f : LayoutUtil.getDip(context, 240f);
    }

    layoutPalette.setTranslationX(translationX);
    layoutPalette.setTranslationY(translationY);
  }
}
