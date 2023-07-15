package com.erkindilekci.search_presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.erkindilekci.common_utils.Navigator
import com.erkindilekci.search_presentation.Constants.APIKEY
import com.erkindilekci.search_presentation.Constants.END_DATE
import com.erkindilekci.search_presentation.Constants.QUERY
import com.erkindilekci.search_presentation.Constants.START_DATE
import com.erkindilekci.search_presentation.databinding.ActivitySearchBinding
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import java.text.SimpleDateFormat

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, SearchActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var _binding: ActivitySearchBinding? = null
    private val binding: ActivitySearchBinding get() = _binding!!

    private val searchViewModel: SearchViewModel by viewModels()
    private val searchAdapter = SearchAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setObservers()
    }

    private fun initView() {
        binding.rvSearch.adapter = searchAdapter
        binding.searchTitle.doAfterTextChanged {
            val map = mutableMapOf<String, String>()
            map[APIKEY] = "36091782782f4cd4831d584627cabe6e"
            map[QUERY] = it.toString()
            searchViewModel.getSearchedArticles(map)
        }
        binding.ivRange.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.dateRangePicker().build()
            datePicker.show(this.supportFragmentManager, "range picker")
            datePicker.addOnPositiveButtonClickListener {
                val start = changeDateFormat(it.first)
                val end = changeDateFormat(it.second)

                val map = mutableMapOf<String, String>()
                map[APIKEY] = "36091782782f4cd4831d584627cabe6e"
                map[QUERY] = binding.searchTitle.text.toString()
                map[START_DATE] = start
                map[END_DATE] = end

                searchViewModel.getSearchedArticles(map)
            }
        }
    }

    private fun changeDateFormat(long: Long?): String {
        return try {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
            simpleDateFormat.format(long)
        } catch (e: Exception) {
            ""
        }
    }

    private fun setObservers() {
        lifecycleScope.launchWhenCreated {
            searchViewModel.state.collectLatest { state ->
                if (state.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (state.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    println("Error: ${state.error}")
                    Toast.makeText(this@SearchActivity, state.error, Toast.LENGTH_LONG).show()
                }
                state.searchedArticles?.let { articles ->
                    binding.progressBar.visibility = View.GONE
                    searchAdapter.setData(articles)
                }
            }
        }
    }
}

object GoToSearchActivity : Navigator {
    override fun navigate(activity: Activity) {
        SearchActivity.launchActivity(activity)
    }
}
