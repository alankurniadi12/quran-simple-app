package com.alankurniadi.myquran.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.quran.sutanlab.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)
        }

    }
}