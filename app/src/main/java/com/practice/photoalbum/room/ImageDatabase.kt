package com.practice.photoalbum.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.practice.photoalbum.model.MyImage

@Database(entities = [MyImage::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {
    // Define abstract methods to access the DAOs
    abstract fun getImageDao(): ImageDao

    companion object {

        @Volatile
        private var INSTANCE: ImageDatabase? = null

        fun getDatabase(context: Context): ImageDatabase {
            // Return the existing INSTANCE if it's not null, otherwise create a new one
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImageDatabase::class.java,
                    "image_database"
                ).build()
                INSTANCE = instance
                // Return the newly created instance
                instance
            }
        }
    }
}
