package dev.trindadedev.blockode.ui.components.editor.block.palette;

/**
 * Originally Decompiled from Sketchware 1.1.13
 * Refactored by Aquiles Trindade (trindadedev)
 */

import static dev.trindadedev.blockode.utils.LayoutUtil.getDip;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import dev.trindadedev.blockode.base.Selectable;
import dev.trindadedev.blockode.databinding.LayoutPaletteSelectorItemBinding;
import dev.trindadedev.blockode.utils.ThemeUtil;

public class PaletteSelectorItem extends RelativeLayout implements Selectable {

  private LayoutPaletteSelectorItemBinding binding;

  private int color;
  private int id;
  private boolean selected;
  private String name;

  public PaletteSelectorItem(
      final Context context, final int id, final String name, final int color) {
    super(context);
    this.color = color;
    this.id = id;
    this.name = name;
    binding =
        LayoutPaletteSelectorItemBinding.inflate(LayoutInflater.from(getContext()), this, true);
    binding.label.setText(name);
    binding.color.setBackgroundColor(color);
    setSelected(false);
  }

  @Override
  public void setSelected(final boolean selected) {
    this.selected = selected;
    var colorLp = binding.color.getLayoutParams();
    if (selected) {
      binding.label.setTextColor(Color.WHITE);
      colorLp.width = ViewGroup.LayoutParams.MATCH_PARENT;
    } else {
      binding.label.setTextColor(
          ThemeUtil.getColor(binding.label, com.google.android.material.R.attr.colorOnSurface));
      colorLp.width = (int) getDip(getContext(), 4.0f);
    }
    binding.color.setLayoutParams(colorLp);
  }

  @Override
  @NonNull
  public boolean getSelected() {
    return selected;
  }

  @NonNull
  public int getColor() {
    return color;
  }

  @NonNull
  public int getId() {
    return id;
  }

  @NonNull
  public String getName() {
    return name;
  }
}
