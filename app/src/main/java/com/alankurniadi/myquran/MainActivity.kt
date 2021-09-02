package com.alankurniadi.myquran

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.myquran.api.DataItem
import com.alankurniadi.myquran.databinding.ActivityMainBinding
import com.google.android.material.appbar.CollapsingToolbarLayout

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mainViewModel.listSurah.observe(this, {dataItem ->
            binding.progressBarList.visibility = View.GONE
            mainAdapter = MainAdapter(this, dataItem)
            binding.rvItem.layoutManager = LinearLayoutManager(this)
            binding.rvItem.adapter = mainAdapter

        })
        binding.searchBar.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchSurahName(newText)
        }
        return false
    }

    private fun searchSurahName(query: String) {
        mainAdapter.filter.filter(query)
    }
}