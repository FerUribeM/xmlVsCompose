package com.ferbajoo.composevsxml.ui.features.chatCompose

import android.content.Intent
import android.widget.Toast
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.contentColorFor
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
import coil.compose.rememberAsyncImagePainter
import com.ferbajoo.composevsxml.R
import com.ferbajoo.composevsxml.ui.features.chatCompose.compose.CustomToolbar
import com.ferbajoo.composevsxml.ui.features.chatXml.XmlActivity
import com.ferbajoo.composevsxml.util.getEmojiRandom
import kotlin.random.Random
import kotlinx.serialization.Serializable

@Serializable
object Home

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    context.startActivity(Intent(context, XmlActivity::class.java))
    Scaffold(
        bottomBar = {
            CustomBottomNavigation()
        },
        floatingActionButton = {
            CustomFloatingButton()
        }
    ) { padding ->
        ChatsContent(modifier = Modifier.padding(padding))
    }
}

@Composable
fun ChatsContent(modifier: Modifier) {
    val context = LocalContext.current
    LazyColumn(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        item {
            CustomToolbar()
        }
        item {
            HeaderMenu()
        }
        items(30) {
            ChatItem(it) {
                Toast.makeText(context, "Click in element $it", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun CustomFloatingButton() {
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
fun HeaderMenu() {
    var indexSelected by remember { mutableIntStateOf(0) }
    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
        ItemHeaderButton(stringResource(id = R.string.all), isSelected = indexSelected == 0) {
            indexSelected = 0
        }
        Spacer(modifier = Modifier.width(10.dp))
        ItemHeaderButton(stringResource(id = R.string.unread), isSelected = indexSelected == 1) {
            indexSelected = 1
        }
        Spacer(modifier = Modifier.width(10.dp))
        ItemHeaderButton(stringResource(id = R.string.group), isSelected = indexSelected == 2) {
            indexSelected = 2
        }
    }
}

@Composable
fun ItemHeaderButton(title: String, isSelected: Boolean, onSelectedItem: () -> Unit) {
    Box(
        modifier = Modifier
            .height(30.dp)
            .clickable { onSelectedItem() }
            .clip(shape = RoundedCornerShape(30))
            .background(if (isSelected) Color(0xFFDBFAC6) else Color(0xFFFAFAFA))
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color(0xFF075E54),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ChatItem(id: Int, onSelectedItem: () -> Unit) {
    val loremIpsum = LoremIpsum(Random.nextInt(1, 5)).values.shuffled()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onSelectedItem() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(70.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://randomuser.me/api/portraits/men/$id.jpg"),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape)
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nombre $id ${getEmojiRandom()}", fontWeight = FontWeight.Bold)
                Text(text = "02:${if (id < 10) "0$id" else id} PM", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.Gray)
            }
            Text(
                text = loremIpsum.joinToString(),
                maxLines = 1
            )
        }
    }
}

@Composable
fun CustomBottomNavigation() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        Pair(Icons.Default.Email, "Chats"),
        Pair(Icons.Default.MailOutline, "Updates"),
        Pair(Icons.Default.Person, "Communities"),
        Pair(Icons.Default.Call, "Calls")
    )
    NavigationBar(containerColor = Color(0xFFFAFAFA)) {
        items.forEachIndexed { index, item ->
            val isSelected = selectedItem == index
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    Color(0xFF075E54),
                    Color.Black,
                    Color(0x8025D366),
                    Color.Black,
                    Color.Black,
                    Color.Black,
                    Color.Black,
                ),
                selected = isSelected,
                onClick = { selectedItem = index },
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