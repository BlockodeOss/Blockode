package dev.trindadedev.blockode.beans;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import dev.trindadedev.blockode.utils.ParcelUtil;
import dev.trindadedev.blockode.utils.PrintUtil;
import java.util.ArrayList;

public class ProjectBean extends BaseBean implements Parcelable {
  public static final Creator<ProjectBean> CREATOR =
      new Creator<ProjectBean>() {
        public ProjectBean createFromParcel(Parcel parcel) {
          return new ProjectBean(parcel);
        }

        public ProjectBean[] newArray(int size) {
          return new ProjectBean[size];
        }
      };

  @Expose public String scId;
  public ProjectBasicInfoBean basicInfo;
  public ArrayList<VariableBean> variables;

  public ProjectBean() {}

  public ProjectBean(final Parcel parcel) {
    this.scId = parcel.readString();
    this.basicInfo = ParcelUtil.readParcelable(parcel, ProjectBasicInfoBean.class);
    this.variables = (ArrayList<VariableBean>) ParcelUtil.readSerializable(parcel, ArrayList.class);
  }

  public void copy(final ProjectBean other) {
    this.scId = other.scId;
    this.basicInfo = other.basicInfo;
    this.variables = other.variables;
  }

  public int describeContents() {
    return 0;
  }

  @Override
  public void print() {
    PrintUtil.print(this.scId);
    PrintUtil.print(this.basicInfo);
    PrintUtil.print(this.variables);
  }

  public void writeToParcel(final Parcel parcel, final int flags) {
    parcel.writeString(this.scId);
    parcel.writeParcelable(this.basicInfo, flags);
    parcel.writeSerializable(this.variables);
  }
}
