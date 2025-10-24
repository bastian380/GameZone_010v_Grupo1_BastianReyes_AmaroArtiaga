package com.gamezone.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gamezone.R


data class CarouselItem(
    @DrawableRes val imageResId: Int,
    val title: String
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onNavigateToTopGames: () -> Unit,
    onNavigateToNovelties: () -> Unit,
    onNavigateToExplore: () -> Unit
) {
    val bg = MaterialTheme.colorScheme.background
    val onBg = MaterialTheme.colorScheme.onBackground
    val containerColor = MaterialTheme.colorScheme.surfaceVariant


    val items = listOf(
        CarouselItem(R.drawable.dark, "Dark Souls Remastered"),
        CarouselItem(R.drawable.elbrujas, "The Witcher 3: Wild Hunt"),
        CarouselItem(R.drawable.minecraft, "Minecraft: La Aventura Infinita"),
        CarouselItem(R.drawable.read, "Red Dead Redemption 2"),
        CarouselItem(R.drawable.zelda, "The Legend of Zelda: Tears of the Kingdom")
    )
    val pagerState = rememberPagerState(pageCount = { items.size })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TÍTULO Y NAVEGACIÓN
            Text(
                text = "Bienvenido a GameZone",
                style = MaterialTheme.typography.headlineMedium,
                color = onBg
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Top Juegos
                Text(
                    text = "Top Juegos",
                    style = MaterialTheme.typography.bodyLarge,
                    color = onBg,
                    modifier = Modifier.clickable { onNavigateToTopGames() }
                )
                // Novedades
                Text(
                    text = "Novedades",
                    style = MaterialTheme.typography.bodyLarge,
                    color = onBg,
                    modifier = Modifier.clickable { onNavigateToNovelties() }
                )
            }


            Spacer(modifier = Modifier.height(8.dp))

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth().height(250.dp)
            ) { page ->
                val item = items[page]
                Card(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.imageResId),
                        contentDescription = item.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Overlay para el título
                    Box(
                        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.scrim.copy(alpha = 0.3f)),
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Text(
                            text = item.title,
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }



            Spacer(modifier = Modifier.height(16.dp))


            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = containerColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "¡Últimas Noticias de la Comunidad!",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    // Usamos HorizontalDivider para evitar la advertencia de 'deprecated'
                    HorizontalDivider()
                    Text(
                        text = "La comunidad de GameZone ha superado los 100.000 miembros. Sigue atento a los torneos y nuevos lanzamientos exclusivos para miembros. ¡Prepara tu equipo y a competir!",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                        textAlign = TextAlign.Justify
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = onNavigateToExplore) {
                Text("Explorar juegos")
            }
        }
    }
}