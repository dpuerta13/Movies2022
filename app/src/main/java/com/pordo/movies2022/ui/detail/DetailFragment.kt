package com.pordo.movies2022.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.pordo.movies2022.R
import com.pordo.movies2022.databinding.DetailFragmentBinding
import com.pordo.movies2022.server.model.Movie
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var detailBinding: DetailFragmentBinding
    private val args: DetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailBinding = DetailFragmentBinding.inflate(inflater, container,false)
        detailViewModel= ViewModelProvider(this)[DetailViewModel::class.java]
        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie: Movie = args.movie
        with(detailBinding){
            movieTitleTextView.text = movie.title
            releaseDateTextView.text = movie.releaseDate
            voteAverageTextView.text = movie.voteAverage.toString()
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.posterPath).into(posterImageView)
            summaryTextView.text = movie.overview
        }
    }

}