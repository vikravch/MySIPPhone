package com.vikravch.sampleapp.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vikravch.sampleapp.simple_feature.data.database.dao.UserDao
import com.vikravch.sampleapp.simple_feature.data.database.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}