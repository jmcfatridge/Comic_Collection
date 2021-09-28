package com.project.comiccollection.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.project.comiccollection.data.models.Comics
import com.project.comiccollection.data.models.Result
import com.project.comiccollection.data.repo.ComicRepo
import com.project.comiccollection.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ComicViewModel @Inject constructor(
    private val comicRepo: ComicRepo,
    application: Application
): AndroidViewModel(application) {

    private val _comics: MutableLiveData<ApiState<Comics>> = MutableLiveData()
    val comics: LiveData<ApiState<Comics>> = _comics

    private val _comicList: MutableLiveData<List<Result>> = MutableLiveData()
    val comicList: LiveData<List<Result>> = _comicList

    private var loading = true

    fun fetchComics() = viewModelScope.launch {
        comicRepo.getComicState().collect {
            loading = false
            _comics.value = it
            val comics = it.data
            val data = comics?.data
            val results = data?.results
            if (results != null) {
                setComicList(results)
            }
        }
    }

    var offset = 0
    var currentOffset = 0

    private fun setComicList(data: List<Result>) {
        Log.e("SET COMICLIST", "set data ${data.size}")
        Log.e("SET COMICLIST", "set data $data")
        _comicList.value = data
    }

    fun updateOffset() {
        if (!loading) {
            loading = true
            currentOffset += 20
            comicRepo.offset = currentOffset
            Log.e("UPDATE-OFFSET", "${comicRepo.offset}")
            fetchComics()
        }
    }
}