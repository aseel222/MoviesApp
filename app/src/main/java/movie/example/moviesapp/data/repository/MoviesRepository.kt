package com.get.costarikamopileapp.data.repository

import com.get.costarikamopileapp.data.source.remote.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import movie.example.moviesapp.data.source.remote.MoviesApiService
import movie.example.moviesapp.data.source.remote.dto.nowplaying.NowPlayingResponse
import movie.example.moviesapp.data.source.remote.dto.popular.PopularMoviesResponse
import movie.example.moviesapp.data.source.remote.dto.upcoming.UpcomingMoviesResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class MoviesRepository @Inject constructor( private val apiService: MoviesApiService){
    suspend fun getNowPlayingMovies(apikey:String,accept:String,language:String,page:Int): Flow<Resource<NowPlayingResponse>> = flow {
        emit(Resource.Loading())
        try {
          emit(Resource.Success(apiService.getNowPlayingMovies(apikey,accept,language,page)))

        }catch (e:HttpException){
         emit(Resource.Error(message = "Oops, something went wrong!"))

        }
        catch (ex:IOException){
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        }
    }
    suspend fun getPopularMovies(apikey:String,accept:String,language:String,page:Int): Flow<Resource<PopularMoviesResponse>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(apiService.getPopularMovies(apikey,accept,language,page)))

        }catch (e:HttpException){
            emit(Resource.Error(message = "Oops, something went wrong!"))

        }
        catch (ex:IOException){
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        }
    }
    suspend fun getUpcomingMovies(apikey:String,accept:String,language:String,page:Int): Flow<Resource<UpcomingMoviesResponse>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(apiService.getUpcomingMovies(apikey,accept,language,page)))

        }catch (e:HttpException){
            emit(Resource.Error(message = "Oops, something went wrong!"))

        }
        catch (ex:IOException){
            emit(Resource.Error(message = "Couldn't reach server, check your internet connection."))
        }
    }


}