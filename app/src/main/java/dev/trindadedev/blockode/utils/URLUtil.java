package dev.trindadedev.blockode.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class URLUtil {
  URLUtil() {}

  public static void openUrl(final Activity activity, final String url) {
    final var intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    activity.startActivity(intent);
  }
}
