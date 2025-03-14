package dev.trindadedev.blockode.ui.components.editor.block;

/** Decompiled from Sketchware 1.1.13 */
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.annotation.Nullable;
import dev.trindadedev.blockode.R;
import dev.trindadedev.blockode.project.manage.variable.VariablesManager;
import dev.trindadedev.blockode.ui.components.dialog.EditValueDialog;
import dev.trindadedev.blockode.ui.components.dialog.PropertyInputDialog;
import dev.trindadedev.blockode.utils.BlockUtil;
import dev.trindadedev.blockode.utils.LayoutUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BlockArg extends BlockBase {
  private Object argValue = "";
  private ViewGroup content;
  private int defaultArgWidth = 20;
  private boolean isEditable = false;
  private Context mContext;
  private AlertDialog mDlg;
  private String mMenuName = "";
  private TextView mTextView;
  private int paddingText = 4;
  private String scId;

  @Nullable private VariablesManager variablesManager;

  public BlockArg(Context var1, String var2, int var3, String var4) {
    super(var1, var2, true);
    this.mContext = var1;
    this.mMenuName = var4;
    this.init(var1);
    variablesManager = new VariablesManager(mContext, scId);
  }

  public void setScId(final String scId) {
    this.scId = scId;
    variablesManager.setScId(scId);
  }

  public String getScId() {
    return scId;
  }

  // $FF: synthetic method
  static AlertDialog access$000(BlockArg var0) {
    return var0.mDlg;
  }

  // $FF: synthetic method
  static ViewGroup access$100(BlockArg var0) {
    return var0.content;
  }

  private RadioButton createImageRadioButton(String var1) {
    RadioButton var2 = new RadioButton(this.getContext());
    var2.setText("");
    var2.setTag(var1);
    LayoutParams var3 =
        new LayoutParams(-2, (int) (60.0F * LayoutUtil.getDip(this.getContext(), 1.0F)));
    var2.setGravity(19);
    var2.setLayoutParams(var3);
    return var2;
  }

  private RadioButton createPairItem(String var1, String var2) {
    RadioButton var3 = new RadioButton(this.getContext());
    var3.setText(var1 + " : " + var2);
    var3.setTag(var2);
    LayoutParams var4 =
        new LayoutParams(-1, (int) (40.0F * LayoutUtil.getDip(this.getContext(), 1.0F)));
    var3.setGravity(19);
    var3.setLayoutParams(var4);
    return var3;
  }

  private RadioButton createRadioButton(String var1) {
    RadioButton var2 = new RadioButton(this.getContext());
    var2.setText("");
    var2.setTag(var1);
    LayoutParams var3 =
        new LayoutParams(-2, (int) (40.0F * LayoutUtil.getDip(this.getContext(), 1.0F)));
    var2.setGravity(19);
    var2.setLayoutParams(var3);
    return var2;
  }

  private LinearLayout createRadioImage(String var1, boolean var2) {
    float var3 = LayoutUtil.getDip(this.getContext(), 1.0F);
    LinearLayout var4 = new LinearLayout(this.getContext());
    var4.setLayoutParams(new LayoutParams(-1, (int) (60.0F * var3)));
    var4.setGravity(19);
    var4.setOrientation(LinearLayout.HORIZONTAL);
    TextView var5 = new TextView(this.getContext());
    LinearLayout.LayoutParams var6 = new LinearLayout.LayoutParams(0, -2);
    var6.weight = 1.0F;
    var5.setLayoutParams(var6);
    var5.setText(var1);
    var4.addView(var5);
    ImageView var7 = new ImageView(this.getContext());
    var7.setScaleType(ScaleType.CENTER_CROP);
    var7.setLayoutParams(new LayoutParams((int) (48.0F * var3), (int) (48.0F * var3)));
    if (var2) {
      var7.setImageResource(
          this.getContext()
              .getResources()
              .getIdentifier(var1, "drawable", this.getContext().getPackageName()));
    }

    var7.setBackgroundColor(-4342339);
    var4.addView(var7);
    return var4;
  }

  private RadioButton createSingleItem(String var1) {
    RadioButton var2 = new RadioButton(this.getContext());
    var2.setText(var1);
    LayoutParams var3 =
        new LayoutParams(-1, (int) (40.0F * LayoutUtil.getDip(this.getContext(), 1.0F)));
    var2.setGravity(19);
    var2.setLayoutParams(var3);
    return var2;
  }

  private int getLabelWidth() {
    Rect var1 = new Rect();
    this.mTextView
        .getPaint()
        .getTextBounds(
            this.mTextView.getText().toString(), 0, this.mTextView.getText().length(), var1);
    return var1.width() + this.paddingText;
  }

  private void init(Context var1) {
    byte var3;
    label48:
    {
      String var2 = this.mType;
      switch (var2.hashCode()) {
        case 98:
          if (var2.equals("b")) {
            var3 = 0;
            break label48;
          }
          break;
        case 100:
          if (var2.equals("d")) {
            var3 = 1;
            break label48;
          }
          break;
        case 109:
          if (var2.equals("m")) {
            var3 = 4;
            break label48;
          }
          break;
        case 110:
          if (var2.equals("n")) {
            var3 = 2;
            break label48;
          }
          break;
        case 115:
          if (var2.equals("s")) {
            var3 = 3;
            break label48;
          }
      }

      var3 = -1;
    }

    switch (var3) {
      case 0:
        this.mColor = 1342177280;
        this.defaultArgWidth = 25;
        break;
      case 1:
        this.mColor = -657931;
        break;
      case 2:
        this.mColor = -3155748;
        break;
      case 3:
        this.mColor = -1;
        break;
      case 4:
        this.mColor = 805306368;
    }

    this.defaultArgWidth = (int) ((float) this.defaultArgWidth * this.dip);
    this.paddingText = (int) ((float) this.paddingText * this.dip);
    if (this.mType.equals("m")
        || this.mType.equals("d")
        || this.mType.equals("n")
        || this.mType.equals("s")) {
      this.mTextView = this.makeEditText("");
      this.addView(this.mTextView);
    }

    this.setWidthAndTopHeight((float) this.defaultArgWidth, (float) this.labelAndArgHeight, false);
  }

  private TextView makeEditText(String var1) {
    TextView var2 = new TextView(this.mContext);
    var2.setText(var1);
    var2.setTextSize(9.0F);
    android.widget.RelativeLayout.LayoutParams var3 =
        new android.widget.RelativeLayout.LayoutParams(
            this.defaultArgWidth, this.labelAndArgHeight);
    var3.setMargins(0, 0, 0, 0);
    var2.setPadding(5, 0, 0, 0);
    var2.setLayoutParams(var3);
    var2.setBackgroundColor(0);
    var2.setSingleLine();
    var2.setGravity(17);
    if (!this.mType.equals("m")) {
      var2.setTextColor(-268435456);
      return var2;
    } else {
      var2.setTextColor(-251658241);
      return var2;
    }
  }

  public Object getArgValue() {
    return !this.mType.equals("d") && !this.mType.equals("m") && !this.mType.equals("s")
        ? this.argValue
        : this.mTextView.getText();
  }

  public void setArgValue(Object var1) {
    this.argValue = var1;
    if (this.mType.equals("d") || this.mType.equals("m") || this.mType.equals("s")) {
      this.mTextView.setText(var1.toString());
      int var2 = Math.max(this.defaultArgWidth, this.getLabelWidth());
      this.mTextView.getLayoutParams().width = var2;
      this.setWidthAndTopHeight((float) var2, (float) this.labelAndArgHeight, true);
    }
  }

  public void setEditable(boolean var1) {
    this.isEditable = var1;
  }

  private void showEditPopup(final boolean isInteger) {
    var dialog = new EditValueDialog(getContext());
    if (isInteger) {
      dialog.setPropertyType(PropertyInputDialog.PROPERTY_TYPE_INTEGER);
      dialog.getEditText().setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
      dialog.getEditText().setImeOptions(EditorInfo.IME_ACTION_DONE);
      dialog.getEditText().setMaxLines(1);
    } else {
      dialog.setPropertyType(PropertyInputDialog.PROPERTY_TYPE_STRING);
      dialog.getEditText().setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
      dialog.getEditText().setImeOptions(EditorInfo.IME_ACTION_NONE);
    }
    dialog.setText(mTextView.getText().toString()); // get text from param in block
    dialog.setOnSave(
        value -> {
          var valueToSave = "";
          if (isInteger) {
            valueToSave = Integer.valueOf(value).toString();
          } else if (value.length() > 0 && value.charAt(0) == 64) {
            valueToSave = " " + value;
          } else {
            valueToSave = value;
          }
          setArgValue(valueToSave);
          parentBlock.recalcWidthToParent();
          parentBlock.topBlock().fixLayout();
          parentBlock.pane.calculateWidthHeight();
        });
    dialog.show();
  }

  public void showPopup() {
    if (this.mType.equals("d")) {
      this.showEditPopup(true);
    } else {
      if (this.mType.equals("s")) {
        if (this.mMenuName.equals("intentData")) {
          //     this.showIntentDataPopup();
          return;
        }

        this.showEditPopup(false);
        return;
      }

      if (this.mType.equals("m")) {
        if (this.mMenuName.equals("resource")) {
          // show image picker maybe
          return;
        }

        if (this.mMenuName.equals("color")) {
          // show color picker maybe
          return;
        }

        if (!this.mMenuName.equals("view")
            && !this.mMenuName.equals("textview")
            && !this.mMenuName.equals("imageview")
            && !this.mMenuName.equals("listview")
            && !this.mMenuName.equals("spinner")
            && !this.mMenuName.equals("listSpn")
            && !this.mMenuName.equals("checkbox")) {
          this.showSelectPopup();
          return;
        }

        // this.showSelectPairPopup();
        return;
      }
    }
  }

  public void showSelectPopup() {
    View var1 = LayoutUtil.inflate(this.getContext(), R.layout.property_popup_selector_single);
    Builder var2 = new Builder(this.getContext());
    var2.setView(var1);
    this.content = (ViewGroup) var1.findViewById(R.id.rg_content);
    List var4 = new ArrayList();
    if (this.mMenuName.equals("varInt")) {
      var2.setTitle(this.getResources().getString(R.string.title_popup_select_int_var));
      var4 = variablesManager.getVariablesByType(BlockUtil.VAR_TYPE_INTEGER);
    } else if (this.mMenuName.equals("varBool")) {
      var2.setTitle(this.getResources().getString(R.string.title_popup_select_bool_var));
      var4 = variablesManager.getVariablesByType(BlockUtil.VAR_TYPE_BOOLEAN);
    } else if (this.mMenuName.equals("varStr")) {
      var2.setTitle(this.getResources().getString(R.string.title_popup_select_str_var));
      var4 = variablesManager.getVariablesByType(BlockUtil.VAR_TYPE_STRING);
    } else if (this.mMenuName.equals("listInt")) {
      var2.setTitle(this.getResources().getString(R.string.title_popup_select_int_list));
      var4 = /* DesignDataManager.getListsByType(LogicEditorActivity.filename, 1); */ null;
    } else if (this.mMenuName.equals("listStr")) {
      var2.setTitle(this.getResources().getString(R.string.title_popup_select_str_list));
      var4 = /* DesignDataManager.getListsByType(LogicEditorActivity.filename, 2); */ null;
    } else if (this.mMenuName.equals("list")) {
      var2.setTitle(this.getResources().getString(R.string.title_popup_select_list));
      var4 = /* DesignDataManager.getAllLists(LogicEditorActivity.filename); */ null;
    }
    Iterator var6 = var4.iterator();

    while (var6.hasNext()) {
      RadioButton var12 = this.createSingleItem((String) var6.next());
      this.content.addView(var12);
    }

    int var7 = this.content.getChildCount();

    for (int var8 = 0; var8 < var7; ++var8) {
      RadioButton var11 = (RadioButton) this.content.getChildAt(var8);
      if (this.argValue.toString().equals(var11.getText().toString())) {
        var11.setChecked(true);
        break;
      }
    }

    var2.setNegativeButton(
        R.string.btn_cancel,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface var1, int var2) {
            mDlg.dismiss();
          }
        });
    var2.setPositiveButton(
        R.string.btn_accept,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface var1, int var2) {

            int var3 = content.getChildCount();

            for (int var4 = 0; var4 < var3; ++var4) {
              RadioButton var5 = (RadioButton) content.getChildAt(var4);
              if (var5.isChecked()) {
                setArgValue(var5.getText());
                parentBlock.recalcWidthToParent();
                parentBlock.topBlock().fixLayout();
                parentBlock.pane.calculateWidthHeight();
                break;
              }
            }

            mDlg.dismiss();
          }
        });
    this.mDlg = var2.create();
    this.mDlg.show();
  }
}
