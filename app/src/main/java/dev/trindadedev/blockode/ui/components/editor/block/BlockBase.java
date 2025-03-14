package dev.trindadedev.blockode.ui.components.editor.block;

/**
 * Originally Decompiled from Sketchware 1.1.13
 * Refactored by Aquiles Trindade (trindadedev)
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.widget.RelativeLayout;
import dev.trindadedev.blockode.utils.LayoutUtil;

public class BlockBase extends RelativeLayout {
  public static final int BooleanShape = 2;
  public static final int CmdOutlineShape = 6;
  public static final int CmdShape = 4;
  public static final int DropdownShape = 12;
  public static final int FinalCmdShape = 5;
  public static final int FinalLoopShape = 10;
  public static final int HatShape = 7;
  public static final int IfElseShape = 11;
  public static final int LoopShape = 9;
  public static final int NumberShape = 3;
  public static final int ProcHatShape = 8;
  public static final int RectShape = 1;
  protected int BottomBarH = 15;
  protected int CornerInset = 3;
  protected int DividerH = 15;
  public int EmptySubstackH = 12;
  protected int InnerCornerInset = 2;
  public int NotchDepth = 3;
  protected int NotchL1 = 15;
  protected int NotchL2 = (this.NotchL1 + this.NotchDepth);
  protected int NotchR1 = (this.NotchL2 + 10);
  protected int NotchR2 = (this.NotchR1 + this.NotchDepth);
  public int SubstackInset = 15;
  protected int childInset = 2;
  private int defaultColor = 805306368;
  public int defaultWidth = 100;
  protected float dip;
  public int dropdownArea = 15;
  public int dropdownIndent = 4;
  public int dropdownWidth = 6;
  protected int hatHeight = 6;
  protected int hatWidth = 60;
  protected int indentBottom = 2;
  protected int indentLeft = 3;
  protected int indentRight = 0;
  protected int indentTop = 2;
  private boolean isNeedReflection = false;
  private boolean isNeedShadow = false;
  public int labelAndArgHeight = 14;
  public int mColor;
  protected Context mContext;
  public Paint mDropdownPaint;
  public boolean mIsArg;
  public Paint mPaint;
  public Paint mReflectionPaint;
  public Paint mShadowPaint;
  public int mShapeType;
  public String mType;
  public Block parentBlock = null;
  private int reflectionWidth = 1;
  private int shadowWidth = 1;
  protected int substack1H = this.EmptySubstackH;
  protected int substack2H = this.EmptySubstackH;
  protected int topH;
  protected int w;

  public BlockBase(Context context, String str, boolean z) {
    super(context);
    this.mContext = context;
    this.mType = str;
    String str2 = this.mType;
    char c = 65535;
    switch (str2.hashCode()) {
      case 32:
        if (str2.equals(" ")) {
          c = 0;
          break;
        }
        break;
      case 98:
        if (str2.equals("b")) {
          c = 1;
          break;
        }
        break;
      case 99:
        if (str2.equals("c")) {
          c = 4;
          break;
        }
        break;
      case 100:
        if (str2.equals("d")) {
          c = 2;
          break;
        }
        break;
      case 101:
        if (str2.equals("e")) {
          c = 6;
          break;
        }
        break;
      case 102:
        if (str2.equals("f")) {
          c = 7;
          break;
        }
        break;
      case 104:
        if (str2.equals("h")) {
          c = 8;
          break;
        }
        break;
      case 109:
        if (str2.equals("m")) {
          c = 9;
          break;
        }
        break;
      case 110:
        if (str2.equals("n")) {
          c = 3;
          break;
        }
        break;
      case 115:
        if (str2.equals("s")) {
          c = 10;
          break;
        }
        break;
      case 3171:
        if (str2.equals("cf")) {
          c = 5;
          break;
        }
        break;
    }
    switch (c) {
      case BlockPane.INSERT_NORMAL /*0*/:
        this.indentTop = 4;
        this.mShapeType = 4;
        break;
      case 1:
        this.indentLeft = 8;
        this.indentRight = 5;
        this.mShapeType = 2;
        break;
      case 2:
        this.mShapeType = 3;
        this.indentLeft = 4;
        break;
      case 3:
        this.mShapeType = 3;
        break;
      case 4:
        this.indentTop = 4;
        this.mShapeType = 9;
        break;
      case 5:
        this.indentTop = 4;
        this.mShapeType = 10;
        break;
      case CmdOutlineShape /*6*/:
        this.indentTop = 4;
        this.mShapeType = 11;
        break;
      case HatShape /*7*/:
        this.indentTop = 4;
        this.mShapeType = 5;
        break;
      case ProcHatShape /*8*/:
        this.indentTop = 8;
        this.mShapeType = 7;
        break;
      case LoopShape /*9*/:
        this.mShapeType = 12;
        break;
      case FinalLoopShape /*10*/:
        this.mShapeType = 1;
        break;
    }
    this.mColor = this.defaultColor;
    this.mIsArg = z;
    setWillNotDraw(false);
    init(context);
  }

  private void drawArm(Path path, int i) {
    path.lineTo((float) this.SubstackInset, (float) (i - this.InnerCornerInset));
    path.lineTo((float) (this.SubstackInset + this.InnerCornerInset), (float) i);
    path.lineTo((float) (this.w - this.CornerInset), (float) i);
    path.lineTo((float) this.w, (float) (this.CornerInset + i));
  }

  private void drawBooleanShape(Canvas canvas) {
    Path path = new Path();
    int i = this.topH / 2;
    path.moveTo((float) i, (float) this.topH);
    path.lineTo(0.0f, (float) i);
    path.lineTo((float) i, 0.0f);
    path.lineTo((float) (this.w - i), 0.0f);
    path.lineTo((float) this.w, (float) i);
    path.lineTo((float) (this.w - i), (float) this.topH);
    canvas.drawPath(path, this.mPaint);
    if (this.isNeedShadow) {
      canvas.drawLines(getBooleanShadows(), this.mShadowPaint);
    }
    if (this.isNeedReflection) {
      canvas.drawLines(getBooleanReflections(), this.mReflectionPaint);
    }
  }

  private void drawCmdShape(Canvas canvas) {
    boolean z = true;
    Path path = new Path();
    drawTop(path);
    drawRightAndBottom(path, this.topH, this.mShapeType != 5, 0);
    canvas.drawPath(path, this.mPaint);
    if (this.isNeedShadow) {
      canvas.drawLines(getRightShadows(0, this.topH), this.mShadowPaint);
      int i = this.topH;
      if (this.mShapeType == 5) {
        z = false;
      }
      canvas.drawLines(getBottomShadows(i, z, 0), this.mShadowPaint);
    }
    if (this.isNeedReflection) {
      canvas.drawLines(getCommandReflections(this.topH), this.mReflectionPaint);
    }
  }

  private void drawDropdownShape(Canvas canvas) {
    canvas.drawRect(new Rect(0, 0, this.w, this.topH), this.mPaint);
    Path path = new Path();
    path.moveTo((float) (this.w - this.dropdownIndent), (float) this.dropdownIndent);
    path.lineTo(
        (float) ((this.w - this.dropdownIndent) - (this.dropdownWidth / 2)),
        (float) (this.dropdownIndent + this.dropdownWidth));
    path.lineTo(
        (float) ((this.w - this.dropdownIndent) - this.dropdownWidth), (float) this.dropdownIndent);
    canvas.drawPath(path, this.mDropdownPaint);
  }

  private void drawHatShape(Canvas canvas) {
    Path path = new Path();
    path.moveTo(0.0f, (float) this.hatHeight);
    path.arcTo(
        new RectF(0.0f, 0.0f, (float) this.hatWidth, (float) (this.hatHeight * 2)), 180.0f, 180.0f);
    path.lineTo((float) (this.w - this.CornerInset), (float) this.hatHeight);
    path.lineTo((float) this.w, (float) (this.hatHeight + this.CornerInset));
    drawRightAndBottom(path, this.topH, true, 0);
    canvas.drawPath(path, this.mPaint);
    if (this.isNeedShadow) {
      canvas.drawLines(getRightShadows(this.hatHeight, this.topH), this.mShadowPaint);
      canvas.drawLines(getBottomShadows(this.topH, true, 0), this.mShadowPaint);
    }
  }

  private void drawIfElseShape(Canvas canvas) {
    Path path = new Path();
    int i = (this.topH + this.substack1H) - this.NotchDepth;
    int i2 = ((this.DividerH + i) + this.substack2H) - this.NotchDepth;
    drawTop(path);
    drawRightAndBottom(path, this.topH, true, this.SubstackInset);
    drawArm(path, i);
    drawRightAndBottom(path, this.DividerH + i, true, this.SubstackInset);
    drawArm(path, i2);
    drawRightAndBottom(path, this.BottomBarH + i2, true, 0);
    canvas.drawPath(path, this.mPaint);
    if (this.isNeedShadow) {
      canvas.drawLines(getRightShadows(0, this.topH), this.mShadowPaint);
      canvas.drawLines(getBottomShadows(this.topH, true, this.SubstackInset), this.mShadowPaint);
      canvas.drawLines(getArmShadows(this.topH, i), this.mShadowPaint);
      canvas.drawLines(getRightShadows(i, this.DividerH + i), this.mShadowPaint);
      canvas.drawLines(
          getBottomShadows(this.DividerH + i, true, this.SubstackInset), this.mShadowPaint);
      canvas.drawLines(getArmShadows(this.DividerH + i, i2), this.mShadowPaint);
      canvas.drawLines(getRightShadows(i2, this.BottomBarH + i2), this.mShadowPaint);
      canvas.drawLines(getBottomShadows(this.BottomBarH + i2, true, 0), this.mShadowPaint);
    }
    if (this.isNeedReflection) {
      canvas.drawLines(getCommandReflections(this.BottomBarH + i2), this.mReflectionPaint);
      canvas.drawLines(getTopReflections(i, this.SubstackInset), this.mReflectionPaint);
      canvas.drawLines(getTopReflections(i2, this.SubstackInset), this.mReflectionPaint);
    }
  }

  private void drawLoopShape(Canvas canvas) {
    boolean z = true;
    Path path = new Path();
    int i = (this.topH + this.substack1H) - this.NotchDepth;
    drawTop(path);
    drawRightAndBottom(path, this.topH, true, this.SubstackInset);
    drawArm(path, i);
    drawRightAndBottom(path, this.BottomBarH + i, this.mShapeType == 9, 0);
    canvas.drawPath(path, this.mPaint);
    if (this.isNeedShadow) {
      canvas.drawLines(getRightShadows(0, this.topH), this.mShadowPaint);
      canvas.drawLines(getBottomShadows(this.topH, true, this.SubstackInset), this.mShadowPaint);
      canvas.drawLines(getArmShadows(this.topH, i), this.mShadowPaint);
      canvas.drawLines(getRightShadows(i, this.BottomBarH + i), this.mShadowPaint);
      int i2 = this.BottomBarH;
      if (this.mShapeType != 9) {
        z = false;
      }
      canvas.drawLines(getBottomShadows(i2 + i, z, 0), this.mShadowPaint);
    }
    if (this.isNeedReflection) {
      canvas.drawLines(getCommandReflections(this.BottomBarH + i), this.mReflectionPaint);
      canvas.drawLines(getTopReflections(i, this.SubstackInset), this.mReflectionPaint);
    }
  }

  private void drawNumberShape(Canvas canvas) {
    Path path = new Path();
    int i = this.topH / 2;
    path.moveTo((float) i, (float) this.topH);
    path.arcTo(new RectF(0.0f, 0.0f, (float) this.topH, (float) this.topH), 90.0f, 180.0f);
    path.lineTo((float) (this.w - i), 0.0f);
    path.arcTo(
        new RectF((float) (this.w - this.topH), 0.0f, (float) this.w, (float) this.topH),
        270.0f,
        180.0f);
    canvas.drawPath(path, this.mPaint);
    if (this.isNeedShadow) {
      canvas.drawArc(
          new RectF(
              (float) (this.w - this.topH),
              0.0f,
              (float) (this.w - (this.shadowWidth / 2)),
              (float) (this.topH - (this.shadowWidth / 2))),
          330.0f,
          120.0f,
          false,
          this.mShadowPaint);
      canvas.drawLines(getNumberBottomShadows(), this.mShadowPaint);
      canvas.drawArc(
          new RectF(
              (float) ((this.shadowWidth / 2) + 0),
              0.0f,
              (float) this.topH,
              (float) (this.topH - (this.shadowWidth / 2))),
          90.0f,
          30.0f,
          false,
          this.mShadowPaint);
    }
    if (this.isNeedReflection) {
      canvas.drawArc(
          new RectF(
              (float) ((this.shadowWidth / 2) + 0),
              (float) ((this.shadowWidth / 2) + 0),
              (float) this.topH,
              (float) this.topH),
          150.0f,
          120.0f,
          false,
          this.mReflectionPaint);
      canvas.drawLines(getNumberTopReflections(), this.mReflectionPaint);
      canvas.drawArc(
          new RectF(
              (float) (this.w - this.topH),
              (float) ((this.shadowWidth / 2) + 0),
              (float) (this.w - (this.shadowWidth / 2)),
              (float) this.topH),
          270.0f,
          30.0f,
          false,
          this.mReflectionPaint);
    }
  }

  private void drawRectShape(Canvas canvas) {
    canvas.drawRect(new Rect(0, 0, this.w, this.topH), this.mPaint);
    if (this.isNeedShadow) {
      canvas.drawLines(getRectShadows(), this.mShadowPaint);
    }
    if (this.isNeedReflection) {
      canvas.drawLines(getRectReflections(), this.mReflectionPaint);
    }
  }

  private void drawRightAndBottom(Path path, int i, boolean z, int i2) {
    path.lineTo((float) this.w, (float) (i - this.CornerInset));
    path.lineTo((float) (this.w - this.CornerInset), (float) i);
    if (z) {
      path.lineTo((float) (this.NotchR2 + i2), (float) i);
      path.lineTo((float) (this.NotchR1 + i2), (float) (this.NotchDepth + i));
      path.lineTo((float) (this.NotchL2 + i2), (float) (this.NotchDepth + i));
      path.lineTo((float) (this.NotchL1 + i2), (float) i);
    }
    if (i2 > 0) {
      path.lineTo((float) (this.InnerCornerInset + i2), (float) i);
      path.lineTo((float) i2, (float) (this.InnerCornerInset + i));
      return;
    }
    path.lineTo((float) (this.CornerInset + i2), (float) i);
    path.lineTo(0.0f, (float) (i - this.CornerInset));
  }

  private void drawTop(Path path) {
    path.moveTo(0.0f, (float) this.CornerInset);
    path.lineTo((float) this.CornerInset, 0.0f);
    path.lineTo((float) this.NotchL1, 0.0f);
    path.lineTo((float) this.NotchL2, (float) this.NotchDepth);
    path.lineTo((float) this.NotchR1, (float) this.NotchDepth);
    path.lineTo((float) this.NotchR2, 0.0f);
    path.lineTo((float) (this.w - this.CornerInset), 0.0f);
    path.lineTo((float) this.w, (float) this.CornerInset);
  }

  private float[] getArmShadows(int i, int i2) {
    return new float[] {
      (float) (this.SubstackInset + this.InnerCornerInset),
      (float) (i - (this.shadowWidth / 2)),
      (float) (this.SubstackInset - (this.shadowWidth / 2)),
      (float) (this.InnerCornerInset + i),
      (float) (this.SubstackInset - (this.shadowWidth / 2)),
      (float) (this.InnerCornerInset + i),
      (float) (this.SubstackInset - (this.shadowWidth / 2)),
      (float) (i2 - this.InnerCornerInset)
    };
  }

  private float[] getBooleanReflections() {
    int i = this.topH / 2;
    return new float[] {
      (float) ((this.shadowWidth / 2) + 0),
      (float) i,
      (float) i,
      (float) ((this.shadowWidth / 2) + 0),
      (float) i,
      (float) ((this.shadowWidth / 2) + 0),
      (float) (this.w - i),
      (float) ((this.shadowWidth / 2) + 0)
    };
  }

  private float[] getBooleanShadows() {
    int i = this.topH / 2;
    return new float[] {
      (float) (this.w - (this.shadowWidth / 2)),
      (float) i,
      (float) (this.w - i),
      (float) (this.topH - (this.shadowWidth / 2)),
      (float) (this.w - i),
      (float) (this.topH - (this.shadowWidth / 2)),
      (float) i,
      (float) (this.topH - (this.shadowWidth / 2))
    };
  }

  private float[] getBottomShadows(int i, boolean z, int i2) {
    float[] fArr = z ? new float[24] : new float[8];
    fArr[0] = (float) this.w;
    fArr[1] = (float) ((i - this.CornerInset) - (this.shadowWidth / 2));
    fArr[2] = (float) (this.w - this.CornerInset);
    fArr[3] = (float) (i - (this.shadowWidth / 2));
    if (z) {
      fArr[4] = (float) (this.w - this.CornerInset);
      fArr[5] = (float) (i - (this.shadowWidth / 2));
      fArr[6] = (float) (this.NotchR2 + i2);
      fArr[7] = (float) (i - (this.shadowWidth / 2));
      fArr[8] = (float) (this.NotchR2 + i2);
      fArr[9] = (float) (i - (this.shadowWidth / 2));
      fArr[10] = (float) (this.NotchR1 + i2);
      fArr[11] = (float) ((this.NotchDepth + i) - (this.shadowWidth / 2));
      fArr[12] = (float) (this.NotchR1 + i2);
      fArr[13] = (float) ((this.NotchDepth + i) - (this.shadowWidth / 2));
      fArr[14] = (float) (this.NotchL2 + i2);
      fArr[15] = (float) ((this.NotchDepth + i) - (this.shadowWidth / 2));
      fArr[16] = (float) (this.NotchL2 + i2);
      fArr[17] = (float) ((this.NotchDepth + i) - (this.shadowWidth / 2));
      fArr[18] = (float) (this.NotchL1 + i2);
      fArr[19] = (float) (i - (this.shadowWidth / 2));
      if (i2 > 0) {
        fArr[20] = (float) (this.NotchL1 + i2);
        fArr[21] = (float) (i - (this.shadowWidth / 2));
        fArr[22] = (float) (this.InnerCornerInset + i2);
        fArr[23] = (float) (i - (this.shadowWidth / 2));
      } else {
        fArr[20] = (float) (this.NotchL1 + i2);
        fArr[21] = (float) (i - (this.shadowWidth / 2));
        fArr[22] = (float) (this.CornerInset + i2);
        fArr[23] = (float) (i - (this.shadowWidth / 2));
      }
    } else if (i2 > 0) {
      fArr[4] = (float) (this.w - this.CornerInset);
      fArr[5] = (float) (i - (this.shadowWidth / 2));
      fArr[6] = (float) (this.InnerCornerInset + i2);
      fArr[7] = (float) (i - (this.shadowWidth / 2));
    } else {
      fArr[4] = (float) (this.w - this.CornerInset);
      fArr[5] = (float) (i - (this.shadowWidth / 2));
      fArr[6] = (float) (this.CornerInset + i2);
      fArr[7] = (float) (i - (this.shadowWidth / 2));
    }
    return fArr;
  }

  private float[] getCommandReflections(int i) {
    return new float[] {
      (float) ((this.shadowWidth / 2) + 0),
      (float) (i - this.CornerInset),
      (float) ((this.shadowWidth / 2) + 0),
      (float) this.CornerInset,
      (float) ((this.shadowWidth / 2) + 0),
      (float) this.CornerInset,
      (float) this.CornerInset,
      (float) ((this.shadowWidth / 2) + 0),
      (float) this.CornerInset,
      (float) ((this.shadowWidth / 2) + 0),
      (float) this.NotchL1,
      (float) ((this.shadowWidth / 2) + 0),
      (float) this.NotchL2,
      (float) (this.NotchDepth + (this.shadowWidth / 2)),
      (float) this.NotchR1,
      (float) (this.NotchDepth + (this.shadowWidth / 2)),
      (float) this.NotchR1,
      (float) (this.NotchDepth + (this.shadowWidth / 2)),
      (float) this.NotchR2,
      (float) ((this.shadowWidth / 2) + 0),
      (float) this.NotchR2,
      (float) ((this.shadowWidth / 2) + 0),
      (float) (this.w - this.CornerInset),
      (float) ((this.shadowWidth / 2) + 0)
    };
  }

  private float[] getNumberBottomShadows() {
    int i = this.topH / 2;
    return new float[] {
      (float) (this.w - i),
      (float) (this.topH - (this.shadowWidth / 2)),
      (float) i,
      (float) (this.topH - (this.shadowWidth / 2))
    };
  }

  private float[] getNumberTopReflections() {
    int i = this.topH / 2;
    return new float[] {
      (float) i,
      (float) ((this.shadowWidth / 2) + 0),
      (float) (this.w - i),
      (float) ((this.shadowWidth / 2) + 0)
    };
  }

  private float[] getRectReflections() {
    return new float[] {
      0.0f,
      (float) ((this.shadowWidth / 2) + 0),
      (float) (this.w - (this.shadowWidth / 2)),
      (float) ((this.shadowWidth / 2) + 0),
      (float) ((this.shadowWidth / 2) + 0),
      0.0f,
      (float) ((this.shadowWidth / 2) + 0),
      (float) (this.topH - (this.shadowWidth / 2))
    };
  }

  private float[] getRectShadows() {
    return new float[] {
      (float) (this.w - (this.shadowWidth / 2)),
      0.0f,
      (float) (this.w - (this.shadowWidth / 2)),
      (float) (this.topH - (this.shadowWidth / 2)),
      (float) (this.w - (this.shadowWidth / 2)),
      (float) (this.topH - (this.shadowWidth / 2)),
      0.0f,
      (float) (this.topH - (this.shadowWidth / 2))
    };
  }

  private float[] getRightShadows(int i, int i2) {
    return new float[] {
      (float) (this.w - (this.shadowWidth / 2)),
      (float) (this.CornerInset + i),
      (float) (this.w - (this.shadowWidth / 2)),
      (float) (i2 - this.CornerInset)
    };
  }

  private float[] getTopReflections(int i, int i2) {
    return new float[] {
      (float) (this.InnerCornerInset + i2),
      (float) ((this.shadowWidth / 2) + i),
      (float) (this.w - this.CornerInset),
      (float) ((this.shadowWidth / 2) + i)
    };
  }

  private void init(Context context) {
    this.dip = LayoutUtil.getDip(context, 1.0f);
    this.NotchDepth = (int) (((float) this.NotchDepth) * this.dip);
    this.EmptySubstackH = (int) (((float) this.EmptySubstackH) * this.dip);
    this.SubstackInset = (int) (((float) this.SubstackInset) * this.dip);
    this.BottomBarH = (int) (((float) this.BottomBarH) * this.dip);
    this.DividerH = (int) (((float) this.DividerH) * this.dip);
    this.CornerInset = (int) (((float) this.CornerInset) * this.dip);
    this.InnerCornerInset = (int) (((float) this.InnerCornerInset) * this.dip);
    this.NotchL1 = (int) (((float) this.NotchL1) * this.dip);
    this.NotchL2 = (int) (((float) this.NotchL2) * this.dip);
    this.NotchR1 = (int) (((float) this.NotchR1) * this.dip);
    this.NotchR2 = (int) (((float) this.NotchR2) * this.dip);
    this.hatHeight = (int) (((float) this.hatHeight) * this.dip);
    this.hatWidth = (int) (((float) this.hatWidth) * this.dip);
    this.substack1H = (int) (((float) this.substack1H) * this.dip);
    this.substack2H = (int) (((float) this.substack2H) * this.dip);
    this.indentLeft = (int) (((float) this.indentLeft) * this.dip);
    this.indentTop = (int) (((float) this.indentTop) * this.dip);
    this.indentRight = (int) (((float) this.indentRight) * this.dip);
    this.indentBottom = (int) (((float) this.indentBottom) * this.dip);
    this.childInset = (int) (((float) this.childInset) * this.dip);
    this.defaultWidth = (int) (((float) this.defaultWidth) * this.dip);
    this.labelAndArgHeight = (int) (((float) this.labelAndArgHeight) * this.dip);
    this.dropdownWidth = (int) (((float) this.dropdownWidth) * this.dip);
    this.dropdownIndent = (int) (((float) this.dropdownIndent) * this.dip);
    this.dropdownArea = (int) (((float) this.dropdownArea) * this.dip);
    this.shadowWidth = (int) (((float) this.shadowWidth) * this.dip);
    this.reflectionWidth = (int) (((float) this.reflectionWidth) * this.dip);
    if (this.shadowWidth < 2) {
      this.shadowWidth = 2;
    }
    if (this.reflectionWidth < 2) {
      this.reflectionWidth = 2;
    }
    this.mPaint = new Paint();
    if (!this.mIsArg) {
      this.isNeedShadow = true;
      this.isNeedReflection = true;
    }
    this.mDropdownPaint = new Paint();
    this.mDropdownPaint.setColor(-536870912);
    this.mDropdownPaint.setStrokeWidth((float) this.shadowWidth);
    this.mShadowPaint = new Paint();
    this.mShadowPaint.setColor(-1610612736);
    this.mShadowPaint.setStyle(Paint.Style.STROKE);
    this.mShadowPaint.setStrokeWidth((float) this.shadowWidth);
    this.mReflectionPaint = new Paint();
    this.mReflectionPaint.setColor(-1593835521);
    this.mReflectionPaint.setStyle(Paint.Style.STROKE);
    this.mReflectionPaint.setStrokeWidth((float) this.reflectionWidth);
    setLayerType(1, (Paint) null);
    setWidthAndTopHeight(
        (float) this.defaultWidth,
        (float) (this.labelAndArgHeight + this.indentTop + this.indentBottom),
        false);
  }

  public boolean canHaveSubstack1() {
    return this.mShapeType >= 9;
  }

  public boolean canHaveSubstack2() {
    return this.mShapeType == 11;
  }

  public void copyFeedbackShapeFrom(BlockBase blockBase, boolean z, boolean z2, int i) {
    this.mColor = -16777216;
    this.mShapeType = blockBase.mShapeType;
    this.w = blockBase.w;
    this.topH = blockBase.topH;
    this.substack1H = blockBase.substack1H;
    this.substack2H = blockBase.substack2H;
    if (!z) {
      if (z2) {
        this.mShapeType = 4;
        this.topH = (int) (6.0f * this.dip);
      } else if (i > 0) {
        this.substack1H = i - this.NotchDepth;
      }
    }
    redraw();
  }

  public int getTopH() {
    return this.topH;
  }

  public int getTotalHeight() {
    int i = this.topH;
    if (canHaveSubstack1()) {
      i += (this.DividerH + this.substack1H) - this.NotchDepth;
    }
    if (canHaveSubstack2()) {
      i += (this.BottomBarH + this.substack2H) - this.NotchDepth;
    }
    return (this.mShapeType == 4
            || this.mShapeType == 7
            || this.mShapeType == 9
            || this.mShapeType == 11)
        ? i + this.NotchDepth
        : i;
  }

  public int getTotalWidth() {
    return this.w;
  }

  public int getW() {
    return this.w;
  }

  public int nextBlockY() {
    return getTotalHeight() - this.NotchDepth;
  }

  /* access modifiers changed from: protected */
  public void onDraw(Canvas canvas) {
    this.mPaint.setColor(this.mColor);
    switch (this.mShapeType) {
      case 1:
        drawRectShape(canvas);
        break;
      case 2:
        drawBooleanShape(canvas);
        break;
      case 3:
        drawNumberShape(canvas);
        break;
      case 4:
      case 5:
        drawCmdShape(canvas);
        break;
      case HatShape /*7*/:
        drawHatShape(canvas);
        break;
      case LoopShape /*9*/:
      case FinalLoopShape /*10*/:
        drawLoopShape(canvas);
        break;
      case IfElseShape /*11*/:
        drawIfElseShape(canvas);
        break;
      case DropdownShape /*12*/:
        drawDropdownShape(canvas);
        break;
    }
    super.onDraw(canvas);
  }

  /* access modifiers changed from: protected */
  public void onMeasure(int i, int i2) {
    super.onMeasure(
        View.MeasureSpec.makeMeasureSpec(getTotalWidth(), 1073741824),
        View.MeasureSpec.makeMeasureSpec(getTotalHeight(), 1073741824));
  }

  public void redraw() {
    requestLayout();
  }

  public void setSubstack1Height(int i) {
    int max = Math.max(i, this.EmptySubstackH);
    if (max != this.substack1H) {
      this.substack1H = max;
    }
  }

  public void setSubstack2Height(int i) {
    int max = Math.max(i, this.EmptySubstackH);
    if (max != this.substack2H) {
      this.substack2H = max;
    }
  }

  public void setWidthAndTopHeight(float f, float f2, boolean z) {
    if (this.mShapeType == 12) {
      this.w = ((int) f) + this.dropdownArea;
    } else {
      this.w = (int) f;
    }
    this.topH = (int) f2;
    if (z) {
      redraw();
    }
  }

  public int substack1y() {
    return this.topH;
  }

  public int substack2y() {
    return ((this.topH + this.substack1H) + this.DividerH) - this.NotchDepth;
  }
}
