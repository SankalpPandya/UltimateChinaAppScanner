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
-keep public class com.google.code**
-keep public class com.proudindian.chinamuktbharat.model.AlternateChinaAppsInfo
-keep public class com.proudindian.chinamuktbharat.model.Alternative
-keep public class com.proudindian.chinamuktbharat.model.Root

-keepclassmembers class com.proudindian.chinamuktbharat.model.AlternateChinaAppsInfo {
public *;
private *;
protected *;
}

-keepclassmembers class com.proudindian.chinamuktbharat.model.Alternative {
public *;
private *;
protected *;
}
-keepclassmembers class com.proudindian.chinamuktbharat.model.Root {
public *;
private *;
protected *;
}

#   public *;
# Gson specific classes
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
# Gson specific classes
-keepclassmembers class **.R$* {
  public static <fields>;
}

-keep class com.google.gson.examples.android.model.** { *; }
#-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserialize
# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile