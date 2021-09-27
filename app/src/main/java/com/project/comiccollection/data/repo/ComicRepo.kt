package com.project.comiccollection.data.repo

import android.util.Log
import com.project.comiccollection.data.models.Comics
import com.project.comiccollection.data.models.Result
import com.project.comiccollection.data.models.requests.Queries
import com.project.comiccollection.data.remote.RemoteDataSource
import com.project.comiccollection.util.ApiState
import com.project.comiccollection.util.BaseApiResponse
import com.project.comiccollection.util.generateHash
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@ViewModelScoped
class ComicRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): BaseApiResponse() {
    suspend fun getComicState(): Flow<ApiState<Comics>> {
        return flow {
            val queries = getQueries().asQueryMap
            emit(safeApiCall { remoteDataSource.getComics(queries) })
        }.flowOn(Dispatchers.IO)
    }

    private fun getQueries(): Queries {
        val time = System.currentTimeMillis().toInt()
        return Queries(
            ts = time,
            hash = generateHash(time)
        )
    }

    private val Queries.asQueryMap: Map<String, Any>
        get() = listOfNotNull(
            "ts" to ts,
            "apikey" to apikey,
            "hash" to hash
        ).toMap()
}