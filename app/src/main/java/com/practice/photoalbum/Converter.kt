package com.practice.photoalbum
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Base64
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.ImageBitmap
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

class Converters{


    companion object {

        fun convertToString(bitmap : Bitmap) : String? {


            val stream = ByteArrayOutputStream()
            val resultCompress = bitmap.compress(Bitmap.CompressFormat.PNG,100,stream)

            if (resultCompress){

                val byteArray = stream.toByteArray()
                val imageAsString = Base64.encodeToString(byteArray,Base64.DEFAULT)

                return imageAsString
            }else{

                return null
            }

        }
        fun convertToBitmap(imageAsString : String) : Bitmap{

            val byteArrayAsDecodedImage = Base64.decode(imageAsString,Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArrayAsDecodedImage,0,byteArrayAsDecodedImage.size)

            return bitmap

        }

    }

}