package com.alankurniadi.myquran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alankurniadi.myquran.api.ApiConfig
import com.alankurniadi.myquran.api.DataItem
import com.alankurniadi.myquran.api.QuranModelResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listSurah = MutableLiveData<ArrayList<DataItem>>()
    val listSurah: LiveData<ArrayList<DataItem>> = _listSurah
    private val _namaSurah = MutableLiveData<QuranModelResponse>()
    val namaSurah: LiveData<QuranModelResponse> = _namaSurah

    companion object {
        private const val TAG = "MainViewModel"
    }

    init {
        findListSurah()
    }

    fun findListSurah() {
        val client = ApiConfig.getApiService().getListSuratQuran()
        client.enqueue(object : Callback<QuranModelResponse> {
            override fun onResponse(
                call: Call<QuranModelResponse>,
                response: Response<QuranModelResponse>
            ) {
                if (response.isSuccessful) {
                    _listSurah.value = response.body()?.data as ArrayList<DataItem>?
                    _namaSurah.value = response.body()
                    Log.d(TAG, "onResponse: ${response.body()?.message}")
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<QuranModelResponse>, t: Throwable) {
                Log.d(TAG, "onFailur: ${t.message}")
            }


        })
    }
}