package com.example.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase(){

    abstract fun userDao() : UserDao

    companion object { // Singleton yapısı -> only one instance!
        private var INSTANCE : UserDatabase? = null

        fun getUserDatabase(context: Context) : UserDatabase{
            // Eğer INSTANCE null değilse direkt döndür. Eğer INSTANCE null ise synchronized blok ile oluştur.
            return INSTANCE ?: synchronized(this){
                // INSTANCE null geldi yani ilk kez buradaki synchronized bloğu içinde oluşturuluyor.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance // Eski instance'ı yeni oluşturulan instance'a eşitledik.
                instance
            }
        }
    }
}