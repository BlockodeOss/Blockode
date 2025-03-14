package dev.trindadedev.neobrutalism.floatingactionbutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dev.trindadedev.neobrutalism.R;
import dev.trindadedev.neobrutalism.base.BaseNeoRelativeLayout;
import dev.trindadedev.neobrutalism.base.IText;
import dev.trindadedev.neobrutalism.databinding.NeoExtendedFloatingActionButtonBinding;

public class NeoExtendedFloatingActionButton extends BaseNeoRelativeLayout implements IText {

  private NeoExtendedFloatingActionButtonBinding binding;

  public NeoExtendedFloatingActionButton(final Context context) {
    super(context);
  }

  public NeoExtendedFloatingActionButton(final Context context, final AttributeSet attrs) {
    super(context, attrs);
  }

  public NeoExtendedFloatingActionButton(
      final Context context, final AttributeSet attrs, final int defStyleRes) {
    super(context, attrs, defStyleRes);
  }

  @Override
  public void init(
      @NonNull final Context context,
      @Nullable final AttributeSet attrs,
      @Nullable final int defStyleRes) {
    binding =
        NeoExtendedFloatingActionButtonBinding.inflate(LayoutInflater.from(context), this, true);

    final var attributes =
        context.obtainStyledAttributes(attrs, R.styleable.NeoFloatingActionButton, 0, 0);
    final var attributesExtended =
        context.obtainStyledAttributes(attrs, R.styleable.NeoExtendedFloatingActionButton, 0, 0);
    final var icon = attributes.getDrawable(R.styleable.NeoFloatingActionButton_android_src);
    final var text =
        attributesExtended.getString(R.styleable.NeoExtendedFloatingActionButton_android_text);
    setIcon(icon);
    setText(text);
  }

  public void setIcon(@DrawableRes final int imageResId) {
    binding.fabIcon.setImageResource(imageResId);
  }

  public void setIcon(final Drawable drawable) {
    binding.fabIcon.setImageDrawable(drawable);
  }

  public void setText(final String text) {
    binding.fabText.setText(text);
  }

  @Override
  public void setOnClickListener(OnClickListener onClickListener) {
    binding.fabRoot.setOnClickListener(onClickListener);
  }

  @Override
  @NonNull
  public TextView asTextView() {
    return binding.fabText;
  }

  @Override
  @NonNull
  public String getNeoName() {
    return "NeoExtendedFloatingActionButton";
  }

  @Override
  @NonNull
  public View getNeoRoot() {
    return binding.fabRoot;
  }
}
