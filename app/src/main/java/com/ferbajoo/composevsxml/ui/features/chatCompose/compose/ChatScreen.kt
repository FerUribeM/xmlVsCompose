package com.ferbajoo.composevsxml.ui.features.chatCompose.compose

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ferbajoo.composevsxml.ui.features.homeScreen.FloatingButton
import com.ferbajoo.composevsxml.util.CustomToolbar
import com.ferbajoo.composevsxml.util.getEmojiRandom
import kotlin.random.Random

@Composable
fun ChatsScreen() {
    val context = LocalContext.current
    Scaffold(
        floatingActionButton = {
            FloatingButton()
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(Color.White)
                .fillMaxSize()
        ) {
            item {
                CustomToolbar()
            }
            item {
                FilterMenu()
            }
            items(30) {
                ChatItem(it) {
                    Toast.makeText(context, "Click in element $it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


@Composable
fun FilterMenu() {
    var indexSelected by remember { mutableIntStateOf(0) }
    Row(modifier = Modifier.padding(horizontal = 10.dp)) {
        ItemHeaderButton("All", isSelected = indexSelected == 0) {
            indexSelected = 0
        }
        Spacer(modifier = Modifier.width(10.dp))
        ItemHeaderButton("Unread", isSelected = indexSelected == 1) {
            indexSelected = 1
        }
        Spacer(modifier = Modifier.width(10.dp))
        ItemHeaderButton("Groups", isSelected = indexSelected == 2) {
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
            .background(if (isSelected) Color(0xFFDBFAC6) else Color(0xFFF3F3F3))
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
        UserImage(id)
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
                Text(
                    text = "02:${if (id < 10) "0$id" else id} PM",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
            Text(
                text = loremIpsum.joinToString(),
                maxLines = 1
            )
        }
    }
}

@Composable
fun UserImage(id: Int) {
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
}
