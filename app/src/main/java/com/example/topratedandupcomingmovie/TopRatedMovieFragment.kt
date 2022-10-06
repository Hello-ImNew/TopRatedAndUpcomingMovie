package com.example.topratedandupcomingmovie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONException

private const val TAG = "TopRatedMovieFragment/"
private const val API_KEY = BuildConfig.API_KEY
private const val TOP_RATED_MOVIE_URL =
    "https://api.themoviedb.org/3/movie/top_rated?api_key=${API_KEY}"

class TopRatedMovieFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_top_rated_movies_list, container, false)
        val processBar = view.findViewById<View>(R.id.TopRatedMovieProgressBar) as ProgressBar
        val TopRatedMovieRecyclerView = view.findViewById<View>(R.id.TopRatedMovieList) as RecyclerView
        val context = view.context
        TopRatedMovieRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        updateAdapter(processBar, TopRatedMovieRecyclerView, context)
        return view
    }

    private fun updateAdapter(progressBar: ProgressBar, recyclerView: RecyclerView, context: Context) {
        progressBar.visibility = View.VISIBLE
        val client = AsyncHttpClient()
        client.get(TOP_RATED_MOVIE_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                progressBar.visibility = View.INVISIBLE

                Log.e(TAG, "Failed to fetch articles: $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON?) {
                progressBar.visibility = View.INVISIBLE

                Log.i(TAG, "Successfully fetched articles: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        TopRatedMovieResults.serializer(),
                        json?.jsonObject.toString()
                    )

                    parsedJson.results?.let { list ->
                        val topRatedMovies = list

                        val TopRatedMovieAdapter = TopRatedMovieRecyclerViewAdapter(context, topRatedMovies)
                        recyclerView.adapter = TopRatedMovieAdapter
                    }
                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })

    }
}