package com.example.compose_clone.navigation

sealed class Routes (val routes : String){
    object Home : Routes("home")
    object Splash : Routes("splash")
    object BottomNav : Routes("bottom_nav")


}