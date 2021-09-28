package com.project.comiccollection.ui.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project.comiccollection.data.adapter.ComicAdapter
import com.project.comiccollection.data.models.Result
import com.project.comiccollection.databinding.FragmentImageBinding
import com.project.comiccollection.ui.viewmodel.ComicViewModel
import com.project.comiccollection.util.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment: Fragment() {

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!
    private val comicViewModel by viewModels<ComicViewModel>()
    private val comicAdapter by lazy { ComicAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentImageBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        comicViewModel.fetchComics()
        setObservers()
        initScroll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setObservers() = with(comicViewModel) {
        comics.observe(viewLifecycleOwner) { state ->
            when(state) {
                is ApiState.Success -> {
                    comicList.observe(viewLifecycleOwner) {
                        if (offset != currentOffset || offset == 0) {
                            loadComics(it)
                        }
                    }
                }
                is ApiState.Failure -> {
                    state.message
                }
                else -> {
                    Log.e("LOADING", "loading")
                }
            }
        }
    }

    private fun initScroll() = with(binding) {
        rvComics.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollHorizontally(1)) {
                    Log.e("SCROLL", "hit end")
                    comicViewModel.updateOffset()
                }
            }
        })
    }

    private fun loadComics(results: List<Result>) = with(binding.rvComics) {
        if (adapter == null)  adapter = comicAdapter
        Log.e("ADAPTER SIZE", "before clear: ${comicAdapter.itemCount}")
        comicAdapter.clear()
        comicViewModel.offset = comicViewModel.currentOffset
        Log.e("ADAPTER SIZE", "after clear: ${comicAdapter.itemCount}")
        comicAdapter.updateList(results)
        Log.e("ADAPTER SIZE", "after update: ${comicAdapter.itemCount}")
    }
}

