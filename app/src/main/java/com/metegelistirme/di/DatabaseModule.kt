package com.metegelistirme.di

import android.content.Context
import com.metegelistirme.database.AppDatabase
import com.metegelistirme.database.dao.ProgressDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideProgressDao(database: AppDatabase): ProgressDao {
        return database.progressDao()
    }
}

