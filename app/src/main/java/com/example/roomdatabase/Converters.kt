package com.example.roomdatabase

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    // Bitmap -> ByteArray (Room'a eklemek için)
    // ByteArray -> Bitmap (Room'dan çekip kullanıcıya gösterirken)

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap) : ByteArray{
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream)
        return outputStream.toByteArray()
    }
    @TypeConverter
    fun toBitmap(byteArray: ByteArray) : Bitmap{
        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
    }
}