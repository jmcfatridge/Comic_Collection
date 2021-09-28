package com.project.comiccollection.data.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.comiccollection.data.models.Data
import com.project.comiccollection.data.models.Image
import com.project.comiccollection.data.models.Result
import com.project.comiccollection.data.models.Thumbnail
import com.project.comiccollection.databinding.ItemComicBinding
import com.project.comiccollection.util.loadWithGlide

class ComicAdapter(
    private val comicList: MutableList<Result> = mutableListOf()
): RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ComicViewHolder.getInstance(parent)

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.loadComic(comicList[position])
    }

    override fun getItemCount() = comicList.size

    fun updateList(results: List<Result>) {
        val positionStart = comicList.size
        comicList.addAll(results)
        notifyItemRangeInserted(positionStart, results.size)
    }

    fun clear() {
        comicList.clear()
        val listSize = comicList.size
        notifyItemRangeRemoved(0, listSize)
    }

    class ComicViewHolder(
        private val binding: ItemComicBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun loadComic(result: Result) = with(binding) {
            val image = result.thumbnail?.path?.replace("http", "https") + "/standard_large." + result.thumbnail?.extension
            itemName.text = result.name
            itemComic.loadWithGlide(image)
        }

        companion object {
            fun getInstance(parent: ViewGroup): ComicViewHolder {
                val binding = ItemComicBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return ComicViewHolder(binding)
            }
        }
    }
}