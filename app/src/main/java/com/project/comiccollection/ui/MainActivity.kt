package com.project.comiccollection.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.project.comiccollection.R
import com.project.comiccollection.data.repo.ComicRepo
import com.project.comiccollection.databinding.ActivityMainBinding
import com.project.comiccollection.ui.viewmodel.ComicViewModel
import com.project.comiccollection.util.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val comicViewModel by viewModels<ComicViewModel>()
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fetchData()
    }

    private fun fetchData() {
        comicViewModel.fetchComics()
        comicViewModel.comics.observe(this) { response ->
            when(response) {
                is ApiState.Success -> {
                    Log.e("API TEST", "${response.data}")
                }
                is ApiState.Failure -> {
                    Log.e("API TEST", "${response.message}")
                }
                else -> {
                    Log.e("API TEST", "loading")
                }
            }
        }
    }
}