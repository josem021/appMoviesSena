package com.example.appmoviessena

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appmoviessena.DataAPI.RetrofitServiceFactory
import com.example.appmoviessena.DataAPI.model.ResultM
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class FirstFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var recyclerView: RecyclerView
    private var movies: List<ResultM> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        loadMovies()
        return view
    }

    private fun loadMovies() {
        val service = RetrofitServiceFactory.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.listPopularMovies("410ad842c1c6d74701740e81046314da")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movies = response.body()?.results ?: listOf()
                    movieAdapter = MovieAdapter(movies) { movie ->
                        val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(movie.id, movie.overview)
                        findNavController().navigate(action)
                    }
                    recyclerView.adapter = movieAdapter
                }
            }
        }
    }
}

