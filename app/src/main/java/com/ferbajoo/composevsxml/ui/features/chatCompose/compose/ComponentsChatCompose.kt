package com.ferbajoo.composevsxml.ui.features.chatCompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ferbajoo.composevsxml.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolbar() {
    var menuExpanded by remember { mutableStateOf(false) }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        title = {
            Text(
                text = "WhatsApp",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF128C7E)
            )
        },
        actions = {
            Row {
                IconButton(onClick = {  }) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_photo_camera_24),
                        contentDescription = "Camera"
                    )
                }
                IconButton(onClick = {  }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
                IconButton(onClick = { menuExpanded = !menuExpanded }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "Settings menu"
                    )
                }
                CustomDropDownMenu(menuExpanded){
                    menuExpanded = false
                }
            }
        },
    )
}

@Composable
fun CustomDropDownMenu(menuExpanded: Boolean,  onDismissRequest: () -> Unit) {
    DropdownMenu(
        expanded = menuExpanded,
        modifier = Modifier.background(Color.White),
        onDismissRequest = { onDismissRequest() },
    ) {
        ItemDropDown(text = "New group") {

        }
        ItemDropDown(text = "New broadcast") {

        }
        ItemDropDown(text = "Linked devices") {

        }
        DropdownMenuItem(
            text = {
                Text("Starred messages")
            },
            onClick = { },
        )
        DropdownMenuItem(
            text = {
                Text("Settings")
            },
            onClick = { },
        )
    }
}

@Composable
fun ItemDropDown(text: String, onClick: () -> Unit) {
    DropdownMenuItem(
        text = {
            Text(text)
        },
        onClick = onClick,
    )
}