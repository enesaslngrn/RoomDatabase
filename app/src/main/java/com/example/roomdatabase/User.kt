package com.example.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_table") // Tablo'nun ismi
data class User(
    @PrimaryKey(autoGenerate = true) // Room library otomatik olarak bizim column'lar için random id üretecek.
    val id : Int = 0,
    val firstName : String,
    val secondName : String,
    val age : Int
)
