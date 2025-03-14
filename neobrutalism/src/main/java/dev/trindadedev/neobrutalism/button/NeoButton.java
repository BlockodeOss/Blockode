package dev.trindadedev.neobrutalism.button;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dev.trindadedev.neobrutalism.R;
import dev.trindadedev.neobrutalism.base.BaseNeoRelativeLayout;
import dev.trindadedev.neobrutalism.base.IText;
import dev.trindadedev.neobrutalism.databinding.NeoButtonBinding;

public class NeoButton extends BaseNeoRelativeLayout implements IText {

  private NeoButtonBinding binding;

  public NeoButton(final Context context) {
    super(context);
  }

  public NeoButton(final Context context, final AttributeSet attrs) {
    super(context, attrs);
  }

  public NeoButton(final Context context, final AttributeSet attrs, final int defStyleRes) {
    super(context, attrs, defStyleRes);
  }

  @Override
  public void init(
      @NonNull final Context context,
      @Nullable final AttributeSet attrs,
      @Nullable final int defStyleRes) {
    binding = NeoButtonBinding.inflate(LayoutInflater.from(context), this, true);
    var attributes = context.obtainStyledAttributes(attrs, R.styleable.NeoButton, 0, 0);
    var text = attributes.getString(R.styleable.NeoButton_android_text);
    setText(text);
  }

  public void setText(final String text) {
    binding.buttonText.setText(text);
  }

  public CharSequence getText() {
    return binding.buttonText.getText();
  }

  /**
   * Returns the textview of the button.
   */
  @Override
  @NonNull
  public TextView asTextView() {
    return binding.buttonText;
  }

  @Override
  @NonNull
  public String getNeoName() {
    return "NeoButton";
  }

  @Override
  @NonNull
  public View getNeoRoot() {
    return binding.getRoot();
  }
}
