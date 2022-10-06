package com.example.topratedandupcomingmovie

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class TopRatedMovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_top_rated)

        val movieImage = findViewById<ImageView>(R.id.TopRatedMovieImageDetail)
        val movieTitle = findViewById<TextView>(R.id.TopRatedMovieTitleDetail)
        val movieReleaseDate = findViewById<TextView>(R.id.TopRatedMovieReleaseDateDetail)
        val movieRating = findViewById<TextView>(R.id.TopRatedMovieRatingDetail)
        val movieOverview = findViewById<TextView>(R.id.TopRatedMovieOverviewDetail)
        val movieView = findViewById<View>(R.id.TopRatedMovieDetailView)

        val topRatedMovie = intent.getSerializableExtra(TOP_RATED_MOVIE_EXTRA) as TopRatedMovie

        movieTitle.text = topRatedMovie.title
        movieOverview.text = topRatedMovie.overview

        val bold = StyleSpan(Typeface.BOLD)

        var word = SpannableString("Release Date: ${topRatedMovie.releaseDate}")
        word.setSpan(bold, 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        movieReleaseDate.text = word

        word = SpannableString("Rating: ${topRatedMovie.voteAverage.toString()}/10, ${topRatedMovie.voteCount.toString()} votes")
        word.setSpan(bold, 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        movieRating.text = word

        Glide.with(this)
            .load(topRatedMovie.posterUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(movieImage)

        movieView.setOnClickListener {
            this.supportFinishAfterTransition()
        }
    }
}