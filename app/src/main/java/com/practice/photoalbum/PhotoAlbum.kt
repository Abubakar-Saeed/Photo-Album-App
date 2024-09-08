package com.practice.photoalbum

import android.app.Application
import com.practice.photoalbum.repository.ImageRepository
import com.practice.photoalbum.room.ImageDatabase

class PhotoAlbum : Application(){


    private val database by lazy { ImageDatabase.getDatabase(this) }
    val repository by lazy { ImageRepository(database.getImageDao()) }


}