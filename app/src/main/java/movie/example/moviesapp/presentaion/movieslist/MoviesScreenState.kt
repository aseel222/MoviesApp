package movie.example.moviesapp.presentaion.movieslist

import movie.example.moviesapp.data.source.remote.dto.nowplaying.Result

data class MoviesScreenState(
    val error: String? = null,
    val nowplayingmovielist: List<Result>?,
    val popularmovielist: List<movie.example.moviesapp.data.source.remote.dto.popular.Result>?,
    val upcomingmovieslist: List<movie.example.moviesapp.data.source.remote.dto.upcoming.Result>?,
    val isloading: Boolean
)
