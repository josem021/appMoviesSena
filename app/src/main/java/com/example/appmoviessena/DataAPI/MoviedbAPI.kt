package com.example.appmoviessena.DataAPI

import com.example.appmoviessena.DataAPI.model.ResultMovies
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface MoviedbAPI {
    @GET("3/movie/popular")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<ResultMovies>
}
object RetrofitServiceFactory {
    fun makeRetrofitService(): MoviedbAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MoviedbAPI::class.java)
    }
}