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

class UpcomingMovieDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_upcoming)

        val movieImage = findViewById<ImageView>(R.id.UpcomingMovieImageDetail)
        val movieTitle = findViewById<TextView>(R.id.UpcomingMovieTitleDetail)
        val movieReleaseDate = findViewById<TextView>(R.id.UpcomingMovieReleaseDateDetail)
        val moviePopularity = findViewById<TextView>(R.id.UpcomingMoviePopularityDetail)
        val movieOverview = findViewById<TextView>(R.id.UpcomingMovieOverviewDetail)
        val movieView = findViewById<View>(R.id.UpcomingMovieDetailView)

        val upcomingMovie = intent.getSerializableExtra(UPCOMING_MOVIE_EXTRA) as UpcomingMovie

        movieTitle.text = upcomingMovie.title
        movieOverview.text = upcomingMovie.overview

        val bold = StyleSpan(android.graphics.Typeface.BOLD)

        var word = SpannableString("Release Date: ${upcomingMovie.releaseDate}")
        word.setSpan(bold, 0, 13, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        movieReleaseDate.text = word

        word = SpannableString("Popularity: ${upcomingMovie.popularity.toString()}")
        word.setSpan(bold, 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        moviePopularity.text = word

        Glide.with(this)
            .load(upcomingMovie.posterUrl)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(movieImage)

        movieView.setOnClickListener {
            this.supportFinishAfterTransition()
        }
    }
}