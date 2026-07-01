# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-dontwarn org.jspecify.nullness.Nullable

# OR (broader, safer)
# -dontwarn org.jspecify.**



# ✅ Keep LocaleHelper for language switching
-keep class com.iam.bitcoin.Multilanguage.LocaleHelper { *; }
-keep class com.iam.bitcoin.Multilanguage.** { *; }
-keep class com.iam.bitcoin.BaseActivity.** { *; }
-keep class com.google.android.gms.ads.** { *; }
-keep interface com.google.android.gms.ads.** { *; }
-keep class com.google.android.gms.internal.ads.** { *; }
-dontwarn com.google.android.gms.internal.ads.**


# ✅ Keep class constructors (for reflection or libraries)
-keepclassmembers class * {
    public <init>(...);
}