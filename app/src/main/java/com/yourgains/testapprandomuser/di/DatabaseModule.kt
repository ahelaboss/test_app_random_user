package com.yourgains.testapprandomuser.di

import android.app.Application
import androidx.room.Room
import com.yourgains.testapprandomuser.data.storage.db.AppDatabase
import com.yourgains.testapprandomuser.data.storage.db.dao.IUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase = Room
        .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
        .addMigrations()
        .build()

    @Provides
    fun provideItemDao(db: AppDatabase): IUserDao = db.userDao()
}