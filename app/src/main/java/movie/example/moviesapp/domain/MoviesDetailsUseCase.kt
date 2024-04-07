package com.get.costarikamopileapp.domain.usecases

import com.get.costarikamopileapp.data.repository.MoviesDetailsRepository
import com.get.costarikamopileapp.data.repository.MoviesRepository
import com.get.costarikamopileapp.data.source.remote.Resource
import kotlinx.coroutines.flow.Flow
import movie.example.moviesapp.data.source.local.Constants.apikey
import movie.example.moviesapp.data.source.local.Constants.page
import movie.example.moviesapp.data.source.remote.dto.moviedetails.MoviesDetailsResponse
import movie.example.moviesapp.data.source.remote.dto.nowplaying.NowPlayingResponse
import javax.inject.Inject

class MoviesDetailsUseCase @Inject constructor(private val moviesDetailsRepository: MoviesDetailsRepository) {
    suspend  operator fun invoke(id:Int,apikey:String,accept:String,language:String): Flow<Resource<MoviesDetailsResponse>> {
        return moviesDetailsRepository.getMoviesDetails(id,apikey,accept,language)

    }
}