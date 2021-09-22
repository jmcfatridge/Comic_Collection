package com.project.comiccollection.data.remote

import com.project.comiccollection.data.models.Comics
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ComicService {
    @GET("/v1/public/comics")
    suspend fun getComics(
        @QueryMap options: Map<String, @JvmSuppressWildcards Any>
    ): Response<Comics>
}