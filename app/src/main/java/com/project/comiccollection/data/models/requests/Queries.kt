package com.project.comiccollection.data.models.requests

data class Queries(
    var ts: Int,
    val apikey: String = "e29766158761b746cf6f1ebbcfc6a810",
    var hash: String,
    val limit: Int = 20,
    var offset: Int
)
