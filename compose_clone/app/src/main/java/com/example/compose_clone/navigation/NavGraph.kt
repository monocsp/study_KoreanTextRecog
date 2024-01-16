package com.example.compose_clone.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_clone.screens.BottomNav
import com.example.compose_clone.screens.Home
import com.example.compose_clone.screens.Splash

@Composable
fun NavGraph(navController :NavHostController ){
    NavHost(navController = navController, startDestination = Routes.Splash.routes ) {
        composable(Routes.Splash.routes){
            Splash(navController = navController)
        }

        composable(Routes.Home.routes){
            Home()
        }

        composable(Routes.Home.routes){
            BottomNav(navController = navController)
        }


    }
//    NavHost(navController = navController, startDestination = Routes.Splash.routes)

}