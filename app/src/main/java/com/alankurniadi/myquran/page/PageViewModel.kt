package com.alankurniadi.myquran.page

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alankurniadi.myquran.api.ApiConfig
import com.alankurniadi.myquran.api.QuranSurahModelResponse
import com.alankurniadi.myquran.api.VersesItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PageViewModel : ViewModel() {

    private val _listItemAyat = MutableLiveData<ArrayList<VersesItem>>()
    val listItemAyat: LiveData<ArrayList<VersesItem>> = _listItemAyat

    private val _dataSurah = MutableLiveData<QuranSurahModelResponse>()
    val dataSurah: LiveData<QuranSurahModelResponse> = _dataSurah

    companion object {
        private const val TAG = "PageViewModel"
    }

    fun findListAyat(number: Int) {
        val client = ApiConfig.getApiService().getAyat(number)
        client.enqueue(object : Callback<QuranSurahModelResponse> {
            override fun onResponse(
                call: Call<QuranSurahModelResponse>,
                response: Response<QuranSurahModelResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body()?.message}")
                    Log.d(TAG, "onResponse: ${response.body()?.data}")
                    _listItemAyat.value = response.body()?.data?.verses as ArrayList<VersesItem>
                    _dataSurah.value = response.body()

                } else {
                    Log.d(TAG, "onResponse: ${response.body()?.status}")
                }
            }

            override fun onFailure(call: Call<QuranSurahModelResponse>, t: Throwable) {
                Log.e(TAG, "onFailur: ${t.message}")
            }

        })
    }
}