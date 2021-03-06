package com.pordo.movies2022.ui.list
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pordo.movies2022.databinding.ListFragmentBinding
import com.pordo.movies2022.server.model.Movie
import com.pordo.movies2022.server.model.MoviesList

class ListFragment : Fragment() {

    private lateinit var listviewModel: ListViewModel
    private lateinit var listBinding: ListFragmentBinding
    private  lateinit var moviesAdapter: MoviesAdapter
    private var moviesList: ArrayList<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listBinding = ListFragmentBinding.inflate(inflater, container, false)
        listviewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        moviesAdapter = MoviesAdapter(moviesList, onItemClicked = {onMovieItemClicked(it)})

        listBinding.moviesRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@ListFragment.requireContext())
            adapter = moviesAdapter
            setHasFixedSize(false)
        }

        listviewModel.getMovies()

        listviewModel.loadMoviesDone.observe(viewLifecycleOwner){result ->
            onLoadMoviesDoneSubscribe(result)
        }
    }

    private fun onLoadMoviesDoneSubscribe(moviesList: ArrayList<Movie>?){

        moviesList?.let{moviesAdapter.appendItems(it)}
    }

    private fun onMovieItemClicked(movie: Movie){
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(movie))

    }
}