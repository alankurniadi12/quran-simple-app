package com.alankurniadi.myquran.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("surah")
    fun getListSuratQuran():Call<QuranModelResponse>

    @GET("surah/{number}")
    fun getAyat(@Path("number") number: Int): Call<QuranSurahModelResponse>
}