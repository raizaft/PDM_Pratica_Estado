package ifpb.edu.br.pdm.pdm_pratica_estado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ifpb.edu.br.pdm.pdm_pratica_estado.ui.theme.PDM_Pratica_EstadoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PDM_Pratica_EstadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ArtGallery(modifier = Modifier.padding(it))
                }
            }
        }
    }
}

data class ArtPiece(val name: String, val artist: String, val imageResId: Int, val contentDescription: String? = null)

@Composable
fun ArtGallery(modifier: Modifier = Modifier) {
    val artPieces = listOf(
        ArtPiece("London Eye - Londres", "Raiza Tomazoni, 2014", R.drawable.dsc_5922, contentDescription = "London Eye"),
        ArtPiece("Edimburgo", "Raiza Tomazoni, 2014", R.drawable.dsc_6475, contentDescription = "Edimburgo"),
        ArtPiece("Budapeste - Hungria", "Raiza Tomazoni, 2014", R.drawable.dsc_6807, contentDescription = "Budapeste"),
        ArtPiece("Deserto do Saara", "Raiza Tomazoni, 2015", R.drawable.dsc_7602, contentDescription = "Deserto do Saara"),
        ArtPiece("Marrakech - Marrocos", "Raiza Tomazoni, 2015", R.drawable.dsc_7702, contentDescription = "Marrakech"),
        ArtPiece("Marrakech - Marrocos", "Raiza Tomazoni, 2015", R.drawable.dsc_8109, contentDescription = "Marrakech"),
        ArtPiece("Veneza - Itália", "Raiza Tomazoni, 2015", R.drawable.dsc_8686_2, contentDescription = "Veneza"),
        ArtPiece("Mikonos - Grécia", "Raiza Tomazoni, 2015", R.drawable.img_1183, contentDescription = "Mikonos"),
        ArtPiece("Paris - França", "Raiza Tomazoni, 2015", R.drawable.img_2373, contentDescription = "Paris")
    )
    var currentIndex by remember { mutableStateOf(0) }

    val currentArt = artPieces[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 48.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageContainer(
            imageResId = currentArt.imageResId,
            contentDescription = currentArt.contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        ArtTitle(
            name = currentArt.name,
            artist = currentArt.artist,
            modifier = Modifier.padding(top = 16.dp)
        )

        Buttons(
            onPreviousClick = {
                if (currentIndex > 0) {
                    currentIndex--
                }
            },
            onNextClick = {
                if (currentIndex < artPieces.size - 1) {
                    currentIndex++
                }
            }
        )
    }
}

@Composable
fun ImageContainer(imageResId: Int, contentDescription: String?, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray)
    )
}

@Composable
fun ArtTitle(name: String, artist: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = artist,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun Buttons(onPreviousClick: () -> Unit, onNextClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onPreviousClick, contentPadding = PaddingValues(start = 40.dp, end = 40.dp, top = 16.dp, bottom = 16.dp)) {
            Text(
                text = "Anterior", fontSize = 16.sp)
        }
        Button(onClick = onNextClick, contentPadding = PaddingValues(start = 40.dp, end = 40.dp, top = 16.dp, bottom = 16.dp)) {
            Text(text = "Próximo", fontSize = 16.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    PDM_Pratica_EstadoTheme {
        ArtGallery()
    }
}