package dev.trindadedev.blockode.ui.components.editor.block.palette;

/** Decompiled from Sketchware 1.1.13 */
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.material.card.MaterialCardView;
import dev.trindadedev.blockode.databinding.LayoutPaletteBlockBinding;
import dev.trindadedev.blockode.ui.components.editor.block.Block;
import dev.trindadedev.blockode.ui.components.editor.block.BlockBase;
import dev.trindadedev.blockode.utils.LayoutUtil;

public class PaletteBlock extends LinearLayout {

  private LayoutPaletteBlockBinding binding;

  private int dip = 0;

  public PaletteBlock(Context context) {
    super(context);
    init(context);
  }

  public PaletteBlock(Context context, AttributeSet attributeSet) {
    super(context, attributeSet);
    init(context);
  }

  private void init(Context context) {
    binding = LayoutPaletteBlockBinding.inflate(LayoutInflater.from(context), this, true);
    this.dip = (int) LayoutUtil.getDip(getContext(), 1.0f);
  }

  public BlockBase addBlock(
      final String str, final String type, final String opCode, final int color, Object... objArr) {
    final var view = new View(getContext());
    view.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (8.0f * this.dip)));
    binding.blockBuilder.addView(view);
    final var block =
        new Block(getContext(), -1, str, type, opCode, Integer.valueOf(color), objArr);
    block.setBlockType(1);
    binding.blockBuilder.addView(block);
    return block;
  }

  public MaterialCardView addButton(final String title) {
    final var textView = new TextView(getContext());
    textView.setText(title);
    textView.setTextSize(10.0F);
    textView.setGravity(Gravity.CENTER);
    textView.setPadding((int) (dip * 8.0F), 0, (int) (dip * 8.0F), 0);

    final var cardView = new MaterialCardView(getContext());
    final var params = getLayoutParams(30);
    params.setMargins(0, 0, 3, 3);
    cardView.setLayoutParams(params);
    cardView.setCardElevation(0f);
    cardView.setRadius(12.0f);
    cardView.setStrokeWidth(0);
    cardView.addView(textView);
    binding.actionsContainer.addView(cardView);
    return cardView;
  }

  public PaletteSelector getPaletteSelector() {
    return binding.paletteSelector;
  }

  private LinearLayout.LayoutParams getLayoutParams(final int heightMultiplier) {
    return new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT, (dip * heightMultiplier));
  }

  public void removeAllBlocks() {
    binding.blockBuilder.removeAllViews();
    binding.actionsContainer.removeAllViews();
  }

  public void setDragEnabled(final boolean dragEnabled) {
    if (dragEnabled) {
      binding.scroll.setScrollEnabled();
      binding.scrollHorizontal.setScrollEnabled();
      return;
    }
    binding.scroll.setScrollDisabled();
    binding.scrollHorizontal.setScrollDisabled();
  }

  public void setMinWidth(final int minWidth) {
    binding.scroll.setMinimumWidth(minWidth - (dip * 5));
    binding.scrollHorizontal.setMinimumWidth(minWidth - (dip * 5));
    getLayoutParams().width = minWidth;
  }
}
