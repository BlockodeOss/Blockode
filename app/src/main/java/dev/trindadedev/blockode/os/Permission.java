package dev.trindadedev.blockode.os;

public interface Permission {
  void request();

  PermissionStatus check();
}
