package com.project.comiccollection.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val comicService: ComicService) {
    suspend fun getComics(data: Map<String, Any>) = comicService.getComics(data)
}