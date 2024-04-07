package movie.example.moviesapp.data.source.remote

import movie.example.moviesapp.data.source.remote.dto.moviedetails.MoviesDetailsResponse
import movie.example.moviesapp.data.source.remote.dto.nowplaying.NowPlayingResponse
import movie.example.moviesapp.data.source.remote.dto.popular.PopularMoviesResponse
import movie.example.moviesapp.data.source.remote.dto.upcoming.UpcomingMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET("now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") api_key: String,
        @Header("accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): NowPlayingResponse

    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") api_key: String,
        @Header("accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): PopularMoviesResponse

    @GET("upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") api_key: String,
        @Header("accept") accept: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): UpcomingMoviesResponse

    @GET("{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String,
        @Header("accept") accept: String,
        @Query("language") language: String

    ): MoviesDetailsResponse

}