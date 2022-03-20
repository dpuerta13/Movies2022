package com.pordo.movies2022.server

import com.pordo.movies2022.server.model.MoviesList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("api_key") apikey: String) : MoviesList
}