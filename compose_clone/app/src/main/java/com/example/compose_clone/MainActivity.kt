package com.example.compose_clone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose_clone.navigation.NavGraph
import com.example.compose_clone.ui.theme.Compose_cloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    Greeting()
//                    val navController = rememberNavController()
//                    NavGraph(navController = navController)
                }

        }
    }
}

@Composable
fun Greeting() {
    val navController = rememberNavController()
    NavGraph(navController = navController)
}

