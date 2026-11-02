package com.example.trackingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF101014)
private val SurfaceDark = Color(0xFF1B1B23)
private val Accent = Color(0xFFB58AFF)
private val Muted = Color(0xFF9999A8)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniShelfApp()
        }
    }
}

@Composable
fun AniShelfApp() {
    var selectedTab by rememberSaveable { mutableIntStateOf(0) }

    val tabs = listOf(
        "Home",
        "Discover",
        "Library",
        "Statistics"
    )

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = Accent,
            background = Background,
            surface = SurfaceDark,
            onBackground = Color.White,
            onSurface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Background,
            bottomBar = {
                NavigationBar(
                    containerColor = SurfaceDark
                ) {
                    tabs.forEachIndexed { index, title ->
                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = {
                                Text(
                                    listOf("⌂", "⌕", "▣", "▤")[index],
                                    fontSize = 20.sp
                                )
                            },
                            label = { Text(title) }
                        )
                    }
                }
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Background)
                    .padding(padding)
            ) {
                when (selectedTab) {
                    0 -> HomeScreen()
                    1 -> PlaceholderScreen("Discover", "Find your next obsession.")
                    2 -> PlaceholderScreen("My Library", "Your personal entertainment collection.")
                    3 -> PlaceholderScreen("Statistics", "Your entertainment journey, visualized.")
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Text(
            text = "ANISHELF",
            color = Accent,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 3.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Your entertainment universe.",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Keep track of everything you love.",
            color = Muted,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "YOUR ACTIVITY",
            color = Muted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard("12", "Watching", Modifier.weight(1f))
            StatCard("28", "Completed", Modifier.weight(1f))
            StatCard("5", "Planning", Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "CONTINUE WATCHING",
            color = Muted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        MediaPlaceholder(
            title = "Your next episode awaits",
            subtitle = "Add anime or TV shows to your library."
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "RECENTLY ADDED",
            color = Muted,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        MediaPlaceholder(
            title = "Your collection is waiting",
            subtitle = "Discover something new to watch or read."
        )
    }
}

@Composable
fun StatCard(
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(SurfaceDark, RoundedCornerShape(14.dp))
            .padding(12.dp)
    ) {
        Text(
            text = value,
            color = Accent,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = label,
            color = Muted,
            fontSize = 11.sp
        )
    }
}

@Composable
fun MediaPlaceholder(
    title: String,
    subtitle: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(SurfaceDark, RoundedCornerShape(16.dp))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("✦", color = Accent, fontSize = 28.sp)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            title,
            color = Color.White,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            subtitle,
            color = Muted,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PlaceholderScreen(
    title: String,
    description: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            title,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(description, color = Muted)
    }
}
