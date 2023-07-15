package com.erkindilekci.multimodulenewsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.erkindilekci.common_utils.Activities
import com.erkindilekci.common_utils.Navigator
import com.erkindilekci.multi_modulenewsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var provider: Navigator.Provider

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provider.getActivities(Activities.NewsActivity).navigate(this)
    }
}
