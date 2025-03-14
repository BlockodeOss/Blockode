package dev.trindadedev.blockode.ui.activities.main.components;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import dev.trindadedev.blockode.R;
import dev.trindadedev.blockode.beans.ProjectBasicInfoBean;
import dev.trindadedev.blockode.beans.ProjectBean;
import dev.trindadedev.blockode.databinding.DialogCreateProjectBinding;
import dev.trindadedev.blockode.project.manage.ProjectManager;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class CreateProjectDialog extends BottomSheetDialog {
  private final DialogCreateProjectBinding binding;

  public CreateProjectDialog(@NonNull Context context) {
    super(context);
    binding = DialogCreateProjectBinding.inflate(getLayoutInflater());
    setContentView(binding.getRoot());
    binding.next.setOnClickListener(v -> onNext());
    binding.cancel.setOnClickListener(v -> dismiss());
  }

  private void onNext() {
    var project = new ProjectBean();
    var basicInfo = new ProjectBasicInfoBean();
    basicInfo.name = Objects.requireNonNull(binding.projectName.getText()).toString();
    basicInfo.packageName = Objects.requireNonNull(binding.projectPackage.getText()).toString();
    basicInfo.mainClassPackage =
        basicInfo.packageName + "." + Objects.requireNonNull(binding.projectClass.getText());
    project.scId = String.valueOf(UUID.randomUUID()); // random scid for now
    project.basicInfo = basicInfo;
    project.variables = new ArrayList<>();
    project.blocks = new ArrayList<>();

    if (!((binding.projectName.getText()).toString().isEmpty()
        || (binding.projectPackage.getText()).toString().isEmpty())) {
      ProjectManager.createProjectByBean(project);
      dismiss();
    } else {
      Toast.makeText(getContext(), R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
    }
  }
}
