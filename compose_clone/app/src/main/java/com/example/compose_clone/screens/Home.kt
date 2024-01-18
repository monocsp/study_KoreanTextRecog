package com.example.compose_clone.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScopeInstance.align
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Home (){
    Scaffold (topBar = { TopAppBar(title = { Text(text = "usb")
    })}){innerPadding->

        Column(modifier = Modifier
            .padding(innerPadding,)
            .background(Color(0xffffffff))){
            Column (modifier = Modifier.fillMaxSize().padding(horizontal = 10.dp, vertical = 20.dp)){
                Text(text = "신분증 종류")
                Row(modifier = Modifier.align(Alignment.)){
                    ElevatedButton(onClick = {  }) {
                        Text("주민등록증")
                    }
                    ElevatedButton(onClick = {  }) {
                        Text("운전면허증")
                    }

                }

            }


        }
    }


}