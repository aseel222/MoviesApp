package movie.example.moviesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import dagger.hilt.android.AndroidEntryPoint
import movie.example.moviesapp.presentaion.moviesdetails.MoviesDetailsViewModel
import movie.example.moviesapp.presentaion.moviesdetails.moviesDetailsScreen
import movie.example.moviesapp.presentaion.movieslist.MoviesViewModel
import movie.example.moviesapp.presentaion.movieslist.moviesScreen
import movie.example.moviesapp.ui.theme.MoviesAppTheme
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
             MoviesApp()
            }
        }
    }
}



@Composable
private fun MoviesApp(){
    val navController= rememberNavController()

    NavHost(navController =navController , startDestination ="movies"  ){
        composable(route="movies"){
            val vm:MoviesViewModel= hiltViewModel()

            moviesScreen(state =vm.state.value) {
                navController.navigate("movies/$it")
            }

        }

        composable(route="movies/{movie_id}", arguments = listOf(navArgument("movie_id"){
            type= NavType.IntType


        })
        ){
            val vm2:MoviesDetailsViewModel= hiltViewModel()
            //val movieid=it.arguments?.getInt("movie_id")
            moviesDetailsScreen(state =vm2.state.value , onbackclicked ={navController.popBackStack()})

        }
    }

}
