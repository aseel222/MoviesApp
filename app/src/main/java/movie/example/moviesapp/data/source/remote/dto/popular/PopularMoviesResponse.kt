package movie.example.moviesapp.data.source.remote.dto.popular



data class PopularMoviesResponse(
    val page: Int?,
    val results: List<Result>?,
    val totalPages: Int?,
    val totalResults: Int?
)