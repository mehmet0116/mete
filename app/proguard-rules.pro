# Room Database
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-keepclassmembers class * {
    @androidx.room.* *;
}

# Gson
-keep class com.google.gson.** { *; }
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keep class kotlinx.coroutines.android.AndroidExceptionPreHandler
-keep class kotlinx.coroutines.android.AndroidDispatcherFactory

# Hilt
-keep class * extends dagger.hilt.android.internal.earlyentrypoint.AggregatedEarlyEntryPoint
-keep class * extends dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories$EntryPoint
-keep class * extends dagger.hilt.internal.GeneratedComponentManagerHolder
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager
-keep class * extends dagger.hilt.android.internal.managers.ActivityComponentManager
-keep class * extends dagger.hilt.android.internal.managers.FragmentComponentManager
-keep class * extends dagger.hilt.android.internal.managers.ServiceComponentManager
-keep class * extends dagger.hilt.android.internal.managers.ViewWithFragmentComponentManager
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ActivityCreatorEntryPoint
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$FragmentCreatorEntryPoint

# Navigation Component
-keep class * extends androidx.navigation.NavArgs
-keep class * extends androidx.navigation.NavDirections

# DataStore
-keep class androidx.datastore.preferences.core.Preferences
-keep class androidx.datastore.preferences.core.PreferencesSerializer

# Lottie
-keep class com.airbnb.lottie.** { *; }
-dontwarn com.airbnb.lottie.**

# Compose
-keep class androidx.compose.runtime.Composer { *; }
-keep class androidx.compose.ui.platform.ComposeView { *; }

# Model sınıfları
-keep class com.metegelistirme.models.** { *; }
-keep class com.metegelistirme.data.** { *; }

# Ses dosyaları
-keep class com.metegelistirme.R$raw { *; }

# ViewBinding
-keep class * extends androidx.viewbinding.ViewBinding {
    public static * inflate(...);
    public static * bind(...);
}

# Okunabilir stack trace için
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Genel optimizasyonlar
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# Android support library
-dontwarn android.support.**
-dontnote android.support.**

# Kotlin
-keep class kotlin.** { *; }
-dontwarn kotlin.**
-keep class kotlinx.** { *; }
-dontwarn kotlinx.**

# OkHttp
-dontwarn okhttp3.**
-dontwarn okio.**

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.module.AppGlideModule
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# Firebase
-dontwarn com.google.firebase.**
-keep class com.google.firebase.** { *; }
-keepnames class com.google.firebase.**

# Çocuk uygulaması için özel
-keep class com.metegelistirme.activities.** { *; }
-keep class com.metegelistirme.fragments.** { *; }
-keep class com.metegelistirme.viewmodels.** { *; }
-keep class com.metegelistirme.adapters.** { *; }
-keep class com.metegelistirme.utils.** { *; }