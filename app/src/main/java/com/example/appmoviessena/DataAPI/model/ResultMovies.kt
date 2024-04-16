package com.example.appmoviessena.DataAPI.model

data class ResultMovies(
    val page: Int,
    val results: List<ResultM>,
    val total_pages: Int,
    val total_results: Int
)