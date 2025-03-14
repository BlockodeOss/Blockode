package dev.trindadedev.blockode.ui.activities.editor;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import dev.trindadedev.blockode.R;
import dev.trindadedev.blockode.content.blocks.JavaBlocks;
import dev.trindadedev.blockode.databinding.ActivityLogicEditorBinding;
import dev.trindadedev.blockode.editor.generator.JavaGenerator;
import dev.trindadedev.blockode.project.manage.ProjectManager;
import dev.trindadedev.blockode.ui.activities.editor.palette.PaletteAnimator;
import dev.trindadedev.blockode.ui.activities.editor.palette.PaletteBlocksManager;
import dev.trindadedev.blockode.ui.base.BaseAppCompatActivity;
import dev.trindadedev.blockode.ui.components.editor.block.OnBlockCategorySelectListener;
import dev.trindadedev.blockode.utils.BlockUtil;
import dev.trindadedev.blockode.utils.FileUtil;
import dev.trindadedev.blockode.utils.GsonUtil;
import dev.trindadedev.blockode.utils.StringUtil;

public class LogicEditorActivity extends BaseAppCompatActivity
    implements OnBlockCategorySelectListener {

  private ActivityLogicEditorBinding binding;

  private OnBackPressedCallback onBackPressedCallback =
      new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
          if (!paletteAnimator.isPaletteOpen) {
            save();
            setEnabled(false);
            getOnBackPressedDispatcher().onBackPressed();
            setEnabled(true);
          }
          paletteAnimator.showHidePalette(false);
        }
      };

  @Nullable private EditorState editorState;
  private PaletteBlocksManager paletteBlocksManager;
  private JavaBlocks javaBlocks;
  private PaletteAnimator paletteAnimator;
  private ProjectManager projectManager;

  @Override
  @NonNull
  protected View bindLayout() {
    binding = ActivityLogicEditorBinding.inflate(getLayoutInflater());
    return binding.getRoot();
  }

  @Override
  protected void onBindLayout(@Nullable final Bundle savedInstanceState) {
    configureData(savedInstanceState);
    paletteAnimator = new PaletteAnimator(this);
    paletteBlocksManager = new PaletteBlocksManager(this, binding.paletteBlock);
    javaBlocks = new JavaBlocks(paletteBlocksManager);
    projectManager = new ProjectManager(this, editorState.project.scId);
  }

  @Override
  public void onPostBind(@Nullable final Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    configurePaletteAnimator();
    configurePaletteManager();
    configureBlockPane();
    configureToolbar(binding.toolbar);
    getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    javaBlocks.createRoot(editorState.project.basicInfo.mainClassPackage);
    paletteAnimator.adjustLayout2(getResources().getConfiguration().orientation);
    binding.paletteBlock.getPaletteSelector().setOnBlockCategorySelectListener(this);
    binding.paletteBlock.getPaletteSelector().getItems().get(0).setSelected(true);
    onBlockCategorySelect(0, BlockUtil.BLOCK_COLOR_VARIABLE);
  }

  @Override
  public void onConfigurationChanged(@NonNull final Configuration configuration) {
    super.onConfigurationChanged(configuration);
    paletteAnimator.adjustLayout2(configuration.orientation);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    var runButton =
        menu.add(Menu.NONE, 0, Menu.NONE, StringUtil.getString(R.string.common_word_run));
    runButton.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    runButton.setIcon(R.drawable.ic_mtrl_run);
    return super.onCreateOptionsMenu(menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem menuItem) {
    if (menuItem.getItemId() == 0) {
      runCode();
      return true;
    }
    return super.onOptionsItemSelected(menuItem);
  }

  @Override
  public void onSaveInstanceState(final Bundle bundle) {
    bundle.putParcelable("editor_state", editorState);
  }

  @Override
  public void onBlockCategorySelect(final int id, final int color) {
    paletteBlocksManager.removeAll();
    switch (id) {
      case 0 -> javaBlocks.createVariableBlocksPalette(color);
      case 1 -> javaBlocks.createListBlocksPalette(color);
      case 2 -> javaBlocks.createControlBlocksPalette(color);
      case 3 -> javaBlocks.createOperatorBlocksPalette(color);
      case 4 -> javaBlocks.createMathBlocksPalette(color);
      case 5 -> javaBlocks.createFileBlocksPalette(color);
    }
  }

  @Override
  protected void configureToolbar(@NonNull MaterialToolbar toolbar) {
    super.configureToolbar(toolbar);
    toolbar.setSubtitle(editorState.project.basicInfo.mainClassPackage);
  }

  /** Get and define all needed variables */
  private final void configureData(@Nullable final Bundle savedInstanceState) {
    if (savedInstanceState == null) {
      editorState = getParcelable("editor_state", EditorState.class);
    } else {
      editorState = getParcelable(savedInstanceState, "editor_state", EditorState.class);
    }
  }

  /** Configures editor BlockPane */
  private final void configureBlockPane() {
    binding.editor.getBlockPane().setScId(editorState.project.scId);
    binding.editor.getBlockPane().setBlocks(projectManager.getCurrentProject().blocks);
  }

  private final void configurePaletteAnimator() {
    paletteAnimator.fabTogglePalette = binding.fabTogglePalette;
    paletteAnimator.editor = binding.editor;
    paletteAnimator.layoutPalette = binding.layoutPalette;
    paletteAnimator.areaPalette = binding.areaPalette;
    paletteAnimator.configureAnimators(getResources().getConfiguration().orientation);
    paletteAnimator.updatePalettePosition(getResources().getConfiguration().orientation);
    paletteAnimator.showHidePalette(!paletteAnimator.isPaletteOpen);
    binding.fabTogglePalette.setOnClickListener(
        v -> paletteAnimator.showHidePalette(!paletteAnimator.isPaletteOpen));
  }

  private final void configurePaletteManager() {
    paletteBlocksManager.setScId(editorState.project.scId);
    paletteBlocksManager.setBlockPane(binding.editor.getBlockPane());
    var paletteBlockTouchListener = paletteBlocksManager.getPaletteBlockTouchListener();
    paletteBlockTouchListener.dummy = binding.dummy;
    paletteBlockTouchListener.editor = binding.editor;
    paletteBlockTouchListener.paletteBlock = binding.paletteBlock;
    paletteBlockTouchListener.pane = binding.editor.getBlockPane();
  }

  private final void save() {
    var blocksJson = GsonUtil.getGson().toJson(binding.editor.getBlockPane().getBlocks());
    FileUtil.writeText(
        ProjectManager.getBlocksFile(projectManager.getScId()).getAbsolutePath(), blocksJson);
    paletteBlocksManager.getPaletteButtonClickListener().getVariablesManager().saveVariables();
  }

  private final void runCode() {
    showProgress(StringUtil.getString(R.string.message_generating_code));
    new Thread(
            () -> {
              var javaBlocks = binding.editor.getBlockPane().getBlocks();
              var code = new JavaGenerator(javaBlocks).generate();
              runOnUiThread(
                  () -> {
                    final var builder =
                        new MaterialAlertDialogBuilder(this)
                            .setTitle(StringUtil.getString(R.string.common_word_code))
                            .setMessage(code)
                            .setPositiveButton(
                                StringUtil.getString(R.string.common_word_ok),
                                (d, w) -> {
                                  d.dismiss();
                                });
                    final AlertDialog dialog = builder.create();
                    dialog.setOnShowListener(
                        d -> {
                          final var message = (TextView) dialog.findViewById(android.R.id.message);
                          message.setTextIsSelectable(true);
                        });

                    dialog.show();

                    dismissProgress();
                  });
            })
        .start();
  }
}
