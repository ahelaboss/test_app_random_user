package com.yourgains.testapprandomuser.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yourgains.testapprandomuser.data.entity.db.UserDB
import com.yourgains.testapprandomuser.data.storage.db.dao.IUserDao

@Database(
    version = AppDatabase.VERSION,
    exportSchema = false,
    entities = [UserDB::class]
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "test_random_users.db"
        const val VERSION = 1
    }

    abstract fun userDao(): IUserDao
}