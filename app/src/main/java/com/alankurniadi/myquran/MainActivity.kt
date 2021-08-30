package com.alankurniadi.myquran

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.myquran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainAdapter = MainAdapter(this)
        binding.rvItem.layoutManager = LinearLayoutManager(this)
        binding.rvItem.adapter = mainAdapter

        mainViewModel.listSurah.observe(this, {dataItem ->
            binding.progressBarList.visibility = View.GONE
            //kirim hasilnya ke adapter
            mainAdapter.setData(dataItem)
        })
    }
}