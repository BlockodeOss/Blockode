package dev.trindadedev.blockode.ui.activities.settings;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import dev.trindadedev.blockode.databinding.ActivitySettingsBinding;
import dev.trindadedev.blockode.ui.base.BaseAppCompatActivity;

public class SettingsActivity extends BaseAppCompatActivity {

  @NonNull private ActivitySettingsBinding binding;

  @Override
  @NonNull
  protected View bindLayout() {
    binding = ActivitySettingsBinding.inflate(getLayoutInflater());
    return binding.getRoot();
  }

  @Override
  protected void onBindLayout(@Nullable final Bundle savedInstanceState) {
    toast("No implemented yet Nigeria bro");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    this.binding = null;
  }
}