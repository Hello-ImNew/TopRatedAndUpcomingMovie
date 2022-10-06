package com.example.topratedandupcomingmovie

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class UpcomingMovieResults(
    @SerialName("results")
    val results: List<UpcomingMovie>?
)

@Keep
@Serializable
data class UpcomingMovie(
    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("overview")
    val overview: String?,

    @SerialName("release_date")
    val releaseDate: String?,

    @SerialName("title")
    val title: String?,

    @SerialName("popularity")
    val popularity: Float?
) : java.io.Serializable {
    val posterUrl = "https://image.tmdb.org/t/p/w500${posterPath}"
}