package com.alankurniadi.myquran

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
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

        //Fragment Layout Connection


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

    private fun checkInternetAccess(): Boolean {
        // register activity with the connectivity manager service
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // if the android version is equal to M
        // or greater we need to use the
        // NetworkCapabilities to check what type of
        // network has the internet connection
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Returns a Network object corresponding to
            // the currently active default data network.
            val network = connectivityManager.activeNetwork ?: return false
            // Representation of the capabilities of an active network.
            val activityNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                // Indicates this network uses a Wi-Fi transport,
                // or WiFi has network connectivity
                activityNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                // Indicates this network uses a Cellular transport. or
                // Cellular has network connectivity
                activityNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                // else return false
                else -> false
            }
        }else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
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