package com.practice.photoalbum.repository

import androidx.lifecycle.LiveData
import com.practice.photoalbum.model.MyImage
import com.practice.photoalbum.room.ImageDao

class ImageRepository(private val imageDao: ImageDao) {

    private  var imageList : LiveData<List<MyImage>> = imageDao.getAll()



    suspend fun insert(image: MyImage){

        imageDao.insert(image)
    }
    suspend fun delete(image: MyImage){

        imageDao.delete(image)
    }
    suspend fun update(iT : String, iU : String, iS : String,iD : Int){

        imageDao.update(iT,iU,iS,iD)
    }
    suspend fun clearTable(){

        return imageDao.clearImagesTable()
    }
    fun getAll():LiveData<List<MyImage>>{

        return imageList
    }

}