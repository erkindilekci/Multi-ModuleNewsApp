package com.erkindilekci.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.erkindilekci.common_utils.Activities
import com.erkindilekci.common_utils.Navigator
import com.erkindilekci.presentation.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    @Inject
    lateinit var provider: Navigator.Provider

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, NewsActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var _binding: ActivityNewsBinding? = null
    private val binding: ActivityNewsBinding get() = _binding!!

    private val newsViewModel: NewsViewModel by viewModels()
    private val newsAdapter = NewsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setObservers()
    }

    private fun initView() {
        binding.rvArticles.adapter = newsAdapter
        binding.ivGoToSearch.setOnClickListener {
            provider.getActivities(Activities.SearchActivity).navigate(this)
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            newsViewModel.state.collectLatest { state ->
                if (state.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (state.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    println("Error: ${state.error}")
                    Toast.makeText(this@NewsActivity, state.error, Toast.LENGTH_LONG).show()
                }
                state.articles?.let { articles ->
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.setData(articles)
                }
            }
        }
    }
}

object GoToNewsActivity : Navigator {
    override fun navigate(activity: Activity) {
        NewsActivity.launchActivity(activity)
    }
}
