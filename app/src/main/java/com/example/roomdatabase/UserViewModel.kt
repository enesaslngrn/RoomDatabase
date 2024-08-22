package com.example.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val getAllUsers : LiveData<List<User>>
    val getAllTests : LiveData<List<Test>>
    private var repository : UserRepository

    init {
        val userDao = UserDatabase.getUserDatabase(application).userDao()
        repository = UserRepository(userDao)
        getAllUsers = repository.getAllUsers
        getAllTests = repository.getAllTests
    }

    fun insertUser(user : User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

    // Test
    fun insertTest(test: Test){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTest(test)
        }
    }
    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }
}