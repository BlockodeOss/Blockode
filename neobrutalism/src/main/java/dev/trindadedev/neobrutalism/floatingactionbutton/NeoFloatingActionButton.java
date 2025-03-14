package dev.trindadedev.neobrutalism.floatingactionbutton;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dev.trindadedev.neobrutalism.R;
import dev.trindadedev.neobrutalism.base.BaseNeoRelativeLayout;
import dev.trindadedev.neobrutalism.databinding.NeoFloatingActionButtonBinding;

public class NeoFloatingActionButton extends BaseNeoRelativeLayout {

  private NeoFloatingActionButtonBinding binding;

  public NeoFloatingActionButton(final Context context) {
    super(context);
  }

  public NeoFloatingActionButton(final Context context, final AttributeSet attrs) {
    super(context, attrs);
  }

  public NeoFloatingActionButton(
      final Context context, final AttributeSet attrs, final int defStyleRes) {
    super(context, attrs, defStyleRes);
  }

  @Override
  public void init(
      @NonNull final Context context,
      @Nullable final AttributeSet attrs,
      @Nullable final int defStyleRes) {
    binding = NeoFloatingActionButtonBinding.inflate(LayoutInflater.from(getContext()), this, true);

    final var attributes =
        context.obtainStyledAttributes(attrs, R.styleable.NeoFloatingActionButton, 0, 0);
    final var icon = attributes.getDrawable(R.styleable.NeoFloatingActionButton_android_src);
    setIcon(icon);
  }

  public void setIcon(@DrawableRes final int imageResId) {
    binding.fabIcon.setImageResource(imageResId);
  }

  public void setIcon(final Drawable drawable) {
    binding.fabIcon.setImageDrawable(drawable);
  }

  @Override
  public String getNeoName() {
    return "NeoFloatingActionButton";
  }

  @Override
  public View getNeoRoot() {
    return binding.fabRoot;
  }
}
