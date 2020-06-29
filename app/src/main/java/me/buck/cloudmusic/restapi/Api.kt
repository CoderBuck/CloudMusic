package me.buck.cloudmusic.restapi

import me.buck.cloudmusic.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(Config.REST_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val cloudMusic = retrofit.create(CloudMusicApi::class.java)
}