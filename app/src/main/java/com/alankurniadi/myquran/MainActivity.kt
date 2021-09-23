package com.alankurniadi.myquran

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.alankurniadi.myquran.connection.CheckInternetAccess
import com.alankurniadi.myquran.connection.ConnectionStateFragment
import com.alankurniadi.myquran.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mainAdapter: MainAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Periksa Koneksi internet
        val checkNet = CheckInternetAccess(this)
        if (checkNet.checkInternetAccess()) {
            mainViewModel.listSurah.observe(this, {dataItem ->
                binding.progressBarList.visibility = View.GONE
                mainAdapter = MainAdapter(this, dataItem)
                binding.rvItem.layoutManager = LinearLayoutManager(this)
                binding.rvItem.adapter = mainAdapter

            })
        }else {
            binding.progressBarList.visibility = View.GONE
            val mFragmentManager = supportFragmentManager
            val mConnectionStateFragment = ConnectionStateFragment()
            val fragment = mFragmentManager.findFragmentByTag(ConnectionStateFragment::class.java.simpleName)
            if (fragment !is ConnectionStateFragment) {
                mFragmentManager
                    .beginTransaction()
                    .add(R.id.frame_connection, mConnectionStateFragment, ConnectionStateFragment::class.java.simpleName)
                    .commit()
            }
        }
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        isDestroyed
    }
}