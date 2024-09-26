package com.vikravch.sampleapp.room_database

import android.content.Context
import androidx.room.Room
import com.vikravch.sampleapp.simple_feature.data.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseComponent {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext applicationContext: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(
        database: AppDatabase
    ): UserDao = database.userDao()
}