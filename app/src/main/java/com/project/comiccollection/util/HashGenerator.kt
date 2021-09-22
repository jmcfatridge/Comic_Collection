package com.project.comiccollection.util

import com.project.comiccollection.data.models.requests.Queries
import java.math.BigInteger
import java.security.MessageDigest

private const val pk = "05abcea937fb51bfa7d3ba00a901685d5838327c"

fun generateHash(queries: Queries) : Queries {
    val time = System.currentTimeMillis()
    queries.ts = time.toInt()
    val input = time.toString() + queries.apikey + pk
    queries.hash = md5(input)
    return  queries
}

fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}