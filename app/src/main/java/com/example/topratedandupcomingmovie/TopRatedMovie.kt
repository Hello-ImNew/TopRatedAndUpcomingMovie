package com.example.topratedandupcomingmovie

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class TopRatedMovieResults(
    @SerialName("results")
    val results: List<TopRatedMovie>?
)

@Keep
@Serializable
data class TopRatedMovie(
    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("overview")
    val overview: String?,

    @SerialName("release_date")
    val releaseDate: String?,

    @SerialName("title")
    val title: String?,

    @SerialName("vote_average")
    val voteAverage: Float?,

    @SerialName("vote_count")
    val voteCount: Int?
) : java.io.Serializable {
    val posterUrl = "https://image.tmdb.org/t/p/w500${posterPath}"
}