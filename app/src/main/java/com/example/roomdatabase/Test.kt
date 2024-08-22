package com.example.roomdatabase

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("test_table")
data class Test(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val testName : String,
    val testImage : Bitmap
)
