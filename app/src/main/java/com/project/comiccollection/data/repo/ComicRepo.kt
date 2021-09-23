package com.project.comiccollection.data.repo

import com.project.comiccollection.data.models.requests.Queries
import com.project.comiccollection.data.remote.ComicService
import com.project.comiccollection.util.ApiState
import com.project.comiccollection.util.generateHash
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

@ViewModelScoped
class ComicRepo @Inject constructor(
    private val comicService: ComicService
){
    fun getComicState() = flow {
        emit(ApiState.Loading)
        val comicState = comicService.getComics(getQueries()).getApiState()
        emit(comicState)
    }

    private fun <S> Response<S>.getApiState(): ApiState<S> {
        return  if (isSuccessful) {
            ApiState.Success(body()!!)
        } else ApiState.Failure("Error fetching data!")
    }

    private fun getQueries(): Queries {
        val time = System.currentTimeMillis().toInt()
        return Queries(
            ts = time,
            hash = generateHash(time)
        )
    }
}