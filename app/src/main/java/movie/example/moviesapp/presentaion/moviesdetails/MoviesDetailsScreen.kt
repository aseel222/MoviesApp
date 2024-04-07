package movie.example.moviesapp.presentaion.moviesdetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import movie.example.moviesapp.R
import movie.example.moviesapp.data.source.local.Constants

@Composable
fun moviesDetailsScreen(state: MoviesDetailsScreenState,onbackclicked:()->Unit){

    Column( horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
        Row (horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 15.dp)){
            Image(painter = painterResource(id = R.drawable.back) , contentDescription ="", modifier =Modifier.clickable { onbackclicked() } .width(20.dp).height(20.dp))
        }
        Card(  modifier = Modifier
            .padding(20.dp)
            .wrapContentSize(),
            shape = RoundedCornerShape(10.dp),
            elevation = CardDefaults.cardElevation(5.dp)) {
            AsyncImage(model = Constants.baseurl+state.moviesDetailsResponse?.poster_path, modifier = Modifier
                .width(220.dp)
                .height(320.dp), contentDescription = "", contentScale = ContentScale.FillBounds)


        }
        state.moviesDetailsResponse?.original_title?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(top = 3.dp)
                    ,
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 14.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.ExtraBold
            )
        }
        state.moviesDetailsResponse?.overview?.let {
            Text(
                text = it,
                modifier = Modifier
                    .padding(top = 3.dp)
                    ,
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }
        state.moviesDetailsResponse?.genres?.forEach {

            Text(
                text =it?.name.toString(),
                modifier = Modifier
               .padding(top = 3.dp),
                color = Color.DarkGray,
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text =state.moviesDetailsResponse?.runtime.toString(),
            modifier = Modifier
                .padding(top = 3.dp),
            color = Color.DarkGray,
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold
        )

    }
}