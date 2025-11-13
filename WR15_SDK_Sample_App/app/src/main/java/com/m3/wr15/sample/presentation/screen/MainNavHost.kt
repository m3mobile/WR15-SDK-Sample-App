package com.m3.wr15.sample.presentation.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.m3.wr15.sample.presentation.navigation.Routes
import com.m3.wr15.sample.presentation.screen.home.HomeScreen
import com.m3.wr15.sample.presentation.screen.s2p.S2PScreen
import com.m3.wr15.sample.presentation.screen.search.SearchScreen
import com.m3.wr15.sample.presentation.screen.home.configure.SettingScreen

@Composable
fun MainNavHost(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN_SCREEN
    ) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen(innerPadding, navController)
        }

        composable(Routes.S2P_SCREEN) {
            S2PScreen(innerPadding, navController)
        }

        composable(Routes.SEARCH_SCREEN) {
            SearchScreen(innerPadding, navController)
        }

        composable(Routes.HOME_SCREEN) {
            HomeScreen(innerPadding, navController)
        }

        composable(Routes.SETTING_SCREEN) {
            SettingScreen(innerPadding, navController)
        }
    }
}