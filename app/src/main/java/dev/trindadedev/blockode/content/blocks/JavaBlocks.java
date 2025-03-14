package dev.trindadedev.blockode.content.blocks;

import static dev.trindadedev.blockode.utils.StringUtil.getString;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;
import dev.trindadedev.blockode.R;
import dev.trindadedev.blockode.project.manage.variable.VariablesManager;
import dev.trindadedev.blockode.ui.activities.editor.palette.ButtonsTag;
import dev.trindadedev.blockode.ui.activities.editor.palette.PaletteBlocksManager;
import dev.trindadedev.blockode.utils.BlockUtil;
import dev.trindadedev.blockode.utils.SpecUtil;
import dev.trindadedev.blockode.utils.variable.Atomic;

/** Store all java blocks of palette */
public class JavaBlocks {
  private final PaletteBlocksManager paletteBlocksManager;
  private final VariablesManager variablesManager;

  public JavaBlocks(final PaletteBlocksManager paletteBlockManager) {
    this.paletteBlocksManager = paletteBlockManager;
    this.variablesManager =
        paletteBlockManager.getPaletteButtonClickListener().getVariablesManager();
  }

  @Nullable
  public void createRoot(@Nullable final String className) {
    paletteBlocksManager.getBlockPane().addRoot(SpecUtil.getSpecForFileName(className), className);
  }

  public void createVariableBlocksPalette(@ColorInt final int color) {
    paletteBlocksManager.addButtonToPalette(
        getString(R.string.logic_btn_add_variable), ButtonsTag.BUTTON_ADD_VARIABLE);
    paletteBlocksManager.addButtonToPalette(
        getString(R.string.logic_btn_remove_variable), ButtonsTag.BUTTON_REMOVE_VARIABLE);
    addVariablesFromVariablesManager(color);
  }

  private void addVariablesFromVariablesManager(@ColorInt final int color) {
    variablesManager.setScId(paletteBlocksManager.getScId());
    final var variables = variablesManager.getVariables();
    final var hasBoolean = new Atomic<Boolean>(false);
    final var hasInteger = new Atomic<Boolean>(false);
    final var hasString = new Atomic<Boolean>(false);

    variables.forEach(
        variable -> {
          final var type = variable.type;
          final var variableName = variable.name;

          switch (type) {
            case BlockUtil.VAR_TYPE_BOOLEAN -> {
              paletteBlocksManager.addBlockToPalette(
                  variableName,
                  BlockUtil.BLOCK_TYPE_BOOLEAN,
                  BlockUtil.BLOCK_OPCODE_GET_VAR,
                  color,
                  new Object[0]);
              hasBoolean.set(true);
            }
            case BlockUtil.VAR_TYPE_INTEGER -> {
              paletteBlocksManager.addBlockToPalette(
                  variableName,
                  BlockUtil.BLOCK_TYPE_INTEGER,
                  BlockUtil.BLOCK_OPCODE_GET_VAR,
                  color,
                  new Object[0]);
              hasInteger.set(true);
            }
            case BlockUtil.VAR_TYPE_STRING -> {
              paletteBlocksManager.addBlockToPalette(
                  variableName,
                  BlockUtil.BLOCK_TYPE_STRING,
                  BlockUtil.BLOCK_OPCODE_GET_VAR,
                  color,
                  new Object[0]);
              hasString.set(true);
            }
          }
        });

    if (hasBoolean.get()) {
      paletteBlocksManager.addBlockToPalette(
          "",
          BlockUtil.BLOCK_TYPE_COMMAND,
          BlockUtil.BLOCK_OPCODE_SET_VAR_BOOL,
          color,
          new Object[0]);
    }
    if (hasInteger.get()) {
      paletteBlocksManager.addBlockToPalette(
          "",
          BlockUtil.BLOCK_TYPE_COMMAND,
          BlockUtil.BLOCK_OPCODE_SET_VAR_INT,
          color,
          new Object[0]);
      paletteBlocksManager.addBlockToPalette(
          "",
          BlockUtil.BLOCK_TYPE_COMMAND,
          BlockUtil.BLOCK_OPCODE_INCREASE_INT,
          color,
          new Object[0]);
      paletteBlocksManager.addBlockToPalette(
          "",
          BlockUtil.BLOCK_TYPE_COMMAND,
          BlockUtil.BLOCK_OPCODE_DECREASE_INT,
          color,
          new Object[0]);
    }
    if (hasString.get()) {
      paletteBlocksManager.addBlockToPalette(
          "",
          BlockUtil.BLOCK_TYPE_COMMAND,
          BlockUtil.BLOCK_OPCODE_SET_VAR_STR,
          color,
          new Object[0]);
    }
  }

  public void createListBlocksPalette(@ColorInt final int color) {
    paletteBlocksManager.addButtonToPalette(
        getString(R.string.logic_btn_add_list), ButtonsTag.BUTTON_ADD_LIST);
    paletteBlocksManager.addButtonToPalette(
        getString(R.string.logic_btn_remove_list), ButtonsTag.BUTTON_REMOVE_VARIABLE);
  }

  public void createControlBlocksPalette(@ColorInt final int color) {
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_LOOP, BlockUtil.BLOCK_OPCODE_REPEAT, color, Integer.valueOf(10));
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_LOOP, BlockUtil.BLOCK_OPCODE_FOREVER, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_FINAL, BlockUtil.BLOCK_OPCODE_BREAK, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_LOOP, BlockUtil.BLOCK_OPCODE_IF, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_IFELSE, BlockUtil.BLOCK_OPCODE_IFELSE, color, new Object[0]);
  }

  public void createOperatorBlocksPalette(@ColorInt final int color) {
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_TRUE, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_FALSE, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_MATH_LESS, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_MATH_EQUAL, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_MATH_GREAT, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_AND, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_OR, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_NOT, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_PLUS, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_MINUS, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_MULTIPLY, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_DIVIDE, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_DIVIDE_REST, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "",
        BlockUtil.BLOCK_TYPE_INTEGER,
        BlockUtil.BLOCK_OPCODE_RANDOM,
        color,
        Integer.valueOf(1),
        Integer.valueOf(10));
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_STR_LENGTH, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_STRING, BlockUtil.BLOCK_OPCODE_STR_JOIN, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_STR_INDEX, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_STRING, BlockUtil.BLOCK_OPCODE_STR_SUB, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_BOOLEAN, BlockUtil.BLOCK_OPCODE_STR_EQUAL, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_INTEGER, BlockUtil.BLOCK_OPCODE_TO_NUMBER, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_STRING, BlockUtil.BLOCK_OPCODE_TO_STRING, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_STRING, BlockUtil.BLOCK_OPCODE_TRIM, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_STRING, BlockUtil.BLOCK_OPCODE_TOUPPERCASE, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "", BlockUtil.BLOCK_TYPE_STRING, BlockUtil.BLOCK_OPCODE_TOLOWERCASE, color, new Object[0]);
    paletteBlocksManager.addBlockToPalette(
        "",
        BlockUtil.BLOCK_TYPE_COMMAND,
        BlockUtil.BLOCK_OPCODE_ADD_SOURCE_DIRECTLY,
        color,
        new Object[0]);
  }

  public void createMathBlocksPalette(@ColorInt final int color) {}

  public void createFileBlocksPalette(@ColorInt final int color) {}
}
