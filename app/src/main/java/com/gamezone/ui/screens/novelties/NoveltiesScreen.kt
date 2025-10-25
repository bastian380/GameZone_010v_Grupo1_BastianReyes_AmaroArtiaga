package com.gamezone.ui.screens.novelties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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

data class NoveltyItem(val title: String, val date: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoveltiesScreen(onBackClicked: () -> Unit) {
    // Datos de ejemplo para el listado de Novedades
    val novelties = listOf(
        NoveltyItem("Anuncio: Nuevo DLC para Dark Souls", "2025-10-20"),
        NoveltyItem("Fecha de lanzamiento: Nuevo juego de Zelda", "2025-10-15"),
        NoveltyItem("Actualización de comunidad: Nueva interfaz de chat", "2025-10-10"),
        NoveltyItem("Oferta de la semana: ¡Minecraft al 50%!", "2025-10-05"),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Últimas Novedades") },
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
            items(novelties) { item ->
                NoveltyListItem(item = item)
            }
        }
    }
}


@Composable
fun NoveltyListItem(item: NoveltyItem) {
    Card(
        modifier = Modifier.Companion.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(
            modifier = Modifier.Companion.padding(16.dp).fillMaxWidth()
        ) {
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.Companion.height(4.dp))
            Text(
                text = "Publicado: ${item.date}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
        }
    }
}