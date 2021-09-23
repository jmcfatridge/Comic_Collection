package com.project.comiccollection.util

import com.project.comiccollection.data.models.requests.Queries
import java.math.BigInteger
import java.security.MessageDigest

private const val pk = "05abcea937fb51bfa7d3ba00a901685d5838327c"
private const val ak = "e29766158761b746cf6f1ebbcfc6a810"

fun generateHash(time: Int) : String {
    val input = time.toString() + pk + ak
    return md5(input)
}

fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}