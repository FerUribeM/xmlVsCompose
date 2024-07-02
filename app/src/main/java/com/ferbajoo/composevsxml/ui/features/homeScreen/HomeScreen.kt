package com.ferbajoo.composevsxml.ui.features.homeScreen

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.ferbajoo.composevsxml.R
import com.ferbajoo.composevsxml.ui.features.chatXml.XmlActivity
import com.ferbajoo.composevsxml.ui.features.greenCompose.GreenScreenRoute
import com.ferbajoo.composevsxml.ui.features.redCompose.RedScreenRoute
import com.ferbajoo.composevsxml.ui.navigation.MainNavigation
import com.ferbajoo.composevsxml.util.getEmojiRandom
import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation { index ->
                when (index) {
                    0 -> {
                        navController.navigate(Home)
                    }
                    1 -> {
                        navController.navigate(RedScreenRoute)
                    }
                    2 -> {
                        navController.navigate(GreenScreenRoute)
                    }
                }
            }
        }
    ) { padding ->
        MainNavigation(modifier = Modifier.padding(padding), navController)
    }
}



@Composable
fun FloatingButton() {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(0xFF25D366))
            .clickable {
                context.startActivity(Intent(context, XmlActivity::class.java))
            },
        contentAlignment = Alignment.Center,
    ) {
        Icon(Icons.Default.AddCircle, contentDescription = null, tint = Color.White)
    }
}

@Composable
fun BottomNavigation(onSelectedItem: (Int) -> Unit) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        Pair(Icons.Default.Email, "Chats"),
        Pair(Icons.Default.MailOutline, "Updates"),
        Pair(Icons.Default.Person, "Communities"),
        Pair(Icons.Default.Call, "Calls")
    )
    val barItemStyle = NavigationBarItemColors(
        Color(0xFF075E54),
        Color.Black,
        Color(0x8025D366),
        Color.Black,
        Color.Black,
        Color.Black,
        Color.Black,
    )
    NavigationBar(containerColor = Color(0xFFFAFAFA)) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            NavigationBarItem(
                colors = barItemStyle,
                selected = isSelected,
                onClick = {
                    selectedItem = index
                    onSelectedItem(selectedItem)
                },
                icon = { Icon(item.first, contentDescription = null) },
                label = {
                    Text(
                        text = item.second,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}