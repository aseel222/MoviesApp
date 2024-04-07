package movie.example.moviesapp.data.source.remote.dto.nowplaying


data class NowPlayingResponse(

    val dates: Dates?,

    val page: Int?,

    val results: List<Result>?,

    val totalPages: Int?,

    val totalResults: Int?
)