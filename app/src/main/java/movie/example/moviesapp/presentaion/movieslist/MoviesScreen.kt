package movie.example.moviesapp.presentaion.movieslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import movie.example.moviesapp.data.source.local.Constants
import movie.example.moviesapp.data.source.remote.dto.nowplaying.NowPlayingResponse
import movie.example.moviesapp.data.source.remote.dto.nowplaying.Result




@Composable
fun moviesScreen(state: MoviesScreenState,onitemclick:(Int)->Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 10.dp, end = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        movieCategory(category = "Now Playing")
       
        LazyRow(
            Modifier
                .fillMaxWidth(),
        ) {
           items(state.nowplayingmovielist ?: arrayListOf()){
               nowplayingMovieItem(result = it) {id->
                   onitemclick(id)
               }
           }
        }
        if (state.isloading) CircularProgressIndicator()
        if(state.error!=null){
            Text(text = state.error)
        }
        movieCategory(category = "Popular Movies")
        LazyRow {
            items(state.popularmovielist ?: arrayListOf()){
             popularMovieItem(result = it) {id->
                 onitemclick(id)
             }

            }
        }
        if (state.isloading) CircularProgressIndicator()
        if(state.error!=null){
            Text(text = state.error)
        }
        movieCategory(category = "Upcoming Movies")
        LazyRow {
            items(state.upcomingmovieslist ?: arrayListOf()){
                UpcomingMovieItem(result = it) {id->
                    onitemclick(id)
                }

            }
        }
        if (state.isloading) CircularProgressIndicator()
        if(state.error!=null){
            Text(text = state.error)
        }

    }
}

@Composable
fun nowplayingMovieItem(result: Result,onclick:(Int)->Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {

        Card(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentSize()
                .clickable { result.id?.let { onclick(it) } },
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            AsyncImage(model =Constants.baseurl+result.poster_path, modifier = Modifier.width(120.dp).height(100.dp), contentDescription = "", contentScale = ContentScale.FillBounds)

        }

            result.title?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .width(100.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
            }
            result.release_date?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding( top = 3.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
            }





    }
}
    @Composable
    fun popularMovieItem(
        result: movie.example.moviesapp.data.source.remote.dto.popular.Result,
        onclick: (Int) -> Unit
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Card(
                modifier = Modifier
                    .padding(5.dp)
                    .wrapContentSize()
                    .clickable { result.id?.let { onclick(it) } },
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(5.dp)
            ) {
                AsyncImage(model =Constants.baseurl+result.poster_path, modifier = Modifier.width(120.dp).height(100.dp), contentDescription = "", contentScale = ContentScale.FillBounds)

            }
            result.title?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(top = 3.dp)
                        .width(100.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Bold
                )
            }
            result.release_date?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding( top = 3.dp),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
            }


        }
    }
@Composable
fun UpcomingMovieItem(
    result: movie.example.moviesapp.data.source.remote.dto.upcoming.Result,
    onclick: (Int) -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Card(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentSize()
                .clickable { result.id?.let { onclick(it) } },
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            AsyncImage(model =Constants.baseurl+result.poster_path, modifier = Modifier.width(120.dp).height(100.dp), contentDescription = "", contentScale = ContentScale.FillBounds)
        }

        result.title?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .width(100.dp),
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }
        result.release_date?.let {
            Text(
                text = it,
                modifier = Modifier.padding( top = 3.dp),
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal
            )
        }


    }
}




@Composable
fun movieCategory(category:String){
    Text(
        text = category,
        modifier = Modifier.padding(10.dp),
        color = Color.Black,
        textAlign = TextAlign.Center,
        fontSize = TextUnit.Unspecified,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.ExtraBold
    )
}