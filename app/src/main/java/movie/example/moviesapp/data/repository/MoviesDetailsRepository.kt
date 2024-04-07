package com.get.costarikamopileapp.data.repository

import com.get.costarikamopileapp.data.source.remote.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import movie.example.moviesapp.data.source.local.Constants.page
import movie.example.moviesapp.data.source.remote.MoviesApiService
import movie.example.moviesapp.data.source.remote.dto.moviedetails.MoviesDetailsResponse
import movie.example.moviesapp.data.source.remote.dto.nowplaying.NowPlayingResponse
import movie.example.moviesapp.data.source.remote.dto.popular.PopularMoviesResponse
import movie.example.moviesapp.data.source.remote.dto.upcoming.UpcomingMoviesResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class MoviesDetailsRepository @Inject constructor(private val apiService: MoviesApiService){
    suspend fun getMoviesDetails(id:Int,apikey:String,accept:String,language:String): Flow<Resource<MoviesDetailsResponse>> = flow {
        emit(Resource.Loading())
        try {
          emit(Resource.Success(apiService.getMovieDetails(id,apikey,accept,language)))

        }catch (e:HttpException){
         emit(Resource.Error(message = "Oops, something went wrong!"))

        }
        catch (ex:IOException){
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        }
    }



}