# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

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

# Room
-keep class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.Entity

# Hilt
-keep class * extends dagger.hilt.android.internal.AndroidInjector { *; }
-keep class * extends dagger.hilt.android.internal.AndroidInjector.Factory { *; }
-keep class * extends dagger.hilt.android.internal.GeneratedComponentManager { *; }

# Gson
-keep class com.google.gson.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

# Coroutines
-keep class kotlinx.coroutines.** { *; }

# Timber
-dontwarn timber.log.Timber

# Lottie
-keep class com.airbnb.lottie.** { *; }

# Coil
-keep class coil.** { *; }
-keep class coil3.** { *; }

# Navigation Component
-keep class androidx.navigation.** { *; }

# DataStore
-keep class androidx.datastore.** { *; }

# WorkManager
-keep class androidx.work.** { *; }

# Paging
-keep class androidx.paging.** { *; }

# Compose
-keep class androidx.compose.** { *; }

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
}

# Keep - Applications. Keep all application classes, as they are accessed dynamically.
-keep public class * extends android.app.Application

# Keep - Activities. Keep all activities.
-keep public class * extends android.app.Activity

# Keep - Fragments. Keep all fragments.
-keep public class * extends androidx.fragment.app.Fragment

# Keep - ViewModels. Keep all ViewModels.
-keep public class * extends androidx.lifecycle.ViewModel

# Keep - Services. Keep all services.
-keep public class * extends android.app.Service

# Keep - Broadcast receivers. Keep all broadcast receivers.
-keep public class * extends android.content.BroadcastReceiver

# Keep - Content providers. Keep all content providers.
-keep public class * extends android.content.ContentProvider

# Keep - Views. Keep all custom views.
-keep public class * extends android.view.View

# Keep - Adapters. Keep all adapters.
-keep public class * extends androidx.recyclerview.widget.RecyclerView.Adapter

# Keep - ViewHolders. Keep all ViewHolders.
-keep public class * extends androidx.recyclerview.widget.RecyclerView.ViewHolder

# Serializable classes
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Parcelable classes
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Keep - Native methods. Keep all native method names and the names of their classes.
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep - Enum classes. Keep the special static methods that are required in enumeration classes.
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep - R classes. Keep your R classes.
-keep class **.R
-keep class **.R$* {
    <fields>;
}

# Keep - BuildConfig class.
-keep class **.BuildConfig { *; }

# Remove debug information
-keepattributes Exceptions, InnerClasses, Signature, Deprecated,
                SourceFile, LineNumberTable, *Annotation*, EnclosingMethod

# Optimization configuration
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification
-dontpreverify