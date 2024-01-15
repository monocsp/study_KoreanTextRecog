package com.example.compose_clone.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_clone.navigation.BottomNavItem
import com.example.compose_clone.navigation.Routes

@Composable
fun BottomNav(navController: NavController) {
    val navController1 = rememberNavController()

    Scaffold (bottomBar = MyBottomBar(navController1)){

    }

}
@Composable
fun MyBottomBar(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState();

    val list = listOf<BottomNavItem>(
        BottomNavItem(title = "Home", route = Routes.Home.routes, icon = Icons.Rounded.Home)
    )

    BottomAppBar {
        list.forEach{
            val selected = it.route == backStackEntry.value?.destination?.route
            NavigationBarItem(selected = selected,
                onClick = { navController.navigate(it.route) {

                    popUpTo(navController.graph.findStartDestination().id ) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }


                }, icon = { Icon(imageVector = it.icon, contentDescription = it.title) })
        }
    }

}
