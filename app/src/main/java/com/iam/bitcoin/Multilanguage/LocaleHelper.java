package com.iam.bitcoin.Multilanguage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;

import java.util.Locale;
public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "app_language";

    public static Context onAttach(Context context) {
        String lang = getPersistedLanguage(context);
        return setLocale(context, lang);
    }

    public static Context setLocale(Context context, String languageCode) {
        persistLanguage(context, languageCode);
        return updateResources(context, languageCode);
    }

    public static void setAppLocale(Context context, String languageCode) {
        persistLanguage(context, languageCode);
        updateResources(context, languageCode);
    }

    private static void persistLanguage(Context context, String languageCode) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(SELECTED_LANGUAGE, languageCode).apply();
    }

    public static String getPersistedLanguage(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, Locale.getDefault().getLanguage());
    }

    @SuppressLint("ObsoleteSdkInt")
    private static Context updateResources(Context context, String languageCode) {
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale); // ✅ RTL support
            return context.createConfigurationContext(configuration);
        } else {
            configuration.locale = locale;
            configuration.setLayoutDirection(locale); // ✅ RTL support
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
            return context;
        }
    }

}