package com.example.compose_clone.screens

import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose_clone.navigation.BottomNavItem
import com.example.compose_clone.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavHostController) {
    val navController1 = rememberNavController()

    Scaffold (bottomBar = { MyBottomBar(navController1) }){innerPadding ->
        NavHost(navController = navController1, startDestination = Routes.Home.routes, modifier = Modifier.padding(innerPadding)){
            composable(route =  Routes.Home.routes){
                Home(navController)
            }
        }

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

                }


                }, icon = { Icon(imageVector = it.icon, contentDescription = it.title) })
        }
    }

}
