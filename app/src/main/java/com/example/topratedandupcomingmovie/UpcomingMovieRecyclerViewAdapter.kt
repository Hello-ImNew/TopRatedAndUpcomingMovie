package com.example.topratedandupcomingmovie

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

private const val TAG = "UpcomingMovieRecyclerViewAdapter/"
const val UPCOMING_MOVIE_EXTRA = "UPCOMING_MOVIE_EXTRA"

class UpcomingMovieRecyclerViewAdapter(
    private val context: Context,
    private val upcomingMovies: List<UpcomingMovie>
) : RecyclerView.Adapter<UpcomingMovieRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val upcomingMovieImageView =
            itemView.findViewById<ImageView>(R.id.UpcomingMovieImage)
        private val upcomingMovieTitleTextView =
            itemView.findViewById<TextView>(R.id.UpcomingMovieTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val upcomingMovie = upcomingMovies[absoluteAdapterPosition]

            val intent = Intent(context, UpcomingMovieDetailActivity::class.java)
            intent.putExtra(UPCOMING_MOVIE_EXTRA, upcomingMovie)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity,
                upcomingMovieImageView,
                "UpcomingMovieImage"
            )
            context.startActivity(intent, option.toBundle())
        }

        fun bind(upcomingMovie: UpcomingMovie) {
            upcomingMovieTitleTextView.text = upcomingMovie.title

            Glide.with(context)
                .load(upcomingMovie.posterUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .transform(RoundedCorners(50))
                .into(upcomingMovieImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_upcoming_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val upcomingMovie = upcomingMovies[position]
        holder.bind(upcomingMovie)
    }

    override fun getItemCount(): Int {
        return upcomingMovies.size
    }
}