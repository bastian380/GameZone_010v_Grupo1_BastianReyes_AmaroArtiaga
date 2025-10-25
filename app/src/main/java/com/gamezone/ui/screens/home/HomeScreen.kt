package com.gamezone.ui.screens.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.gamezone.R
import kotlin.math.absoluteValue

data class CarouselItem(
    @DrawableRes val imageResId: Int,
    val title: String,
    val description: String = ""
)


data class NavigationItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val action: () -> Unit
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    onNavigateToTopGames: () -> Unit,
    onNavigateToNovelties: () -> Unit,
    onNavigateToExplore: () -> Unit,
    onNavigateToCreateUser: () -> Unit
) {
    val backgroundColor = MaterialTheme.colorScheme.background
    val onBackgroundColor = MaterialTheme.colorScheme.onBackground
    val primaryColor = MaterialTheme.colorScheme.primary
    val surfaceColor = MaterialTheme.colorScheme.surface

    val carouselItems = listOf(
        CarouselItem(
            R.drawable.dark,
            "Dark Souls Remastered",
            "Una aventura épica llena de desafíos"
        ),
        CarouselItem(
            R.drawable.elbrujas,
            "The Witcher 3: Wild Hunt",
            "Vive la historia de Geralt de Rivia"
        ),
        CarouselItem(
            R.drawable.minecraft,
            "Minecraft",
            "Crea y explora mundos infinitos"
        ),
        CarouselItem(
            R.drawable.read,
            "Red Dead Redemption 2",
            "El salvaje oeste como nunca lo viste"
        ),
        CarouselItem(
            R.drawable.zelda,
            "The Legend of Zelda",
            "Una aventura legendaria te espera"
        )
    )

    val navigationItems = listOf(
        NavigationItem(
            title = "Top Juegos",
            icon = Icons.Filled.Star,
            action = onNavigateToTopGames
        ),
        NavigationItem(
            title = "Novedades",
            icon = Icons.AutoMirrored.Filled.ArrowForward,
            action = onNavigateToNovelties
        )
    )

    val pagerState = rememberPagerState(pageCount = { carouselItems.size })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "GameZone",
                style = MaterialTheme.typography.headlineSmall,
                color = primaryColor,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = onNavigateToCreateUser,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                ),
                modifier = Modifier.size(height = 40.dp, width = 120.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Crear cuenta",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
                Text("Registro", style = MaterialTheme.typography.labelSmall)
            }
        }

        // CAROUSEL MEJORADO
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Juegos Destacados",
                style = MaterialTheme.typography.titleLarge,
                color = onBackgroundColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) { page ->
                val item = carouselItems[page]
                val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction

                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp)
                        .graphicsLayer {
                            val scale = lerp(
                                start = 0.8f,
                                stop = 1f,
                                fraction = 1f - pageOffset.absoluteValue.coerceIn(0f, 1f)
                            )
                            scaleX = scale
                            scaleY = scale
                            alpha = scale
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = surfaceColor)
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        Image(
                            painter = painterResource(id = item.imageResId),
                            contentDescription = item.title,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )

                        // Gradient overlay
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            Color.Black.copy(alpha = 0.7f)
                                        ),
                                        startY = 0.5f
                                    )
                                )
                        )

                        // Content
                        Column(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .padding(12.dp)
                        ) {
                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            if (item.description.isNotEmpty()) {
                                Text(
                                    text = item.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.8f),
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }


            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                repeat(carouselItems.size) { index ->
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(
                                if (index == pagerState.currentPage)
                                    primaryColor
                                else
                                    primaryColor.copy(alpha = 0.3f)
                            )
                    )
                }
            }
        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Explorar",
                    style = MaterialTheme.typography.titleMedium,
                    color = onBackgroundColor,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Navigation Items - CORREGIDO
                navigationItems.forEachIndexed { index, navItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navItem.action() }
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = navItem.title,
                                tint = primaryColor,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.padding(horizontal = 12.dp))
                            Text(
                                text = navItem.title,
                                style = MaterialTheme.typography.bodyMedium,
                                color = onBackgroundColor
                            )
                        }
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Ir a ${navItem.title}",
                            tint = primaryColor.copy(alpha = 0.6f),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    if (index < navigationItems.size - 1) {
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 8.dp))
                    }
                }
            }
        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = surfaceColor),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Últimas Noticias",
                    style = MaterialTheme.typography.titleMedium,
                    color = onBackgroundColor,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                HorizontalDivider(modifier = Modifier.padding(bottom = 12.dp))

                Text(
                    text = "🎉 ¡La comunidad de GameZone ha superado los 100.000 miembros! " +
                            "Participa en nuestros torneos exclusivos y sé parte de esta gran familia gamer. " +
                            "Próximamente: lanzamiento de nuevos juegos AAA disponibles solo para miembros registrados.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = onBackgroundColor.copy(alpha = 0.8f),
                    textAlign = TextAlign.Justify,
                    lineHeight = MaterialTheme.typography.bodyMedium.lineHeight * 1.2
                )
            }
        }


        Button(
            onClick = onNavigateToExplore,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
        ) {
            Text(
                text = "Explorar Todos los Juegos",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}