package com.ferbajoo.composevsxml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.ferbajoo.composevsxml.ui.features.homeScreen.HomeScreen
import com.ferbajoo.composevsxml.ui.theme.ComposeVsXmlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeVsXmlTheme {
                HomeScreen()
            }
        }
    }
}
