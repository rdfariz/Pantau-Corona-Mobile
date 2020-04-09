package org.d3if4127.pantaucorona.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if4127.pantaucorona.data.IndonesiaData
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.kawalcorona.com/")
    .build()

interface IndonesiaService{
    @GET("indonesia/")
    suspend fun getDataIndonesia(): List<IndonesiaData>
}

object CoronaApi {
    val retrofitService = retrofit.create(IndonesiaService::class.java)
}