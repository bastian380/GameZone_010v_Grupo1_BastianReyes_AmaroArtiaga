package com.gamezone.ui



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


data class Game(val name: String, val genre: String, val rating: Float)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopGamesScreen(onBackClicked: () -> Unit) {
    // Datos de ejemplo para el listado
    val topGames = listOf(
        Game("Elden Ring", "Action RPG", 4.9f),
        Game("God of War Ragnarök", "Action-Adventure", 4.8f),
        Game("Cyberpunk 2077", "RPG / Shooter", 4.5f),
        Game("The Witcher 3: Wild Hunt", "Action RPG", 4.7f),
        Game("Red Dead Redemption 2", "Action-Adventure", 4.9f),
        Game("Baldur's Gate 3", "RPG Táctico", 5.0f),
        Game("Final Fantasy XVI", "Action RPG", 4.5f),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Listado de Top Juegos") },
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
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(topGames) { game ->
                GameListItem(game = game)
            }
        }
    }
}


@Composable
fun GameListItem(game: Game) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = game.genre,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = "${game.rating} ⭐",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}