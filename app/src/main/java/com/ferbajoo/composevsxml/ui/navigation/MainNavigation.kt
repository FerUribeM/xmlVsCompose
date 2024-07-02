package com.ferbajoo.composevsxml.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ferbajoo.composevsxml.ui.features.chatCompose.compose.ChatsScreen
import com.ferbajoo.composevsxml.ui.features.homeScreen.Home
import com.ferbajoo.composevsxml.ui.features.greenCompose.GreenScreen
import com.ferbajoo.composevsxml.ui.features.greenCompose.GreenScreenRoute
import com.ferbajoo.composevsxml.ui.features.redCompose.RedScreen
import com.ferbajoo.composevsxml.ui.features.redCompose.RedScreenRoute

@Composable
fun MainNavigation(modifier: Modifier, controller: NavHostController) {
    NavHost(
        navController = controller,
        startDestination = Home,
        modifier = modifier
    ) {
        composable<Home> {
            ChatsScreen()
        }
        composable<RedScreenRoute> {
            RedScreen()
        }
        composable<GreenScreenRoute> {
            GreenScreen()
        }
    }

}
