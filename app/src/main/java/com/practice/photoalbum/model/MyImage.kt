package com.practice.photoalbum.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "MyImagesTable")
class MyImage(

    val imageTitle: String,
    val imageDescription: String,
    val imageAsString: String,

    )  {

    @PrimaryKey(autoGenerate = true)
    var imageID = 0

}