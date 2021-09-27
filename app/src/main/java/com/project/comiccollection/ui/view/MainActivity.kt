package com.project.comiccollection.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.project.comiccollection.R
import com.project.comiccollection.data.models.Comics
import com.project.comiccollection.data.models.Data
import com.project.comiccollection.data.repo.ComicRepo
import com.project.comiccollection.databinding.ActivityMainBinding
import com.project.comiccollection.ui.viewmodel.ComicViewModel
import com.project.comiccollection.util.ApiState
import com.project.comiccollection.util.loadWithGlide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNavigation.setupWithNavController(navHostFragment.navController)
    }
}