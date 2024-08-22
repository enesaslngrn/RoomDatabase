package com.example.roomdatabase

import androidx.lifecycle.LiveData

class UserRepository(private val userDao : UserDao) {

    val getAllUsers : LiveData<List<User>> = userDao.getAllUsers()
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    suspend fun deleteAllUsers() = userDao.deleteAllUsers()

    // Test
    val getAllTests : LiveData<List<Test>> = userDao.getAllTests()
    suspend fun insertTest(test: Test) = userDao.insertTest(test)

}