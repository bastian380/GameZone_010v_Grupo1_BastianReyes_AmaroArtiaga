package com.gamezone.ui.screens.topgames

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
            modifier = Modifier.Companion
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
        modifier = Modifier.Companion.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.Companion.padding(16.dp).fillMaxWidth(),
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