package com.iam.bitcoin.Game;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import com.iam.bitcoin.R;

/**
 * Applies status bar / nav bar / action bar colors that respect the current
 * system theme, instead of hardcoding R.color.white (which is intentionally
 * pure white in both light and dark colors.xml — it's for icon-on-button use,
 * not for chrome backgrounds).
 *
 * Call ThemeBars.apply(this) once in onCreate(), after setContentView().
 */
public class ThemeBars {

    public static void apply(Activity activity) {
        boolean isNightMode = isNightMode(activity);

        // bg_page already flips correctly between light/dark colors.xml
        int barColor = ContextCompat.getColor(activity, R.color.bg_page);

        if (activity instanceof androidx.appcompat.app.AppCompatActivity) {
            androidx.appcompat.app.AppCompatActivity appCompatActivity =
                    (androidx.appcompat.app.AppCompatActivity) activity;
            if (appCompatActivity.getSupportActionBar() != null) {
                appCompatActivity.getSupportActionBar()
                        .setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(barColor));
            }
        }

        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= 21) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(barColor);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setNavigationBarColor(barColor);
        }

        // Light status bar icons only make sense when the bar itself is light.
        // In dark mode we must NOT set this flag, or dark icons disappear on a dark bar.
        View decorView = window.getDecorView();
        int flags = decorView.getSystemUiVisibility();
        if (isNightMode) {
            flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        decorView.setSystemUiVisibility(flags);
    }

    public static boolean isNightMode(Activity activity) {
        int nightModeFlags = activity.getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK;
        return nightModeFlags == Configuration.UI_MODE_NIGHT_YES;
    }
}