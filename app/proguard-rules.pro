# ProGuard kuralları
# Temel optimizasyonlar
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# Kotlin koruma
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keep class kotlin.** { *; }

# Hilt koruma
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.ActivityComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.FragmentComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.ServiceComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewWithFragmentComponentManager { *; }
-keep class * extends dagger.hilt.android.EntryPoint { *; }

# Room koruma
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * extends androidx.room.Entity { *; }
-keepclassmembers class * {
    @androidx.room.* *;
}

# Gson koruma
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Lottie koruma
-keep class com.airbnb.lottie.** { *; }
-dontwarn com.airbnb.lottie.**

# Compose koruma
-keep class androidx.compose.runtime.** { *; }
-keep class androidx.compose.ui.** { *; }
-keep class androidx.compose.foundation.** { *; }
-keep class androidx.compose.material.** { *; }

# Navigation koruma
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

# DataStore koruma
-keep class androidx.datastore.** { *; }
-dontwarn androidx.datastore.**

# Paging koruma
-keep class androidx.paging.** { *; }
-dontwarn androidx.paging.**

# WorkManager koruma
-keep class androidx.work.** { *; }
-dontwarn androidx.work.**

# Coil koruma
-keep class coil.** { *; }
-dontwarn coil.**

# Timber koruma
-keep class timber.log.** { *; }
-dontwarn timber.log.**

# Uygulama sınıfları
-keep public class * extends android.app.Application
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends androidx.fragment.app.Fragment
-keep public class * extends android.view.View
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.Service

# View Binding koruma
-keep class * extends androidx.viewbinding.ViewBinding { *; }

# BuildConfig koruma
-keep class **.BuildConfig { *; }

# R dosyaları
-keep class **.R
-keep class **.R$* {
    <fields>;
}

# Parcelable koruma
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Serializable koruma
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Native metodlar
-keepclasseswithmembernames class * {
    native <methods>;
}

# Reflection kullanımı
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes EnclosingMethod

# OkHttp koruma
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Retrofit koruma
-keep class retrofit2.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
-dontwarn retrofit2.**

# Coroutines koruma
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Lifecycle koruma
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

# Material Design koruma
-keep class com.google.android.material.** { *; }
-dontwarn com.google.android.material.**

# ConstraintLayout koruma
-keep class androidx.constraintlayout.** { *; }
-dontwarn androidx.constraintlayout.**

# AppCompat koruma
-keep class androidx.appcompat.** { *; }
-dontwarn androidx.appcompat.**

# MultiDex koruma
-keep class androidx.multidex.** { *; }
-dontwarn androidx.multidex.**

# SplashScreen koruma
-keep class androidx.core.splashscreen.** { *; }
-dontwarn androidx.core.splashscreen.**

# Ses dosyaları koruma
-keep class **.raw.** { *; }

# Asset dosyaları koruma
-keep class **.assets.** { *; }

# Layout dosyaları koruma
-keep class **.layout.** { *; }

# Drawable dosyaları koruma
-keep class **.drawable.** { *; }

# Anim dosyaları koruma
-keep class **.anim.** { *; }

# String dosyaları koruma
-keep class **.string.** { *; }