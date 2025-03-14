package dev.trindadedev.neobrutalism.base;

import android.widget.TextView;
import androidx.annotation.NonNull;

/** Used in views that contain text. */
public interface IText {
  @NonNull
  TextView asTextView();
}
