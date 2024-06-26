package movie.example.moviesapp.data.source.remote.dto.upcoming

data class UpcomingMoviesResponse(
    val dates: Dates?,
    val page: Int?,
    val results: List<Result>?,
    val total_pages: Int?,
    val total_results: Int?
)