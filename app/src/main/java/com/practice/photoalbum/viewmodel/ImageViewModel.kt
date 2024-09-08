package com.practice.photoalbum.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.practice.photoalbum.model.MyImage
import com.practice.photoalbum.repository.ImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImageViewModel (private val  imageRepository : ImageRepository ): ViewModel() {

    private val _imageList : LiveData<List<MyImage>> = imageRepository.getAll()



    fun insert(image: MyImage){

        viewModelScope.launch ( Dispatchers.IO) {

            imageRepository.insert(image)

        }
    }
    fun update(iT : String, iU : String, iS : String,iD : Int){

        viewModelScope.launch ( Dispatchers.IO) {

            imageRepository.update(iT,iU,iS,iD)

        }
    }
    fun delete(image: MyImage){

        viewModelScope.launch ( Dispatchers.IO) {

            imageRepository.delete(image)

        }
    }

    fun clear(){
        viewModelScope.launch ( Dispatchers.IO) {

            imageRepository.clearTable()

        }

    }

    fun getAllImage(): LiveData<List<MyImage>>{

        return _imageList
    }



}
class ImageViewModelFactory(private val repository: ImageRepository) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            return ImageViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}