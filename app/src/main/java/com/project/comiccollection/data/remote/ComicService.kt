package com.project.comiccollection.data.remote

import com.project.comiccollection.data.models.Comics
import com.project.comiccollection.data.models.requests.Queries
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ComicService {
    @GET("v1/public/comics")
    suspend fun getComics(
        @QueryMap options: Queries
    ): Response<Comics>
}