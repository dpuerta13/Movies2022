package com.pordo.movies2022.server.moviesrepository

import com.pordo.movies2022.server.MovieDB

class MoviesRepository {

    private val apikey = "38d39cb4c80188e0a494f18c28fd746a"

    suspend fun getMovies() = MovieDB.retrofit.getTopRated(apikey)
}