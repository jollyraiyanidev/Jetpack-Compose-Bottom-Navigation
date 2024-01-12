package com.app.navigationsample.bottombar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.navigationsample.uiscreen.RecipeScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            RecipeScreen()
        }
        composable(route = BottomBarScreen.Report.route) {
            //ReportScreen()
        }
        composable(route = BottomBarScreen.Profile.route) {
           // ProfileScreen()
        }
    }
}