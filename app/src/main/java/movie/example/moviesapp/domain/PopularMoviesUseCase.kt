package com.get.costarikamopileapp.domain.usecases

import com.get.costarikamopileapp.data.repository.MoviesRepository
import com.get.costarikamopileapp.data.source.remote.Resource
import kotlinx.coroutines.flow.Flow
import movie.example.moviesapp.data.source.remote.dto.nowplaying.NowPlayingResponse
import movie.example.moviesapp.data.source.remote.dto.popular.PopularMoviesResponse
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend  operator fun invoke(apikey:String,accept:String,language:String,page:Int): Flow<Resource<PopularMoviesResponse>> {
        return moviesRepository.getPopularMovies(apikey,accept,language,page)

    }
}