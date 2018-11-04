package com.example.guillaume.magic.services.api

import com.example.guillaume.magic.Result
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MagicApiService {
    @GET("cards")
    fun allCards(): Observable<Result>

    companion object {
        fun create(): MagicApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.magicthegathering.io/v1/")
                    .build()

            return retrofit.create(MagicApiService::class.java)
        }
    }
}