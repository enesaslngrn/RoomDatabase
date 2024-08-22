package com.example.roomdatabase

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [User::class, Test::class],
    version = 4,
)
@TypeConverters(Converters::class)
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
                ).addMigrations(migrate3to4).build()
                INSTANCE = instance // Eski instance'ı yeni oluşturulan instance'a eşitledik.
                instance
            }
        }
        private val migrate3to4 = object : Migration(3,4){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE test_table ADD COLUMN testImage BLOB NOT NULL")
            }
        }
    }
}