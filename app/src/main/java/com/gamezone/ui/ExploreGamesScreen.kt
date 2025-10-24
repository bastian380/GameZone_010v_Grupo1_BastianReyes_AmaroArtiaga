package com.gamezone.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.gamezone.R // Necesario para cargar imágenes (Placeholder)



data class GameDetail(
    val name: String,
    val genre: String,
    val description: String,

    val imageResId: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreGamesScreen(onBackClicked: () -> Unit) {

    val allGames = listOf(
        GameDetail(
            "Dark Souls Remastered", "Action RPG",
            "La brutal obra maestra que definió un género. Explora Lordran, supera jefes colosales y descubre los secretos de un mundo en decadencia.",
            R.drawable.dark // Usamos un drawable existente como placeholder
        ),
        GameDetail(
            "Minecraft", "Sandbox",
            "Un mundo de bloques infinito donde la única limitación es tu imaginación. Construye, explora y sobrevive.",
            R.drawable.minecraft
        ),
        GameDetail(
            "The Witcher 3: Wild Hunt", "Action RPG",
            "Ponte en la piel de Geralt de Rivia, un cazador de monstruos a sueldo. Explora un vasto mundo abierto lleno de decisiones morales y criaturas épicas.",
            R.drawable.elbrujas
        ),
        GameDetail(
            "Red Dead Redemption 2", "Action-Adventure",
            "La épica historia de Arthur Morgan y la banda de Van der Linde. Un inmenso mundo abierto del Lejano Oeste con una atención al detalle sin precedentes.",
            R.drawable.read
        ),
        GameDetail(
            "Halo Infinite", "FPS",
            "El Jefe Maestro regresa en una nueva aventura para salvar a la humanidad. Combate a través de las ruinas de un misterioso anillo.",
            R.drawable.zelda // Placeholder
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explorar Todos los Juegos") },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp) // Aumentamos el espacio para el nuevo contenido
        ) {
            items(allGames) { game ->
                GameDetailItem(detail = game)
            }
        }
    }
}



@Composable
fun GameDetailItem(detail: GameDetail) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Image(
                    painter = painterResource(id = detail.imageResId),
                    contentDescription = "Carátula de ${detail.name}",
                    modifier = Modifier
                        .size(80.dp), // Tamaño definido para la imagen
                    contentScale = ContentScale.Crop
                )


                Column {
                    Text(
                        text = detail.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = detail.genre,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }


            Text(
                text = detail.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 8.dp)
            )


            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { /* Navegar a la página de detalle del juego */ },
                modifier = Modifier.align(Alignment.End)) {
                Text("Ver Detalle")
            }
        }
    }
}