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

# Hilt
-keep class com.metegelistirme.Hilt_* { *; }
-keep @dagger.hilt.android.AndroidEntryPoint class * { *; }

# Room
-keep class * extends androidx.room.RoomDatabase {
    *;
}

# Keep Room entities
-keep class com.metegelistirme.database.entities.** { *; }

# Keep Room DAOs
-keep class com.metegelistirme.database.dao.** { *; }

# Keep ViewModels
-keep class com.metegelistirme.viewmodels.** { *; }

# Keep data classes
-keepclassmembers class com.metegelistirme.models.** {
    *;
}

# Keep Lottie animations
-keep class com.airbnb.lottie.** { *; }

# Keep Timber
-keep class timber.log.Timber { *; }
-keep class timber.log.Timber$Tree { *; }

# Keep Gson
-keep class com.google.gson.** { *; }

# Keep Coroutines
-keep class kotlinx.coroutines.** { *; }

# Keep Compose runtime
-keep class androidx.compose.runtime.** { *; }

# Keep navigation component
-keep class androidx.navigation.** { *; }

# Keep databinding
-keep class androidx.databinding.** { *; }

# Keep view binding
-keep class * implements androidx.viewbinding.ViewBinding {
    public static * inflate(android.view.LayoutInflater);
    public static * inflate(android.view.LayoutInflater, android.view.ViewGroup, boolean);
}

# Remove debug logging in release
-assumenosideeffects class timber.log.Timber {
    public static void d(...);
    public static void v(...);
    public static void i(...);
}

# Optimizations for Kotlin
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify
-dontwarn kotlin.**
-dontwarn org.jetbrains.**
-dontwarn androidx.**
-dontwarn com.google.**
-dontwarn javax.annotation.**

# Keep generic signatures for reflection
-keepattributes Signature,InnerClasses,EnclosingMethod

# Keep annotations
-keepattributes *Annotation*

# Keep resource class names
-keepnames class **.R
-keepnames class **.R$* {
    <fields>;
}

# Keep application class
-keep public class * extends android.app.Application

# Keep activity classes
-keep public class * extends android.app.Activity

# Keep fragment classes
-keep public class * extends androidx.fragment.app.Fragment

# Keep service classes
-keep public class * extends android.app.Service

# Keep broadcast receiver classes
-keep public class * extends android.content.BroadcastReceiver

# Keep content provider classes
-keep public class * extends android.content.ContentProvider

# Keep view classes
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# Keep all native method names and the names of their classes
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep serialization classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep parcelable classes
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep the special static methods that are required in all enumeration classes
-keepclassmembers class * {
    public static ** valueOf(java.lang.String);
}

# Keep custom views, buttons etc.
-keep public class * extends android.view.View {
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Keep onClick listeners
-keepclassmembers class * {
    void *(android.view.View);
}

# Keep menu items
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.MenuItem);
}

# For Google Play Services
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**

# For Firebase
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# For AndroidX
-keep class androidx.** { *; }
-dontwarn androidx.**

# For Material Components
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# For Coil
-keep class coil.** { *; }
-dontwarn coil.**

# For LeakCanary (debug only, but keep rules for safety)
-dontwarn com.squareup.leakcanary.**

# For WorkManager
-keep class androidx.work.** { *; }
-dontwarn androidx.work.**