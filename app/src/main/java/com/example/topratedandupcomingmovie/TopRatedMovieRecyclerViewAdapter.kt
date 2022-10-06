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

private const val TAG = "TopRatedMovieRecyclerViewAdapter/"
const val TOP_RATED_MOVIE_EXTRA = "TOP_RATED_MOVIE_EXTRA"

class TopRatedMovieRecyclerViewAdapter(
    private val context: Context,
    private val topRatedMovies: List<TopRatedMovie>
) : RecyclerView.Adapter<TopRatedMovieRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val topRatedMovieImageView =
            itemView.findViewById<ImageView>(R.id.TopRatedMovieImage)
        private val topRatedMovieTitleTextView =
            itemView.findViewById<TextView>(R.id.TopRatedMovieTitle)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val topRatedMovie = topRatedMovies[absoluteAdapterPosition]

            val intent = Intent(context, TopRatedMovieDetailActivity::class.java)
            intent.putExtra(TOP_RATED_MOVIE_EXTRA, topRatedMovie)
            val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity,
                topRatedMovieImageView,
                "TopRatedMovieImage"
            )
            context.startActivity(intent, option.toBundle())
        }

        fun bind(topRatedMovie: TopRatedMovie) {
            topRatedMovieTitleTextView.text = topRatedMovie.title

            Glide.with(context)
                .load(topRatedMovie.posterUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .transform(RoundedCorners(50))
                .into(topRatedMovieImageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.fragment_top_rated_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topRatedMovie = topRatedMovies[position]
        holder.bind(topRatedMovie)
    }

    override fun getItemCount(): Int {
        return topRatedMovies.size
    }
}