package com.yourgains.testapprandomuser.data.storage.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yourgains.testapprandomuser.data.entity.db.UserDB

@Dao
interface IUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(model: UserDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<UserDB>)

    @Query("SELECT * FROM users")
    suspend fun select(): List<UserDB>

    @Query("DELETE FROM users")
    suspend fun clear()
}