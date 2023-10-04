package com.derandecker.uhaulcodingchallenge.network

import com.derandecker.uhaulcodingchallenge.models.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://jsonplaceholder.typicode.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface UserNetworkApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}

object UserApi {
    val retrofitService: UserNetworkApi by lazy { retrofit.create(UserNetworkApi::class.java) }
}
