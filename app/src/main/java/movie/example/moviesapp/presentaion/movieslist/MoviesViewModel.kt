package movie.example.moviesapp.presentaion.movieslist

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.get.costarikamopileapp.data.source.remote.Resource
import com.get.costarikamopileapp.domain.usecases.NowPlayingUseCase
import com.get.costarikamopileapp.domain.usecases.PopularMoviesUseCase
import com.get.costarikamopileapp.domain.usecases.UpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import movie.example.moviesapp.data.source.local.Constants
import movie.example.moviesapp.data.source.remote.dto.upcoming.Result
import javax.inject.Inject
@HiltViewModel
class MoviesViewModel @Inject constructor (
    private val nowPlayingUseCase: NowPlayingUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val upcomingMoviesUseCase: UpcomingMoviesUseCase,
    private val stateHandle: SavedStateHandle

      )
    :ViewModel() {
    private var _state =
        mutableStateOf(MoviesScreenState(nowplayingmovielist = emptyList(), popularmovielist = emptyList(), upcomingmovieslist = emptyList(), isloading = true))
    val state: State<MoviesScreenState>
        get() = derivedStateOf {
            _state.value
        }


    init {
        getNowPlaying(
            Constants.apikey,
            Constants.Accept,
            Constants.language,
            Constants.page
        )
        getPopularMovies(
            Constants.apikey,
            Constants.Accept,
            Constants.language,
            Constants.page
        )
        getUpcomingMovies(
            Constants.apikey,
            Constants.Accept,
            Constants.language,
            Constants.page
        )

    }


    fun getNowPlaying(token: String, accept: String, language: String, page: Int) {
        viewModelScope.launch() {
            nowPlayingUseCase(token, accept, language, page).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isloading = true, nowplayingmovielist = emptyList(), error = null)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isloading = false,
                            nowplayingmovielist = it.data?.results,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(isloading = false, nowplayingmovielist = emptyList(), error = it.message)
                    }


                }
            }.launchIn(this)


        }
    }
    fun getPopularMovies(token: String, accept: String, language: String, page: Int) {
        viewModelScope.launch() {
            popularMoviesUseCase(token, accept, language, page).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isloading = true, popularmovielist = emptyList(), error = null)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isloading = false,
                            popularmovielist = it.data?.results,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(isloading = false, popularmovielist = emptyList(), error = it.message)
                    }


                }
            }.launchIn(this)


        }
    }
    fun getUpcomingMovies(token: String, accept: String, language: String, page: Int) {
        viewModelScope.launch() {
            upcomingMoviesUseCase(token, accept, language, page).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isloading = true, upcomingmovieslist = emptyList(), error = null)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isloading = false,
                            upcomingmovieslist = it.data?.results ,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(isloading = false, upcomingmovieslist = emptyList(), error = it.message)
                    }


                }
            }.launchIn(this)


        }
    }
}













