# ProGuard rules for Mete Eğitim Uygulaması

# Basic optimization
-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers
-dontpreverify
-verbose
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

# Keep - Application. Keep all application classes
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference

# Keep - Views
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# Keep - Activities
-keep class * extends android.app.Activity {
    public void *(android.view.View);
}

# Keep - Enums
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep - Parcelable
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Keep - Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep - Native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep - Custom views
-keepclassmembers class * extends android.view.View {
   void set*(***);
   *** get*();
}

# Keep - Getters and setters in custom classes
-keepclassmembers class * {
    void set*(***);
    *** get*();
}

# Keep - Hilt
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.ActivityComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.FragmentComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.ServiceComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewWithFragmentComponentManager { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ActivityCreator { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$FragmentCreator { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$1 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$2 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$3 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$4 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$5 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$6 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$7 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$8 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$9 { *; }
-keep class * extends dagger.hilt.android.internal.lifecycle.HiltViewModelFactory$ViewModelModule$10 { *; }

# Keep - Room
-keep class * extends androidx.room.RoomDatabase
-keep class * extends androidx.room.Entity
-keepclassmembers class * {
    @androidx.room.* *;
}

# Keep - DataBinding
-keep class androidx.databinding.** { *; }
-keep class * extends androidx.databinding.DataBinderMapper {
    *;
}

# Keep - Navigation
-keep class * extends androidx.navigation.NavController { *; }
-keep class * extends androidx.navigation.fragment.NavHostFragment { *; }

# Keep - Compose
-keep class androidx.compose.runtime.Composable { *; }
-keep class androidx.compose.ui.platform.ComposeView { *; }

# Keep - Lottie
-keep class com.airbnb.lottie.** { *; }

# Keep - Timber
-keep class timber.log.Timber { *; }

# Keep - Gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}

# OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Coroutines
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Kotlin
-keep class kotlin.** { *; }
-dontwarn kotlin.**
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.Metadata

# AndroidX
-keep class androidx.** { *; }
-dontwarn androidx.**

# Google
-keep class com.google.** { *; }
-dontwarn com.google.**

# Support library
-keep class android.support.** { *; }
-dontwarn android.support.**

# Multidex
-keep class androidx.multidex.** { *; }

# WorkManager
-keep class androidx.work.** { *; }

# DataStore
-keep class androidx.datastore.** { *; }

# Coil
-keep class coil.** { *; }
-dontwarn coil.**

# Application specific - keep all classes in your package
-keep class com.metegelistirme.** { *; }
-keep interface com.metegelistirme.** { *; }
-keep enum com.metegelistirme.** { *; }

# Models
-keep class com.metegelistirme.models.** { *; }

# Activities
-keep class com.metegelistirme.activities.** { *; }

# Fragments
-keep class com.metegelistirme.fragments.** { *; }

# ViewModels
-keep class com.metegelistirme.viewmodels.** { *; }

# Repositories
-keep class com.metegelistirme.repositories.** { *; }

# Database
-keep class com.metegelistirme.database.** { *; }

# Utils
-keep class com.metegelistirme.utils.** { *; }

# Services
-keep class com.metegelistirme.services.** { *; }

# Adapters
-keep class com.metegelistirme.adapters.** { *; }

# Keep - Resource classes
-keepclassmembers class **.R$* {
    public static <fields>;
}

# Keep - BuildConfig
-keep class **.BuildConfig { *; }

# Optimization for release builds
-optimizations !code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 3
-allowaccessmodification
-mergeinterfacesaggressively