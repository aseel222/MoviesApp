package movie.example.moviesapp.presentaion.moviesdetails

import movie.example.moviesapp.data.source.remote.dto.moviedetails.MoviesDetailsResponse

data class MoviesDetailsScreenState(
    val error: String? = null,
    val moviesDetailsResponse: MoviesDetailsResponse?,
    val isloading: Boolean
)
