package dev.trindadedev.blockode.project.manage.variable;

import android.content.Context;
import android.text.Spanned;
import com.google.android.material.textfield.TextInputLayout;
import dev.trindadedev.blockode.R;
import dev.trindadedev.blockode.base.BaseInputValidator;
import dev.trindadedev.blockode.utils.function.SingleListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class VariableNameValidator extends BaseInputValidator {
  private ArrayList<String> alreadyUsed;
  private String exception;
  private String[] keywords;
  Pattern ps = Pattern.compile("^[a-zA-Z][a-zA-Z0-9_]*");
  private String[] usedwords;
  private SingleListener onTextChanged;

  public VariableNameValidator(
      Context context,
      TextInputLayout textInputLayout,
      String[] strArr,
      String[] strArr2,
      ArrayList<String> arrayList) {
    super(context, textInputLayout);
    keywords = strArr;
    usedwords = strArr2;
    alreadyUsed = arrayList;
  }

  public VariableNameValidator(
      Context context,
      TextInputLayout textInputLayout,
      String[] strArr,
      String[] strArr2,
      ArrayList<String> arrayList,
      String str) {
    super(context, textInputLayout);
    keywords = strArr;
    usedwords = strArr2;
    alreadyUsed = arrayList;
    exception = str;
  }

  public CharSequence filter(
      CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
    return null;
  }

  public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    if (charSequence.toString().trim().length() < 1) {
      textInputLayout.setErrorEnabled(true);
      textInputLayout.setError(
          context.getString(R.string.err_valid_min_len, new Object[] {Integer.valueOf(1)}));
      valid = false;
    } else if (charSequence.toString().trim().length() > 20) {
      textInputLayout.setErrorEnabled(true);
      textInputLayout.setError(
          context.getString(R.string.err_valid_max_len, new Object[] {Integer.valueOf(20)}));
      valid = false;
    } else if (exception != null
        && exception.length() > 0
        && charSequence.toString().equals(exception)) {
      valid = true;
    } else if (alreadyUsed.indexOf(charSequence.toString()) >= 0) {
      textInputLayout.setErrorEnabled(true);
      textInputLayout.setError(context.getString(R.string.err_valid_already_used));
      valid = false;
    } else {
      boolean z;
      for (Object equals : usedwords) {
        if (charSequence.toString().equals(equals)) {
          z = true;
          break;
        }
      }
      z = false;
      if (z) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(context.getString(R.string.err_valid_already_used));
        valid = false;
        return;
      }
      for (Object equals2 : keywords) {
        if (charSequence.toString().equals(equals2)) {
          z = true;
          break;
        }
      }
      z = false;
      if (z) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(context.getString(R.string.err_use_rev_name));
        valid = false;
      } else if (!Character.isLetter(charSequence.charAt(0))) {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(context.getString(R.string.err_start_number));
        valid = false;
      } else if (ps.matcher(charSequence.toString()).matches()) {
        textInputLayout.setErrorEnabled(false);
        valid = true;
      } else {
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(context.getString(R.string.err_valid_only_eng_3));
        valid = false;
      }
    }
    onTextChanged.call();
  }

  public void setUsedWords(String[] strArr) {
    usedwords = strArr;
  }

  public SingleListener getOnTextChanged() {
    return this.onTextChanged;
  }

  public void setOnTextChanged(final SingleListener onTextChanged) {
    this.onTextChanged = onTextChanged;
  }
}
