package com.practice.photoalbum.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.practice.photoalbum.model.MyImage


@Dao
interface ImageDao {


    @Insert
    suspend fun insert(image : MyImage)

    @Query("Update MyImagesTable set imageTitle = :iT, imageDescription = :iU,imageAsString = :iS where imageID =:iD")
    suspend fun update(iT : String,iU : String,iS : String, iD : Int)
    @Delete
    suspend fun delete(image: MyImage)
    @Query("Select * from MyImagesTable order by imageID asc")
    fun getAll() : LiveData<List<MyImage>>
    @Query("DELETE FROM MyImagesTable")
    suspend fun clearImagesTable()


}