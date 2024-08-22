package com.example.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert

@Dao
interface UserDao { // Data access object (DAO) - CRUD işlemlerinden sorumlu

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Ya da Upsert diye bir şey var.
    suspend fun insertUser(user : User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    // Test
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTest(test: Test)

    @Query("SELECT * FROM test_table ORDER BY id ASC")
    fun getAllTests(): LiveData<List<Test>>

}