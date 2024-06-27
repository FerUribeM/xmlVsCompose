package com.ferbajoo.composevsxml.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ferbajoo.composevsxml.ui.features.chatCompose.Home
import com.ferbajoo.composevsxml.ui.features.chatCompose.HomeScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen()
        }
    }

}
