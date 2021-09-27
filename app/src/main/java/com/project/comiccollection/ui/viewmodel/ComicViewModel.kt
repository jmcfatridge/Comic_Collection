package com.project.comiccollection.ui.viewmodel

import android.app.Application
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

    fun fetchComics() = viewModelScope.launch {
        comicRepo.getComicState().collect {
            _comics.value = it
            val comics = it.data
            val data = comics?.data
            val results = data?.results
            if (results != null) {
                setComicList(results)
            }
        }
    }

    private fun setComicList(data: List<Result>) {
        _comicList.value = data
    }
}