package com.project.comiccollection.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl

fun ImageView.loadWithGlide(url: String) {
    val glideUrl = GlideUrl(url)
    Glide.with(context).load(glideUrl).into(this)
}