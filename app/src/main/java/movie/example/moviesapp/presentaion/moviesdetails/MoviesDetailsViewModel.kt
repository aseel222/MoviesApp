package movie.example.moviesapp.presentaion.moviesdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.get.costarikamopileapp.data.source.remote.Resource
import com.get.costarikamopileapp.domain.usecases.MoviesDetailsUseCase
import com.get.costarikamopileapp.domain.usecases.NowPlayingUseCase
import com.get.costarikamopileapp.domain.usecases.PopularMoviesUseCase
import com.get.costarikamopileapp.domain.usecases.UpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import movie.example.moviesapp.data.source.local.Constants
import movie.example.moviesapp.data.source.local.Constants.page
import movie.example.moviesapp.data.source.remote.dto.upcoming.Result
import movie.example.moviesapp.presentaion.movieslist.MoviesScreenState
import javax.inject.Inject
@HiltViewModel
class MoviesDetailsViewModel @Inject constructor (
    private val moviesDetailsUseCase: MoviesDetailsUseCase,
    private val stateHandle: SavedStateHandle

      )
    :ViewModel() {
    private var _state =
        mutableStateOf(MoviesDetailsScreenState(moviesDetailsResponse = null, isloading = true))
    val state: State<MoviesDetailsScreenState>
        get() = derivedStateOf {
            _state.value
        }
    private val movieId = stateHandle.get<Int>("movie_id")


    init {
        if (movieId != null) {
            getMoviesDetails(
                movieId,
                Constants.apikey,
                Constants.Accept,
                Constants.language,

            )
        }


    }


    fun getMoviesDetails(id:Int,token: String, accept: String, language: String) {
        viewModelScope.launch() {
            moviesDetailsUseCase(id,token, accept, language).onEach {
                when (it) {
                    is Resource.Loading -> {
                        _state.value = _state.value.copy(isloading = true, moviesDetailsResponse =null, error = null)
                    }

                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isloading = false,
                            moviesDetailsResponse = it.data,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(isloading = false, moviesDetailsResponse =null, error = it.message)
                    }


                }
            }.launchIn(this)


        }
    }

}













