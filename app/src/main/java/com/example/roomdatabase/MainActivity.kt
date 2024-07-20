package com.example.roomdatabase

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomdatabase.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.btnAddUser.setOnClickListener { addUser() }
        binding.btnUpdateUser.setOnClickListener { updateUser() }
        binding.btnDeleteUser.setOnClickListener { deleteUser() }
        binding.btnDeleteAll.setOnClickListener { deleteAllUsers() }
        binding.btnShowUsers.setOnClickListener { showUsers() }
    }

    private fun addUser() {
        val firstName = binding.etFirstName.text.toString()
        val secondName = binding.etSecondName.text.toString()
        val age = binding.etAge.text.toString().toIntOrNull()
        if (firstName.isNotBlank() && secondName.isNotBlank() && age != null) {
            val user = User(firstName = firstName, secondName = secondName, age = age)
            userViewModel.insertUser(user)
        } else {
            Toast.makeText(this, "Please enter valid details", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUser() {
        val userId = binding.etUserId.text.toString().toIntOrNull()
        val firstName = binding.etFirstName.text.toString()
        val secondName = binding.etSecondName.text.toString()
        val age = binding.etAge.text.toString().toIntOrNull()
        if (userId != null && firstName.isNotBlank() && secondName.isNotBlank() && age != null) {
            // Assume we're updating the user with the provided ID
            userViewModel.getAllUsers.value?.find { it.id == userId }?.let {
                val updatedUser = it.copy(firstName = firstName, secondName = secondName, age = age)
                userViewModel.updateUser(updatedUser)
            }
        } else {
            Toast.makeText(this, "Please enter valid details and a valid user ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteUser() {
        val userId = binding.etUserId.text.toString().toIntOrNull()
        if (userId != null) {
            // Assume we're deleting the user with the provided ID
            userViewModel.getAllUsers.value?.find { it.id == userId }?.let {
                userViewModel.deleteUser(it)
            }
        } else {
            Toast.makeText(this, "Please enter a valid user ID", Toast.LENGTH_SHORT).show()
        }
    }

    private fun deleteAllUsers() {
        userViewModel.deleteAllUsers()
    }

    private fun showUsers() {
        userViewModel.getAllUsers.observe(this) { users ->
            val userText =
                users.joinToString(separator = "\n") { "${it.id}: ${it.firstName} ${it.secondName}, Age: ${it.age}" }
            binding.tvUser.text = userText
        }
    }
}

