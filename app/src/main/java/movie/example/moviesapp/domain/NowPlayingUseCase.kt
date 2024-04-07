package com.get.costarikamopileapp.domain.usecases

import com.get.costarikamopileapp.data.repository.MoviesRepository
import com.get.costarikamopileapp.data.source.remote.Resource
import kotlinx.coroutines.flow.Flow
import movie.example.moviesapp.data.source.remote.dto.nowplaying.NowPlayingResponse
import javax.inject.Inject

class NowPlayingUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend  operator fun invoke(apikey:String,accept:String,language:String,page:Int): Flow<Resource<NowPlayingResponse>> {
        return moviesRepository.getNowPlayingMovies(apikey,accept,language,page)

    }
}